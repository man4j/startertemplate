package template.fixture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import starter.service.ProfileService;

@Component
@Profile({"dev", "test"})
public class ProfileFixture {
    @Autowired
    private ProfileService myProfileService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct
    public void init() {
        if (myProfileService.getById("man4j@ya.ru") == null) {
            myProfileService.create("man4j@ya.ru", "man4j@ya.ru", passwordEncoder.encode("123456"), true);
        }
    }
}
