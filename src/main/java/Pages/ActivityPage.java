package Pages;

import Elements.ActivitiyPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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

    public ActivityPage navigateToActivityPage() {
        TestLogger.info("Navigating to Activity page...");
        waitHelper.waitForElementClickable(By.xpath("//a[@href='/activity']")).click();
        waitHelper.waitForElementVisible( activityElements.pageHeader);
        TestLogger.success("Successfully navigated to Activity page.");
        return this;
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

    public ActivityPage clickAddActivityButton() {
        TestLogger.info("Clicking 'Add Activity' button...");
        activityElements.addActivityButton.click();
        waitHelper.waitForElementVisible((By) activityElements.activityModal);
        TestLogger.success("'Add Activity' modal is open.");
        return this;
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
    public ActivityPage clicksearchicon() {
        activityElements.Searchicon.click();
        TestLogger.info("Clicked the search icon");
        return this;
    }

    public ActivityPage enterActivitynameinsearchgbox(String activityName) {
        activityElements.Searchbox.sendKeys(activityName);
        TestLogger.info("Entered the activity name in the search box: " + activityName);
        return this;
    }
    public ActivityPage clickactionbutton() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        activityElements.Actionbutton.click();
        TestLogger.info("Clicked the action button");
        return this;
    }
    public ActivityPage clickviewbutton() {
        activityElements.ViewButton.click();
        TestLogger.info("Clicked the view button");
        return this;
    }
    public boolean isActivitydetailsLabelDisplayed() {
        try {
            return activityElements.ActivitydetailsLabel.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Activity details label not found: " + e.getMessage());
            return false;
        }
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

    public ActivityPage clickNextPageButton() {
        activityElements.nextPageButton.click();
        return this;
    }

    public ActivityPage clickPreviousPageButton() {
        activityElements.previousPageButton.click();
        return this;
    }

    public boolean isNextPageButtonEnabled() {
        return !activityElements.nextPageButton.getAttribute("class").contains("pointer-events-none");
    }

    public boolean isPreviousPageButtonEnabled() {
        return !activityElements.previousPageButton.getAttribute("class").contains("pointer-events-none");
    }

    // --- Add Activity Workflow ---

    public ActivityPage fillBasicInfoStep(String name, String location, String role, String key) {
        TestLogger.info("Filling out Basic Info step...");
        activityElements.locationLevelDropdown.click();
        waitHelper.waitForElementClickable((By) activityElements.locationLevelOption).click();

        activityElements.locationDropdown.click();
        waitHelper.waitForElementClickable(By.xpath("//div[text()='" + location + "']")).click();
        
        activityElements.selectAccessRoleButton.click();
        waitHelper.waitForElementClickable(By.xpath("//label[text()='" + role + "']")).click();
        
        activityElements.selectKeysButton.click();
        waitHelper.waitForElementClickable(By.xpath("//label[text()='" + key + "']")).click();

        clicknextbutton();
        TestLogger.success("Completed Basic Info step.");
        return this;
    }

    public ActivityPage fillScheduleStep(String fromTime, String toTime) {
        TestLogger.info("Filling out Schedule step...");
        waitHelper.waitForElementVisible((By) activityElements.scheduleModal);
        activityElements.fromTimeInput.sendKeys(fromTime);
        activityElements.toTimeInput.sendKeys(toTime);
        activityElements.mondayCheckbox.click();
        clicknextbutton();
        TestLogger.success("Completed Schedule step.");
        return this;
    }

    public ActivityPage fillFrequencyStep() {
        TestLogger.info("Filling out Frequency step...");
        waitHelper.waitForElementVisible((By) activityElements.frequencyModal);
        activityElements.frequency1Checkbox.click();
        clicknextbutton();
        TestLogger.success("Completed Frequency step.");
        return this;
    }

    public ActivityPage completeConfirmationStep() {
        TestLogger.info("Confirming and submitting the new activity...");
        waitHelper.waitForElementVisible((By) activityElements.confirmationModal);
        activityElements.submitButton.click();
        TestLogger.success("New activity submitted.");
        return this;
    }
    public ActivityPage selectEditFromActionMenu() {
        TestLogger.info("Selecting 'Edit' from action menu...");
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
        waitHelper.waitForElementVisible((By) activityElements.activityModal);
        return this;
    }

    public ActivityPage selectDeleteFromActionMenu() {
        TestLogger.info("Selecting 'Delete' from action menu...");
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        waitHelper.waitForElementVisible(By.xpath("//h2[text()='Are you sure?']"));
        return this;
    }

    public ActivityPage clickEditButton() {
        TestLogger.info("CLICK EDIT BUTTON");
        activityElements.EditButton.click();
        TestLogger.success("Clicked edit button");
        return this;
    }
    public ActivityPage closeViewModal() {
        activityElements.Modalclosebutton.click();
        return this;
    }
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
            return activityElements.ActivityName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ActivityPage clearActivityName() {
        TestLogger.info("Clearing activity name field.");
        activityElements.ActivityName.sendKeys(Keys.CONTROL+"a");
        activityElements.ActivityName.sendKeys(Keys.DELETE);
        return this;
    }

    public String getActivityNameValue() {
        return activityElements.ActivityName.getAttribute("value");
    }

    public boolean isLocationLevelDropdownDisplayed() {
        TestLogger.info("Checking if Location Level dropdown is displayed.");
        try {
            return activityElements.Locationlevel.isDisplayed();
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

    public boolean isRoleDropdownDisplayed() {
        TestLogger.info("Checking if Select Access Role button is displayed.");
        try {
            return activityElements.Roles.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isKeyDropdownDisplayed() {
        TestLogger.info("Checking if Select Keys button is displayed.");
        try {
            return activityElements.Keys.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ActivityPage clickCancelButton() {
        TestLogger.info("Clicking Cancel button...");
        activityElements.cancelButton.click();
        TestLogger.success("Clicked Cancel button.");
        return this;
    }

    public boolean isSubmitButtonDisplayed() {
        TestLogger.info("Checking if Submit button is displayed.");
        try {
            return activityElements.submitButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ActivityPage clickSubmitButton() {
        TestLogger.info("Clicking Submit button...");
        activityElements.submitButton.click();
        return this;
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


    public boolean isErrorMessageOfRoleDisplayed() {
        TestLogger.info("Checking if Access Role error is displayed.");
        try {
            return activityElements.ErrormsgofRole.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageOfLocationLevelDisplayed() {
        TestLogger.info("Checking if location level error is displayed.");
        try {
            return activityElements.ErrormsgofLocationLevel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isErrorMessageOfInvalidActivityNameDisplayed() {
        TestLogger.info("Checking if Error message of Invalid Activity Name  is displayed.");
        try {
            return activityElements.ErrormsgofInvalidActivityName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isPreviousButtonDisplayed() {
        TestLogger.info("Checking if Previous button is displayed.");
        try {
            return activityElements.previousButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ActivityPage clickPreviousButton() {
        TestLogger.info("Clicking Previous button...");
        activityElements.previousButton.click();
        TestLogger.success("Clicked Previous button.");
        return this;
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
    public boolean isasicinfoStepDisplayed() {
        TestLogger.info("Checking if Basic Info step is displayed.");
        try {
            return activityElements.basicInfoStep.isDisplayed();
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

    public ActivityPage selectAllDays() {
        TestLogger.info("Selecting all days...");
        activityElements.allDaysCheckbox.click();
        TestLogger.success("All days selected.");
        return this;
    }

    public ActivityPage selectDay(String day) {
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
        return this;
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

    public ActivityPage selectFrequency1() {
        TestLogger.info("Selecting Frequency 1...");
        activityElements.frequency1Checkbox.click();
        return this;
    }

    public ActivityPage selectFrequency2() {
        TestLogger.info("Selecting Frequency 2...");
        activityElements.frequency2Checkbox.click();
        return this;
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

    public ActivityPage clickFirstPageButton() {
        TestLogger.info("Clicking first page button...");
        activityElements.firstPageButton.click();
        return this;
    }

    public ActivityPage clickLastPageButton() {
        TestLogger.info("Clicking last page button...");
        activityElements.lastPageButton.click();
        return this;
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

    public ActivityPage cancelDeletion() {
        TestLogger.info("Canceling deletion...");
        waitHelper.waitForElementClickable(By.xpath("//button[text()='No']")).click();
        TestLogger.success("Deletion canceled.");
        return this;
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

    public ActivityPage enterDurationLimit(String duration) {
        TestLogger.info("Entering duration limit: " + duration);
        activityElements.durationLimitInput.clear();
        activityElements.durationLimitInput.sendKeys(duration);
        return this;
    }

    public boolean isDurationLimitInputDisplayed() {
        TestLogger.info("Checking if Duration Limit input is displayed.");
        try {
            return activityElements.durationLimitInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ActivityPage searchAndSelectRole(String roleName) {
        TestLogger.info("Searching and selecting role: " + roleName);
        activityElements.selectAccessRoleButton.click();
        waitHelper.waitForElementVisible(By.xpath("//div[@role='dialog']//input[@placeholder='Search role...']")).sendKeys(roleName);
        waitHelper.waitForElementClickable(By.xpath("//label[contains(text(),'" + roleName + "')]")).click();
        return this;
    }

    public ActivityPage searchAndSelectKey(String keyName) {
        TestLogger.info("Searching and selecting key: " + keyName);
        activityElements.selectKeysButton.click();
        waitHelper.waitForElementVisible(By.xpath("//div[@role='dialog']//input[@placeholder='Search key...']")).sendKeys(keyName);
        waitHelper.waitForElementClickable(By.xpath("//label[contains(text(),'" + keyName + "')]")).click();
        return this;
    }

    public boolean isCloseButtonDisplayed() {
        TestLogger.info("Checking if Close button is displayed.");
        try {
            return activityElements.Modalclosebutton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isErrorMessageOfUsingSpecialCharactersInActivitynameDisplayed() {
        TestLogger.info("Checking the error message of activity name is displayed");
        try {
            return activityElements.ErrormsgOfUsingSpecialCharactersInActivitryName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isErrorMessageOfUsingLongTextInActivitynameDisplayed() {
        TestLogger.info("Checking the error message of activity name is displayed");
        try {
            return activityElements.ErrormsgOfUsingLongTextInActivitryName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isErrorMessageOfUsingSpacesInActivitryNamaeDisplayed() {
        TestLogger.info("Checking the Error message Of Using Spaces In Activitry Namae is displayed");
        try {
            return activityElements.ErrormsgOfUsingSpacesInActivitryNamae.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public ActivityPage clickCloseButton() {
        TestLogger.info("Clicking Close button");
        activityElements.Modalclosebutton.click();
        return this;
    }

    public ActivityPage enterActivityNameWithSpecialCharacters() {
        String specialName = "Test@Activity#$%^&*()!";
        enterActivityName(specialName);
        return this;
    }

    public ActivityPage enterActivityNameWithVeryLongText() {
        StringBuilder longName = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            longName.append("ActivityName");
        }
        enterActivityName(longName.toString());
        return this;
    }

    public ActivityPage enterActivityNameWithSpacesOnly() {
        enterActivityName("  ACTIVITY   ");
        return this;
    }

    public ActivityPage enterActivityNameWithSQLInjection() {
        enterActivityName("' OR '1'='1'; DROP TABLE activities; --");
        return this;
    }

    public ActivityPage enterActivityNameWithXSS() {
        enterActivityName("<script>alert('XSS')</script>");
        return this;
    }
    public ActivityPage enterActivityName(String activityName) {
        TestLogger.info("Entering the code");
        activityElements.ActivityName.sendKeys(activityName);
        return this;
    }
    public ActivityPage entercode(String code) {
        TestLogger.info("Entering the code");
        activityElements.Activitycode.sendKeys(code);
        return this;
    }
    public ActivityPage clicklocationleveldropdown() {
        TestLogger.info("Clicking location level dropdown");
        activityElements.Locationlevel.click();
        return this;
    }
    public ActivityPage clicklocationlevelOption() {
        TestLogger.info("Clicking location level option");
        activityElements.LocationlevelOption.click();
        return this;
    }
    public ActivityPage clicklocation() {
        TestLogger.info("Clicking location");
        activityElements.Location.click();
        return this;
    }
    public ActivityPage clicklocationOption() {
        TestLogger.info("Clicking location option");
        activityElements.LocationOption.click();
        return this;
    }
    public ActivityPage clickRoles() {
        TestLogger.info("Clicking Roles dropdown");
        activityElements.Roles.click();
        return this;
    }
    public ActivityPage ClickRolesoption() {
        TestLogger.info("Clicking Roles option");
        activityElements.RolesOption.click();
        return this;
    }
    public ActivityPage clickKeys() {
        TestLogger.info("Clicking Keys dropdown");
        activityElements.Keys.click();
        return this;
    }
    public ActivityPage clickkeysoption() {
        TestLogger.info("Clicking keys option");
        activityElements.KeysOption.click();
        return this;
    }
    public ActivityPage clicknextbutton() {
        TestLogger.info("Clicking next button");
        activityElements.NextButton.click();
        return this;
    }
    public ActivityPage clickfromdatecalendar() {
        TestLogger.info("Clicking from date calendar");
        activityElements.FromDateCalendar.click();
        return this;
    }
    public ActivityPage clickfromdate() {
        TestLogger.info("Clicking from date");
        activityElements.FromDate.click();
        return this;
    }
    public ActivityPage clicktodatecalendar() {
        TestLogger.info("Clicking to date calendar");
        activityElements.ToDateCalendar.click();
        return this;
    }
    public ActivityPage clicktodate() {
        TestLogger.info("Clicking to date");
        activityElements.ToDate.click();
        return this;
    }
    public ActivityPage enterfromtime(String fromtime) {
        TestLogger.info("enter from time");
        activityElements.FromTime.sendKeys(fromtime);
        return this;
    }
    public ActivityPage entertotime(String totime) {
        TestLogger.info("enter to time");
        activityElements.ToTime.sendKeys(totime);
        return this;
    }
    public ActivityPage clickduration() {
        TestLogger.info("Clicking Duration dropdown");
        activityElements.Duration.click();
        return this;
    }
    public ActivityPage clickdurationoption() {
        TestLogger.info("Clicking Duration option");
        activityElements.DurationOption.click();
        return this;
    }
    public ActivityPage clickworkingdayscheckbox() {
        TestLogger.info("Clicking working days checkbox");
        activityElements.WorkingDaysCheckbox.click();
        return this;
    }
    public ActivityPage clickfrequencycheckbox() {
        TestLogger.info("Clicking frequency checkbox");
        activityElements.FrequencyCheckbox.click();
        return this;
    }
    public ActivityPage clickevent() {
        TestLogger.info("Clicking event dropdown");
        activityElements.Events.click();
        return this;
    }
    public ActivityPage clickeventoption() {
        TestLogger.info("Clicking event option");
        activityElements.EventsOption.click();
        return this;
    }
    public ActivityPage clickperiod() {
        TestLogger.info("Clicking periiod dropdown");
        activityElements.Period.click();
        return this;
    }
    public ActivityPage clickperiodoption() {
        TestLogger.info("Clicking Period option");
        activityElements.PeriodOption.click();
        return this;
    }
    public ActivityPage clicksubmitbutton() {
        TestLogger.info("Clicking submit button");
        activityElements.Submitbutton.click();
        return this;
    }
    public ActivityPage addnewactivity() {
        TestLogger.info("Clicking submit button");

        navigateToActivityPage(). clickAddActivityButton().enterActivityName("New Activity").entercode("541").clicklocationleveldropdown().clicklocationlevelOption();
        clicklocation().clicklocationOption().clickRoles().ClickRolesoption().clickKeys().clickkeysoption().clickKeys().clicknextbutton().clickfromdatecalendar().
        clickfromdate().clicktodatecalendar();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        clicktodate().enterfromtime("0900AM").entertotime("1800PM").clickduration().clickdurationoption().
          clickworkingdayscheckbox().clicknextbutton().clickfrequencycheckbox().clickevent().clickeventoption().clickperiod().clickperiodoption().clicknextbutton().clicksubmitbutton();
    return this;
    }
    public boolean isBasicInformationSectionInViewModalDisplayed() {
        TestLogger.info("Checking the Basic Information Section In View Modal is displayed.");
        try {
            return activityElements.BasicInformationSectionInViewModal.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Basic Information Section In View Modal is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isScheduleSectionInViewModalDisplayed() {
        TestLogger.info("Checking the Schedule Section In View Modal is displayed.");
        try {
            return activityElements.ScheduleSectionInViewModal.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Schedule Section In View Modal is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isWeeklyAccessSectionInViewModalDisplayed() {
        TestLogger.info("Checking the Weekly Access Section In View Modal is displayed.");
        try {
            return activityElements.WeeklyAccessSectionInViewModal.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Weekly Access Section In View Modal is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isAccessRolesSectionInViewModalDisplayed() {
        TestLogger.info("Checking the Access Roles Section In View Modal is displayed.");
        try {
            return activityElements.AccessRolesSectionInViewModal.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Access Roles Section In View Modal is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isAccessKeysSectionInViewModalDisplayed() {
        TestLogger.info("Checking the Access Keys Section In View Modal is displayed.");
        try {
            return activityElements.AccessKeysSectionInViewModal.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Access Keys Section In View Modal is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isAccessEventsSectionInViewModalDisplayed() {
        TestLogger.info("Checking the Access Events Section In View Modal is displayed.");
        try {
            return activityElements.AccessEventsSectionInViewModal.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Access Events Section In View Modal is not displayed: " + e.getMessage());
            return false;
        }
    }

    // --- Missing Methods Required by ActivityTest ---

    public boolean isSelectAccessRoleButtonDisplayed() {
        TestLogger.info("Checking if Select Access Role button is displayed.");
        try {
            return activityElements.selectAccessRoleButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Select Access Role button not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isSelectKeysButtonDisplayed() {
        TestLogger.info("Checking if Select Keys button is displayed.");
        try {
            return activityElements.selectKeysButton.isDisplayed();
        } catch (Exception e) {
            TestLogger.error("Select Keys button not found: " + e.getMessage());
            return false;
        }
    }

    public ActivityPage selectLocationLevel() {
        TestLogger.info("Selecting location level...");
        clicklocationleveldropdown();
        clicklocationlevelOption();
        TestLogger.success("Location level selected.");
        return this;
    }

    public String getViewModalActivityName() {
        TestLogger.info("Getting activity name from view modal.");
        try {
            return activityElements.activityconfigName.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get activity name from view modal: " + e.getMessage());
            return "";
        }
    }

    public String getView_ModalStatus() {
        TestLogger.info("Getting status from view modal.");
        try {
            return activityElements.activityStatus.getText();
        } catch (Exception e) {
            TestLogger.error("Could not get status from view modal: " + e.getMessage());
            return "";
        }
    }

    public List<String> getViewModalAccessRoles() {
        TestLogger.info("Getting access roles from view modal.");
        return activityElements.accessRoles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public ActivityPage searchActivity(String activityName) {
        TestLogger.info("Searching for activity: " + activityName);
        clicksearchicon();
        enterActivitynameinsearchgbox(activityName);
        TestLogger.success("Search performed for: " + activityName);
        return this;
    }

    public ActivityPage clickActionMenuForRow(int rowIndex) {
        TestLogger.info("Clicking action menu for row: " + rowIndex);
        try {
            if (activityElements.actionButtons.size() > rowIndex) {
                activityElements.actionButtons.get(rowIndex).click();
                TestLogger.success("Clicked action menu for row " + rowIndex);
            } else {
                TestLogger.error("Row index " + rowIndex + " is out of bounds. Total action buttons: " + activityElements.actionButtons.size());
            }
        } catch (Exception e) {
            TestLogger.error("Could not click action menu for row " + rowIndex + ": " + e.getMessage());
        }
        return this;
    }

    public ActivityPage selectViewFromActionMenu() {
        TestLogger.info("Selecting 'View' from action menu...");
        clickviewbutton();
        waitHelper.waitForElementVisible((By) activityElements.viewModalContainer);
        return this;
    }

    public boolean isLocationLevelErrorDisplayed() {
        TestLogger.info("Checking if Location Level error is displayed.");
        try {
            return activityElements.ErrormsgofLocationLevel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAccessRoleErrorDisplayed() {
        TestLogger.info("Checking if Access Role error is displayed.");
        try {
            return activityElements.ErrormsgofRole.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isModalClosed() {
        TestLogger.info("Checking if modal is closed.");
        try {
            Thread.sleep(500);
            driver.findElement((By) activityElements.activityModal);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
