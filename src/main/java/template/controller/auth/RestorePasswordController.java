package template.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starter.email.EmailService;
import starter.model.AbstractProfile;
import starter.security.ProfileService;
import starter.security.SecurityService;
import template.model.RestorePasswordForm;
import template.service.EmailSignInMessageGenerator;

@Controller
@RequestMapping("/auth/restore")
public class RestorePasswordController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private EmailSignInMessageGenerator generator;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public void get(@SuppressWarnings("unused") @ModelAttribute("form") RestorePasswordForm form) {
        // empty
    }

    @RequestMapping(method = RequestMethod.POST)
    public String restore(@ModelAttribute("form") @Valid RestorePasswordForm form, BindingResult result) {
        if (result.hasErrors()) return "/auth/restore";

        AbstractProfile profile = profileService.getById(form.getEmail());
        
        if (profile == null) {
            result.rejectValue("email", "email.notExists", "Данный E-mail не зарегистрирован");
            
            return "/auth/restore";
        }

        String newDecryptedPassword = securityService.generatePassword();

        profile.setPassword(passwordEncoder.encode(newDecryptedPassword));

        profileService.saveOrUpdate(profile);

        emailService.sendEmailAsync(generator.generate(profile, newDecryptedPassword));

        return "/auth/check_email";
    }
}