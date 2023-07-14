package Steps;

import Hook.TestContext;
import Pages.CheckOutPage;
import Pages.LoginPage;
import Pages.SwagLabsPage;
import Pages.YourCartPage;
import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;

public class ShoppingSteps {
    private TestContext testContext;

    public ShoppingSteps(TestContext testContext) {

        this.testContext = testContext;
    }

    @And("click the button {string}")
    public void clickTheButton(String button) {
        SwagLabsPage swagLabsPage = PageFactory.initElements(testContext.getHook().driver, SwagLabsPage.class);
        CheckOutPage checkOutPage = PageFactory.initElements(testContext.getHook().driver, CheckOutPage.class);
        YourCartPage yourCartPage = PageFactory.initElements(testContext.getHook().driver, YourCartPage.class);
        switch (button) {
            case "Add to cart":
                swagLabsPage.addBackpack.click();
                break;
            case "ShoppingCartContainer":
                swagLabsPage.shoppingCartContainer.click();
                break;
            case "Checkout":
                yourCartPage.checkOutButton.click();
                break;


        }
    }


}
