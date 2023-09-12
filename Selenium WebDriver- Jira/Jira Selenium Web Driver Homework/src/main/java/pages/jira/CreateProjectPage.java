package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class CreateProjectPage extends BasePage {
    public CreateProjectPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }
    public void createProject(String projectName) {

        actions.waitForElementClickable("jira.createProjectPage.switchButton");
        actions.clickElement("jira.createProjectPage.switchButton");

        actions.waitForElementClickable("jira.createProjectPage.jiraWorkManagement");
        actions.clickElement("jira.createProjectPage.jiraWorkManagement");

        actions.waitForElementClickable("jira.createProjectPage.createProjectButton");
        actions.clickElement("jira.createProjectPage.createProjectButton");

         actions.javaScriptExecutorClick("jira.createProjectPage.scrumButton");

        actions.waitForElementClickable("jira.createProjectPage.useTemplateButton");
        actions.clickElement("jira.createProjectPage.useTemplateButton");

        actions.waitForElementClickable("jira.createProjectPage.companyManagedButton");
        actions.clickElement("jira.createProjectPage.companyManagedButton");

        actions.waitForElementVisible("jira.createProjectPage.projectNameButton");
        actions.waitForElementClickable("jira.createProjectPage.projectNameButton");
        actions.typeValueInField(projectName, "jira.createProjectPage.projectNameButton");

        actions.waitForElementClickable("jira.createProjectPage.nextButton");
        actions.clickElement("jira.createProjectPage.nextButton");

        actions.waitForElementClickable("jira.createProjectPage.goToProjectPage");
        actions.clickElement("jira.createProjectPage.goToProjectPage");
    }
}
