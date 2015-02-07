package template.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starter.email.SimpleMessage;
import starter.email.TemplatedMessageGenerator;
import starter.model.AbstractProfile;
import starter.util.RequestUtil;

@Service
public class EmailSignInMessageGenerator {
    @Autowired
    private TemplatedMessageGenerator generator;
    
    @Autowired
    private HttpServletRequest httpRequest;
    
    public SimpleMessage generate(AbstractProfile profile, String decryptedPassword) {
        Map<String, String> map = new HashMap<>();

        map.put("email", profile.getEmail());
        map.put("uuid", profile.getConfirmUuid());
        map.put("baseUrl", RequestUtil.getAppURL(httpRequest));
        map.put("password", decryptedPassword);        
        
        String body = generator.createMessageFromTemplate("/email/email_signin.ftl", map);
        
        return new SimpleMessage("support@example.com", "SUPPORT", profile.getEmail(), body, "Вход в аккаунт");
    }
}
