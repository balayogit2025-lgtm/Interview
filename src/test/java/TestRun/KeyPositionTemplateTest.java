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


    @Test(priority = 1, description = "Verify page header 'Key Position Template' is displayed")
    public void verifyPageHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Header");
        TestLogger.info("Test: Verifying Page Header");
        Assert.assertTrue(keyPositionTemplatePage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(keyPositionTemplatePage.getPageHeaderText(), "Key Position Template",
                "Page header text should be 'Key Position Template'");
        getExtentTest().log(Status.PASS, "Page header 'Key Position Template' is displayed correctly.");
        TestLogger.success("Page header verification passed.");
    }

    @Test(priority = 2, description = "Verify page sub-header text is displayed")
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

    @Test(priority = 3, description = "Verify search button is displayed")
    public void verifySearchButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Button");
        TestLogger.info("Test: Verifying Search Button");
        Assert.assertTrue(keyPositionTemplatePage.isSearchButtonDisplayed(), "Search button should be displayed.");
        getExtentTest().log(Status.PASS, "Search button is displayed.");
        TestLogger.success("Search button verification passed.");
    }

    @Test(priority = 4, description = "Verify Add Template button is displayed")
    public void verifyAddTemplateButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Button");
        TestLogger.info("Test: Verifying Add Template Button");
        Assert.assertTrue(keyPositionTemplatePage.isAddTemplateButtonDisplayed(),
                "Add Template button should be displayed.");
        getExtentTest().log(Status.PASS, "Add Template button is displayed.");
        TestLogger.success("Add Template button verification passed.");
    }

    @Test(priority = 5, description = "Verify Key Position Template table is displayed")
    public void verifyTemplateTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Template Table");
        TestLogger.info("Test: Verifying Template Table");
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(),
                "Key Position Template table should be displayed.");
        getExtentTest().log(Status.PASS, "Key Position Template table is displayed.");
        TestLogger.success("Template table verification passed.");
    }

    @Test(priority = 6, description = "Verify all column headers are displayed with correct text")
    public void verifyTableColumnHeaders() {
        getExtentTest().log(Status.INFO, "Test: Verifying Table Column Headers");
        TestLogger.info("Test: Verifying Table Column Headers");
        List<String> expectedHeaders = Arrays.asList("S.NO", "NAME", "CREATED BY", "UPDATED BY", "ACTION");
        List<String> actualHeaders = keyPositionTemplatePage.getColumnHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "All column headers are displayed correctly: " + actualHeaders);
        TestLogger.success("Table column headers verification passed.");
    }

    @Test(priority = 7, description = "Verify pagination info is displayed")
    public void verifyPaginationInfoDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Info");
        TestLogger.info("Test: Verifying Pagination Info");
        Assert.assertTrue(keyPositionTemplatePage.isPaginationInfoDisplayed(),
                "Pagination info should be displayed.");
        String paginationText = keyPositionTemplatePage.getPaginationInfoText();
        Assert.assertTrue(paginationText.contains("Showing"), "Pagination text should contain 'Showing'");
        getExtentTest().log(Status.PASS, "Pagination info is displayed: " + paginationText);
        TestLogger.success("Pagination info verification passed.");
    }

    @Test(priority = 8, description = "Verify pagination controls are displayed")
    public void verifyPaginationControlsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination Controls");
        TestLogger.info("Test: Verifying Pagination Controls");
        Assert.assertTrue(keyPositionTemplatePage.verifyAllPaginationControlsDisplayed(),
                "All pagination controls should be displayed.");
        getExtentTest().log(Status.PASS, "All pagination controls are displayed.");
        TestLogger.success("Pagination controls verification passed.");
    }

    @Test(priority = 9, description = "Verify pagination navigation functionality")
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

    @Test(priority = 10, description = "Verify search functionality with valid template name")
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

    @Test(priority = 11, description = "Verify search with non-existent template name")
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

    @Test(priority = 12, description = "Verify Add Template button opens modal")
    public void verifyAddTemplateOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Opens Modal");
        TestLogger.info("Test: Verifying Add Template Opens Modal");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Add Template modal should be displayed.");
        getExtentTest().log(Status.PASS, "Add Template modal is displayed.");
        TestLogger.success("Add Template modal verification passed.");
    }

    @Test(priority = 13, description = "Verify Add Template modal displays template name")
    public void verifyAddTemplateModalInputField() {
        getExtentTest().log(Status.INFO, "Test: Verifying Add Template Modal Input Field");
        TestLogger.info("Test: Verifying Add Template Modal Input Field");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateNameInputDisplayed(), "Template name input should be displayed.");
        getExtentTest().log(Status.PASS, "Template name input is displayed in Add Template modal.");
        TestLogger.success("Add Template modal input field verification passed.");
    }

    @Test(priority = 14, description = "Verify Cancel button in Add Template modal closes the modal")
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

    @Test(priority = 15, description = "Verify Close button in Add Template modal closes the modal")
    public void verifyCloseButtonInAddTemplateModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Close Button in Add Template Modal");
        TestLogger.info("Test: Verifying Close Button in Add Template Modal");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Add Template modal should be displayed.");
        keyPositionTemplatePage.clickModalCloseButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(), "Template table should be displayed after clicking Close.");
        getExtentTest().log(Status.PASS, "Close button closed Add Template modal successfully.");
        TestLogger.success("Close button in Add Template modal verification passed.");
    }
    @Test(priority = 16, description = "Verify page info updates after navigation")
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
    @Test(priority = 17, description = "Verify Key Positions table is displayed in modal")
    public void verifyKeyPositionsTableDisplayedInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Table in Modal");
        TestLogger.info("Test: Verifying Key Positions Table in Modal");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsTableDisplayed(), "Key Positions table should be displayed in modal.");
        getExtentTest().log(Status.PASS, "Key Positions table is displayed in modal.");
        TestLogger.success("Key Positions table verification passed.");
    }
    @Test(priority =18, description = "Verify position headers 1-24 are displayed in Key Positions table")
    public void verifyPositionHeadersDisplayedInModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Position Headers 1-24 in Key Positions Table");
        TestLogger.info("Test: Verifying Position Headers 1-24 in Key Positions Table");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.verifyAllPositionHeadersDisplayed(), "All position headers (1-24) should be displayed.");
        getExtentTest().log(Status.PASS, "All position headers (1-24) are displayed in Key Positions table.");
        TestLogger.success("Position headers 1-24 verification passed.");
    }

    @Test(priority = 19, description = "Verify Key Positions table has correct header count (24 columns)")
    public void verifyKeyPositionsTableHeaderCount() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Table Header Count");
        TestLogger.info("Test: Verifying Key Positions Table Header Count");
        keyPositionTemplatePage.clickAddTemplateButton();
        int headerCount = keyPositionTemplatePage.getKeyPositionsTableHeaderCount();
        Assert.assertEquals(headerCount, 26, "Key Positions table should have 26 headers.");
        getExtentTest().log(Status.PASS, "Key Positions table has " + headerCount + " headers.");
        TestLogger.success("Key Positions table header count verification passed.");
    }

    @Test(priority = 20, description = "Verify enable checkboxes are displayed for each key position")
    public void verifyEnableCheckboxesDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Enable Checkboxes");
        TestLogger.info("Test: Verifying Enable Checkboxes");
        keyPositionTemplatePage.clickAddTemplateButton();
         try {
           Thread.sleep(1000);
         } catch (InterruptedException e) {

         }
        Assert.assertTrue(keyPositionTemplatePage.isKeyboxesCheckboxesDisplayed() ,"Enabled keyboxes are not displayed");
        getExtentTest().log(Status.PASS, "Enable checkboxes are displayed ");
        TestLogger.success("Enable checkboxes verification passed");
    }
    @Test(priority = 21, description = "Verify the key checkbox can be disabled")
    public void verifytheKeyCheckboxCanBeDisabled() {
        getExtentTest().log(Status.INFO, "Test: Verify the key checkbox can be disabled");
        TestLogger.info("Test: Verify the key checkbox can be disabled");
        keyPositionTemplatePage.clickAddTemplateButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        keyPositionTemplatePage.clickEnableCheckbox(1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionSelectionDisabled(1,1,24),"The Key position selection is enabled for that row");
        getExtentTest().log(Status.PASS, "Enable checkbox toggle works correctly");
        TestLogger.success("Enable checkbox toggle verification passed.");
    }
    @Test(priority = 22, description = "Verify position selector buttons are displayed")
    public void verifyPositionSelectorButtonsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Position Selector Buttons");
        TestLogger.info("Test: Verifying Position Selector Buttons");
        keyPositionTemplatePage.clickAddTemplateButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        Assert.assertTrue(keyPositionTemplatePage.isPositionSelectorButtonsDisplayed(), "Position selector buttons should be displayed.");
        getExtentTest().log(Status.PASS, "Position selector buttons are displayed. Count");
        TestLogger.success("Position selector buttons verification passed.");
    }
    @Test(priority = 23, description = "Verify all Add Template modal elements are displayed together")
    public void verifyAllAddTemplateModalElementsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying All Add Template Modal Elements");
        TestLogger.info("Test: Verifying All Add Template Modal Elements");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.verifyAllAddTemplateModalElementsDisplayed(), "All Add Template modal elements should be displayed.");
        keyPositionTemplatePage.closeModalIfDisplayed();
        getExtentTest().log(Status.PASS, "All Add Template modal elements are displayed correctly.");
        TestLogger.success("All Add Template modal elements verification passed.");
    }
    @Test(priority = 24, description = "Verify Close (X) button closes the modal")
    public void verifyModalCloseButtonClosesModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Modal Close Button Closes Modal");
        TestLogger.info("Test: Verifying Modal Close Button Closes Modal");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Modal should be displayed.");
        keyPositionTemplatePage.clickModalCloseButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(), "Template table should be displayed after clicking Close.");
        getExtentTest().log(Status.PASS, "Modal Close (X) button closes the modal.");
        TestLogger.success("Modal Close button verification passed.");
    }
    @Test(priority =25, description = "Verify multiple key boxes are disabled")
    public void verifyMultipleKeyboxDisabled() {
        getExtentTest().log(Status.INFO, "Test: Verifying multiple key boxes are disabled");
        TestLogger.info("Test: Verifying multiple key boxes are disabled");
        keyPositionTemplatePage.clickAddTemplateButton();
        keyPositionTemplatePage.clickEnableCheckbox(1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        keyPositionTemplatePage.clickEnableCheckbox(2);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        keyPositionTemplatePage.clickEnableCheckbox(3);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Assert.assertTrue(keyPositionTemplatePage.isEnableCheckboxChecked(1) ||
                        keyPositionTemplatePage.isEnableCheckboxChecked(2) ||
                        keyPositionTemplatePage.isEnableCheckboxChecked(3),
                "All the checkboxes are enabled");
        getExtentTest().log(Status.PASS, "Multiple key boxes can be disabled");
        TestLogger.success("Multiple key boxes disable verification passed.");
    }
    @Test(priority = 26, description = "Add new key position template with valid details")
    public void AddNewKeyPositionTemplate() {
        getExtentTest().log(Status.INFO, "Test: Add new key position template with valid details");
        TestLogger.info("Test: Add new key position template with valid details");
        keyPositionTemplatePage.addNewTemplate();
        keyPositionTemplatePage.clickModalSubmitButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(), "A new key position template is not created");
        getExtentTest().log(Status.PASS, "A new  key position template is created successfully");
        TestLogger.success("Creation of new key position template is verified");
    }
    @Test(priority = 27, description = "Verify Submit button is disabled when Name field & key positions are empty")
    public void verifySubmitButtonDisabledWithEmptyNameAndKeyPosition() {
        getExtentTest().log(Status.INFO, "Test: Verifying Submit Button Disabled with Empty Name");
        TestLogger.info("Test: Verifying Submit Button Disabled with Empty Name");
        keyPositionTemplatePage.clickAddTemplateButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisabled(), "Submission should be prevented with empty name");
        getExtentTest().log(Status.PASS, "Submit is properly handled with empty Name & key position field.");
        TestLogger.success("Submit button disabled verification passed.");
    }
    @Test(priority = 28, description = "Verify Submit button is disabled when Name is empty")
    public void verifySubmitButtonDisabledWithEmptyName() {
        getExtentTest().log(Status.INFO, "Test: Verifying Submit Button Disabled with Empty Name");
        TestLogger.info("Test: Verifying Submit Button Disabled with Empty Name");
        keyPositionTemplatePage.addNewTemplatewithemptyname();
        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisabled(), "Submit button is enabled");
        getExtentTest().log(Status.PASS, "Submit is properly handled with empty Name field.");
        TestLogger.success("Submit button disabled verification passed.");
    }
    @Test(priority = 29, description = "Verify Submit button is disabled when key positions is empty")
    public void verifySubmitButtonDisabledWithEmptyKeyPosition() {
        getExtentTest().log(Status.INFO, "Test: Verifying Submit Button Disabled with Empty key position");
        TestLogger.info("Test: Verifying Submit Button Disabled with Empty key position");
        keyPositionTemplatePage.addNewTemplatewithemptyKeyposition();
        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisabled(), "Submit button is enabled");
        getExtentTest().log(Status.PASS, "Submit is properly handled with empty key position");
        TestLogger.success("Submit button disabled verification passed.");
    }
    @Test(priority = 30, description = "Verify Submit button is disabled when Name is created using spaces")
    public void verifySubmitButtonwhenNameIsCreatedUsingSpaces() {
        getExtentTest().log(Status.INFO, "Test: Verifying Submit Button Disabled when Name is created using spaces");
        TestLogger.info("Test: Verifying Submit Button Disabled when Name is created using spaces");
        keyPositionTemplatePage.addNewTemplateUsingSpacesInName();
        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisabled(), "Submit button is enabled");
        getExtentTest().log(Status.PASS, "Submit is properly handled when Name is created using spaces.");
        TestLogger.success("Submit button disabled verification passed.");
    }
    @Test(priority = 31, description = "Verify Submit button is disabled when Name is created using special characters")
    public void verifySubmitButtonWhenNameIsCreatedUsingSpecialCharacters() {
        getExtentTest().log(Status.INFO, "Test: Verifying Submit Button Disabled when Name is created using special characters");
        TestLogger.info("Test: Verifying Submit Button Disabled when Name is created using special characters");
        keyPositionTemplatePage.addNewTemplateUsingSpecialCharactersInName();
        Assert.assertTrue(keyPositionTemplatePage.isModalSubmitButtonDisabled(), "Submit button is enabled");
        getExtentTest().log(Status.PASS, "Submit is properly handled when Name is created using special characters");
        TestLogger.success("Submit button disabled verification passed.");
    }
    @Test(priority = 32, description = "Verify the duplicate template creation")
    public void verifyDuplicateTemplateCreation() {
        getExtentTest().log(Status.INFO, "Test: Verify the duplicate template creation");
        TestLogger.info("Test: Verify the duplicate template creation");
        keyPositionTemplatePage.addDuplicateTemplate();
        keyPositionTemplatePage.clickModalSubmitButton();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Duplicate creation of template is allowed");
        getExtentTest().log(Status.PASS, "Duplicate template creation is verified");
        TestLogger.success("verification of Duplicate template creation passed");
    }
    @Test(priority = 33, description = "Verify the error message when less than 8 keyboxes are enabled")
    public void verifyErrorMessageWhenMinimumKeyboxesEnabled() {
        getExtentTest().log(Status.INFO, "Test: Verify the error message when less than 8 keyboxes are enabled");
        TestLogger.info("Test: Verify the error message when less than 8 keyboxes are enabled");
        keyPositionTemplatePage.selectallthekeypositions();
        keyPositionTemplatePage.disablethekeyboxescheckbox();
        Assert.assertTrue(keyPositionTemplatePage.isErrorMessageOfLessKeyboxedEnabledDisplayed(), "No Error Message");
        getExtentTest().log(Status.PASS, "Error Message is displayed when less than eight keyboxes are enabled");
        TestLogger.success("Verification of error message when minimum key boxes enabled");
    }
    @Test(priority = 34, description = "Verify Edit action opens edit modal")
    public void verifyEditTemplateOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Template Opens Modal");
        TestLogger.info("Test: Verifying Edit Template Opens Modal");
        keyPositionTemplatePage.clickFirstActionButton().clickEditButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        Assert.assertTrue(keyPositionTemplatePage.isEditModalLabelDisplayed(), "Edit modal should be displayed.");
        getExtentTest().log(Status.PASS, "Edit template modal is displayed.");
        TestLogger.success("Edit template modal verification passed.");
    }
    @Test(priority = 35, description = "Verify Cancel button closes edit modal")
    public void verifyCancelButtonClosesEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Cancel Button Closes Edit Modal");
        TestLogger.info("Test: Verifying Cancel Button Closes Edit Modal");
        keyPositionTemplatePage.editFirstTemplate();
        keyPositionTemplatePage.clickCancelButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(), "Template table should be displayed after clicking Cancel.");
        getExtentTest().log(Status.PASS, "Cancel button closed edit modal successfully.");
        TestLogger.success("Cancel button close edit modal verification passed.");
    }
    @Test(priority = 36, description = "Verify Key Positions table is displayed in Edit modal")
    public void verifyKeyPositionsTableDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Positions Table in Edit Modal");
        TestLogger.info("Test: Verifying Key Positions Table in Edit Modal");
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionsTableDisplayed(), "Key Positions table should be displayed in Edit modal.");
        getExtentTest().log(Status.PASS, "Key Positions table is displayed in Edit modal.");
        TestLogger.success("Key Positions table in Edit modal verification passed.");
    }
    @Test(priority =37 , description = "Verify position headers 1-24 are displayed in Edit modal")
    public void verifyPositionHeadersDisplayedInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Position Headers 1-24 in Edit Modal");
        TestLogger.info("Test: Verifying Position Headers 1-24 in Edit Modal");
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.verifyAllPositionHeadersDisplayed(), "All position headers (1-24) should be displayed in Edit modal.");
        getExtentTest().log(Status.PASS, "All position headers (1-24) are displayed in Edit modal.");
        TestLogger.success("Position headers 1-24 in Edit modal verification passed.");
    }
    @Test(priority = 38, description = "Verify all Edit Template modal elements are displayed together")
    public void verifyAllEditTemplateModalElementsDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying All Edit Template Modal Elements");
        TestLogger.info("Test: Verifying All Edit Template Modal Elements");
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.verifyAllEditTemplateModalElementsDisplayed(), "All Edit Template modal elements should be displayed.");
        keyPositionTemplatePage.closeModalIfDisplayed();
        getExtentTest().log(Status.PASS, "All Edit Template modal elements are displayed correctly.");
        TestLogger.success("All Edit Template modal elements verification passed.");
    }
    @Test(priority = 39, description = "Verify Close (X) button closes Edit modal")
    public void verifyCloseButtonClosesEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Close (X) Button Closes Edit Modal");
        TestLogger.info("Test: Verifying Close (X) Button Closes Edit Modal");
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        Assert.assertTrue(keyPositionTemplatePage.isModalDisplayed(), "Edit modal should be displayed.");
        keyPositionTemplatePage.clickModalCloseButton();
        Assert.assertTrue(keyPositionTemplatePage.isTemplateTableDisplayed(), "Template table should be displayed after clicking Close.");
        getExtentTest().log(Status.PASS, "Close (X) button closes Edit modal.");
        TestLogger.success("Close (X) button close Edit modal verification passed.");
    }
    @Test(priority = 40, description = "Verify enable checkboxes can be Disabled in Edit modal")
    public void verifyEnableCheckboxesCanBeDisabledInEditModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Enable Checkboxes disabled in Edit Modal");
        TestLogger.info("Test: Verifying Enable Checkboxes disabled in Edit Modal");
        keyPositionTemplatePage.openEditModalForFirstTemplate();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        keyPositionTemplatePage.clickEnableCheckbox(1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        Assert.assertTrue(keyPositionTemplatePage.isKeyPositionSelectionDisabled(1,1,24),"The Key position selection is enabled for that row");
        getExtentTest().log(Status.PASS, "Enable checkboxes can be toggled in Edit modal.");
        TestLogger.success("Enable checkboxes toggle in Edit modal verification passed.");
    }
}
//keypositiontemplatetest