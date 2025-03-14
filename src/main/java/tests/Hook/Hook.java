package tests.Hook;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class  Hook{
    public WebDriver driver;
    private Properties properties;
    private final String propertyFilePath = "src/main/java/tests/Config/config.properties";

    public Hook() {
        // Load the configuration properties
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String browser = properties.getProperty("browser");
        String env = properties.getProperty("env");

        // Set the browser options based on the configuration

        if (browser.equals("chrome")) {
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();

            // Добавление аргументов для безголового режима и других необходимых флагов
            options.addArguments(
                    "--headless",                   // Безголовый режим
                    "--disable-gpu",                // Отключение GPU
                    "--no-sandbox",                 // Для CI/CD
                    "--disable-dev-shm-usage",      // Для работы в контейнерах
                    "--remote-debugging-port=9222", // Для удаленной отладки
                    "--window-size=1366,768"        // Установка размера окна
            );

            Configuration.browserSize = "1366x768";
            Configuration.headless = true;
            Configuration.pageLoadStrategy = "normal";
            Configuration.timeout = 15000;
            Configuration.reportsFolder = "target/screenshots";
            Configuration.browserCapabilities = options;
        } else if (browser.equals("edge")) {
            Configuration.browser = "edge";
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            Configuration.browserCapabilities = edgeOptions;
            edgeOptions.setExperimentalOption("mobileEmulation", Map.of("deviceName", "Samsung Galaxy A51/71"));
        }

        // Открываем URL после того как WebDriver настроен
        String url = properties.getProperty("url");
        Selenide.open(url); // Открываем URL
        WebDriverRunner.getWebDriver().manage().window().maximize(); // Разворачиваем окно

        return browser;
    }

    @Before
    public void setup() {
        getDriverPath(); // Ensure WebDriver is initialized and URL is opened
    }

    @After
    public void close(Scenario scenario) {
        try {
            System.out.println(scenario.getName() + " : " + scenario.getStatus());

            String screenshotName = scenario.getName();
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Selenide.closeWebDriver(); // Close the WebDriver after the scenario
        System.out.println("Driver was closed");
    }
}
