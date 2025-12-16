package Elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ActivitiyPageElements {

    private static final Logger logger = LogManager.getLogger(ActivitiyPageElements.class);

    public ActivitiyPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        logger.info("Activity Page Elements initialized");
    }

    // --- Main Activity Page Elements ---

    @FindBy(xpath = "//h1[text()='Activity']")
    public WebElement pageHeader;

    @FindBy(xpath = "//p[text()='Manage and monitor all scheduled access activities']")
    public WebElement pageSubHeader;

    @FindBy(xpath = "//button[@aria-label='Expand search']")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@placeholder='Search...']")
    public WebElement searchInput;

    @FindBy(xpath = "//button[normalize-space()='Add Activity']")
    public WebElement addActivityButton;

    @FindBy(xpath = "//table")
    public WebElement activityTable;

    @FindBy(xpath = "//thead/tr")
    public WebElement tableHeaderRow;

    @FindBy(xpath = "//thead/tr/th")
    public List<WebElement> tableHeaders;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> tableRows;

    @FindBy(xpath = "//div[contains(text(), 'Showing From')]")
    public WebElement paginationInfo;

    @FindBy(xpath = "//a[@aria-label='Go to first page']")
    public WebElement firstPageButton;

    @FindBy(xpath = "//a[@aria-label='Go to previous page']")
    public WebElement previousPageButton;

    @FindBy(xpath = "//a[@aria-label='Go to next page']")
    public WebElement nextPageButton;

    @FindBy(xpath = "//a[@aria-label='Go to last page']")
    public WebElement lastPageButton;
    
    @FindBy(xpath = "//td[8]//button")
    public List<WebElement> actionButtons;

    // --- Add/Edit Activity Modal Elements ---

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement activityModal;

    @FindBy(xpath = "//h2[text()='Activity Details']")
    public WebElement modalHeader;

    @FindBy(xpath = "//label[text()='Activity Name']")
    public WebElement activityNameLabel;

    @FindBy(xpath = "//input[@placeholder='Enter Activity Name']")
    public WebElement activityNameInput;

    @FindBy(xpath = "//label[text()='Duration Limit']")
    public WebElement durationLimitLabel;

    @FindBy(xpath = "//input[@placeholder='Enter Duration Limit']")
    public WebElement durationLimitInput;

    @FindBy(xpath = "//label[text()='Location Level']")
    public WebElement locationLevelLabel;

    @FindBy(xpath = "//button[@role='combobox' and .//span[text()='Select Location Level']]")
    public WebElement locationLevelDropdown;

    @FindBy(xpath = "//div[contains(@class, 'select-content')]//div[text()='Location']")
    public WebElement locationLevelOption;

    @FindBy(xpath = "//label[text()='Location']")
    public WebElement locationLabel;

    @FindBy(xpath = "//button[@role='combobox' and .//span[text()='Select Location']]")
    public WebElement locationDropdown;

    @FindBy(xpath = "//div[contains(@class, 'select-content')]//div[text()='Tharavi RO']")
    public WebElement locationOption;

    @FindBy(xpath = "//label[text()='From Date']")
    public WebElement fromDateLabel;

    @FindBy(xpath = "//input[@placeholder='From Date']")
    public WebElement fromDateInput;

    @FindBy(xpath = "//label[text()='To Date']")
    public WebElement toDateLabel;

    @FindBy(xpath = "//input[@placeholder='To Date']")
    public WebElement toDateInput;

    @FindBy(xpath = "//label[text()='From Time']")
    public WebElement fromTimeLabel;

    @FindBy(xpath = "//input[@placeholder='From Time']")
    public WebElement fromTimeInput;

    @FindBy(xpath = "//label[text()='To Time']")
    public WebElement toTimeLabel;

    @FindBy(xpath = "//input[@placeholder='To Time']")
    public WebElement toTimeInput;

    @FindBy(xpath = "//label[text()='Mon']")
    public WebElement mondayCheckbox;

    @FindBy(xpath = "//label[text()='Tue']")
    public WebElement tuesdayCheckbox;

    @FindBy(xpath = "//label[text()='Wed']")
    public WebElement wednesdayCheckbox;

    @FindBy(xpath = "//label[text()='Thu']")
    public WebElement thursdayCheckbox;

    @FindBy(xpath = "//label[text()='Fri']")
    public WebElement fridayCheckbox;

    @FindBy(xpath = "//label[text()='Sat']")
    public WebElement saturdayCheckbox;

    @FindBy(xpath = "//label[text()='Sun']")
    public WebElement sundayCheckbox;

    @FindBy(xpath = "//button[text()='Select Access Role']")
    public WebElement selectAccessRoleButton;

    @FindBy(xpath = "//div[@role='dialog']//input[@placeholder='Search role...']")
    public WebElement searchRoleInput;

    @FindBy(xpath = "//div[@role='dialog']//label[text()='key safe user']")
    public WebElement keySafeUserRoleCheckbox;

    @FindBy(xpath = "//button[text()='Select Keys']")
    public WebElement selectKeysButton;



    @FindBy(xpath = "//div[@role='dialog']//input[@placeholder='Search key...']")
    public WebElement searchKeyInput;

    @FindBy(xpath = "//div[@role='dialog']//label[text()='Fill p.tank #2']")
    public WebElement keyCheckbox;

    @FindBy(xpath = "//button[text()='Submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//button[text()='Cancel']")
    public WebElement cancelButton;


    /*-----view model------------------------*/


    @FindBy(xpath = "//div[@role='dialog' and .//h2[text()='Activity Details']]")
    public WebElement viewModalContainer;

    @FindBy(xpath = "//div[p[text()='Access configuration details']]//h3")
    public WebElement activityName;

    @FindBy(xpath = "//div[p[text()='Access configuration details']]/following-sibling::span")
    public WebElement activityStatus;

    // --- Basic Information Section ---
    @FindBy(xpath = "//p[text()='Code']/following-sibling::p")
    public WebElement codeValue;

    @FindBy(xpath = "//p[text()='Location Level']/following-sibling::p")
    public WebElement locationLevelValue;

    @FindBy(xpath = "//p[text()='Location']/following-sibling::p")
    public WebElement locationValue;

    @FindBy(xpath = "//p[text()='Duration Limit']/following-sibling::p")
    public WebElement durationLimitValue;

    // --- Schedule Section ---
    @FindBy(xpath = "//div[p[text()='Date Range']]//span[text()='From:']/following-sibling::span")
    public WebElement fromDateValue;

    @FindBy(xpath = "//div[p[text()='Date Range']]//span[text()='To:']/following-sibling::span")
    public WebElement toDateValue;

    @FindBy(xpath = "//div[p[text()='Time Range']]//span[text()='From:']/following-sibling::span")
    public WebElement fromTimeValue;

    @FindBy(xpath = "//div[p[text()='Time Range']]//span[text()='To:']/following-sibling::span")
    public WebElement toTimeValue;

    // --- Weekly Access Section ---
    @FindBy(xpath = "//div[h3[text()='Weekly Access']]//div[contains(@class, 'gap-3')]//span")
    public List<WebElement> weeklyAccessDays;

    // --- Access Roles Section ---
    @FindBy(xpath = "//div[h3[text()='Access Roles']]//div[contains(@class, 'flex-wrap')]//span")
    public List<WebElement> accessRoles;

    // --- Access Keys Section ---
    @FindBy(xpath = "//div[h3[text()='Access Keys']]//div[contains(@class, 'grid')]//span")
    public List<WebElement> accessKeys;

    @FindBy(xpath = "//button[.//svg[contains(@class, 'lucide-x')]]")
    public WebElement closeButton;


    @FindBy(xpath = "//div[@role='dialog' and .//h2[text()='Add Activity']]")
    public WebElement scheduleModal;

    // --- Header/Progress Bar ---
    @FindBy(xpath = "//p[text()='Basic Info']")
    public WebElement basicInfoStep;

    @FindBy(xpath = "//p[text()='Schedule']")
    public WebElement scheduleStep;

    @FindBy(xpath = "//p[text()='Frequency']")
    public WebElement frequencyStep;

    @FindBy(xpath = "//p[text()='Confirmation']")
    public WebElement confirmationStep;

    // --- Schedule Form Elements ---


    @FindBy(xpath = "//button[.//span[text()='Select From Date']]")
    public WebElement fromDateButton;


    @FindBy(xpath = "//button[.//span[text()='Select To Date']]")
    public WebElement toDateButton;


    @FindBy(xpath = "//label[text()='Duration']")
    public WebElement durationLabel;

    @FindBy(xpath = "//button[.//span[text()='Select Duration']]")
    public WebElement durationDropdown;

    // --- Working Days ---
    @FindBy(xpath = "//label[text()='Working Days']")
    public WebElement workingDaysLabel;

    @FindBy(xpath = "//button[.//following-sibling::label[text()='All']]")
    public WebElement allDaysCheckbox;

    @FindBy(xpath = "//div[@role='dialog' and .//h2[text()='Add Activity']]")
    public WebElement frequencyModal;



    // --- Frequency Checkboxes ---
    @FindBy(xpath = "//label[text()='Frequency 1']")
    public WebElement frequency1Label;

    @FindBy(xpath = "//button[@role='checkbox' and .//following-sibling::label[text()='Frequency 1']]")
    public WebElement frequency1Checkbox;

    @FindBy(xpath = "//label[text()='Frequency 2']")
    public WebElement frequency2Label;

    @FindBy(xpath = "//button[@role='checkbox' and .//following-sibling::label[text()='Frequency 2']]")
    public WebElement frequency2Checkbox;


    // --- Dynamic Fields for Frequency 2 ---
    @FindBy(xpath = "//label[text()='Events']")
    public WebElement eventsLabel;

    @FindBy(xpath = "//button[@role='combobox' and .//span[text()='Select Events']]")
    public WebElement eventsDropdown;

    @FindBy(xpath = "//label[text()='Period']")
    public WebElement periodLabel;

    @FindBy(xpath = "//button[@role='combobox' and .//span[text()='Select Period']]")
    public WebElement periodDropdown;

    // --- Footer Buttons -------------
    @FindBy(xpath = "//div[@data-slot='dialog-footer']//button[text()='Previous']")
    public WebElement previousButton;

    @FindBy(xpath = "//div[@data-slot='dialog-footer']//button[text()='Next']")
    public WebElement nextButton;

    @FindBy(xpath = "//div[@role='dialog' and .//h2[text()='Add Activity']]")
    public WebElement confirmationModal;

    // --- Header/Progress Bar ---


    // --- Basic Information ---
    @FindBy(xpath = "//h3[text()='Basic Information']")
    public WebElement basicInformationHeader;

    @FindBy(xpath = "//span[text()='Name:']/following-sibling::span")
    public WebElement nameValue;

    @FindBy(xpath = "//span[text()='Roles:']/following-sibling::span")
    public WebElement rolesValue;

    @FindBy(xpath = "//span[text()='Keys:']/following-sibling::span")
    public WebElement keysValue;

    // --- Schedule Details ---
    @FindBy(xpath = "//h3[text()='Schedule Details']")
    public WebElement scheduleDetailsHeader;

    @FindBy(xpath = "//span[text()='Date Range:']/following-sibling::span")
    public WebElement dateRangeValue;

    @FindBy(xpath = "//span[text()='Duration:']/following-sibling::span")
    public WebElement durationValue;

    @FindBy(xpath = "//span[text()='Working Days:']/following-sibling::span")
    public WebElement workingDaysValue;

    // --- Frequency 1 ---
    @FindBy(xpath = "//h3[text()='Frequency 1']")
    public WebElement frequency1Header;

    @FindBy(xpath = "//h3[text()='Frequency 1']/following-sibling::div//span[text()='Events:']/following-sibling::span")
    public WebElement frequency1EventsValue;

    @FindBy(xpath = "//h3[text()='Frequency 1']/following-sibling::div//span[text()='Per:']/following-sibling::span")
    public WebElement frequency1PerValue;

    // --- Frequency 2 ---
    @FindBy(xpath = "//h3[text()='Frequency 2']")
    public WebElement frequency2Header;

    @FindBy(xpath = "//h3[text()='Frequency 2']/following-sibling::div//span[text()='Events:']/following-sibling::span")
    public WebElement frequency2EventsValue;

    @FindBy(xpath = "//h3[text()='Frequency 2']/following-sibling::div//span[text()='Per:']/following-sibling::span")
    public WebElement frequency2PerValue;


}
