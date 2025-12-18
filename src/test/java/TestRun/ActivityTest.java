package TestRun;

import Base.BaseTest;
import Pages.ActivityPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
    public void verifyActivityGridPageElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Page Elements");
        TestLogger.info("Test: Verifying Activity Page Elements");

        // Verify Page Header
        Assert.assertTrue(activityPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(activityPage.getPageHeaderText(), "Activity", "Page header text is incorrect.");
        getExtentTest().log(Status.PASS, "Page header is correct and displayed.");
        TestLogger.success("Page header is correct and displayed.");

        // Verify 'Add Activity' Button
        Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(), "'Add Activity' button should be displayed.");
        getExtentTest().log(Status.PASS, "'Add Activity' button is displayed.");
        TestLogger.success("'Add Activity' button is correct and displayed.");

        // Verify Search Button
        Assert.assertTrue(activityPage.isSearchButtonDisplayed(), "Search button should be displayed.");
        getExtentTest().log(Status.PASS, "Search button is displayed.");
        TestLogger.info("Test: Verifying Activity Page Elements");

        // Verify Activity Table
        Assert.assertTrue(activityPage.isActivityTableDisplayed(), "Activity table should be displayed.");
        getExtentTest().log(Status.PASS, "Activity table is displayed.");
        TestLogger.success("Activity table is displayed.");
        getExtentTest().log(Status.PASS, "All essential elements on the Activity page are displayed correctly.");
        TestLogger.success("All essential elements are displayed correctly.");        
    }

    @Test(priority = 2, description = "Verify the headers of the activity table.")
    public void verifyActivityTableHeader() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Table Headers");
        TestLogger.info("Test: Verifying Activity Table Headers");

        List<String> expectedHeaders = List.of("S.NO", "NAME", "LOCATION", "DATE", "TIME", "CREATED BY","UPDATED BY", "ACTION");
        List<String> actualHeaders = activityPage.getTableHeaders();

        Assert.assertEquals(actualHeaders.size(), expectedHeaders.size(), "Number of table headers is incorrect.");
        getExtentTest().log(Status.PASS, "Correct number of table headers is displayed.");
        TestLogger.success("Correct number of table headers is displayed.");

        for (int i = 0; i < expectedHeaders.size(); i++) {
            Assert.assertEquals(actualHeaders.get(i), expectedHeaders.get(i), "Header text is incorrect for column " + (i + 1));
        }
        getExtentTest().log(Status.PASS, "All table headers are correct.");
        TestLogger.success("All table headers are correct.");
    }

    @Test(priority = 3, description = "Verify the pagination functionality of the activity table.")
    public void verifyActivityTablePagination() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Table Pagination");
        TestLogger.info("Test: Verifying Activity Table Pagination");

        // Verify pagination info is displayed
        Assert.assertTrue(activityPage.isPaginationInfoDisplayed(), "Pagination info should be displayed.");
        getExtentTest().log(Status.PASS, "Pagination info is displayed.");
        TestLogger.success("Pagination info is displayed.");

        // Verify initial state of pagination buttons
        Assert.assertFalse(activityPage.isPreviousPageButtonEnabled(), "Previous page button should be disabled on the first page.");
        getExtentTest().log(Status.PASS, "Previous page button is disabled on the first page.");
        TestLogger.success("Previous page button is disabled on the first page.");

        if (activityPage.isNextPageButtonEnabled()) {
            // Get current pagination info
            String initialPaginationInfo = activityPage.getPaginationInfoText();

            // Click next page button
            activityPage.clickNextPageButton();
            getExtentTest().log(Status.INFO, "Clicked on the next page button.");
            TestLogger.info("Clicked on the next page button.");

            // Verify pagination info has changed
            String newPaginationInfo = activityPage.getPaginationInfoText();
            Assert.assertNotEquals(newPaginationInfo, initialPaginationInfo, "Pagination info should change after clicking next.");
            getExtentTest().log(Status.PASS, "Pagination info updated correctly after clicking next.");
            TestLogger.success("Pagination info updated correctly after clicking next.");

            // Verify previous page button is now enabled
            Assert.assertTrue(activityPage.isPreviousPageButtonEnabled(), "Previous page button should be enabled after moving to the next page.");
            getExtentTest().log(Status.PASS, "Previous page button is enabled on the second page.");
            TestLogger.success("Previous page button is enabled on the second page.");

            // Click previous page button
            activityPage.clickPreviousPageButton();
            getExtentTest().log(Status.INFO, "Clicked on the previous page button.");
            TestLogger.info("Clicked on the previous page button.");

            // Verify pagination info is back to the initial state
            String finalPaginationInfo = activityPage.getPaginationInfoText();
            Assert.assertEquals(finalPaginationInfo, initialPaginationInfo, "Pagination info should revert to the initial state after clicking previous.");
            getExtentTest().log(Status.PASS, "Pagination info reverted correctly after clicking previous.");
            TestLogger.success("Pagination info reverted correctly after clicking previous.");
        } else {
            getExtentTest().log(Status.INFO, "Skipping pagination test as there is only one page of activities.");
            TestLogger.info("Skipping pagination test as there is only one page of activities.");
        }
    }

    @Test(priority = 4, description = "Verify that the 'Add Activity' button is clickable and opens the modal.")
    public void verifyAddActivityButtonClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying 'Add Activity' Button Clickable");
        TestLogger.info("Test: Verifying 'Add Activity' Button Clickable");

        // Click the 'Add Activity' button
        activityPage.clickAddActivityButton();
        getExtentTest().log(Status.INFO, "Clicked on the 'Add Activity' button.");
        TestLogger.info("Clicked on the 'Add Activity' button.");

        // Verify that the 'Add Activity' modal is displayed
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "'Add Activity' modal should be displayed after clicking the button.");
        getExtentTest().log(Status.PASS, "'Add Activity' modal is displayed.");
        TestLogger.success("'Add Activity' modal is displayed.");
    }
    

    

}
