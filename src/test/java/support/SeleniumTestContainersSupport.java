package support;

import java.io.IOException;
import java.sql.SQLException;

import javax.script.ScriptException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.containers.MySQLContainer;

import template.CustomDeployer;

public class SeleniumTestContainersSupport extends TestContainersSupport {
    @SuppressWarnings("rawtypes")
    private static BrowserWebDriverContainer browser;
    
    @SuppressWarnings("rawtypes")
    private static MySQLContainer mysql;
    
    private static String WEB_URL;
    
    private static CustomDeployer deployer;
    
    private static final String DOCKER_HOST_IP = "172.17.0.1";
    
    @SuppressWarnings("rawtypes")
    @BeforeClass
    public static void startContainer() throws SQLException, ScriptException, IOException, UnsupportedOperationException {
        browser = new BrowserWebDriverContainer().withDesiredCapabilities(DesiredCapabilities.firefox())
                                                 .withRecordingMode(VncRecordingMode.SKIP, null);
        browser.start();
        
        String host;
        
        if (System.getenv("WEB_HOST") != null) {
            host = System.getenv("WEB_HOST");
        } else {
            host = DOCKER_HOST_IP;
        }
        
        WEB_URL = "http://" + host + ":8080/";
        
        System.out.println("Web Application URL: " + WEB_URL);
        
        mysql = createMySQLContainer();
        
        System.setProperty("spring.profiles.active", "integration");
        
        deployer = new CustomDeployer();
        deployer.deploy();
    }
    
    @AfterClass
    public static void stopContainer() {
        browser.stop();
        deployer.undeploy();
        mysql.stop();
    }
    
    public RemoteWebDriver getDriver() {
        return browser.getWebDriver();
    }
    
    public static String getWebURL() {
        return WEB_URL;
    }
}
