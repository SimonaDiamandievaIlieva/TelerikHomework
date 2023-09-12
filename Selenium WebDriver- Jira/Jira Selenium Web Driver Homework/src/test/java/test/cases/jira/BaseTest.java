package test.cases.jira;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import pages.jira.*;

public class BaseTest {
    public static final String storySummary = "Try to receive information about the site";
    public static final String storyDescription = "*Description:* \n" +
            "As a user, I want to see information about the company from their website.\n" +
            "\n" +
            "*Preconditions:*\n" +
            "Navigate to https://www.phptravels.net/ru \n" +
            "*Test Steps:*\n" +
            "1. Scroll to the bottom of the homepage.\n" +
            "2. Click on “About Us”.\n" +
            "3. The page is redirected to https://www.phptravels.net/About-Us \n" +
            "*Expected result:*\n" +
            "A page with information about the company is displayed.";
    public static final String bugSummary = "About Us information page in Russian version is empty";
    public static final String bugDescription = "**Description:**\n" +
            "As a user, I want to see information about the company from their website.\n" +
            "\n" +
            "**Preconditions:**\n" +
            "Browser: Mozilla Firefox 2019 08 10 10 11 28\n" +
            "\n" +
            "**Steps to reproduce:**\n" +
            "1. Navigate to [https://www.phptravels.net/ru](https://www.phptravels.net/ru)\n" +
            "2. Scroll to the bottom of the homepage.\n" +
            "3. Click on “About Us”.\n" +
            "4. The page is redirected to [https://www.phptravels.net/About-Us](https://www.phptravels.net/About-Us)\n" +
            "\n" +
            "**Expected result:**\n" +
            "A page with information about the company is displayed.\n" +
            "\n" +
            "**Actual result:**\n" +
            "There is no information about the company on their website. The page is empty.\n";
    static UserActions actions = new UserActions();
    static LoginPage loginPage = new LoginPage(actions.getDriver(), "jira.loginPage");
    static CreateProjectPage createProjectPage = new CreateProjectPage(actions.getDriver(), "jira.homePage");
    static StoryPage storyPage = new StoryPage(actions.getDriver(), "jira.projectUrl");
    static BugPage bugPage = new BugPage(actions.getDriver(), "jira.projectUrl");
    static LinkStoryAndBugPage linkStoryAndBugPage = new LinkStoryAndBugPage(actions.getDriver(), "jira.projectUrl");
    static LogoutPage logoutPage = new LogoutPage(actions.getDriver(), "jira.projectUrl");
    public static final String projectName = "WTF" + actions.generateRandomString (3, 5) + " (WTF)";
    public static final String relationType = "is blocked by";
    public static final String userKey = "user";

    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("jira.homePage");
    }

    @AfterClass
    public static void tearDown() {
        UserActions.quitDriver();
    }
}
