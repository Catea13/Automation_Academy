package Hook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Hook {
    public WebDriver driver;
    private Properties properties;
    private final String path = "src/main/java/Config/config.properties";

    public Hook() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(path));
        properties=new Properties();
         properties.load(reader);
        reader.close();

    }

    public String getDriver() {
        String browser = properties.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("This browser not existed");
        }
        driver.manage().window().fullscreen();
        String url = properties.getProperty("url");
        driver.get(url);
        return browser;

    }

    public void start() {
        String browser = getDriver();
        getDriver();
    }

    public void close() {
        driver.quit();
    }

}