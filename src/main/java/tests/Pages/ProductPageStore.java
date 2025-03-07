package tests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPageStore {
    public SelenideElement phonesCategories=$(By.xpath("//a[@id='itemc'  and text()='Phones']"));
}
