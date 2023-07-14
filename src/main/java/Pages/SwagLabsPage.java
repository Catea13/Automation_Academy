package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwagLabsPage {
    private WebDriver driver;
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement addBackpack;
    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartContainer;



    public SwagLabsPage(WebDriver driver) {
        this.driver = driver;
    }


}

