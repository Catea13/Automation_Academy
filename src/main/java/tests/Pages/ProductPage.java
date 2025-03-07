package tests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    public SelenideElement productName= $(By.xpath("//h2[@class='name']"));
    public SelenideElement productPrice= $(By.xpath("//h3[@class='price-container']"));
    public SelenideElement productDescription= $(By.xpath("//div[@class='description description-tabs']//p"));
    public SelenideElement addToCartButton= $(By.xpath("//a[normalize-space(text())='Add to cart']"));

}
