package Pages;

import Elements.KeyboxPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * KeyboxPage - Page Object class for Key Box page
 * URL: /keySafes
 * Contains all business logic and user actions for the Key Box page
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class KeyboxPage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final CommonUtil commonUtil;
    private final KeyboxPageElements keyboxElements;

    public KeyboxPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.commonUtil = new CommonUtil(driver);
        this.keyboxElements = new KeyboxPageElements(driver);
        PageFactory.initElements(driver, this);
    }

    // ==================== PAGE NAVIGATION & VERIFICATION ====================

    /**
     * Navigate to the Key Box page from the sidebar
     */
    public void navigateToKeyboxPage() {
        TestLogger.info("Navigating to Key Box page...");
        waitHelper.waitForElementClickable(keyboxElements.keyboxNavLinkLocator).click();
        waitHelper.waitForElementVisible(keyboxElements.pageHeaderLocator);
        TestLogger.success("Successfully navigated to Key Box page.");
    }

    /**
     * Check if page header is displayed
     */
    public boolean isPageHeaderDisplayed() {
        TestLogger.info("Checking if page header is displayed.");
        try {
            WebElement pageHeader = waitHelper.waitForElementVisible(keyboxElements.pageHeaderLocator);
            return pageHeader.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Page header not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get page header text
     */
    public String getPageHeaderText() {
        WebElement pageHeader = waitHelper.waitForElementVisible(keyboxElements.pageHeaderLocator);
        return pageHeader.getText();
    }

    /**
     * Check if page sub-header is displayed
     */
    public boolean isPageSubHeaderDisplayed() {
        TestLogger.info("Checking if page sub-header is displayed.");
        try {
            return keyboxElements.pageSubHeader.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Page sub-header not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get page sub-header text
     */
    public String getPageSubHeaderText() {
        return keyboxElements.pageSubHeader.getText();
    }

    // ==================== STATISTICS CARDS ====================

    /**
     * Check if Total Key Box card is displayed
     */
    public boolean isTotalKeyBoxCardDisplayed() {
        TestLogger.info("Checking if Total Key Box card is displayed.");
        try {
            return keyboxElements.totalKeyBoxCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Total Key Box count
     */
    public String getTotalKeyBoxCount() {
        TestLogger.info("Getting Total Key Box count...");
        try {
            return keyboxElements.totalKeyBoxCount.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get total key box count: " + e.getMessage());
            return "0";
        }
    }

    /**
     * Check if Total Key Box label is displayed
     */
    public boolean isTotalKeyBoxLabelDisplayed() {
        TestLogger.info("Checking if Total Key Box label is displayed.");
        try {
            return keyboxElements.totalKeyBoxLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Total Key Box label text
     */
    public String getTotalKeyBoxLabelText() {
        TestLogger.info("Getting Total Key Box label text...");
        try {
            return keyboxElements.totalKeyBoxLabel.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get Total Key Box label text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if Registered card is displayed
     */
    public boolean isRegisteredCardDisplayed() {
        TestLogger.info("Checking if Registered card is displayed.");
        try {
            return keyboxElements.registeredCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Registered count
     */
    public String getRegisteredCount() {
        TestLogger.info("Getting Registered count...");
        try {
            return keyboxElements.registeredCount.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get registered count: " + e.getMessage());
            return "0";
        }
    }

    /**
     * Check if Registered label is displayed
     */
    public boolean isRegisteredLabelDisplayed() {
        TestLogger.info("Checking if Registered label is displayed.");
        try {
            return keyboxElements.registeredLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Registered label text
     */
    public String getRegisteredLabelText() {
        TestLogger.info("Getting Registered label text...");
        try {
            return keyboxElements.registeredLabel.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get Registered label text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if Unregistered card is displayed
     */
    public boolean isUnregisteredCardDisplayed() {
        TestLogger.info("Checking if Unregistered card is displayed.");
        try {
            return keyboxElements.unregisteredCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Unregistered count
     */
    public String getUnregisteredCount() {
        TestLogger.info("Getting Unregistered count...");
        try {
            return keyboxElements.unregisteredCount.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get unregistered count: " + e.getMessage());
            return "0";
        }
    }

    /**
     * Check if Unregistered label is displayed
     */
    public boolean isUnregisteredLabelDisplayed() {
        TestLogger.info("Checking if Unregistered label is displayed.");
        try {
            return keyboxElements.unregisteredLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Unregistered label text
     */
    public String getUnregisteredLabelText() {
        TestLogger.info("Getting Unregistered label text...");
        try {
            return keyboxElements.unregisteredLabel.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get Unregistered label text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Verify statistics cards sum (Total = Registered + Unregistered)
     */
    public boolean verifyStatisticsSum() {
        TestLogger.info("Verifying statistics sum...");
        try {
            int total = Integer.parseInt(getTotalKeyBoxCount());
            int registered = Integer.parseInt(getRegisteredCount());
            int unregistered = Integer.parseInt(getUnregisteredCount());
            boolean isValid = total == (registered + unregistered);
            TestLogger.info("Total: " + total + ", Registered: " + registered + ", Unregistered: " + unregistered + ", Sum Valid: " + isValid);
            return isValid;
        } catch (NumberFormatException e) {
            TestLogger.error("Could not parse statistics values: " + e.getMessage());
            return false;
        }
    }

    // ==================== TABLE OPERATIONS ====================

    /**
     * Check if Key Box table is displayed
     */
    public boolean isKeyboxTableDisplayed() {
        TestLogger.info("Checking if Key Box table is displayed.");
        try {
            return keyboxElements.keyboxTable.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Key Box table not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get table headers
     */
    public List<String> getTableHeaders() {
        TestLogger.info("Getting table headers...");
        return keyboxElements.tableHeaders.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Check if table header row is displayed
     */
    public boolean isTableHeaderRowDisplayed() {
        TestLogger.info("Checking if table header row is displayed.");
        try {
            return keyboxElements.tableHeaderRow.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get table row count (excluding empty state row)
     */
    public int getTableRowCount() {
        try {
            // Check if empty state is displayed
            if (isNoResultsDisplayed()) {
                return 0;
            }
            return keyboxElements.tableRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Check if "No results found" message is displayed
     */
    public boolean isNoResultsDisplayed() {
        TestLogger.info("Checking if 'No results found' message is displayed.");
        try {
            return keyboxElements.noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get text from specific cell in first row
     */
    public String getFirstRowText(int columnIndex) {
        if (getTableRowCount() > 0) {
            try {
                WebElement firstRow = keyboxElements.tableRows.get(0);
                List<WebElement> cells = firstRow.findElements(By.tagName("td"));
                if (cells.size() > columnIndex) {
                    return cells.get(columnIndex).getText();
                }
            } catch (Exception e) {
                TestLogger.error("Could not get first row text: " + e.getMessage());
            }
        }
        return "";
    }

    /**
     * Get text from specific row and column
     */
    public String getCellText(int rowIndex, int columnIndex) {
        try {
            if (getTableRowCount() > rowIndex) {
                WebElement row = keyboxElements.tableRows.get(rowIndex);
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() > columnIndex) {
                    return cells.get(columnIndex).getText();
                }
            }
        } catch (Exception e) {
            TestLogger.error("Error getting cell text: " + e.getMessage());
        }
        return "";
    }

    /**
     * Get serial number from specific row (column index 1)
     */
    public String getSerialNumberFromRow(int rowIndex) {
        return getCellText(rowIndex, 1);
    }

    /**
     * Get location from specific row (column index 2)
     */
    public String getLocationFromRow(int rowIndex) {
        return getCellText(rowIndex, 2);
    }

    /**
     * Get Ro Type from specific row (column index 3)
     */
    public String getRoTypeFromRow(int rowIndex) {
        return getCellText(rowIndex, 3);
    }

    /**
     * Get action buttons count
     */
    public int getActionButtonsCount() {
        try {
            return keyboxElements.actionButtons.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== SEARCH OPERATIONS ====================

    /**
     * Check if search button is displayed
     */
    public boolean isSearchButtonDisplayed() {
        TestLogger.info("Checking if Search button is displayed.");
        try {
            return keyboxElements.searchButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Search button not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Click search button to expand search
     */
    public void clickSearchButton() {
        TestLogger.info("Clicking search button...");
        keyboxElements.searchButton.click();
    }

    /**
     * Check if search input is displayed after clicking search button
     */
    public boolean isSearchInputDisplayed() {
        TestLogger.info("Checking if search input is displayed.");
        try {
            return waitHelper.waitForElementVisible(keyboxElements.searchInputLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Search for key box by text
     */
    public void searchKeybox(String searchText) {
        TestLogger.info("Searching for key box: " + searchText);
        clickSearchButton();
        WebElement searchField = waitHelper.waitForElementVisible(keyboxElements.searchInputLocator);
        searchField.clear();
        searchField.sendKeys(searchText);
        TestLogger.info("Search initiated for: " + searchText);
    }

    /**
     * Clear search input
     */
    public void clearSearch() {
        TestLogger.info("Clearing search input...");
        try {
            WebElement searchField = driver.findElement(keyboxElements.searchInputLocator);
            searchField.clear();
            TestLogger.success("Search cleared.");
        } catch (Exception e) {
            TestLogger.info("Search field not visible or already cleared.");
        }
    }

    // ==================== FILTER OPERATIONS ====================

    /**
     * Check if filter button is displayed
     */
    public boolean isFilterButtonDisplayed() {
        TestLogger.info("Checking if Filter button is displayed.");
        try {
            return keyboxElements.filterButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Filter button not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Click filter button
     */
    public void clickFilterButton() {
        TestLogger.info("Clicking filter button...");
        keyboxElements.filterButton.click();
    }

    /**
     * Check if filter dialog is displayed
     */
    public boolean isFilterDialogDisplayed() {
        TestLogger.info("Checking if filter dialog is displayed.");
        try {
            WebElement dialog = waitHelper.waitForElementVisible(keyboxElements.filterDialogLocator);
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if filter dialog title is displayed
     */
    public boolean isFilterDialogTitleDisplayed() {
        TestLogger.info("Checking if filter dialog title is displayed.");
        try {
            return keyboxElements.filterDialogTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get filter dialog title text
     */
    public String getFilterDialogTitleText() {
        try {
            return keyboxElements.filterDialogTitle.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if filter dialog description is displayed
     */
    public boolean isFilterDialogDescriptionDisplayed() {
        TestLogger.info("Checking if filter dialog description is displayed.");
        try {
            return keyboxElements.filterDialogDescription.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get filter dialog description text
     */
    public String getFilterDialogDescriptionText() {
        try {
            return keyboxElements.filterDialogDescription.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if Status filter label is displayed
     */
    public boolean isStatusFilterLabelDisplayed() {
        TestLogger.info("Checking if Status filter label is displayed.");
        try {
            return keyboxElements.statusFilterLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Status filter dropdown is displayed
     */
    public boolean isStatusFilterDropdownDisplayed() {
        TestLogger.info("Checking if Status filter dropdown is displayed.");
        try {
            return keyboxElements.statusFilterDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Status filter dropdown
     */
    public void clickStatusFilterDropdown() {
        TestLogger.info("Clicking Status filter dropdown...");
        keyboxElements.statusFilterDropdown.click();
    }

    /**
     * Get Status filter selected value
     */
    public String getStatusFilterSelectedValue() {
        try {
            return keyboxElements.statusFilterSelectedValue.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Select Status filter option
     */
    public void selectStatusFilterOption(String optionText) {
        TestLogger.info("Selecting Status filter option: " + optionText);
        clickStatusFilterDropdown();
        try {
            Thread.sleep(500);
            waitHelper.waitForElementClickable(keyboxElements.filterDropdownOptionLocator(optionText)).click();
            TestLogger.success("Status filter option selected: " + optionText);
        } catch (Exception e) {
            TestLogger.error("Could not select Status filter option: " + e.getMessage());
        }
    }

    /**
     * Check if Type filter label is displayed
     */
    public boolean isTypeFilterLabelDisplayed() {
        TestLogger.info("Checking if Type filter label is displayed.");
        try {
            return keyboxElements.typeFilterLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Type filter dropdown is displayed
     */
    public boolean isTypeFilterDropdownDisplayed() {
        TestLogger.info("Checking if Type filter dropdown is displayed.");
        try {
            return keyboxElements.typeFilterDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Type filter dropdown
     */
    public void clickTypeFilterDropdown() {
        TestLogger.info("Clicking Type filter dropdown...");
        keyboxElements.typeFilterDropdown.click();
    }

    /**
     * Get Type filter selected value
     */
    public String getTypeFilterSelectedValue() {
        try {
            return keyboxElements.typeFilterSelectedValue.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Select Type filter option
     */
    public void selectTypeFilterOption(String optionText) {
        TestLogger.info("Selecting Type filter option: " + optionText);
        clickTypeFilterDropdown();
        try {
            Thread.sleep(500);
            waitHelper.waitForElementClickable(keyboxElements.filterDropdownOptionLocator(optionText)).click();
            TestLogger.success("Type filter option selected: " + optionText);
        } catch (Exception e) {
            TestLogger.error("Could not select Type filter option: " + e.getMessage());
        }
    }

    /**
     * Check if Clear Filter button is displayed
     */
    public boolean isClearFilterButtonDisplayed() {
        TestLogger.info("Checking if Clear Filter button is displayed.");
        try {
            return keyboxElements.clearFilterButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Clear Filter button
     */
    public void clickClearFilterButton() {
        TestLogger.info("Clicking Clear Filter button...");
        keyboxElements.clearFilterButton.click();
        TestLogger.success("Clear Filter button clicked.");
    }

    /**
     * Check if Apply Filter button is displayed
     */
    public boolean isApplyFilterButtonDisplayed() {
        TestLogger.info("Checking if Apply Filter button is displayed.");
        try {
            return keyboxElements.applyFilterButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Apply Filter button
     */
    public void clickApplyFilterButton() {
        TestLogger.info("Clicking Apply Filter button...");
        keyboxElements.applyFilterButton.click();
        TestLogger.success("Apply Filter button clicked.");
    }

    /**
     * Check if filter dialog close button (X) is displayed
     */
    public boolean isFilterDialogCloseButtonDisplayed() {
        TestLogger.info("Checking if filter dialog close button is displayed.");
        try {
            return keyboxElements.filterDialogCloseButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click filter dialog close button (X)
     */
    public void clickFilterDialogCloseButton() {
        TestLogger.info("Clicking filter dialog close button...");
        keyboxElements.filterDialogCloseButton.click();
        TestLogger.success("Filter dialog close button clicked.");
    }

    /**
     * Open filter dialog
     */
    public void openFilterDialog() {
        TestLogger.info("Opening filter dialog...");
        clickFilterButton();
        waitHelper.waitForElementVisible(keyboxElements.filterDialogLocator);
        TestLogger.success("Filter dialog opened.");
    }

    /**
     * Close filter dialog using X button
     */
    public void closeFilterDialog() {
        TestLogger.info("Closing filter dialog...");
        if (isFilterDialogDisplayed()) {
            clickFilterDialogCloseButton();
        }
    }

    /**
     * Apply filter with Status option
     */
    public void applyStatusFilter(String statusOption) {
        TestLogger.info("Applying Status filter: " + statusOption);
        openFilterDialog();
        selectStatusFilterOption(statusOption);
        clickApplyFilterButton();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestLogger.success("Status filter applied: " + statusOption);
    }

    /**
     * Apply filter with Type option
     */
    public void applyTypeFilter(String typeOption) {
        TestLogger.info("Applying Type filter: " + typeOption);
        openFilterDialog();
        selectTypeFilterOption(typeOption);
        clickApplyFilterButton();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestLogger.success("Type filter applied: " + typeOption);
    }

    /**
     * Apply filter with both Status and Type options
     */
    public void applyFilter(String statusOption, String typeOption) {
        TestLogger.info("Applying filter - Status: " + statusOption + ", Type: " + typeOption);
        openFilterDialog();
        selectStatusFilterOption(statusOption);
        selectTypeFilterOption(typeOption);
        clickApplyFilterButton();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestLogger.success("Filter applied - Status: " + statusOption + ", Type: " + typeOption);
    }

    /**
     * Clear all filters
     */
    public void clearAllFilters() {
        TestLogger.info("Clearing all filters...");
        openFilterDialog();
        clickClearFilterButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestLogger.success("All filters cleared.");
    }

    /**
     * Check if filter dialog is closed
     */
    public boolean isFilterDialogClosed() {
        TestLogger.info("Checking if filter dialog is closed.");
        try {
            Thread.sleep(500);
            List<WebElement> dialogs = driver.findElements(keyboxElements.filterDialogLocator);
            return dialogs.isEmpty() || !dialogs.get(0).isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Verify all filter dialog elements are displayed
     */
    public boolean verifyFilterDialogElements() {
        TestLogger.info("Verifying all filter dialog elements...");
        boolean allElementsDisplayed = true;

        allElementsDisplayed &= isFilterDialogTitleDisplayed();
        allElementsDisplayed &= isFilterDialogDescriptionDisplayed();
        allElementsDisplayed &= isStatusFilterLabelDisplayed();
        allElementsDisplayed &= isStatusFilterDropdownDisplayed();
        allElementsDisplayed &= isTypeFilterLabelDisplayed();
        allElementsDisplayed &= isTypeFilterDropdownDisplayed();
        allElementsDisplayed &= isClearFilterButtonDisplayed();
        allElementsDisplayed &= isApplyFilterButtonDisplayed();
        allElementsDisplayed &= isFilterDialogCloseButtonDisplayed();

        return allElementsDisplayed;
    }

    // ==================== ACTION MENU OPERATIONS ====================

    /**
     * Click action menu for specific row
     */
    public void clickActionMenuForRow(int rowIndex) {
        TestLogger.info("Clicking action menu for row " + rowIndex);
        if (keyboxElements.actionButtons.size() > rowIndex) {
            keyboxElements.actionButtons.get(rowIndex).click();
            waitHelper.waitForElementVisible(keyboxElements.actionMenuLocator);
        } else {
            TestLogger.error("Action button not found for row " + rowIndex);
        }
    }

    /**
     * Select View from action menu
     */
    public void selectViewFromActionMenu() {
        TestLogger.info("Selecting 'View' from action menu...");
        waitHelper.waitForElementClickable(keyboxElements.viewActionLocator).click();
    }

    /**
     * Select Edit from action menu
     */
    public void selectEditFromActionMenu() {
        TestLogger.info("Selecting 'Edit' from action menu...");
        waitHelper.waitForElementClickable(keyboxElements.editActionLocator).click();
    }

    /**
     * Select Delete from action menu
     */
    public void selectDeleteFromActionMenu() {
        TestLogger.info("Selecting 'Delete' from action menu...");
        waitHelper.waitForElementClickable(keyboxElements.deleteActionLocator).click();
    }

    // ==================== MODAL OPERATIONS ====================

    /**
     * Check if modal is displayed
     */
    public boolean isModalDisplayed() {
        TestLogger.info("Checking if modal is displayed.");
        try {
            WebElement modal = waitHelper.waitForElementVisible(keyboxElements.modalContainerLocator);
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get modal header text
     */
    public String getModalHeaderText() {
        try {
            WebElement header = waitHelper.waitForElementVisible(keyboxElements.modalHeaderLocator);
            return header.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if close button is displayed in modal
     */
    public boolean isCloseButtonDisplayed() {
        TestLogger.info("Checking if close button is displayed.");
        try {
            return keyboxElements.closeButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click close button
     */
    public void clickCloseButton() {
        TestLogger.info("Clicking close button...");
        keyboxElements.closeButton.click();
        TestLogger.success("Clicked close button.");
    }

    /**
     * Check if modal is closed
     */
    public boolean isModalClosed() {
        TestLogger.info("Checking if modal is closed.");
        try {
            Thread.sleep(500);
            List<WebElement> modals = driver.findElements(keyboxElements.modalContainerLocator);
            return modals.isEmpty() || !modals.get(0).isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    // ==================== VIEW MODAL OPERATIONS ====================

    /**
     * Get serial number from view modal
     */
    public String getViewModalSerialNumber() {
        try {
            return keyboxElements.viewSerialNumber.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get location from view modal
     */
    public String getViewModalLocation() {
        try {
            return keyboxElements.viewLocation.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get Ro Type from view modal
     */
    public String getViewModalRoType() {
        try {
            return keyboxElements.viewRoType.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get status from view modal
     */
    public String getViewModalStatus() {
        try {
            return keyboxElements.viewStatus.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // ==================== DELETE OPERATIONS ====================

    /**
     * Check if delete confirmation is displayed
     */
    public boolean isDeleteConfirmationDisplayed() {
        TestLogger.info("Checking if delete confirmation is displayed.");
        try {
            WebElement confirmDialog = waitHelper.waitForElementVisible(keyboxElements.deleteConfirmationLocator);
            return confirmDialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Confirm deletion
     */
    public void confirmDeletion() {
        TestLogger.info("Confirming deletion...");
        waitHelper.waitForElementClickable(keyboxElements.confirmDeleteButtonLocator).click();
        TestLogger.success("Deletion confirmed.");
    }

    /**
     * Cancel deletion
     */
    public void cancelDeletion() {
        TestLogger.info("Canceling deletion...");
        waitHelper.waitForElementClickable(keyboxElements.cancelDeleteButtonLocator).click();
        TestLogger.success("Deletion canceled.");
    }

    // ==================== PAGINATION OPERATIONS ====================

    /**
     * Check if pagination info is displayed
     */
    public boolean isPaginationInfoDisplayed() {
        TestLogger.info("Checking if pagination info is displayed.");
        try {
            return keyboxElements.paginationInfo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get pagination info text
     */
    public String getPaginationInfoText() {
        try {
            return keyboxElements.paginationInfo.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get total records count from pagination text
     * Parses text like "Showing 1 to 10 of 50 results" to extract 50
     */
    public int getTotalRecordsFromPagination() {
        TestLogger.info("Getting total records from pagination...");
        try {
            String paginationText = keyboxElements.paginationTotalRecords.getText();
            TestLogger.info("Pagination text: " + paginationText);

            // Parse "Showing X to Y of Z results" format
            if (paginationText.contains("of") && paginationText.contains("results")) {
                String[] parts = paginationText.split("of");
                if (parts.length > 1) {
                    String totalPart = parts[1].replaceAll("[^0-9]", "");
                    return Integer.parseInt(totalPart);
                }
            }
            return -1;
        } catch (Exception e) {
            TestLogger.error("Could not get total records from pagination: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Get total records by counting all pages
     * Navigates through all pages and counts total rows
     */
    public int getTotalRecordsByNavigatingAllPages() {
        TestLogger.info("Getting total records by navigating all pages...");
        int totalRecords = 0;

        try {
            // Go to first page first
            if (isPaginationInfoDisplayed()) {
                try {
                    clickFirstPageButton();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // Already on first page or button not available
                }
            }

            // Count records on current page
            totalRecords += getTableRowCount();

            // Navigate through all pages
            while (isNextPageButtonEnabled()) {
                clickNextPageButton();
                Thread.sleep(1000);
                totalRecords += getTableRowCount();
            }

            // Go back to first page
            try {
                clickFirstPageButton();
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore if first page button not available
            }

            TestLogger.info("Total records counted: " + totalRecords);
            return totalRecords;
        } catch (Exception e) {
            TestLogger.error("Error counting total records: " + e.getMessage());
            return totalRecords;
        }
    }

    /**
     * Get table total records - tries pagination first, then counts if needed
     */
    public int getTableTotalRecords() {
        TestLogger.info("Getting table total records...");

        // First try to get from pagination text
        int totalFromPagination = getTotalRecordsFromPagination();
        if (totalFromPagination > 0) {
            TestLogger.info("Total records from pagination: " + totalFromPagination);
            return totalFromPagination;
        }

        // If pagination doesn't show total, check if there's pagination
        if (!isPaginationInfoDisplayed() || !isNextPageButtonEnabled()) {
            // Single page - just count rows
            int rowCount = getTableRowCount();
            TestLogger.info("Single page, row count: " + rowCount);
            return rowCount;
        }

        // Navigate through all pages to count
        return getTotalRecordsByNavigatingAllPages();
    }

    /**
     * Get count of rows with specific status on current page
     * @param status The status to count (e.g., "Registered", "Unregistered")
     */
    public int getRowCountByStatusOnCurrentPage(String status) {
        TestLogger.info("Counting rows with status '" + status + "' on current page...");
        int count = 0;
        try {
            List<WebElement> rows = keyboxElements.tableRows;
            for (WebElement row : rows) {
                try {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    // Status is typically in the 8th column (index 7)
                    if (cells.size() > 7) {
                        String cellText = cells.get(7).getText().trim();
                        if (cellText.equalsIgnoreCase(status)) {
                            count++;
                        }
                    }
                } catch (Exception e) {
                    // Skip row if error
                }
            }
            TestLogger.info("Found " + count + " rows with status '" + status + "' on current page.");
        } catch (Exception e) {
            TestLogger.error("Error counting rows by status: " + e.getMessage());
        }
        return count;
    }

    /**
     * Get total count of rows with specific status across all pages
     * @param status The status to count (e.g., "Registered", "Unregistered")
     */
    public int getTotalRowCountByStatus(String status) {
        TestLogger.info("Getting total count of rows with status '" + status + "'...");
        int totalCount = 0;

        try {
            // Go to first page first
            if (isPaginationInfoDisplayed()) {
                try {
                    clickFirstPageButton();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // Already on first page or button not available
                }
            }

            // Count records with status on current page
            totalCount += getRowCountByStatusOnCurrentPage(status);

            // Navigate through all pages if pagination exists
            while (isNextPageButtonEnabled()) {
                clickNextPageButton();
                Thread.sleep(1000);
                totalCount += getRowCountByStatusOnCurrentPage(status);
            }

            // Go back to first page
            try {
                clickFirstPageButton();
                Thread.sleep(500);
            } catch (Exception e) {
                // Ignore if first page button not available
            }

            TestLogger.info("Total count of rows with status '" + status + "': " + totalCount);
            return totalCount;
        } catch (Exception e) {
            TestLogger.error("Error getting total row count by status: " + e.getMessage());
            return totalCount;
        }
    }

    /**
     * Get count of Registered status rows in the table
     */
    public int getRegisteredStatusCountFromTable() {
        TestLogger.info("Getting Registered status count from table...");
        return getTotalRowCountByStatus("Registered");
    }

    /**
     * Get count of Unregistered status rows in the table
     */
    public int getUnregisteredStatusCountFromTable() {
        TestLogger.info("Getting Unregistered status count from table...");
        return getTotalRowCountByStatus("Unregistered");
    }

    /**
     * Click next page button
     */
    public void clickNextPageButton() {
        TestLogger.info("Clicking next page button...");
        keyboxElements.nextPageButton.click();
    }

    /**
     * Click previous page button
     */
    public void clickPreviousPageButton() {
        TestLogger.info("Clicking previous page button...");
        keyboxElements.previousPageButton.click();
    }

    /**
     * Click first page button
     */
    public void clickFirstPageButton() {
        TestLogger.info("Clicking first page button...");
        keyboxElements.firstPageButton.click();
    }

    /**
     * Click last page button
     */
    public void clickLastPageButton() {
        TestLogger.info("Clicking last page button...");
        keyboxElements.lastPageButton.click();
    }

    /**
     * Check if next page button is enabled
     */
    public boolean isNextPageButtonEnabled() {
        try {
            String buttonClass = keyboxElements.nextPageButton.getAttribute("class");
            return buttonClass == null || !buttonClass.contains("pointer-events-none");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if previous page button is enabled
     */
    public boolean isPreviousPageButtonEnabled() {
        try {
            String buttonClass = keyboxElements.previousPageButton.getAttribute("class");
            return buttonClass == null || !buttonClass.contains("pointer-events-none");
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== TOAST MESSAGE OPERATIONS ====================

    /**
     * Check if success toast is displayed
     */
    public boolean isSuccessToastDisplayed() {
        TestLogger.info("Checking if success toast is displayed.");
        try {
            WebElement successMsg = waitHelper.waitForElementVisible(keyboxElements.successToastLocator);
            return successMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if error toast is displayed
     */
    public boolean isErrorToastDisplayed() {
        TestLogger.info("Checking if error toast is displayed.");
        try {
            WebElement errorMsg = driver.findElement(keyboxElements.errorToastLocator);
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== LOADING STATE ====================

    /**
     * Check if loading spinner is displayed
     */
    public boolean isLoadingSpinnerDisplayed() {
        try {
            return driver.findElement(keyboxElements.loadingSpinnerLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for loading to complete
     */
    public void waitForLoadingComplete() {
        TestLogger.info("Waiting for loading to complete...");
        try {
            waitHelper.waitForElementInvisible(keyboxElements.loadingSpinnerLocator);
        } catch (Exception e) {
            // Loading spinner might not appear for quick operations
        }
    }

    // ==================== INDIVIDUAL HEADER VERIFICATION ====================

    /**
     * Check if S.No header is displayed
     */
    public boolean isSnoHeaderDisplayed() {
        try {
            return keyboxElements.snoHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Serial Number header is displayed
     */
    public boolean isSerialNumberHeaderDisplayed() {
        try {
            return keyboxElements.serialNumberHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Location header is displayed
     */
    public boolean isLocationHeaderDisplayed() {
        try {
            return keyboxElements.locationHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Ro Type header is displayed
     */
    public boolean isRoTypeHeaderDisplayed() {
        try {
            return keyboxElements.roTypeHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Last Communicated Date header is displayed
     */
    public boolean isLastCommunicatedHeaderDisplayed() {
        try {
            return keyboxElements.lastCommunicatedHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Registered By header is displayed
     */
    public boolean isRegisteredByHeaderDisplayed() {
        try {
            return keyboxElements.registeredByHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Updated By header is displayed
     */
    public boolean isUpdatedByHeaderDisplayed() {
        try {
            return keyboxElements.updatedByHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Status header is displayed
     */
    public boolean isStatusHeaderDisplayed() {
        try {
            return keyboxElements.statusHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Action header is displayed
     */
    public boolean isActionHeaderDisplayed() {
        try {
            return keyboxElements.actionHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== COMPLEX WORKFLOW METHODS ====================

    /**
     * View key box details for specific row
     */
    public void viewKeyboxDetails(int rowIndex) {
        TestLogger.info("Viewing key box details at row " + rowIndex);
        clickActionMenuForRow(rowIndex);
        selectViewFromActionMenu();
    }

    /**
     * Close view modal if open
     */
    public void closeViewModal() {
        TestLogger.info("Closing view modal...");
        if (isModalDisplayed()) {
            clickCloseButton();
        }
    }
}
