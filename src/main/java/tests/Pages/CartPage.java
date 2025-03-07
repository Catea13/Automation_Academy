package tests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    public SelenideElement titleProduct=$(By.xpath("//td[text()][1]"));
    public SelenideElement priceProduct=$(By.xpath("//*[@id='tbodyid']//td[3]"));
    public SelenideElement totalPrice=$(By.xpath("//h3[@id='totalp']"));
    public SelenideElement placeOrderButton=$(By.xpath("//button[text()='Place Order']"));
    public SelenideElement nameFieldPlaceOrder=$(By.xpath("//input[@id='name']"));
    public SelenideElement countryFieldPlaceOrder=$(By.xpath("//input[@id='country']"));
    public SelenideElement cityFieldPlaceOrder=$(By.xpath("//input[@id='city']"));
    public SelenideElement creditCardFieldPlaceOrder=$(By.xpath("//input[@id='card']"));
    public SelenideElement monthFieldPlaceOrder=$(By.xpath("//input[@id='month']"));
    public SelenideElement yearFieldPlaceOrder=$(By.xpath("//input[@id='year']"));
    public SelenideElement purchaseButton=$(By.xpath("//button[text()='Purchase']"));
}
