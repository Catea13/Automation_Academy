package Steps;

import Hook.TestContext;
import Pages.CheckOutPage;
import Pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutSteps {
    private TestContext testContext;

    public CheckoutSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    @And("the user fills out the questionnaire Checkout with following data")
    public void theUserFillsOutTheQuestionnaireCheckoutWithFollowingData(DataTable values)throws Throwable {
        CheckOutPage checkOutPage = PageFactory.initElements(testContext.getHook().driver, CheckOutPage.class);
                List<List<String>> data = values.asLists();

        //This is to get the first data of the set (First Row + First Column)
      checkOutPage.firstNameCheckoutField.sendKeys(data.get(0).get(0));

        //This is to get the first data of the set (First Row + Second Column)
      checkOutPage.lastNameCheckoutField.sendKeys(data.get(0).get(1));
      checkOutPage.postalCodeCheckoutField.sendKeys(data.get(0).get(2));



    }
}
