package integration.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private WebDriver webDriver;
    
    private String url;
    
    public BasePage(WebDriver driver, String url) {
        this.webDriver = driver;
        this.url = url;
        
        PageFactory.initElements(driver, this);
        
        driver.get(url);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
    
    public String getUrl() {
        return url;
    }
}
