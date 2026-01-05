package Pages;

import Elements.KeyPositionTemplatePageElements;
import Elements.KeyboxPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * KeyPositionTemplatePage - Contains all page actions for Key Position Template page
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class KeyPositionTemplatePage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final CommonUtil commonUtil;
    private final KeyPositionTemplatePageElements elements;

    public KeyPositionTemplatePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.commonUtil = new CommonUtil(driver);
        this.elements = new KeyPositionTemplatePageElements(driver);
        PageFactory.initElements(driver, this);
    }

    // ==================== NAVIGATION ====================

    /**
     * Navigate to Key Position Template page via sidebar menu
     */
    public KeyPositionTemplatePage navigateToKeyPositionTemplatePage() {
        TestLogger.info("Navigating to Key Position Template page...");
        waitHelper.waitForElementClickable(elements.keyPositionTemplateNavLinkLocator).click();
        waitHelper.waitForElementVisible(elements.pageHeaderLocator);
        TestLogger.success("Successfully navigated to Key Position Template page.");
        return this;
    }

    // ==================== PAGE HEADER VERIFICATION ====================

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
        TestLogger.info("Getting page header text...");
        try {
            WebElement pageHeader = waitHelper.waitForElementVisible(elements.pageHeaderLocator);
            return pageHeader.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get page header text: " + e.getMessage());
            return "";
        }
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
        TestLogger.info("Getting page sub-header text...");
        try {
            return elements.pageSubHeader.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get page sub-header text: " + e.getMessage());
            return "";
        }
    }

    // ==================== SEARCH FUNCTIONALITY ====================

    /**
     * Check if search button is displayed
     */
    public boolean isSearchButtonDisplayed() {
        TestLogger.info("Checking if search button is displayed.");
        try {
            return elements.searchButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Search button not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Click search button to expand search
     */
    public KeyPositionTemplatePage clickSearchButton() {
        TestLogger.info("Clicking search button...");
        elements.searchButton.click();
        return this;
    }

    /**
     * Check if search input is displayed
     */
    public boolean isSearchInputDisplayed() {
        TestLogger.info("Checking if search input is displayed.");
        try {
            WebElement input = waitHelper.waitForElementVisible(elements.searchInputLocator);
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter search text
     */
    public KeyPositionTemplatePage searchTemplate(String searchText) {
        TestLogger.info("Searching for template: " + searchText);
        WebElement searchField = waitHelper.waitForElementVisible(elements.searchInputLocator);
        searchField.clear();
        searchField.sendKeys(searchText);
        TestLogger.info("Search initiated for: " + searchText);
        return this;
    }

    /**
     * Clear search input
     */
    public KeyPositionTemplatePage clearSearch() {
        TestLogger.info("Clearing search input...");
        try {
            WebElement searchField = driver.findElement(elements.searchInputLocator);
            searchField.clear();
            searchField.sendKeys(Keys.ESCAPE);
            TestLogger.success("Search cleared.");
        } catch (Exception e) {
            TestLogger.info("Search field not visible or already cleared.");
        }
        return this;
    }

    /**
     * Search with valid template name
     */
    public KeyPositionTemplatePage searchWithValidTemplateName() {
        clickSearchButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        searchTemplate("sample template");
        return this;
    }

    /**
     * Search with invalid template name
     */
    public KeyPositionTemplatePage searchWithInvalidTemplateName() {
        clickSearchButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        searchTemplate("NonExistentTemplate");
        return this;
    }

    // ==================== ADD TEMPLATE FUNCTIONALITY ====================

    /**
     * Check if Add Template button is displayed
     */
    public boolean isAddTemplateButtonDisplayed() {
        TestLogger.info("Checking if Add Template button is displayed.");
        try {
            return elements.addTemplateButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Add Template button not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Click Add Template button
     */
    public KeyPositionTemplatePage clickAddTemplateButton() {
        TestLogger.info("Clicking Add Template button...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        elements.addTemplateButton.click();
        return this;
    }

    // ==================== TABLE OPERATIONS ====================

    /**
     * Check if template table is displayed
     */
    public boolean isTemplateTableDisplayed() {
        TestLogger.info("Checking if template table is displayed.");
        try {
            return elements.templateTable.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Template table not found: " + e.getMessage());
            return false;
        }
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
     * Get column header texts
     */
    public List<String> getColumnHeaderTexts() {
        TestLogger.info("Getting column header texts...");
        List<String> headerTexts = new ArrayList<>();
        try {
            List<WebElement> headers = driver.findElements(By.xpath("//th[@class='border border-primary-50 whitespace-nowrap bg-primary/80 px-4 py-1.5 uppercase text-primary-foreground text-xs+ lg:px-5']"));
            for (WebElement header : headers) {
                headerTexts.add(header.getText().trim());
            }
        } catch (Exception e) {
            TestLogger.error("Could not get column header texts: " + e.getMessage());
        }
        return headerTexts;
    }

    /**
     * Get table row count
     */
    public int getTableRowCount() {
        TestLogger.info("Getting table row count...");
        try {
            List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='border-y border-transparent border-b-slate-100 ']"));
            Thread.sleep(500);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Check if S.No header is displayed
     */
    public boolean isSnoHeaderDisplayed() {
        TestLogger.info("Checking if S.No header is displayed.");
        try {
            return elements.snoHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Name header is displayed
     */
    public boolean isNameHeaderDisplayed() {
        TestLogger.info("Checking if Name header is displayed.");
        try {
            return elements.nameHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Created By header is displayed
     */
    public boolean isCreatedByHeaderDisplayed() {
        TestLogger.info("Checking if Created By header is displayed.");
        try {
            return elements.createdByHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Updated By header is displayed
     */
    public boolean isUpdatedByHeaderDisplayed() {
        TestLogger.info("Checking if Updated By header is displayed.");
        try {
            return elements.updatedByHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Action header is displayed
     */
    public boolean isActionHeaderDisplayed() {
        TestLogger.info("Checking if Action header is displayed.");
        try {
            return elements.actionHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get template name from specific row (0-based index)
     */
    public String getTemplateNameFromRow(int rowIndex) {
        TestLogger.info("Getting template name from row: " + rowIndex);
        try {
            List<WebElement> templateNames = elements.templateNameCells;
            if (templateNames.size() > rowIndex) {
                return templateNames.get(rowIndex).getText();
            }
        } catch (Exception e) {
            TestLogger.error("Could not get template name: " + e.getMessage());
        }
        return "";
    }

    /**
     * Get first template name
     */
    public String getFirstTemplateName() {
        TestLogger.info("Getting first template name...");
        try {
            return elements.firstTemplateName.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get first template name: " + e.getMessage());
            return "";
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

    // ==================== ACTION BUTTON OPERATIONS ====================

    /**
     * Click action button for first row
     */
    public KeyPositionTemplatePage clickFirstActionButton() {
        TestLogger.info("Clicking first action button...");
        elements.firstActionButton.click();
        return this;
    }

    /**
     * Click action button for specific row (1-based index)
     */
    public KeyPositionTemplatePage clickActionButton(int rowIndex) {
        TestLogger.info("Clicking action button for row: " + rowIndex);
        WebElement actionBtn = driver.findElement(elements.getActionButtonLocator(rowIndex));
        actionBtn.click();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        return this;
    }




    /**
     * Click Edit button
     */
    public KeyPositionTemplatePage clickEditButton() {
        TestLogger.info("Clicking Edit button...");
        waitHelper.waitForElementClickable(elements.editButtonLocator).click();
        return this;
    }

    /**
     * Click Delete button
     */
    public KeyPositionTemplatePage clickDeleteButton() {
        TestLogger.info("Clicking Delete button...");
        waitHelper.waitForElementClickable(elements.deleteButtonLocator).click();
        return this;
    }

    // ==================== MODAL OPERATIONS ====================

    /**
     * Check if modal is displayed
     */
    public boolean isModalDisplayed() {
        TestLogger.info("Checking if modal is displayed.");
        try {
            WebElement modal = waitHelper.waitForElementVisible(elements.modalLocator);
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isEditModalLabelDisplayed() {
        TestLogger.info("Checking the edit modal label is displayed.");
        try {
            return elements.EditmodalHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



    /**
     * Get modal header text
     */
    public String getModalHeaderText() {
        TestLogger.info("Getting modal header text...");
        try {
            WebElement header = waitHelper.waitForElementVisible(elements.modalHeaderLocator);
            return header.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get modal header text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if modal close button is displayed
     */
    public boolean isModalCloseButtonDisplayed() {
        TestLogger.info("Checking if modal close button is displayed.");
        try {
            return elements.modalCloseButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click modal close button
     */
    public KeyPositionTemplatePage clickModalCloseButton() {
        TestLogger.info("Clicking modal close button...");
        elements.modalCloseButton.click();
        return this;
    }

    /**
     * Close modal if displayed
     */
    public KeyPositionTemplatePage closeModalIfDisplayed() {
        TestLogger.info("Closing modal if displayed...");
        if (isModalDisplayed()) {
            clickModalCloseButton();
        }
        return this;
    }

    // ==================== FORM OPERATIONS ====================

    /**
     * Check if template name input is displayed
     */
    public boolean isTemplateNameInputDisplayed() {
        TestLogger.info("Checking if template name input is displayed.");
        try {
            WebElement input = waitHelper.waitForElementVisible(elements.templateNameInputLocator);
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter template name
     */
    public KeyPositionTemplatePage enterTemplateName(String templateName) {
        TestLogger.info("Entering template name: " + templateName);
        WebElement input = waitHelper.waitForElementVisible(elements.templateNameInputLocator);
        input.clear();
        input.sendKeys(templateName);
        return this;
    }

    /**
     * Clear template name input
     */
    public KeyPositionTemplatePage clearTemplateNameInput() {
        TestLogger.info("Clearing template name input...");
        WebElement input = waitHelper.waitForElementVisible(elements.templateNameInputLocator);
        input.clear();
        return this;
    }

    /**
     * Check if Cancel button is displayed
     */
    public boolean isCancelButtonDisplayed() {
        TestLogger.info("Checking if Cancel button is displayed.");
        try {
            return elements.cancelButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Cancel button
     */
    public KeyPositionTemplatePage clickCancelButton() {
        TestLogger.info("Clicking Cancel button...");
        elements.cancelButton.click();
        return this;
    }

    /**
     * Check if Submit button is displayed
     */
    public boolean isSubmitButtonDisplayed() {
        TestLogger.info("Checking if Submit button is displayed.");
        try {
            return elements.submitButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Submit button
     */
    public KeyPositionTemplatePage clickSubmitButton() {
        TestLogger.info("Clicking Submit button...");
        elements.submitButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    /**
     * Check if Save button is displayed
     */
    public boolean isSaveButtonDisplayed() {
        TestLogger.info("Checking if Save button is displayed.");
        try {
            return elements.saveButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Save button
     */
    public KeyPositionTemplatePage clickSaveButton() {
        TestLogger.info("Clicking Save button...");
        elements.saveButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    // ==================== DELETE CONFIRMATION ====================

    /**
     * Check if delete confirmation dialog is displayed
     */
    public boolean isDeleteConfirmDialogDisplayed() {
        TestLogger.info("Checking if delete confirmation dialog is displayed.");
        try {
            WebElement dialog = waitHelper.waitForElementVisible(elements.deleteConfirmDialogLocator);
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



    /**
     * Click No button in delete confirmation
     */
    public KeyPositionTemplatePage clickNoButton() {
        TestLogger.info("Clicking No button...");
        elements.noButton.click();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        return this;
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
        TestLogger.info("Getting pagination info text...");
        try {
            return elements.paginationInfo.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get pagination info text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get page number info text
     */
    public String getPageNumberInfoText() {
        TestLogger.info("Getting page number info text...");
        try {
            return elements.pageNumberInfo.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get page number info text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if first page button is displayed
     */
    public boolean isFirstPageButtonDisplayed() {
        TestLogger.info("Checking if first page button is displayed.");
        try {
            return elements.firstPageButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if previous page button is displayed
     */
    public boolean isPreviousPageButtonDisplayed() {
        TestLogger.info("Checking if previous page button is displayed.");
        try {
            return elements.previousPageButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if next page button is displayed
     */
    public boolean isNextPageButtonDisplayed() {
        TestLogger.info("Checking if next page button is displayed.");
        try {
            return elements.nextPageButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if last page button is displayed
     */
    public boolean isLastPageButtonDisplayed() {
        TestLogger.info("Checking if last page button is displayed.");
        try {
            return elements.lastPageButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if next page button is enabled
     */
    public boolean isNextPageButtonEnabled() {
        TestLogger.info("Checking if next page button is enabled.");
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
        TestLogger.info("Checking if previous page button is enabled.");
        try {
            String buttonClass = elements.previousPageButton.getAttribute("class");
            return buttonClass == null || !buttonClass.contains("pointer-events-none");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click first page button
     */
    public KeyPositionTemplatePage clickFirstPageButton() {
        TestLogger.info("Clicking first page button...");
        elements.firstPageButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    /**
     * Click previous page button
     */
    public KeyPositionTemplatePage clickPreviousPageButton() {
        TestLogger.info("Clicking previous page button...");
        elements.previousPageButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    /**
     * Click next page button
     */
    public KeyPositionTemplatePage clickNextPageButton() {
        TestLogger.info("Clicking next page button...");
        elements.nextPageButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    /**
     * Click last page button
     */
    public KeyPositionTemplatePage clickLastPageButton() {
        TestLogger.info("Clicking last page button...");
        elements.lastPageButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    // ==================== TOAST/NOTIFICATION OPERATIONS ====================

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

    /**
     * Get toast message text
     */
    public String getToastMessageText() {
        TestLogger.info("Getting toast message text...");
        try {
            WebElement toast = waitHelper.waitForElementVisible(elements.toastMessageLocator);
            return toast.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get toast message text: " + e.getMessage());
            return "";
        }
    }

    // ==================== VALIDATION ERROR OPERATIONS ====================

    /**
     * Check if validation error is displayed
     */
    public boolean isValidationErrorDisplayed() {
        TestLogger.info("Checking if validation error is displayed.");
        try {
            return elements.validationError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get validation error text
     */
    public String getValidationErrorText() {
        TestLogger.info("Getting validation error text...");
        try {
            return elements.validationError.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get validation error text: " + e.getMessage());
            return "";
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
    public KeyPositionTemplatePage waitForLoadingComplete() {
        TestLogger.info("Waiting for loading to complete...");
        try {
            waitHelper.waitForElementInvisible(elements.loadingSpinnerLocator);
        } catch (Exception e) {
            // Loading spinner might not appear for quick operations
        }
        return this;
    }

    // ==================== COMPOSITE OPERATIONS ====================

    /**
     * Add new template with given name
     */
    public KeyPositionTemplatePage addNewTemplate(String templateName) {
        TestLogger.info("Adding new template: " + templateName);
        clickAddTemplateButton();
        enterTemplateName(templateName);
        clickSubmitButton();
        return this;
    }



    /**
     * Edit first template
     */
    public KeyPositionTemplatePage editFirstTemplate() {
        TestLogger.info("Editing first template...");
        clickFirstActionButton();
        clickEditButton();
        return this;
    }

    /**
     * Delete first template
     */
    public KeyPositionTemplatePage deleteFirstTemplate() {
        TestLogger.info("Deleting first template...");
        clickFirstActionButton();
        clickDeleteButton();
        return this;
    }

    /**
     * Verify all pagination controls are displayed
     */
    public boolean verifyAllPaginationControlsDisplayed() {
        TestLogger.info("Verifying all pagination controls are displayed...");
        boolean allDisplayed = isFirstPageButtonDisplayed()
                && isPreviousPageButtonDisplayed()
                && isNextPageButtonDisplayed()
                && isLastPageButtonDisplayed();
        return allDisplayed;
    }

    /**
     * Verify all table headers are displayed
     */
    public boolean verifyAllTableHeadersDisplayed() {
        TestLogger.info("Verifying all table headers are displayed...");
        boolean allDisplayed = isSnoHeaderDisplayed()
                && isNameHeaderDisplayed()
                && isCreatedByHeaderDisplayed()
                && isUpdatedByHeaderDisplayed()
                && isActionHeaderDisplayed();
        return allDisplayed;
    }

    /**
     * Get total records count from pagination text
     * Parses text like "Showing From 1 To 10 of 24 results" to extract 24
     */
    public int getTotalRecordsFromPagination() {
        TestLogger.info("Getting total records from pagination...");
        try {
            String paginationText = getPaginationInfoText();
            TestLogger.info("Pagination text: " + paginationText);
            // Parse "Showing From X To Y of Z results" format
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

    // ==================== ADD KEY POSITION TEMPLATE MODAL OPERATIONS ====================

    /**
     * Check if Add Template modal title is displayed
     */
    public boolean isAddTemplateModalTitleDisplayed() {
        TestLogger.info("Checking if Add Template modal title is displayed.");
        try {
            WebElement title = waitHelper.waitForElementVisible(elements.addTemplateModalTitleLocator);
            return title.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Add Template modal title text
     */
    public String getAddTemplateModalTitleText() {
        TestLogger.info("Getting Add Template modal title text...");
        try {
            WebElement title = waitHelper.waitForElementVisible(elements.addTemplateModalTitleLocator);
            return title.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get modal title text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if Add Template modal description is displayed
     */
    public boolean isAddTemplateModalDescriptionDisplayed() {
        TestLogger.info("Checking if Add Template modal description is displayed.");
        try {
            WebElement desc = waitHelper.waitForElementVisible(elements.addTemplateModalDescriptionLocator);
            return desc.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Add Template modal description text
     */
    public String getAddTemplateModalDescriptionText() {
        TestLogger.info("Getting Add Template modal description text...");
        try {
            WebElement desc = waitHelper.waitForElementVisible(elements.addTemplateModalDescriptionLocator);
            return desc.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get modal description text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if Name label is displayed in modal
     */
    public boolean isNameLabelDisplayed() {
        TestLogger.info("Checking if Name label is displayed.");
        try {
            return elements.nameLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Name input field is displayed in modal
     */
    public boolean isNameInputDisplayed() {
        TestLogger.info("Checking if Name input is displayed.");
        try {
            WebElement input = waitHelper.waitForElementVisible(elements.nameInputLocator);
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Name input placeholder text
     */
    public String getNameInputPlaceholder() {
        TestLogger.info("Getting Name input placeholder...");
        try {
            WebElement input = waitHelper.waitForElementVisible(elements.nameInputLocator);
            return input.getAttribute("placeholder");
        } catch (Exception e) {
            TestLogger.error("Could not get placeholder: " + e.getMessage());
            return "";
        }
    }

    /**
     * Enter name in the modal Name input field
     */
    public KeyPositionTemplatePage enterName(String name) {
        TestLogger.info("Entering name: " + name);
        WebElement input = waitHelper.waitForElementVisible(elements.nameInputLocator);
        input.clear();
        input.sendKeys(name);
        return this;
    }

    /**
     * Clear the Name input field
     */
    public KeyPositionTemplatePage clearNameInput() {
        TestLogger.info("Clearing Name input...");
        WebElement input = waitHelper.waitForElementVisible(elements.nameInputLocator);
        input.clear();
        return this;
    }

    // ==================== KEY POSITIONS TABLE OPERATIONS ====================

    /**
     * Check if Key Positions table is displayed in modal
     */
    public boolean isKeyPositionsTableDisplayed() {
        TestLogger.info("Checking if Key Positions table is displayed.");
        try {
            WebElement table = waitHelper.waitForElementVisible(elements.keyPositionsTableLocator);
            return table.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get count of Key Positions table headers
     */
    public int getKeyPositionsTableHeaderCount() {
        TestLogger.info("Getting Key Positions table header count...");
        try {
            List<WebElement> headers = driver.findElements(elements.keyPositionsTableHeadersLocator);
            return headers.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get Key Positions table header texts
     */
    public List<String> getKeyPositionsTableHeaderTexts() {
        TestLogger.info("Getting Key Positions table header texts...");
        List<String> headerTexts = new ArrayList<>();
        try {
            List<WebElement> headers = driver.findElements(elements.keyPositionsTableHeadersLocator);
            for (WebElement header : headers) {
                headerTexts.add(header.getText().trim());
            }
        } catch (Exception e) {
            TestLogger.error("Could not get header texts: " + e.getMessage());
        }
        return headerTexts;
    }

    /**
     * Check if Enable header is displayed
     */
    public boolean isEnableHeaderDisplayed() {
        TestLogger.info("Checking if Enable header is displayed.");
        try {
            return elements.enableHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Name header in Key Positions table is displayed
     */
    public boolean isKeyPositionNameHeaderDisplayed() {
        TestLogger.info("Checking if Key Position Name header is displayed.");
        try {
            return elements.keyPositionNameHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if position number header is displayed (1-24)
     */
    public boolean isPositionNumberHeaderDisplayed(int positionNumber) {
        TestLogger.info("Checking if position " + positionNumber + " header is displayed.");
        try {
            WebElement header = driver.findElement(elements.getPositionHeaderLocator(positionNumber));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify all position headers (1-24) are displayed
     */
    public boolean verifyAllPositionHeadersDisplayed() {
        TestLogger.info("Verifying all position headers (1-24) are displayed...");
        for (int i = 1; i <= 24; i++) {
            if (!isPositionNumberHeaderDisplayed(i)) {
                TestLogger.error("Position header " + i + " is not displayed.");
                return false;
            }
        }
        return true;
    }

    // ==================== KEY POSITION ROWS OPERATIONS ====================

    /**
     * Get count of key position rows
     */
    public int getKeyPositionRowCount() {
        TestLogger.info("Getting key position row count...");
        try {
            List<WebElement> rows = driver.findElements(elements.keyPositionRowsLocator);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get all key position names
     */
    public List<String> getAllKeyPositionNames() {
        TestLogger.info("Getting all key position names...");
        List<String> names = new ArrayList<>();
        try {
            List<WebElement> nameElements = driver.findElements(elements.keyPositionNamesLocator);
            for (WebElement nameEl : nameElements) {
                names.add(nameEl.getText().trim());
            }
        } catch (Exception e) {
            TestLogger.error("Could not get key position names: " + e.getMessage());
        }
        return names;
    }

    /**
     * Get key position name from specific row (1-based index)
     */
    public String getKeyPositionNameFromRow(int rowIndex) {
        TestLogger.info("Getting key position name from row: " + rowIndex);
        try {
            WebElement nameEl = driver.findElement(elements.getKeyPositionNameLocator(rowIndex));
            return nameEl.getText().trim();
        } catch (Exception e) {
            TestLogger.error("Could not get key position name: " + e.getMessage());
            return "";
        }
    }

    // ==================== ENABLE CHECKBOX OPERATIONS ====================

    /**
     * Get count of enable checkboxes
     */
    public int getEnableCheckboxCount() {
        TestLogger.info("Getting enable checkbox count...");
        try {
            List<WebElement> checkboxes = driver.findElements(elements.enableCheckboxesLocator);
            return checkboxes.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isEnableCheckboxChecked(int rowIndex) {
        TestLogger.info("Checking if enable checkbox in row " + rowIndex + " is checked.");
        try {
            WebElement checkbox = driver.findElement(elements.getEnableCheckboxLocator(rowIndex));
            String ariaChecked = checkbox.getAttribute("aria-checked");
            return "true".equals(ariaChecked);
        } catch (Exception e) {
            return false;
        }
    }

    public KeyPositionTemplatePage clickEnableCheckbox(int rowIndex) {
        TestLogger.info("Clicking enable checkbox in row: " + rowIndex);
        WebElement checkbox = driver.findElement(elements.getEnableCheckboxLocator(rowIndex));
        checkbox.click();
        TestLogger.info("Clicked the enabled checkbox in row: " + rowIndex);
        return this;
    }

    public KeyPositionTemplatePage clickEnableCheckboxByName(String positionName) {
        TestLogger.info("Clicking enable checkbox for position: " + positionName);
        WebElement checkbox = driver.findElement(elements.getEnableCheckboxByPositionName(positionName));
        checkbox.click();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        return this;
    }

    /**
     * Enable multiple key positions by their names
     */
    public KeyPositionTemplatePage enableKeyPositions(String... positionNames) {
        TestLogger.info("Enabling key positions: " + String.join(", ", positionNames));
        for (String name : positionNames) {
            try {
                WebElement checkbox = driver.findElement(elements.getEnableCheckboxByPositionName(name));
                String ariaChecked = checkbox.getAttribute("aria-checked");
                if (!"true".equals(ariaChecked)) {
                    checkbox.click();
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                TestLogger.error("Could not enable position: " + name);
            }
        }
        return this;
    }

    // ==================== POSITION SELECTOR OPERATIONS ====================

    /**
     * Get count of position selector buttons
     */
    public int getPositionSelectorCount() {
        TestLogger.info("Getting position selector count...");
        try {
            List<WebElement> selectors = driver.findElements(elements.positionSelectorButtonsLocator);
            return selectors.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Click position selector button by row and position number
     */
    public KeyPositionTemplatePage clickPositionSelector(int rowIndex, int positionNumber) {
        TestLogger.info("Clicking position selector - Row: " + rowIndex + ", Position: " + positionNumber);
        WebElement selector = driver.findElement(elements.getPositionSelectorLocator(rowIndex, positionNumber));
        selector.click();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        return this;
    }

    /**
     * Check if position selector is selected (active)
     */
    public boolean isPositionSelectorSelected(int rowIndex, int positionNumber) {
        TestLogger.info("Checking if position selector is selected - Row: " + rowIndex + ", Position: " + positionNumber);
        try {
            WebElement selector = driver.findElement(elements.getPositionSelectorLocator(rowIndex, positionNumber));
            String classes = selector.getAttribute("class");
            // Check for active/selected class
            return classes != null && (classes.contains("bg-primary") || classes.contains("active") || classes.contains("selected"));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Select multiple positions for a specific row
     */
    public KeyPositionTemplatePage selectPositions(int rowIndex, int... positionNumbers) {
        TestLogger.info("Selecting positions for row " + rowIndex + ": " + java.util.Arrays.toString(positionNumbers));
        for (int posNum : positionNumbers) {
            clickPositionSelector(rowIndex, posNum);
        }
        return this;
    }

    // ==================== MODAL BUTTON OPERATIONS ====================

    /**
     * Check if Modal Cancel button is displayed
     */
    public boolean isModalCancelButtonDisplayed() {
        TestLogger.info("Checking if Modal Cancel button is displayed.");
        try {
            return elements.modalCancelButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Modal Cancel button
     */
    public KeyPositionTemplatePage clickModalCancelButton() {
        TestLogger.info("Clicking Modal Cancel button...");
        elements.modalCancelButton.click();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        return this;
    }

    /**
     * Check if Modal Submit button is displayed
     */
    public boolean isModalSubmitButtonDisplayed() {
        TestLogger.info("Checking if Modal Submit button is displayed.");
        try {
            return elements.modalSubmitButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Modal Submit button is enabled
     */
    public boolean isModalSubmitButtonEnabled() {
        TestLogger.info("Checking if Modal Submit button is enabled.");
        try {
            String disabled = elements.modalSubmitButton.getAttribute("disabled");
            return disabled == null || !disabled.equals("true");
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isModalSubmitButtonDisabled() {
        TestLogger.info("Checking if Modal Submit button is disabled.");
        try {
            String disabled = elements.modalSubmitButton.getAttribute("disabled");
            return disabled != null && disabled.equals("true");
        } catch (Exception e) {
            return true; // assume disabled if an error occurs
        }
    }

    /**
     * Click Modal Submit button
     */
    public KeyPositionTemplatePage clickModalSubmitButton() {
        TestLogger.info("Clicking Modal Submit button...");
        elements.modalSubmitButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    // ==================== SPECIFIC KEY POSITION VERIFICATION ====================

    /**
     * Check if Fill p.tank positions are displayed
     */
    public boolean isFillPTankPositionsDisplayed() {
        TestLogger.info("Checking if Fill p.tank positions are displayed.");
        try {
            List<WebElement> positions = driver.findElements(elements.fillPTankPositionsLocator);
            return !positions.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get count of Fill p.tank positions
     */
    public int getFillPTankPositionsCount() {
        try {
            return driver.findElements(elements.fillPTankPositionsLocator).size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Check if El.head dis. positions are displayed
     */
    public boolean isElHeadDisPositionsDisplayed() {
        TestLogger.info("Checking if El.head dis. positions are displayed.");
        try {
            List<WebElement> positions = driver.findElements(elements.elHeadDisPositionsLocator);
            return !positions.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get count of El.head dis. positions
     */
    public int getElHeadDisPositionsCount() {
        try {
            return driver.findElements(elements.elHeadDisPositionsLocator).size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Check if hyd.ch.dis. positions are displayed
     */
    public boolean isHydChDisPositionsDisplayed() {
        TestLogger.info("Checking if hyd.ch.dis. positions are displayed.");
        try {
            List<WebElement> positions = driver.findElements(elements.hydChDisPositionsLocator);
            return !positions.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get count of hyd.ch.dis. positions
     */
    public int getHydChDisPositionsCount() {
        try {
            return driver.findElements(elements.hydChDisPositionsLocator).size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Check if Post mix oil position is displayed
     */
    public boolean isPostMixOilPositionDisplayed() {
        TestLogger.info("Checking if Post mix oil position is displayed.");
        try {
            return elements.postMixOilPosition.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Tank truck key position is displayed
     */
    public boolean isTankTruckKeyPositionDisplayed() {
        TestLogger.info("Checking if Tank truck key position is displayed.");
        try {
            return elements.tankTruckKeyPosition.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if UG tank padlock position is displayed
     */
    public boolean isUGTankPadlockPositionDisplayed() {
        TestLogger.info("Checking if UG tank padlock position is displayed.");
        try {
            return elements.ugTankPadlockPosition.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Spare (IT room) position is displayed
     */
    public boolean isSpareITRoomPositionDisplayed() {
        TestLogger.info("Checking if Spare (IT room) position is displayed.");
        try {
            return elements.spareITRoomPosition.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== COMPOSITE MODAL OPERATIONS ====================

    /**
     * Verify all modal elements are displayed
     */
    public boolean verifyAllAddTemplateModalElementsDisplayed() {
        TestLogger.info("Verifying all Add Template modal elements are displayed...");
        return isAddTemplateModalTitleDisplayed()
                && isAddTemplateModalDescriptionDisplayed()
                && isNameLabelDisplayed()
                && isNameInputDisplayed()
                && isKeyPositionsTableDisplayed()
                && isEnableHeaderDisplayed()
                && isKeyPositionNameHeaderDisplayed()
                && isModalCancelButtonDisplayed()
                && isModalSubmitButtonDisplayed();
    }

    /**
     * Create a new key position template with specified name and positions
     */
    public KeyPositionTemplatePage createKeyPositionTemplate(String templateName, String... enabledPositions) {
        TestLogger.info("Creating key position template: " + templateName);
        clickAddTemplateButton();
        enterName(templateName);
        enableKeyPositions(enabledPositions);
        clickModalSubmitButton();
        return this;
    }

    /**
     * Verify key position names in modal
     */
    public boolean verifyKeyPositionNamesExist(String... expectedNames) {
        TestLogger.info("Verifying key position names exist...");
        List<String> actualNames = getAllKeyPositionNames();
        for (String expectedName : expectedNames) {
            boolean found = actualNames.stream().anyMatch(name -> name.contains(expectedName));
            if (!found) {
                TestLogger.error("Key position name not found: " + expectedName);
                return false;
            }
        }
        return true;
    }

    // ==================== EDIT KEY POSITION TEMPLATE MODAL OPERATIONS ====================

    /**
     * Check if Edit Template modal title is displayed
     */
    public boolean isEditTemplateModalTitleDisplayed() {
        TestLogger.info("Checking if Edit Template modal title is displayed.");
        try {
            WebElement title = waitHelper.waitForElementVisible(elements.editTemplateModalTitleLocator);
            return title.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Edit Template modal title text
     */
    public String getEditTemplateModalTitleText() {
        TestLogger.info("Getting Edit Template modal title text...");
        try {
            WebElement title = waitHelper.waitForElementVisible(elements.editTemplateModalTitleLocator);
            return title.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get Edit modal title text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if Edit Template modal description is displayed
     */
    public boolean isEditTemplateModalDescriptionDisplayed() {
        TestLogger.info("Checking if Edit Template modal description is displayed.");
        try {
            WebElement desc = waitHelper.waitForElementVisible(elements.editTemplateModalDescriptionLocator);
            return desc.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Edit Template modal description text
     */
    public String getEditTemplateModalDescriptionText() {
        TestLogger.info("Getting Edit Template modal description text...");
        try {
            WebElement desc = waitHelper.waitForElementVisible(elements.editTemplateModalDescriptionLocator);
            return desc.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get Edit modal description text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if Template Name label is displayed in Edit modal
     */
    public boolean isTemplateNameLabelDisplayed() {
        TestLogger.info("Checking if Template Name label is displayed.");
        try {
            return elements.templateNameLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Edit Template Name input field is displayed
     */
    public boolean isEditTemplateNameInputDisplayed() {
        TestLogger.info("Checking if Edit Template Name input is displayed.");
        try {
            WebElement input = waitHelper.waitForElementVisible(elements.editTemplateNameInputLocator);
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Edit Template Name input placeholder text
     */
    public String getEditTemplateNameInputPlaceholder() {
        TestLogger.info("Getting Edit Template Name input placeholder...");
        try {
            WebElement input = waitHelper.waitForElementVisible(elements.editTemplateNameInputLocator);
            return input.getAttribute("placeholder");
        } catch (Exception e) {
            TestLogger.error("Could not get placeholder: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get Edit Template Name input current value
     */
    public String getEditTemplateNameInputValue() {
        TestLogger.info("Getting Edit Template Name input value...");
        try {
            WebElement input = waitHelper.waitForElementVisible(elements.editTemplateNameInputLocator);
            return input.getAttribute("value");
        } catch (Exception e) {
            TestLogger.error("Could not get input value: " + e.getMessage());
            return "";
        }
    }

    /**
     * Enter template name in the Edit modal input field
     */
    public KeyPositionTemplatePage enterEditTemplateName(String templateName) {
        TestLogger.info("Entering template name in Edit modal: " + templateName);
        WebElement input = waitHelper.waitForElementVisible(elements.editTemplateNameInputLocator);
        input.clear();
        input.sendKeys(templateName);
        return this;
    }

    /**
     * Clear the Edit Template Name input field
     */
    public KeyPositionTemplatePage clearEditTemplateNameInput() {
        TestLogger.info("Clearing Edit Template Name input...");
        WebElement input = waitHelper.waitForElementVisible(elements.editTemplateNameInputLocator);
        input.clear();
        return this;
    }

    /**
     * Check if Key Positions label is displayed
     */
    public boolean isKeyPositionsLabelDisplayed() {
        TestLogger.info("Checking if Key Positions label is displayed.");
        try {
            return elements.keyPositionsLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== ACTION POPOVER OPERATIONS ====================

    /**
     * Check if Action Popover is displayed
     */
    public boolean isActionPopoverDisplayed() {
        TestLogger.info("Checking if Action Popover is displayed.");
        try {
            WebElement popover = waitHelper.waitForElementVisible(elements.actionPopoverLocator);
            return popover.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if Actions header is displayed in popover
     */
    public boolean isActionsHeaderDisplayed() {
        TestLogger.info("Checking if Actions header is displayed.");
        try {
            return elements.actionsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Actions header text
     */
    public String getActionsHeaderText() {
        TestLogger.info("Getting Actions header text...");
        try {
            return elements.actionsHeader.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get Actions header text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if Edit button is displayed in popover
     */
    public boolean isPopoverEditButtonDisplayed() {
        TestLogger.info("Checking if Edit button is displayed in popover.");
        try {
            return elements.popoverEditButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Edit button in popover
     */
    public KeyPositionTemplatePage clickPopoverEditButton() {
        TestLogger.info("Clicking Edit button in popover...");
        waitHelper.waitForElementClickable(elements.popoverEditButtonLocator).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return this;
    }

    // ==================== EDIT TEMPLATE COMPOSITE OPERATIONS ====================

    /**
     * Open Edit modal for first template
     */
    public KeyPositionTemplatePage openEditModalForFirstTemplate() {
        TestLogger.info("Opening Edit modal for first template...");
        clickFirstActionButton();
        clickPopoverEditButton();
        return this;
    }

    /**
     * Verify all Edit Template modal elements are displayed
     */
    public boolean verifyAllEditTemplateModalElementsDisplayed() {
        TestLogger.info("Verifying all Edit Template modal elements are displayed...");
        return isEditTemplateModalTitleDisplayed()
                && isEditTemplateModalDescriptionDisplayed()
                && isTemplateNameLabelDisplayed()
                && isEditTemplateNameInputDisplayed()
                && isKeyPositionsLabelDisplayed()
                && isKeyPositionsTableDisplayed()
                && isEnableHeaderDisplayed()
                && isKeyPositionNameHeaderDisplayed()
                && isModalCancelButtonDisplayed()
                && isModalSubmitButtonDisplayed();
    }

    /**
     * Update template name and submit
     */
    public KeyPositionTemplatePage updateTemplateName(String newName) {
        TestLogger.info("Updating template name to: " + newName);
        enterEditTemplateName(newName);
        clickModalSubmitButton();
        return this;
    }

    /**
     * Verify Edit modal has pre-populated template name
     */
    public boolean verifyEditModalHasPrePopulatedName() {
        TestLogger.info("Verifying Edit modal has pre-populated template name...");
        String value = getEditTemplateNameInputValue();
        return value != null && !value.isEmpty();
    }

    /**
     * Verify template name can be modified in Edit modal
     */
    public boolean verifyTemplateNameCanBeModified(String newName) {
        TestLogger.info("Verifying template name can be modified...");
        String originalValue = getEditTemplateNameInputValue();
        enterEditTemplateName(newName);
        String newValue = getEditTemplateNameInputValue();
        return !originalValue.equals(newValue) && newName.equals(newValue);
    }

    public KeyPositionTemplatePage addNewTemplate() {
        TestLogger.info("Add a new key position template");
        clickAddTemplateButton().enterTemplateName("Key Position Template 1");
        for (int key = 1; key <= 24; key++) {
            clickPositionSelector(key, key);
        }
        return this;
    }
    public KeyPositionTemplatePage addNewTemplatewithemptyname() {
        TestLogger.info("Add a new key position template with empty name");
        clickAddTemplateButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        for (int key = 1; key <= 24; key++) {
            clickPositionSelector(key, key);
        }
        return this;
    }
    public KeyPositionTemplatePage addNewTemplatewithemptyKeyposition() {
        TestLogger.info("Add a new key position template with empty Keyposition");
        clickAddTemplateButton().enterTemplateName("Key Position Template 2");
        return this;
    }
    public KeyPositionTemplatePage addNewTemplateUsingSpacesInName() {
        TestLogger.info("Add a new key position template using spaces in name");
        clickAddTemplateButton().enterTemplateName("Key Position Template ");
        for (int key = 1; key <= 24; key++) {
            clickPositionSelector(key, key);
        }
        return this;
    }
    public KeyPositionTemplatePage addNewTemplateUsingSpecialCharactersInName() {
        TestLogger.info("Add a new key position template using special characters in name");
        clickAddTemplateButton().enterTemplateName("Template#$%^&*");
        for (int key = 1; key <= 24; key++) {
            clickPositionSelector(key, key);
        }
        return this;
    }
    public KeyPositionTemplatePage addDuplicateTemplate() {
        TestLogger.info("create a duplicate key position template");
        clickAddTemplateButton().enterTemplateName("new template");
        for (int key = 1; key <= 24; key++) {
            clickPositionSelector(key, key);
        }
        return this;
    }
    public KeyPositionTemplatePage selectallthekeypositions() {
        TestLogger.info("select unique position for all the keyboxes");
        clickAddTemplateButton().enterTemplateName("new template");
        for (int key = 1; key <= 24; key++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            clickPositionSelector(key, key);
        }
        return this;
    }
    public KeyPositionTemplatePage disableKeyBoxesfrom24to15() {
        TestLogger.info("Disable all keyboxes");
        for (int i = 24; i >= 16; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
         clickEnableCheckbox(i);
        }
        return this;
    }
    public KeyPositionTemplatePage disableKeyBoxesfrom15to7() {
        TestLogger.info("Disable all keyboxes");
        WebElement startElement = driver.findElement(By.xpath("(//button[@role='checkbox']) [16]"));
        startElement.sendKeys(Keys.PAGE_UP);
        for (int i = 15; i >= 7; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            clickEnableCheckbox(i);
        }
        return this;
    }
    public KeyPositionTemplatePage disableKeyBoxesfrom7to2() {
        TestLogger.info("Disable all keyboxes");
        WebElement startElements = driver.findElement(By.xpath("(//button[@role='checkbox']) [7]"));
        startElements.sendKeys(Keys.PAGE_UP);
        for (int i = 6; i >= 1; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            clickEnableCheckbox(i);
        }
        return this;
    }
    public boolean isErrorMessageOfLessKeyboxedEnabledDisplayed() {
        TestLogger.info("Checking the error message of less key boxes enabled");
        try {
            return elements.Errormsgofminimumkeyboxenabled.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isPositionSelectorButtonsDisplayed() {
        TestLogger.info("Checking if position selector buttons are displayed");
        try {
            if (elements.positionSelectorButtons == null || elements.positionSelectorButtons.isEmpty()) {
                return false;
            }

            for (WebElement button : elements.positionSelectorButtons) {
                if (button.isDisplayed()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isKeyboxesCheckboxesDisplayed() {
        TestLogger.info("Checking if keyboxes checkbox are displayed");
        try {
            if (elements.enableCheckboxes == null || elements.enableCheckboxes.isEmpty()) {
                return false;
            }

            for (WebElement button : elements.enableCheckboxes) {
                if (button.isDisplayed()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isKeyPositionSelectionDisabled(int row, int startPosition, int endPosition) {
        TestLogger.info("Checking if position selector is disabled");
        try {
            WebElement selector = driver.findElement(
                    elements.getKeyPositionSelectorLocator(row,startPosition ,endPosition ));
            return !selector.isEnabled()
                    || selector.getAttribute("disabled") != null;
        } catch (Exception e) {
            return true;
        }
    }

//keypositiontemplatepage

}
