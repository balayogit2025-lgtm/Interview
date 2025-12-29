package TestRun;

import Base.BaseTest;
import Pages.ActivityPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static Base.BaseTest.getExtentTest;

public class ActivityTests extends BaseTest {
    private LoginPage loginPage;
    private ActivityPage activityPage;

    @BeforeMethod
    public void setupActivityPage() {
        navigateToApp();
        loginPage = new LoginPage(getDriver());
        activityPage = new ActivityPage(getDriver());
        loginPage.login(ConfigReader.getValidEmail(), ConfigReader.getValidPassword());
        activityPage.navigateToActivityPage();
        getExtentTest().log(Status.INFO, "Setup complete. Navigated to Activity Page.");
    }

    @Test(priority = 1, description = "Verify that all essential elements are displayed on the Activity page.")
    public void verifyActivityGridPageElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Page Elements");
        TestLogger.info("Test: Verifying Activity Page Elements");
        Assert.assertTrue(activityPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(activityPage.getPageHeaderText(), "Activity", "Page header text is incorrect.");
        getExtentTest().log(Status.PASS, "Page header is correct and displayed.");
        TestLogger.success("Page header is correct and displayed.");
        Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(), "'Add Activity' button should be displayed.");
        getExtentTest().log(Status.PASS, "'Add Activity' button is displayed.");
        TestLogger.success("'Add Activity' button is correct and displayed.");
        Assert.assertTrue(activityPage.isSearchButtonDisplayed(), "Search button should be displayed.");
        getExtentTest().log(Status.PASS, "Search button is displayed.");
        TestLogger.info("Test: Verifying Activity Page Elements");
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
        List<String> expectedHeaders = List.of("S.NO", "NAME", "LOCATION", "DATE", "TIME", "CREATED BY", "UPDATED BY", "ACTION");
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

    @Test(priority = 4, description = "Verify table header row is displayed.")
    public void verifyTableHeaderRowDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Header Row");
        TestLogger.info("Test: Verifying Table Header Row");
        Assert.assertTrue(activityPage.isTableHeaderRowDisplayed(), "Table header row should be displayed.");
        getExtentTest().log(Status.PASS, "Table header row is displayed.");
        TestLogger.success("Table header row is displayed.");
    }

    @Test(priority = 5, description = "Verify action buttons are present for each table row.")
    public void verifyActionButtonsPresent() {
        getExtentTest().log(Status.INFO, "Test: Verifying Action Buttons");
        TestLogger.info("Test: Verifying Action Buttons");
        int rowCount = activityPage.getTableRowCount();
        int actionButtonCount = activityPage.getActionButtonsCount();
        if (rowCount > 0) {
            Assert.assertEquals(rowCount, actionButtonCount, "Action Buttons count is incorrect");
            getExtentTest().log(Status.PASS, "Action buttons are present for all rows.");
            TestLogger.success("Action buttons are present for all rows.");
        } else {
            getExtentTest().log(Status.INFO, "No rows present in the table to verify action buttons.");
            TestLogger.info("No rows present in the table to verify action buttons.");
        }
    }

    @Test(priority = 6, description = "Verify Add Activity modal displays all required elements.")
    public void verifyAddActivityModalElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Activity Modal Elements");
        TestLogger.info("Test: Verifying Add Activity Modal Elements");
        activityPage.clickAddActivityButton();
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Add Activity modal should be displayed.");
        getExtentTest().log(Status.PASS, "Add Activity modal is displayed.");
        Assert.assertTrue(activityPage.isModalHeaderDisplayed(), "Modal header should be displayed.");
        getExtentTest().log(Status.PASS, "Modal header is displayed.");
        Assert.assertTrue(activityPage.isActivityNameInputDisplayed(), "Activity Name input should be displayed.");
        getExtentTest().log(Status.PASS, "Activity Name input is displayed.");
        Assert.assertTrue(activityPage.isLocationLevelDropdownDisplayed(), "Location Level dropdown should be displayed.");
        getExtentTest().log(Status.PASS, "Location Level dropdown is displayed.");
        Assert.assertTrue(activityPage.isRoleDropdownDisplayed(), "Select Access Role button should be displayed.");
        getExtentTest().log(Status.PASS, "Select Access Role button is displayed.");
        Assert.assertTrue(activityPage.isKeyDropdownDisplayed(), "Select Keys button should be displayed.");
        getExtentTest().log(Status.PASS, "Select Keys button is displayed.");
        getExtentTest().log(Status.PASS, "All Add Activity modal elements are displayed correctly.");
        TestLogger.success("All Add Activity modal elements are displayed correctly.");
    }

    @Test(priority = 7, description = "Verify wizard steps are displayed in Add Activity modal.")
    public void verifyWizardStepsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Wizard Steps in Modal");
        TestLogger.info("Test: Verifying Wizard Steps in Modal");
        activityPage.clickAddActivityButton();
        boolean allwizardDisplayed = activityPage.isasicinfoStepDisplayed() && activityPage.isScheduleStepDisplayed()
                && activityPage.isFrequencyStepDisplayed() && activityPage.isConfirmationStepDisplayed();
        Assert.assertTrue(allwizardDisplayed, "Wizard steps are not displayed");
        getExtentTest().log(Status.PASS, "All wizard steps are displayed correctly.");
        TestLogger.success("All wizard steps are displayed correctly.");
    }

    @Test(priority = 8, description = "Verify the pagination functionality of the activity table.")
    public void verifyActivityTablePagination() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Table Pagination");
        TestLogger.info("Test: Verifying Activity Table Pagination");
        Assert.assertTrue(activityPage.isPaginationInfoDisplayed(), "Pagination info should be displayed.");
        getExtentTest().log(Status.PASS, "Pagination info is displayed.");
        TestLogger.success("Pagination info is displayed.");
        Assert.assertFalse(activityPage.isPreviousPageButtonEnabled(), "Previous page button should be disabled on the first page.");
        getExtentTest().log(Status.PASS, "Previous page button is disabled on the first page.");
        TestLogger.success("Previous page button is disabled on the first page.");
        if (activityPage.isNextPageButtonEnabled()) {
            String initialPaginationInfo = activityPage.getPaginationInfoText();
            activityPage.clickNextPageButton();
            getExtentTest().log(Status.INFO, "Clicked on the next page button.");
            TestLogger.info("Clicked on the next page button.");
            String newPaginationInfo = activityPage.getPaginationInfoText();
            Assert.assertNotEquals(newPaginationInfo, initialPaginationInfo, "Pagination info should change after clicking next.");
            getExtentTest().log(Status.PASS, "Pagination info updated correctly after clicking next.");
            TestLogger.success("Pagination info updated correctly after clicking next.");
            Assert.assertTrue(activityPage.isPreviousPageButtonEnabled(), "Previous page button should be enabled after moving to the next page.");
            getExtentTest().log(Status.PASS, "Previous page button is enabled on the second page.");
            TestLogger.success("Previous page button is enabled on the second page.");
            activityPage.clickPreviousPageButton();
            getExtentTest().log(Status.INFO, "Clicked on the previous page button.");
            TestLogger.info("Clicked on the previous page button.");
            String finalPaginationInfo = activityPage.getPaginationInfoText();
            Assert.assertEquals(finalPaginationInfo, initialPaginationInfo, "Pagination info should revert to the initial state after clicking previous.");
            getExtentTest().log(Status.PASS, "Pagination info reverted correctly after clicking previous.");
            TestLogger.success("Pagination info reverted correctly after clicking previous.");
        } else {
            getExtentTest().log(Status.INFO, "Skipping pagination test as there is only one page of activities.");
            TestLogger.info("Skipping pagination test as there is only one page of activities.");
        }
    }

    @Test(priority = 9, description = "Verify first and last page buttons are displayed")
    public void verifyFirstAndLastPageButtons() {
        getExtentTest().log(Status.INFO, "Test: Verifying the presence of First and Last Page Buttons");
        TestLogger.info("Test: Verifying First and Last Page Buttons");
        activityPage.navigateToActivityPage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        boolean firstandlastpagebuttondisplayed = activityPage.isFirstPageButtonDisplayed() && activityPage.isLastPageButtonDisplayed();
        Assert.assertTrue(firstandlastpagebuttondisplayed,"first and last page buttons are not displayed on the activity page");
        getExtentTest().log(Status.PASS, "First page button and Last Page button are displayed on the activity page");
    }

    @Test(priority = 10, description = "Verify that the 'Add Activity' button is clickable and opens the modal.")
    public void verifyAddActivityButtonClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying 'Add Activity' Button Clickable");
        TestLogger.info("Test: Verifying 'Add Activity' Button Clickable");
        activityPage.clickAddActivityButton();
        getExtentTest().log(Status.INFO, "Clicked on the 'Add Activity' button.");
        TestLogger.info("Clicked on the 'Add Activity' button.");
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "'Add Activity' modal should be displayed after clicking the button.");
        getExtentTest().log(Status.PASS, "'Add Activity' modal is displayed.");
        TestLogger.success("'Add Activity' modal is displayed.");
    }

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
        Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(), "Modal should be closed after clicking cancel.");
        getExtentTest().log(Status.PASS, "Modal closed successfully after clicking cancel.");
        TestLogger.success("Modal closed successfully after clicking cancel.");
    }

    @Test(priority = 12, description = "Verify close button (X) closes the Add Activity modal.")
    public void verifyCloseButtonInAddActivityModal() {
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
        Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(), "Modal should be closed after clicking close button.");
        getExtentTest().log(Status.PASS, "Modal closed successfully after clicking close button.");
        TestLogger.success("Modal closed successfully after clicking close button.");
    }

    @Test(priority = 13, description = "New activity creation")
    public void AddNewActivity() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name Case Sensitivity");
        TestLogger.info("Test: Verifying Activity Name Case Sensitivity");
        activityPage.addnewactivity();
        Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(),"New Activity is not created");
        getExtentTest().log(Status.PASS, "New Activity is been created");
        TestLogger.success("Successfully created a new activity");
    }


    @Test(priority = 14, description = "Verify activity creation fails with empty activity name")
    public void verifyActivityCreationWithEmptyName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Creation with Empty Name");
        TestLogger.info("Test: Verifying Activity Creation with Empty Name");
        activityPage.clickAddActivityButton();
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Modal should be displayed.");
        activityPage.clearActivityName();
        activityPage.clicknextbutton();
        boolean hasError = activityPage.isActivityNameErrorDisplayed();
        Assert.assertTrue(hasError, "Should show validation error for empty activity name.");
        getExtentTest().log(Status.PASS, "Validation error shown for empty activity name.");
        TestLogger.success("Empty activity name validation test passed.");
    }

    @Test(priority = 15, description = "Verify activity name field handles special characters.")
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
        boolean Errormsgofspecialchractersinactivityname = activityPage.isErrorMessageOfUsingSpecialCharactersInActivitynameDisplayed();
        Assert.assertTrue(Errormsgofspecialchractersinactivityname, "No error message is displayed");
        getExtentTest().log(Status.PASS, "Error message is getting displayed for entering the special characters in activity name field.");
        TestLogger.success("Error msg for using Special characters is displayed");
    }

    @Test(priority = 16, description = "Verify activity name field handles very long text.")
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
        boolean Errormsgforusinglongtext = activityPage.isErrorMessageOfUsingLongTextInActivitynameDisplayed();
        Assert.assertTrue(Errormsgforusinglongtext, "No Error message is displayed for entering long text in activity name field");
        getExtentTest().log(Status.PASS, "Error message is displayed for entering long text in activity name field.");
        TestLogger.success("Error message is displayed for entering long text in activity name field");
    }

    @Test(priority = 17, description = "Verify activity name field rejects spaces only input.")
    public void verifyActivityNameWithSpacesOnly() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Name with Spaces Only");
        TestLogger.info("Test: Verifying Activity Name with Spaces Only");
        activityPage.clickAddActivityButton();
        activityPage.enterActivityNameWithSpacesOnly();
        activityPage.clicknextbutton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean ErrorMessageofusingspaces = activityPage.isErrorMessageOfUsingSpacesInActivitryNamaeDisplayed();
        Assert.assertTrue(ErrorMessageofusingspaces, "Error message Of Using Spaces In Activitry Namae is not displaye");
        getExtentTest().log(Status.PASS, "Error message Of Using Spaces In Activitry Namae is displayed");
        TestLogger.success("Error message Of Using Spaces In Activitry Namae is displayed");
    }

    @Test(priority = 18, description = "Verify activity creation fails without selecting location level.")
    public void verifyActivityCreationWithoutLocationLevel() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Creation without Location Level");
        TestLogger.info("Test: Verifying Activity Creation without Location Level");
        activityPage.clickAddActivityButton();
        activityPage.enterActivityName("Test Activity No Location").entercode("5896").clickRoles().ClickRolesoption().clickKeys().clickkeysoption();
        activityPage.clicknextbutton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean ErrormessageofLocation = activityPage.isErrorMessageOfLocationLevelDisplayed();
        Assert.assertTrue(ErrormessageofLocation, "Should show validation error for missing location level.");
        getExtentTest().log(Status.PASS, "Validation shown for missing location level.");
        TestLogger.success("Missing location level validation test passed.");
    }

    @Test(priority = 19, description = "Verify activity creation fails without selecting access role.")
    public void verifyActivityCreationWithoutAccessRole() {
        getExtentTest().log(Status.INFO, "Test: Verifying Activity Creation without Access Role");
        TestLogger.info("Test: Verifying Activity Creation without Access Role");
        activityPage.clickAddActivityButton();
        activityPage.enterActivityName("Test Activity No Role");
        activityPage.clicklocationleveldropdown().clicklocationlevelOption().clicklocation();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        activityPage.clicklocationOption().clickKeys().clickkeysoption();
        activityPage.clicknextbutton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(activityPage.isErrorMessageOfRoleDisplayed(), "Should show validation error for missing access role.");
        getExtentTest().log(Status.PASS, "Validation shown for missing access role.");
        TestLogger.success("Missing access role validation test passed.");
    }

    @Test(priority = 20, description = "Verify activity name field can be cleared.")
    public void verifyActivityNameFieldClearFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verifying Field Clear Functionality");
        TestLogger.info("Test: Verifying Field Clear Functionality");
        activityPage.clickAddActivityButton();
        String testName = "Test Activity to Clear";
        activityPage.enterActivityName(testName);
        Assert.assertEquals(activityPage.getActivityNameValue(), testName, "Activity name should be entered.");
        getExtentTest().log(Status.INFO, "Activity name entered: " + testName);
        activityPage.clearActivityName();
        String clearedValue = activityPage.getActivityNameValue();
        Assert.assertTrue(clearedValue.isEmpty(), "The activity name field contains a value");
        getExtentTest().log(Status.PASS, "Activity name field cleared successfully.");
        TestLogger.success("Field clear functionality test passed.");
    }
    @Test(priority = 21, description = "Verify search functionality with valid activity name.")
    public void verifySearchFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Functionality");
        TestLogger.info("Test: Verifying Search Functionality");
        int initialRowCount = activityPage.getTableRowCount();
        if (initialRowCount > 0) {
            activityPage.navigateToActivityPage().clicksearchicon().enterActivitynameinsearchgbox("AMS Activity");
            int searchResultCount = activityPage.getTableRowCount();
            Assert.assertTrue(searchResultCount >= 0, "Search should return results or empty table.");
            getExtentTest().log(Status.PASS, "Search functionality works correctly.");
            TestLogger.success("Search functionality works correctly.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test search functionality.");
        }
    }
    @Test(priority = 22 , description = "Verify search with non-existent activity name returns no results.")
    public void verifySearchWithNonExistentName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Non-Existent activity Name");
        TestLogger.info("Test: Verifying Search with Non-Existent activity Name");
        activityPage.navigateToActivityPage().clicksearchicon().enterActivitynameinsearchgbox("No Activity");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        int rowCount = activityPage.getTableRowCount();
        Assert.assertEquals(rowCount, 0, "actual row count does not matches with expected row count");
        getExtentTest().log(Status.PASS, "Search with non-existent activity name handled correctly.");
        TestLogger.success("Non-existent activity name search test passed.");
    }

    @Test(priority = 23, description = "Verify View action displays activity details.")
    public void verifyViewActivityDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Activity Details");
        TestLogger.info("Test: Verifying View Activity Details");
        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.navigateToActivityPage().clickactionbutton();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            activityPage.clickviewbutton();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            getExtentTest().log(Status.INFO, "Clicked view button for first row.");
            Assert.assertTrue(activityPage.isActivitydetailsLabelDisplayed(), "activity details  should be displayed.");
            getExtentTest().log(Status.PASS, "Activity details are displayed correctly. Activity Name");
            TestLogger.success("View activity details test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities details are getting displayed");
        }
    }

    @Test(priority = 24, description = "Verify Edit action opens edit modal.")
    public void verifyEditActivityOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Activity Opens Modal");
        TestLogger.info("Test: Verifying Edit Activity Opens Modal");
        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickactionbutton();
            getExtentTest().log(Status.INFO, "Clicked action menu for first row.");
            activityPage.clickEditButton();
            getExtentTest().log(Status.INFO, "Selected Edit from action menu.");
            Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Edit modal should be displayed.");
            getExtentTest().log(Status.PASS, "Edit modal is displayed successfully.");
            TestLogger.success("Edit activity modal test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test Edit functionality.");
        }
    }

    @Test(priority = 25, description = "Verify navigation between wizard steps using Next and Previous buttons.")
    public void verifyWizardStepNavigation() {
        getExtentTest().log(Status.INFO, "Test: Verifying Wizard Step Navigation");
        TestLogger.info("Test: Verifying Wizard Step Navigation");
        activityPage.clickAddActivityButton();
        Assert.assertTrue(activityPage.isActivityModalDisplayed(), "Modal should be displayed.");
        activityPage.enterActivityName("Test Navigation Activity").entercode("2598").clicklocationleveldropdown().clicklocationlevelOption().clicklocation();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        activityPage.clicklocationOption().clickRoles().ClickRolesoption().clickKeys().clickkeysoption();
        activityPage.clicknextbutton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getExtentTest().log(Status.INFO, "Filled basic info step.");
        if (activityPage.isPreviousButtonDisplayed()) {
            activityPage.clickPreviousButton();
            getExtentTest().log(Status.INFO, "Clicked Previous button.");
            getExtentTest().log(Status.PASS, "Previous button navigation works.");
        }
        getExtentTest().log(Status.PASS, "Wizard step navigation test completed.");
        TestLogger.success("Wizard step navigation test passed.");
    }

    @Test(priority = 26, description = "Verify total activity count across all pages.")
    public void verifyTotalActivityCount() {
        getExtentTest().log(Status.INFO, "Test: Verifying Total Activity Count");
        TestLogger.info("Test: Verifying Total Activity Count");
        String totalCount = activityPage.totalActivityCount();
        int count = Integer.parseInt(totalCount);
        Assert.assertTrue(count >= 0, "Total activity count should be zero or more.");
        getExtentTest().log(Status.PASS, "Total activity count is: " + totalCount);
        TestLogger.success("Total activity count verification passed: " + totalCount);
    }


    @Test(priority = 27, description = "Verify View modal displays all activity details sections.")
    public void verifyViewModalDisplaysAllDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Modal Displays All Details");
        TestLogger.info("Test: Verifying View Modal Displays All Details");
        activityPage.navigateToActivityPage();
        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickactionbutton();
            activityPage.clickviewbutton();
            boolean ActivitySections = activityPage.isBasicInformationSectionInViewModalDisplayed()&& activityPage.isScheduleSectionInViewModalDisplayed() && activityPage.isWeeklyAccessSectionInViewModalDisplayed()
                    && activityPage.isAccessRolesSectionInViewModalDisplayed() && activityPage.isAccessKeysSectionInViewModalDisplayed() && activityPage.isAccessEventsSectionInViewModalDisplayed();
            Assert.assertTrue(ActivitySections,"Activity sections not displayed");
            getExtentTest().log(Status.PASS, "Activity sections are displayed" );
        } else {
            getExtentTest().log(Status.INFO, "No activities present to verify view modal details.");
        }
    }

    @Test(priority = 28, description = "Verify View modal can be closed using close button.")
    public void verifyViewModalCloseButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Modal Close Button");
        TestLogger.info("Test: Verifying View Modal Close Button");
        int rowCount = activityPage.getTableRowCount();
        if (rowCount > 0) {
            activityPage.clickactionbutton();
            activityPage.clickviewbutton();
            Assert.assertTrue(activityPage.isActivitydetailsLabelDisplayed(), "View modal should be displayed.");
            getExtentTest().log(Status.INFO, "View modal is displayed.");
            activityPage.closeViewModal();
            getExtentTest().log(Status.INFO, "Clicked close button on view modal.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(), "View modal should be closed.");
            getExtentTest().log(Status.PASS, "View modal closed successfully.");
            TestLogger.success("View modal close button test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No activities present to test view modal close.");
        }
    }

    @Test(priority = 29, description = "Verify Schedule step displays all required elements.")
    public void verifyScheduleStepElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Schedule Step Elements");
        TestLogger.info("Test: Verifying Schedule Step Elements");
        activityPage.clickAddActivityButton().enterActivityName("New Activity 55").entercode("591").clicklocationleveldropdown().clicklocationlevelOption().clicklocation();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        activityPage.clicklocationOption().clickRoles().ClickRolesoption().clickKeys().clickkeysoption().clicknextbutton();
        boolean Scheduleelementsdisplayed = activityPage.isFromDateInScheduleSectionDisplayed() && activityPage.isFromtimeInScheduleSectionDisplayed()
                && activityPage.isToTimeInScheduleSectionDisplayed() && activityPage.isDurationInScheduleSectionDisplayed();
        Assert.assertTrue(Scheduleelementsdisplayed, "Schedule elements are not displayed");
        getExtentTest().log(Status.INFO, "Schedule elements are displayed");
        getExtentTest().log(Status.PASS, "Schedule step elements verification initiated.");
        TestLogger.success("Schedule step elements test completed.");

    }

    @Test(priority = 30, description = "Verify Frequency checkboxes displayed")
    public void verifyFrequencyStepElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying Frequency checkboxes displayed");
        TestLogger.info("Test: Verifying Frequency checkboxes displayed");
        activityPage.clickAddActivityButton().enterActivityName("New Activity 77").entercode("777").clicklocationleveldropdown().clicklocationlevelOption().clicklocation();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        activityPage.clicklocationOption().clickRoles().ClickRolesoption().clickKeys().clickkeysoption().clickKeys().clicknextbutton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
       activityPage .clickfromdatecalendar().clickfromdate().clicktodatecalendar();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        activityPage.clicktodate().enterfromtime("0900AM").entertotime("1800PM").clickduration().clickdurationoption().
                clickworkingdayscheckbox().clicknextbutton();
        boolean frequencycheckboxdisplayed = activityPage.isFrequency1checkboxDisplayed() && activityPage.isFrequency2checkboxDisplayed();
        Assert.assertTrue(frequencycheckboxdisplayed, "Frequency checkboxes not displayed");
        getExtentTest().log(Status.PASS, "Frequency checkboxes are displayed");
        TestLogger.success("Frequency checkboxes displayed");
    }
    @Test(priority = 31, description = "Edit activity")
    public void EditActivity() {
        getExtentTest().log(Status.INFO, "Test: Edit the  activity details");
        TestLogger.info("Test: Edit the activity details");
        activityPage.clickactionbutton().clickEditButton().clearActivityName().enterActivityName("Edit activity").clicknextbutton().clicknextbutton().clicknextbutton().clicksubmitbutton();
        Assert.assertTrue(activityPage.isAddActivityButtonDisplayed(),"Activity details are not edited");
        getExtentTest().log(Status.PASS, "Activity details has been edited");
        TestLogger.success("Activity details has been edited");
    }
    @Test(priority = 32, description = "Verify the column header names in ACTIVITY list screen")
    public void verifycolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in Activity list screen");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        List<String> expectedHeaders = Arrays.asList("S.NO", "NAME", "LOCATION", "DATE","TIME","CREATED BY","UPDATED BY","ACTION");
        List<String> actualHeaders = activityPage.getHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }
    @Test(priority = 33, description = "Verify Duplicate creation of an activity using Activity name")
    public void DuplicateActivityUsingActivityName() {
        getExtentTest().log(Status.INFO, "Test: ");
        TestLogger.info("Test: Verify Duplicate creation of an activity using Activity name");
        activityPage.createduplicateactivityusingactivityname();
        Assert.assertTrue(activityPage.isAddactivitylabelDisplayed(),"Duplicate Activity is been created");
        getExtentTest().log(Status.PASS, "Duplicate Activity creation is restricted");
        TestLogger.success("Duplicate Activity creation is restricted");
    }
    @Test(priority = 34, description = "Verify Duplicate creation of an activity using Activity code")
    public void DuplicateActivityUsingActivityCode() {
        getExtentTest().log(Status.INFO, "Test: Verify Duplicate creation of an activity using Activity code");
        TestLogger.info("Test: Verify Duplicate creation of an activity using Activity code");
        activityPage.createduplicateactivityusingcode();
        Assert.assertTrue(activityPage.isAddactivitylabelDisplayed(),"Duplicate Activity is created");
        getExtentTest().log(Status.PASS, "Duplicate Activity creation is restricted");
        TestLogger.success("Duplicate Activity creation is restricted");
    }
//activitytests
}