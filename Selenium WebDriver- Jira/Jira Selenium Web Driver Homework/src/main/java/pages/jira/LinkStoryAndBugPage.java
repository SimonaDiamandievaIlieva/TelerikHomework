package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkStoryAndBugPage extends BasePage {
    public LinkStoryAndBugPage(WebDriver driver, String urlKey) {
        super(driver, "jira.projectUrl");
    }
    public void linkStoryAndBug(String bugSummary) {

        actions.waitForElementClickable("jira.linkStoryAndBugPage.storyButton");
        actions.clickElement("jira.linkStoryAndBugPage.storyButton");

        actions.javaScriptExecutorClick("jira.linkStoryAndBugPage.linkButton");

        actions.javaScriptExecutorScrollIntoView("jira.linkStoryAndBugPage.searchLink");

        actions.typeValueInField(bugSummary,"jira.linkStoryAndBugPage.searchForIssueInput");
        actions.waitForElementVisible("jira.linkStoryAndBugPage.bugButton");
        actions.clickElement("jira.linkStoryAndBugPage.bugButton");

        actions.waitForElementClickable("jira.linkStoryAndBugPage.clickLinkButton");
        actions.clickElement("jira.linkStoryAndBugPage.clickLinkButton");
    }

    public void validateLinkedIssues (String relation, String bugName) {
        String relationTypeExistingXpath = actions.getLocatorValueByKey("jira.linkStoryAndBugPage.assertionRelation", relation);
        WebElement relType = driver.findElement(By.xpath(relationTypeExistingXpath));
        String bugExistingXpath = actions.getLocatorValueByKey("jira.linkStoryAndBugPage.assertionLinkedBug", bugName);
        WebElement bugNameXpath = driver.findElement(By.xpath(bugExistingXpath));
        Assert.assertEquals(relation, relType.getText());
        Assert.assertEquals(bugName, bugNameXpath.getText());
    }
}
