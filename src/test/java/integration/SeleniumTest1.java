package integration;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest1 {
    @Test
    public void shouldWork() throws MalformedURLException {
        String seleniumUrl = System.getProperty("selenium.url");
        String undertowInternalUrl = System.getProperty("undertow.internalUrl");
        
        String signInUrl = undertowInternalUrl + "/auth/signin";
        
        WebDriver driver = new RemoteWebDriver(new URL(seleniumUrl), DesiredCapabilities.chrome());
        
        driver.get(signInUrl);
        
        WebElement e = driver.findElement(By.id("submitButton"));
        
        Assert.assertNotNull(e);
        Assert.assertEquals("Войти", e.getText());
        
        driver.quit();
    }
}
