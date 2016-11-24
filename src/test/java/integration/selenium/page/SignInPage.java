package integration.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    private static final String URL = "/auth/signin";
    
    @FindBy(id = "submitButton")
    private WebElement submitButton;

    public SignInPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl + URL);
    }
    
    public WebElement getSubmitButton() {
        return submitButton;
    }
}
