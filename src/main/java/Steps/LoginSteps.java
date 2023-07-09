package Steps;


import Hook.TestContext;
import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

public class LoginSteps {
    private TestContext testContext;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
    }


    @When("fill in {string} with {string}")
    public void fillInWith(String field, String value) {
        LoginPage loginPage = PageFactory.initElements(testContext.getHook().driver, LoginPage.class);
        switch (field) {
            case "Name":
                loginPage.usernameField.sendKeys(value);
                break;
            case "Password":
                loginPage.passwordField.sendKeys(value);
                break;
            default:
                System.out.println("This field" + field + " " + "not existed");

        }
    }

    @And("click  {string} button")
    public void clickButton(String button) {
        LoginPage loginPage = PageFactory.initElements(testContext.getHook().driver, LoginPage.class);
        switch (button) {
            case "Login":
                loginPage.loginButton.click();
                break;
        }
    }
}