package seleniumDriverHomework;

import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSetUp implements Constants{
    static WebDriver driver;
    static WebDriverWait wait;

    enum BrowserTypes {
        FIREFOX,
        FIREFOX_HEADLESS,
        CHROME,
        CHROME_HEADLESS
    }

    static WebDriver startBrowser(BrowserTypes browserTypes) {
        switch (browserTypes) {
            case CHROME:
                return new ChromeDriver();
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                return new FirefoxDriver();
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
        }
        return null;
    }

    @AfterAll
    public static void classTearDown() {
        driver.close();
    }
}
