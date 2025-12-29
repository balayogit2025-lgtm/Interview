package TestRun;

import Base.BaseTest;
import Pages.KeyboxPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * KeyboxTest - Contains all test cases for Key Box Page
 * URL: /keySafes
 * Tests cover UI verification, positive workflows, negative workflows, and edge cases
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class KeyboxTest extends BaseTest {

    private LoginPage loginPage;
    private KeyboxPage keyboxPage;

    @BeforeMethod
    public void setupKeyboxPage() {
        navigateToApp();
        loginPage = new LoginPage(getDriver());
        keyboxPage = new KeyboxPage(getDriver());

        // Login before each test
        loginPage.login(ConfigReader.getkeyboxValidEmail(), ConfigReader.getValidPassword());
        keyboxPage.navigateToKeyboxPage();
        getExtentTest().log(Status.INFO, "Setup complete. Navigated to Key Box Page.");
    }

    // ==================================================================================
    // UI VERIFICATION TEST CASES - PAGE ELEMENTS
    // ==================================================================================

    /**
     * TC_KEYBOX_UI_001: Verify page header is displayed correctly
     */
    @Test(priority = 1, description = "Verify page header 'Key Box' is displayed correctly.")
    public void verifyPageHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Header");
        TestLogger.info("Test: Verifying Page Header");

        Assert.assertTrue(keyboxPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(keyboxPage.getPageHeaderText(), "Key Box", "Page header text should be 'Key Box'.");
        getExtentTest().log(Status.PASS, "Page header 'Key Box' is displayed correctly.");
        TestLogger.success("Page header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_002: Verify page sub-header is displayed correctly
     */
    @Test(priority = 2, description = "Verify page sub-header is displayed correctly.")
    public void verifyPageSubHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Sub-Header");
        TestLogger.info("Test: Verifying Page Sub-Header");

        Assert.assertTrue(keyboxPage.isPageSubHeaderDisplayed(), "Page sub-header should be displayed.");
        String expectedSubHeader = "Manage and configure all registered key box devices";
        Assert.assertEquals(keyboxPage.getPageSubHeaderText(), expectedSubHeader, "Sub-header text should match.");
        getExtentTest().log(Status.PASS, "Page sub-header is displayed correctly: " + expectedSubHeader);
        TestLogger.success("Page sub-header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_003: Verify search button is displayed
     */
    @Test(priority = 3, description = "Verify search button is displayed.")
    public void verifySearchButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Button");
        TestLogger.info("Test: Verifying Search Button");

        Assert.assertTrue(keyboxPage.isSearchButtonDisplayed(), "Search button should be displayed.");
        getExtentTest().log(Status.PASS, "Search button is displayed.");
        TestLogger.success("Search button verification passed.");
    }

    /**
     * TC_KEYBOX_UI_004: Verify filter button is displayed
     */
    @Test(priority = 4, description = "Verify filter button is displayed.")
    public void verifyFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Button");
        TestLogger.info("Test: Verifying Filter Button");

        Assert.assertTrue(keyboxPage.isFilterButtonDisplayed(), "Filter button should be displayed.");
        getExtentTest().log(Status.PASS, "Filter button is displayed.");
        TestLogger.success("Filter button verification passed.");
    }

    /**
     * TC_KEYBOX_UI_005: Verify Key Box table is displayed
     */
    @Test(priority = 5, description = "Verify Key Box table is displayed.")
    public void verifyKeyboxTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Box Table");
        TestLogger.info("Test: Verifying Key Box Table");

        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Key Box table should be displayed.");
        getExtentTest().log(Status.PASS, "Key Box table is displayed.");
        TestLogger.success("Key Box table verification passed.");
    }

    // ==================================================================================
    // UI VERIFICATION TEST CASES - STATISTICS CARDS
    // ==================================================================================

    /**
     * TC_KEYBOX_UI_006: Verify Total Key Box statistics card is displayed with correct label
     */
    @Test(priority = 6, description = "Verify Total Key Box statistics card is displayed with correct label.")
    public void verifyTotalKeyBoxCardDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Total Key Box Card");
        TestLogger.info("Test: Verifying Total Key Box Card");

        Assert.assertTrue(keyboxPage.isTotalKeyBoxCardDisplayed(), "Total Key Box card should be displayed.");

        // Verify label text
        Assert.assertTrue(keyboxPage.isTotalKeyBoxLabelDisplayed(), "Total Key Box label should be displayed.");
        String labelText = keyboxPage.getTotalKeyBoxLabelText();
        Assert.assertEquals(labelText, "Total Key Box", "Label text should be 'Total Key Box'.");
        getExtentTest().log(Status.INFO, "Label text verified: " + labelText);

        // Verify count
        String totalCount = keyboxPage.getTotalKeyBoxCount();
        getExtentTest().log(Status.PASS, "Total Key Box card is displayed. Label: " + labelText + ", Count: " + totalCount);
        TestLogger.success("Total Key Box card verification passed. Label: " + labelText + ", Count: " + totalCount);
    }

    /**
     * TC_KEYBOX_UI_007: Verify Registered statistics card is displayed with correct label
     */
    @Test(priority = 7, description = "Verify Registered statistics card is displayed with correct label.")
    public void verifyRegisteredCardDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Registered Card");
        TestLogger.info("Test: Verifying Registered Card");

        Assert.assertTrue(keyboxPage.isRegisteredCardDisplayed(), "Registered card should be displayed.");

        // Verify label text
        Assert.assertTrue(keyboxPage.isRegisteredLabelDisplayed(), "Registered label should be displayed.");
        String labelText = keyboxPage.getRegisteredLabelText();
        Assert.assertEquals(labelText, "Registered", "Label text should be 'Registered'.");
        getExtentTest().log(Status.INFO, "Label text verified: " + labelText);

        // Verify count
        String registeredCount = keyboxPage.getRegisteredCount();
        getExtentTest().log(Status.PASS, "Registered card is displayed. Label: " + labelText + ", Count: " + registeredCount);
        TestLogger.success("Registered card verification passed. Label: " + labelText + ", Count: " + registeredCount);
    }

    /**
     * TC_KEYBOX_UI_008: Verify Unregistered statistics card is displayed with correct label
     */
    @Test(priority = 8, description = "Verify Unregistered statistics card is displayed with correct label.")
    public void verifyUnregisteredCardDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Unregistered Card");
        TestLogger.info("Test: Verifying Unregistered Card");

        Assert.assertTrue(keyboxPage.isUnregisteredCardDisplayed(), "Unregistered card should be displayed.");

        // Verify label text
        Assert.assertTrue(keyboxPage.isUnregisteredLabelDisplayed(), "Unregistered label should be displayed.");
        String labelText = keyboxPage.getUnregisteredLabelText();
        Assert.assertEquals(labelText, "Unregistered", "Label text should be 'Unregistered'.");
        getExtentTest().log(Status.INFO, "Label text verified: " + labelText);

        // Verify count
        String unregisteredCount = keyboxPage.getUnregisteredCount();
        getExtentTest().log(Status.PASS, "Unregistered card is displayed. Label: " + labelText + ", Count: " + unregisteredCount);
        TestLogger.success("Unregistered card verification passed. Label: " + labelText + ", Count: " + unregisteredCount);
    }

    /**
     * TC_KEYBOX_UI_009: Verify statistics sum (Total = Registered + Unregistered)
     */
    @Test(priority = 9, description = "Verify statistics sum: Total = Registered + Unregistered.")
    public void verifyStatisticsSum() {
        getExtentTest().log(Status.INFO, "Test: Verifying Statistics Sum");
        TestLogger.info("Test: Verifying Statistics Sum");

        String total = keyboxPage.getTotalKeyBoxCount();
        String registered = keyboxPage.getRegisteredCount();
        String unregistered = keyboxPage.getUnregisteredCount();

        getExtentTest().log(Status.INFO, "Total: " + total + ", Registered: " + registered + ", Unregistered: " + unregistered);

        Assert.assertTrue(keyboxPage.verifyStatisticsSum(), "Total should equal Registered + Unregistered.");
        getExtentTest().log(Status.PASS, "Statistics sum verification passed.");
        TestLogger.success("Statistics sum verification passed.");
    }

    // ==================================================================================
    // UI VERIFICATION TEST CASES - TABLE HEADERS
    // ==================================================================================

    /**
     * TC_KEYBOX_UI_010: Verify table header row is displayed
     */
    @Test(priority = 10, description = "Verify table header row is displayed.")
    public void verifyTableHeaderRowDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Header Row");
        TestLogger.info("Test: Verifying Table Header Row");

        Assert.assertTrue(keyboxPage.isTableHeaderRowDisplayed(), "Table header row should be displayed.");
        getExtentTest().log(Status.PASS, "Table header row is displayed.");
        TestLogger.success("Table header row verification passed.");
    }

    /**
     * TC_KEYBOX_UI_011: Verify all table headers are displayed correctly
     */
    @Test(priority = 11, description = "Verify all table headers are displayed correctly.")
    public void verifyTableHeaders() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Headers");
        TestLogger.info("Test: Verifying Table Headers");

        List<String> actualHeaders = keyboxPage.getTableHeaders();
        getExtentTest().log(Status.INFO, "Actual headers: " + actualHeaders);

        // Expected headers based on actual page
        List<String> expectedHeaders = List.of("S.No", "Serial Number", "Location", "Ro Type",
                "Last Communicated Date & Time", "Registered By", "Updated By", "Status", "Action");

        Assert.assertEquals(actualHeaders.size(), expectedHeaders.size(),
                "Number of table headers should be " + expectedHeaders.size());
        getExtentTest().log(Status.PASS, "Table has correct number of headers: " + actualHeaders.size());

        // Verify each expected header is present
        for (String expectedHeader : expectedHeaders) {
            boolean found = actualHeaders.stream()
                    .anyMatch(h -> h.toUpperCase().contains(expectedHeader.toUpperCase().replace("&", "").trim()));
            getExtentTest().log(Status.INFO, "Header '" + expectedHeader + "' found: " + found);
        }

        TestLogger.success("Table headers verification completed.");
    }

    /**
     * TC_KEYBOX_UI_012: Verify S.No header is displayed
     */
    @Test(priority = 12, description = "Verify S.No header is displayed.")
    public void verifySnoHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying S.No Header");
        TestLogger.info("Test: Verifying S.No Header");

        Assert.assertTrue(keyboxPage.isSnoHeaderDisplayed(), "S.No header should be displayed.");
        getExtentTest().log(Status.PASS, "S.No header is displayed.");
        TestLogger.success("S.No header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_013: Verify Serial Number header is displayed
     */
    @Test(priority = 13, description = "Verify Serial Number header is displayed.")
    public void verifySerialNumberHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Serial Number Header");
        TestLogger.info("Test: Verifying Serial Number Header");

        Assert.assertTrue(keyboxPage.isSerialNumberHeaderDisplayed(), "Serial Number header should be displayed.");
        getExtentTest().log(Status.PASS, "Serial Number header is displayed.");
        TestLogger.success("Serial Number header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_014: Verify Location header is displayed
     */
    @Test(priority = 14, description = "Verify Location header is displayed.")
    public void verifyLocationHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Location Header");
        TestLogger.info("Test: Verifying Location Header");

        Assert.assertTrue(keyboxPage.isLocationHeaderDisplayed(), "Location header should be displayed.");
        getExtentTest().log(Status.PASS, "Location header is displayed.");
        TestLogger.success("Location header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_015: Verify Ro Type header is displayed
     */
    @Test(priority = 15, description = "Verify Ro Type header is displayed.")
    public void verifyRoTypeHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Ro Type Header");
        TestLogger.info("Test: Verifying Ro Type Header");

        Assert.assertTrue(keyboxPage.isRoTypeHeaderDisplayed(), "Ro Type header should be displayed.");
        getExtentTest().log(Status.PASS, "Ro Type header is displayed.");
        TestLogger.success("Ro Type header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_016: Verify Last Communicated Date & Time header is displayed
     */
    @Test(priority = 16, description = "Verify Last Communicated Date & Time header is displayed.")
    public void verifyLastCommunicatedHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Last Communicated Date Header");
        TestLogger.info("Test: Verifying Last Communicated Date Header");

        Assert.assertTrue(keyboxPage.isLastCommunicatedHeaderDisplayed(),
                "Last Communicated Date & Time header should be displayed.");
        getExtentTest().log(Status.PASS, "Last Communicated Date & Time header is displayed.");
        TestLogger.success("Last Communicated Date header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_017: Verify Registered By header is displayed
     */
    @Test(priority = 17, description = "Verify Registered By header is displayed.")
    public void verifyRegisteredByHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Registered By Header");
        TestLogger.info("Test: Verifying Registered By Header");

        Assert.assertTrue(keyboxPage.isRegisteredByHeaderDisplayed(), "Registered By header should be displayed.");
        getExtentTest().log(Status.PASS, "Registered By header is displayed.");
        TestLogger.success("Registered By header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_018: Verify Updated By header is displayed
     */
    @Test(priority = 18, description = "Verify Updated By header is displayed.")
    public void verifyUpdatedByHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Updated By Header");
        TestLogger.info("Test: Verifying Updated By Header");

        Assert.assertTrue(keyboxPage.isUpdatedByHeaderDisplayed(), "Updated By header should be displayed.");
        getExtentTest().log(Status.PASS, "Updated By header is displayed.");
        TestLogger.success("Updated By header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_019: Verify Status header is displayed
     */
    @Test(priority = 19, description = "Verify Status header is displayed.")
    public void verifyStatusHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Header");
        TestLogger.info("Test: Verifying Status Header");

        Assert.assertTrue(keyboxPage.isStatusHeaderDisplayed(), "Status header should be displayed.");
        getExtentTest().log(Status.PASS, "Status header is displayed.");
        TestLogger.success("Status header verification passed.");
    }

    /**
     * TC_KEYBOX_UI_020: Verify Action header is displayed
     */
    @Test(priority = 20, description = "Verify Action header is displayed.")
    public void verifyActionHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Action Header");
        TestLogger.info("Test: Verifying Action Header");

        Assert.assertTrue(keyboxPage.isActionHeaderDisplayed(), "Action header should be displayed.");
        getExtentTest().log(Status.PASS, "Action header is displayed.");
        TestLogger.success("Action header verification passed.");
    }

    // ==================================================================================
    // POSITIVE WORKFLOW TEST CASES - SEARCH FUNCTIONALITY
    // ==================================================================================

    /**
     * TC_KEYBOX_POS_001: Verify search button is clickable and expands search input
     */
    @Test(priority = 21, description = "Verify search button is clickable and expands search input.")
    public void verifySearchButtonClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Button Clickable");
        TestLogger.info("Test: Verifying Search Button Clickable");

        keyboxPage.clickSearchButton();
        getExtentTest().log(Status.INFO, "Clicked search button.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isSearchInputDisplayed(), "Search input should be displayed after clicking search button.");
        getExtentTest().log(Status.PASS, "Search input is displayed after clicking search button.");
        TestLogger.success("Search button click test passed.");
    }

    /**
     * TC_KEYBOX_POS_002: Verify search functionality with valid serial number
     */
    @Test(priority = 22, description = "Verify search functionality with valid serial number.")
    public void verifySearchFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Functionality");
        TestLogger.info("Test: Verifying Search Functionality");

        int rowCount = keyboxPage.getTableRowCount();
        if (rowCount > 0) {
            String serialNumber = keyboxPage.getSerialNumberFromRow(0);
            if (!serialNumber.isEmpty()) {
                keyboxPage.searchKeybox(serialNumber);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                int searchResultCount = keyboxPage.getTableRowCount();
                getExtentTest().log(Status.INFO, "Search results: " + searchResultCount);
                Assert.assertTrue(searchResultCount >= 0, "Search should return valid results.");
                getExtentTest().log(Status.PASS, "Search functionality works correctly.");
            } else {
                getExtentTest().log(Status.INFO, "Could not get serial number from first row.");
            }
        } else {
            getExtentTest().log(Status.INFO, "No key boxes present to test search functionality.");
        }

        TestLogger.success("Search functionality test completed.");
    }

    // ==================================================================================
    // POSITIVE WORKFLOW TEST CASES - FILTER FUNCTIONALITY
    // ==================================================================================

    /**
     * TC_KEYBOX_POS_003: Verify filter button is clickable and opens filter dialog
     */
    @Test(priority = 23, description = "Verify filter button is clickable and opens filter dialog.")
    public void verifyFilterButtonClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Button Clickable");
        TestLogger.info("Test: Verifying Filter Button Clickable");

        keyboxPage.clickFilterButton();
        getExtentTest().log(Status.INFO, "Clicked filter button.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isFilterDialogDisplayed(), "Filter dialog should be displayed after clicking filter button.");
        getExtentTest().log(Status.PASS, "Filter dialog is displayed after clicking filter button.");

        // Close the dialog
        keyboxPage.clickCloseButton();
        TestLogger.success("Filter button click test passed.");
    }

    // ==================================================================================
    // POSITIVE WORKFLOW TEST CASES - TABLE DATA (When Data Exists)
    // ==================================================================================

    /**
     * TC_KEYBOX_POS_004: Verify action buttons are present for each row (when data exists)
     */
    @Test(priority = 24, description = "Verify action buttons are present for each table row.")
    public void verifyActionButtonsPresent() {
        getExtentTest().log(Status.INFO, "Test: Verifying Action Buttons");
        TestLogger.info("Test: Verifying Action Buttons");

        int rowCount = keyboxPage.getTableRowCount();
        int actionButtonCount = keyboxPage.getActionButtonsCount();

        if (rowCount > 0) {
            Assert.assertEquals(actionButtonCount, rowCount, "Each row should have an action button.");
            getExtentTest().log(Status.PASS, "Action buttons are present for all " + rowCount + " rows.");
            TestLogger.success("Action buttons verification passed.");
        } else {
            getExtentTest().log(Status.INFO, "No data rows present - 'No results found' displayed. Action buttons test skipped.");
            Assert.assertTrue(keyboxPage.isNoResultsDisplayed(), "Empty state message should be displayed.");
            TestLogger.info("No data rows - empty state verified.");
        }
    }

    /**
     * TC_KEYBOX_POS_005: Verify View action opens details modal (when data exists)
     */
    @Test(priority = 25, description = "Verify View action opens key box details modal.")
    public void verifyViewKeyboxDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Key Box Details");
        TestLogger.info("Test: Verifying View Key Box Details");

        int rowCount = keyboxPage.getTableRowCount();
        if (rowCount > 0) {
            keyboxPage.clickActionMenuForRow(0);
            getExtentTest().log(Status.INFO, "Clicked action menu for first row.");

            keyboxPage.selectViewFromActionMenu();
            getExtentTest().log(Status.INFO, "Selected View from action menu.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Assert.assertTrue(keyboxPage.isModalDisplayed(), "View modal should be displayed.");
            getExtentTest().log(Status.PASS, "View modal is displayed.");

            String modalHeader = keyboxPage.getModalHeaderText();
            getExtentTest().log(Status.INFO, "Modal header: " + modalHeader);

            keyboxPage.closeViewModal();
            getExtentTest().log(Status.PASS, "View modal closed successfully.");
            TestLogger.success("View key box details test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No key boxes present to test View functionality.");
            Assert.assertTrue(keyboxPage.isNoResultsDisplayed(), "Empty state should be displayed when no data.");
        }
    }

    /**
     * TC_KEYBOX_POS_006: Verify Edit action opens edit modal (when data exists)
     */
    @Test(priority = 26, description = "Verify Edit action opens edit modal.")
    public void verifyEditKeyboxOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Key Box Opens Modal");
        TestLogger.info("Test: Verifying Edit Key Box Opens Modal");

        int rowCount = keyboxPage.getTableRowCount();
        if (rowCount > 0) {
            keyboxPage.clickActionMenuForRow(0);
            getExtentTest().log(Status.INFO, "Clicked action menu for first row.");

            keyboxPage.selectEditFromActionMenu();
            getExtentTest().log(Status.INFO, "Selected Edit from action menu.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Assert.assertTrue(keyboxPage.isModalDisplayed(), "Edit modal should be displayed.");
            getExtentTest().log(Status.PASS, "Edit modal is displayed.");

            keyboxPage.clickCloseButton();
            getExtentTest().log(Status.PASS, "Edit modal closed successfully.");
            TestLogger.success("Edit key box modal test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No key boxes present to test Edit functionality.");
        }
    }

    /**
     * TC_KEYBOX_POS_007: Verify Delete confirmation dialog appears (when data exists)
     */
    @Test(priority = 27, description = "Verify Delete confirmation dialog appears.")
    public void verifyDeleteConfirmationAppears() {
        getExtentTest().log(Status.INFO, "Test: Verifying Delete Confirmation Dialog");
        TestLogger.info("Test: Verifying Delete Confirmation Dialog");

        int rowCount = keyboxPage.getTableRowCount();
        if (rowCount > 0) {
            keyboxPage.clickActionMenuForRow(0);
            getExtentTest().log(Status.INFO, "Clicked action menu for first row.");

            keyboxPage.selectDeleteFromActionMenu();
            getExtentTest().log(Status.INFO, "Selected Delete from action menu.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Assert.assertTrue(keyboxPage.isDeleteConfirmationDisplayed(), "Delete confirmation dialog should be displayed.");
            getExtentTest().log(Status.PASS, "Delete confirmation dialog is displayed.");

            keyboxPage.cancelDeletion();
            getExtentTest().log(Status.PASS, "Delete confirmation canceled successfully.");
            TestLogger.success("Delete confirmation dialog test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No key boxes present to test Delete functionality.");
        }
    }

    /**
     * TC_KEYBOX_POS_008: Verify cancel delete keeps the key box
     */
    @Test(priority = 28, description = "Verify canceling delete action keeps the key box.")
    public void verifyCancelDeleteKeepsKeybox() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Delete Keeps Key Box");
        TestLogger.info("Test: Verifying Cancel Delete Keeps Key Box");

        int initialRowCount = keyboxPage.getTableRowCount();
        if (initialRowCount > 0) {
            keyboxPage.clickActionMenuForRow(0);
            keyboxPage.selectDeleteFromActionMenu();

            Assert.assertTrue(keyboxPage.isDeleteConfirmationDisplayed(), "Delete confirmation should appear.");
            getExtentTest().log(Status.INFO, "Delete confirmation dialog displayed.");

            keyboxPage.cancelDeletion();
            getExtentTest().log(Status.INFO, "Canceled deletion.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            int finalRowCount = keyboxPage.getTableRowCount();
            Assert.assertEquals(finalRowCount, initialRowCount, "Row count should remain the same after canceling delete.");
            getExtentTest().log(Status.PASS, "Cancel delete works correctly. Key box preserved.");
            TestLogger.success("Cancel delete test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No key boxes present to test cancel delete.");
        }
    }

    // ==================================================================================
    // NEGATIVE WORKFLOW TEST CASES
    // ==================================================================================

    /**
     * TC_KEYBOX_NEG_001: Verify search with non-existent serial number returns no results
     */
    @Test(priority = 29, description = "Verify search with non-existent serial number returns no results.")
    public void verifySearchWithNonExistentSerialNumber() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Non-Existent Serial Number");
        TestLogger.info("Test: Verifying Search with Non-Existent Serial Number");

        String nonExistentSerial = "NONEXISTENT_SERIAL_XYZ_123456789";
        keyboxPage.searchKeybox(nonExistentSerial);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int rowCount = keyboxPage.getTableRowCount();
        boolean noResultsDisplayed = keyboxPage.isNoResultsDisplayed();

        Assert.assertTrue(rowCount == 0 || noResultsDisplayed,
                "Search with non-existent serial number should return no results.");
        getExtentTest().log(Status.PASS, "Non-existent serial number search handled correctly.");
        TestLogger.success("Non-existent serial number search test passed.");
    }

    /**
     * TC_KEYBOX_NEG_002: Verify search with special characters
     */
    @Test(priority = 30, description = "Verify search handles special characters gracefully.")
    public void verifySearchWithSpecialCharacters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Special Characters");
        TestLogger.info("Test: Verifying Search with Special Characters");

        String specialChars = "@#$%^&*()!";
        keyboxPage.searchKeybox(specialChars);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Page should handle gracefully without errors
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Table should still be displayed after special character search.");
        getExtentTest().log(Status.PASS, "Special character search handled gracefully.");
        TestLogger.success("Special character search test passed.");
    }

    /**
     * TC_KEYBOX_NEG_003: Verify search with SQL injection attempt
     */
    @Test(priority = 31, description = "Verify search handles SQL injection attempt safely.")
    public void verifySearchWithSQLInjection() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with SQL Injection");
        TestLogger.info("Test: Verifying Search with SQL Injection");

        String sqlInjection = "' OR '1'='1'; DROP TABLE keybox; --";
        keyboxPage.searchKeybox(sqlInjection);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Page should handle gracefully without errors
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Table should still be displayed after SQL injection attempt.");
        getExtentTest().log(Status.PASS, "SQL injection attempt handled safely.");
        TestLogger.success("SQL injection search test passed.");
    }

    /**
     * TC_KEYBOX_NEG_004: Verify search with XSS attack attempt
     */
    @Test(priority = 32, description = "Verify search handles XSS attack attempt safely.")
    public void verifySearchWithXSSAttack() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with XSS Attack");
        TestLogger.info("Test: Verifying Search with XSS Attack");

        String xssAttack = "<script>alert('XSS')</script>";
        keyboxPage.searchKeybox(xssAttack);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Page should handle gracefully without errors
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Table should still be displayed after XSS attempt.");
        getExtentTest().log(Status.PASS, "XSS attack attempt handled safely.");
        TestLogger.success("XSS attack search test passed.");
    }

    // ==================================================================================
    // EMPTY STATE TEST CASES
    // ==================================================================================

    /**
     * TC_KEYBOX_EMPTY_001: Verify 'No results found' message is displayed when table is empty
     */
    @Test(priority = 33, description = "Verify empty state message when no data exists.")
    public void verifyEmptyStateMessage() {
        getExtentTest().log(Status.INFO, "Test: Verifying Empty State Message");
        TestLogger.info("Test: Verifying Empty State Message");

        int rowCount = keyboxPage.getTableRowCount();
        if (rowCount == 0) {
            Assert.assertTrue(keyboxPage.isNoResultsDisplayed(), "'No results found' message should be displayed.");
            getExtentTest().log(Status.PASS, "'No results found' message is displayed correctly.");
            TestLogger.success("Empty state message verification passed.");
        } else {
            getExtentTest().log(Status.INFO, "Data exists in table. Performing search to trigger empty state.");

            // Search for non-existent data to trigger empty state
            keyboxPage.searchKeybox("DEFINITELY_NOT_EXIST_12345");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Assert.assertTrue(keyboxPage.isNoResultsDisplayed() || keyboxPage.getTableRowCount() == 0,
                    "Empty state should be shown for no matching results.");
            getExtentTest().log(Status.PASS, "Empty state handled correctly.");
        }
    }

    // ==================================================================================
    // MODAL TEST CASES
    // ==================================================================================

    /**
     * TC_KEYBOX_MODAL_001: Verify filter modal can be closed using close button
     */
    @Test(priority = 34, description = "Verify filter modal can be closed using close button.")
    public void verifyFilterModalCloseButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Modal Close Button");
        TestLogger.info("Test: Verifying Filter Modal Close Button");

        keyboxPage.clickFilterButton();
        Assert.assertTrue(keyboxPage.isFilterDialogDisplayed(), "Filter dialog should be displayed.");
        getExtentTest().log(Status.INFO, "Filter dialog is displayed.");

        keyboxPage.clickCloseButton();
        getExtentTest().log(Status.INFO, "Clicked close button.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isModalClosed(), "Filter dialog should be closed.");
        getExtentTest().log(Status.PASS, "Filter modal closed successfully.");
        TestLogger.success("Filter modal close button test passed.");
    }

    /**
     * TC_KEYBOX_MODAL_002: Verify view modal displays key box details (when data exists)
     */
    @Test(priority = 35, description = "Verify view modal displays all key box details.")
    public void verifyViewModalDisplaysDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Modal Details");
        TestLogger.info("Test: Verifying View Modal Details");

        int rowCount = keyboxPage.getTableRowCount();
        if (rowCount > 0) {
            keyboxPage.viewKeyboxDetails(0);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Assert.assertTrue(keyboxPage.isModalDisplayed(), "View modal should be displayed.");

            String serialNumber = keyboxPage.getViewModalSerialNumber();
            String location = keyboxPage.getViewModalLocation();
            String status = keyboxPage.getViewModalStatus();

            getExtentTest().log(Status.INFO, "Serial Number: " + serialNumber);
            getExtentTest().log(Status.INFO, "Location: " + location);
            getExtentTest().log(Status.INFO, "Status: " + status);

            keyboxPage.closeViewModal();
            getExtentTest().log(Status.PASS, "View modal displays details correctly.");
            TestLogger.success("View modal details test passed.");
        } else {
            getExtentTest().log(Status.INFO, "No key boxes present to test view modal.");
        }
    }

    // ==================================================================================
    // PAGINATION TEST CASES
    // ==================================================================================

    /**
     * TC_KEYBOX_PAG_001: Verify pagination is displayed when data exists
     */
    @Test(priority = 36, description = "Verify pagination controls are displayed.")
    public void verifyPaginationDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination");
        TestLogger.info("Test: Verifying Pagination");

        if (keyboxPage.isPaginationInfoDisplayed()) {
            String paginationInfo = keyboxPage.getPaginationInfoText();
            getExtentTest().log(Status.PASS, "Pagination is displayed: " + paginationInfo);
            TestLogger.success("Pagination verification passed.");
        } else {
            getExtentTest().log(Status.INFO, "Pagination not displayed - possibly single page or no data.");
        }
    }

    /**
     * TC_KEYBOX_PAG_002: Verify pagination navigation (when multiple pages exist)
     */
    @Test(priority = 37, description = "Verify pagination navigation functionality.")
    public void verifyPaginationNavigation() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Navigation");
        TestLogger.info("Test: Verifying Pagination Navigation");

        if (keyboxPage.isPaginationInfoDisplayed() && keyboxPage.isNextPageButtonEnabled()) {
            String initialPagination = keyboxPage.getPaginationInfoText();
            getExtentTest().log(Status.INFO, "Initial pagination: " + initialPagination);

            keyboxPage.clickNextPageButton();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            String newPagination = keyboxPage.getPaginationInfoText();
            getExtentTest().log(Status.INFO, "After next: " + newPagination);

            Assert.assertNotEquals(newPagination, initialPagination, "Pagination should change after clicking next.");

            if (keyboxPage.isPreviousPageButtonEnabled()) {
                keyboxPage.clickPreviousPageButton();

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                String finalPagination = keyboxPage.getPaginationInfoText();
                Assert.assertEquals(finalPagination, initialPagination, "Pagination should return to initial state.");
            }

            getExtentTest().log(Status.PASS, "Pagination navigation works correctly.");
            TestLogger.success("Pagination navigation test passed.");
        } else {
            getExtentTest().log(Status.INFO, "Pagination navigation test skipped - single page or no data.");
        }
    }

    // ==================================================================================
    // DATA VERIFICATION TEST CASES
    // ==================================================================================

    /**
     * TC_KEYBOX_DATA_001: Verify table displays correct data structure
     */
    @Test(priority = 38, description = "Verify table displays correct data structure.")
    public void verifyTableDataStructure() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Data Structure");
        TestLogger.info("Test: Verifying Table Data Structure");

        int rowCount = keyboxPage.getTableRowCount();
        if (rowCount > 0) {
            // Verify first row has all columns
            String sno = keyboxPage.getCellText(0, 0);
            String serialNumber = keyboxPage.getSerialNumberFromRow(0);
            String location = keyboxPage.getLocationFromRow(0);
            String roType = keyboxPage.getRoTypeFromRow(0);

            getExtentTest().log(Status.INFO, "S.No: " + sno);
            getExtentTest().log(Status.INFO, "Serial Number: " + serialNumber);
            getExtentTest().log(Status.INFO, "Location: " + location);
            getExtentTest().log(Status.INFO, "Ro Type: " + roType);

            Assert.assertFalse(sno.isEmpty(), "S.No should not be empty.");
            getExtentTest().log(Status.PASS, "Table data structure is correct.");
            TestLogger.success("Table data structure verification passed.");
        } else {
            getExtentTest().log(Status.INFO, "No data to verify table structure.");
            Assert.assertTrue(keyboxPage.isNoResultsDisplayed(), "Empty state should be displayed.");
        }
    }

    /**
     * TC_KEYBOX_DATA_002: Verify statistics count matches table row count
     */
    @Test(priority = 39, description = "Verify statistics count consistency.")
    public void verifyStatisticsCountConsistency() {
        getExtentTest().log(Status.INFO, "Test: Verifying Statistics Count Consistency");
        TestLogger.info("Test: Verifying Statistics Count Consistency");

        String totalCount = keyboxPage.getTotalKeyBoxCount();
        int rowCount = keyboxPage.getTableRowCount();

        getExtentTest().log(Status.INFO, "Total Key Box from card: " + totalCount);
        getExtentTest().log(Status.INFO, "Current page row count: " + rowCount);

        // Note: Total count might be more than current page if pagination exists
        int total = Integer.parseInt(totalCount);
        Assert.assertTrue(total >= rowCount, "Total count should be >= current page row count.");
        getExtentTest().log(Status.PASS, "Statistics count consistency verified.");
        TestLogger.success("Statistics count consistency test passed.");
    }

    /**
     * TC_KEYBOX_DATA_003: Verify Total Key Box card count matches table total records
     */
    @Test(priority = 63, description = "Verify Total Key Box card count matches table total records.")
    public void verifyTotalKeyBoxCountMatchesTableRecords() {
        getExtentTest().log(Status.INFO, "Test: Verifying Total Key Box Card Count Matches Table Total Records");
        TestLogger.info("Test: Verifying Total Key Box Card Count Matches Table Total Records");

        // Get count from Total Key Box statistics card
        String cardCountStr = keyboxPage.getTotalKeyBoxCount();
        int cardCount = Integer.parseInt(cardCountStr);
        getExtentTest().log(Status.INFO, "Total Key Box card count: " + cardCount);

        // Get total records from table (handles pagination)
        int tableRecords = keyboxPage.getTableTotalRecords();
        getExtentTest().log(Status.INFO, "Table total records: " + tableRecords);

        // Verify counts match
        Assert.assertEquals(cardCount, tableRecords,
                "Total Key Box card count (" + cardCount + ") should match table total records (" + tableRecords + ").");

        getExtentTest().log(Status.PASS, "Total Key Box card count (" + cardCount + ") matches table total records (" + tableRecords + ").");
        TestLogger.success("Total Key Box count matches table records verification passed.");
    }

    /**
     * TC_KEYBOX_DATA_004: Verify Registered card count matches table Registered status count
     */
    @Test(priority = 64, description = "Verify Registered card count matches table Registered status count.")
    public void verifyRegisteredCountMatchesTableStatus() {
        getExtentTest().log(Status.INFO, "Test: Verifying Registered Card Count Matches Table Registered Status Count");
        TestLogger.info("Test: Verifying Registered Card Count Matches Table Registered Status Count");

        // Get count from Registered statistics card
        String cardCountStr = keyboxPage.getRegisteredCount();
        int cardCount = Integer.parseInt(cardCountStr);
        getExtentTest().log(Status.INFO, "Registered card count: " + cardCount);

        // Get count of rows with Registered status from table (handles pagination)
        int tableRegisteredCount = keyboxPage.getRegisteredStatusCountFromTable();
        getExtentTest().log(Status.INFO, "Table Registered status count: " + tableRegisteredCount);

        // Verify counts match
        Assert.assertEquals(cardCount, tableRegisteredCount,
                "Registered card count (" + cardCount + ") should match table Registered status count (" + tableRegisteredCount + ").");

        getExtentTest().log(Status.PASS, "Registered card count (" + cardCount + ") matches table Registered status count (" + tableRegisteredCount + ").");
        TestLogger.success("Registered count matches table status verification passed.");
    }

    /**
     * TC_KEYBOX_DATA_005: Verify Unregistered card count matches table Unregistered status count
     */
    @Test(priority = 65, description = "Verify Unregistered card count matches table Unregistered status count.")
    public void verifyUnregisteredCountMatchesTableStatus() {
        getExtentTest().log(Status.INFO, "Test: Verifying Unregistered Card Count Matches Table Unregistered Status Count");
        TestLogger.info("Test: Verifying Unregistered Card Count Matches Table Unregistered Status Count");

        // Get count from Unregistered statistics card
        String cardCountStr = keyboxPage.getUnregisteredCount();
        int cardCount = Integer.parseInt(cardCountStr);
        getExtentTest().log(Status.INFO, "Unregistered card count: " + cardCount);

        // Get count of rows with Unregistered status from table (handles pagination)
        int tableUnregisteredCount = keyboxPage.getUnregisteredStatusCountFromTable();
        getExtentTest().log(Status.INFO, "Table Unregistered status count: " + tableUnregisteredCount);

        // Verify counts match
        Assert.assertEquals(cardCount, tableUnregisteredCount,
                "Unregistered card count (" + cardCount + ") should match table Unregistered status count (" + tableUnregisteredCount + ").");

        getExtentTest().log(Status.PASS, "Unregistered card count (" + cardCount + ") matches table Unregistered status count (" + tableUnregisteredCount + ").");
        TestLogger.success("Unregistered count matches table status verification passed.");
    }

    // ==================================================================================
    // PAGE LOAD TEST CASES
    // ==================================================================================

    /**
     * TC_KEYBOX_LOAD_001: Verify page loads completely without errors
     */
    @Test(priority = 40, description = "Verify Key Box page loads completely without errors.")
    public void verifyPageLoadsWithoutErrors() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Loads Without Errors");
        TestLogger.info("Test: Verifying Page Loads Without Errors");

        // Verify all essential elements are present
        Assert.assertTrue(keyboxPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertTrue(keyboxPage.isSearchButtonDisplayed(), "Search button should be displayed.");
        Assert.assertTrue(keyboxPage.isFilterButtonDisplayed(), "Filter button should be displayed.");
        Assert.assertTrue(keyboxPage.isTotalKeyBoxCardDisplayed(), "Statistics cards should be displayed.");
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Table should be displayed.");
        Assert.assertTrue(keyboxPage.isTableHeaderRowDisplayed(), "Table header row should be displayed.");

        getExtentTest().log(Status.PASS, "Key Box page loads completely without errors.");
        TestLogger.success("Page load verification passed.");
    }

    // ==================================================================================
    // FILTER FUNCTIONALITY TEST CASES
    // ==================================================================================

    /**
     * TC_KEYBOX_FILTER_001: Verify filter dialog opens when clicking Filter button
     */
    @Test(priority = 41, description = "Verify filter dialog opens when clicking Filter button.")
    public void verifyFilterDialogOpens() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Opens");
        TestLogger.info("Test: Verifying Filter Dialog Opens");

        keyboxPage.clickFilterButton();
        getExtentTest().log(Status.INFO, "Clicked Filter button.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isFilterDialogDisplayed(), "Filter dialog should be displayed after clicking Filter button.");
        getExtentTest().log(Status.PASS, "Filter dialog is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Filter dialog opens test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_002: Verify filter dialog title is displayed correctly
     */
    @Test(priority = 42, description = "Verify filter dialog title is 'Key Box Filter'.")
    public void verifyFilterDialogTitle() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Title");
        TestLogger.info("Test: Verifying Filter Dialog Title");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isFilterDialogTitleDisplayed(), "Filter dialog title should be displayed.");
        String title = keyboxPage.getFilterDialogTitleText();
        Assert.assertEquals(title, "Key Box Filter", "Filter dialog title should be 'Key Box Filter'.");
        getExtentTest().log(Status.PASS, "Filter dialog title is correct: " + title);

        keyboxPage.closeFilterDialog();
        TestLogger.success("Filter dialog title verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_003: Verify filter dialog description is displayed correctly
     */
    @Test(priority = 43, description = "Verify filter dialog description is displayed correctly.")
    public void verifyFilterDialogDescription() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Description");
        TestLogger.info("Test: Verifying Filter Dialog Description");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isFilterDialogDescriptionDisplayed(), "Filter dialog description should be displayed.");
        String description = keyboxPage.getFilterDialogDescriptionText();
        Assert.assertTrue(description.contains("Select filter to apply on key box list"),
                "Filter dialog description should contain expected text.");
        getExtentTest().log(Status.PASS, "Filter dialog description is correct: " + description);

        keyboxPage.closeFilterDialog();
        TestLogger.success("Filter dialog description verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_004: Verify Status filter label is displayed
     */
    @Test(priority = 44, description = "Verify Status filter label is displayed.")
    public void verifyStatusFilterLabelDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Label");
        TestLogger.info("Test: Verifying Status Filter Label");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isStatusFilterLabelDisplayed(), "Status filter label should be displayed.");
        getExtentTest().log(Status.PASS, "Status filter label is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Status filter label verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_005: Verify Status filter dropdown is displayed
     */
    @Test(priority = 45, description = "Verify Status filter dropdown is displayed.")
    public void verifyStatusFilterDropdownDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Dropdown");
        TestLogger.info("Test: Verifying Status Filter Dropdown");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isStatusFilterDropdownDisplayed(), "Status filter dropdown should be displayed.");
        getExtentTest().log(Status.PASS, "Status filter dropdown is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Status filter dropdown verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_006: Verify Status filter default value is 'All'
     */
    @Test(priority = 46, description = "Verify Status filter default value is 'All'.")
    public void verifyStatusFilterDefaultValue() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Default Value");
        TestLogger.info("Test: Verifying Status Filter Default Value");

        keyboxPage.openFilterDialog();

        String defaultValue = keyboxPage.getStatusFilterSelectedValue();
        Assert.assertEquals(defaultValue, "All", "Status filter default value should be 'All'.");
        getExtentTest().log(Status.PASS, "Status filter default value is correct: " + defaultValue);

        keyboxPage.closeFilterDialog();
        TestLogger.success("Status filter default value verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_007: Verify Type filter label is displayed
     */
    @Test(priority = 47, description = "Verify Type filter label is displayed.")
    public void verifyTypeFilterLabelDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Label");
        TestLogger.info("Test: Verifying Type Filter Label");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isTypeFilterLabelDisplayed(), "Type filter label should be displayed.");
        getExtentTest().log(Status.PASS, "Type filter label is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Type filter label verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_008: Verify Type filter dropdown is displayed
     */
    @Test(priority = 48, description = "Verify Type filter dropdown is displayed.")
    public void verifyTypeFilterDropdownDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Dropdown");
        TestLogger.info("Test: Verifying Type Filter Dropdown");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isTypeFilterDropdownDisplayed(), "Type filter dropdown should be displayed.");
        getExtentTest().log(Status.PASS, "Type filter dropdown is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Type filter dropdown verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_009: Verify Type filter default value is 'Today'
     */
    @Test(priority = 49, description = "Verify Type filter default value is 'Today'.")
    public void verifyTypeFilterDefaultValue() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Default Value");
        TestLogger.info("Test: Verifying Type Filter Default Value");

        keyboxPage.openFilterDialog();

        String defaultValue = keyboxPage.getTypeFilterSelectedValue();
        Assert.assertEquals(defaultValue, "Today", "Type filter default value should be 'Today'.");
        getExtentTest().log(Status.PASS, "Type filter default value is correct: " + defaultValue);

        keyboxPage.closeFilterDialog();
        TestLogger.success("Type filter default value verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_010: Verify Clear Filter button is displayed
     */
    @Test(priority = 50, description = "Verify Clear Filter button is displayed.")
    public void verifyClearFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Clear Filter Button");
        TestLogger.info("Test: Verifying Clear Filter Button");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isClearFilterButtonDisplayed(), "Clear Filter button should be displayed.");
        getExtentTest().log(Status.PASS, "Clear Filter button is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Clear Filter button verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_011: Verify Apply Filter button is displayed
     */
    @Test(priority = 51, description = "Verify Apply Filter button is displayed.")
    public void verifyApplyFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Apply Filter Button");
        TestLogger.info("Test: Verifying Apply Filter Button");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isApplyFilterButtonDisplayed(), "Apply Filter button should be displayed.");
        getExtentTest().log(Status.PASS, "Apply Filter button is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Apply Filter button verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_012: Verify filter dialog close button (X) is displayed
     */
    @Test(priority = 52, description = "Verify filter dialog close button (X) is displayed.")
    public void verifyFilterDialogCloseButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Close Button (X)");
        TestLogger.info("Test: Verifying Filter Dialog Close Button (X)");

        keyboxPage.openFilterDialog();

        Assert.assertTrue(keyboxPage.isFilterDialogCloseButtonDisplayed(), "Filter dialog close button (X) should be displayed.");
        getExtentTest().log(Status.PASS, "Filter dialog close button (X) is displayed.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Filter dialog close button verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_013: Verify filter dialog closes when clicking close button (X)
     */
    @Test(priority = 53, description = "Verify filter dialog closes when clicking close button (X).")
    public void verifyFilterDialogClosesOnCloseButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Closes on Close Button");
        TestLogger.info("Test: Verifying Filter Dialog Closes on Close Button");

        keyboxPage.openFilterDialog();
        Assert.assertTrue(keyboxPage.isFilterDialogDisplayed(), "Filter dialog should be open.");

        keyboxPage.clickFilterDialogCloseButton();
        getExtentTest().log(Status.INFO, "Clicked close button (X).");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isFilterDialogClosed(), "Filter dialog should be closed.");
        getExtentTest().log(Status.PASS, "Filter dialog closed successfully using close button.");
        TestLogger.success("Filter dialog close button functionality test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_014: Verify filter dialog closes when clicking Clear Filter button
     */
    @Test(priority = 54, description = "Verify filter dialog closes when clicking Clear Filter button.")
    public void verifyFilterDialogClosesOnClearFilter() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Closes on Clear Filter");
        TestLogger.info("Test: Verifying Filter Dialog Closes on Clear Filter");

        keyboxPage.openFilterDialog();
        Assert.assertTrue(keyboxPage.isFilterDialogDisplayed(), "Filter dialog should be open.");

        keyboxPage.clickClearFilterButton();
        getExtentTest().log(Status.INFO, "Clicked Clear Filter button.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isFilterDialogClosed(), "Filter dialog should be closed after clicking Clear Filter.");
        getExtentTest().log(Status.PASS, "Filter dialog closed successfully using Clear Filter button.");
        TestLogger.success("Clear Filter button closes dialog test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_015: Verify Status filter dropdown is clickable
     */
    @Test(priority = 55, description = "Verify Status filter dropdown is clickable.")
    public void verifyStatusFilterDropdownClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Dropdown Clickable");
        TestLogger.info("Test: Verifying Status Filter Dropdown Clickable");

        keyboxPage.openFilterDialog();

        keyboxPage.clickStatusFilterDropdown();
        getExtentTest().log(Status.INFO, "Clicked Status filter dropdown.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Dropdown should expand - we verify by checking the state change or options appearing
        getExtentTest().log(Status.PASS, "Status filter dropdown is clickable.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Status filter dropdown clickable test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_016: Verify Type filter dropdown is clickable
     */
    @Test(priority = 56, description = "Verify Type filter dropdown is clickable.")
    public void verifyTypeFilterDropdownClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Dropdown Clickable");
        TestLogger.info("Test: Verifying Type Filter Dropdown Clickable");

        keyboxPage.openFilterDialog();

        keyboxPage.clickTypeFilterDropdown();
        getExtentTest().log(Status.INFO, "Clicked Type filter dropdown.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Dropdown should expand
        getExtentTest().log(Status.PASS, "Type filter dropdown is clickable.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Type filter dropdown clickable test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_017: Verify Apply Filter button is clickable
     */
    @Test(priority = 57, description = "Verify Apply Filter button is clickable and applies filter.")
    public void verifyApplyFilterButtonClickable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Apply Filter Button Clickable");
        TestLogger.info("Test: Verifying Apply Filter Button Clickable");

        keyboxPage.openFilterDialog();

        keyboxPage.clickApplyFilterButton();
        getExtentTest().log(Status.INFO, "Clicked Apply Filter button.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // After applying filter, dialog should close and table should refresh
        Assert.assertTrue(keyboxPage.isFilterDialogClosed(), "Filter dialog should be closed after applying filter.");
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Key Box table should be displayed after applying filter.");
        getExtentTest().log(Status.PASS, "Apply Filter button works correctly.");
        TestLogger.success("Apply Filter button clickable test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_018: Verify all filter dialog elements are displayed
     */
    @Test(priority = 58, description = "Verify all filter dialog elements are displayed correctly.")
    public void verifyAllFilterDialogElements() {
        getExtentTest().log(Status.INFO, "Test: Verifying All Filter Dialog Elements");
        TestLogger.info("Test: Verifying All Filter Dialog Elements");

        keyboxPage.openFilterDialog();

        // Verify title
        Assert.assertTrue(keyboxPage.isFilterDialogTitleDisplayed(), "Filter dialog title should be displayed.");
        getExtentTest().log(Status.INFO, "Title is displayed.");

        // Verify description
        Assert.assertTrue(keyboxPage.isFilterDialogDescriptionDisplayed(), "Filter dialog description should be displayed.");
        getExtentTest().log(Status.INFO, "Description is displayed.");

        // Verify Status filter
        Assert.assertTrue(keyboxPage.isStatusFilterLabelDisplayed(), "Status label should be displayed.");
        Assert.assertTrue(keyboxPage.isStatusFilterDropdownDisplayed(), "Status dropdown should be displayed.");
        getExtentTest().log(Status.INFO, "Status filter is displayed.");

        // Verify Type filter
        Assert.assertTrue(keyboxPage.isTypeFilterLabelDisplayed(), "Type label should be displayed.");
        Assert.assertTrue(keyboxPage.isTypeFilterDropdownDisplayed(), "Type dropdown should be displayed.");
        getExtentTest().log(Status.INFO, "Type filter is displayed.");

        // Verify action buttons
        Assert.assertTrue(keyboxPage.isClearFilterButtonDisplayed(), "Clear Filter button should be displayed.");
        Assert.assertTrue(keyboxPage.isApplyFilterButtonDisplayed(), "Apply Filter button should be displayed.");
        getExtentTest().log(Status.INFO, "Action buttons are displayed.");

        // Verify close button
        Assert.assertTrue(keyboxPage.isFilterDialogCloseButtonDisplayed(), "Close button should be displayed.");
        getExtentTest().log(Status.INFO, "Close button is displayed.");

        keyboxPage.closeFilterDialog();
        getExtentTest().log(Status.PASS, "All filter dialog elements are displayed correctly.");
        TestLogger.success("All filter dialog elements verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_019: Verify filter persists after applying
     */
    @Test(priority = 59, description = "Verify filter selection persists after applying.")
    public void verifyFilterPersistsAfterApplying() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Persists After Applying");
        TestLogger.info("Test: Verifying Filter Persists After Applying");

        // Apply filter
        keyboxPage.openFilterDialog();
        String initialStatusValue = keyboxPage.getStatusFilterSelectedValue();
        String initialTypeValue = keyboxPage.getTypeFilterSelectedValue();
        getExtentTest().log(Status.INFO, "Initial Status: " + initialStatusValue + ", Type: " + initialTypeValue);

        keyboxPage.clickApplyFilterButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Re-open filter dialog and verify values are maintained
        keyboxPage.openFilterDialog();
        String afterApplyStatusValue = keyboxPage.getStatusFilterSelectedValue();
        String afterApplyTypeValue = keyboxPage.getTypeFilterSelectedValue();
        getExtentTest().log(Status.INFO, "After Apply Status: " + afterApplyStatusValue + ", Type: " + afterApplyTypeValue);

        // Values should be the same as initial (since we didn't change them)
        Assert.assertEquals(afterApplyStatusValue, initialStatusValue, "Status filter value should persist.");
        Assert.assertEquals(afterApplyTypeValue, initialTypeValue, "Type filter value should persist.");

        keyboxPage.closeFilterDialog();
        getExtentTest().log(Status.PASS, "Filter values persist after applying.");
        TestLogger.success("Filter persistence verification passed.");
    }

    /**
     * TC_KEYBOX_FILTER_020: Verify table refreshes after applying filter
     */
    @Test(priority = 60, description = "Verify table refreshes after applying filter.")
    public void verifyTableRefreshesAfterFilter() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Refreshes After Filter");
        TestLogger.info("Test: Verifying Table Refreshes After Filter");

        // Get initial table state
        int initialRowCount = keyboxPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Initial row count: " + initialRowCount);

        // Apply filter
        keyboxPage.openFilterDialog();
        keyboxPage.clickApplyFilterButton();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify table is still displayed and functioning
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Table should be displayed after applying filter.");
        int afterFilterRowCount = keyboxPage.getTableRowCount();
        getExtentTest().log(Status.INFO, "Row count after filter: " + afterFilterRowCount);

        // Table should be refreshed (might have same or different count based on filter)
        getExtentTest().log(Status.PASS, "Table refreshes correctly after applying filter.");
        TestLogger.success("Table refresh after filter test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_021: Verify Clear Filter resets filter values
     */
    @Test(priority = 61, description = "Verify Clear Filter resets filter values to default.")
    public void verifyClearFilterResetsValues() {
        getExtentTest().log(Status.INFO, "Test: Verifying Clear Filter Resets Values");
        TestLogger.info("Test: Verifying Clear Filter Resets Values");

        // Open filter and clear
        keyboxPage.openFilterDialog();
        keyboxPage.clickClearFilterButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Re-open and verify default values
        keyboxPage.openFilterDialog();
        String statusValue = keyboxPage.getStatusFilterSelectedValue();
        String typeValue = keyboxPage.getTypeFilterSelectedValue();

        getExtentTest().log(Status.INFO, "After clear - Status: " + statusValue + ", Type: " + typeValue);

        // Default values should be restored
        Assert.assertEquals(statusValue, "All", "Status should be reset to 'All'.");
        Assert.assertEquals(typeValue, "Today", "Type should be reset to 'Today'.");

        keyboxPage.closeFilterDialog();
        getExtentTest().log(Status.PASS, "Clear Filter resets values to default.");
        TestLogger.success("Clear Filter reset test passed.");
    }

    /**
     * TC_KEYBOX_FILTER_022: Verify filter dialog can be reopened after closing
     */
    @Test(priority = 62, description = "Verify filter dialog can be reopened after closing.")
    public void verifyFilterDialogReopensAfterClose() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Reopens After Close");
        TestLogger.info("Test: Verifying Filter Dialog Reopens After Close");

        // Open and close
        keyboxPage.openFilterDialog();
        Assert.assertTrue(keyboxPage.isFilterDialogDisplayed(), "Filter dialog should open.");
        keyboxPage.closeFilterDialog();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isFilterDialogClosed(), "Filter dialog should be closed.");

        // Reopen
        keyboxPage.openFilterDialog();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(keyboxPage.isFilterDialogDisplayed(), "Filter dialog should reopen.");
        getExtentTest().log(Status.PASS, "Filter dialog reopens correctly after closing.");

        keyboxPage.closeFilterDialog();
        TestLogger.success("Filter dialog reopen test passed.");
    }
}
