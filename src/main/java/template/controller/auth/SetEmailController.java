package template.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starter.email.EmailService;
import starter.model.AbstractProfile;
import starter.security.SecurityService;
import starter.service.ProfileService;
import template.model.SetEmailForm;
import template.service.EmailSignInMessageGenerator;

@Controller
@RequestMapping("/auth/setEmail")
public class SetEmailController {
    @Autowired
    private ConnectionRepository repository;
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailSignInMessageGenerator generator;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private HttpSession session;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;
    
    @RequestMapping(method = RequestMethod.GET)
    public String get(@ModelAttribute("form") SetEmailForm form, Model model) {
        prepareModel(form, model);

        return "/auth/set_email";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute("form") @Valid SetEmailForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            prepareModel(form, model);
            
            return "/auth/set_email";
        }
        
        String socialUserId = getAndResetSocialUserId();
        Connection<?> connection = getSocialConnection(socialUserId);
        String socialEmail = connection.fetchUserProfile().getEmail();
        
        AbstractProfile profile;
        
        if (socialEmail != null) {
            profile = profileService.getByEmail(socialEmail);//если есть профиль в системе соответствующий профилю соц. сети, то связываем их
            
            if (profile != null) {
                return linkAndAuth(profile, socialUserId);
            }
        }
        
        profile = profileService.getByEmail(form.getEmail());//связываем с профайлом заданным юзером вручную
        
        boolean isConfirmed = form.getEmail().equals(socialEmail);
        
        if (profile == null) {
            profile = profileService.create(form.getEmail(), passwordEncoder.encode(securityService.generatePassword()), isConfirmed);
        }
        
        return isConfirmed ? linkAndAuth(profile, socialUserId) : requestEmailConfirmation(profile, socialUserId);
    }
    
    private void prepareModel(SetEmailForm form, Model model) {
        Connection<?> connection = getSocialConnection((String) session.getAttribute("notExistingUserId"));
        
        String socialEmail = connection.fetchUserProfile().getEmail();
        
        if (socialEmail != null) {
            AbstractProfile profile = profileService.getByEmail(socialEmail);
            
            if (profile != null) {
                model.addAttribute("profile", profile);
            }
            
            form.setEmail(socialEmail);
        }
        
        model.addAttribute("name", connection.getDisplayName());
        model.addAttribute("img", connection.getImageUrl());
        model.addAttribute("email", socialEmail);
    }

    private String requestEmailConfirmation(AbstractProfile profile, String socialUserId) {
        emailService.sendEmailAsync(generator.generate(profile, null, socialUserId));
        
        return "/auth/check_email";
    }
    
    private String linkAndAuth(AbstractProfile profile, String socialUserId) {
        securityService.updateSocialConnection(socialUserId, profile.getEmail());
        securityService.auth(profile, request, response, true);
        
        return "redirect:/";
    }

    private Connection<?> getSocialConnection(String socialUserId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(socialUserId, null));
        
        Connection<?> connection = repository.getConnection(securityService.getConnectionKeyByUserId(socialUserId));
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        return connection;
    }

    private String getAndResetSocialUserId() {
        String id = (String) session.getAttribute("notExistingUserId");
        
        session.setAttribute("notExistingUserId", null);
        
        return id;
    }
}
