package support;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.HierarchyMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;

import template.config.CustomDbConfig;
import template.config.CustomMvcConfig;
import template.config.CustomSecurityConfig;
import template.config.CustomSocialConfig;

/**
 * add DOCKER_HOST,DOCKER_TLS_VERIFY,DOCKER_CERT_PATH in OS env vars
 * f.e. 
 * DOCKER_HOST=tcp://kaizen-retail.com:2376
 * DOCKER_CERT_PATH=C:\Users\home1\.docker
 * DOCKER_TLS_VERIFY=1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CustomDbConfig.class, CustomMvcConfig.class, CustomSecurityConfig.class, CustomSocialConfig.class})
@ActiveProfiles("integration")
@DirtiesContext
public class SpringMvcTestContainersSupport {
    @Autowired
    private WebApplicationContext wac;

    @SuppressWarnings("rawtypes")
    private static MySQLContainer mysql;
    
    private MockMvc mockMvc;
    
    @BeforeClass
    public static void startContainer() {
        mysql = TestContainersSupport.createMySQLContainer();
    }

    @AfterClass
    public static void stopContainer() throws IOException {
        mysql.stop();
        TestContainersSupport.closeDockerClient(mysql);
    }
    
    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                                 .apply(SecurityMockMvcConfigurers.springSecurity())
                                 .build();
    }
    
    public WebApplicationContext getWac() {
        return wac;
    }
    
    public MockMvc getMockMvc() {
        return mockMvc;
    }
}
