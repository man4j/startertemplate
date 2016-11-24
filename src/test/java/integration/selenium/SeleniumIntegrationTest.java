package integration.selenium;

import org.junit.Assert;
import org.junit.Test;

import integration.selenium.page.SignInPage;
import support.SeleniumTestContainersSupport;

public class SeleniumIntegrationTest extends SeleniumTestContainersSupport {
    @Test
    public void shouldWork() {
        SignInPage signInPage = new SignInPage(getDriver(), getWebURL());
        
        Assert.assertEquals("Войти", signInPage.getSubmitButton().getText());
    }
}
