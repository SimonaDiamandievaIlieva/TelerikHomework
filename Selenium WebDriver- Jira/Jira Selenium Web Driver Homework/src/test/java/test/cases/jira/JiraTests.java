package test.cases.jira;
import org.junit.Test;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class JiraTests extends BaseTest {

    @Test
    public void a_storyIsCreated_when_authenticateAndNavigateCorrectly() {
        loginPage.loginUserInJiraForStory(userKey);
        createProjectPage.createProject(projectName);
        storyPage.CreateStory(storySummary, storyDescription);
        storyPage.assertStoryIsCreated(storySummary);
    }

    @Test
    public void b_bugIsCreated_when_authenticateAndNavigateCorrectly() {
        bugPage.createBug(bugSummary, bugDescription);
        bugPage.assertBugIsCreated(bugSummary);
    }

    @Test
    public void c_linkBugToStory_when_areCreatedCorrectly() {
        linkStoryAndBugPage.linkStoryAndBug(bugSummary);
        linkStoryAndBugPage.validateLinkedIssues(relationType, bugSummary);
        storyPage.deleteStoryAndBug(storySummary, bugSummary);
        logoutPage.logout();
    }
}
