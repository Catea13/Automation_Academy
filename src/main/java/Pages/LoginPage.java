package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private WebDriver driver;
    @FindBy(id = "user-name")
    public WebElement usernameField;
    @FindBy(id = "password")
    public WebElement passwordField;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void typeInUserNameField(String name) {
        usernameField.sendKeys(name);
    }

    public void typeInUserPasswordField(String password) {
        passwordField.sendKeys(password);


    }

}


