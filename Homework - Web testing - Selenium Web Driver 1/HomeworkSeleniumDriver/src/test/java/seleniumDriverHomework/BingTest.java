package seleniumDriverHomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BingTest extends BaseSetUp {
    @BeforeAll
    static void beforeTests() {

        driver = startBrowser(BrowserTypes.FIREFOX);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://bing.com");
    }

    @Test
    public void resultValidate_when_searchTermProvided_bing() {

        WebElement searchField = driver.findElement(By.id("sb_form_q"));
        searchField.sendKeys(INPUT);

        WebElement searchButton = driver.findElement(By.xpath("//label[@id='search_icon']/*"));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        Assertions.assertTrue(searchField.isDisplayed(), SEARCH_BUTTON_DOES_NOT_DISPLAY);

        searchButton.click();
        WebElement firstResult = driver.findElement(By.linkText(ACADEMY_ALPHA));

        Assertions.assertTrue(firstResult.getText().contains(ACADEMY_ALPHA), SEARCH_RESULT_IS_NOT_FOUND);
    }
}
