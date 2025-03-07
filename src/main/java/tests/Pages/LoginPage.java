package tests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement usernameField=$(By.id("loginusername"));
    public SelenideElement passwordField=$(By.id("loginpassword"));
    public SelenideElement loginButton=$(By.xpath("//button[@onclick='logIn()' and contains(@class, 'btn-primary')]"));
}
