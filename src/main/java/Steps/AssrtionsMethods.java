package Steps;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AssrtionsMethods {
    WebDriver driver=new ChromeDriver();
    @Then("appear next text {string}")
    public void appearNextText(String text) {
        Assert.assertTrue(driver.getPageSource().equals(text));


    }
}
