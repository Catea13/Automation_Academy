package tests.Steps;

import tests.Pages.CartPage;
import tests.Pages.ProductPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static tests.Steps.MainSteps.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertEquals;


public class UISteps {
    ProductPage productPage=new ProductPage();

    @Then("the product name should be the same as the selected product")
    public void theProductNameShouldBeTheSameAsTheSelectedProduct() {

        assertEquals("Product name does not match", productPage.productName.getText(),selectedProductName);
    }

    @And("the product price should match the selected price")
    public void theProductPriceShouldMatchTheSelectedPrice() {
        assertEquals("Product price does not match", productPage.productPrice.getText(),selectedProductPrice);
    }

    @And("the product description should match the selected description")
    public void theProductDescriptionShouldMatchTheSelectedDescription() {
        assertEquals("Product description does not match", productPage.productDescription.getText(),selectedProductDescription);
    }

    @Then("the product name in the cart should be the same as the added product name")
    public void theProductNameInTheCartShouldBeTheSameAsTheAddedProductName() {
        CartPage cartPage=new CartPage();
        assertEquals("product name in the cart does not match", selectedProductName,cartPage.titleProduct.getText());
    }

    @And("the product price in the cart should match the added product price")
    public void theProductPriceInTheCartShouldMatchTheAddedProductPrice() {
        CartPage cartPage = new CartPage();


        String expectedPrice = selectedProductPrice.trim().replace("*includes tax", "").replace("$", "").trim();


        String actualPrice = cartPage.priceProduct.getText().trim().replace("$", "").trim();


        assertEquals("Product price in the cart does not match", expectedPrice, actualPrice);
    }

    @Then("the total price should be correct based on the cart items")
    public void theTotalPriceShouldBeCorrectBasedOnTheCartItems() {
        CartPage cartPage=new CartPage();
        ElementsCollection productPrices = $$(By.xpath("//*[@id='tbodyid']//td[3]"));
        double totalPrice = 0;

        for (WebElement price : productPrices) {
            String priceText = price.getText().replace("$", "").trim();
            double priceValue = Double.parseDouble(priceText);
            totalPrice += priceValue;
        }

        System.out.println("Total Price: " + totalPrice);
        double actualTotalPrice= Double.parseDouble(cartPage.totalPrice.getText());
        Assert.assertEquals("Total price does not match", totalPrice, actualTotalPrice,0.1);
        }


          @Then("purchase confirmation is received")
              public void purchaseConfirmationIsReceived() {
        SelenideElement confirmationMessage =$(By.xpath("//h2[text()='Thank you for your purchase!']"));
        String displayedText = confirmationMessage.getText();
        Assert.assertEquals("The confirmation message is incorrect", "Thank you for your purchase!", displayedText);
    }

}

