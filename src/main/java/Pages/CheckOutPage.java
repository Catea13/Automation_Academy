package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage {
    private WebDriver driver;

    @FindBy(id = "first-name")
    public WebElement firstNameCheckoutField;

    @FindBy(id = "last-name")
    public WebElement lastNameCheckoutField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeCheckoutField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }
}
