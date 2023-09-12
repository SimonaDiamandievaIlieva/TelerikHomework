package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BugPage extends BasePage {

    public BugPage(WebDriver driver, String urlKey) {
        super(driver, "jira.projectUrl");
    }

    public void createBug (String summary, String description) {

        actions.waitForElementClickable("jira.storyPage.createButton");
        actions.clickElement("jira.storyPage.createButton");

        actions.waitForElementClickable("jira.storyPage.clickIssueTypeDropDownButton");
        actions.clickElement("jira.storyPage.clickIssueTypeDropDownButton");

        actions.javaScriptExecutorClick("jira.bugPage.selectBug");

        actions.waitForElementVisible("jira.storyPage.summaryField");
        actions.clickElement("jira.storyPage.summaryField");
        actions.typeValueInField(summary, "jira.storyPage.summaryField");

        actions.waitForElementVisible("jira.storyPage.descriptionField");
        actions.clickElement("jira.storyPage.descriptionField");
        actions.typeValueInField(description, "jira.storyPage.descriptionField");

        actions.waitForElementClickable("jira.storyPage.clickPriorityDropDownButton");
        actions.clickElement("jira.storyPage.clickPriorityDropDownButton");

        actions.javaScriptExecutorClick("jira.bugPage.selectHighestPriority");

        actions.waitForElementVisible("jira.storyPage.finalCreateButton");
        actions.clickElement("jira.storyPage.finalCreateButton");

        actions.waitForElementClickable("jira.linkStoryAndBugPage.refreshButton");
        actions.clickElement("jira.linkStoryAndBugPage.refreshButton");
    }

    public void assertBugIsCreated (String bugName) {
        actions.waitForElementPresent("jira.bugPage.assertionBug", bugName);
    }
}
