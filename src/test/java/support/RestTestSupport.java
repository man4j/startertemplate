package support;

import java.io.IOException;
import java.sql.SQLException;

import javax.script.ScriptException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testcontainers.containers.MySQLContainer;

import template.CustomDeployer;

public class RestTestSupport extends TestContainersSupport {
    private static CustomDeployer deployer;
    
    @SuppressWarnings("rawtypes")
    private static MySQLContainer mysql;
    
    @BeforeClass
    public static void before() throws SQLException, ScriptException, IOException {
        mysql = createMySQLContainer();
        
        System.setProperty("spring.profiles.active", "integration");
        
        deployer = new CustomDeployer();
        deployer.deploy();
    }
    
    @AfterClass
    public static void stopContainer() {
        deployer.undeploy();
        mysql.stop();
    }
    
    public static String getWebURL() {
        return "http://localhost:8080";
    }
}
