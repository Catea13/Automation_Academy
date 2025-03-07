package tests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public SelenideElement contactLink=$(By.xpath("//a[@class='nav-link' and text()='Contact']"));
    public SelenideElement signUpLink=$(By.xpath("//a[@id='signin2' and text()='Sign up']"));
    public SelenideElement  loginLink=$(By.xpath("//a[@id='login2' and @data-toggle='modal' and @data-target='#logInModal' and text()='Log in']"));
    public SelenideElement  logoutLink=$(By.xpath("//a[@class='nav-link' and text()='Log out']"));
    public SelenideElement  welcomeLink=$(By.xpath("//a[@class='nav-link' and contains(text(), 'Welcome')]"));
    public SelenideElement  cartLink=$(By.xpath("//a[@class='nav-link' and contains(text(), 'Cart')]"));
}
