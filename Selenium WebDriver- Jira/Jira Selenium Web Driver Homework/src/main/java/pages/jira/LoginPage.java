package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver, String urlKey) {
        super(driver, "jira.loginPage");
    }

    public void loginUserInJiraForStory(String userKey) {
        String username = getConfigPropertyByKey("jira.users." + userKey + ".username");
        String password = getConfigPropertyByKey("jira.users." + userKey + ".password");

        actions.waitForElementVisible("jira.loginPage.username");

        actions.typeValueInField(username, "jira.loginPage.username");
        actions.waitForElementVisible("jira.loginPage.continueButton");
        actions.clickElement("jira.loginPage.continueButton");

        actions.waitForElementClickable("jira.loginPage.password");
        actions.typeValueInField(password, "jira.loginPage.password");
        actions.clickElement("jira.loginPage.loginSubmitButton");
    }
}
