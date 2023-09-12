package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {
    public LogoutPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void logout() {
        actions.waitForElementClickable("jira.logoutPage.baseButton");
        actions.clickElement("jira.logoutPage.baseButton");

        actions.waitForElementClickable("jira.logoutPage.logoutButton");
        actions.clickElement("jira.logoutPage.logoutButton");

        actions.waitForElementClickable("jira.logoutPage.confirmLogoutButton");
        actions.clickElement("jira.logoutPage.confirmLogoutButton");
    }
}
