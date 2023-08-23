package seleniumDriverHomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleTest extends BaseSetUp {
    @BeforeAll
    static void beforeTests() {

        driver = startBrowser(BrowserTypes.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://google.com");

        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='W0wltc']/div[@class='QS5gu sy4vM']"));
        agreeButton.click();
    }

    @Test
    public void resultValidate_when_searchTermProvided_google() {

        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys(INPUT);

        WebElement searchButton = driver.findElement(By.xpath("(//center/input[@class='gNO89b'])[2]"));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        Assertions.assertTrue(searchField.isDisplayed(), SEARCH_BUTTON_DOES_NOT_DISPLAY);
        searchButton.click();

        WebElement firstResult = driver.findElement(By.xpath(("(//a/h3)[1]")));

        Assertions.assertTrue(firstResult.getText().contains(ACADEMY_ALPHA), SEARCH_RESULT_IS_NOT_FOUND);
    }
}
