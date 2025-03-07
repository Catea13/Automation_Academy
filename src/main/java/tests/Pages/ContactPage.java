package tests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ContactPage {
    public SelenideElement contactEmailField=$(By.id("recipient-email"));
    public SelenideElement contactNameField=$(By.id("recipient-name"));
    public SelenideElement messageField=$(By.id("message-text"));
    public SelenideElement sendMessageButton=$(By.xpath("//button[contains(@class, 'btn btn-primary') and text()='Send message']"));
    public SelenideElement closeButton=$(By.xpath("//button[contains(@class, 'btn btn-secondary') and text()='Close']"));

}
