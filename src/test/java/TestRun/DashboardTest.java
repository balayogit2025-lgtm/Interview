package TestRun;

import Base.BaseTest;
import Pages.ActivityPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.RolePage;
import Pages.UserPage;
import Utils.ConfigReader;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DashboardTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(DashboardTest.class);
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private ActivityPage activityPage;
    private RolePage rolePage;
    private UserPage userPage;


    @BeforeMethod
    public void setUp() {
        logger.info("=== Starting Test Setup ===");

        try {
            navigateToApp();
            loginPage = new LoginPage(getDriver());
            dashboardPage = new DashboardPage(getDriver());
            activityPage = new ActivityPage(getDriver());
            rolePage = new RolePage(getDriver());
            userPage = new UserPage(getDriver());
            String email = ConfigReader.getValidEmail();
            String password = ConfigReader.getValidPassword();
            loginPage.login(email, password);
            TestLogger.info("Test setup completed - Navigated to Dashboard");
            logger.info("WebDriver initialized and navigated to dashboard successfully");
            getExtentTest().log(Status.INFO, "Test setup completed successfully");
        } catch (Exception e) {
            logger.error("Test setup failed: " + e.getMessage(), e);
            getExtentTest().log(Status.FAIL, "Test setup failed: " + e.getMessage());
            throw e;
        }
    }


    @Test(description = "Verify breadcrumb header and subtext are displayed correctly",
            priority = 1, groups = {"smoke", "regression"})
    public void testBreadcrumbTexts() {
        logger.info("Starting testBreadcrumbTexts");
        getExtentTest().log(Status.INFO, "Starting breadcrumb texts verification");

        String headerText = dashboardPage.getBreadcrumbHeaderText();
        String subText = dashboardPage.getBreadcrumbSubText();

        Assert.assertNotNull(headerText, "Breadcrumb header text should not be null");
        Assert.assertNotNull(subText, "Breadcrumb sub text should not be null");
        Assert.assertTrue(headerText.contains("Dashboard"), "Header should contain Dashboard text");

        getExtentTest().log(Status.PASS, "Breadcrumb Header: " + headerText);
        getExtentTest().log(Status.PASS, "Breadcrumb Subtext: " + subText);
        TestLogger.success("Breadcrumb verification passed - Header: " + headerText + ", Subtext: " + subText);
        logger.info("Breadcrumb verification PASSED - Header: {}, Subtext: {}", headerText, subText);
    }

    @Test(description = "Verify user greeting, date and time are displayed",
            priority = 2, groups = {"smoke", "regression"})
    public void testUserGreetingDateTime() {
        logger.info("Starting testUserGreetingDateTime");
        getExtentTest().log(Status.INFO, "Starting user greeting, date & time verification");

        String greeting = dashboardPage.getUserGreeting();
        String date = dashboardPage.getCurrentDate();
        String time = dashboardPage.getCurrentTime();

        Assert.assertNotNull(greeting, "User greeting should not be null");
        Assert.assertNotNull(date, "Current date should not be null");
        Assert.assertNotNull(time, "Current time should not be null");

        getExtentTest().log(Status.PASS, "Greeting: " + greeting);
        getExtentTest().log(Status.PASS, "Date: " + date);
        getExtentTest().log(Status.PASS, "Time: " + time);

        TestLogger.success("Greeting, Date & Time verification passed - Greeting: " + greeting);
        logger.info("Greeting, Date & Time verification PASSED");
    }

    @Test(description = "Verify all cards are displayed and have correct titles",
            priority = 3, groups = {"regression"})
    public void testCardsDisplayAndTitles() {
        logger.info("Starting testCardsDisplayAndTitles");
        getExtentTest().log(Status.INFO, "Verifying all cards display and titles");

        // Verify card visibility
        Assert.assertTrue(dashboardPage.isActivityCardDisplayed(), "Activity card should be displayed");
        Assert.assertTrue(dashboardPage.isRolesCardDisplayed(), "Roles card should be displayed");
        Assert.assertTrue(dashboardPage.isTotalUserCardDisplayed(), "Total Users card should be displayed");
        Assert.assertTrue(dashboardPage.isWebUserCardDisplayed(), "Web Users card should be displayed");
        Assert.assertTrue(dashboardPage.isKeySafeUserCardDisplayed(), "Key Safe Users card should be displayed");

        // Verify card titles
        Assert.assertEquals(dashboardPage.getActivityCardTitle(), "Activity", "Activity card title mismatch");
        Assert.assertEquals(dashboardPage.getRolesCardTitle(), "Total Roles", "Roles card title mismatch");
        Assert.assertEquals(dashboardPage.getTotalUserCardTitle(), "Total Users", "Total Users card title mismatch");
        Assert.assertEquals(dashboardPage.getWebUserCardTitle(), "Web Users", "Web Users card title mismatch");
        Assert.assertEquals(dashboardPage.getTotalKeySafeCardTitle(), "Key Safe Users", "Key Safe Users card title mismatch");

        getExtentTest().log(Status.PASS, "All cards verified successfully");
        TestLogger.success("All cards display and title verification passed");
        logger.info("All cards display and title verification PASSED");
    }

    @Test(description = "Verify all chart titles and visibility",
            priority = 4, groups = {"regression"})
    public void testChartTitlesAndVisibility() {
        logger.info("Starting testChartTitlesAndVisibility");
        getExtentTest().log(Status.INFO, "Verifying chart titles and visibility");

        Assert.assertTrue(dashboardPage.isKeyBoxChartTitleDisplayed(), "Key Box chart title should be displayed");
        Assert.assertTrue(dashboardPage.isUsersChartTitleDisplayed(), "Users chart title should be displayed");
        Assert.assertTrue(dashboardPage.isActivitiesChartTitleDisplayed(), "Activities chart title should be displayed");

        String keyBoxTitle = dashboardPage.getKeyBoxChartTitle();
        String usersTitle = dashboardPage.getUsersChartTitle();
        String activitiesTitle = dashboardPage.getActivitiesChartTitle();

        Assert.assertNotNull(keyBoxTitle, "Key Box chart title should not be null");
        Assert.assertNotNull(usersTitle, "Users chart title should not be null");
        Assert.assertNotNull(activitiesTitle, "Activities chart title should not be null");

        getExtentTest().log(Status.PASS, "All chart titles verified: KeyBox=" + keyBoxTitle + ", Users=" + usersTitle + ", Activities=" + activitiesTitle);
        TestLogger.success("Chart titles and visibility verification passed");
        logger.info("Chart titles and visibility verification PASSED");
    }

    @Test(description = "Verify all card counts are displayed",
            priority = 5, groups = {"regression"})
    public void testCardCountsDisplay() {
        logger.info("Starting testCardCountsDisplay");
        getExtentTest().log(Status.INFO, "Verifying card counts display");

        Assert.assertTrue(dashboardPage.isActivityCardCountDisplayed(), "Activity card count should be displayed");
        Assert.assertTrue(dashboardPage.isTotalUsersCardCountDisplayed(), "Total Users card count should be displayed");
        Assert.assertTrue(dashboardPage.isTotalRolesCardCountDisplayed(), "Total Roles card count should be displayed");
        Assert.assertTrue(dashboardPage.isWebUsersCardCountDisplayed(), "Web Users card count should be displayed");
        Assert.assertTrue(dashboardPage.isKeySafeUsersCardCountDisplayed(), "Key Safe Users card count should be displayed");

        getExtentTest().log(Status.PASS, "All card counts verified");
        TestLogger.success("All card counts verification passed");
        logger.info("All card counts verification PASSED");
    }

    @Test(description = "Verify all charts are displayed",
            priority = 6, groups = {"regression"})
    public void testChartsDisplay() {
        logger.info("Starting testChartsAndCenterValues");
        getExtentTest().log(Status.INFO, "Verifying charts and center values");

        Assert.assertTrue(dashboardPage.isKeyBoxChartDisplayed(), "Key Box chart should be displayed");
        Assert.assertTrue(dashboardPage.isUsersChartDisplayed(), "Users chart should be displayed");
        Assert.assertTrue(dashboardPage.isActivitiesChartDisplayed(), "Activities chart should be displayed");

        getExtentTest().log(Status.PASS, "All charts verified");
        TestLogger.success("Charts verification passed");
        logger.info("Charts verification PASSED");
    }

    @Test(description = "Verify all chart legends are displayed and have correct text",
            priority = 7, groups = {"regression"})
    public void testChartLegends() {
        logger.info("Starting testChartLegends");
        getExtentTest().log(Status.INFO, "Verifying chart legends");

        // Key Box legends
        Assert.assertTrue(dashboardPage.isKeyboxLegendDisplayed(), "Key Box legends should be displayed");
        Assert.assertEquals(dashboardPage.keyboxRegisteredLegendText(), "Registered", "Key Box registered legend text mismatch");
        Assert.assertEquals(dashboardPage.keyboxUnRegisteredLegendText(), "Unregistered", "Key Box unregistered legend text mismatch");

        // Users legends
        Assert.assertTrue(dashboardPage.isUserLegendDisplayed(), "Users legends should be displayed");
        Assert.assertEquals(dashboardPage.usersActiveLegendText(), "Active", "Users active legend text mismatch");
        Assert.assertEquals(dashboardPage.usersInActiveLegendText(), "Inactive", "Users inactive legend text mismatch");

        // Activities legends
        Assert.assertTrue(dashboardPage.isActivitiesLegendDisplayed(), "Activities legends should be displayed");
        Assert.assertEquals(dashboardPage.activitiesActiveLegendText(), "Active", "Activities active legend text mismatch");
        Assert.assertEquals(dashboardPage.activitiesInActiveLegendText(), "Inactive", "Activities inactive legend text mismatch");

        getExtentTest().log(Status.PASS, "All chart legends verified successfully");
        TestLogger.success("All chart legends verification passed");
        logger.info("All chart legends verification PASSED");
    }

    @Test(description = "Comprehensive dashboard verification - All elements",
            priority = 8, groups = {"smoke", "regression"})
    public void testCompleteDashboardVerification() {
        logger.info("Starting testCompleteDashboardVerification");
        getExtentTest().log(Status.INFO, "Starting complete dashboard verification");

        // Breadcrumb
        Assert.assertNotNull(dashboardPage.getBreadcrumbHeaderText());
        Assert.assertNotNull(dashboardPage.getBreadcrumbSubText());

        // Header elements
        Assert.assertNotNull(dashboardPage.getUserGreeting());
        Assert.assertNotNull(dashboardPage.getCurrentDate());
        Assert.assertNotNull(dashboardPage.getCurrentTime());

        // All cards and counts
        Assert.assertTrue(dashboardPage.isActivityCardDisplayed());
        Assert.assertTrue(dashboardPage.isActivityCardCountDisplayed());
        Assert.assertTrue(dashboardPage.isRolesCardDisplayed());
        Assert.assertTrue(dashboardPage.isTotalRolesCardCountDisplayed());
        Assert.assertTrue(dashboardPage.isTotalUserCardDisplayed());
        Assert.assertTrue(dashboardPage.isTotalUsersCardCountDisplayed());
        Assert.assertTrue(dashboardPage.isWebUserCardDisplayed());
        Assert.assertTrue(dashboardPage.isWebUsersCardCountDisplayed());
        Assert.assertTrue(dashboardPage.isKeySafeUserCardDisplayed());
        Assert.assertTrue(dashboardPage.isKeySafeUsersCardCountDisplayed());

        getExtentTest().log(Status.PASS, "Complete dashboard verification PASSED");
        TestLogger.success("Complete dashboard verification passed");
        logger.info("Complete dashboard verification PASSED");
    }

    @Test(description = "Verify dashboard activity card count with activity page total",
            priority = 9, groups = {"regression"})
    public void verifyDashboardActivityCount() throws InterruptedException {
        logger.info("Starting verifyDashboardActivityCount");
        getExtentTest().log(Status.INFO, "Verifying dashboard activity card count with activity page total");

        String dashboardActivityCountStr = dashboardPage.getActivityCardCount();
        Assert.assertNotNull(dashboardActivityCountStr, "Dashboard activity count string should not be null");

        String activityPageTotalCountStr = activityPage.totalActivityCount();
        Assert.assertNotNull(activityPageTotalCountStr, "Activity page total count string should not be null");

        Assert.assertEquals(dashboardActivityCountStr, activityPageTotalCountStr, "Dashboard activity count does not match the total on the activity page.");

        getExtentTest().log(Status.PASS, "Dashboard activity count matches activity page total: " + dashboardActivityCountStr);
        TestLogger.success("Dashboard activity count verification passed: " + dashboardActivityCountStr);
        logger.info("Dashboard activity count verification PASSED: " + dashboardActivityCountStr);
    }

    @Test(description = "Verify dashboard role card count with role page total",
            priority = 10, groups = {"regression"})
    public void verifyDashboardRoleCount() throws InterruptedException {
        logger.info("Starting verifyDashboardRoleCount");
        getExtentTest().log(Status.INFO, "Verifying dashboard role card count with role page total");

        String dashboardRoleCountStr = dashboardPage.getRolesCardCount();
        Assert.assertNotNull(dashboardRoleCountStr, "Dashboard role count string should not be null");

        String rolePageTotalCountStr = rolePage.totalRoleCount();
        Assert.assertNotNull(rolePageTotalCountStr, "Role page total count string should not be null");

        Assert.assertEquals(dashboardRoleCountStr, rolePageTotalCountStr, "Dashboard role count does not match the total on the role page.");

        getExtentTest().log(Status.PASS, "Dashboard role count matches role page total: " + dashboardRoleCountStr);
        TestLogger.success("Dashboard role count verification passed: " + dashboardRoleCountStr);
        logger.info("Dashboard role count verification PASSED: " + dashboardRoleCountStr);
    }

    @Test(description = "Verify dashboard total user card count with user page total",
            priority = 11, groups = {"regression"})
    public void verifyDashboardTotalUserCount() throws InterruptedException {
        logger.info("Starting verifyDashboardTotalUserCount");
        getExtentTest().log(Status.INFO, "Verifying dashboard total user card count with user page total");

        String dashboardTotalUserCountStr = dashboardPage.getTotalUsersCardCount();
        Assert.assertNotNull(dashboardTotalUserCountStr, "Dashboard total user count string should not be null");

        String userPageTotalCountStr = userPage.TotalUserCount();
        Assert.assertNotNull(userPageTotalCountStr, "User page total count string should not be null");

        Assert.assertEquals(dashboardTotalUserCountStr, userPageTotalCountStr, "Dashboard total user count does not match the total on the user page.");

        getExtentTest().log(Status.PASS, "Dashboard total user count matches user page total: " + dashboardTotalUserCountStr);
        TestLogger.success("Dashboard total user count verification passed: " + dashboardTotalUserCountStr);
        logger.info("Dashboard total user count verification PASSED: " + dashboardTotalUserCountStr);
    }

    @Test(description = "Verify dashboard web user count with actual web user count from user page",
            priority = 12, groups = {"regression"})
    public void verifyDashboardWebUserCount() {
        logger.info("Starting verifyDashboardWebUserCount");
        getExtentTest().log(Status.INFO, "Verifying dashboard web user count with actual web user count from user page");

        String dashboardWebUserCountStr = dashboardPage.getWebUserCount();
        Assert.assertNotNull(dashboardWebUserCountStr, "Dashboard web user count string should not be null");

        String actualWebUserCountStr = userPage.getWebUsersCount();
        Assert.assertNotNull(actualWebUserCountStr, "Actual web user count string should not be null");

        Assert.assertEquals(dashboardWebUserCountStr, actualWebUserCountStr, "Dashboard web user count does not match the actual web user count from the user page.");

        getExtentTest().log(Status.PASS, "Dashboard web user count matches actual web user count: " + dashboardWebUserCountStr);
        TestLogger.success("Dashboard web user count verification passed: " + dashboardWebUserCountStr);
        logger.info("Dashboard web user count verification PASSED: " + dashboardWebUserCountStr);
    }

    @Test(description = "Verify dashboard key safe user count with actual key safe user count from user page",
            priority = 13, groups = {"regression"})
    public void verifyDashboardKeySafeUserCount() {
        logger.info("Starting verifyDashboardKeySafeUserCount");
        getExtentTest().log(Status.INFO, "Verifying dashboard key safe user count with actual key safe user count from user page");

        String dashboardKeySafeUserCountStr = dashboardPage.getTotalUsersCardCount();
        Assert.assertNotNull(dashboardKeySafeUserCountStr, "Dashboard key safe user count string should not be null");

        String actualKeySafeUserCountStr = userPage.getKeySafeUsersCount();
        Assert.assertNotNull(actualKeySafeUserCountStr, "Actual key safe user count string should not be null");

        Assert.assertEquals(dashboardKeySafeUserCountStr, actualKeySafeUserCountStr, "Dashboard key safe user count does not match the actual key safe user count from the user page.");

        getExtentTest().log(Status.PASS, "Dashboard key safe user count matches actual key safe user count: " + dashboardKeySafeUserCountStr);
        TestLogger.success("Dashboard key safe user count verification passed: " + dashboardKeySafeUserCountStr);
        logger.info("Dashboard key safe user count verification PASSED: " + dashboardKeySafeUserCountStr);
    }
}
