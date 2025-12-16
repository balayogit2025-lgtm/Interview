package TestRun;

import Base.BaseTest;
import Pages.ActivityPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityTest extends BaseTest {

    private LoginPage loginPage;
    private ActivityPage activityPage;

    @BeforeMethod
    public void setupActivityPage() {
        navigateToApp();
        loginPage = new LoginPage(getDriver());
        activityPage = new ActivityPage(getDriver());
        
        // Login before each test
        loginPage.login(ConfigReader.getValidEmail(), ConfigReader.getValidPassword());
        activityPage.navigateToActivityPage();
        getExtentTest().log(Status.INFO, "Setup complete. Navigated to Activity Page.");
    }

    @Test(priority = 1, description = "Verify that all essential elements are displayed on the Activity page.")
    public void verifyActivityPageElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Page Elements");
        
        // Verify Page Header
        Assert.assertTrue(activityPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(activityPage.getPageHeaderText(), "Activity", "Page header text is incorrect.");
        getExtentTest().log(Status.PASS, "Page header is correct and displayed.");

        // Verify 'Add Activity' Button
        Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(), "'Add Activity' button should be displayed.");
        getExtentTest().log(Status.PASS, "'Add Activity' button is displayed.");

        // Verify Search Button
        Assert.assertTrue(activityPage.isSearchButtonDisplayed(), "Search button should be displayed.");
        getExtentTest().log(Status.PASS, "Search button is displayed.");

        // Verify Activity Table
        Assert.assertTrue(activityPage.isActivityTableDisplayed(), "Activity table should be displayed.");
        getExtentTest().log(Status.PASS, "Activity table is displayed.");
        getExtentTest().log(Status.PASS, "All essential elements on the Activity page are displayed correctly.");
    }

}
