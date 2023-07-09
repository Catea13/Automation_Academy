package Steps;


import Hook.TestContext;
import Pages.LoginPage;
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
                loginPage.typeInUserNameField(value);
                break;
            case "Password":
                loginPage.typeInUserPasswordField(value);
                break;
            default:
                System.out.println("This field" + field + " " + "not existed");

        }
    }
}