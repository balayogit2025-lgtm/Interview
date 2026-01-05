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

import java.util.Arrays;
import java.util.List;


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
        keyboxPage.clickFilterButton().clickTypeDropdown().clickDateRangeOptionInTypeDropdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickFromDate().clickPreviousMonth();
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
       }
        keyboxPage.clickTheExactFromDate();
        keyboxPage.clickToDate();
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
       }
        keyboxPage.clickExactToDate();
         keyboxPage.clickApplyFilterButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        getExtentTest().log(Status.INFO, "Setup complete. Navigated to Key Box Page.");
    }
    @Test(priority = 1, description = "Verify page header 'Key Box' is displayed correctly.")
    public void verifyPageHeaderDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Page Header");
        TestLogger.info("Test: Verifying Page Header");
        Assert.assertTrue(keyboxPage.isPageHeaderDisplayed(), "Page header should be displayed.");
        Assert.assertEquals(keyboxPage.getPageHeaderText(), "Key Box", "Page header text should be 'Key Box'");
        getExtentTest().log(Status.PASS, "Page header 'Key Box' is displayed correctly.");
        TestLogger.success("Page header verification passed.");
    }
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
    @Test(priority = 3, description = "Verify search button is displayed.")
    public void verifySearchButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Button");
        TestLogger.info("Test: Verifying Search Button");
        Assert.assertTrue(keyboxPage.isSearchIconDisplayed(), "Search button should be displayed.");
        getExtentTest().log(Status.PASS, "Search button is displayed.");
        TestLogger.success("Search button verification passed.");
    }
    @Test(priority = 4, description = "Verify filter button is displayed.")
    public void verifyFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Button");
        TestLogger.info("Test: Verifying Filter Button");
        Assert.assertTrue(keyboxPage.isFilterButtonDisplayed(), "Filter button should be displayed.");
        getExtentTest().log(Status.PASS, "Filter button is displayed.");
        TestLogger.success("Filter button verification passed.");
    }
    @Test(priority = 5, description = "Verify Key Box table is displayed.")
    public void verifyKeyboxTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Key Box Table");
        TestLogger.info("Test: Verifying Key Box Table");
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Key Box table should be displayed.");
        getExtentTest().log(Status.PASS, "Key Box table is displayed.");
        TestLogger.success("Key Box table verification passed.");
    }
    @Test(priority = 6, description = "Verify the column header names in Key box list screen")
    public void verifyTableHeaders() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in KEY BOX list screen");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        List<String> expectedHeaders = Arrays.asList("S.NO", "SERIAL NUMBER", "LOCATION", "RO TYPE","LAST COMMUNICATED DATE & TIME","REGISTERED BY","UPDATED BY","STATUS","ACTION");
        List<String> actualHeaders = keyboxPage.getColumnHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }

    @Test(priority = 7, description = "Verify search functionality with valid serial number.")
    public void verifySearchFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search Functionality");
        TestLogger.info("Test: Verifying Search Functionality");
        keyboxPage.SearchaValidValue();
        int rowCount = keyboxPage.getTableRowCount();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid user name");
    }

    @Test(priority = 8, description = "Verify search with non-existent serial number returns no results.")
    public void verifySearchWithNonExistentSerialNumber() {
        getExtentTest().log(Status.INFO, "Test: Verifying Search with Non-Existent Serial Number");
        TestLogger.info("Test: Verifying Search with Non-Existent Serial Number");
        keyboxPage.SearchaInValidValue();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        int rowCount = keyboxPage.getTableRowCount();
        Assert.assertTrue(rowCount == 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid user name");
    }

    @Test(priority = 9, description = "Verify View action opens key box details modal.")
    public void verifyViewKeyboxDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Key Box Details");
        TestLogger.info("Test: Verifying View Key Box Details");
        keyboxPage.clickActionButton().clickViewButton();
        Assert.assertTrue(keyboxPage.isModalDisplayed());
        getExtentTest().log(Status.PASS, "Verfied the view modal displayed");
    }

    @Test(priority = 10, description = "Verify Edit action opens edit modal.")
    public void verifyEditKeyboxOpensModal() {
        getExtentTest().log(Status.INFO, "Test: Verifying Edit Key Box Opens Modal");
        TestLogger.info("Test: Verifying Edit Key Box Opens Modal");
        keyboxPage.clickActionButton().clickEditButton();
        Assert.assertTrue(keyboxPage.isModalDisplayed());
        getExtentTest().log(Status.PASS, "Verfied the edit modal displayed");
    }

    @Test(priority = 11, description = "Verify view modal can be closed using close button")
    public void verifyViewModalCloseButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying view Modal can be closed using Close Button");
        TestLogger.info("Test: Verifying view Modal can be closed using Close Button");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickActionButton().clickViewButton().clickCloseButton();
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(),"View modal is not closed");
        getExtentTest().log(Status.PASS, "View modal is closed successfully.");
        TestLogger.success("View modal close button test passed.");
    }
    @Test(priority = 12, description = "Verify view modal displays all key box details.")
    public void verifyViewModalDisplaysDetails() {
        getExtentTest().log(Status.INFO, "Test: Verifying View Modal Details");
        TestLogger.info("Test: Verifying View Modal Details");
        keyboxPage.clickActionButton().clickViewButton();
        boolean ViewModalColumsdisplayed = keyboxPage.IsSerialNumberColumnDisplayedInViewModal() && keyboxPage.IsNameColumnDisplayedInViewModal()
                && keyboxPage.IsRoleColumnDisplayedInViewModal();
        Assert.assertTrue(ViewModalColumsdisplayed, "View modal details are not getting displayed");
        getExtentTest().log(Status.PASS, "View modal details are getting displayed");
        TestLogger.success("Verified the view modal details successfully");
    }

    @Test(priority = 13, description = "Verify pagination controls are displayed.")
    public void verifyPaginationDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Pagination");
        TestLogger.info("Test: Verifying Pagination");
        boolean paginationacontrolsdisplayed = keyboxPage.isFirstPageButtonDisplayed() && keyboxPage.isPreviousPageButtonDisplayed()
         && keyboxPage.isNextPageButtonDisplayed() && keyboxPage.isLastPageButtonDisplayed();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Assert.assertTrue(paginationacontrolsdisplayed, "pagination controls are not getting displayed");
        getExtentTest().log(Status.PASS, "pagination controls are getting displayed");
        TestLogger.success("pagination controls are getting displayed");
    }

     @Test(priority = 14, description = "Verify Total Key Box card count matches table total records.")
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

    @Test(priority = 15, description = "Verify Registered card count matches table Registered status count.")
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
    @Test(priority = 16, description = "Verify Unregistered card count matches table Unregistered status count.")
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

    @Test(priority = 17, description = "Verify Status filter dropdown is displayed.")
    public void verifyStatusFilterDropdownDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Dropdown");
        TestLogger.info("Test: Verifying Status Filter Dropdown");
        keyboxPage.clickFilterButton();
        Assert.assertTrue(keyboxPage.isStatusFilterDropdownDisplayed(), "Status filter dropdown should be displayed.");
        getExtentTest().log(Status.PASS, "Status filter dropdown is displayed.");
    }

    @Test(priority = 18, description = "Verify Status filter default value is 'All'.")
    public void verifyStatusFilterDefaultValue() {
        getExtentTest().log(Status.INFO, "Test: Verifying Status Filter Default Value");
        TestLogger.info("Test: Verifying Status Filter Default Value");
        keyboxPage.clickFilterButton();
        String defaultValue = keyboxPage.getStatusFilterSelectedValue();
        Assert.assertEquals(defaultValue, "All", "Status filter default value should be 'All'.");
        getExtentTest().log(Status.PASS, "Status filter default value is correct: " + defaultValue);
    }


    @Test(priority = 19, description = "Verify Type filter dropdown is displayed.")
    public void verifyTypeFilterDropdownDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Dropdown");
        TestLogger.info("Test: Verifying Type Filter Dropdown");
        keyboxPage.clickFilterButton();
        Assert.assertTrue(keyboxPage.isTypeFilterDropdownDisplayed(), "Type filter dropdown should be displayed.");
        getExtentTest().log(Status.PASS, "Type filter dropdown is displayed.");
    }

    @Test(priority = 20, description = "Verify Clear Filter button is displayed.")
    public void verifyClearFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Clear Filter Button");
        TestLogger.info("Test: Verifying Clear Filter Button");
        keyboxPage.clickFilterButton();
        Assert.assertTrue(keyboxPage.isClearFilterButtonDisplayed(), "Clear Filter button should be displayed.");
        getExtentTest().log(Status.PASS, "Clear Filter button is displayed.");
    }
    @Test(priority = 21, description = "Verify Apply Filter button is displayed.")
    public void verifyApplyFilterButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Apply Filter Button");
        TestLogger.info("Test: Verifying Apply Filter Button");
        keyboxPage.clickFilterButton();
        Assert.assertTrue(keyboxPage.isApplyFilterButtonDisplayed(), "Apply Filter button should be displayed.");
        getExtentTest().log(Status.PASS, "Apply Filter button is displayed.");
    }

    @Test(priority = 22, description = "Verify filter dialog close button (X) is displayed.")
    public void verifyFilterDialogCloseButtonDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Close Button (X)");
        TestLogger.info("Test: Verifying Filter Dialog Close Button (X)");
        keyboxPage.clickFilterButton();
        Assert.assertTrue(keyboxPage.isFilterDialogCloseButtonDisplayed(), "Filter dialog close button (X) should be displayed.");
        getExtentTest().log(Status.PASS, "Filter dialog close button (X) is displayed.");
    }
    @Test(priority = 23, description = "Verify filter dialog closes when clicking close button (X).")
    public void verifyFilterDialogClosesOnCloseButton() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Closes on Close Button");
        TestLogger.info("Test: Verifying Filter Dialog Closes on Close Button");
         keyboxPage.clickFilterButton().clickFilterDialogCloseButton();
        Assert.assertTrue(keyboxPage.isSearchIconDisplayed(), "Filter dialog should be closed.");
        getExtentTest().log(Status.PASS, "Filter dialog closed successfully using close button.");
        TestLogger.success("Filter dialog close button functionality test passed.");
    }
    @Test(priority = 24, description = "Verify filter dialog closes when clicking Clear Filter button")
    public void verifyFilterDialogClosesOnClearFilter() {
        getExtentTest().log(Status.INFO, "Test: Verifying Filter Dialog Closes on Clear Filter");
        TestLogger.info("Test: Verifying Filter Dialog Closes on Clear Filter");
        keyboxPage.clickFilterButton().clickClearFilterButton();
        Assert.assertTrue(keyboxPage.isSearchIconDisplayed(), "Filter dialog should be closed after clicking Clear Filter.");
        getExtentTest().log(Status.PASS, "Filter dialog closed successfully using Clear Filter button.");
        TestLogger.success("Clear Filter button closes dialog test passed.");
    }
   /* @Test(priority = 25, description = "Register a KeyBox with valid details")
    public void RegisteraKeyBox() {
        getExtentTest().log(Status.INFO, "Test:Register a KeyBox with valid details");
        TestLogger.info("Test: Register a KeyBox with valid details");
        keyboxPage.navigateToKeyboxPage().clickFilterButton().clickStatusFilterDropdown().clickOptionInStatusDropdown().clickApplyFilterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickActionButtonOfUnregistered().clickRegisterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickRoTypeDropdown().clickOptionInRoTypeDropdown().clickLocationDropdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.EnterLocationInSearchbox("chennai");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickOptionInLocationDropdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickTemplateDropdown().clickOptionInTemplateDropdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickCancelButton();
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Key box is not registered");
        getExtentTest().log(Status.PASS, "Successfully Registered a key box");
        TestLogger.success("key box has been Registered Successfully ");
    }
    @Test(priority = 26, description = "Verify the cancel button in Registering a key box")
    public void VerifyTheCancelButtonInRegisteringaKeyBox() {
        getExtentTest().log(Status.INFO, "Test:Verify the cancel button in Registering a key box");
        TestLogger.info("Test: Verify the cancel button in Registering a key box");
        keyboxPage.navigateToKeyboxPage().clickFilterButton().clickStatusFilterDropdown().clickOptionInStatusDropdown().clickApplyFilterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickActionButtonOfUnregistered().clickRegisterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickCancelButton();
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Cancel button is not working in registering a keybox");
        getExtentTest().log(Status.PASS, "Cancel button functionality is verifed in registering a keybox");
        TestLogger.success("verification of cancel button in Registering a key box is passed");
    }
    @Test(priority = 27, description = "Verify the close button in Registering a key box")
    public void VerifyTheCloseButtonInRegisteringaKeyBox() {
        getExtentTest().log(Status.INFO, "Test:Verify the close button in Registering a key box");
        TestLogger.info("Test: Verify the close button in Registering a key box");
        keyboxPage.navigateToKeyboxPage().clickFilterButton().clickStatusFilterDropdown().clickOptionInStatusDropdown().clickApplyFilterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickActionButtonOfUnregistered().clickRegisterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickCloseButton();
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Close button is not working in registering a keybox");
        getExtentTest().log(Status.PASS, "Cancel button functionality is verifed in registering a keybox");
        TestLogger.success("verification of cancel button in Registering a key box is passed");
    }
 /*   @Test(priority = 28, description = "Verify the clear all button in Registering a key box")
    public void VerifyTheClearallButtonInRegisteringaKeyBox() {
        getExtentTest().log(Status.INFO, "Test:Verify the clear all button in Registering a key box");
        TestLogger.info("Test: Verify the clear all button in Registering a key box");
        keyboxPage.navigateToKeyboxPage().clickFilterButton().clickStatusFilterDropdown().clickOptionInStatusDropdown().clickApplyFilterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickActionButtonOfUnregistered().clickRegisterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickRoTypeDropdown().clickOptionInRoTypeDropdown().clickLocationDropdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.EnterLocationInSearchbox("chennai");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickOptionInLocationDropdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickTemplateDropdown().clickOptionInTemplateDropdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        keyboxPage.clickClearAllButton().clickSubmitButton();
        Assert.assertTrue(keyboxPage.isKeyboxTableDisplayed(), "Cancel button is not working in registering a keybox");
        getExtentTest().log(Status.PASS, "Cancel button functionality is verifed in registering a keybox");
        TestLogger.success("verification of cancel button in Registering a key box is passed");
    }*/
     /*  @Test(priority = 29, description = "Verify Type filter default value is 'Today'.")
    public void verifyTypeFilterDefaultValue() {
        getExtentTest().log(Status.INFO, "Test: Verifying Type Filter Default Value");
        TestLogger.info("Test: Verifying Type Filter Default Value");

        keyboxPage.openFilterDialog();

        String defaultValue = keyboxPage.getTypeFilterSelectedValue();
        Assert.assertEquals(defaultValue, "Today", "Type filter default value should be 'Today'.");
        getExtentTest().log(Status.PASS, "Type filter default value is correct: " + defaultValue);

        keyboxPage.closeFilterDialog();
        TestLogger.success("Type filter default value verification passed.");
    }*/
 @Test(priority = 30, description = "Verify pagination navigation functionality")
 public void verifyPaginationNavigation() {
     getExtentTest().log(Status.INFO, "Test: Verifying Pagination Navigation");
     TestLogger.info("Test: Verifying Pagination Navigation");

     if (keyboxPage.isNextPageButtonEnabled()) {
         TestLogger.info("Next page button is ENABLED. Proceeding to verify all pagination buttons.");
         keyboxPage.clickNextPageButton();
         TestLogger.info("Clicked Next page button successfully.");
         keyboxPage.clickPreviousPageButton();
         TestLogger.info("Clicked Previous page button successfully.");
         keyboxPage.clickLastPageButton();
         TestLogger.info("Clicked Last page button successfully.");
         keyboxPage.clickFirstPageButton();
         TestLogger.info("Clicked First page button successfully.");
         getExtentTest().log(Status.PASS, "All pagination buttons navigated successfully.");

     } else {
         String message = "Next page button is DISABLED" +
                 "So Other pagination buttons are expected to be disabled as well.";
         TestLogger.info("Next page button is Disabled");
         getExtentTest().log(Status.PASS, "Next page button is Disabled");
     }
 }



}
