package Pages;

import Elements.DeviceActivityPageElements;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DeviceActivityPage - Page Object class for Device Activity Report page
 * URL: /device-activity
 * Contains all business logic and user actions for the Device Activity page
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class DeviceActivityPage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final DeviceActivityPageElements elements;

    public DeviceActivityPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.elements = new DeviceActivityPageElements(driver);
        PageFactory.initElements(driver, this);
    }

    // ==================== PAGE NAVIGATION & VERIFICATION ====================

    /**
     * Navigate to the Device Activity page from the sidebar
     */
    public void navigateToDeviceActivityPage() {
        TestLogger.info("Navigating to Device Activity page...");
        try {
            // First click on Reports menu if collapsed
            waitHelper.waitForElementClickable(elements.reportsMenuButtonLocator).click();
            Thread.sleep(500);
        } catch (Exception e) {
            // Reports menu might already be expanded
        }
        waitHelper.waitForElementClickable(elements.deviceActivityNavLinkLocator).click();
        waitHelper.waitForElementVisible(elements.pageHeaderLocator);
        TestLogger.success("Successfully navigated to Device Activity page.");
    }

    /**
     * Check if page header is displayed
     */
    public boolean isPageHeaderDisplayed() {
        TestLogger.info("Checking if page header is displayed.");
        try {
            WebElement pageHeader = waitHelper.waitForElementVisible(elements.pageHeaderLocator);
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
        WebElement pageHeader = waitHelper.waitForElementVisible(elements.pageHeaderLocator);
        return pageHeader.getText();
    }

    /**
     * Check if page sub-header is displayed
     */
    public boolean isPageSubHeaderDisplayed() {
        TestLogger.info("Checking if page sub-header is displayed.");
        try {
            return elements.pageSubHeader.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Page sub-header not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get page sub-header text
     */
    public String getPageSubHeaderText() {
        return elements.pageSubHeader.getText();
    }

    // ==================== STATISTICS CARDS ====================

    /**
     * Check if Total Device Activity card is displayed
     */
    public boolean isTotalDeviceActivityCardDisplayed() {
        TestLogger.info("Checking if Total Device Activity card is displayed.");
        try {
            return elements.totalDeviceActivityCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Total Device Activity count
     */
    public String getTotalDeviceActivityCount() {
        TestLogger.info("Getting Total Device Activity count...");
        try {
            return elements.totalDeviceActivityCount.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get total device activity count: " + e.getMessage());
            return "0";
        }
    }

    /**
     * Check if Total Device Activity label is displayed
     */
    public boolean isTotalDeviceActivityLabelDisplayed() {
        try {
            return elements.totalDeviceActivityLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Total Device Activity label text
     */
    public String getTotalDeviceActivityLabelText() {
        try {
            return elements.totalDeviceActivityLabel.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if Success card is displayed
     */
    public boolean isSuccessCardDisplayed() {
        TestLogger.info("Checking if Success card is displayed.");
        try {
            return elements.successCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Success count
     */
    public String getSuccessCount() {
        TestLogger.info("Getting Success count...");
        try {
            return elements.successCount.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get success count: " + e.getMessage());
            return "0";
        }
    }

    /**
     * Check if Success label is displayed
     */
    public boolean isSuccessLabelDisplayed() {
        try {
            return elements.successLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Success label text
     */
    public String getSuccessLabelText() {
        try {
            return elements.successLabel.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if Pending card is displayed
     */
    public boolean isPendingCardDisplayed() {
        TestLogger.info("Checking if Pending card is displayed.");
        try {
            return elements.pendingCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Pending count
     */
    public String getPendingCount() {
        TestLogger.info("Getting Pending count...");
        try {
            return elements.pendingCount.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get pending count: " + e.getMessage());
            return "0";
        }
    }

    /**
     * Check if Pending label is displayed
     */
    public boolean isPendingLabelDisplayed() {
        try {
            return elements.pendingLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Pending label text
     */
    public String getPendingLabelText() {
        try {
            return elements.pendingLabel.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if Failed card is displayed
     */
    public boolean isFailedCardDisplayed() {
        TestLogger.info("Checking if Failed card is displayed.");
        try {
            return elements.failedCard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Failed count
     */
    public String getFailedCount() {
        TestLogger.info("Getting Failed count...");
        try {
            return elements.failedCount.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get failed count: " + e.getMessage());
            return "0";
        }
    }

    /**
     * Check if Failed label is displayed
     */
    public boolean isFailedLabelDisplayed() {
        try {
            return elements.failedLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Failed label text
     */
    public String getFailedLabelText() {
        try {
            return elements.failedLabel.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Verify statistics cards sum (Total = Success + Pending + Failed)
     */
    public boolean verifyStatisticsSum() {
        TestLogger.info("Verifying statistics sum...");
        try {
            int total = Integer.parseInt(getTotalDeviceActivityCount());
            int success = Integer.parseInt(getSuccessCount());
            int pending = Integer.parseInt(getPendingCount());
            int failed = Integer.parseInt(getFailedCount());
            boolean isValid = total == (success + pending + failed);
            TestLogger.info("Total: " + total + ", Success: " + success + ", Pending: " + pending + ", Failed: " + failed + ", Sum Valid: " + isValid);
            return isValid;
        } catch (NumberFormatException e) {
            TestLogger.error("Could not parse statistics values: " + e.getMessage());
            return false;
        }
    }

    // ==================== TABLE OPERATIONS ====================

    /**
     * Check if Device Activity table is displayed
     */
    public boolean isDeviceActivityTableDisplayed() {
        TestLogger.info("Checking if Device Activity table is displayed.");
        try {
            return elements.deviceActivityTable.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Device Activity table not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get table headers
     */
    public List<String> getTableHeaders() {
        TestLogger.info("Getting table headers...");
        return elements.tableHeaders.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Check if table header row is displayed
     */
    public boolean isTableHeaderRowDisplayed() {
        TestLogger.info("Checking if table header row is displayed.");
        try {
            return elements.tableHeaderRow.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get table row count (excluding empty state row)
     */
    public int getTableRowCount() {
        try {
            if (isNoResultsDisplayed()) {
                return 0;
            }
            return elements.tableRows.size();
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
            return elements.noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get text from specific cell
     */
    public String getCellText(int rowIndex, int columnIndex) {
        try {
            if (getTableRowCount() > rowIndex) {
                WebElement row = elements.tableRows.get(rowIndex);
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
     * Get command from specific row (column index 2)
     */
    public String getCommandFromRow(int rowIndex) {
        return getCellText(rowIndex, 2);
    }

    /**
     * Get sync date from specific row (column index 3)
     */
    public String getSyncDateFromRow(int rowIndex) {
        return getCellText(rowIndex, 3);
    }

    /**
     * Get created date from specific row (column index 4)
     */
    public String getCreatedDateFromRow(int rowIndex) {
        return getCellText(rowIndex, 4);
    }

    /**
     * Get status from specific row (column index 5)
     */
    public String getStatusFromRow(int rowIndex) {
        return getCellText(rowIndex, 5);
    }

    // ==================== INDIVIDUAL HEADER VERIFICATION ====================

    /**
     * Check if S.No header is displayed
     */
    public boolean isSnoHeaderDisplayed() {
        try {
            return elements.snoHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Serial Number header is displayed
     */
    public boolean isSerialNumberHeaderDisplayed() {
        try {
            return elements.serialNumberHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Command header is displayed
     */
    public boolean isCommandHeaderDisplayed() {
        try {
            return elements.commandHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Sync Date & Time header is displayed
     */
    public boolean isSyncDateHeaderDisplayed() {
        try {
            return elements.syncDateHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Created Date & Time header is displayed
     */
    public boolean isCreatedDateHeaderDisplayed() {
        try {
            return elements.createdDateHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Status header is displayed
     */
    public boolean isStatusHeaderDisplayed() {
        try {
            return elements.statusHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SEARCH OPERATIONS ====================

    /**
     * Check if search button is displayed
     */
    public boolean isSearchButtonDisplayed() {
        TestLogger.info("Checking if Search button is displayed.");
        try {
            return elements.searchButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click search button to expand search
     */
    public void clickSearchButton() {
        TestLogger.info("Clicking search button...");
        elements.searchButton.click();
    }

    /**
     * Check if search input is displayed after clicking search button
     */
    public boolean isSearchInputDisplayed() {
        TestLogger.info("Checking if search input is displayed.");
        try {
            return waitHelper.waitForElementVisible(elements.searchInputLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Search for device activity by text
     */
    public void searchDeviceActivity(String searchText) {
        TestLogger.info("Searching for device activity: " + searchText);
        clickSearchButton();
        WebElement searchField = waitHelper.waitForElementVisible(elements.searchInputLocator);
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
            WebElement searchField = driver.findElement(elements.searchInputLocator);
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
            return elements.filterButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click filter button
     */
    public void clickFilterButton() {
        TestLogger.info("Clicking filter button...");
        elements.filterButton.click();
    }

    /**
     * Check if filter dialog is displayed
     */
    public boolean isFilterDialogDisplayed() {
        TestLogger.info("Checking if filter dialog is displayed.");
        try {
            WebElement dialog = waitHelper.waitForElementVisible(elements.filterDialogLocator);
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Open filter dialog
     */
    public void openFilterDialog() {
        TestLogger.info("Opening filter dialog...");
        clickFilterButton();
        waitHelper.waitForElementVisible(elements.filterDialogLocator);
        TestLogger.success("Filter dialog opened.");
    }

    /**
     * Close filter dialog
     */
    public void closeFilterDialog() {
        TestLogger.info("Closing filter dialog...");
        if (isFilterDialogDisplayed()) {
            try {
                elements.filterDialogCloseButton.click();
            } catch (Exception e) {
                TestLogger.info("Could not close filter dialog: " + e.getMessage());
            }
        }
    }

    /**
     * Check if filter dialog is closed
     */
    public boolean isFilterDialogClosed() {
        TestLogger.info("Checking if filter dialog is closed.");
        try {
            Thread.sleep(500);
            List<WebElement> dialogs = driver.findElements(elements.filterDialogLocator);
            return dialogs.isEmpty() || !dialogs.get(0).isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Click Clear Filter button
     */
    public void clickClearFilterButton() {
        TestLogger.info("Clicking Clear Filter button...");
        elements.clearFilterButton.click();
    }

    /**
     * Click Apply Filter button
     */
    public void clickApplyFilterButton() {
        TestLogger.info("Clicking Apply Filter button...");
        elements.applyFilterButton.click();
    }

    /**
     * Check if Clear Filter button is displayed
     */
    public boolean isClearFilterButtonDisplayed() {
        try {
            return elements.clearFilterButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Apply Filter button is displayed
     */
    public boolean isApplyFilterButtonDisplayed() {
        try {
            return elements.applyFilterButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== STATUS BADGE OPERATIONS ====================

    /**
     * Get count of Success badges in table
     */
    public int getSuccessBadgeCount() {
        try {
            return elements.successBadges.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get count of Pending badges in table
     */
    public int getPendingBadgeCount() {
        try {
            return elements.pendingBadges.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get count of Failed badges in table
     */
    public int getFailedBadgeCount() {
        try {
            return elements.failedBadges.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get count of rows with specific status on current page
     */
    public int getRowCountByStatusOnCurrentPage(String status) {
        TestLogger.info("Counting rows with status '" + status + "' on current page...");
        int count = 0;
        try {
            List<WebElement> rows = elements.tableRows;
            for (WebElement row : rows) {
                try {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    // Status is in the 6th column (index 5)
                    if (cells.size() > 5) {
                        String cellText = cells.get(5).getText().trim();
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
                    // Already on first page
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
                // Ignore
            }

            TestLogger.info("Total count of rows with status '" + status + "': " + totalCount);
            return totalCount;
        } catch (Exception e) {
            TestLogger.error("Error getting total row count by status: " + e.getMessage());
            return totalCount;
        }
    }

    /**
     * Get count of Success status rows in the table
     */
    public int getSuccessStatusCountFromTable() {
        return getTotalRowCountByStatus("Success");
    }

    /**
     * Get count of Pending status rows in the table
     */
    public int getPendingStatusCountFromTable() {
        return getTotalRowCountByStatus("Pending");
    }

    /**
     * Get count of Failed status rows in the table
     */
    public int getFailedStatusCountFromTable() {
        return getTotalRowCountByStatus("Failed");
    }

    // ==================== PAGINATION OPERATIONS ====================

    /**
     * Check if pagination info is displayed
     */
    public boolean isPaginationInfoDisplayed() {
        TestLogger.info("Checking if pagination info is displayed.");
        try {
            return elements.paginationInfo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get pagination info text
     */
    public String getPaginationInfoText() {
        try {
            return elements.paginationInfo.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get page number info text
     */
    public String getPageNumberInfoText() {
        try {
            return elements.pageNumberInfo.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Click first page button
     */
    public void clickFirstPageButton() {
        TestLogger.info("Clicking first page button...");
        elements.firstPageButton.click();
    }

    /**
     * Click previous page button
     */
    public void clickPreviousPageButton() {
        TestLogger.info("Clicking previous page button...");
        elements.previousPageButton.click();
    }

    /**
     * Click next page button
     */
    public void clickNextPageButton() {
        TestLogger.info("Clicking next page button...");
        elements.nextPageButton.click();
    }

    /**
     * Click last page button
     */
    public void clickLastPageButton() {
        TestLogger.info("Clicking last page button...");
        elements.lastPageButton.click();
    }

    /**
     * Check if next page button is enabled
     */
    public boolean isNextPageButtonEnabled() {
        try {
            String buttonClass = elements.nextPageButton.getAttribute("class");
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
            String buttonClass = elements.previousPageButton.getAttribute("class");
            return buttonClass == null || !buttonClass.contains("pointer-events-none");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if first page button is displayed
     */
    public boolean isFirstPageButtonDisplayed() {
        try {
            return elements.firstPageButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if last page button is displayed
     */
    public boolean isLastPageButtonDisplayed() {
        try {
            return elements.lastPageButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== TABLE TOTAL RECORDS ====================

    /**
     * Get total records from pagination text
     * Parses text like "Showing From 1 To 9 of 9 results"
     */
    public int getTotalRecordsFromPagination() {
        TestLogger.info("Getting total records from pagination...");
        try {
            String paginationText = getPaginationInfoText();
            TestLogger.info("Pagination text: " + paginationText);

            // Parse "Showing From X To Y of Z results" format
            if (paginationText.contains("of")) {
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
     * Get table total records
     */
    public int getTableTotalRecords() {
        TestLogger.info("Getting table total records...");

        // First try to get from pagination text
        int totalFromPagination = getTotalRecordsFromPagination();
        if (totalFromPagination > 0) {
            return totalFromPagination;
        }

        // If pagination doesn't show total, just count current page rows
        return getTableRowCount();
    }

    // ==================== TOAST MESSAGE OPERATIONS ====================

    /**
     * Check if success toast is displayed
     */
    public boolean isSuccessToastDisplayed() {
        TestLogger.info("Checking if success toast is displayed.");
        try {
            WebElement successMsg = waitHelper.waitForElementVisible(elements.successToastLocator);
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
            WebElement errorMsg = driver.findElement(elements.errorToastLocator);
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
            return driver.findElement(elements.loadingSpinnerLocator).isDisplayed();
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
            waitHelper.waitForElementInvisible(elements.loadingSpinnerLocator);
        } catch (Exception e) {
            // Loading spinner might not appear
        }
    }
}
