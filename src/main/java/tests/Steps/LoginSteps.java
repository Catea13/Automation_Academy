package tests.Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.Pages.LoginPage;
import tests.Pages.MainPage;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import org.junit.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.codeborne.selenide.Condition.visible;

public class LoginSteps {
    private static LoginPage loginPage=new LoginPage();
    private static MainPage mainPage = new MainPage();
    private static final Map<String, Consumer<String>> fieldActions = new HashMap<>();
    static {
        fieldActions.put("Username", value -> {
            loginPage.usernameField.click();
            loginPage.usernameField.sendKeys(value);
        });
        fieldActions.put("Password", value -> {
            loginPage.passwordField.click();

            loginPage.passwordField.sendKeys(value);
        });
    }
    public void entersValueInField(String value, String field) {
        Consumer<String> action = fieldActions.get(field);
        if (action != null) {
            action.accept(value);
        } else {
            throw new IllegalArgumentException("Invalid field: " + field);
        }
    }




    @And("the user logs in with valid credentials and sees the {string} and {string} buttons")
    public void theUserLogsInWithValidCredentialsAndSeesTheLogOutAndWelcomeButtons(String welcome,String logout) {
        entersValueInField("Ecaterina","Username");
        entersValueInField("test","Password");
        loginPage.loginButton.click();
        mainPage.logoutLink.shouldBe(visible,Duration.ofSeconds(10));
        mainPage.welcomeLink.shouldBe(visible,Duration.ofSeconds(10));
        Assert.assertTrue( mainPage.logoutLink.isDisplayed());
        Assert.assertTrue( mainPage.welcomeLink.isDisplayed());
    }

    @And("the user enters a username and password and click Log in button")
    public void theUserEntersAUsernameAndPasswordAndClickLogInButton() {
        entersValueInField("Ecaterina","Username");
        entersValueInField("test","Password");
        loginPage.loginButton.click();
        Assert.assertTrue( mainPage.logoutLink.isDisplayed());
        Assert.assertTrue( mainPage.welcomeLink.isDisplayed());
    }

    @And("the user enters a invalid username and password and click Log in button")
    public void theUserEntersAInvalidUsernameAndPasswordAndClickLogInButton() {
        entersValueInField("InvalidName","Username");
        entersValueInField("test","Password");
        loginPage.loginButton.click();
    }

    @And("the user enters username and invalid password and click Log in button")
    public void theUserEntersUsernameAndInvalidPasswordAndClickLogInButton() {
        entersValueInField("Ecaterina","Username");
        entersValueInField("invalidPassword","Password");
        loginPage.loginButton.click();
    }

    @And("the user enters a username and empty password and click Log in button")
    public void theUserEntersAUsernameAndEmptyPasswordAndClickLogInButton() {
        entersValueInField("Ecaterina","Username");
        entersValueInField("","Password");
        loginPage.loginButton.click();
    }
    @And("the user enters a empty username and password and click Log in button")
    public void theUserEntersAEmptyUsernameAndPasswordAndClickLogInButton() {
        entersValueInField("", "Username");
        entersValueInField("test", "Password");
        loginPage.loginButton.click();
    }

    @Given("user is logged in")
    public void userIsLoggedIn() {

        mainPage.loginLink.click();
        entersValueInField("Ecaterina","Username");
        entersValueInField("test","Password");
        loginPage.loginButton.click();
        mainPage.logoutLink.shouldBe(visible, Duration.ofSeconds(10));
        mainPage.welcomeLink.shouldBe(visible,Duration.ofSeconds(10));
        Assert.assertTrue( mainPage.logoutLink.isDisplayed());
        Assert.assertTrue( mainPage.welcomeLink.isDisplayed());
    }

}