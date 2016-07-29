package template.controller.auth;

import com.ning.http.client.AsyncHttpClient;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starter.model.AbstractProfile;
import starter.security.ProfileService;
import starter.security.SecurityService;
import template.model.SigninForm;
import template.profile.CustomApplicationProfile;

@Controller
@RequestMapping("/auth/signin")
public class SignInController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CustomApplicationProfile applicationProfile;
    
    @RequestMapping(method = RequestMethod.GET)
    String get(@SuppressWarnings("unused") @ModelAttribute("form") SigninForm form, Model model) throws InterruptedException, ExecutionException, IOException {
        try (AsyncHttpClient client = new AsyncHttpClient()) {
            String body = client.prepareGet(applicationProfile.getCouchDbUrl()).execute().get().getResponseBody();
            
            model.addAttribute("couchdbResponse", body);
        }
      
        model.addAttribute("couchdbPort", applicationProfile.getCouchDbUrl());
        
        return "/auth/signin";
    }

    @RequestMapping(method = RequestMethod.POST)
    String signin(@ModelAttribute("form") @Valid SigninForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) return "/auth/signin";

        AbstractProfile profile = profileService.getById(form.getEmail());

        if (profile == null || !passwordEncoder.matches(form.getPassword(), profile.getPassword())) {
            result.rejectValue("email", "signin.incorrectEmailOrPassword", "Неверный логин или пароль");
            result.rejectValue("password", "emptymessage", "");

            return "/auth/signin";
        }
        
        if (!profile.isConfirmed()) {
            result.rejectValue("email", "signin.notConfirmed", "Данный аккаунт не подтвержден по e-mail");
            result.rejectValue("password", "emptymessage", "");

            return "/auth/signin";
        }

        securityService.auth(profile, request, response, form.isRememberMe());

        return "redirect:/";
    }
}
