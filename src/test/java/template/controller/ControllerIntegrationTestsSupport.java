package template.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import starter.security.ProfileService;
import starter.security.SecurityService;
import template.config.CustomMvcConfig;
import template.config.CustomSecurityConfig;
import template.config.CustomSocialConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CustomMvcConfig.class, CustomSecurityConfig.class, CustomSocialConfig.class})
@ActiveProfiles("test")
public class ControllerIntegrationTestsSupport {
    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private ProfileService profileService;
    
    private MockMvc mockMvc;
    
    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                                 .apply(SecurityMockMvcConfigurers.springSecurity())
                                 .build();
    }
    
    public WebApplicationContext getWac() {
        return wac;
    }
    
    public SecurityService getSecurityService() {
        return securityService;
    }
    
    public ProfileService getProfileService() {
        return profileService;
    }
    
    public MockMvc getMockMvc() {
        return mockMvc;
    }
}
