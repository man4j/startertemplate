package integration.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import support.SeleniumTestSupport;

public class SeleniumIntegrationTest extends SeleniumTestSupport {
    @Test
    public void shouldWork() {
        String signInUrl = getWebInternalURL() + "/auth/signin";
        
        WebDriver driver = getDriver();
        
        driver.get(signInUrl);
        
        WebElement e = driver.findElement(By.id("submitButton"));
        
        Assert.assertNotNull(e);
        Assert.assertEquals("Войти", e.getText());
    }
}