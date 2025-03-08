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

        // Set the browser options based on the configuration
        if (browser.equals("chrome")) {
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            Configuration.browserSize = "1366x768";
            Configuration.headless = true;  // Run browser in headless mode
            Configuration.pageLoadStrategy = "normal";
            Configuration.timeout = 15000;
            Configuration.reportsFolder = "target/screenshots";
            Configuration.browserCapabilities = options;
        } else if (browser.equals("edge")) {
            Configuration.browser = "edge";

            // Make sure the driver version is up to date
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();

            // Generate a unique user data directory for each test run
            String uniqueUserDataDir = "/tmp/selenium/userDataDir_" + System.currentTimeMillis(); // Unique directory for each test run
            edgeOptions.addArguments("--user-data-dir=" + uniqueUserDataDir);  // Set the unique user data directory
            edgeOptions.addArguments("--disable-dev-shm-usage", "--window-size=1366,768"); // Disable dev-shm usage for CI environments
            edgeOptions.setExperimentalOption("mobileEmulation", Map.of("deviceName", "Samsung Galaxy A51/71"));
            Configuration.browserCapabilities = edgeOptions;
        }

        // Open the URL after setting the WebDriver options
        String url = properties.getProperty("url");
        Selenide.open(url); // Open the URL to initialize WebDriver
        WebDriverRunner.getWebDriver().manage().window().maximize(); // Maximize the window after opening the URL

        return browser;
    }

    @Before
    public void setup() {
        // Call open(url) to initialize the WebDriver and ensure it's bound to the current thread
        getDriverPath(); // Ensure WebDriver is initialized and URL is opened
    }

    @After
    public void close(Scenario scenario) {
        try {
            // Capture the status of the scenario
            System.out.println(scenario.getName() + " : " + scenario.getStatus());

            // Take screenshot if the scenario fails
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close the WebDriver session after each test
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
            System.out.println("Driver was closed.");
        } else {
            System.out.println("No WebDriver session was started.");
        }
    }
}
