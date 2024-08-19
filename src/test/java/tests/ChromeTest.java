package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChromeTest {

    private static WebDriver driver;
    private static Properties properties;

    @BeforeAll
    public static void setUp() throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata/config.properties")) {
            properties.load(fis);
        }
    }

    @Test
    @DisplayName("Open Chrome browser and go to specified url")
    @Description("This test attempts to open Chrome browser and go to specified url.")
    @Severity(CRITICAL)
    public void testChrome() throws MalformedURLException {
        String url = properties.getProperty("URL");

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", false);
        options.setCapability("selenoid:options", selenoidOptions);

        driver = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(url);
        assertTrue(driver.getCurrentUrl().contains("login"));
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
