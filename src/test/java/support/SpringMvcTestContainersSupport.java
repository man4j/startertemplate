package support;

import java.io.IOException;
import java.sql.SQLException;

import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class SpringMvcTestContainersSupport {
    @Autowired
    private WebApplicationContext wac;

    @SuppressWarnings("rawtypes")
    public static MySQLContainer mysql = new MySQLContainer() {
        @Override
        public String getJdbcUrl() {
            return "jdbc:mysql://" + getContainerIpAddress() + ":" + getMappedPort(3306) + "/test_db";
        }
        
        @Override
        protected void configure() {
            optionallyMapResourceParameterAsVolume("TC_MY_CNF", "/etc/mysql/conf.d");

            addExposedPort(3306);
            addEnv("MYSQL_DATABASE", "test_db");
            addEnv("MYSQL_USER", "test");
            addEnv("MYSQL_PASSWORD", "test");
            addEnv("MYSQL_ROOT_PASSWORD", "test");
            setCommand("mysqld");
            setStartupAttempts(3);        
        }
    };
    
    private MockMvc mockMvc;
    
    @BeforeClass
    public static void startContainer() throws SQLException, ScriptException, IOException {
        mysql.start();
        
        try (java.sql.Connection conn = mysql.createConnection("")) {
            org.testcontainers.jdbc.ext.ScriptUtils.executeSqlScript(conn, "", IOUtils.toString(SpringMvcTestContainersSupport.class.getResourceAsStream("/dump.sql")));
        }
        
        System.setProperty("db.url", mysql.getJdbcUrl());
        System.setProperty("db.user", mysql.getUsername());
        System.setProperty("db.password", mysql.getPassword());
    }

    @AfterClass
    public static void stopContainer() {
        mysql.stop();
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
