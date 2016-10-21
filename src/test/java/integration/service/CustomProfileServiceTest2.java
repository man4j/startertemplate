package integration.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import support.SpringMvcTestContainersSupport;
import template.model.UserProfile;
import template.service.CustomProfileService;

public class CustomProfileServiceTest2 extends SpringMvcTestContainersSupport {
    @Autowired
    private CustomProfileService customProfileService;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private PlatformTransactionManager txManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void shouldWork() {
        UserProfile up = customProfileService.create("userId", "qwe@mail.ru", passwordEncoder.encode("123456"), true);
        
        up.setEmail("asd@mail.ru");
        up.getRoles().add("role1");
        
        customProfileService.update(up);
        
        new TransactionTemplate(txManager).execute(s -> {
            UserProfile up1 = customProfileService.getById("userId");
           
            em.remove(up1);
            
            return null;
        });
    }
}
