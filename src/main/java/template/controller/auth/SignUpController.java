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
import starter.service.ProfileService;
import template.model.SignUpForm;
import template.service.EmailSignInMessageGenerator;

@Controller
@RequestMapping("/auth/signup")
public class SignUpController {
    @Autowired
    private EmailSignInMessageGenerator generator;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    String get(@SuppressWarnings("unused") @ModelAttribute("form") SignUpForm form) {
        return "/auth/signup";
    } 

    @RequestMapping(method = RequestMethod.POST)
    String signup(@ModelAttribute("form") @Valid SignUpForm form, BindingResult result) {
        if (result.hasErrors()) return "/auth/signup";
        
        if (profileService.getByEmail(form.getEmail()) != null) {
            result.rejectValue("email", "email.notUnique", "Данный E-mail уже зарегистрирован");
            
            return "/auth/signup";
        }

        AbstractProfile profile = profileService.create(form.getEmail(), passwordEncoder.encode(form.getPassword()), false);

        emailService.sendEmailAsync(generator.generate(profile, form.getPassword()));

        return "/auth/check_email";
    }
}