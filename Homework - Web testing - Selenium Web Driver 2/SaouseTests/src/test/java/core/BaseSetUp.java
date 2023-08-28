package core;

import core.sauseTests.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseSetUp implements Constants {

    public static WebDriver driver;
    public static WebDriverWait wait;
    protected enum BrowserTypes {
        FIREFOX,
        FIREFOX_HEADLESS,
        CHROME,
        CHROME_HEADLESS,
        EDGE,
        EDGE_HEADLESS
    }

    @BeforeEach
    public void beforeEachTests(){
        driver = startBrowser(BrowserTypes.FIREFOX);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://www.saucedemo.com/");

        authenticateWithUser("standard_user", "secret_sauce");
    }

    @AfterEach
    public void classTearDown(){
        driver.close();
    }

    public static WebDriver startBrowser(BrowserTypes browserTypes) {
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
            case EDGE:
                return new EdgeDriver();
            case EDGE_HEADLESS:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);
        }
        return null;
    }

    protected static void authenticateWithUser(String username, String pass) {
        WebElement usernameInput = driver.findElement(By.xpath("//input[@data-test='username']"));
        usernameInput.sendKeys(username);

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        loginButton.click();

        WebElement inventoryPageTitle = driver.findElement(By.xpath("//div[@class='app_logo']"));
        wait.until(ExpectedConditions.visibilityOf(inventoryPageTitle));
    }

    protected WebElement getProductByTitle(String title){
        return driver.findElement(By.xpath(String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", title)));
    }

    protected static void fillShippingDetails(String firstName, String lastName, String zip) {
        driver.findElement(By.xpath("//input[@data-test='firstName']")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(zip);
    }

    protected void addProductToTheShoppingCart(String productTitle){
        WebElement backpack = getProductByTitle(productTitle);
        backpack.findElement(By.className("btn_inventory")).click();
    }

    protected void clickOnShoppingCart(){
        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        shoppingCart.click();
    }

    protected  void checkoutCart(String firstName, String lastName, String zip){
        WebElement checkOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test='checkout']")));;
        checkOutButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-test='firstName']")));
        fillShippingDetails(firstName, lastName, zip);
        driver.findElement(By.xpath("//input[@data-test='continue']")).click();
    }
}
