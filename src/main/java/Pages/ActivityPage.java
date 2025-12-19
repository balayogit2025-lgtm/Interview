package Pages;

import Elements.ActivitiyPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ActivityPage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final CommonUtil commonUtil;
    private final ActivitiyPageElements activityElements;


    public ActivityPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.commonUtil = new CommonUtil(driver);
        this.activityElements = new ActivitiyPageElements(driver);
        PageFactory.initElements(driver, this);
    }

    // --- Page Navigation & Verification ---

    public void navigateToActivityPage() {
        TestLogger.info("Navigating to Activity page...");
        waitHelper.waitForElementClickable(By.xpath("//a[@href='/activity']")).click();
        waitHelper.waitForElementVisible( activityElements.pageHeader);
        TestLogger.success("Successfully navigated to Activity page.");
    }

    public boolean isPageHeaderDisplayed() {
       WebElement pageHeader =  waitHelper.waitForElementVisible( activityElements.pageHeader);
        return pageHeader.isDisplayed();
    }

    public String getPageHeaderText() {
        WebElement pageHeader =  waitHelper.waitForElementVisible( activityElements.pageHeader);
        return pageHeader.getText();
    }

    public boolean isActivityTableDisplayed() {
        TestLogger.info("Checking if Activity table is displayed.");

        return activityElements.activityTable.isDisplayed();
    }

    public List<String> getTableHeaders() {
        TestLogger.info("Getting table headers...");
        return activityElements.tableHeaders.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isAddActivityButtonDisplayed() {
        TestLogger.info("Checking if 'Add Activity' button is displayed.");
        try {
            return activityElements.addActivityButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("'Add Activity' button not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        TestLogger.info("Checking if 'Search' button is displayed.");
        try {
            return activityElements.searchButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("'Search' button not found: " + e.getMessage());
            return false;
        }
    }

    // --- Main Page Actions ---

    public void clickAddActivityButton() {
        TestLogger.info("Clicking 'Add Activity' button...");
        activityElements.addActivityButton.click();
        waitHelper.waitForElementVisible((By) activityElements.activityModal);
        TestLogger.success("'Add Activity' modal is open.");
    }

    public boolean isActivityModalDisplayed() {
        TestLogger.info("Checking if 'Add Activity' modal is displayed.");
        try {
            WebElement activityModal = waitHelper.waitForElementVisible((By) activityElements.activityModal);
            return activityModal.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("'Add Activity' modal not found: " + e.getMessage());
            return false;
        }
    }

    public void searchActivity(String activityName) {
        TestLogger.info("Searching for activity: " + activityName);
        activityElements.searchButton.click();
        waitHelper.waitForElementVisible((By) activityElements.searchInput).sendKeys(activityName);
        TestLogger.info("Search initiated for: " + activityName);
    }

    public int getTableRowCount() {
        return activityElements.tableRows.size();
    }

    public String getFirstRowText(int columnIndex) {
        if (getTableRowCount() > 0) {
            WebElement firstRow = activityElements.tableRows.get(0);
            return firstRow.findElements(By.tagName("td")).get(columnIndex).getText();
        }
        return "";
    }

    public String totalActivityCount() {
        TestLogger.info("Getting total activity count...");
        navigateToActivityPage();
        int totalCount = 0;
        while (true) {
            waitHelper.waitForElementVisible(By.xpath("//table/tbody/tr"));
            totalCount += getTableRowCount();

            try {
                WebElement nextButton = driver.findElement(By.xpath("//a[@aria-label='Go to next page']"));
                String buttonClass = nextButton.getAttribute("class");

                if (buttonClass != null && buttonClass.contains("pointer-events-none")) {
                    break; // Exit loop if Next button is disabled
                }
                
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
                Thread.sleep(1500); // Wait for the next page's content to load

            } catch (NoSuchElementException e) {
                break; // Exit loop if Next button is not found
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        TestLogger.success("Total activity count is: " + totalCount);
        return String.valueOf(totalCount);
    }

    // --- Pagination Methods ---

    public boolean isPaginationInfoDisplayed() {
        return activityElements.paginationInfo.isDisplayed();
    }

    public String getPaginationInfoText() {
        return activityElements.paginationInfo.getText();
    }

    public void clickNextPageButton() {
        activityElements.nextPageButton.click();
    }

    public void clickPreviousPageButton() {
        activityElements.previousPageButton.click();
    }

    public boolean isNextPageButtonEnabled() {
        return !activityElements.nextPageButton.getAttribute("class").contains("pointer-events-none");
    }

    public boolean isPreviousPageButtonEnabled() {
        return !activityElements.previousPageButton.getAttribute("class").contains("pointer-events-none");
    }

    // --- Add Activity Workflow ---

    public void fillBasicInfoStep(String name, String location, String role, String key) {
        TestLogger.info("Filling out Basic Info step...");
        waitHelper.waitForElementVisible((By) activityElements.activityNameInput).sendKeys(name);
        
        activityElements.locationLevelDropdown.click();
        waitHelper.waitForElementClickable((By) activityElements.locationLevelOption).click();

        activityElements.locationDropdown.click();
        waitHelper.waitForElementClickable(By.xpath("//div[text()='" + location + "']")).click();
        
        activityElements.selectAccessRoleButton.click();
        waitHelper.waitForElementClickable(By.xpath("//label[text()='" + role + "']")).click();
        
        activityElements.selectKeysButton.click();
        waitHelper.waitForElementClickable(By.xpath("//label[text()='" + key + "']")).click();

        clickNextButton();
        TestLogger.success("Completed Basic Info step.");
    }

    public void fillScheduleStep(String fromTime, String toTime) {
        TestLogger.info("Filling out Schedule step...");
        waitHelper.waitForElementVisible((By) activityElements.scheduleModal);
        activityElements.fromTimeInput.sendKeys(fromTime);
        activityElements.toTimeInput.sendKeys(toTime);
        activityElements.mondayCheckbox.click();
        clickNextButton();
        TestLogger.success("Completed Schedule step.");
    }

    public void fillFrequencyStep() {
        TestLogger.info("Filling out Frequency step...");
        waitHelper.waitForElementVisible((By) activityElements.frequencyModal);
        activityElements.frequency1Checkbox.click();
        clickNextButton();
        TestLogger.success("Completed Frequency step.");
    }

    public void completeConfirmationStep() {
        TestLogger.info("Confirming and submitting the new activity...");
        waitHelper.waitForElementVisible((By) activityElements.confirmationModal);
        activityElements.submitButton.click();
        TestLogger.success("New activity submitted.");
    }

    public void clickNextButton() {
        TestLogger.info("Clicking 'Next' button...");
        try {
            WebElement nextButton = waitHelper.waitForElementClickable(By.xpath("//div[@data-slot='dialog-footer']//button[text()='Next']"));
            nextButton.click();
            TestLogger.success("Clicked 'Next' button successfully.");
        } catch (Exception e) {
            TestLogger.error("Failed to click 'Next' button: " + e.getMessage());
            throw new RuntimeException("Could not click the 'Next' button.", e);
        }
    }

    // --- Action Menu Methods ---

    public void clickActionMenuForRow(int rowIndex) {
        TestLogger.info("Clicking action menu for row " + rowIndex);
        activityElements.actionButtons.get(rowIndex).click();
        waitHelper.waitForElementVisible(By.xpath("//div[text()='Actions']"));
    }

    public void selectViewFromActionMenu() {
        TestLogger.info("Selecting 'View' from action menu...");
        driver.findElement(By.xpath("//button[text()='View']")).click();
        waitHelper.waitForElementVisible((By) activityElements.viewModalContainer);
    }

    public void selectEditFromActionMenu() {
        TestLogger.info("Selecting 'Edit' from action menu...");
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
        waitHelper.waitForElementVisible((By) activityElements.activityModal);
    }

    public void selectDeleteFromActionMenu() {
        TestLogger.info("Selecting 'Delete' from action menu...");
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        waitHelper.waitForElementVisible(By.xpath("//h2[text()='Are you sure?']"));
    }

    public void confirmDeletion() {
        TestLogger.info("Confirming deletion...");
        waitHelper.waitForElementClickable(By.xpath("//button[text()='Yes']")).click();
        TestLogger.success("Deletion confirmed.");
    }

    // --- View Modal Methods ---

    public String getViewModalActivityName() {
        return activityElements.activityName.getText();
    }

    public String getView_ModalStatus() {
        return activityElements.activityStatus.getText();
    }

    public List<String> getViewModalAccessRoles() {
        return activityElements.accessRoles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void closeViewModal() {
        activityElements.closeButton.click();
    }

    // --- Additional Methods for Comprehensive Testing ---

    public boolean isPageSubHeaderDisplayed() {
        TestLogger.info("Checking if page sub-header is displayed.");
        try {
            return activityElements.pageSubHeader.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Page sub-header not found: " + e.getMessage());
            return false;
        }
    }

    public String getPageSubHeaderText() {
        return activityElements.pageSubHeader.getText();
    }

    public boolean isModalHeaderDisplayed() {
        TestLogger.info("Checking if modal header is displayed.");
        try {
            WebElement modalHeader = waitHelper.waitForElementVisible(activityElements.modalHeaderLocator);
            return modalHeader.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Modal header not found: " + e.getMessage());
            return false;
        }
    }

    public String getModalHeaderText() {
        WebElement modalHeader = waitHelper.waitForElementVisible(activityElements.modalHeaderLocator);
        return modalHeader.getText();
    }

    public boolean isActivityNameInputDisplayed() {
        TestLogger.info("Checking if Activity Name input is displayed.");
        try {
            return activityElements.activityNameInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterActivityName(String name) {
        TestLogger.info("Entering activity name: " + name);
        activityElements.activityNameInput.clear();
        activityElements.activityNameInput.sendKeys(name);
    }

    public void clearActivityName() {
        TestLogger.info("Clearing activity name field.");
        activityElements.activityNameInput.clear();
    }

    public String getActivityNameValue() {
        return activityElements.activityNameInput.getAttribute("value");
    }

    public boolean isLocationLevelDropdownDisplayed() {
        TestLogger.info("Checking if Location Level dropdown is displayed.");
        try {
            return activityElements.locationLevelDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLocationDropdownDisplayed() {
        TestLogger.info("Checking if Location dropdown is displayed.");
        try {
            return activityElements.locationDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectAccessRoleButtonDisplayed() {
        TestLogger.info("Checking if Select Access Role button is displayed.");
        try {
            return activityElements.selectAccessRoleButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectKeysButtonDisplayed() {
        TestLogger.info("Checking if Select Keys button is displayed.");
        try {
            return activityElements.selectKeysButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCancelButton() {
        TestLogger.info("Clicking Cancel button...");
        activityElements.cancelButton.click();
        TestLogger.success("Clicked Cancel button.");
    }

    public boolean isSubmitButtonDisplayed() {
        TestLogger.info("Checking if Submit button is displayed.");
        try {
            return activityElements.submitButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSubmitButton() {
        TestLogger.info("Clicking Submit button...");
        activityElements.submitButton.click();
    }

    public boolean isActivityNameErrorDisplayed() {
        TestLogger.info("Checking if Activity Name error is displayed.");
        try {
            WebElement error = driver.findElement(By.xpath("//input[@placeholder='Enter Activity Name']/following-sibling::p[contains(@class,'text-red') or contains(@class,'error')]"));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getActivityNameErrorText() {
        try {
            WebElement error = driver.findElement(By.xpath("//input[@placeholder='Enter Activity Name']/following-sibling::p[contains(@class,'text-red') or contains(@class,'error')]"));
            return error.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLocationLevelErrorDisplayed() {
        TestLogger.info("Checking if Location Level error is displayed.");
        try {
            WebElement error = driver.findElement(By.xpath("//label[text()='Location Level']/following::p[contains(@class,'text-red') or contains(@class,'error')][1]"));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAccessRoleErrorDisplayed() {
        TestLogger.info("Checking if Access Role error is displayed.");
        try {
            WebElement error = driver.findElement(By.xpath("//button[text()='Select Access Role']/following::p[contains(@class,'text-red') or contains(@class,'error')][1]"));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectLocationLevel() {
        TestLogger.info("Selecting Location Level...");
        activityElements.locationLevelDropdown.click();
        waitHelper.waitForElementClickable((By) activityElements.locationLevelOption).click();
        TestLogger.success("Location Level selected.");
    }

    public void selectLocation(String location) {
        TestLogger.info("Selecting Location: " + location);
        activityElements.locationDropdown.click();
        waitHelper.waitForElementClickable(By.xpath("//div[text()='" + location + "']")).click();
        TestLogger.success("Location selected: " + location);
    }

    public void selectAccessRole(String role) {
        TestLogger.info("Selecting Access Role: " + role);
        activityElements.selectAccessRoleButton.click();
        waitHelper.waitForElementClickable(By.xpath("//label[text()='" + role + "']")).click();
        TestLogger.success("Access Role selected: " + role);
    }

    public void selectKey(String key) {
        TestLogger.info("Selecting Key: " + key);
        activityElements.selectKeysButton.click();
        waitHelper.waitForElementClickable(By.xpath("//label[text()='" + key + "']")).click();
        TestLogger.success("Key selected: " + key);
    }

    public boolean isPreviousButtonDisplayed() {
        TestLogger.info("Checking if Previous button is displayed.");
        try {
            return activityElements.previousButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPreviousButton() {
        TestLogger.info("Clicking Previous button...");
        activityElements.previousButton.click();
        TestLogger.success("Clicked Previous button.");
    }

    public boolean isBasicInfoStepActive() {
        TestLogger.info("Checking if Basic Info step is active.");
        try {
            String classAttr = activityElements.basicInfoStep.getAttribute("class");
            return classAttr != null && (classAttr.contains("active") || classAttr.contains("current"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isScheduleStepDisplayed() {
        TestLogger.info("Checking if Schedule step is displayed.");
        try {
            return activityElements.scheduleStep.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFrequencyStepDisplayed() {
        TestLogger.info("Checking if Frequency step is displayed.");
        try {
            return activityElements.frequencyStep.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConfirmationStepDisplayed() {
        TestLogger.info("Checking if Confirmation step is displayed.");
        try {
            return activityElements.confirmationStep.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFromDateButtonDisplayed() {
        TestLogger.info("Checking if From Date button is displayed.");
        try {
            return activityElements.fromDateButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isToDateButtonDisplayed() {
        TestLogger.info("Checking if To Date button is displayed.");
        try {
            return activityElements.toDateButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDurationDropdownDisplayed() {
        TestLogger.info("Checking if Duration dropdown is displayed.");
        try {
            return activityElements.durationDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areDayCheckboxesDisplayed() {
        TestLogger.info("Checking if day checkboxes are displayed.");
        try {
            return activityElements.mondayCheckbox.isDisplayed() &&
                   activityElements.tuesdayCheckbox.isDisplayed() &&
                   activityElements.wednesdayCheckbox.isDisplayed() &&
                   activityElements.thursdayCheckbox.isDisplayed() &&
                   activityElements.fridayCheckbox.isDisplayed() &&
                   activityElements.saturdayCheckbox.isDisplayed() &&
                   activityElements.sundayCheckbox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectAllDays() {
        TestLogger.info("Selecting all days...");
        activityElements.allDaysCheckbox.click();
        TestLogger.success("All days selected.");
    }

    public void selectDay(String day) {
        TestLogger.info("Selecting day: " + day);
        switch (day.toLowerCase()) {
            case "monday": case "mon": activityElements.mondayCheckbox.click(); break;
            case "tuesday": case "tue": activityElements.tuesdayCheckbox.click(); break;
            case "wednesday": case "wed": activityElements.wednesdayCheckbox.click(); break;
            case "thursday": case "thu": activityElements.thursdayCheckbox.click(); break;
            case "friday": case "fri": activityElements.fridayCheckbox.click(); break;
            case "saturday": case "sat": activityElements.saturdayCheckbox.click(); break;
            case "sunday": case "sun": activityElements.sundayCheckbox.click(); break;
        }
    }

    public boolean isFrequency1CheckboxDisplayed() {
        TestLogger.info("Checking if Frequency 1 checkbox is displayed.");
        try {
            return activityElements.frequency1Checkbox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFrequency2CheckboxDisplayed() {
        TestLogger.info("Checking if Frequency 2 checkbox is displayed.");
        try {
            return activityElements.frequency2Checkbox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectFrequency1() {
        TestLogger.info("Selecting Frequency 1...");
        activityElements.frequency1Checkbox.click();
    }

    public void selectFrequency2() {
        TestLogger.info("Selecting Frequency 2...");
        activityElements.frequency2Checkbox.click();
    }

    public boolean isConfirmationBasicInfoDisplayed() {
        TestLogger.info("Checking if confirmation basic info is displayed.");
        try {
            return activityElements.basicInformationHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConfirmationScheduleDetailsDisplayed() {
        TestLogger.info("Checking if confirmation schedule details is displayed.");
        try {
            return activityElements.scheduleDetailsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getConfirmationNameValue() {
        return activityElements.nameValue.getText();
    }

    public boolean isFirstPageButtonEnabled() {
        return !activityElements.firstPageButton.getAttribute("class").contains("pointer-events-none");
    }

    public boolean isLastPageButtonEnabled() {
        return !activityElements.lastPageButton.getAttribute("class").contains("pointer-events-none");
    }

    public void clickFirstPageButton() {
        TestLogger.info("Clicking first page button...");
        activityElements.firstPageButton.click();
    }

    public void clickLastPageButton() {
        TestLogger.info("Clicking last page button...");
        activityElements.lastPageButton.click();
    }

    public boolean isTableHeaderRowDisplayed() {
        TestLogger.info("Checking if table header row is displayed.");
        try {
            return activityElements.tableHeaderRow.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getActionButtonsCount() {
        return activityElements.actionButtons.size();
    }

    public boolean isViewModalDisplayed() {
        TestLogger.info("Checking if View modal is displayed.");
        try {
            WebElement viewModal = waitHelper.waitForElementVisible((By) activityElements.viewModalContainer);
            return viewModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getViewModalAccessKeys() {
        return activityElements.accessKeys.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getViewModalWeeklyAccessDays() {
        return activityElements.weeklyAccessDays.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getViewModalCode() {
        return activityElements.codeValue.getText();
    }

    public String getViewModalLocationLevel() {
        return activityElements.locationLevelValue.getText();
    }

    public String getViewModalLocation() {
        return activityElements.locationValue.getText();
    }

    public String getViewModalDurationLimit() {
        return activityElements.durationLimitValue.getText();
    }

    public String getViewModalFromDate() {
        return activityElements.fromDateValue.getText();
    }

    public String getViewModalToDate() {
        return activityElements.toDateValue.getText();
    }

    public String getViewModalFromTime() {
        return activityElements.fromTimeValue.getText();
    }

    public String getViewModalToTime() {
        return activityElements.toTimeValue.getText();
    }

    public boolean isDeleteConfirmationDisplayed() {
        TestLogger.info("Checking if delete confirmation is displayed.");
        try {
            WebElement confirmDialog = driver.findElement(By.xpath("//h2[text()='Are you sure?']"));
            return confirmDialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void cancelDeletion() {
        TestLogger.info("Canceling deletion...");
        waitHelper.waitForElementClickable(By.xpath("//button[text()='No']")).click();
        TestLogger.success("Deletion canceled.");
    }

    public boolean isSuccessMessageDisplayed() {
        TestLogger.info("Checking if success message is displayed.");
        try {
            WebElement successMsg = waitHelper.waitForElementVisible(By.xpath("//div[contains(@class,'toast') or contains(@class,'success')]//div[contains(text(),'success') or contains(text(),'Success') or contains(text(),'created') or contains(text(),'deleted')]"));
            return successMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessageText() {
        try {
            WebElement successMsg = driver.findElement(By.xpath("//div[contains(@class,'toast') or contains(@class,'success')]//div[contains(text(),'success') or contains(text(),'Success') or contains(text(),'created') or contains(text(),'deleted')]"));
            return successMsg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        TestLogger.info("Checking if error message is displayed.");
        try {
            WebElement errorMsg = driver.findElement(By.xpath("//div[contains(@class,'toast') or contains(@class,'error')]//div[contains(text(),'error') or contains(text(),'Error') or contains(text(),'failed') or contains(text(),'Failed')]"));
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterDurationLimit(String duration) {
        TestLogger.info("Entering duration limit: " + duration);
        activityElements.durationLimitInput.clear();
        activityElements.durationLimitInput.sendKeys(duration);
    }

    public boolean isDurationLimitInputDisplayed() {
        TestLogger.info("Checking if Duration Limit input is displayed.");
        try {
            return activityElements.durationLimitInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void searchAndSelectRole(String roleName) {
        TestLogger.info("Searching and selecting role: " + roleName);
        activityElements.selectAccessRoleButton.click();
        waitHelper.waitForElementVisible(By.xpath("//div[@role='dialog']//input[@placeholder='Search role...']")).sendKeys(roleName);
        waitHelper.waitForElementClickable(By.xpath("//label[contains(text(),'" + roleName + "')]")).click();
    }

    public void searchAndSelectKey(String keyName) {
        TestLogger.info("Searching and selecting key: " + keyName);
        activityElements.selectKeysButton.click();
        waitHelper.waitForElementVisible(By.xpath("//div[@role='dialog']//input[@placeholder='Search key...']")).sendKeys(keyName);
        waitHelper.waitForElementClickable(By.xpath("//label[contains(text(),'" + keyName + "')]")).click();
    }

    public boolean isCloseButtonDisplayed() {
        TestLogger.info("Checking if Close button is displayed.");
        try {
            return activityElements.closeButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCloseButton() {
        TestLogger.info("Clicking Close button...");
        activityElements.closeButton.click();
    }

    public boolean isModalClosed() {
        TestLogger.info("Checking if modal is closed.");
        try {
            Thread.sleep(500);
            List<WebElement> modals = driver.findElements(By.xpath("//div[@role='dialog']"));
            return modals.isEmpty() || !modals.get(0).isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    public void enterActivityNameWithSpecialCharacters() {
        String specialName = "Test@Activity#$%^&*()!";
        enterActivityName(specialName);
    }

    public void enterActivityNameWithVeryLongText() {
        StringBuilder longName = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            longName.append("ActivityName");
        }
        enterActivityName(longName.toString());
    }

    public void enterActivityNameWithSpacesOnly() {
        enterActivityName("     ");
    }

    public void enterActivityNameWithSQLInjection() {
        enterActivityName("' OR '1'='1'; DROP TABLE activities; --");
    }

    public void enterActivityNameWithXSS() {
        enterActivityName("<script>alert('XSS')</script>");
    }
}
