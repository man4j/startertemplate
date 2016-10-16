package support;

import java.io.IOException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTestSupport {
    private static WebDriver driver;
    
    private static String SELENIUM_URL = System.getProperty("selenium.url");
    private static String SELENIUM_VNC = System.getProperty("selenium.vnc");
    
    private static String WEB_INTERNAL_URL = "http://web:8080";
        
    @BeforeClass
    public static void before() throws IOException {
        if (SELENIUM_URL != null) {
            driver = new RemoteWebDriver(new URL(SELENIUM_URL), DesiredCapabilities.chrome());
            
            System.out.println("Selenium vnc addr: " + SELENIUM_VNC);
        } else {
            driver = new FirefoxDriver();
            
            WEB_INTERNAL_URL = "http://127.0.0.1:8080";
        }
    }
    
    @AfterClass
    public static void after() {
        driver.quit();
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public String getWebInternalURL() {
        return WEB_INTERNAL_URL;
    }
}
