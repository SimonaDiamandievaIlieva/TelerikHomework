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

    @Test
    public void productAddedToShoppingCart_when_addToCart(){

        addProductToTheShoppingCart(BACKPACK);
        addProductToTheShoppingCart(T_SHIRT);

        clickOnShoppingCart();

        var items = driver.findElements(By.className("inventory_item_name"));

        Assertions.assertEquals(2, items.size(), ERROR_MESSAGE_ITEMS_COUNT);

        Assertions.assertEquals(BACKPACK, items.get(0).getText(), ERROR_MESSAGE_BACKPACK_TITLE);
        Assertions.assertEquals(T_SHIRT, items.get(1).getText(), ERROR_MESSAGE_TSHIRT_TITLE);
    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation(){

        addProductToTheShoppingCart(BACKPACK);
        addProductToTheShoppingCart(T_SHIRT);

        clickOnShoppingCart();

        checkoutCart("Kristina", "Koleva", "1000");

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

        addProductToTheShoppingCart(BACKPACK);
        addProductToTheShoppingCart(T_SHIRT);

        clickOnShoppingCart();

        checkoutCart("Kristina", "Koleva", "1000");

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
