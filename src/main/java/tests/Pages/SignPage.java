package tests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SignPage {
    public SelenideElement usernameField=$(By.id("sign-username"));
    public SelenideElement passwordField=$(By.id("sign-password"));
    public SelenideElement signUpButton=$(By.xpath("//button[contains(@class, 'btn btn-primary') and text()='Sign up']"));
}
