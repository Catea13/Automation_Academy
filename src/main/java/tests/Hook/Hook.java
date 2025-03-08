package tests.Hook;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Hook {
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

        // Set browser options based on configuration
        if (browser.equals("chrome")) {
            // Устанавливаем браузер и его опции
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();

            // Очищаем все прокси настройки (не используем прокси)
            options.addArguments("start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-extensions");

            // Убираем headless режим, чтобы тесты запускались с интерфейсом
            Configuration.headless = false;

            // Используем WebDriverManager для автоматической настройки драйвера
            WebDriverManager.chromedriver().setup();  // Это автоматически настроит драйвер

            // Дополнительная конфигурация Selenide
            Configuration.browserSize = "1366x768"; // Размер окна браузера
            Configuration.pageLoadStrategy = "normal";
            Configuration.timeout = 17000; // Время ожидания
            Configuration.reportsFolder = "target/screenshots"; // Папка для отчетов
            Configuration.browserCapabilities = options;
        }

    else if (browser.equals("edge")) {
                WebDriverManager.edgedriver().setup();  // Set up Edge driver
                Configuration.browser = "edge";
                EdgeOptions edgeOptions = new EdgeOptions();
                Configuration.browserCapabilities = edgeOptions;
                edgeOptions.setExperimentalOption("mobileEmulation", Map.of("deviceName", "Samsung Galaxy A51/71"));
            }

            // Open the URL after setting up WebDriver
            String url = properties.getProperty("url");
            Selenide.open(url);  // Open URL to initialize WebDriver
            WebDriverRunner.getWebDriver().manage().window().maximize();  // Maximize window after opening URL

            return browser;
        }

    @Before
    public void setup() {
        System.out.println("Initializing WebDriver...");
        getDriverPath();  // Initialize WebDriver and open the URL
        if (WebDriverRunner.getWebDriver() == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        System.out.println("WebDriver successfully initialized.");
    }

    @After
    public void close(Scenario scenario) {
        try {
            System.out.println(scenario.getName() + " : " + scenario.getStatus());

            // Take screenshot if test fails
            String screenshotName = scenario.getName();
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close WebDriver only if it was started
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
        }

        System.out.println("Driver was closed.");
    }
}
