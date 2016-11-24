package integration.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import support.SpringMvcTestContainersSupport;
import template.model.UserProfile;
import template.service.CustomProfileService;

public class CustomProfileServiceTest extends SpringMvcTestContainersSupport {
    @Autowired
    private CustomProfileService customProfileService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void shouldWork() {
        UserProfile up = customProfileService.create("qwe@mail.ru", passwordEncoder.encode("123456"), true);
        
        up.setEmail("asd@mail.ru");
        up.getRoles().add("role1");
        
        customProfileService.update(up);
        
        Assert.assertNotNull(customProfileService.getByEmail("asd@mail.ru"));
    }
}
