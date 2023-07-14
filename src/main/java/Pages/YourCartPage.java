package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourCartPage {
    private WebDriver driver;
    @FindBy(className = "inventory_item_desc")
    WebElement description;

    @FindBy(id = "checkout")
    public WebElement checkOutButton;

    public YourCartPage(WebDriver driver) {
        this.driver = driver;
    }


}
