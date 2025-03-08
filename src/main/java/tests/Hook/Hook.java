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
import java.util.UUID;

public class Hook {
    public WebDriver driver;
    private Properties properties;
    private final String propertyFilePath = "src/main/java/tests/Config/config.properties";

    public Hook() {
        // Загружаем конфигурационные параметры
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
            throw new RuntimeException("config.properties не найден по пути " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String browser = properties.getProperty("browser");
        String env = properties.getProperty("env");

        // Устанавливаем настройки для выбранного браузера
        if (browser.equals("chrome")) {
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            Configuration.browserSize = "1366x768";
            Configuration.headless = true;  // Запускаем браузер в headless режиме
            Configuration.pageLoadStrategy = "normal";
            Configuration.timeout = 15000;
            Configuration.reportsFolder = "target/screenshots";
            Configuration.browserCapabilities = options;
        } else if (browser.equals("edge")) {
            Configuration.browser = "edge";

            // Настроим WebDriver для Edge
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();

            // Если необходимо, генерируем уникальный путь для директории данных пользователя
            String uniqueUserDataDir = "/tmp/selenium/userDataDir_" + UUID.randomUUID().toString(); // Генерация уникального пути
            edgeOptions.addArguments("--user-data-dir=" + uniqueUserDataDir);  // Используем уникальный путь для данных пользователя
            edgeOptions.addArguments("--disable-dev-shm-usage", "--window-size=1366,768"); // Отключаем использование dev-shm для CI-среды
            edgeOptions.setExperimentalOption("mobileEmulation", Map.of("deviceName", "Samsung Galaxy A51/71"));
            Configuration.browserCapabilities = edgeOptions;
        }

        // Открываем URL после того, как WebDriver настроен
        String url = properties.getProperty("url");
        Selenide.open(url); // Открываем URL для инициализации WebDriver
        WebDriverRunner.getWebDriver().manage().window().maximize(); // Максимизируем окно браузера после открытия

        return browser;
    }

    @Before
    public void setup() {
        // Запускаем open(url) для инициализации WebDriver и связывания его с текущим потоком
        getDriverPath(); // Убедитесь, что WebDriver инициализирован и URL открыт
    }

    @After
    public void close(Scenario scenario) {
        try {
            // Захват состояния сценария
            System.out.println(scenario.getName() + " : " + scenario.getStatus());

            // Делаем скриншот, если тест не прошел
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Закрываем сессию WebDriver после каждого теста
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.closeWebDriver();
            System.out.println("Driver был закрыт.");
        } else {
            System.out.println("Сессия WebDriver не была инициализирована.");
        }
    }
}
