package tests.Steps;

import tests.Pages.SignPage;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SignSteps {
    private static SignPage signPage = new SignPage();



    private static final Map<String, Consumer<String>> fieldActions = new HashMap<>();

    static {
        fieldActions.put("Username", value -> {
            signPage.usernameField.click();
            signPage.usernameField.sendKeys(value);
        });
        fieldActions.put("Password", value -> {
            signPage.passwordField.click();
            signPage.passwordField.sendKeys(value);
        });
    }



    public void theUserEntersValueInField(String value, String field) {

        Consumer<String> action = fieldActions.get(field);
        if (action != null) {
            action.accept(value);
            Selenide.sleep(1000);
        } else {
            throw new IllegalArgumentException("Invalid field: " + field);
        }
    }


    @When("the user enters a username and password and click Sign Up button")
    public void theUserEntersAUsernameAndPasswordAndClickSignUpButton() {

        theUserEntersValueInField("Ecaterina", "Username");

        theUserEntersValueInField("test", "Password");
        signPage.signUpButton.click();


        }


    @And("the user enters a empty username and password and click Sign Up button")
    public void theUserEntersAEmptyUsernameAndPasswordAndClickSignUpButton() {

        theUserEntersValueInField("", "Username");


        theUserEntersValueInField("test", "Password");
        signPage.signUpButton.click();
        }


    @And("the user enters a username and empty password and click Sign Up button")
    public void theUserEntersAUsernameAndEmptyPasswordAndClickSignUpButton() {

        theUserEntersValueInField("Ecaterina", "Username");
        theUserEntersValueInField("", "Password");
        signPage.signUpButton.click();

        }

}

