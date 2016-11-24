package support;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.containers.MySQLContainer;

import com.jcraft.jsch.Session;

import template.CustomDeployer;

public class SeleniumTestContainersSupport extends TestContainersSupport {
    @SuppressWarnings("rawtypes")
    private static BrowserWebDriverContainer browser;
    
    @SuppressWarnings("rawtypes")
    private static MySQLContainer mysql;
    
    private static String WEB_URL;
    
    private static CustomDeployer deployer;
    
    private static Session sshSession;
    
    private static final String DOCKER_HOST_IP = "172.17.0.1";
    
    @SuppressWarnings("rawtypes")
    @BeforeClass
    public static void startContainer() throws UnsupportedOperationException, IOException {
        int port = 8080;
        
        if (System.getenv("DOCKER_HOST") != null) {        
            Object[] result = createTunnel();
            
            sshSession = (Session) result[0];
            port = (int) result[1];
        }
        
        browser = new BrowserWebDriverContainer().withDesiredCapabilities(DesiredCapabilities.firefox())
                                                 .withRecordingMode(VncRecordingMode.SKIP, null);
        browser.start();
        
        WEB_URL = "http://" + DOCKER_HOST_IP + ":" + port;
        
        System.out.println("Web Application URL: " + WEB_URL);
        System.out.println("VNC URL: " + browser.getVncAddress());
        
        mysql = createMySQLContainer();
        
        System.setProperty("spring.profiles.active", "integration");
        
        deployer = new CustomDeployer();
        deployer.deploy();
    }
    
    @AfterClass
    public static void stopContainer() throws IOException {
        browser.getWebDriver().quit();
        browser.stop();
        
        deployer.undeploy();
        
        mysql.stop();
        mysql.getDockerClient().close();//Закрывает расшаренный докер-клиент для всех контейнеров
        
        if (sshSession != null) {
            sshSession.disconnect();
        }
    }
        
    public RemoteWebDriver getDriver() {
        return browser.getWebDriver();
    }
    
    public static String getWebURL() {
        return WEB_URL;
    }
}
