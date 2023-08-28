package core.sauseTests;
import core.BaseSetUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SauseTest extends BaseSetUp {
    @BeforeEach
    public void beforeAllTests(){
        driver = startBrowser(BrowserTypes.FIREFOX);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://www.saucedemo.com/");

        authenticateWithUser("standard_user", "secret_sauce");
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart(){

        WebElement backpack = getProductByTitle(BACKPACK);
        backpack.findElement(By.className("btn_inventory")).click();

        WebElement tshirt = getProductByTitle(T_SHIRT);
        tshirt.findElement(By.className("btn_inventory")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        var items = driver.findElements(By.className("inventory_item_name"));

        Assertions.assertEquals(2, items.size(), ERROR_MESSAGE_ITEMS_COUNT);

        Assertions.assertEquals(BACKPACK, items.get(0).getText(), ERROR_MESSAGE_BACKPACK_TITLE);
        Assertions.assertEquals(T_SHIRT, items.get(1).getText(), ERROR_MESSAGE_TSHIRT_TITLE);
    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation(){

        WebElement backpack = wait.until(ExpectedConditions.visibilityOf(getProductByTitle(BACKPACK)));
        backpack.findElement(By.className("btn_inventory")).click();

        WebElement tshirt = getProductByTitle(T_SHIRT);
        tshirt.findElement(By.className("btn_inventory")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        fillShippingDetails("Kristina", "Koleva", "1000");

        driver.findElement(By.id("continue")).click();

        var items = driver.findElements(By.className("inventory_item_name"));

        var total = driver.findElement(By.className("summary_total_label")).getText();
        String convertedTotal = total.replace(".", ",");
        double expectedPrice = BACKPACK_PRICE + T_SHIRT_PRICE + TAX;
        String expectedTotal = String.format("Total: $%.2f", expectedPrice);


        Assertions.assertEquals(2, items.size(), ERROR_MESSAGE_ITEMS_COUNT);
        Assertions.assertEquals(BACKPACK, items.get(0).getText(), ERROR_MESSAGE_BACKPACK_TITLE);
        Assertions.assertEquals(T_SHIRT, items.get(1).getText(), ERROR_MESSAGE_TSHIRT_TITLE);
        Assertions.assertEquals(expectedTotal, convertedTotal, ERROR_MESSAGE_WRONG_TOTAL_PRICE);
    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {

        WebElement backpack = wait.until(ExpectedConditions.visibilityOf(getProductByTitle(BACKPACK)));
        backpack.findElement(By.className("btn_inventory")).click();
        backpack.isDisplayed();

        var tshirt = getProductByTitle(T_SHIRT);
        tshirt.findElement(By.className("btn_inventory")).click();
        tshirt.isDisplayed();

        WebElement shoppingCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='shopping_cart_badge']")));;

        Assertions.assertEquals(shoppingCartButton.getText(),"2",ERROR_MESSAGE_ITEMS_COUNT);
        shoppingCartButton.click();

        WebElement checkOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test='checkout']")));;
        checkOutButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-test='firstName']")));
        fillShippingDetails("Kristina", "Koleva", "1000");
        driver.findElement(By.xpath("//input[@data-test='continue']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
        driver.findElement(By.id("finish")).click();

        WebElement completeOrderMessage = driver.findElement(By.className("complete-header"));
        wait.until(ExpectedConditions.visibilityOf(completeOrderMessage));
        Assertions.assertEquals(COMPLETE_ORDER_MESSAGE, completeOrderMessage.getText(), ERROR_MESSAGE_ORDER_MISTAKE);

        WebElement backHomeButton = driver.findElement((By.xpath("//button[@data-test='back-to-products']")));
        backHomeButton.click();

        WebElement shoppingCart = driver.findElement(By.xpath("//div/a[@class='shopping_cart_link']"));
        Assertions.assertTrue(shoppingCart.getText().isEmpty(), ERROR_MESSAGE_SHOPPING_CART_IS_NOT_EMPTY);
    }
}
