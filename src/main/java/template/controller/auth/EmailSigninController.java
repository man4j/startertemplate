package template.controller.auth;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import starter.model.AbstractProfile;
import starter.security.SecurityService;
import starter.service.ProfileService;

@Controller
@RequestMapping("/auth/email")
public class EmailSigninController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    String signin(@RequestParam String uuid, String socialUserId, HttpServletRequest request, HttpServletResponse response) {
        AbstractProfile profile = profileService.getByConfirmUuid(uuid);

        if (profile == null) {
            return "/auth/expired_link";
        }
        
        profile.setConfirmUuid(UUID.randomUUID().toString());//Одну и ту же ссылку нельзя использовать дважды

        profile.setConfirmed(true); //если аккаунт не подтвержден

        profileService.update(profile);
        
        if (socialUserId != null) {
            securityService.updateSocialConnection(socialUserId, profile.getEmail());
        }

        securityService.auth(profile, request, response, true);

        return "redirect:/";
    }
}