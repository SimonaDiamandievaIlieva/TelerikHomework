package seleniumDriverHomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleTest extends BaseSetUp {

    @BeforeEach
    void beforeTests() {

        driver = startBrowser(BrowserTypes.EDGE_HEADLESS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(GOOGLE_URL);

        WebElement agreeButton = driver.findElement(By.xpath(agreeButtonLocator));
        agreeButton.click();
    }

    @Test
    public void resultValidate_when_searchTermProvided_google() {

        WebElement googleLogo = driver.findElement(By.xpath(googleLogoLocator));
        wait.until(ExpectedConditions.visibilityOf(googleLogo));

        WebElement searchField = driver.findElement(By.xpath(searchFieldLocator));
        searchField.sendKeys(INPUT);

        WebElement searchButton = driver.findElement(By.xpath(searchButtonLocator));

        Assertions.assertTrue(searchField.isDisplayed(), SEARCH_BUTTON_DOES_NOT_DISPLAY);
        googleLogo.click();
        searchButton.click();

        WebElement firstResult = driver.findElement(By.xpath(firstResultLocator));
        wait.until(ExpectedConditions.visibilityOf(firstResult));

        Assertions.assertTrue(firstResult.getText().contains(ACADEMY_ALPHA), SEARCH_RESULT_IS_NOT_FOUND);
    }
}
