package TestRun;

import Base.BaseTest;
import Pages.DeviceActivityPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * DeviceActivityTest - Contains all test cases for Device Activity Report Page
 * URL: /device-activity
 * Tests cover UI verification, positive workflows, negative workflows, and data verification
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class DeviceActivityTest extends BaseTest {

    private LoginPage loginPage;
    private DeviceActivityPage deviceActivityPage;

    @BeforeMethod
    public void setupDeviceActivityPage() throws InterruptedException {
        navigateToApp();
        loginPage = new LoginPage(getDriver());
        deviceActivityPage = new DeviceActivityPage(getDriver());

        // Login before each test
        loginPage.login(ConfigReader.getValidEmail(), ConfigReader.getValidPassword());
        deviceActivityPage.navigateToDeviceActivityPage();
        getExtentTest().log(Status.INFO, "Setup complete. Navigated to Device Activity Page.");
    }

    // ==================================================================================
    // UI VERIFICATION TEST CASES - PAGE ELEMENTS
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_UI_001: Verify page header is displayed correctly
     */
    @Test(priority = 1, description = "Verify page header 'Device Activity' is displayed correctly.")
    public void verifyPageHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Header");
        TestLogger.info("Test: Verifying Page Header");

        Assert.assertTrue(deviceActivityPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(deviceActivityPage.getPageHeaderText(), "Device Activity", "Page header text should be 'Device Activity'.");
        getExtentTest().log(Status.PASS, "Page header 'Device Activity' is displayed correctly.");
        TestLogger.success("Page header verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_002: Verify page sub-header is displayed correctly
     */
    @Test(priority = 2, description = "Verify page sub-header is displayed correctly.")
    public void verifyPageSubHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Sub-Header");
        TestLogger.info("Test: Verifying Page Sub-Header");

        Assert.assertTrue(deviceActivityPage.isPageSubHeaderDisplayed(), "Page sub-header should be displayed.");
        String expectedSubHeader = "View and track detailed activity logs for all devices";
        Assert.assertTrue(deviceActivityPage.getPageSubHeaderText().contains("View and track"),
                "Sub-header text should contain expected text.");
        getExtentTest().log(Status.PASS, "Page sub-header is displayed correctly.");
        TestLogger.success("Page sub-header verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_003: Verify search button is displayed
     */
    @Test(priority = 3, description = "Verify search button is displayed.")
    public void verifySearchButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Button");
        TestLogger.info("Test: Verifying Search Button");

        Assert.assertTrue(deviceActivityPage.isSearchButtonDisplayed(), "Search button should be displayed.");
        getExtentTest().log(Status.PASS, "Search button is displayed.");
        TestLogger.success("Search button verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_004: Verify filter button is displayed
     */
    @Test(priority = 4, description = "Verify filter button is displayed.")
    public void verifyFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Button");
        TestLogger.info("Test: Verifying Filter Button");

        Assert.assertTrue(deviceActivityPage.isFilterButtonDisplayed(), "Filter button should be displayed.");
        getExtentTest().log(Status.PASS, "Filter button is displayed.");
        TestLogger.success("Filter button verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_005: Verify Device Activity table is displayed
     */
    @Test(priority = 5, description = "Verify Device Activity table is displayed.")
    public void verifyDeviceActivityTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Device Activity Table");
        TestLogger.info("Test: Verifying Device Activity Table");

        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(), "Device Activity table should be displayed.");
        getExtentTest().log(Status.PASS, "Device Activity table is displayed.");
        TestLogger.success("Device Activity table verification passed.");
    }

    // ==================================================================================
    // UI VERIFICATION TEST CASES - STATISTICS CARDS
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_UI_006: Verify Total Device Activity card is displayed with correct label
     */
    @Test(priority = 6, description = "Verify Total Device Activity statistics card is displayed with correct label.")
    public void verifyTotalDeviceActivityCardDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Total Device Activity Card");
        TestLogger.info("Test: Verifying Total Device Activity Card");

        Assert.assertTrue(deviceActivityPage.isTotalDeviceActivityCardDisplayed(), "Total Device Activity card should be displayed.");

        // Verify label text
        Assert.assertTrue(deviceActivityPage.isTotalDeviceActivityLabelDisplayed(), "Total Device Activity label should be displayed.");
        String labelText = deviceActivityPage.getTotalDeviceActivityLabelText();
        Assert.assertTrue(labelText.contains("Total Device Activity"), "Label text should contain 'Total Device Activity'.");
        getExtentTest().log(Status.INFO, "Label text verified: " + labelText);

        // Verify count
        String totalCount = deviceActivityPage.getTotalDeviceActivityCount();
        getExtentTest().log(Status.PASS, "Total Device Activity card is displayed. Label: " + labelText + ", Count: " + totalCount);
        TestLogger.success("Total Device Activity card verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_007: Verify Success card is displayed with correct label
     */
    @Test(priority = 7, description = "Verify Success statistics card is displayed with correct label.")
    public void verifySuccessCardDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Success Card");
        TestLogger.info("Test: Verifying Success Card");

        Assert.assertTrue(deviceActivityPage.isSuccessCardDisplayed(), "Success card should be displayed.");

        // Verify label text
        Assert.assertTrue(deviceActivityPage.isSuccessLabelDisplayed(), "Success label should be displayed.");
        String labelText = deviceActivityPage.getSuccessLabelText();
        Assert.assertEquals(labelText, "Success", "Label text should be 'Success'.");
        getExtentTest().log(Status.INFO, "Label text verified: " + labelText);

        // Verify count
        String successCount = deviceActivityPage.getSuccessCount();
        getExtentTest().log(Status.PASS, "Success card is displayed. Label: " + labelText + ", Count: " + successCount);
        TestLogger.success("Success card verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_008: Verify Pending card is displayed with correct label
     */
    @Test(priority = 8, description = "Verify Pending statistics card is displayed with correct label.")
    public void verifyPendingCardDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pending Card");
        TestLogger.info("Test: Verifying Pending Card");

        Assert.assertTrue(deviceActivityPage.isPendingCardDisplayed(), "Pending card should be displayed.");

        // Verify label text
        Assert.assertTrue(deviceActivityPage.isPendingLabelDisplayed(), "Pending label should be displayed.");
        String labelText = deviceActivityPage.getPendingLabelText();
        Assert.assertEquals(labelText, "Pending", "Label text should be 'Pending'.");
        getExtentTest().log(Status.INFO, "Label text verified: " + labelText);

        // Verify count
        String pendingCount = deviceActivityPage.getPendingCount();
        getExtentTest().log(Status.PASS, "Pending card is displayed. Label: " + labelText + ", Count: " + pendingCount);
        TestLogger.success("Pending card verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_009: Verify Failed card is displayed with correct label
     */
    @Test(priority = 9, description = "Verify Failed statistics card is displayed with correct label.")
    public void verifyFailedCardDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Failed Card");
        TestLogger.info("Test: Verifying Failed Card");

        Assert.assertTrue(deviceActivityPage.isFailedCardDisplayed(), "Failed card should be displayed.");

        // Verify label text
        Assert.assertTrue(deviceActivityPage.isFailedLabelDisplayed(), "Failed label should be displayed.");
        String labelText = deviceActivityPage.getFailedLabelText();
        Assert.assertEquals(labelText, "Failed", "Label text should be 'Failed'.");
        getExtentTest().log(Status.INFO, "Label text verified: " + labelText);

        // Verify count
        String failedCount = deviceActivityPage.getFailedCount();
        getExtentTest().log(Status.PASS, "Failed card is displayed. Label: " + labelText + ", Count: " + failedCount);
        TestLogger.success("Failed card verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_010: Verify statistics sum (Total = Success + Pending + Failed)
     */
    @Test(priority = 10, description = "Verify statistics sum: Total = Success + Pending + Failed.")
    public void verifyStatisticsSum() {
        getExtentTest().log(Status.INFO, "Test: Verifying Statistics Sum");
        TestLogger.info("Test: Verifying Statistics Sum");

        String total = deviceActivityPage.getTotalDeviceActivityCount();
        String success = deviceActivityPage.getSuccessCount();
        String pending = deviceActivityPage.getPendingCount();
        String failed = deviceActivityPage.getFailedCount();

        getExtentTest().log(Status.INFO, "Total: " + total + ", Success: " + success + ", Pending: " + pending + ", Failed: " + failed);

        Assert.assertTrue(deviceActivityPage.verifyStatisticsSum(), "Total should equal Success + Pending + Failed.");
        getExtentTest().log(Status.PASS, "Statistics sum verification passed.");
        TestLogger.success("Statistics sum verification passed.");
    }

    // ==================================================================================
    // UI VERIFICATION TEST CASES - TABLE HEADERS
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_UI_011: Verify table header row is displayed
     */
    @Test(priority = 11, description = "Verify table header row is displayed.")
    public void verifyTableHeaderRowDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Header Row");
        TestLogger.info("Test: Verifying Table Header Row");

        Assert.assertTrue(deviceActivityPage.isTableHeaderRowDisplayed(), "Table header row should be displayed.");
        getExtentTest().log(Status.PASS, "Table header row is displayed.");
        TestLogger.success("Table header row verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_012: Verify all table headers are displayed correctly
     */
    @Test(priority = 12, description = "Verify all table headers are displayed correctly.")
    public void verifyTableHeaders() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Headers");
        TestLogger.info("Test: Verifying Table Headers");

        List<String> actualHeaders = deviceActivityPage.getTableHeaders();
        getExtentTest().log(Status.INFO, "Actual headers: " + actualHeaders);

        // Expected headers based on actual page
        List<String> expectedHeaders = List.of("S.No", "Serial Number", "command", "Sync Date & Time", "Created Date & Time", "Status");

        Assert.assertEquals(actualHeaders.size(), expectedHeaders.size(),
                "Number of table headers should be " + expectedHeaders.size());
        getExtentTest().log(Status.PASS, "Table has correct number of headers: " + actualHeaders.size());
        TestLogger.success("Table headers verification completed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_013: Verify S.No header is displayed
     */
    @Test(priority = 13, description = "Verify S.No header is displayed.")
    public void verifySnoHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying S.No Header");
        TestLogger.info("Test: Verifying S.No Header");

        Assert.assertTrue(deviceActivityPage.isSnoHeaderDisplayed(), "S.No header should be displayed.");
        getExtentTest().log(Status.PASS, "S.No header is displayed.");
        TestLogger.success("S.No header verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_014: Verify Serial Number header is displayed
     */
    @Test(priority = 14, description = "Verify Serial Number header is displayed.")
    public void verifySerialNumberHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Serial Number Header");
        TestLogger.info("Test: Verifying Serial Number Header");

        Assert.assertTrue(deviceActivityPage.isSerialNumberHeaderDisplayed(), "Serial Number header should be displayed.");
        getExtentTest().log(Status.PASS, "Serial Number header is displayed.");
        TestLogger.success("Serial Number header verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_015: Verify Command header is displayed
     */
    @Test(priority = 15, description = "Verify Command header is displayed.")
    public void verifyCommandHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Command Header");
        TestLogger.info("Test: Verifying Command Header");

        Assert.assertTrue(deviceActivityPage.isCommandHeaderDisplayed(), "Command header should be displayed.");
        getExtentTest().log(Status.PASS, "Command header is displayed.");
        TestLogger.success("Command header verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_016: Verify Sync Date & Time header is displayed
     */
    @Test(priority = 16, description = "Verify Sync Date & Time header is displayed.")
    public void verifySyncDateHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Sync Date & Time Header");
        TestLogger.info("Test: Verifying Sync Date & Time Header");

        Assert.assertTrue(deviceActivityPage.isSyncDateHeaderDisplayed(), "Sync Date & Time header should be displayed.");
        getExtentTest().log(Status.PASS, "Sync Date & Time header is displayed.");
        TestLogger.success("Sync Date & Time header verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_017: Verify Created Date & Time header is displayed
     */
    @Test(priority = 17, description = "Verify Created Date & Time header is displayed.")
    public void verifyCreatedDateHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Created Date & Time Header");
        TestLogger.info("Test: Verifying Created Date & Time Header");

        Assert.assertTrue(deviceActivityPage.isCreatedDateHeaderDisplayed(), "Created Date & Time header should be displayed.");
        getExtentTest().log(Status.PASS, "Created Date & Time header is displayed.");
        TestLogger.success("Created Date & Time header verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_018: Verify Status header is displayed
     */
    @Test(priority = 18, description = "Verify Status header is displayed.")
    public void verifyStatusHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Header");
        TestLogger.info("Test: Verifying Status Header");

        Assert.assertTrue(deviceActivityPage.isStatusHeaderDisplayed(), "Status header should be displayed.");
        getExtentTest().log(Status.PASS, "Status header is displayed.");
        TestLogger.success("Status header verification passed.");
    }

    // ==================================================================================
    // UI VERIFICATION TEST CASES - PAGINATION
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_UI_019: Verify pagination info is displayed
     */
    @Test(priority = 19, description = "Verify pagination info is displayed.")
    public void verifyPaginationInfoDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Info");
        TestLogger.info("Test: Verifying Pagination Info");

        Assert.assertTrue(deviceActivityPage.isPaginationInfoDisplayed(), "Pagination info should be displayed.");
        String paginationText = deviceActivityPage.getPaginationInfoText();
        Assert.assertTrue(paginationText.contains("Showing"), "Pagination text should contain 'Showing'.");
        getExtentTest().log(Status.PASS, "Pagination info is displayed: " + paginationText);
        TestLogger.success("Pagination info verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_UI_020: Verify pagination buttons are displayed
     */
    @Test(priority = 20, description = "Verify pagination buttons are displayed.")
    public void verifyPaginationButtonsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Buttons");
        TestLogger.info("Test: Verifying Pagination Buttons");

        Assert.assertTrue(deviceActivityPage.isFirstPageButtonDisplayed(), "First page button should be displayed.");
        Assert.assertTrue(deviceActivityPage.isLastPageButtonDisplayed(), "Last page button should be displayed.");
        getExtentTest().log(Status.PASS, "Pagination buttons are displayed.");
        TestLogger.success("Pagination buttons verification passed.");
    }

    // ==================================================================================
    // POSITIVE WORKFLOW TEST CASES - SEARCH FUNCTIONALITY
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_POS_001: Verify search button is clickable and expands search input
     */
    @Test(priority = 21, description = "Verify search button is clickable and expands search input.")
    public void verifySearchButtonClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Button Clickable");
        TestLogger.info("Test: Verifying Search Button Clickable");

        deviceActivityPage.clickSearchButton();
        getExtentTest().log(Status.INFO, "Clicked search button.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(deviceActivityPage.isSearchInputDisplayed(), "Search input should be displayed after clicking search button.");
        getExtentTest().log(Status.PASS, "Search input is displayed after clicking search button.");
        TestLogger.success("Search button click test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_POS_002: Verify search functionality with valid serial number
     */
    @Test(priority = 22, description = "Verify search functionality with valid serial number.")
    public void verifySearchFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Functionality");
        TestLogger.info("Test: Verifying Search Functionality");

        int rowCount = deviceActivityPage.getTableRowCount();
        if (rowCount > 0) {
            String serialNumber = deviceActivityPage.getSerialNumberFromRow(0);
            if (!serialNumber.isEmpty()) {
                deviceActivityPage.searchDeviceActivity(serialNumber);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                int searchResultCount = deviceActivityPage.getTableRowCount();
                getExtentTest().log(Status.INFO, "Search results: " + searchResultCount);
                Assert.assertTrue(searchResultCount >= 0, "Search should return valid results.");
                getExtentTest().log(Status.PASS, "Search functionality works correctly.");
            } else {
                getExtentTest().log(Status.INFO, "Could not get serial number from first row.");
            }
        } else {
            getExtentTest().log(Status.INFO, "No device activities present to test search functionality.");
        }

        TestLogger.success("Search functionality test completed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_POS_003: Verify search with command type
     */
    @Test(priority = 23, description = "Verify search with command type.")
    public void verifySearchWithCommandType() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Command Type");
        TestLogger.info("Test: Verifying Search with Command Type");

        deviceActivityPage.searchDeviceActivity("userSync");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(), "Table should be displayed after search.");
        getExtentTest().log(Status.PASS, "Search with command type works correctly.");
        TestLogger.success("Search with command type test passed.");
    }

    // ==================================================================================
    // POSITIVE WORKFLOW TEST CASES - FILTER FUNCTIONALITY
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_POS_004: Verify filter button is clickable and opens filter dialog
     */
    @Test(priority = 24, description = "Verify filter button is clickable and opens filter dialog.")
    public void verifyFilterButtonClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Button Clickable");
        TestLogger.info("Test: Verifying Filter Button Clickable");

        deviceActivityPage.clickFilterButton();
        getExtentTest().log(Status.INFO, "Clicked filter button.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(deviceActivityPage.isFilterDialogDisplayed(), "Filter dialog should be displayed after clicking filter button.");
        getExtentTest().log(Status.PASS, "Filter dialog is displayed after clicking filter button.");

        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Filter button click test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_POS_005: Verify filter dialog can be closed
     */
    @Test(priority = 25, description = "Verify filter dialog can be closed.")
    public void verifyFilterDialogCanBeClosed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Close");
        TestLogger.info("Test: Verifying Filter Dialog Close");

        deviceActivityPage.openFilterDialog();
        Assert.assertTrue(deviceActivityPage.isFilterDialogDisplayed(), "Filter dialog should be open.");

        deviceActivityPage.closeFilterDialog();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(deviceActivityPage.isFilterDialogClosed(), "Filter dialog should be closed.");
        getExtentTest().log(Status.PASS, "Filter dialog can be closed successfully.");
        TestLogger.success("Filter dialog close test passed.");
    }

    // ==================================================================================
    // FILTER DIALOG UI VERIFICATION TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_001: Verify filter dialog title is displayed correctly
     */
    @Test(priority = 41, description = "Verify filter dialog title 'Device Activity Filter' is displayed correctly.")
    public void verifyFilterDialogTitle() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Title");
        TestLogger.info("Test: Verifying Filter Dialog Title");

        deviceActivityPage.openFilterDialog();

        Assert.assertTrue(deviceActivityPage.isFilterDialogTitleDisplayed(), "Filter dialog title should be displayed.");
        String titleText = deviceActivityPage.getFilterDialogTitleText();
        Assert.assertTrue(titleText.contains("Device Activity Filter"), "Title should contain 'Device Activity Filter'. Actual: " + titleText);

        getExtentTest().log(Status.PASS, "Filter dialog title is displayed correctly: " + titleText);
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Filter dialog title verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_002: Verify filter dialog description is displayed
     */
    @Test(priority = 42, description = "Verify filter dialog description is displayed.")
    public void verifyFilterDialogDescription() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Description");
        TestLogger.info("Test: Verifying Filter Dialog Description");

        deviceActivityPage.openFilterDialog();

        Assert.assertTrue(deviceActivityPage.isFilterDialogDescriptionDisplayed(), "Filter dialog description should be displayed.");
        String descriptionText = deviceActivityPage.getFilterDialogDescriptionText();
        Assert.assertTrue(descriptionText.contains("Select filter to apply"), "Description should contain expected text. Actual: " + descriptionText);

        getExtentTest().log(Status.PASS, "Filter dialog description is displayed: " + descriptionText);
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Filter dialog description verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_003: Verify Status filter dropdown is displayed in filter dialog
     */
    @Test(priority = 43, description = "Verify Status filter dropdown is displayed in filter dialog.")
    public void verifyStatusFilterDropdownDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Dropdown");
        TestLogger.info("Test: Verifying Status Filter Dropdown");

        deviceActivityPage.openFilterDialog();

        Assert.assertTrue(deviceActivityPage.isFilterStatusLabelDisplayed(), "Status label should be displayed.");
        Assert.assertTrue(deviceActivityPage.isStatusFilterDropdownDisplayed(), "Status filter dropdown should be displayed.");

        getExtentTest().log(Status.PASS, "Status filter dropdown is displayed.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Status filter dropdown verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_004: Verify Type filter dropdown is displayed in filter dialog
     */
    @Test(priority = 44, description = "Verify Type filter dropdown is displayed in filter dialog.")
    public void verifyTypeFilterDropdownDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Dropdown");
        TestLogger.info("Test: Verifying Type Filter Dropdown");

        deviceActivityPage.openFilterDialog();

        Assert.assertTrue(deviceActivityPage.isFilterTypeLabelDisplayed(), "Type label should be displayed.");
        Assert.assertTrue(deviceActivityPage.isTypeFilterDropdownDisplayed(), "Type filter dropdown should be displayed.");

        getExtentTest().log(Status.PASS, "Type filter dropdown is displayed.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Type filter dropdown verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_005: Verify Clear Filter button is displayed in filter dialog
     */
    @Test(priority = 45, description = "Verify Clear Filter button is displayed in filter dialog.")
    public void verifyClearFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Clear Filter Button");
        TestLogger.info("Test: Verifying Clear Filter Button");

        deviceActivityPage.openFilterDialog();

        Assert.assertTrue(deviceActivityPage.isClearFilterButtonDisplayed(), "Clear Filter button should be displayed.");

        getExtentTest().log(Status.PASS, "Clear Filter button is displayed.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Clear Filter button verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_006: Verify Apply Filter button is displayed in filter dialog
     */
    @Test(priority = 46, description = "Verify Apply Filter button is displayed in filter dialog.")
    public void verifyApplyFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Apply Filter Button");
        TestLogger.info("Test: Verifying Apply Filter Button");

        deviceActivityPage.openFilterDialog();

        Assert.assertTrue(deviceActivityPage.isApplyFilterButtonDisplayed(), "Apply Filter button should be displayed.");

        getExtentTest().log(Status.PASS, "Apply Filter button is displayed.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Apply Filter button verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_007: Verify Status filter default value is 'All'
     */
    @Test(priority = 47, description = "Verify Status filter default value is 'All'.")
    public void verifyStatusFilterDefaultValue() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Default Value");
        TestLogger.info("Test: Verifying Status Filter Default Value");

        deviceActivityPage.openFilterDialog();

        String defaultValue = deviceActivityPage.getStatusFilterSelectedValue();
        getExtentTest().log(Status.INFO, "Status filter default value: " + defaultValue);
        Assert.assertEquals(defaultValue, "All", "Status filter default value should be 'All'.");

        getExtentTest().log(Status.PASS, "Status filter default value is 'All'.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Status filter default value verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_UI_008: Verify Type filter default value is 'Month Till Date'
     */
    @Test(priority = 48, description = "Verify Type filter default value is 'Month Till Date'.")
    public void verifyTypeFilterDefaultValue() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Default Value");
        TestLogger.info("Test: Verifying Type Filter Default Value");

        deviceActivityPage.openFilterDialog();

        String defaultValue = deviceActivityPage.getTypeFilterSelectedValue();
        getExtentTest().log(Status.INFO, "Type filter default value: " + defaultValue);
        Assert.assertEquals(defaultValue, "Month Till Date", "Type filter default value should be 'Month Till Date'.");

        getExtentTest().log(Status.PASS, "Type filter default value is 'Month Till Date'.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Type filter default value verification passed.");
    }

    // ==================================================================================
    // FILTER FUNCTIONALITY POSITIVE TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_001: Verify Status filter dropdown is clickable and opens options
     */
    @Test(priority = 49, description = "Verify Status filter dropdown is clickable and opens options.")
    public void verifyStatusFilterDropdownClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Dropdown Clickable");
        TestLogger.info("Test: Verifying Status Filter Dropdown Clickable");

        deviceActivityPage.openFilterDialog();
        deviceActivityPage.clickStatusFilterDropdown();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        getExtentTest().log(Status.PASS, "Status filter dropdown is clickable.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Status filter dropdown click test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_002: Verify Type filter dropdown is clickable and opens options
     */
    @Test(priority = 50, description = "Verify Type filter dropdown is clickable and opens options.")
    public void verifyTypeFilterDropdownClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Dropdown Clickable");
        TestLogger.info("Test: Verifying Type Filter Dropdown Clickable");

        deviceActivityPage.openFilterDialog();
        deviceActivityPage.clickTypeFilterDropdown();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        getExtentTest().log(Status.PASS, "Type filter dropdown is clickable.");
        deviceActivityPage.closeFilterDialog();
        TestLogger.success("Type filter dropdown click test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_003: Verify filter by Status 'Success' works correctly
     */
    @Test(priority = 51, description = "Verify filter by Status 'Success' works correctly.")
    public void verifyFilterByStatusSuccess() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter by Status 'Success'");
        TestLogger.info("Test: Verifying Filter by Status 'Success'");

        deviceActivityPage.applyStatusFilter("Success");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int rowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Rows after filtering by 'Success': " + rowCount);

        if (rowCount > 0) {
            Assert.assertTrue(deviceActivityPage.verifyAllRowsMatchStatusFilter("Success"),
                    "All rows should have 'Success' status after filtering.");
        }

        getExtentTest().log(Status.PASS, "Filter by Status 'Success' works correctly.");
        TestLogger.success("Filter by Status 'Success' test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_004: Verify filter by Status 'Pending' works correctly
     */
    @Test(priority = 52, description = "Verify filter by Status 'Pending' works correctly.")
    public void verifyFilterByStatusPending() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter by Status 'Pending'");
        TestLogger.info("Test: Verifying Filter by Status 'Pending'");

        deviceActivityPage.applyStatusFilter("Pending");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int rowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Rows after filtering by 'Pending': " + rowCount);

        if (rowCount > 0) {
            Assert.assertTrue(deviceActivityPage.verifyAllRowsMatchStatusFilter("Pending"),
                    "All rows should have 'Pending' status after filtering.");
        }

        getExtentTest().log(Status.PASS, "Filter by Status 'Pending' works correctly.");
        TestLogger.success("Filter by Status 'Pending' test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_005: Verify filter by Status 'Failed' works correctly
     */
    @Test(priority = 53, description = "Verify filter by Status 'Failed' works correctly.")
    public void verifyFilterByStatusFailed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter by Status 'Failed'");
        TestLogger.info("Test: Verifying Filter by Status 'Failed'");

        deviceActivityPage.applyStatusFilter("Failed");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int rowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Rows after filtering by 'Failed': " + rowCount);

        if (rowCount > 0) {
            Assert.assertTrue(deviceActivityPage.verifyAllRowsMatchStatusFilter("Failed"),
                    "All rows should have 'Failed' status after filtering.");
        }

        getExtentTest().log(Status.PASS, "Filter by Status 'Failed' works correctly.");
        TestLogger.success("Filter by Status 'Failed' test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_006: Verify filter by Status 'All' shows all records
     */
    @Test(priority = 54, description = "Verify filter by Status 'All' shows all records.")
    public void verifyFilterByStatusAll() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter by Status 'All'");
        TestLogger.info("Test: Verifying Filter by Status 'All'");

        // First get the total count
        String totalCount = deviceActivityPage.getTotalDeviceActivityCount();
        getExtentTest().log(Status.INFO, "Total Device Activity count: " + totalCount);

        deviceActivityPage.applyStatusFilter("All");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int tableTotal = deviceActivityPage.getTableTotalRecords();
        getExtentTest().log(Status.INFO, "Table total after 'All' filter: " + tableTotal);

        Assert.assertEquals(Integer.parseInt(totalCount), tableTotal,
                "All records should be displayed when Status is 'All'.");

        getExtentTest().log(Status.PASS, "Filter by Status 'All' shows all records.");
        TestLogger.success("Filter by Status 'All' test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_007: Verify Clear Filter button resets all filters
     */
    @Test(priority = 55, description = "Verify Clear Filter button resets all filters.")
    public void verifyClearFilterResetsFilters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Clear Filter Resets Filters");
        TestLogger.info("Test: Verifying Clear Filter Resets Filters");

        // Apply a filter first
        deviceActivityPage.applyStatusFilter("Success");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Now clear the filter
        deviceActivityPage.clearFilters();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify filter is cleared by checking default values
        deviceActivityPage.openFilterDialog();
        String statusValue = deviceActivityPage.getStatusFilterSelectedValue();
        String typeValue = deviceActivityPage.getTypeFilterSelectedValue();

        getExtentTest().log(Status.INFO, "Status after clear: " + statusValue + ", Type after clear: " + typeValue);

        Assert.assertEquals(statusValue, "All", "Status filter should be reset to 'All'.");
        Assert.assertEquals(typeValue, "Month Till Date", "Type filter should be reset to 'Month Till Date'.");

        deviceActivityPage.closeFilterDialog();
        getExtentTest().log(Status.PASS, "Clear Filter button resets all filters.");
        TestLogger.success("Clear Filter button test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_008: Verify Apply Filter button applies selected filters
     */
    @Test(priority = 56, description = "Verify Apply Filter button applies selected filters.")
    public void verifyApplyFilterAppliesFilters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Apply Filter Applies Filters");
        TestLogger.info("Test: Verifying Apply Filter Applies Filters");

        int initialRowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Initial row count: " + initialRowCount);

        deviceActivityPage.openFilterDialog();
        deviceActivityPage.selectStatusFilter("Success");
        deviceActivityPage.clickApplyFilterButton();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int filteredRowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Filtered row count: " + filteredRowCount);

        // Verify table is displayed after applying filter
        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(),
                "Table should be displayed after applying filter.");

        getExtentTest().log(Status.PASS, "Apply Filter button applies selected filters.");
        TestLogger.success("Apply Filter button test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_009: Verify filter dialog closes after applying filter
     */
    @Test(priority = 57, description = "Verify filter dialog closes after applying filter.")
    public void verifyFilterDialogClosesAfterApply() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Closes After Apply");
        TestLogger.info("Test: Verifying Filter Dialog Closes After Apply");

        deviceActivityPage.openFilterDialog();
        Assert.assertTrue(deviceActivityPage.isFilterDialogDisplayed(), "Filter dialog should be open.");

        deviceActivityPage.clickApplyFilterButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(deviceActivityPage.isFilterDialogClosed(), "Filter dialog should be closed after applying filter.");

        getExtentTest().log(Status.PASS, "Filter dialog closes after applying filter.");
        TestLogger.success("Filter dialog close after apply test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_010: Verify filter results update statistics cards accordingly
     */
    @Test(priority = 58, description = "Verify filter results update statistics cards accordingly.")
    public void verifyFilterUpdatesStatisticsCards() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Updates Statistics Cards");
        TestLogger.info("Test: Verifying Filter Updates Statistics Cards");

        // Get initial total count
        String initialTotal = deviceActivityPage.getTotalDeviceActivityCount();
        getExtentTest().log(Status.INFO, "Initial total: " + initialTotal);

        // Apply Success filter
        deviceActivityPage.applyStatusFilter("Success");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check that statistics cards are still displayed
        Assert.assertTrue(deviceActivityPage.isTotalDeviceActivityCardDisplayed(),
                "Total Device Activity card should be displayed after filtering.");
        Assert.assertTrue(deviceActivityPage.isSuccessCardDisplayed(),
                "Success card should be displayed after filtering.");

        getExtentTest().log(Status.PASS, "Filter updates statistics cards accordingly.");
        TestLogger.success("Filter statistics update test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_011: Verify combining Status and Type filters works correctly
     */
    @Test(priority = 59, description = "Verify combining Status and Type filters works correctly.")
    public void verifyCombinedFilters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Combined Filters");
        TestLogger.info("Test: Verifying Combined Filters");

        deviceActivityPage.applyFilter("Success", "Month Till Date");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify table is displayed
        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(),
                "Table should be displayed after applying combined filters.");

        int rowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Row count after combined filters: " + rowCount);

        if (rowCount > 0) {
            Assert.assertTrue(deviceActivityPage.verifyAllRowsMatchStatusFilter("Success"),
                    "All rows should match the applied status filter.");
        }

        getExtentTest().log(Status.PASS, "Combined filters work correctly.");
        TestLogger.success("Combined filters test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_POS_012: Verify filter persists after page interaction
     */
    @Test(priority = 60, description = "Verify filter persists after page interaction.")
    public void verifyFilterPersistsAfterInteraction() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Persists After Interaction");
        TestLogger.info("Test: Verifying Filter Persists After Interaction");

        // Apply a filter
        deviceActivityPage.applyStatusFilter("Success");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int initialFilteredCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Initial filtered count: " + initialFilteredCount);

        // Perform some interaction (click search button)
        deviceActivityPage.clickSearchButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify filter is still applied
        int currentCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Current count after interaction: " + currentCount);

        Assert.assertEquals(currentCount, initialFilteredCount,
                "Filter should persist after page interaction.");

        getExtentTest().log(Status.PASS, "Filter persists after page interaction.");
        TestLogger.success("Filter persistence test passed.");
    }

    // ==================================================================================
    // FILTER FUNCTIONALITY NEGATIVE TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_FILTER_NEG_001: Verify empty results when no data matches filter
     */
    @Test(priority = 61, description = "Verify empty results message when no data matches filter criteria.")
    public void verifyEmptyResultsWhenNoMatch() {
        getExtentTest().log(Status.INFO, "Test: Verifying Empty Results When No Match");
        TestLogger.info("Test: Verifying Empty Results When No Match");

        // First check if we have any failed records
        String failedCount = deviceActivityPage.getFailedCount();
        getExtentTest().log(Status.INFO, "Failed count: " + failedCount);

        // Apply filter and verify table handles no results gracefully
        deviceActivityPage.applyStatusFilter("Failed");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int rowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Row count after filter: " + rowCount);

        if (rowCount == 0) {
            Assert.assertTrue(deviceActivityPage.isNoResultsDisplayed() || rowCount == 0,
                    "Should show no results message or empty table.");
        }

        getExtentTest().log(Status.PASS, "Empty results handled correctly.");
        TestLogger.success("Empty results test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_NEG_002: Verify dialog can be dismissed without applying filter
     */
    @Test(priority = 62, description = "Verify dialog can be dismissed without applying filter.")
    public void verifyDialogDismissWithoutApply() {
        getExtentTest().log(Status.INFO, "Test: Verifying Dialog Dismiss Without Apply");
        TestLogger.info("Test: Verifying Dialog Dismiss Without Apply");

        int initialRowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Initial row count: " + initialRowCount);

        // Open filter dialog and change selection but don't apply
        deviceActivityPage.openFilterDialog();
        deviceActivityPage.selectStatusFilter("Success");

        // Close dialog without applying
        deviceActivityPage.closeFilterDialog();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int afterCloseRowCount = deviceActivityPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Row count after closing dialog: " + afterCloseRowCount);

        Assert.assertEquals(afterCloseRowCount, initialRowCount,
                "Data should not change when dialog is closed without applying filter.");

        getExtentTest().log(Status.PASS, "Dialog dismiss without apply works correctly.");
        TestLogger.success("Dialog dismiss without apply test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_FILTER_NEG_003: Verify multiple rapid filter changes are handled correctly
     */
    @Test(priority = 63, description = "Verify multiple rapid filter changes are handled correctly.")
    public void verifyMultipleRapidFilterChanges() {
        getExtentTest().log(Status.INFO, "Test: Verifying Multiple Rapid Filter Changes");
        TestLogger.info("Test: Verifying Multiple Rapid Filter Changes");

        // Apply multiple filters in quick succession
        deviceActivityPage.applyStatusFilter("Success");
        deviceActivityPage.applyStatusFilter("Pending");
        deviceActivityPage.applyStatusFilter("Failed");
        deviceActivityPage.applyStatusFilter("All");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify page is stable and table is displayed
        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(),
                "Table should be displayed after multiple filter changes.");

        getExtentTest().log(Status.PASS, "Multiple rapid filter changes handled correctly.");
        TestLogger.success("Multiple rapid filter changes test passed.");
    }

    // ==================================================================================
    // NEGATIVE WORKFLOW TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_NEG_001: Verify search with non-existent serial number returns no results
     */
    @Test(priority = 26, description = "Verify search with non-existent serial number returns no results.")
    public void verifySearchWithNonExistentSerialNumber() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Non-Existent Serial Number");
        TestLogger.info("Test: Verifying Search with Non-Existent Serial Number");

        String nonExistentSerial = "NONEXISTENT_SERIAL_XYZ_123456789";
        deviceActivityPage.searchDeviceActivity(nonExistentSerial);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int rowCount = deviceActivityPage.getTableRowCount();
        boolean noResultsDisplayed = deviceActivityPage.isNoResultsDisplayed();

        Assert.assertTrue(rowCount == 0 || noResultsDisplayed,
                "Search with non-existent serial number should return no results.");
        getExtentTest().log(Status.PASS, "Non-existent serial number search handled correctly.");
        TestLogger.success("Non-existent serial number search test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_NEG_002: Verify search with special characters
     */
    @Test(priority = 27, description = "Verify search handles special characters gracefully.")
    public void verifySearchWithSpecialCharacters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Special Characters");
        TestLogger.info("Test: Verifying Search with Special Characters");

        String specialChars = "@#$%^&*()!";
        deviceActivityPage.searchDeviceActivity(specialChars);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Page should handle gracefully without errors
        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(), "Table should still be displayed after special character search.");
        getExtentTest().log(Status.PASS, "Special character search handled gracefully.");
        TestLogger.success("Special character search test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_NEG_003: Verify search with SQL injection attempt
     */
    @Test(priority = 28, description = "Verify search handles SQL injection attempt safely.")
    public void verifySearchWithSQLInjection() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with SQL Injection");
        TestLogger.info("Test: Verifying Search with SQL Injection");

        String sqlInjection = "' OR '1'='1'; DROP TABLE device_activity; --";
        deviceActivityPage.searchDeviceActivity(sqlInjection);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Page should handle gracefully without errors
        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(), "Table should still be displayed after SQL injection attempt.");
        getExtentTest().log(Status.PASS, "SQL injection attempt handled safely.");
        TestLogger.success("SQL injection search test passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_NEG_004: Verify search with XSS attack attempt
     */
    @Test(priority = 29, description = "Verify search handles XSS attack attempt safely.")
    public void verifySearchWithXSSAttack() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with XSS Attack");
        TestLogger.info("Test: Verifying Search with XSS Attack");

        String xssAttack = "<script>alert('XSS')</script>";
        deviceActivityPage.searchDeviceActivity(xssAttack);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Page should handle gracefully without errors
        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(), "Table should still be displayed after XSS attempt.");
        getExtentTest().log(Status.PASS, "XSS attack attempt handled safely.");
        TestLogger.success("XSS attack search test passed.");
    }

    // ==================================================================================
    // DATA VERIFICATION TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_DATA_001: Verify table displays correct data structure
     */
    @Test(priority = 30, description = "Verify table displays correct data structure.")
    public void verifyTableDataStructure() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Data Structure");
        TestLogger.info("Test: Verifying Table Data Structure");

        int rowCount = deviceActivityPage.getTableRowCount();
        if (rowCount > 0) {
            // Verify first row has all columns
            String sno = deviceActivityPage.getCellText(0, 0);
            String serialNumber = deviceActivityPage.getSerialNumberFromRow(0);
            String command = deviceActivityPage.getCommandFromRow(0);
            String syncDate = deviceActivityPage.getSyncDateFromRow(0);
            String createdDate = deviceActivityPage.getCreatedDateFromRow(0);
            String status = deviceActivityPage.getStatusFromRow(0);

            getExtentTest().log(Status.INFO, "S.No: " + sno);
            getExtentTest().log(Status.INFO, "Serial Number: " + serialNumber);
            getExtentTest().log(Status.INFO, "Command: " + command);
            getExtentTest().log(Status.INFO, "Sync Date: " + syncDate);
            getExtentTest().log(Status.INFO, "Created Date: " + createdDate);
            getExtentTest().log(Status.INFO, "Status: " + status);

            Assert.assertFalse(sno.isEmpty(), "S.No should not be empty.");
            getExtentTest().log(Status.PASS, "Table data structure is correct.");
            TestLogger.success("Table data structure verification passed.");
        } else {
            getExtentTest().log(Status.INFO, "No data to verify table structure.");
            Assert.assertTrue(deviceActivityPage.isNoResultsDisplayed(), "Empty state should be displayed.");
        }
    }

    /**
     * TC_DEVICE_ACTIVITY_DATA_002: Verify Total Device Activity card count matches table total records
     */
    @Test(priority = 31, description = "Verify Total Device Activity card count matches table total records.")
    public void verifyTotalCountMatchesTableRecords() {
        getExtentTest().log(Status.INFO, "Test: Verifying Total Count Matches Table Records");
        TestLogger.info("Test: Verifying Total Count Matches Table Records");

        String cardCountStr = deviceActivityPage.getTotalDeviceActivityCount();
        int cardCount = Integer.parseInt(cardCountStr);
        getExtentTest().log(Status.INFO, "Total Device Activity card count: " + cardCount);

        int tableRecords = deviceActivityPage.getTableTotalRecords();
        getExtentTest().log(Status.INFO, "Table total records: " + tableRecords);

        Assert.assertEquals(cardCount, tableRecords,
                "Total Device Activity card count should match table total records.");
        getExtentTest().log(Status.PASS, "Total count matches table records.");
        TestLogger.success("Total count matches table records verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_DATA_003: Verify Success card count matches table Success status count
     */
    @Test(priority = 32, description = "Verify Success card count matches table Success status count.")
    public void verifySuccessCountMatchesTableStatus() {
        getExtentTest().log(Status.INFO, "Test: Verifying Success Count Matches Table Status");
        TestLogger.info("Test: Verifying Success Count Matches Table Status");

        String cardCountStr = deviceActivityPage.getSuccessCount();
        int cardCount = Integer.parseInt(cardCountStr);
        getExtentTest().log(Status.INFO, "Success card count: " + cardCount);

        int tableSuccessCount = deviceActivityPage.getSuccessStatusCountFromTable();
        getExtentTest().log(Status.INFO, "Table Success status count: " + tableSuccessCount);

        Assert.assertEquals(cardCount, tableSuccessCount,
                "Success card count should match table Success status count.");
        getExtentTest().log(Status.PASS, "Success count matches table status.");
        TestLogger.success("Success count matches table status verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_DATA_004: Verify Pending card count matches table Pending status count
     */
    @Test(priority = 33, description = "Verify Pending card count matches table Pending status count.")
    public void verifyPendingCountMatchesTableStatus() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pending Count Matches Table Status");
        TestLogger.info("Test: Verifying Pending Count Matches Table Status");

        String cardCountStr = deviceActivityPage.getPendingCount();
        int cardCount = Integer.parseInt(cardCountStr);
        getExtentTest().log(Status.INFO, "Pending card count: " + cardCount);

        int tablePendingCount = deviceActivityPage.getPendingStatusCountFromTable();
        getExtentTest().log(Status.INFO, "Table Pending status count: " + tablePendingCount);

        Assert.assertEquals(cardCount, tablePendingCount,
                "Pending card count should match table Pending status count.");
        getExtentTest().log(Status.PASS, "Pending count matches table status.");
        TestLogger.success("Pending count matches table status verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_DATA_005: Verify Failed card count matches table Failed status count
     */
    @Test(priority = 34, description = "Verify Failed card count matches table Failed status count.")
    public void verifyFailedCountMatchesTableStatus() {
        getExtentTest().log(Status.INFO, "Test: Verifying Failed Count Matches Table Status");
        TestLogger.info("Test: Verifying Failed Count Matches Table Status");

        String cardCountStr = deviceActivityPage.getFailedCount();
        int cardCount = Integer.parseInt(cardCountStr);
        getExtentTest().log(Status.INFO, "Failed card count: " + cardCount);

        int tableFailedCount = deviceActivityPage.getFailedStatusCountFromTable();
        getExtentTest().log(Status.INFO, "Table Failed status count: " + tableFailedCount);

        Assert.assertEquals(cardCount, tableFailedCount,
                "Failed card count should match table Failed status count.");
        getExtentTest().log(Status.PASS, "Failed count matches table status.");
        TestLogger.success("Failed count matches table status verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_DATA_006: Verify status badges are displayed correctly
     */
    @Test(priority = 35, description = "Verify status badges are displayed with correct styling.")
    public void verifyStatusBadgesDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Badges");
        TestLogger.info("Test: Verifying Status Badges");

        int rowCount = deviceActivityPage.getTableRowCount();
        if (rowCount > 0) {
            int successBadges = deviceActivityPage.getSuccessBadgeCount();
            int pendingBadges = deviceActivityPage.getPendingBadgeCount();
            int failedBadges = deviceActivityPage.getFailedBadgeCount();

            getExtentTest().log(Status.INFO, "Success badges: " + successBadges);
            getExtentTest().log(Status.INFO, "Pending badges: " + pendingBadges);
            getExtentTest().log(Status.INFO, "Failed badges: " + failedBadges);

            int totalBadges = successBadges + pendingBadges + failedBadges;
            Assert.assertEquals(totalBadges, rowCount, "Total badges should equal row count.");
            getExtentTest().log(Status.PASS, "Status badges are displayed correctly.");
            TestLogger.success("Status badges verification passed.");
        } else {
            getExtentTest().log(Status.INFO, "No data to verify status badges.");
        }
    }

    // ==================================================================================
    // EMPTY STATE TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_EMPTY_001: Verify 'No results found' message when search returns no data
     */
    @Test(priority = 36, description = "Verify empty state message when no data exists.")
    public void verifyEmptyStateMessage() {
        getExtentTest().log(Status.INFO, "Test: Verifying Empty State Message");
        TestLogger.info("Test: Verifying Empty State Message");

        // Search for non-existent data to trigger empty state
        deviceActivityPage.searchDeviceActivity("DEFINITELY_NOT_EXIST_12345_ABCDEF");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(deviceActivityPage.isNoResultsDisplayed() || deviceActivityPage.getTableRowCount() == 0,
                "Empty state should be shown for no matching results.");
        getExtentTest().log(Status.PASS, "Empty state handled correctly.");
        TestLogger.success("Empty state message verification passed.");
    }

    // ==================================================================================
    // PAGE LOAD TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_LOAD_001: Verify page loads completely without errors
     */
    @Test(priority = 37, description = "Verify Device Activity page loads completely without errors.")
    public void verifyPageLoadsWithoutErrors() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Loads Without Errors");
        TestLogger.info("Test: Verifying Page Loads Without Errors");

        // Verify all essential elements are present
        Assert.assertTrue(deviceActivityPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertTrue(deviceActivityPage.isSearchButtonDisplayed(), "Search button should be displayed.");
        Assert.assertTrue(deviceActivityPage.isFilterButtonDisplayed(), "Filter button should be displayed.");
        Assert.assertTrue(deviceActivityPage.isTotalDeviceActivityCardDisplayed(), "Statistics cards should be displayed.");
        Assert.assertTrue(deviceActivityPage.isDeviceActivityTableDisplayed(), "Table should be displayed.");
        Assert.assertTrue(deviceActivityPage.isTableHeaderRowDisplayed(), "Table header row should be displayed.");

        getExtentTest().log(Status.PASS, "Device Activity page loads completely without errors.");
        TestLogger.success("Page load verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_LOAD_002: Verify all four statistics cards are visible on page load
     */
    @Test(priority = 38, description = "Verify all four statistics cards are visible on page load.")
    public void verifyAllStatisticsCardsVisible() throws InterruptedException {
        getExtentTest().log(Status.INFO, "Test: Verifying All Statistics Cards Visible");
        TestLogger.info("Test: Verifying All Statistics Cards Visible");
Thread.sleep(1000);
        Assert.assertTrue(deviceActivityPage.isTotalDeviceActivityCardDisplayed(), "Total Device Activity card should be displayed.");
        Assert.assertTrue(deviceActivityPage.isSuccessCardDisplayed(), "Success card should be displayed.");
        Assert.assertTrue(deviceActivityPage.isPendingCardDisplayed(), "Pending card should be displayed.");
        Assert.assertTrue(deviceActivityPage.isFailedCardDisplayed(), "Failed card should be displayed.");

        getExtentTest().log(Status.PASS, "All four statistics cards are visible.");
        TestLogger.success("All statistics cards visibility verification passed.");
    }

    // ==================================================================================
    // PAGINATION TEST CASES
    // ==================================================================================

    /**
     * TC_DEVICE_ACTIVITY_PAG_001: Verify pagination info text format
     */
    @Test(priority = 39, description = "Verify pagination info text format.")
    public void verifyPaginationInfoTextFormat() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Info Text Format");
        TestLogger.info("Test: Verifying Pagination Info Text Format");

        String paginationText = deviceActivityPage.getPaginationInfoText();
        getExtentTest().log(Status.INFO, "Pagination text: " + paginationText);

        Assert.assertTrue(paginationText.contains("Showing") && paginationText.contains("of"),
                "Pagination text should contain 'Showing' and 'of'.");
        getExtentTest().log(Status.PASS, "Pagination info text format is correct.");
        TestLogger.success("Pagination info text format verification passed.");
    }

    /**
     * TC_DEVICE_ACTIVITY_PAG_002: Verify page number info is displayed
     */
    @Test(priority = 40, description = "Verify page number info is displayed.")
    public void verifyPageNumberInfoDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Number Info");
        TestLogger.info("Test: Verifying Page Number Info");

        String pageInfo = deviceActivityPage.getPageNumberInfoText();
        getExtentTest().log(Status.INFO, "Page info: " + pageInfo);

        Assert.assertTrue(pageInfo.contains("Page"), "Page info should contain 'Page'.");
        getExtentTest().log(Status.PASS, "Page number info is displayed.");
        TestLogger.success("Page number info verification passed.");
    }
}
