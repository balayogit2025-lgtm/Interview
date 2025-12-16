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
        return activityElements.activityTable.isDisplayed();
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

    public String getViewModalStatus() {
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
}
