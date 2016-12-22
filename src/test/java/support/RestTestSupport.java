package support;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testcontainers.containers.MySQLContainer;

import template.CustomDeployer;

public class RestTestSupport {
    private static CustomDeployer deployer;
    
    @SuppressWarnings("rawtypes")
    private static MySQLContainer mysql;
    
    @BeforeClass
    public static void before() {
        mysql = TestContainersSupport.createMySQLContainer();
        
        System.setProperty("spring.profiles.active", "integration");
        
        deployer = new CustomDeployer();
        deployer.deploy();
    }
    
    @AfterClass
    public static void stopContainer() throws IOException {
        deployer.undeploy();
        mysql.stop();
        TestContainersSupport.closeDockerClient(mysql);
    }
    
    public static String getWebURL() {
        return "http://localhost:8080";
    }
}
