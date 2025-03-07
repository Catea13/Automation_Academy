package tests.Steps;

import tests.Pages.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainSteps {
    static String selectedProductName;
    static String selectedProductPrice;
    static String selectedProductDescription;

    private static SignPage signPage = new SignPage();
    private static MainPage mainPage = new MainPage();
    private static ProductPageStore productPageStore = new ProductPageStore();
    private static ProductPage productPage = new ProductPage();
    private static CartPage cartPage = new CartPage();
    private static ContactPage contactPage = new ContactPage();
    private static final Map<String, Runnable> buttonActions = new HashMap<>();

    static {
        buttonActions.put("Sign Up", () -> mainPage.signUpLink.click());
        buttonActions.put("Sign up", () -> signPage.signUpButton.click());
        buttonActions.put("Log In", () -> mainPage.loginLink.click());
        buttonActions.put("Phones", () -> productPageStore.phonesCategories.click());
        buttonActions.put("Add to Cart", () -> productPage.addToCartButton.click());
        buttonActions.put("Cart", () -> mainPage.cartLink.click());
        buttonActions.put("Plaseaza", () -> cartPage.placeOrderButton.click());
        buttonActions.put("Contact", () -> mainPage.contactLink.click());
        buttonActions.put("Send Message", () ->  contactPage.sendMessageButton.click());
        buttonActions.put("Close", () ->   contactPage.closeButton.click());

    }


    private static final Map<String, Consumer<String>> fieldActions = new HashMap<>();

    static {
        fieldActions.put("Name", name -> {
            CartPage cartPage = new CartPage();
            cartPage.nameFieldPlaceOrder.click();
            cartPage.nameFieldPlaceOrder.sendKeys(name);

        });

        fieldActions.put("Country", country -> {
            CartPage cartPage = new CartPage();
            cartPage.countryFieldPlaceOrder.click();
            cartPage.countryFieldPlaceOrder.sendKeys(country);

        });

        fieldActions.put("City", city -> {
            CartPage cartPage = new CartPage();
            cartPage.cityFieldPlaceOrder.click();
            cartPage.cityFieldPlaceOrder.sendKeys(city);

        });

        fieldActions.put("CreditCard", creditCard -> {
            CartPage cartPage = new CartPage();
            cartPage.creditCardFieldPlaceOrder.click();
            cartPage.creditCardFieldPlaceOrder.sendKeys(creditCard);

        });

        fieldActions.put("Month", month -> {
            CartPage cartPage = new CartPage();
            cartPage.monthFieldPlaceOrder.click();
            cartPage.monthFieldPlaceOrder.sendKeys(month);

        });

        fieldActions.put("Year", year -> {
            CartPage cartPage = new CartPage();
            cartPage.yearFieldPlaceOrder.click();
            cartPage.yearFieldPlaceOrder.sendKeys(year);

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

    @And("clicks on the {string} button")
    public void clickOnTheButton(String button) {
        Selenide.sleep(1000);

        Runnable action = buttonActions.get(button);
        if (action != null) {
            action.run();
            Selenide.sleep(1000);
        } else {
            throw new IllegalArgumentException("Invalid button: " + button);
        }
    }

    @Then("an alert should appear with the message {string}")
    public void anAlertShouldAppearWithTheMessage(String expectedText) {
        Alert alert = switchTo().alert();
        String alertText = alert.getText();

        System.out.println(alertText);
        assertTrue(alertText.contains(expectedText));
        alert.accept();

    }


    @When("user add random product to cart")
    public void theUserAddRandomProductToCart() {
        MainSteps mainSteps = new MainSteps();
        mainSteps.clickOnTheButton("Phones");
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='card h-100']")));
        ElementsCollection products = $$(By.xpath("//div[@class='card h-100']"));


        // Assert that the list is not empty
        Assertions.assertTrue(products.size() > 0, "Products not found! The test cannot be performed.");

        // If products are found, select a random one (between 1 and products.size())
        if (products.size() > 0) {
            System.out.println("Size products" + products.size());
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(products.size() - 1) + 1;  // random index between 1 and products.size() - 1


        System.out.println("Random product" + randomIndex);
        WebElement selectedProduct = products.get(randomIndex);
        SelenideElement productName = $(By.xpath("(//h4[@class='card-title']//a[@class='hrefch'])[" + (randomIndex + 1) + "]"));
        selectedProductName = productName.getText();
        SelenideElement productPrice = $(By.xpath("(//h4[@class='card-title']//a[@class='hrefch']/following::h5[1])[" + (randomIndex + 1) + "]"));
        selectedProductPrice = productPrice.getText().trim() + " " + "*includes tax";
        SelenideElement productDescription = $(By.xpath("(//p[@id='article' and @class='card-text'])[" + (randomIndex + 1) + "]"));
        selectedProductDescription = productDescription.getText();
        selectedProduct.click();
        mainSteps.clickOnTheButton("Add to Cart");
        Alert alert = switchTo().alert();
        String alertText = alert.getText();

        System.out.println(alertText);
        assertTrue(alertText.contains("Product added"));
        alert.accept();
        mainSteps.clickOnTheButton("Cart");
        assertEquals("product name in the cart does not match", selectedProductName, cartPage.titleProduct.getText());

    }

    @When("the user clicks on the Cart button and verifies that the product name and price in the cart match the added product's name and price")
    public void theUserClicksOnTheCartButtonAndVerifiesThatTheProductNameAndPriceInTheCartMatchTheAddedProductSNameAndPrice() {
        MainSteps mainSteps = new MainSteps();
        mainSteps.clickOnTheButton("Cart");
        assertEquals("product name in the cart does not match", selectedProductName, cartPage.titleProduct.getText());
        String expectedPrice = selectedProductPrice.trim().replace("*includes tax", "").replace("$", "").trim();


        String actualPrice = cartPage.priceProduct.getText().trim().replace("$", "").trim();


        assertEquals("Product price in the cart does not match", expectedPrice, actualPrice);
        ElementsCollection productPrices = $$(By.xpath("//*[@id='tbodyid']//td[3]"));
        double totalPrice = 0;

        for (WebElement price : productPrices) {
            String priceText = price.getText().replace("$", "").trim();
            double priceValue = Double.parseDouble(priceText);
            totalPrice += priceValue;

        }

    }

    @And("user places the order from cart")
    public void userPlacesTheOrderFromCart() {
        MainSteps mainSteps = new MainSteps();
        mainSteps.clickOnTheButton("Plaseaza");
        theUserEntersValueInField( "Test","Name");
        theUserEntersValueInField( "Moldova","Country");
        theUserEntersValueInField("Chisinau","City");
        theUserEntersValueInField( "0123","CreditCard");
        theUserEntersValueInField("July","Month");
        theUserEntersValueInField("2025","Year");
        cartPage.purchaseButton.click();
    }
}