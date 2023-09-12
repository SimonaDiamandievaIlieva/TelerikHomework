package pages.jira;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class StoryPage extends BasePage {
    public StoryPage(WebDriver driver, String urlKey) {
        super(driver, "jira.projectUrl");
    }

    public void CreateStory(String summary, String description) {

        actions.waitForElementClickable("jira.storyPage.createButton");
        actions.clickElement("jira.storyPage.createButton");

        actions.waitForElementClickable("jira.storyPage.clickIssueTypeDropDownButton");
        actions.clickElement("jira.storyPage.clickIssueTypeDropDownButton");

        actions.javaScriptExecutorClick("jira.storyPage.selectStory");

        actions.waitForElementVisible("jira.storyPage.summaryField");
        actions.clickElement("jira.storyPage.summaryField");
        actions.typeValueInField(summary, "jira.storyPage.summaryField");

        actions.waitForElementVisible("jira.storyPage.descriptionField");
        actions.clickElement("jira.storyPage.descriptionField");
        actions.typeValueInField(description, "jira.storyPage.descriptionField");

        actions.waitForElementClickable("jira.storyPage.clickPriorityDropDownButton");
        actions.clickElement("jira.storyPage.clickPriorityDropDownButton");

        actions.javaScriptExecutorClick("jira.storyPage.selectHighPriority");

        actions.waitForElementVisible("jira.storyPage.finalCreateButton");
        actions.clickElement("jira.storyPage.finalCreateButton");
    }

    public void assertStoryIsCreated (String storyName) {
        actions.waitForElementClickable("jira.linkStoryAndBugPage.issueButton");
        actions.clickElement("jira.linkStoryAndBugPage.issueButton");
        actions.waitForElementPresent("jira.storyPage.assertionStory", storyName);
    }

    public void deleteStoryAndBug(String storyTitle, String bugName) {

       actions.waitForElementPresent("jira.storyPage.clickOnStory", storyTitle);
       actions.waitForElementClickable("jira.storyPage.clickOnStory", storyTitle);
       actions.clickElement("jira.storyPage.clickOnStory", storyTitle);

       actions.waitForElementClickable("jira.storyPage.dropDownMenuButton");
       actions.clickElement("jira.storyPage.dropDownMenuButton");

       actions.waitForElementClickable("jira.storyPage.deleteButton");
       actions.clickElement("jira.storyPage.deleteButton");

       actions.waitForElementClickable("jira.storyPage.confirmDeleteButton");
       actions.clickElement("jira.storyPage.confirmDeleteButton");

        actions.waitForElementPresent("jira.storyPage.clickOnStory", bugName);
        actions.waitForElementClickable("jira.storyPage.clickOnStory", bugName);
        actions.clickElement("jira.storyPage.clickOnStory", bugName);

        actions.waitForElementClickable("jira.storyPage.dropDownMenuButton");
        actions.clickElement("jira.storyPage.dropDownMenuButton");

        actions.waitForElementClickable("jira.storyPage.deleteButton");
        actions.clickElement("jira.storyPage.deleteButton");

        actions.waitForElementClickable("jira.storyPage.confirmDeleteButton");
        actions.clickElement("jira.storyPage.confirmDeleteButton");
    }
}
