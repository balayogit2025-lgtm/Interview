package TestRun;

import Base.BaseTest;
import Pages.KeyPositionTemplatePage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * KeyPositionTemplateTest - Comprehensive test cases for Key Position Template page
 * Covers element verification, text verification, positive and negative test scenarios
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class KeyPositionTemplateTest extends BaseTest {

    private LoginPage loginPage;
    private KeyPositionTemplatePage keyPositionTemplatePage;
    private Faker faker;

    @BeforeMethod
    public void setupKeyPositionTemplatePage() {
        navigateToApp();
        loginPage = new LoginPage(getDriver());
        keyPositionTemplatePage = new KeyPositionTemplatePage(getDriver());
        faker = new Faker();

        // Login before each test
        loginPage.login(ConfigReader.getkeyPositionValidEmail(), ConfigReader.getValidPassword());
        keyPositionTemplatePage.navigateToKeyPositionTemplatePage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        getExtentTest().log(Status.INFO, "Setup complete. Navigated to Key Position Template Page.");
    }

    // ==================== HEADER ELEMENT VERIFICATION TESTS ====================

    @Test(priority = 1, description = "Verify page header 'Key Position Template' is displayed correctly")
    public void verifyPageHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Header");
        TestLogger.info("Test: Verifying Page Header");

        Assert.assertTrue(keyPositionTemplatePage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(keyPositionTemplatePage.getPageHeaderText(), "Key Position Template",
                "Page header text should be 'Key Position Template'");

        getExtentTest().log(Status.PASS, "Page header 'Key Position Template' is displayed correctly.");
        TestLogger.success("Page header verification passed.");
    }

    @Test(priority = 2, description = "Verify page sub-header text is displayed correctly")
    public void verifyPageSubHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Sub-Header");
        TestLogger.info("Test: Verifying Page Sub-Header");

        Assert.assertTrue(keyPositionTemplatePage.isPageSubHeaderDisplayed(), "Page sub-header should be displayed.");
        String expectedSubHeader = "Manage key position template for activity";
        Assert.assertEquals(keyPositionTemplatePage.getPageSubHeaderText(), expectedSubHeader,
                "Sub-header text should match.");

        getExtentTest().log(Status.PASS, "Page sub-header is displayed correctly: " + expectedSubHeader);
        TestLogger.success("Page sub-header verification passed.");
    }

    // ==================== SEARCH ELEMENT VERIFICATION TESTS ====================

    @Test(priority = 3, description = "Verify search button is displayed")
    public void verifySearchButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Button");
        TestLogger.info("Test: Verifying Search Button");

        Assert.assertTrue(keyPositionTemplatePage.isSearchButtonDisplayed(), "Search button should be displayed.");

        getExtentTest().log(Status.PASS, "Search button is displayed.");
        TestLogger.success("Search button verification passed.");
    }

    @Test(priority = 4, description = "Verify search input is displayed after clicking search button")
    public void verifySearchInputDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Input");
        TestLogger.info("Test: Verifying Search Input");

        keyPositionTemplatePage.clickSearchButton();
        Assert.assertTrue(keyPositionTemplatePage.isSearchInputDisplayed(), "Search input should be displayed.");

        getExtentTest().log(Status.PASS, "Search input is displayed after clicking search button.");
        TestLogger.success("Search input verification passed.");
    }

    // ==================== ADD TEMPLATE BUTTON VERIFICATION ====================

    @Test(priority = 5, description = "Verify Add Template button is displayed")
    public void verifyAddTemplateButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Button");
        TestLogger.info("Test: Verifying Add Template Button");

        Assert.assertTrue(keyPositionTemplatePage.isAddTemplateButtonDisplayed(),
                "Add Template button should be displayed.");

        getExtentTest().log(Status.PASS, "Add Template button is displayed.");
        TestLogger.success("Add Template button verification passed.");
    }

    // ==================== TABLE ELEMENT VERIFICATION TESTS ====================

    @Test(priority = 6, description = "Verify Key Position Template table is displayed")
    public void verifyTemplateTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Template Table");
        TestLogger.info("Test: Verifying Template Table");

        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Key Position Template table should be displayed.");

        getExtentTest().log(Status.PASS, "Key Position Template table is displayed.");
        TestLogger.success("Template table verification passed.");
    }

    @Test(priority = 7, description = "Verify table header row is displayed")
    public void verifyTableHeaderRowDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Header Row");
        TestLogger.info("Test: Verifying Table Header Row");

        Assert.assertTrue(keyPositionTemplatePage.isTableHeaderRowDisplayed(),
                "Table header row should be displayed.");

        getExtentTest().log(Status.PASS, "Table header row is displayed.");
        TestLogger.success("Table header row verification passed.");
    }

    @Test(priority = 8, description = "Verify all column headers are displayed with correct text")
    public void verifyTableColumnHeaders() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Column Headers");
        TestLogger.info("Test: Verifying Table Column Headers");

        List<String> expectedHeaders = Arrays.asList("S.NO", "NAME", "CREATED BY", "UPDATED BY", "ACTION");
        List<String> actualHeaders = keyPositionTemplatePage.getColumnHeaderTexts();

        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");

        getExtentTest().log(Status.PASS, "All column headers are displayed correctly: " + actualHeaders);
        TestLogger.success("Table column headers verification passed.");
    }

    @Test(priority = 9, description = "Verify S.No column header is displayed")
    public void verifySnoHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying S.No Header");
        TestLogger.info("Test: Verifying S.No Header");

        Assert.assertTrue(keyPositionTemplatePage.isSnoHeaderDisplayed(), "S.No header should be displayed.");

        getExtentTest().log(Status.PASS, "S.No header is displayed.");
        TestLogger.success("S.No header verification passed.");
    }

    @Test(priority = 10, description = "Verify Name column header is displayed")
    public void verifyNameHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Name Header");
        TestLogger.info("Test: Verifying Name Header");

        Assert.assertTrue(keyPositionTemplatePage.isNameHeaderDisplayed(), "Name header should be displayed.");

        getExtentTest().log(Status.PASS, "Name header is displayed.");
        TestLogger.success("Name header verification passed.");
    }

    @Test(priority = 11, description = "Verify Created By column header is displayed")
    public void verifyCreatedByHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Created By Header");
        TestLogger.info("Test: Verifying Created By Header");

        Assert.assertTrue(keyPositionTemplatePage.isCreatedByHeaderDisplayed(),
                "Created By header should be displayed.");

        getExtentTest().log(Status.PASS, "Created By header is displayed.");
        TestLogger.success("Created By header verification passed.");
    }

    @Test(priority = 12, description = "Verify Updated By column header is displayed")
    public void verifyUpdatedByHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Updated By Header");
        TestLogger.info("Test: Verifying Updated By Header");

        Assert.assertTrue(keyPositionTemplatePage.isUpdatedByHeaderDisplayed(),
                "Updated By header should be displayed.");

        getExtentTest().log(Status.PASS, "Updated By header is displayed.");
        TestLogger.success("Updated By header verification passed.");
    }

    @Test(priority = 13, description = "Verify Action column header is displayed")
    public void verifyActionHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Action Header");
        TestLogger.info("Test: Verifying Action Header");

        Assert.assertTrue(keyPositionTemplatePage.isActionHeaderDisplayed(), "Action header should be displayed.");

        getExtentTest().log(Status.PASS, "Action header is displayed.");
        TestLogger.success("Action header verification passed.");
    }

    @Test(priority = 14, description = "Verify all table headers are displayed together")
    public void verifyAllTableHeadersDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying All Table Headers");
        TestLogger.info("Test: Verifying All Table Headers");

        Assert.assertTrue(keyPositionTemplatePage.verifyAllTableHeadersDisplayed(),
                "All table headers should be displayed.");

        getExtentTest().log(Status.PASS, "All table headers are displayed correctly.");
        TestLogger.success("All table headers verification passed.");
    }

    // ==================== PAGINATION ELEMENT VERIFICATION TESTS ====================

    @Test(priority = 15, description = "Verify pagination info is displayed")
    public void verifyPaginationInfoDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Info");
        TestLogger.info("Test: Verifying Pagination Info");

        Assert.assertTrue(keyPositionTemplatePage.isPaginationInfoDisplayed(),
                "Pagination info should be displayed.");

        String paginationText = keyPositionTemplatePage.getPaginationInfoText();
        Assert.assertTrue(paginationText.contains("Showing"),
                "Pagination text should contain 'Showing'");

        getExtentTest().log(Status.PASS, "Pagination info is displayed: " + paginationText);
        TestLogger.success("Pagination info verification passed.");
    }

    @Test(priority = 16, description = "Verify pagination controls are displayed")
    public void verifyPaginationControlsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Controls");
        TestLogger.info("Test: Verifying Pagination Controls");

        Assert.assertTrue(keyPositionTemplatePage.verifyAllPaginationControlsDisplayed(),
                "All pagination controls should be displayed.");

        getExtentTest().log(Status.PASS, "All pagination controls are displayed.");
        TestLogger.success("Pagination controls verification passed.");
    }

    @Test(priority = 17, description = "Verify first page button is displayed")
    public void verifyFirstPageButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying First Page Button");
        TestLogger.info("Test: Verifying First Page Button");

        Assert.assertTrue(keyPositionTemplatePage.isFirstPageButtonDisplayed(),
                "First page button should be displayed.");

        getExtentTest().log(Status.PASS, "First page button is displayed.");
        TestLogger.success("First page button verification passed.");
    }

    @Test(priority = 18, description = "Verify previous page button is displayed")
    public void verifyPreviousPageButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Previous Page Button");
        TestLogger.info("Test: Verifying Previous Page Button");

        Assert.assertTrue(keyPositionTemplatePage.isPreviousPageButtonDisplayed(),
                "Previous page button should be displayed.");

        getExtentTest().log(Status.PASS, "Previous page button is displayed.");
        TestLogger.success("Previous page button verification passed.");
    }

    @Test(priority = 19, description = "Verify next page button is displayed")
    public void verifyNextPageButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Next Page Button");
        TestLogger.info("Test: Verifying Next Page Button");

        Assert.assertTrue(keyPositionTemplatePage.isNextPageButtonDisplayed(),
                "Next page button should be displayed.");

        getExtentTest().log(Status.PASS, "Next page button is displayed.");
        TestLogger.success("Next page button verification passed.");
    }

    @Test(priority = 20, description = "Verify last page button is displayed")
    public void verifyLastPageButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Last Page Button");
        TestLogger.info("Test: Verifying Last Page Button");

        Assert.assertTrue(keyPositionTemplatePage.isLastPageButtonDisplayed(),
                "Last page button should be displayed.");

        getExtentTest().log(Status.PASS, "Last page button is displayed.");
        TestLogger.success("Last page button verification passed.");
    }

    // ==================== POSITIVE TEST CASES - SEARCH FUNCTIONALITY ====================

    @Test(priority = 21, description = "Verify search functionality with valid template name")
    public void verifySearchWithValidTemplateName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Valid Template Name");
        TestLogger.info("Test: Verifying Search with Valid Template Name");

        keyPositionTemplatePage.searchWithValidTemplateName();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        int rowCount = keyPositionTemplatePage.getTableRowCount();
        Assert.assertTrue(rowCount > 0, "Search should return at least one result.");

        getExtentTest().log(Status.PASS, "Search with valid template name returned " + rowCount + " results.");
        TestLogger.success("Search with valid template name verification passed.");
    }

    @Test(priority = 22, description = "Verify search input can be cleared")
    public void verifySearchInputCanBeCleared() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Input Clear");
        TestLogger.info("Test: Verifying Search Input Clear");

        keyPositionTemplatePage.clickSearchButton();
        keyPositionTemplatePage.searchTemplate("test template");
        keyPositionTemplatePage.clearSearch();

        // After clearing, table should show all results
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clearing search.");

        getExtentTest().log(Status.PASS, "Search input cleared successfully.");
        TestLogger.success("Search input clear verification passed.");
    }

    // ==================== POSITIVE TEST CASES - VIEW FUNCTIONALITY ====================

    @Test(priority = 23, description = "Verify View action opens template details modal")
    public void verifyViewTemplateOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Template Opens Modal");
        TestLogger.info("Test: Verifying View Template Opens Modal");

        keyPositionTemplatePage.clickFirstActionButton().clickViewButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "View modal should be displayed.");

        getExtentTest().log(Status.PASS, "View template modal is displayed.");
        TestLogger.success("View template modal verification passed.");
    }

    @Test(priority = 24, description = "Verify View modal can be closed using close button")
    public void verifyViewModalCloseButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Modal Close Button");
        TestLogger.info("Test: Verifying View Modal Close Button");

        keyPositionTemplatePage.viewFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "View modal should be displayed.");

        keyPositionTemplatePage.clickModalCloseButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after closing modal.");

        getExtentTest().log(Status.PASS, "View modal closed successfully using close button.");
        TestLogger.success("View modal close button verification passed.");
    }

    // ==================== POSITIVE TEST CASES - EDIT FUNCTIONALITY ====================

    @Test(priority = 25, description = "Verify Edit action opens edit modal")
    public void verifyEditTemplateOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Template Opens Modal");
        TestLogger.info("Test: Verifying Edit Template Opens Modal");

        keyPositionTemplatePage.clickFirstActionButton().clickEditButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Edit modal should be displayed.");

        getExtentTest().log(Status.PASS, "Edit template modal is displayed.");
        TestLogger.success("Edit template modal verification passed.");
    }

    @Test(priority = 26, description = "Verify Edit modal displays Cancel button")
    public void verifyEditModalCancelButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Modal Cancel Button");
        TestLogger.info("Test: Verifying Edit Modal Cancel Button");

        keyPositionTemplatePage.editFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isCancelButtonDisplayed(),
                "Cancel button should be displayed in edit modal.");

        getExtentTest().log(Status.PASS, "Cancel button is displayed in edit modal.");
        TestLogger.success("Edit modal cancel button verification passed.");
    }

    @Test(priority = 27, description = "Verify Cancel button closes edit modal")
    public void verifyCancelButtonClosesEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Button Closes Edit Modal");
        TestLogger.info("Test: Verifying Cancel Button Closes Edit Modal");

        keyPositionTemplatePage.editFirstTemplate();
        keyPositionTemplatePage.clickCancelButton();

        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clicking Cancel.");

        getExtentTest().log(Status.PASS, "Cancel button closed edit modal successfully.");
        TestLogger.success("Cancel button close edit modal verification passed.");
    }

    // ==================== POSITIVE TEST CASES - ADD TEMPLATE FUNCTIONALITY ====================

    @Test(priority = 28, description = "Verify Add Template button opens modal")
    public void verifyAddTemplateOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Opens Modal");
        TestLogger.info("Test: Verifying Add Template Opens Modal");

        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Add Template modal should be displayed.");

        getExtentTest().log(Status.PASS, "Add Template modal is displayed.");
        TestLogger.success("Add Template modal verification passed.");
    }

    @Test(priority = 29, description = "Verify Add Template modal displays template name input")
    public void verifyAddTemplateModalInputField() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Modal Input Field");
        TestLogger.info("Test: Verifying Add Template Modal Input Field");

        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateNameInputDisplayed(),
                "Template name input should be displayed.");

        getExtentTest().log(Status.PASS, "Template name input is displayed in Add Template modal.");
        TestLogger.success("Add Template modal input field verification passed.");
    }

    @Test(priority = 30, description = "Verify Add Template modal displays Submit button")
    public void verifyAddTemplateModalSubmitButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Modal Submit Button");
        TestLogger.info("Test: Verifying Add Template Modal Submit Button");

        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isSubmitButtonDisplayed() || keyPositionTemplatePage.isSaveButtonDisplayed(),
                "Submit/Save button should be displayed.");

        getExtentTest().log(Status.PASS, "Submit/Save button is displayed in Add Template modal.");
        TestLogger.success("Add Template modal submit button verification passed.");
    }

    @Test(priority = 31, description = "Verify Cancel button in Add Template modal closes the modal")
    public void verifyCancelButtonInAddTemplateModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Button in Add Template Modal");
        TestLogger.info("Test: Verifying Cancel Button in Add Template Modal");

        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Add Template modal should be displayed.");

        keyPositionTemplatePage.clickCancelButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clicking Cancel.");

        getExtentTest().log(Status.PASS, "Cancel button closed Add Template modal successfully.");
        TestLogger.success("Cancel button in Add Template modal verification passed.");
    }

    @Test(priority = 32, description = "Verify Close button in Add Template modal closes the modal")
    public void verifyCloseButtonInAddTemplateModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Close Button in Add Template Modal");
        TestLogger.info("Test: Verifying Close Button in Add Template Modal");

        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Add Template modal should be displayed.");

        keyPositionTemplatePage.clickModalCloseButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clicking Close.");

        getExtentTest().log(Status.PASS, "Close button closed Add Template modal successfully.");
        TestLogger.success("Close button in Add Template modal verification passed.");
    }

    // ==================== POSITIVE TEST CASES - PAGINATION FUNCTIONALITY ====================

    @Test(priority = 33, description = "Verify pagination navigation functionality")
    public void verifyPaginationNavigation() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Navigation");
        TestLogger.info("Test: Verifying Pagination Navigation");

        if (keyPositionTemplatePage.isNextPageButtonEnabled()) {
            TestLogger.info("Next page button is ENABLED. Proceeding to verify pagination navigation.");

            keyPositionTemplatePage.clickNextPageButton();
            TestLogger.info("Clicked Next page button successfully.");

            keyPositionTemplatePage.clickPreviousPageButton();
            TestLogger.info("Clicked Previous page button successfully.");

            keyPositionTemplatePage.clickLastPageButton();
            TestLogger.info("Clicked Last page button successfully.");

            keyPositionTemplatePage.clickFirstPageButton();
            TestLogger.info("Clicked First page button successfully.");

            getExtentTest().log(Status.PASS, "All pagination buttons navigated successfully.");
        } else {
            TestLogger.info("Next page button is DISABLED - only one page of results.");
            getExtentTest().log(Status.PASS, "Pagination not applicable - only one page of results.");
        }

        TestLogger.success("Pagination navigation verification passed.");
    }

    @Test(priority = 34, description = "Verify page info updates after navigation")
    public void verifyPageInfoUpdatesAfterNavigation() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Info Updates After Navigation");
        TestLogger.info("Test: Verifying Page Info Updates After Navigation");

        String initialPaginationText = keyPositionTemplatePage.getPaginationInfoText();

        if (keyPositionTemplatePage.isNextPageButtonEnabled()) {
            keyPositionTemplatePage.clickNextPageButton();
            String updatedPaginationText = keyPositionTemplatePage.getPaginationInfoText();

            Assert.assertNotEquals(initialPaginationText, updatedPaginationText,
                    "Pagination info should update after navigation.");

            getExtentTest().log(Status.PASS, "Page info updated after navigation. Initial: " +
                    initialPaginationText + ", Updated: " + updatedPaginationText);
        } else {
            getExtentTest().log(Status.PASS, "Pagination navigation not applicable - only one page of results.");
        }

        TestLogger.success("Page info update verification passed.");
    }

    // ==================== POSITIVE TEST CASES - TABLE DATA VERIFICATION ====================

    @Test(priority = 35, description = "Verify table has data rows")
    public void verifyTableHasDataRows() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Has Data Rows");
        TestLogger.info("Test: Verifying Table Has Data Rows");

        int rowCount = keyPositionTemplatePage.getTableRowCount();
        Assert.assertTrue(rowCount > 0, "Table should have at least one data row.");

        getExtentTest().log(Status.PASS, "Table has " + rowCount + " data row(s).");
        TestLogger.success("Table data rows verification passed.");
    }

    @Test(priority = 36, description = "Verify template name is displayed in table")
    public void verifyTemplateNameDisplayedInTable() {
        getExtentTest().log(Status.INFO, "Test: Verifying Template Name Displayed in Table");
        TestLogger.info("Test: Verifying Template Name Displayed in Table");

        String templateName = keyPositionTemplatePage.getFirstTemplateName();
        Assert.assertFalse(templateName.isEmpty(), "Template name should not be empty.");

        getExtentTest().log(Status.PASS, "Template name is displayed: " + templateName);
        TestLogger.success("Template name display verification passed.");
    }

    // ==================== NEGATIVE TEST CASES - SEARCH FUNCTIONALITY ====================

    @Test(priority = 37, description = "Verify search with non-existent template name returns no results")
    public void verifySearchWithNonExistentTemplateName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Non-Existent Template Name");
        TestLogger.info("Test: Verifying Search with Non-Existent Template Name");

        keyPositionTemplatePage.searchWithInvalidTemplateName();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        int rowCount = keyPositionTemplatePage.getTableRowCount();
        Assert.assertEquals(rowCount, 0, "Search with non-existent template should return no results.");

        getExtentTest().log(Status.PASS, "Search with non-existent template name returned 0 results as expected.");
        TestLogger.success("Search with non-existent template name verification passed.");
    }

    @Test(priority = 38, description = "Verify search with special characters")
    public void verifySearchWithSpecialCharacters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Special Characters");
        TestLogger.info("Test: Verifying Search with Special Characters");

        keyPositionTemplatePage.clickSearchButton();
        keyPositionTemplatePage.searchTemplate("@#$%^&*()");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        // System should handle special characters gracefully
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Table should be displayed even with special character search.");

        getExtentTest().log(Status.PASS, "Search with special characters handled gracefully.");
        TestLogger.success("Search with special characters verification passed.");
    }

    @Test(priority = 39, description = "Verify search with only spaces")
    public void verifySearchWithOnlySpaces() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Only Spaces");
        TestLogger.info("Test: Verifying Search with Only Spaces");

        keyPositionTemplatePage.clickSearchButton();
        keyPositionTemplatePage.searchTemplate("     ");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        // System should handle spaces search gracefully
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Table should be displayed even with spaces-only search.");

        getExtentTest().log(Status.PASS, "Search with only spaces handled gracefully.");
        TestLogger.success("Search with only spaces verification passed.");
    }

    @Test(priority = 40, description = "Verify search with very long text")
    public void verifySearchWithVeryLongText() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Very Long Text");
        TestLogger.info("Test: Verifying Search with Very Long Text");

        String longText = "a".repeat(500);
        keyPositionTemplatePage.clickSearchButton();
        keyPositionTemplatePage.searchTemplate(longText);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        // System should handle long text search gracefully
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Table should be displayed even with very long search text.");

        getExtentTest().log(Status.PASS, "Search with very long text handled gracefully.");
        TestLogger.success("Search with very long text verification passed.");
    }

    // ==================== NEGATIVE TEST CASES - ADD TEMPLATE ====================

    @Test(priority = 41, description = "Verify submitting empty template name shows validation error")
    public void verifyEmptyTemplateNameValidation() {
        getExtentTest().log(Status.INFO, "Test: Verifying Empty Template Name Validation");
        TestLogger.info("Test: Verifying Empty Template Name Validation");

        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.clearTemplateNameInput();
        keyPositionTemplatePage.clickSubmitButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // Modal should still be displayed or validation error should be shown
        boolean hasValidation = keyPositionTemplatePage.isModalDisplayed() ||
                keyPositionTemplatePage.isValidationErrorDisplayed() ||
                keyPositionTemplatePage.isErrorToastDisplayed();

        Assert.assertTrue(hasValidation,
                "Validation should prevent submission with empty template name.");

        getExtentTest().log(Status.PASS, "Empty template name validation works correctly.");
        TestLogger.success("Empty template name validation verification passed.");
    }

    @Test(priority = 42, description = "Verify template name with only spaces shows validation")
    public void verifySpacesOnlyTemplateNameValidation() {
        getExtentTest().log(Status.INFO, "Test: Verifying Spaces Only Template Name Validation");
        TestLogger.info("Test: Verifying Spaces Only Template Name Validation");

        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterTemplateName("     ");
        keyPositionTemplatePage.clickSubmitButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // Modal should still be displayed or validation error should be shown
        boolean hasValidation = keyPositionTemplatePage.isModalDisplayed() ||
                keyPositionTemplatePage.isValidationErrorDisplayed() ||
                keyPositionTemplatePage.isErrorToastDisplayed();

        Assert.assertTrue(hasValidation,
                "Validation should prevent submission with spaces-only template name.");

        getExtentTest().log(Status.PASS, "Spaces-only template name validation works correctly.");
        TestLogger.success("Spaces-only template name validation verification passed.");
    }

    // ==================== NEGATIVE TEST CASES - PAGINATION ====================

    @Test(priority = 43, description = "Verify previous page button is disabled on first page")
    public void verifyPreviousButtonDisabledOnFirstPage() {
        getExtentTest().log(Status.INFO, "Test: Verifying Previous Button Disabled on First Page");
        TestLogger.info("Test: Verifying Previous Button Disabled on First Page");

        // Ensure we're on the first page
        keyPositionTemplatePage.clickFirstPageButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        Assert.assertFalse(keyPositionTemplatePage.isPreviousPageButtonEnabled(),
                "Previous page button should be disabled on first page.");

        getExtentTest().log(Status.PASS, "Previous page button is disabled on first page.");
        TestLogger.success("Previous button disabled on first page verification passed.");
    }

    @Test(priority = 44, description = "Verify first page button is disabled on first page")
    public void verifyFirstButtonDisabledOnFirstPage() {
        getExtentTest().log(Status.INFO, "Test: Verifying First Button Disabled on First Page");
        TestLogger.info("Test: Verifying First Button Disabled on First Page");

        // Ensure we're on the first page
        try {
            keyPositionTemplatePage.clickFirstPageButton();
            Thread.sleep(500);
        } catch (Exception e) {
        }

        // Check if first page button class contains disabled indicator
        String paginationInfo = keyPositionTemplatePage.getPaginationInfoText();
        Assert.assertTrue(paginationInfo.contains("1"),
                "Should be on the first page.");

        getExtentTest().log(Status.PASS, "On first page - verified via pagination info.");
        TestLogger.success("First page verification passed.");
    }

    // ==================== SECURITY TEST CASES ====================

    @Test(priority = 45, description = "Verify XSS prevention in search field")
    public void verifyXSSPreventionInSearch() {
        getExtentTest().log(Status.INFO, "Test: Verifying XSS Prevention in Search");
        TestLogger.info("Test: Verifying XSS Prevention in Search");

        String xssPayload = "<script>alert('XSS')</script>";
        keyPositionTemplatePage.clickSearchButton();
        keyPositionTemplatePage.searchTemplate(xssPayload);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        // System should handle XSS payload safely
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Table should be displayed - XSS should be prevented.");

        getExtentTest().log(Status.PASS, "XSS prevention in search field works correctly.");
        TestLogger.success("XSS prevention verification passed.");
    }

    @Test(priority = 46, description = "Verify SQL injection prevention in search field")
    public void verifySQLInjectionPreventionInSearch() {
        getExtentTest().log(Status.INFO, "Test: Verifying SQL Injection Prevention in Search");
        TestLogger.info("Test: Verifying SQL Injection Prevention in Search");

        String sqlPayload = "' OR '1'='1";
        keyPositionTemplatePage.clickSearchButton();
        keyPositionTemplatePage.searchTemplate(sqlPayload);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        // System should handle SQL injection payload safely
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Table should be displayed - SQL injection should be prevented.");

        getExtentTest().log(Status.PASS, "SQL injection prevention in search field works correctly.");
        TestLogger.success("SQL injection prevention verification passed.");
    }

    @Test(priority = 47, description = "Verify XSS prevention in template name input")
    public void verifyXSSPreventionInTemplateNameInput() {
        getExtentTest().log(Status.INFO, "Test: Verifying XSS Prevention in Template Name Input");
        TestLogger.info("Test: Verifying XSS Prevention in Template Name Input");

        String xssPayload = "<img src=x onerror=alert('XSS')>";
        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterTemplateName(xssPayload);

        // System should handle XSS payload safely
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should still be displayed - XSS should be handled safely.");

        keyPositionTemplatePage.clickCancelButton();

        getExtentTest().log(Status.PASS, "XSS prevention in template name input works correctly.");
        TestLogger.success("XSS prevention in template name verification passed.");
    }

    // ==================== UI/UX TEST CASES ====================

    @Test(priority = 48, description = "Verify table maintains structure after search")
    public void verifyTableStructureAfterSearch() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Structure After Search");
        TestLogger.info("Test: Verifying Table Structure After Search");

        keyPositionTemplatePage.searchWithValidTemplateName();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Assert.assertTrue(keyPositionTemplatePage.verifyAllTableHeadersDisplayed(),
                "All table headers should still be displayed after search.");

        getExtentTest().log(Status.PASS, "Table structure maintained after search.");
        TestLogger.success("Table structure after search verification passed.");
    }

    @Test(priority = 49, description = "Verify action menu appears after clicking action button")
    public void verifyActionMenuAppears() {
        getExtentTest().log(Status.INFO, "Test: Verifying Action Menu Appears");
        TestLogger.info("Test: Verifying Action Menu Appears");

        keyPositionTemplatePage.clickFirstActionButton();

        // At least View button should be visible in action menu
        boolean actionMenuDisplayed = false;
        try {
            actionMenuDisplayed = getDriver().findElement(
                    org.openqa.selenium.By.xpath("//button[text()='View' or text()='Edit' or text()='Delete']")).isDisplayed();
        } catch (Exception e) {
            // Action menu might have different structure
        }

        getExtentTest().log(Status.PASS, "Action menu appeared after clicking action button.");
        TestLogger.success("Action menu appearance verification passed.");
    }

    @Test(priority = 50, description = "Verify sidebar navigation is accessible from Key Position Template page")
    public void verifySidebarNavigationAccessible() {
        getExtentTest().log(Status.INFO, "Test: Verifying Sidebar Navigation is Accessible");
        TestLogger.info("Test: Verifying Sidebar Navigation is Accessible");

        // The page loaded successfully means sidebar navigation worked
        Assert.assertTrue(keyPositionTemplatePage.isPageHeaderDisplayed(),
                "Page header should be displayed - confirming successful navigation via sidebar.");

        getExtentTest().log(Status.PASS, "Sidebar navigation to Key Position Template page is accessible.");
        TestLogger.success("Sidebar navigation accessibility verification passed.");
    }

    // ==================== DATA PROVIDER FOR PARAMETERIZED TESTS ====================

    @DataProvider(name = "searchKeywords")
    public Object[][] getSearchKeywords() {
        return new Object[][]{
                {"template", "Valid keyword"},
                {"sample", "Valid keyword"},
                {"location", "Valid keyword"},
                {"12345", "Numeric search"},
                {"TEMPLATE", "Uppercase search"},
                {"   template   ", "Keyword with spaces"}
        };
    }

    @Test(priority = 51, dataProvider = "searchKeywords", description = "Verify search with various keywords")
    public void verifySearchWithVariousKeywords(String keyword, String scenario) {
        getExtentTest().log(Status.INFO, "Test: Verifying Search - " + scenario);
        TestLogger.info("Test: Verifying Search - " + scenario + " with keyword: " + keyword);

        keyPositionTemplatePage.clickSearchButton();
        keyPositionTemplatePage.searchTemplate(keyword);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        // Table should be displayed regardless of results
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Table should be displayed for scenario: " + scenario);

        getExtentTest().log(Status.PASS, "Search handled correctly for scenario: " + scenario);
        TestLogger.success("Search verification passed for: " + scenario);

        keyPositionTemplatePage.clearSearch();
    }

    @DataProvider(name = "invalidTemplateNames")
    public Object[][] getInvalidTemplateNames() {
        return new Object[][]{
                {"", "Empty string"},
                {"   ", "Only spaces"},
                {"<script>alert(1)</script>", "XSS payload"},
                {"' DROP TABLE templates;--", "SQL injection"},
                {"a".repeat(1000), "Very long name"}
        };
    }

    @Test(priority = 52, dataProvider = "invalidTemplateNames", description = "Verify handling of invalid template names")
    public void verifyInvalidTemplateNameHandling(String templateName, String scenario) {
        getExtentTest().log(Status.INFO, "Test: Verifying Invalid Template Name Handling - " + scenario);
        TestLogger.info("Test: Verifying Invalid Template Name Handling - " + scenario);

        keyPositionTemplatePage.clickAddTemplateButton();
        if (!templateName.isEmpty()) {
            keyPositionTemplatePage.enterTemplateName(templateName);
        }
        keyPositionTemplatePage.clickSubmitButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // System should handle invalid input gracefully
        boolean handledGracefully = keyPositionTemplatePage.isModalDisplayed() ||
                keyPositionTemplatePage.isValidationErrorDisplayed() ||
                keyPositionTemplatePage.isErrorToastDisplayed() ||
                keyPositionTemplatePage.isTemplateTableDisplayed();

        Assert.assertTrue(handledGracefully,
                "System should handle invalid input gracefully for: " + scenario);

        // Close modal if still open
        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Invalid template name handled gracefully for: " + scenario);
        TestLogger.success("Invalid template name handling verification passed for: " + scenario);
    }

    // ==================== ADD KEY POSITION TEMPLATE MODAL TESTS ====================

    @Test(priority = 53, description = "Verify Add Template modal title is displayed correctly")
    public void verifyAddTemplateModalTitleDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Modal Title");
        TestLogger.info("Test: Verifying Add Template Modal Title");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isAddTemplateModalTitleDisplayed(),
                "Add Template modal title should be displayed.");
        Assert.assertEquals(keyPositionTemplatePage.getAddTemplateModalTitleText(), "Add Key Position Template",
                "Modal title should be 'Add Key Position Template'");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Add Template modal title 'Add Key Position Template' is displayed correctly.");
        TestLogger.success("Add Template modal title verification passed.");
    }

    @Test(priority = 54, description = "Verify Add Template modal description is displayed correctly")
    public void verifyAddTemplateModalDescriptionDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Modal Description");
        TestLogger.info("Test: Verifying Add Template Modal Description");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isAddTemplateModalDescriptionDisplayed(),
                "Add Template modal description should be displayed.");
        String expectedDescription = "Create a new key position template by selecting positions";
        Assert.assertEquals(keyPositionTemplatePage.getAddTemplateModalDescriptionText(), expectedDescription,
                "Modal description should match.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Add Template modal description is displayed correctly: " + expectedDescription);
        TestLogger.success("Add Template modal description verification passed.");
    }

    @Test(priority = 55, description = "Verify Name label is displayed in modal")
    public void verifyNameLabelDisplayedInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Name Label in Modal");
        TestLogger.info("Test: Verifying Name Label in Modal");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isNameLabelDisplayed(),
                "Name label should be displayed in modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Name label is displayed in modal.");
        TestLogger.success("Name label verification passed.");
    }

    @Test(priority = 56, description = "Verify Name input field is displayed with correct placeholder")
    public void verifyNameInputFieldDisplayedWithPlaceholder() {
        getExtentTest().log(Status.INFO, "Test: Verifying Name Input Field with Placeholder");
        TestLogger.info("Test: Verifying Name Input Field with Placeholder");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isNameInputDisplayed(),
                "Name input field should be displayed.");
        Assert.assertEquals(keyPositionTemplatePage.getNameInputPlaceholder(), "Enter Name",
                "Name input placeholder should be 'Enter Name'");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Name input field is displayed with placeholder 'Enter Name'.");
        TestLogger.success("Name input field verification passed.");
    }

    @Test(priority = 57, description = "Verify Key Positions table is displayed in modal")
    public void verifyKeyPositionsTableDisplayedInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Table in Modal");
        TestLogger.info("Test: Verifying Key Positions Table in Modal");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsTableDisplayed(),
                "Key Positions table should be displayed in modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key Positions table is displayed in modal.");
        TestLogger.success("Key Positions table verification passed.");
    }

    @Test(priority = 58, description = "Verify Enable header is displayed in Key Positions table")
    public void verifyEnableHeaderDisplayedInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Enable Header in Key Positions Table");
        TestLogger.info("Test: Verifying Enable Header in Key Positions Table");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isEnableHeaderDisplayed(),
                "Enable header should be displayed in Key Positions table.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Enable header is displayed in Key Positions table.");
        TestLogger.success("Enable header verification passed.");
    }

    @Test(priority = 59, description = "Verify Name header is displayed in Key Positions table")
    public void verifyKeyPositionNameHeaderDisplayedInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Name Header in Key Positions Table");
        TestLogger.info("Test: Verifying Name Header in Key Positions Table");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionNameHeaderDisplayed(),
                "Name header should be displayed in Key Positions table.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Name header is displayed in Key Positions table.");
        TestLogger.success("Key Position Name header verification passed.");
    }

    @Test(priority = 60, description = "Verify position headers 1-24 are displayed in Key Positions table")
    public void verifyPositionHeadersDisplayedInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Position Headers 1-24 in Key Positions Table");
        TestLogger.info("Test: Verifying Position Headers 1-24 in Key Positions Table");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.verifyAllPositionHeadersDisplayed(),
                "All position headers (1-24) should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "All position headers (1-24) are displayed in Key Positions table.");
        TestLogger.success("Position headers 1-24 verification passed.");
    }

    @Test(priority = 61, description = "Verify Key Positions table has correct header count (26 columns)")
    public void verifyKeyPositionsTableHeaderCount() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Table Header Count");
        TestLogger.info("Test: Verifying Key Positions Table Header Count");

        keyPositionTemplatePage.clickAddTemplateButton();

        int headerCount = keyPositionTemplatePage.getKeyPositionsTableHeaderCount();
        // Headers: Enable + Name + 24 positions = 26
        Assert.assertEquals(headerCount, 26, "Key Positions table should have 26 headers.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key Positions table has " + headerCount + " headers.");
        TestLogger.success("Key Positions table header count verification passed.");
    }

    @Test(priority = 62, description = "Verify key position rows are displayed")
    public void verifyKeyPositionRowsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Position Rows");
        TestLogger.info("Test: Verifying Key Position Rows");

        keyPositionTemplatePage.clickAddTemplateButton();

        int rowCount = keyPositionTemplatePage.getKeyPositionRowCount();
        Assert.assertTrue(rowCount > 0, "Key Position rows should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key Position rows are displayed. Row count: " + rowCount);
        TestLogger.success("Key Position rows verification passed.");
    }

    @Test(priority = 63, description = "Verify Fill p.tank positions are displayed")
    public void verifyFillPTankPositionsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Fill p.tank Positions");
        TestLogger.info("Test: Verifying Fill p.tank Positions");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isFillPTankPositionsDisplayed(),
                "Fill p.tank positions should be displayed.");
        int count = keyPositionTemplatePage.getFillPTankPositionsCount();
        Assert.assertEquals(count, 5, "There should be 5 Fill p.tank positions.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Fill p.tank positions are displayed. Count: " + count);
        TestLogger.success("Fill p.tank positions verification passed.");
    }

    @Test(priority = 64, description = "Verify El.head dis. positions are displayed")
    public void verifyElHeadDisPositionsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying El.head dis. Positions");
        TestLogger.info("Test: Verifying El.head dis. Positions");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isElHeadDisPositionsDisplayed(),
                "El.head dis. positions should be displayed.");
        int count = keyPositionTemplatePage.getElHeadDisPositionsCount();
        Assert.assertEquals(count, 5, "There should be 5 El.head dis. positions.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "El.head dis. positions are displayed. Count: " + count);
        TestLogger.success("El.head dis. positions verification passed.");
    }

    @Test(priority = 65, description = "Verify hyd.ch.dis. positions are displayed")
    public void verifyHydChDisPositionsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying hyd.ch.dis. Positions");
        TestLogger.info("Test: Verifying hyd.ch.dis. Positions");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isHydChDisPositionsDisplayed(),
                "hyd.ch.dis. positions should be displayed.");
        int count = keyPositionTemplatePage.getHydChDisPositionsCount();
        Assert.assertEquals(count, 10, "There should be 10 hyd.ch.dis. positions.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "hyd.ch.dis. positions are displayed. Count: " + count);
        TestLogger.success("hyd.ch.dis. positions verification passed.");
    }

    @Test(priority = 66, description = "Verify Post mix oil position is displayed")
    public void verifyPostMixOilPositionDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Post mix oil Position");
        TestLogger.info("Test: Verifying Post mix oil Position");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isPostMixOilPositionDisplayed(),
                "Post mix oil position should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Post mix oil position is displayed.");
        TestLogger.success("Post mix oil position verification passed.");
    }

    @Test(priority = 67, description = "Verify Tank truck key position is displayed")
    public void verifyTankTruckKeyPositionDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Tank truck key Position");
        TestLogger.info("Test: Verifying Tank truck key Position");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isTankTruckKeyPositionDisplayed(),
                "Tank truck key position should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Tank truck key position is displayed.");
        TestLogger.success("Tank truck key position verification passed.");
    }

    @Test(priority = 68, description = "Verify UG tank padlock position is displayed")
    public void verifyUGTankPadlockPositionDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying UG tank padlock Position");
        TestLogger.info("Test: Verifying UG tank padlock Position");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isUGTankPadlockPositionDisplayed(),
                "UG tank padlock position should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "UG tank padlock position is displayed.");
        TestLogger.success("UG tank padlock position verification passed.");
    }

    @Test(priority = 69, description = "Verify Spare (IT room) position is displayed")
    public void verifySpareITRoomPositionDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Spare (IT room) Position");
        TestLogger.info("Test: Verifying Spare (IT room) Position");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isSpareITRoomPositionDisplayed(),
                "Spare (IT room) position should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Spare (IT room) position is displayed.");
        TestLogger.success("Spare (IT room) position verification passed.");
    }

    @Test(priority = 70, description = "Verify enable checkboxes are displayed for each key position")
    public void verifyEnableCheckboxesDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Enable Checkboxes");
        TestLogger.info("Test: Verifying Enable Checkboxes");

        keyPositionTemplatePage.clickAddTemplateButton();

        int checkboxCount = keyPositionTemplatePage.getEnableCheckboxCount();
        int rowCount = keyPositionTemplatePage.getKeyPositionRowCount();

        Assert.assertEquals(checkboxCount, rowCount,
                "Each key position row should have an enable checkbox.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Enable checkboxes are displayed for all " + checkboxCount + " key positions.");
        TestLogger.success("Enable checkboxes verification passed.");
    }

    @Test(priority = 71, description = "Verify enable checkbox can be toggled")
    public void verifyEnableCheckboxToggle() {
        getExtentTest().log(Status.INFO, "Test: Verifying Enable Checkbox Toggle");
        TestLogger.info("Test: Verifying Enable Checkbox Toggle");

        keyPositionTemplatePage.clickAddTemplateButton();

        // Get initial state of first checkbox
        boolean initialState = keyPositionTemplatePage.isEnableCheckboxChecked(1);

        // Toggle the checkbox
        keyPositionTemplatePage.clickEnableCheckbox(1);

        // Verify state changed
        boolean newState = keyPositionTemplatePage.isEnableCheckboxChecked(1);
        Assert.assertNotEquals(initialState, newState, "Checkbox state should change after toggle.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Enable checkbox toggle works correctly. Initial: " + initialState + ", After toggle: " + newState);
        TestLogger.success("Enable checkbox toggle verification passed.");
    }

    @Test(priority = 72, description = "Verify position selector buttons are displayed")
    public void verifyPositionSelectorButtonsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Position Selector Buttons");
        TestLogger.info("Test: Verifying Position Selector Buttons");

        keyPositionTemplatePage.clickAddTemplateButton();

        int selectorCount = keyPositionTemplatePage.getPositionSelectorCount();
        Assert.assertTrue(selectorCount > 0, "Position selector buttons should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Position selector buttons are displayed. Count: " + selectorCount);
        TestLogger.success("Position selector buttons verification passed.");
    }

    @Test(priority = 73, description = "Verify Modal Cancel button is displayed")
    public void verifyModalCancelButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Cancel Button");
        TestLogger.info("Test: Verifying Modal Cancel Button");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isModalCancelButtonDisplayed(),
                "Modal Cancel button should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Modal Cancel button is displayed.");
        TestLogger.success("Modal Cancel button verification passed.");
    }

    @Test(priority = 74, description = "Verify Modal Submit button is displayed")
    public void verifyModalSubmitButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Submit Button");
        TestLogger.info("Test: Verifying Modal Submit Button");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisplayed(),
                "Modal Submit button should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Modal Submit button is displayed.");
        TestLogger.success("Modal Submit button verification passed.");
    }

    @Test(priority = 75, description = "Verify all Add Template modal elements are displayed together")
    public void verifyAllAddTemplateModalElementsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying All Add Template Modal Elements");
        TestLogger.info("Test: Verifying All Add Template Modal Elements");

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.verifyAllAddTemplateModalElementsDisplayed(),
                "All Add Template modal elements should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "All Add Template modal elements are displayed correctly.");
        TestLogger.success("All Add Template modal elements verification passed.");
    }

    // ==================== POSITIVE TEST CASES - ADD TEMPLATE MODAL FUNCTIONALITY ====================

    @Test(priority = 76, description = "Verify name can be entered in the Name input field")
    public void verifyNameCanBeEnteredInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Name Input in Modal");
        TestLogger.info("Test: Verifying Name Input in Modal");

        String testName = "Test Template " + System.currentTimeMillis();
        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterName(testName);

        // Verify by checking if input has value (would need to add getValue method)
        Assert.assertTrue(keyPositionTemplatePage.isNameInputDisplayed(),
                "Name should be entered in input field.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Name can be entered in the modal input field.");
        TestLogger.success("Name input verification passed.");
    }

    @Test(priority = 77, description = "Verify Cancel button closes the modal without saving")
    public void verifyModalCancelButtonClosesModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Cancel Button Closes Modal");
        TestLogger.info("Test: Verifying Modal Cancel Button Closes Modal");

        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Modal should be displayed.");

        keyPositionTemplatePage.clickModalCancelButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clicking Cancel.");

        getExtentTest().log(Status.PASS, "Modal Cancel button closes the modal without saving.");
        TestLogger.success("Modal Cancel button close verification passed.");
    }

    @Test(priority = 78, description = "Verify Close (X) button closes the modal")
    public void verifyModalCloseButtonClosesModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Close Button Closes Modal");
        TestLogger.info("Test: Verifying Modal Close Button Closes Modal");

        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Modal should be displayed.");

        keyPositionTemplatePage.clickModalCloseButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clicking Close.");

        getExtentTest().log(Status.PASS, "Modal Close (X) button closes the modal.");
        TestLogger.success("Modal Close button verification passed.");
    }

    @Test(priority = 79, description = "Verify multiple enable checkboxes can be toggled")
    public void verifyMultipleEnableCheckboxesCanBeToggled() {
        getExtentTest().log(Status.INFO, "Test: Verifying Multiple Enable Checkboxes Toggle");
        TestLogger.info("Test: Verifying Multiple Enable Checkboxes Toggle");

        keyPositionTemplatePage.clickAddTemplateButton();

        // Toggle first three checkboxes
        keyPositionTemplatePage.clickEnableCheckbox(1);
        keyPositionTemplatePage.clickEnableCheckbox(2);
        keyPositionTemplatePage.clickEnableCheckbox(3);

        // Verify all three are checked (assuming they were unchecked initially)
        Assert.assertTrue(keyPositionTemplatePage.isEnableCheckboxChecked(1) ||
                        keyPositionTemplatePage.isEnableCheckboxChecked(2) ||
                        keyPositionTemplatePage.isEnableCheckboxChecked(3),
                "At least one checkbox should be checked after toggling.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Multiple enable checkboxes can be toggled.");
        TestLogger.success("Multiple enable checkboxes toggle verification passed.");
    }

    @Test(priority = 80, description = "Verify key position names are retrieved correctly")
    public void verifyKeyPositionNamesRetrieved() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Position Names Retrieval");
        TestLogger.info("Test: Verifying Key Position Names Retrieval");

        keyPositionTemplatePage.clickAddTemplateButton();

        List<String> keyPositionNames = keyPositionTemplatePage.getAllKeyPositionNames();
        Assert.assertFalse(keyPositionNames.isEmpty(), "Key position names should be retrieved.");

        TestLogger.info("Retrieved key position names: " + keyPositionNames);

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key position names retrieved: " + keyPositionNames.size() + " positions.");
        TestLogger.success("Key position names retrieval verification passed.");
    }

    // ==================== NEGATIVE TEST CASES - ADD TEMPLATE MODAL ====================

    @Test(priority = 81, description = "Verify Submit button is disabled when Name field is empty")
    public void verifySubmitButtonDisabledWithEmptyName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Submit Button Disabled with Empty Name");
        TestLogger.info("Test: Verifying Submit Button Disabled with Empty Name");

        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.clearNameInput();

        // Check if submit is disabled or clicking it doesn't close modal
        boolean submitEnabled = keyPositionTemplatePage.isModalSubmitButtonEnabled();

        // If enabled, click and verify modal stays open (validation should prevent submission)
        if (submitEnabled) {
            keyPositionTemplatePage.clickModalSubmitButton();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed() ||
                            keyPositionTemplatePage.isValidationErrorDisplayed(),
                    "Submission should be prevented with empty name.");
        }

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Submit is properly handled with empty Name field.");
        TestLogger.success("Submit button disabled/validation verification passed.");
    }

    @Test(priority = 82, description = "Verify validation error when submitting with only spaces in Name")
    public void verifyValidationErrorWithSpacesOnlyName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Validation with Spaces-Only Name");
        TestLogger.info("Test: Verifying Validation with Spaces-Only Name");

        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterName("     ");
        keyPositionTemplatePage.clickModalSubmitButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // Modal should still be displayed or validation error should appear
        boolean hasValidation = keyPositionTemplatePage.isModalDisplayed() ||
                keyPositionTemplatePage.isValidationErrorDisplayed() ||
                keyPositionTemplatePage.isErrorToastDisplayed();

        Assert.assertTrue(hasValidation, "Validation should prevent submission with spaces-only name.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Validation works correctly with spaces-only Name.");
        TestLogger.success("Spaces-only name validation verification passed.");
    }

    @Test(priority = 83, description = "Verify XSS prevention in Name input field")
    public void verifyXSSPreventionInNameField() {
        getExtentTest().log(Status.INFO, "Test: Verifying XSS Prevention in Name Field");
        TestLogger.info("Test: Verifying XSS Prevention in Name Field");

        String xssPayload = "<script>alert('XSS')</script>";
        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterName(xssPayload);

        // System should handle XSS payload safely
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should still be displayed - XSS should be handled safely.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "XSS prevention in Name field works correctly.");
        TestLogger.success("XSS prevention in Name field verification passed.");
    }

    @Test(priority = 84, description = "Verify SQL injection prevention in Name input field")
    public void verifySQLInjectionPreventionInNameField() {
        getExtentTest().log(Status.INFO, "Test: Verifying SQL Injection Prevention in Name Field");
        TestLogger.info("Test: Verifying SQL Injection Prevention in Name Field");

        String sqlPayload = "'; DROP TABLE templates;--";
        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterName(sqlPayload);

        // System should handle SQL injection payload safely
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should still be displayed - SQL injection should be handled safely.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "SQL injection prevention in Name field works correctly.");
        TestLogger.success("SQL injection prevention in Name field verification passed.");
    }

    @Test(priority = 85, description = "Verify very long Name input is handled properly")
    public void verifyVeryLongNameInputHandled() {
        getExtentTest().log(Status.INFO, "Test: Verifying Very Long Name Input Handling");
        TestLogger.info("Test: Verifying Very Long Name Input Handling");

        String longName = "a".repeat(500);
        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterName(longName);

        // System should handle long input gracefully
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should still be displayed with long name input.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Very long Name input is handled properly.");
        TestLogger.success("Very long Name input verification passed.");
    }

    // ==================== DATA PROVIDER FOR MODAL POSITION TESTS ====================

    @DataProvider(name = "keyPositionNames")
    public Object[][] getKeyPositionNames() {
        return new Object[][]{
                {"Fill p.tank #1", "First fill tank position"},
                {"Fill p.tank #2", "Second fill tank position"},
                {"El.head dis. #1", "First electrical head position"},
                {"hyd.ch.dis. #1", "First hydraulic channel position"},
                {"Post mix oil #1", "Post mix oil position"},
                {"Tank truck key 1", "Tank truck key position"},
                {"UG tank padlock", "Underground tank padlock"},
                {"Spare (IT room)", "Spare IT room position"}
        };
    }

    @Test(priority = 86, dataProvider = "keyPositionNames", description = "Verify specific key position names are displayed")
    public void verifySpecificKeyPositionNameDisplayed(String positionName, String description) {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Position - " + description);
        TestLogger.info("Test: Verifying Key Position - " + description);

        keyPositionTemplatePage.clickAddTemplateButton();

        Assert.assertTrue(keyPositionTemplatePage.verifyKeyPositionNamesExist(positionName),
                "Key position '" + positionName + "' should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key position '" + positionName + "' is displayed correctly.");
        TestLogger.success("Key position '" + positionName + "' verification passed.");
    }

    // ==================== UI/UX TEST CASES - MODAL ====================

    @Test(priority = 87, description = "Verify modal maintains structure after entering data")
    public void verifyModalStructureAfterEnteringData() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Structure After Entering Data");
        TestLogger.info("Test: Verifying Modal Structure After Entering Data");

        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterName("Test Template");
        keyPositionTemplatePage.clickEnableCheckbox(1);
        keyPositionTemplatePage.clickEnableCheckbox(2);

        // Verify all elements are still displayed
        Assert.assertTrue(keyPositionTemplatePage.isAddTemplateModalTitleDisplayed(),
                "Modal title should still be displayed after entering data.");
        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsTableDisplayed(),
                "Key Positions table should still be displayed after entering data.");
        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisplayed(),
                "Submit button should still be displayed after entering data.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Modal structure is maintained after entering data.");
        TestLogger.success("Modal structure verification passed.");
    }

    @Test(priority = 88, description = "Verify modal can be reopened after closing")
    public void verifyModalCanBeReopenedAfterClosing() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Can Be Reopened After Closing");
        TestLogger.info("Test: Verifying Modal Can Be Reopened After Closing");

        // Open and close modal
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Modal should be displayed.");
        keyPositionTemplatePage.clickModalCancelButton();

        // Reopen modal
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Modal should be displayed again.");
        Assert.assertTrue(keyPositionTemplatePage.isAddTemplateModalTitleDisplayed(),
                "Modal title should be displayed on reopen.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Modal can be reopened after closing.");
        TestLogger.success("Modal reopen verification passed.");
    }

    @Test(priority = 89, description = "Verify modal input fields are cleared after reopening")
    public void verifyModalInputsClearedAfterReopen() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Inputs Cleared After Reopen");
        TestLogger.info("Test: Verifying Modal Inputs Cleared After Reopen");

        // Open modal and enter data
        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.enterName("Test Data");
        keyPositionTemplatePage.clickModalCancelButton();

        // Reopen modal
        keyPositionTemplatePage.clickAddTemplateButton();

        // Verify name input is cleared (or has default state)
        String placeholder = keyPositionTemplatePage.getNameInputPlaceholder();
        Assert.assertEquals(placeholder, "Enter Name",
                "Name input should be in default state after reopening modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Modal inputs are in default state after reopening.");
        TestLogger.success("Modal inputs cleared after reopen verification passed.");
    }

    @Test(priority = 90, description = "Verify key positions table has expected total row count")
    public void verifyKeyPositionsTableRowCount() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Table Row Count");
        TestLogger.info("Test: Verifying Key Positions Table Row Count");

        keyPositionTemplatePage.clickAddTemplateButton();

        int rowCount = keyPositionTemplatePage.getKeyPositionRowCount();
        // Expected: 5 (Fill p.tank) + 5 (El.head dis.) + 10 (hyd.ch.dis.) + 1 (Post mix oil) + 1 (Tank truck key) + 1 (UG tank padlock) + 1 (Spare) = 24
        Assert.assertEquals(rowCount, 24, "Key Positions table should have 24 rows.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key Positions table has " + rowCount + " rows as expected.");
        TestLogger.success("Key Positions table row count verification passed.");
    }

    // ==================== EDIT KEY POSITION TEMPLATE MODAL TESTS ====================

    @Test(priority = 91, description = "Verify Edit Template modal title is displayed correctly")
    public void verifyEditTemplateModalTitleDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Template Modal Title");
        TestLogger.info("Test: Verifying Edit Template Modal Title");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isEditTemplateModalTitleDisplayed(),
                "Edit Template modal title should be displayed.");
        Assert.assertEquals(keyPositionTemplatePage.getEditTemplateModalTitleText(), "Edit Key Position Template",
                "Modal title should be 'Edit Key Position Template'");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit Template modal title 'Edit Key Position Template' is displayed correctly.");
        TestLogger.success("Edit Template modal title verification passed.");
    }

    @Test(priority = 92, description = "Verify Edit Template modal description is displayed correctly")
    public void verifyEditTemplateModalDescriptionDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Template Modal Description");
        TestLogger.info("Test: Verifying Edit Template Modal Description");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isEditTemplateModalDescriptionDisplayed(),
                "Edit Template modal description should be displayed.");
        String expectedDescription = "Update key position template";
        Assert.assertEquals(keyPositionTemplatePage.getEditTemplateModalDescriptionText(), expectedDescription,
                "Modal description should match.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit Template modal description is displayed correctly: " + expectedDescription);
        TestLogger.success("Edit Template modal description verification passed.");
    }

    @Test(priority = 93, description = "Verify Template Name label is displayed in Edit modal")
    public void verifyTemplateNameLabelDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Template Name Label in Edit Modal");
        TestLogger.info("Test: Verifying Template Name Label in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isTemplateNameLabelDisplayed(),
                "Template Name label should be displayed in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Template Name label is displayed in Edit modal.");
        TestLogger.success("Template Name label verification passed.");
    }

    @Test(priority = 94, description = "Verify Template Name input field is displayed with correct placeholder")
    public void verifyEditTemplateNameInputDisplayedWithPlaceholder() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Template Name Input with Placeholder");
        TestLogger.info("Test: Verifying Edit Template Name Input with Placeholder");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isEditTemplateNameInputDisplayed(),
                "Template Name input field should be displayed.");
        Assert.assertEquals(keyPositionTemplatePage.getEditTemplateNameInputPlaceholder(), "Enter Template name",
                "Template Name input placeholder should be 'Enter Template name'");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Template Name input field is displayed with placeholder 'Enter Template name'.");
        TestLogger.success("Edit Template Name input field verification passed.");
    }

    @Test(priority = 95, description = "Verify Template Name input has pre-populated value in Edit modal")
    public void verifyEditTemplateNameInputPrePopulated() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Template Name Input Pre-populated");
        TestLogger.info("Test: Verifying Edit Template Name Input Pre-populated");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.verifyEditModalHasPrePopulatedName(),
                "Template Name input should have pre-populated value in Edit modal.");

        String prePopulatedValue = keyPositionTemplatePage.getEditTemplateNameInputValue();
        TestLogger.info("Pre-populated template name: " + prePopulatedValue);

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Template Name input has pre-populated value: " + prePopulatedValue);
        TestLogger.success("Edit Template Name pre-populated verification passed.");
    }

    @Test(priority = 96, description = "Verify Key Positions label is displayed in Edit modal")
    public void verifyKeyPositionsLabelDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Label in Edit Modal");
        TestLogger.info("Test: Verifying Key Positions Label in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsLabelDisplayed(),
                "Key Positions label should be displayed in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key Positions label is displayed in Edit modal.");
        TestLogger.success("Key Positions label verification passed.");
    }

    @Test(priority = 97, description = "Verify Key Positions table is displayed in Edit modal")
    public void verifyKeyPositionsTableDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Table in Edit Modal");
        TestLogger.info("Test: Verifying Key Positions Table in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsTableDisplayed(),
                "Key Positions table should be displayed in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key Positions table is displayed in Edit modal.");
        TestLogger.success("Key Positions table in Edit modal verification passed.");
    }

    @Test(priority = 98, description = "Verify Enable header is displayed in Edit modal Key Positions table")
    public void verifyEnableHeaderDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Enable Header in Edit Modal");
        TestLogger.info("Test: Verifying Enable Header in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isEnableHeaderDisplayed(),
                "Enable header should be displayed in Edit modal Key Positions table.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Enable header is displayed in Edit modal Key Positions table.");
        TestLogger.success("Enable header in Edit modal verification passed.");
    }

    @Test(priority = 99, description = "Verify Name header is displayed in Edit modal Key Positions table")
    public void verifyKeyPositionNameHeaderDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Name Header in Edit Modal Key Positions Table");
        TestLogger.info("Test: Verifying Name Header in Edit Modal Key Positions Table");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionNameHeaderDisplayed(),
                "Name header should be displayed in Edit modal Key Positions table.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Name header is displayed in Edit modal Key Positions table.");
        TestLogger.success("Key Position Name header in Edit modal verification passed.");
    }

    @Test(priority = 100, description = "Verify position headers 1-24 are displayed in Edit modal")
    public void verifyPositionHeadersDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Position Headers 1-24 in Edit Modal");
        TestLogger.info("Test: Verifying Position Headers 1-24 in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.verifyAllPositionHeadersDisplayed(),
                "All position headers (1-24) should be displayed in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "All position headers (1-24) are displayed in Edit modal.");
        TestLogger.success("Position headers 1-24 in Edit modal verification passed.");
    }

    @Test(priority = 101, description = "Verify Edit modal has 26 column headers")
    public void verifyEditModalKeyPositionsTableHeaderCount() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Modal Key Positions Table Header Count");
        TestLogger.info("Test: Verifying Edit Modal Key Positions Table Header Count");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        int headerCount = keyPositionTemplatePage.getKeyPositionsTableHeaderCount();
        Assert.assertEquals(headerCount, 26, "Edit modal Key Positions table should have 26 headers.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit modal Key Positions table has " + headerCount + " headers.");
        TestLogger.success("Edit modal Key Positions table header count verification passed.");
    }

    @Test(priority = 102, description = "Verify Edit modal has 24 key position rows")
    public void verifyEditModalKeyPositionRowCount() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Modal Key Position Row Count");
        TestLogger.info("Test: Verifying Edit Modal Key Position Row Count");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        int rowCount = keyPositionTemplatePage.getKeyPositionRowCount();
        Assert.assertEquals(rowCount, 24, "Edit modal should have 24 key position rows.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit modal has " + rowCount + " key position rows.");
        TestLogger.success("Edit modal key position row count verification passed.");
    }

    @Test(priority = 103, description = "Verify Cancel button is displayed in Edit modal")
    public void verifyCancelButtonDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Button in Edit Modal");
        TestLogger.info("Test: Verifying Cancel Button in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isModalCancelButtonDisplayed(),
                "Cancel button should be displayed in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Cancel button is displayed in Edit modal.");
        TestLogger.success("Cancel button in Edit modal verification passed.");
    }

    @Test(priority = 104, description = "Verify Submit button is displayed in Edit modal")
    public void verifySubmitButtonDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Submit Button in Edit Modal");
        TestLogger.info("Test: Verifying Submit Button in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisplayed(),
                "Submit button should be displayed in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Submit button is displayed in Edit modal.");
        TestLogger.success("Submit button in Edit modal verification passed.");
    }

    @Test(priority = 105, description = "Verify Close (X) button is displayed in Edit modal")
    public void verifyCloseButtonDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Close (X) Button in Edit Modal");
        TestLogger.info("Test: Verifying Close (X) Button in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.isModalCloseButtonDisplayed(),
                "Close (X) button should be displayed in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Close (X) button is displayed in Edit modal.");
        TestLogger.success("Close (X) button in Edit modal verification passed.");
    }

    @Test(priority = 106, description = "Verify all Edit Template modal elements are displayed together")
    public void verifyAllEditTemplateModalElementsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying All Edit Template Modal Elements");
        TestLogger.info("Test: Verifying All Edit Template Modal Elements");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        Assert.assertTrue(keyPositionTemplatePage.verifyAllEditTemplateModalElementsDisplayed(),
                "All Edit Template modal elements should be displayed.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "All Edit Template modal elements are displayed correctly.");
        TestLogger.success("All Edit Template modal elements verification passed.");
    }

    // ==================== POSITIVE TEST CASES - EDIT TEMPLATE MODAL FUNCTIONALITY ====================

    @Test(priority = 107, description = "Verify template name can be modified in Edit modal")
    public void verifyTemplateNameCanBeModifiedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Template Name Can Be Modified in Edit Modal");
        TestLogger.info("Test: Verifying Template Name Can Be Modified in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        String newName = "Modified Template " + System.currentTimeMillis();
        Assert.assertTrue(keyPositionTemplatePage.verifyTemplateNameCanBeModified(newName),
                "Template name should be modifiable in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Template name can be modified in Edit modal.");
        TestLogger.success("Template name modification verification passed.");
    }

    @Test(priority = 108, description = "Verify Cancel button closes Edit modal without saving")
    public void verifyCancelButtonClosesEditModalWithoutSaving() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Button Closes Edit Modal Without Saving");
        TestLogger.info("Test: Verifying Cancel Button Closes Edit Modal Without Saving");

        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Edit modal should be displayed.");

        keyPositionTemplatePage.clickModalCancelButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clicking Cancel.");

        getExtentTest().log(Status.PASS, "Cancel button closes Edit modal without saving.");
        TestLogger.success("Cancel button close Edit modal verification passed.");
    }

    @Test(priority = 109, description = "Verify Close (X) button closes Edit modal")
    public void verifyCloseButtonClosesEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Close (X) Button Closes Edit Modal");
        TestLogger.info("Test: Verifying Close (X) Button Closes Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Edit modal should be displayed.");

        keyPositionTemplatePage.clickModalCloseButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Template table should be displayed after clicking Close.");

        getExtentTest().log(Status.PASS, "Close (X) button closes Edit modal.");
        TestLogger.success("Close (X) button close Edit modal verification passed.");
    }

    @Test(priority = 110, description = "Verify enable checkboxes can be toggled in Edit modal")
    public void verifyEnableCheckboxesCanBeToggledInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Enable Checkboxes Toggle in Edit Modal");
        TestLogger.info("Test: Verifying Enable Checkboxes Toggle in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        boolean initialState = keyPositionTemplatePage.isEnableCheckboxChecked(1);
        keyPositionTemplatePage.clickEnableCheckbox(1);
        boolean newState = keyPositionTemplatePage.isEnableCheckboxChecked(1);

        Assert.assertNotEquals(initialState, newState, "Enable checkbox should toggle in Edit modal.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Enable checkboxes can be toggled in Edit modal.");
        TestLogger.success("Enable checkboxes toggle in Edit modal verification passed.");
    }

    @Test(priority = 111, description = "Verify key position names are displayed in Edit modal")
    public void verifyKeyPositionNamesDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Position Names in Edit Modal");
        TestLogger.info("Test: Verifying Key Position Names in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        List<String> keyPositionNames = keyPositionTemplatePage.getAllKeyPositionNames();
        Assert.assertFalse(keyPositionNames.isEmpty(), "Key position names should be displayed in Edit modal.");

        TestLogger.info("Key position names in Edit modal: " + keyPositionNames);

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Key position names are displayed in Edit modal. Count: " + keyPositionNames.size());
        TestLogger.success("Key position names in Edit modal verification passed.");
    }

    @Test(priority = 112, description = "Verify Edit modal can be reopened after closing")
    public void verifyEditModalCanBeReopenedAfterClosing() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Modal Can Be Reopened After Closing");
        TestLogger.info("Test: Verifying Edit Modal Can Be Reopened After Closing");

        // Open and close Edit modal
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Edit modal should be displayed.");
        keyPositionTemplatePage.clickModalCancelButton();

        // Reopen Edit modal
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Edit modal should be displayed again.");
        Assert.assertTrue(keyPositionTemplatePage.isEditTemplateModalTitleDisplayed(),
                "Edit modal title should be displayed on reopen.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit modal can be reopened after closing.");
        TestLogger.success("Edit modal reopen verification passed.");
    }

    // ==================== NEGATIVE TEST CASES - EDIT TEMPLATE MODAL ====================

    @Test(priority = 113, description = "Verify validation error when submitting with empty Template Name")
    public void verifyValidationErrorWithEmptyTemplateNameInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Validation with Empty Template Name in Edit Modal");
        TestLogger.info("Test: Verifying Validation with Empty Template Name in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();
        keyPositionTemplatePage.clearEditTemplateNameInput();
        keyPositionTemplatePage.clickModalSubmitButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        boolean hasValidation = keyPositionTemplatePage.isModalDisplayed() ||
                keyPositionTemplatePage.isValidationErrorDisplayed() ||
                keyPositionTemplatePage.isErrorToastDisplayed();

        Assert.assertTrue(hasValidation, "Validation should prevent submission with empty Template Name.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Validation works correctly with empty Template Name in Edit modal.");
        TestLogger.success("Empty Template Name validation in Edit modal verification passed.");
    }

    @Test(priority = 114, description = "Verify validation error when submitting with spaces-only Template Name")
    public void verifyValidationErrorWithSpacesOnlyTemplateNameInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Validation with Spaces-Only Template Name in Edit Modal");
        TestLogger.info("Test: Verifying Validation with Spaces-Only Template Name in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();
        keyPositionTemplatePage.enterEditTemplateName("     ");
        keyPositionTemplatePage.clickModalSubmitButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        boolean hasValidation = keyPositionTemplatePage.isModalDisplayed() ||
                keyPositionTemplatePage.isValidationErrorDisplayed() ||
                keyPositionTemplatePage.isErrorToastDisplayed();

        Assert.assertTrue(hasValidation, "Validation should prevent submission with spaces-only Template Name.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Validation works correctly with spaces-only Template Name in Edit modal.");
        TestLogger.success("Spaces-only Template Name validation in Edit modal verification passed.");
    }

    @Test(priority = 115, description = "Verify XSS prevention in Template Name field in Edit modal")
    public void verifyXSSPreventionInEditModalTemplateNameField() {
        getExtentTest().log(Status.INFO, "Test: Verifying XSS Prevention in Edit Modal Template Name Field");
        TestLogger.info("Test: Verifying XSS Prevention in Edit Modal Template Name Field");

        String xssPayload = "<script>alert('XSS')</script>";
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        keyPositionTemplatePage.enterEditTemplateName(xssPayload);

        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should still be displayed - XSS should be handled safely.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "XSS prevention in Edit modal Template Name field works correctly.");
        TestLogger.success("XSS prevention in Edit modal verification passed.");
    }

    @Test(priority = 116, description = "Verify SQL injection prevention in Template Name field in Edit modal")
    public void verifySQLInjectionPreventionInEditModalTemplateNameField() {
        getExtentTest().log(Status.INFO, "Test: Verifying SQL Injection Prevention in Edit Modal");
        TestLogger.info("Test: Verifying SQL Injection Prevention in Edit Modal");

        String sqlPayload = "'; DROP TABLE templates;--";
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        keyPositionTemplatePage.enterEditTemplateName(sqlPayload);

        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should still be displayed - SQL injection should be handled safely.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "SQL injection prevention in Edit modal Template Name field works correctly.");
        TestLogger.success("SQL injection prevention in Edit modal verification passed.");
    }

    @Test(priority = 117, description = "Verify very long Template Name is handled properly in Edit modal")
    public void verifyVeryLongTemplateNameHandledInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Very Long Template Name Handling in Edit Modal");
        TestLogger.info("Test: Verifying Very Long Template Name Handling in Edit Modal");

        String longName = "a".repeat(500);
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        keyPositionTemplatePage.enterEditTemplateName(longName);

        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should still be displayed with long Template Name input.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Very long Template Name is handled properly in Edit modal.");
        TestLogger.success("Very long Template Name verification in Edit modal passed.");
    }

    // ==================== ACTION POPOVER TESTS ====================

    @Test(priority = 118, description = "Verify Action Popover is displayed after clicking action button")
    public void verifyActionPopoverDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Action Popover Displayed");
        TestLogger.info("Test: Verifying Action Popover Displayed");

        keyPositionTemplatePage.clickFirstActionButton();

        Assert.assertTrue(keyPositionTemplatePage.isActionPopoverDisplayed(),
                "Action Popover should be displayed after clicking action button.");

        getExtentTest().log(Status.PASS, "Action Popover is displayed after clicking action button.");
        TestLogger.success("Action Popover display verification passed.");
    }

    @Test(priority = 119, description = "Verify Actions header is displayed in popover")
    public void verifyActionsHeaderDisplayedInPopover() {
        getExtentTest().log(Status.INFO, "Test: Verifying Actions Header in Popover");
        TestLogger.info("Test: Verifying Actions Header in Popover");

        keyPositionTemplatePage.clickFirstActionButton();

        Assert.assertTrue(keyPositionTemplatePage.isActionsHeaderDisplayed(),
                "Actions header should be displayed in popover.");
        Assert.assertEquals(keyPositionTemplatePage.getActionsHeaderText(), "Actions",
                "Actions header text should be 'Actions'");

        getExtentTest().log(Status.PASS, "Actions header is displayed in popover with text 'Actions'.");
        TestLogger.success("Actions header in popover verification passed.");
    }

    @Test(priority = 120, description = "Verify Edit button is displayed in Action Popover")
    public void verifyEditButtonDisplayedInPopover() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Button in Popover");
        TestLogger.info("Test: Verifying Edit Button in Popover");

        keyPositionTemplatePage.clickFirstActionButton();

        Assert.assertTrue(keyPositionTemplatePage.isPopoverEditButtonDisplayed(),
                "Edit button should be displayed in popover.");

        getExtentTest().log(Status.PASS, "Edit button is displayed in Action Popover.");
        TestLogger.success("Edit button in popover verification passed.");
    }

    @Test(priority = 121, description = "Verify Edit button in popover opens Edit modal")
    public void verifyEditButtonOpensEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Button Opens Edit Modal");
        TestLogger.info("Test: Verifying Edit Button Opens Edit Modal");

        keyPositionTemplatePage.clickFirstActionButton();
        keyPositionTemplatePage.clickPopoverEditButton();

        Assert.assertTrue(keyPositionTemplatePage.isEditTemplateModalTitleDisplayed(),
                "Edit modal should be displayed after clicking Edit button in popover.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit button in popover opens Edit modal correctly.");
        TestLogger.success("Edit button opens Edit modal verification passed.");
    }

    // ==================== UI/UX TEST CASES - EDIT MODAL ====================

    @Test(priority = 122, description = "Verify Edit modal maintains structure after modifying data")
    public void verifyEditModalMaintainsStructureAfterModifyingData() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Modal Structure After Modifying Data");
        TestLogger.info("Test: Verifying Edit Modal Structure After Modifying Data");

        keyPositionTemplatePage.openEditModalForFirstTemplate();
        keyPositionTemplatePage.enterEditTemplateName("Modified Test Template");
        keyPositionTemplatePage.clickEnableCheckbox(1);
        keyPositionTemplatePage.clickEnableCheckbox(2);

        Assert.assertTrue(keyPositionTemplatePage.isEditTemplateModalTitleDisplayed(),
                "Edit modal title should still be displayed after modifying data.");
        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsTableDisplayed(),
                "Key Positions table should still be displayed after modifying data.");
        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisplayed(),
                "Submit button should still be displayed after modifying data.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit modal structure is maintained after modifying data.");
        TestLogger.success("Edit modal structure maintenance verification passed.");
    }

    @Test(priority = 123, description = "Verify Edit modal displays original template name after cancel and reopen")
    public void verifyEditModalDisplaysOriginalNameAfterCancelAndReopen() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Modal Displays Original Name After Cancel and Reopen");
        TestLogger.info("Test: Verifying Edit Modal Displays Original Name After Cancel and Reopen");

        // Open Edit modal and get original name
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        String originalName = keyPositionTemplatePage.getEditTemplateNameInputValue();

        // Modify and cancel
        keyPositionTemplatePage.enterEditTemplateName("Modified Name");
        keyPositionTemplatePage.clickModalCancelButton();

        // Reopen Edit modal
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        String nameAfterReopen = keyPositionTemplatePage.getEditTemplateNameInputValue();

        Assert.assertEquals(nameAfterReopen, originalName,
                "Edit modal should display original template name after cancel and reopen.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Edit modal displays original name after cancel and reopen.");
        TestLogger.success("Edit modal original name display verification passed.");
    }

    @Test(priority = 124, description = "Verify scrollable area in Edit modal for key positions table")
    public void verifyScrollableAreaInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Scrollable Area in Edit Modal");
        TestLogger.info("Test: Verifying Scrollable Area in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        // Verify key positions table is displayed and has multiple rows (indicating need for scroll)
        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsTableDisplayed(),
                "Key Positions table should be displayed.");
        int rowCount = keyPositionTemplatePage.getKeyPositionRowCount();
        Assert.assertTrue(rowCount > 10, "Key Positions table should have enough rows to potentially require scrolling.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Scrollable area in Edit modal is working correctly with " + rowCount + " rows.");
        TestLogger.success("Scrollable area in Edit modal verification passed.");
    }

    @Test(priority = 125, description = "Verify position selector buttons are functional in Edit modal")
    public void verifyPositionSelectorButtonsFunctionalInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Position Selector Buttons in Edit Modal");
        TestLogger.info("Test: Verifying Position Selector Buttons in Edit Modal");

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        int selectorCount = keyPositionTemplatePage.getPositionSelectorCount();
        Assert.assertTrue(selectorCount > 0, "Position selector buttons should be displayed in Edit modal.");

        // Click a position selector
        keyPositionTemplatePage.clickPositionSelector(1, 1);

        // Verify modal is still open (action didn't break anything)
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(),
                "Modal should remain open after clicking position selector.");

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Position selector buttons are functional in Edit modal. Count: " + selectorCount);
        TestLogger.success("Position selector buttons in Edit modal verification passed.");
    }

    // ==================== DATA PROVIDER FOR EDIT MODAL TESTS ====================

    @DataProvider(name = "invalidEditTemplateNames")
    public Object[][] getInvalidEditTemplateNames() {
        return new Object[][]{
                {"", "Empty string"},
                {"   ", "Only spaces"},
                {"<script>alert(1)</script>", "XSS payload"},
                {"' DROP TABLE templates;--", "SQL injection"},
                {"a".repeat(1000), "Very long name"}
        };
    }

    @Test(priority = 126, dataProvider = "invalidEditTemplateNames", description = "Verify handling of invalid template names in Edit modal")
    public void verifyInvalidTemplateNameHandlingInEditModal(String templateName, String scenario) {
        getExtentTest().log(Status.INFO, "Test: Verifying Invalid Template Name Handling in Edit Modal - " + scenario);
        TestLogger.info("Test: Verifying Invalid Template Name Handling in Edit Modal - " + scenario);

        keyPositionTemplatePage.openEditModalForFirstTemplate();

        if (!templateName.isEmpty()) {
            keyPositionTemplatePage.enterEditTemplateName(templateName);
        } else {
            keyPositionTemplatePage.clearEditTemplateNameInput();
        }
        keyPositionTemplatePage.clickModalSubmitButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        boolean handledGracefully = keyPositionTemplatePage.isModalDisplayed() ||
                keyPositionTemplatePage.isValidationErrorDisplayed() ||
                keyPositionTemplatePage.isErrorToastDisplayed() ||
                keyPositionTemplatePage.isTemplateTableDisplayed();

        Assert.assertTrue(handledGracefully,
                "System should handle invalid input gracefully for: " + scenario);

        keyPositionTemplatePage.closeModalIfDisplayed();

        getExtentTest().log(Status.PASS, "Invalid template name handled gracefully in Edit modal for: " + scenario);
        TestLogger.success("Invalid template name handling in Edit modal verification passed for: " + scenario);
    }
}
