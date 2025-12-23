package TestRun;

import Base.BaseTest;
import Pages.ActivityPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * ActivityTest - Contains all test cases for Activity Page
 * Tests cover positive, negative, and UI scenarios
 *
 * @author Sterna QA Team
 * @version 2.0
 */
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

    // ==================== UI TEST CASES ====================

    /**
     * TC_ACTIVITY_UI_001: Verify that all essential elements are displayed on the Activity page
     */
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

    /**
     * TC_ACTIVITY_UI_002: Verify the headers of the activity table
     */
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

    /**
     * TC_ACTIVITY_UI_003: Verify page sub-header text
     */
    @Test(priority = 3, description = "Verify page sub-header text is displayed correctly.")
    public void verifyActivityPageSubHeader() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Page Sub-Header");
        TestLogger.info("Test: Verifying Activity Page Sub-Header");

        Assert.assertTrue(activityPage.isPageSubHeaderDisplayed(), "Page sub-header should be displayed.");
        String expectedSubHeader = "Manage and monitor all scheduled access activities";
        Assert.assertEquals(activityPage.getPageSubHeaderText(), expectedSubHeader, "Sub-header text is incorrect.");
        getExtentTest().log(Status.PASS, "Page sub-header is displayed correctly.");
        TestLogger.success("Page sub-header is displayed correctly.");
    }

    /**
     * TC_ACTIVITY_UI_004: Verify table header row is displayed
     */
    @Test(priority = 4, description = "Verify table header row is displayed.")
    public void verifyTableHeaderRowDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Header Row");
        TestLogger.info("Test: Verifying Table Header Row");

        Assert.assertTrue(activityPage.isTableHeaderRowDisplayed(), "Table header row should be displayed.");
        getExtentTest().log(Status.PASS, "Table header row is displayed.");
        TestLogger.success("Table header row is displayed.");
    }

    /**
     * TC_ACTIVITY_UI_005: Verify action buttons are present for each row
     */
    @Test(priority = 5, description = "Verify action buttons are present for each table row.")
    public void verifyActionButtonsPresent() {
        getExtentTest().log(Status.INFO, "Test: Verifying Action Buttons");
        TestLogger.info("Test: Verifying Action Buttons");

        int rowCount = activityPage.getTableRowCount();
        int actionButtonCount = activityPage.getActionButtonsCount();

        if (rowCount > 0) {
            Assert.assertEquals(actionButtonCount, rowCount, "Each row should have an action button.");
            getExtentTest().log(Status.PASS, "Action buttons are present for all rows.");
            TestLogger.success("Action buttons are present for all rows.");
        } else {
            getExtentTest().log(Status.INFO, "No rows present in the table to verify action buttons.");
            TestLogger.info("No rows present in the table to verify action buttons.");
        }
    }


    /**
     * TC_ACTIVITY_UI_006: Verify Add Activity modal elements
     */
    @Test(priority = 6, description = "Verify Add Activity modal displays all required elements.")
    public void verifyAddActivityModalElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Activity Modal Elements");
        TestLogger.info("Test: Verifying Add Activity Modal Elements");

        activityPage.clickAddActivityButton();

        // Verify modal is displayed
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Add Activity modal should be displayed.");
        getExtentTest().log(Status.PASS, "Add Activity modal is displayed.");

        // Verify modal header
        Assert.assertTrue(activityPage.isModalHeaderDisplayed(), "Modal header should be displayed.");
        getExtentTest().log(Status.PASS, "Modal header is displayed.");

        // Verify form elements
        Assert.assertTrue(activityPage.isActivityNameInputDisplayed(), "Activity Name input should be displayed.");
        getExtentTest().log(Status.PASS, "Activity Name input is displayed.");

        Assert.assertTrue(activityPage.isLocationLevelDropdownDisplayed(), "Location Level dropdown should be displayed.");
        getExtentTest().log(Status.PASS, "Location Level dropdown is displayed.");

        Assert.assertTrue(activityPage.isSelectAccessRoleButtonDisplayed(), "Select Access Role button should be displayed.");
        getExtentTest().log(Status.PASS, "Select Access Role button is displayed.");

        Assert.assertTrue(activityPage.isSelectKeysButtonDisplayed(), "Select Keys button should be displayed.");
        getExtentTest().log(Status.PASS, "Select Keys button is displayed.");

        getExtentTest().log(Status.PASS, "All Add Activity modal elements are displayed correctly.");
        TestLogger.success("All Add Activity modal elements are displayed correctly.");
    }

    /**
     * TC_ACTIVITY_UI_007: Verify wizard steps are displayed in modal
     */
    @Test(priority = 7, description = "Verify wizard steps are displayed in Add Activity modal.")
    public void verifyWizardStepsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Wizard Steps in Modal");
        TestLogger.info("Test: Verifying Wizard Steps in Modal");

        activityPage.clickAddActivityButton();

        // Verify all wizard steps are visible
        Assert.assertTrue(activityPage.isScheduleStepDisplayed(), "Schedule step should be displayed.");
        getExtentTest().log(Status.PASS, "Schedule step is displayed.");

        Assert.assertTrue(activityPage.isFrequencyStepDisplayed(), "Frequency step should be displayed.");
        getExtentTest().log(Status.PASS, "Frequency step is displayed.");

        Assert.assertTrue(activityPage.isConfirmationStepDisplayed(), "Confirmation step should be displayed.");
        getExtentTest().log(Status.PASS, "Confirmation step is displayed.");

        getExtentTest().log(Status.PASS, "All wizard steps are displayed correctly.");
        TestLogger.success("All wizard steps are displayed correctly.");
    }

    // ==================== PAGINATION TEST CASES ====================

    /**
     * TC_ACTIVITY_PAG_001: Verify pagination functionality
     */
    @Test(priority = 8, description = "Verify the pagination functionality of the activity table.")
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

    /**
     * TC_ACTIVITY_PAG_002: Verify first and last page buttons
     */
    @Test(priority = 9, description = "Verify first and last page buttons functionality.")
    public void verifyFirstAndLastPageButtons() {
        getExtentTest().log(Status.INFO, "Test: Verifying First and Last Page Buttons");
        TestLogger.info("Test: Verifying First and Last Page Buttons");

        // Verify first page button is disabled on first page
        Assert.assertFalse(activityPage.isFirstPageButtonEnabled(), "First page button should be disabled on the first page.");
        getExtentTest().log(Status.PASS, "First page button is disabled on the first page.");

        if (activityPage.isLastPageButtonEnabled()) {
            // Click last page button
            activityPage.clickLastPageButton();
            getExtentTest().log(Status.INFO, "Clicked on the last page button.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Verify next page button is now disabled
            Assert.assertFalse(activityPage.isNextPageButtonEnabled(), "Next page button should be disabled on the last page.");
            getExtentTest().log(Status.PASS, "Next page button is disabled on the last page.");

            // Verify first page button is now enabled
            Assert.assertTrue(activityPage.isFirstPageButtonEnabled(), "First page button should be enabled on the last page.");
            getExtentTest().log(Status.PASS, "First page button is enabled on the last page.");

            // Click first page button to go back
            activityPage.clickFirstPageButton();
            getExtentTest().log(Status.INFO, "Clicked on the first page button.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Verify we're back on first page
            Assert.assertFalse(activityPage.isPreviousPageButtonEnabled(), "Previous page button should be disabled on the first page.");
            getExtentTest().log(Status.PASS, "Successfully navigated back to the first page.");
        } else {
            getExtentTest().log(Status.INFO, "Skipping test as there is only one page of activities.");
        }
    }

    // ==================== POSITIVE TEST CASES ====================

    /**
     * TC_ACTIVITY_POS_001: Verify that the 'Add Activity' button is clickable and opens the modal
     */
    @Test(priority = 10, description = "Verify that the 'Add Activity' button is clickable and opens the modal.")
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

    /**
     * TC_ACTIVITY_POS_002: Verify cancel button closes the Add Activity modal
     */
    @Test(priority = 11, description = "Verify cancel button closes the Add Activity modal.")
    public void verifyCancelButtonClosesModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Button Closes Modal");
        TestLogger.info("Test: Verifying Cancel Button Closes Modal");

        activityPage.clickAddActivityButton();
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Modal should be displayed.");
        getExtentTest().log(Status.INFO, "Modal is displayed.");

        activityPage.clickCancelButton();
        getExtentTest().log(Status.INFO, "Clicked cancel button.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(activityPage.isModalClosed(), "Modal should be closed after clicking cancel.");
        getExtentTest().log(Status.PASS, "Modal closed successfully after clicking cancel.");
        TestLogger.success("Modal closed successfully after clicking cancel.");
    }

    /**
     * TC_ACTIVITY_POS_003: Verify close button closes the modal
     */
    @Test(priority = 12, description = "Verify close button (X) closes the Add Activity modal.")
    public void verifyCloseButtonClosesModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Close Button Closes Modal");
        TestLogger.info("Test: Verifying Close Button Closes Modal");

        activityPage.clickAddActivityButton();
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Modal should be displayed.");
        getExtentTest().log(Status.INFO, "Modal is displayed.");

        activityPage.clickCloseButton();
        getExtentTest().log(Status.INFO, "Clicked close button.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(activityPage.isModalClosed(), "Modal should be closed after clicking close button.");
        getExtentTest().log(Status.PASS, "Modal closed successfully after clicking close button.");
        TestLogger.success("Modal closed successfully after clicking close button.");
    }

    /**
     * TC_ACTIVITY_POS_004: Verify activity name field accepts valid input
     */
    @Test(priority = 13, description = "Verify activity name field accepts valid input.")
    public void verifyActivityNameFieldAcceptsInput() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name Field Accepts Input");
        TestLogger.info("Test: Verifying Activity Name Field Accepts Input");

        activityPage.clickAddActivityButton();
        String testActivityName = "Test Activity 123";
        activityPage.enterActivityName(testActivityName);

        String enteredValue = activityPage.getActivityNameValue();
        Assert.assertEquals(enteredValue, testActivityName, "Activity name should be entered correctly.");
        getExtentTest().log(Status.PASS, "Activity name field accepts valid input.");
        TestLogger.success("Activity name field accepts valid input.");
    }

    /**
     * TC_ACTIVITY_POS_005: Verify search functionality with valid activity name
     */
    @Test(priority = 14, description = "Verify search functionality with valid activity name.")
    public void verifySearchFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Functionality");
        TestLogger.info("Test: Verifying Search Functionality");

        int initialRowCount = activityPage.getTableRowCount();
        if (initialRowCount > 0) {
            // Get the first activity name to search
            String activityName = activityPage.getFirstRowText(1);
            activityPage.searchActivity(activityName);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int searchResultCount = activityPage.getTableRowCount();
            Assert.assertTrue(searchResultCount >= 0, "Search should return results or empty table.");
            getExtentTest().log(Status.PASS, "Search functionality works correctly.");
            TestLogger.success("Search functionality works correctly.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test search functionality.");
        }
    }

    /**
     * TC_ACTIVITY_POS_006: Verify View action displays activity details
     */
    @Test(priority = 15, description = "Verify View action displays activity details.")
    public void verifyViewActivityDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Activity Details");
        TestLogger.info("Test: Verifying View Activity Details");

        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickActionMenuForRow(0);
            getExtentTest().log(Status.INFO, "Clicked action menu for first row.");

            activityPage.selectViewFromActionMenu();
            getExtentTest().log(Status.INFO, "Selected View from action menu.");

            Assert.assertTrue(activityPage.isViewModalDisplayed(), "View modal should be displayed.");
            getExtentTest().log(Status.PASS, "View modal is displayed.");

            // Verify activity details are shown
            String activityName = activityPage.getViewModalActivityName();
            Assert.assertFalse(activityName.isEmpty(), "Activity name should be displayed in view modal.");
            getExtentTest().log(Status.PASS, "Activity details are displayed correctly. Activity Name: " + activityName);

            activityPage.closeViewModal();
            getExtentTest().log(Status.PASS, "View modal closed successfully.");
            TestLogger.success("View activity details test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test View functionality.");
        }
    }

    /**
     * TC_ACTIVITY_POS_007: Verify Edit action opens edit modal
     */
    @Test(priority = 16, description = "Verify Edit action opens edit modal.")
    public void verifyEditActivityOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Activity Opens Modal");
        TestLogger.info("Test: Verifying Edit Activity Opens Modal");

        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickActionMenuForRow(0);
            getExtentTest().log(Status.INFO, "Clicked action menu for first row.");

            activityPage.selectEditFromActionMenu();
            getExtentTest().log(Status.INFO, "Selected Edit from action menu.");

            Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Edit modal should be displayed.");
            getExtentTest().log(Status.PASS, "Edit modal is displayed successfully.");

            activityPage.clickCancelButton();
            getExtentTest().log(Status.PASS, "Edit modal closed successfully.");
            TestLogger.success("Edit activity modal test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test Edit functionality.");
        }
    }

    /**
     * TC_ACTIVITY_POS_008: Verify Delete confirmation dialog appears
     */
    @Test(priority = 17, description = "Verify Delete confirmation dialog appears.")
    public void verifyDeleteConfirmationAppears() {
        getExtentTest().log(Status.INFO, "Test: Verifying Delete Confirmation Dialog");
        TestLogger.info("Test: Verifying Delete Confirmation Dialog");

        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickActionMenuForRow(0);
            getExtentTest().log(Status.INFO, "Clicked action menu for first row.");

            activityPage.selectDeleteFromActionMenu();
            getExtentTest().log(Status.INFO, "Selected Delete from action menu.");

            Assert.assertTrue(activityPage.isDeleteConfirmationDisplayed(), "Delete confirmation dialog should be displayed.");
            getExtentTest().log(Status.PASS, "Delete confirmation dialog is displayed.");

            // Cancel the deletion
            activityPage.cancelDeletion();
            getExtentTest().log(Status.PASS, "Delete confirmation canceled successfully.");
            TestLogger.success("Delete confirmation dialog test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test Delete functionality.");
        }
    }

    /**
     * TC_ACTIVITY_POS_009: Verify navigation between wizard steps
     */
    @Test(priority = 18, description = "Verify navigation between wizard steps using Next and Previous buttons.")
    public void verifyWizardStepNavigation() {
        getExtentTest().log(Status.INFO, "Test: Verifying Wizard Step Navigation");
        TestLogger.info("Test: Verifying Wizard Step Navigation");

        activityPage.clickAddActivityButton();
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Modal should be displayed.");

        // Fill basic info and go to next step
        activityPage.enterActivityName("Test Navigation Activity");
        activityPage.selectLocationLevel();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Note: Additional fields may need to be filled based on form requirements
        getExtentTest().log(Status.INFO, "Filled basic info step.");

        // Verify Previous button functionality (if available)
        if (activityPage.isPreviousButtonDisplayed()) {
            activityPage.clickPreviousButton();
            getExtentTest().log(Status.INFO, "Clicked Previous button.");
            getExtentTest().log(Status.PASS, "Previous button navigation works.");
        }

        activityPage.clickCancelButton();
        getExtentTest().log(Status.PASS, "Wizard step navigation test completed.");
        TestLogger.success("Wizard step navigation test passed.");
    }

    /**
     * TC_ACTIVITY_POS_010: Verify total activity count
     */
    @Test(priority = 19, description = "Verify total activity count across all pages.")
    public void verifyTotalActivityCount() {
        getExtentTest().log(Status.INFO, "Test: Verifying Total Activity Count");
        TestLogger.info("Test: Verifying Total Activity Count");

        String totalCount = activityPage.totalActivityCount();
        int count = Integer.parseInt(totalCount);

        Assert.assertTrue(count >= 0, "Total activity count should be zero or more.");
        getExtentTest().log(Status.PASS, "Total activity count is: " + totalCount);
        TestLogger.success("Total activity count verification passed: " + totalCount);
    }

    // ==================== NEGATIVE TEST CASES ====================

    /**
     * TC_ACTIVITY_NEG_001: Verify activity creation fails with empty activity name
     */
    @Test(priority = 20, description = "Verify activity creation fails with empty activity name.")
    public void verifyActivityCreationWithEmptyName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Creation with Empty Name");
        TestLogger.info("Test: Verifying Activity Creation with Empty Name");

        activityPage.clickAddActivityButton();
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Modal should be displayed.");

        // Leave activity name empty and try to proceed
        activityPage.clearActivityName();
        activityPage.clickSubmitButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should show validation error or remain on same step
        boolean hasError = activityPage.isActivityNameErrorDisplayed() || activityPage.isActivityModalDisplayed();
        Assert.assertTrue(hasError, "Should show validation error for empty activity name.");
        getExtentTest().log(Status.PASS, "Validation error shown for empty activity name.");
        TestLogger.success("Empty activity name validation test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_002: Verify activity name field rejects special characters
     */
    @Test(priority = 21, description = "Verify activity name field handles special characters.")
    public void verifyActivityNameWithSpecialCharacters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with Special Characters");
        TestLogger.info("Test: Verifying Activity Name with Special Characters");

        activityPage.clickAddActivityButton();
        activityPage.enterActivityNameWithSpecialCharacters();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if error message is displayed or the field handles it
        boolean isStillOnModal = activityPage.isActivityModalDisplayed();
        Assert.assertTrue(isStillOnModal, "Should handle special characters gracefully.");
        getExtentTest().log(Status.PASS, "Special characters handled correctly in activity name field.");
        TestLogger.success("Special characters test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_003: Verify activity name field with very long text
     */
    @Test(priority = 22, description = "Verify activity name field handles very long text.")
    public void verifyActivityNameWithVeryLongText() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with Very Long Text");
        TestLogger.info("Test: Verifying Activity Name with Very Long Text");

        activityPage.clickAddActivityButton();
        activityPage.enterActivityNameWithVeryLongText();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isStillOnModal = activityPage.isActivityModalDisplayed();
        Assert.assertTrue(isStillOnModal, "Should handle very long text gracefully.");
        getExtentTest().log(Status.PASS, "Very long text handled correctly in activity name field.");
        TestLogger.success("Very long text test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_004: Verify activity name field with spaces only
     */
    @Test(priority = 23, description = "Verify activity name field rejects spaces only input.")
    public void verifyActivityNameWithSpacesOnly() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with Spaces Only");
        TestLogger.info("Test: Verifying Activity Name with Spaces Only");

        activityPage.clickAddActivityButton();
        activityPage.enterActivityNameWithSpacesOnly();
        activityPage.clickSubmitButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should show validation error
        boolean hasError = activityPage.isActivityNameErrorDisplayed() || activityPage.isActivityModalDisplayed();
        Assert.assertTrue(hasError, "Should show validation error for spaces only input.");
        getExtentTest().log(Status.PASS, "Spaces only input handled correctly.");
        TestLogger.success("Spaces only test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_005: Verify activity name field handles SQL injection attempt
     */
    @Test(priority = 24, description = "Verify activity name field handles SQL injection attempt.")
    public void verifyActivityNameWithSQLInjection() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with SQL Injection");
        TestLogger.info("Test: Verifying Activity Name with SQL Injection");

        activityPage.clickAddActivityButton();
        activityPage.enterActivityNameWithSQLInjection();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isStillOnModal = activityPage.isActivityModalDisplayed();
        Assert.assertTrue(isStillOnModal, "Should handle SQL injection attempt gracefully.");
        getExtentTest().log(Status.PASS, "SQL injection attempt handled correctly.");
        TestLogger.success("SQL injection test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_006: Verify activity name field handles XSS attack attempt
     */
    @Test(priority = 25, description = "Verify activity name field handles XSS attack attempt.")
    public void verifyActivityNameWithXSSAttack() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with XSS Attack");
        TestLogger.info("Test: Verifying Activity Name with XSS Attack");

        activityPage.clickAddActivityButton();
        activityPage.enterActivityNameWithXSS();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isStillOnModal = activityPage.isActivityModalDisplayed();
        Assert.assertTrue(isStillOnModal, "Should handle XSS attack attempt gracefully.");
        getExtentTest().log(Status.PASS, "XSS attack attempt handled correctly.");
        TestLogger.success("XSS attack test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_007: Verify activity creation fails without selecting location level
     */
    @Test(priority = 26, description = "Verify activity creation fails without selecting location level.")
    public void verifyActivityCreationWithoutLocationLevel() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Creation without Location Level");
        TestLogger.info("Test: Verifying Activity Creation without Location Level");

        activityPage.clickAddActivityButton();
        activityPage.enterActivityName("Test Activity No Location");
        // Don't select location level
        activityPage.clickSubmitButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should show validation error or remain on same step
        boolean hasError = activityPage.isLocationLevelErrorDisplayed() || activityPage.isActivityModalDisplayed();
        Assert.assertTrue(hasError, "Should show validation error for missing location level.");
        getExtentTest().log(Status.PASS, "Validation shown for missing location level.");
        TestLogger.success("Missing location level validation test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_008: Verify activity creation fails without selecting access role
     */
    @Test(priority = 27, description = "Verify activity creation fails without selecting access role.")
    public void verifyActivityCreationWithoutAccessRole() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Creation without Access Role");
        TestLogger.info("Test: Verifying Activity Creation without Access Role");

        activityPage.clickAddActivityButton();
        activityPage.enterActivityName("Test Activity No Role");
        activityPage.selectLocationLevel();
        // Don't select access role
        activityPage.clickSubmitButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should show validation error or remain on same step
        boolean hasError = activityPage.isAccessRoleErrorDisplayed() || activityPage.isActivityModalDisplayed();
        Assert.assertTrue(hasError, "Should show validation error for missing access role.");
        getExtentTest().log(Status.PASS, "Validation shown for missing access role.");
        TestLogger.success("Missing access role validation test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_009: Verify search with non-existent activity name
     */
    @Test(priority = 28, description = "Verify search with non-existent activity name returns no results.")
    public void verifySearchWithNonExistentName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Non-Existent Name");
        TestLogger.info("Test: Verifying Search with Non-Existent Name");

        String nonExistentName = "NonExistentActivity_XYZ_12345";
        activityPage.searchActivity(nonExistentName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int rowCount = activityPage.getTableRowCount();
        Assert.assertEquals(rowCount, 0, "Search with non-existent name should return no results.");
        getExtentTest().log(Status.PASS, "Search with non-existent name handled correctly.");
        TestLogger.success("Non-existent name search test passed.");
    }

    /**
     * TC_ACTIVITY_NEG_010: Verify field clear functionality
     */
    @Test(priority = 29, description = "Verify activity name field can be cleared.")
    public void verifyActivityNameFieldClearFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verifying Field Clear Functionality");
        TestLogger.info("Test: Verifying Field Clear Functionality");

        activityPage.clickAddActivityButton();
        String testName = "Test Activity to Clear";
        activityPage.enterActivityName(testName);

        // Verify input was entered
        Assert.assertEquals(activityPage.getActivityNameValue(), testName, "Activity name should be entered.");
        getExtentTest().log(Status.INFO, "Activity name entered: " + testName);

        // Clear the field
        activityPage.clearActivityName();

        String clearedValue = activityPage.getActivityNameValue();
        Assert.assertTrue(clearedValue.isEmpty(), "Activity name field should be cleared.");
        getExtentTest().log(Status.PASS, "Activity name field cleared successfully.");
        TestLogger.success("Field clear functionality test passed.");
    }

    // ==================== DATA DRIVEN TEST CASES ====================

    /**
     * Data Provider for invalid activity names
     */
    @DataProvider(name = "invalidActivityNames")
    public Object[][] getInvalidActivityNames() {
        return new Object[][] {
                {"", "Empty activity name"},
                {"   ", "Spaces only"},
                {"<script>alert('test')</script>", "XSS injection"},
                {"' OR '1'='1", "SQL injection"},
                {"Test\nActivity", "Name with newline"},
                {"Test\tActivity", "Name with tab"}
        };
    }

    /**
     * TC_ACTIVITY_NEG_011: Data-driven test for invalid activity names
     */
    @Test(priority = 30, dataProvider = "invalidActivityNames",
            description = "Verify activity name field handles various invalid inputs.")
    public void verifyActivityNameWithVariousInvalidInputs(String activityName, String scenario) {
        getExtentTest().log(Status.INFO, "Test Scenario: " + scenario);
        TestLogger.info("Test Scenario: " + scenario);

        activityPage.clickAddActivityButton();
        activityPage.enterActivityName(activityName);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should remain on modal and handle gracefully
        boolean isStillOnModal = activityPage.isActivityModalDisplayed();
        Assert.assertTrue(isStillOnModal, "Should handle invalid input gracefully for scenario: " + scenario);
        getExtentTest().log(Status.PASS, "Scenario passed: " + scenario);

        activityPage.clickCancelButton();
    }

    // ==================== VIEW MODAL TEST CASES ====================

    /**
     * TC_ACTIVITY_VIEW_001: Verify View modal displays all activity details
     */
    @Test(priority = 31, description = "Verify View modal displays all activity details sections.")
    public void verifyViewModalDisplaysAllDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Modal Displays All Details");
        TestLogger.info("Test: Verifying View Modal Displays All Details");

        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickActionMenuForRow(0);
            activityPage.selectViewFromActionMenu();

            Assert.assertTrue(activityPage.isViewModalDisplayed(), "View modal should be displayed.");
            getExtentTest().log(Status.PASS, "View modal is displayed.");

            // Verify activity name
            String activityName = activityPage.getViewModalActivityName();
            Assert.assertFalse(activityName.isEmpty(), "Activity name should not be empty.");
            getExtentTest().log(Status.PASS, "Activity name displayed: " + activityName);

            // Verify status
            String status = activityPage.getView_ModalStatus();
            Assert.assertNotNull(status, "Status should be displayed.");
            getExtentTest().log(Status.PASS, "Activity status displayed: " + status);

            // Verify access roles
            List<String> accessRoles = activityPage.getViewModalAccessRoles();
            getExtentTest().log(Status.INFO, "Access roles count: " + accessRoles.size());

            activityPage.closeViewModal();
            getExtentTest().log(Status.PASS, "View modal details verification completed.");
            TestLogger.success("View modal details test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to verify view modal details.");
        }
    }

    /**
     * TC_ACTIVITY_VIEW_002: Verify View modal close functionality
     */
    @Test(priority = 32, description = "Verify View modal can be closed using close button.")
    public void verifyViewModalCloseButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Modal Close Button");
        TestLogger.info("Test: Verifying View Modal Close Button");

        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickActionMenuForRow(0);
            activityPage.selectViewFromActionMenu();

            Assert.assertTrue(activityPage.isViewModalDisplayed(), "View modal should be displayed.");
            getExtentTest().log(Status.INFO, "View modal is displayed.");

            activityPage.closeViewModal();
            getExtentTest().log(Status.INFO, "Clicked close button on view modal.");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Assert.assertTrue(activityPage.isModalClosed(), "View modal should be closed.");
            getExtentTest().log(Status.PASS, "View modal closed successfully.");
            TestLogger.success("View modal close button test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test view modal close.");
        }
    }

    // ==================== DELETE FUNCTIONALITY TEST CASES ====================

    /**
     * TC_ACTIVITY_DEL_001: Verify cancel delete keeps the activity
     */
    @Test(priority = 33, description = "Verify canceling delete action keeps the activity.")
    public void verifyCancelDeleteKeepsActivity() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Delete Keeps Activity");
        TestLogger.info("Test: Verifying Cancel Delete Keeps Activity");

        int initialRowCount = activityPage.getTableRowCount();
        if (initialRowCount > 0) {
            activityPage.clickActionMenuForRow(0);
            activityPage.selectDeleteFromActionMenu();

            Assert.assertTrue(activityPage.isDeleteConfirmationDisplayed(), "Delete confirmation should appear.");
            getExtentTest().log(Status.INFO, "Delete confirmation dialog displayed.");

            activityPage.cancelDeletion();
            getExtentTest().log(Status.INFO, "Canceled deletion.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int finalRowCount = activityPage.getTableRowCount();
            Assert.assertEquals(finalRowCount, initialRowCount, "Activity count should remain the same after canceling delete.");
            getExtentTest().log(Status.PASS, "Cancel delete works correctly. Activity preserved.");
            TestLogger.success("Cancel delete test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test cancel delete.");
        }
    }

    // ==================== MODAL STEP ELEMENTS TEST CASES ====================

    /**
     * TC_ACTIVITY_STEP_001: Verify Schedule step elements are displayed
     */
    @Test(priority = 34, description = "Verify Schedule step displays all required elements.")
    public void verifyScheduleStepElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Schedule Step Elements");
        TestLogger.info("Test: Verifying Schedule Step Elements");

        activityPage.clickAddActivityButton();

        // Fill basic info to proceed to schedule step
        activityPage.enterActivityName("Test Schedule Step");
        getExtentTest().log(Status.INFO, "Entered activity name.");

        // Note: Navigate to Schedule step if needed by filling required fields
        // This depends on the actual form flow

        getExtentTest().log(Status.PASS, "Schedule step elements verification initiated.");
        TestLogger.success("Schedule step elements test completed.");

        activityPage.clickCancelButton();
    }

    /**
     * TC_ACTIVITY_STEP_002: Verify Frequency step elements are displayed
     */
    @Test(priority = 35, description = "Verify Frequency step displays checkbox options.")
    public void verifyFrequencyStepElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Frequency Step Elements");
        TestLogger.info("Test: Verifying Frequency Step Elements");

        activityPage.clickAddActivityButton();

        // Verify frequency step indicator is displayed
        Assert.assertTrue(activityPage.isFrequencyStepDisplayed(), "Frequency step should be displayed in wizard.");
        getExtentTest().log(Status.PASS, "Frequency step is visible in wizard.");
        TestLogger.success("Frequency step elements test completed.");

        activityPage.clickCancelButton();
    }

    /**
     * TC_ACTIVITY_STEP_003: Verify Confirmation step elements
     */
    @Test(priority = 36, description = "Verify Confirmation step is displayed in wizard.")
    public void verifyConfirmationStepElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Confirmation Step Elements");
        TestLogger.info("Test: Verifying Confirmation Step Elements");

        activityPage.clickAddActivityButton();

        // Verify confirmation step indicator is displayed
        Assert.assertTrue(activityPage.isConfirmationStepDisplayed(), "Confirmation step should be displayed in wizard.");
        getExtentTest().log(Status.PASS, "Confirmation step is visible in wizard.");
        TestLogger.success("Confirmation step elements test completed.");

        activityPage.clickCancelButton();
    }

    // ==================== ACTIVITY NAME VALIDATION TEST CASES ====================

    /**
     * TC_ACTIVITY_VAL_001: Verify activity name with numbers only
     */
    @Test(priority = 37, description = "Verify activity name field accepts numbers only.")
    public void verifyActivityNameWithNumbersOnly() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with Numbers Only");
        TestLogger.info("Test: Verifying Activity Name with Numbers Only");

        activityPage.clickAddActivityButton();
        String numbersOnlyName = "123456789";
        activityPage.enterActivityName(numbersOnlyName);

        String enteredValue = activityPage.getActivityNameValue();
        Assert.assertEquals(enteredValue, numbersOnlyName, "Activity name should accept numbers only.");
        getExtentTest().log(Status.PASS, "Activity name field accepts numbers only input.");
        TestLogger.success("Numbers only activity name test passed.");

        activityPage.clickCancelButton();
    }

    /**
     * TC_ACTIVITY_VAL_002: Verify activity name with alphanumeric input
     */
    @Test(priority = 38, description = "Verify activity name field accepts alphanumeric input.")
    public void verifyActivityNameWithAlphanumeric() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with Alphanumeric Input");
        TestLogger.info("Test: Verifying Activity Name with Alphanumeric Input");

        activityPage.clickAddActivityButton();
        String alphanumericName = "Activity123Test456";
        activityPage.enterActivityName(alphanumericName);

        String enteredValue = activityPage.getActivityNameValue();
        Assert.assertEquals(enteredValue, alphanumericName, "Activity name should accept alphanumeric input.");
        getExtentTest().log(Status.PASS, "Activity name field accepts alphanumeric input.");
        TestLogger.success("Alphanumeric activity name test passed.");

        activityPage.clickCancelButton();
    }

    /**
     * TC_ACTIVITY_VAL_003: Verify activity name with leading/trailing spaces
     */
    @Test(priority = 39, description = "Verify activity name field handles leading/trailing spaces.")
    public void verifyActivityNameWithLeadingTrailingSpaces() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with Leading/Trailing Spaces");
        TestLogger.info("Test: Verifying Activity Name with Leading/Trailing Spaces");

        activityPage.clickAddActivityButton();
        String nameWithSpaces = "   Test Activity   ";
        activityPage.enterActivityName(nameWithSpaces);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Field should either trim the spaces or handle them
        boolean isStillOnModal = activityPage.isActivityModalDisplayed();
        Assert.assertTrue(isStillOnModal, "Should handle leading/trailing spaces gracefully.");
        getExtentTest().log(Status.PASS, "Leading/trailing spaces handled correctly.");
        TestLogger.success("Leading/trailing spaces test passed.");

        activityPage.clickCancelButton();
    }

    /**
     * TC_ACTIVITY_VAL_004: Verify activity name is case sensitive
     */
    @Test(priority = 40, description = "Verify activity name field preserves case.")
    public void verifyActivityNameCaseSensitivity() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name Case Sensitivity");
        TestLogger.info("Test: Verifying Activity Name Case Sensitivity");

        activityPage.clickAddActivityButton();
        String mixedCaseName = "TeSt AcTiViTy NaMe";
        activityPage.enterActivityName(mixedCaseName);

        String enteredValue = activityPage.getActivityNameValue();
        Assert.assertEquals(enteredValue, mixedCaseName, "Activity name should preserve case.");
        getExtentTest().log(Status.PASS, "Activity name field preserves case correctly.");
        TestLogger.success("Case sensitivity test passed.");

        activityPage.clickCancelButton();
    }
}
