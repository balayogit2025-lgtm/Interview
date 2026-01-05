package Elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KeyboxPageElements {

    private static final Logger logger = LogManager.getLogger(KeyboxPageElements.class);

    public KeyboxPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        logger.info("Keybox Page Elements initialized");
    }

    @FindBy(xpath = "//h1[text()='Key Box']")
    public WebElement pageHeader;

    public By pageHeaderLocator = By.xpath("//h1[text()='Key Box']");

    @FindBy(xpath = "//p[text()='Manage and configure all registered key box devices']")
    public WebElement pageSubHeader;

    public By pageSubHeaderLocator = By.xpath("//p[text()='Manage and configure all registered key box devices']");



    @FindBy(xpath = "(//button[@data-slot='button']) [2]")
    public WebElement searchicon;

    public By searchIconLocator = By.xpath("(//button[@data-slot='button']) [2]");

    @FindBy(xpath = "//input[@data-slot='input']")
    public WebElement searchInput;

    public By searchInputLocator = By.xpath("//input[@data-slot='input']");

    @FindBy(xpath = "//button[text()='Filter']")
    public WebElement filterButton;

    public By filterButtonLocator = By.xpath("//button[contains(.,'Filter')]");

    @FindBy(xpath = "(//button[@role='combobox']) [2]")
    public WebElement TypeDropdown;

    public By TypeDropdownDefaultValueLocator = By.xpath("(//span[@data-slot='select-value']) [2]");

    @FindBy(xpath = "//span[text()='Month Till Date']")
    public WebElement OptionInTypeDropDown;

    public By OptionInTypeDropDownLocator = By.xpath("//span[text()='Month Till Date']");

    @FindBy(xpath = "//span[text()='Date Range']")
    public WebElement DateRangeOptionInTypeDropDown;

    public By DateRangeOptionInTypeDropDownLocator = By.xpath("//span[text()='Date Range']");

    @FindBy(xpath = "//span[text()='From Date']")
    public WebElement FromDate;

    public By FromDateLocator = By.xpath("//span[text()='From Date']");

    @FindBy(xpath = "//button[@aria-label='Go to the Previous Month']")
    public WebElement PreviousMonth;

    public By PreviousMonthLocator = By.xpath("//button[@aria-label='Go to the Previous Month']");

    @FindBy(xpath = "//button[@aria-label='Monday, December 1st, 2025']")
    public WebElement ExactFromDate;

    public By ExactFromDateLocator = By.xpath("//button[@aria-label='Monday, December 1st, 2025']");

    @FindBy(xpath = "//span[text()='To Date']")
    public WebElement ToDate;

    public By ToDateLocator = By.xpath("//span[text()='To Date']");

    @FindBy(xpath = "//button[@aria-label='Today, Monday, January 5th, 2026']")
    public WebElement ExactToDate;

    public By ExactToDateLocator = By.xpath("//button[@aria-label='Today, Monday, January 5th, 2026']");


    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement filterDialog;

    public By filterDialogLocator = By.xpath("//div[@role='dialog']");

    // Filter Dialog Header
    @FindBy(xpath = "//h2[text()='Key Box Filter']")
    public WebElement filterDialogTitle;

    public By filterDialogTitleLocator = By.xpath("//h2[text()='Key Box Filter']");

    @FindBy(xpath = "//p[contains(text(),'Select filter to apply on key box list')]")
    public WebElement filterDialogDescription;

    public By filterDialogDescriptionLocator = By.xpath("//p[contains(text(),'Select filter to apply on key box list')]");

    // Status Filter Dropdown
    @FindBy(xpath = "//label[text()='Status']")
    public WebElement statusFilterLabel;

    public By statusFilterLabelLocator = By.xpath("//label[text()='Status']");

    @FindBy(xpath = "(//button[@role='combobox']) [1]")
    public WebElement statusFilterDropdown;

    public By statusFilterDropdownLocator = By.xpath("(//button[@role='combobox']) [1]");

    @FindBy(xpath = "(//span[@data-slot='select-value']) [1]")
    public WebElement statusFilterSelectedValue;

    // Type Filter Dropdown
    @FindBy(xpath = "//label[text()='Type']")
    public WebElement typeFilterLabel;

    public By typeFilterLabelLocator = By.xpath("//label[text()='Type']");

    @FindBy(xpath = "(//button[@role='combobox']) [2]")
    public WebElement typeFilterDropdown;

    public By typeFilterDropdownLocator = By.xpath("(//button[@role='combobox']) [2]");

    @FindBy(xpath = "//label[text()='Type']/following-sibling::button[@role='combobox']//span[@data-slot='select-value']")
    public WebElement typeFilterSelectedValue;

    // Filter Action Buttons
    @FindBy(xpath = "//button[text()='Clear Filter']")
    public WebElement clearFilterButton;

    public By clearFilterButtonLocator = By.xpath("//button[text()='Clear Filter']");

    @FindBy(xpath = "//button[text()='Apply Filter']")
    public WebElement applyFilterButton;

    public By applyFilterButtonLocator = By.xpath("//button[text()='Apply Filter']");

    // Filter Dialog Close Button (X)
    @FindBy(xpath = "(//button[@data-slot='dialog-close']) [2]")
    public WebElement filterDialogCloseButton;

    public By filterDialogCloseButtonLocator = By.xpath("(//button[@data-slot='dialog-close']) [2]");

    // Filter Dropdown Options (generic locators)
    public By filterDropdownOptionLocator(String optionText) {
        return By.xpath("//div[@role='listbox']//div[@role='option' and contains(text(),'" + optionText + "')]");
    }

    public By filterDropdownOptionsLocator = By.xpath("//div[@role='listbox']//div[@role='option']");

    // ==================== STATISTICS CARDS ====================

    @FindBy(xpath = "(//div[@class='text-3xl font-bold']) [1]")
    public WebElement totalKeyBoxCount;

    public By totalKeyBoxCountLocator = By.xpath("(//div[@class='text-3xl font-bold']) [1]");

    @FindBy(xpath = "(//div[@class='text-3xl font-bold']) [2]")
    public WebElement registeredCount;

    public By registeredCountLocator = By.xpath("(//div[@class='text-3xl font-bold']) [2]");

    @FindBy(xpath = "(//div[@class='text-3xl font-bold']) [3]")
    public WebElement unregisteredCount;

    public By unregisteredCountLocator = By.xpath("(//div[@class='text-3xl font-bold']) [3]");

    // Statistics Card Containers
    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Total Key Box']]")
    public WebElement totalKeyBoxCard;

    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Registered']]")
    public WebElement registeredCard;

    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Unregistered']]")
    public WebElement unregisteredCard;

    // Statistics Card Labels
    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Total Key Box']")
    public WebElement totalKeyBoxLabel;

    public By totalKeyBoxLabelLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Total Key Box']");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Registered']")
    public WebElement registeredLabel;

    public By registeredLabelLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Registered']");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Unregistered']")
    public WebElement unregisteredLabel;

    public By unregisteredLabelLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Unregistered']");

    // ==================== TABLE ELEMENTS ====================

    @FindBy(xpath = "//table[@class='w-full text-left  border border-primary-50 rounded-lg table']")
    public WebElement keyboxTable;

    public By keyboxTableLocator = By.xpath("//table[@class='w-full text-left  border border-primary-50 rounded-lg table']");

    @FindBy(xpath = "//thead/tr")
    public WebElement tableHeaderRow;

    public By tableHeaderRowLocator = By.xpath("//thead/tr");

    @FindBy(xpath = "//thead/tr/th")
    public List<WebElement> tableHeaders;

    public By tableHeadersLocator = By.xpath("//thead/tr/th");

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> tableRows;

    public By tableRowsLocator = By.xpath("//tbody/tr");

    @FindBy(xpath = "//tbody/tr/td")
    public List<WebElement> tableCells;

    // Individual Table Headers
    @FindBy(xpath = "//th[contains(text(),'S.No')]")
    public WebElement snoHeader;

    @FindBy(xpath = "//th[contains(text(),'Serial Number')]")
    public WebElement serialNumberHeader;

    @FindBy(xpath = "//th[contains(text(),'Location')]")
    public WebElement locationHeader;

    @FindBy(xpath = "//th[contains(text(),'Ro Type')]")
    public WebElement roTypeHeader;

    @FindBy(xpath = "//th[contains(text(),'Last Communicated Date')]")
    public WebElement lastCommunicatedHeader;

    @FindBy(xpath = "//th[contains(text(),'Registered By')]")
    public WebElement registeredByHeader;

    @FindBy(xpath = "//th[contains(text(),'Updated By')]")
    public WebElement updatedByHeader;

    @FindBy(xpath = "//th[contains(text(),'Status')]")
    public WebElement statusHeader;

    @FindBy(xpath = "//th[contains(text(),'Action')]")
    public WebElement actionHeader;

    // ==================== EMPTY STATE ELEMENTS ====================

    @FindBy(xpath = "//td[contains(text(),'No results found')]")
    public WebElement noResultsMessage;

    public By noResultsMessageLocator = By.xpath("//td[contains(text(),'No results found')]");

    @FindBy(xpath = "//td[@colspan]")
    public WebElement emptyTableCell;

    @FindBy(xpath = "//button[@data-slot='popover-trigger']")
    public WebElement ActionButtonofUnregistered;

    public By ActionButtonofUnregisteredLocator = By.xpath("//button[@data-slot='popover-trigger']");

    @FindBy(xpath = "(//button[@data-slot='popover-trigger']) [1]")
    public WebElement ActionButton;

    public By ActionButtonLocator = By.xpath("(//button[@data-slot='popover-trigger']) [1]");

    @FindBy(xpath = "(//button[@role='combobox']) [1]")
    public WebElement ROTypeDropdown;

    public By ROTypeDropdownLocator = By.xpath("(//button[@role='combobox']) [1]");

    @FindBy(xpath = "//span[text()='Company Owned Company Operator']")
    public WebElement OptionInROTypeDropdown;

    public By OptionInROTypeDropdownLocator = By.xpath("//span[text()='Company Owned Company Operator']");

    @FindBy(xpath = "(//button[@role='combobox']) [2]")
    public WebElement LocationDropdown;

    public By LocationDropdownLocator = By.xpath("(//button[@role='combobox']) [2]");

    @FindBy(xpath = "//span[text()='Chennai']")
    public WebElement OptionInLocationDropdown;

    public By OptionInLocationDropdownLocator = By.xpath("//span[text()='Chennai']");

    @FindBy(xpath = "(//button[@role='combobox']) [3]")
    public WebElement TemplateDropdown;

    public By TemplateDropdownLocator = By.xpath("(//button[@role='combobox']) [3]");

    @FindBy(xpath = "//span[text()='Template 1']")
    public WebElement OptionInTemplateDropdown;

    public By OptionInTemplateDropdownLocator = By.xpath("//span[text()='Template 1']");

    @FindBy(xpath = "//button[text()='Cancel']")
    public WebElement CancelButton;

    public By CancelButtonLocator = By.xpath("//button[text()='Cancel']");

    @FindBy(xpath = "//button[text()='Submit']")
    public WebElement SubmitButton;

    public By SubmitButtonLocator = By.xpath("//button[text()='Submit']");

    @FindBy(xpath = "//span[text()='Search Location']")
    public WebElement SearchLocation;

    public By SearchLocationLocator = By.xpath("//span[text()='Search Location']");

    @FindBy(xpath = "//button[text()='Clear All']")
    public WebElement ClearAllButton;

    public By ClearAllButtonLocator = By.xpath("//button[text()='Clear All']");

    @FindBy(xpath = "//input[@placeholder='Search Location']")
    public WebElement LocationSearchBox;

    public By LocationSearchBoxLocator = By.xpath("//input[@placeholder='Search Location']");

   /* @FindBy(xpath = "")
    public WebElement ;

    public By  = By.xpath("");

    @FindBy(xpath = "")
    public WebElement ;

    public By  = By.xpath("");

    @FindBy(xpath = "")
    public WebElement ;

    public By  = By.xpath("");

    @FindBy(xpath = "")
    public WebElement ;

    public By  = By.xpath("");

    @FindBy(xpath = "")
    public WebElement ;

    public By  = By.xpath("");*/


    @FindBy(xpath = "//button[text()='View']")
    public WebElement ViewButton;

    public By ViewButtonLocator = By.xpath("//button[text()='View']");

    @FindBy(xpath = "//button[text()='Edit']")
    public WebElement EditButton;

    public By EditButtonLocator = By.xpath("//button[text()='Edit']");


    // Action Menu Container
    public By actionMenuLocator = By.xpath("//div[@role='menu' or contains(@class,'dropdown-menu')]");

    // ==================== PAGINATION ELEMENTS ====================

    @FindBy(xpath = "//div[contains(text(),'Showing') or contains(text(),'Page')]")
    public WebElement paginationInfo;

    public By paginationInfoLocator = By.xpath("//div[contains(text(),'Showing') or contains(text(),'Page')]");

    // Pagination total records text (e.g., "Showing 1 to 10 of 50 results")
    @FindBy(xpath = "//div[contains(text(),'of') and contains(text(),'results')]")
    public WebElement paginationTotalRecords;

    public By paginationTotalRecordsLocator = By.xpath("//div[contains(text(),'of') and contains(text(),'results')]");

    // Alternative: Page X of Y format
    @FindBy(xpath = "//span[contains(text(),'Page')] | //div[contains(text(),'Page')]")
    public WebElement pageNumberInfo;

    public By pageNumberInfoLocator = By.xpath("//span[contains(text(),'Page')] | //div[contains(text(),'Page')]");

    @FindBy(xpath = "//a[@aria-label='Go to first page']")
    public WebElement firstPageButton;

    public By firstpagebuttonLocator = By.xpath("//a[@aria-label='Go to first page']");

    @FindBy(xpath = "//a[@aria-label='Go to previous page']")
    public WebElement previousPageButton;

    public By previouspagebuttonLocator = By.xpath("//a[@aria-label='Go to previous page']");

    @FindBy(xpath = "//a[@aria-label='Go to next page']")
    public WebElement nextPageButton;

    public By nextpagebuttonLocator = By.xpath("//a[@aria-label='Go to next page']");

    @FindBy(xpath = "//a[@aria-label='Go to last page']")
    public WebElement lastPageButton;

    public By lastpagebuttonLocator = By.xpath("//a[@aria-label='Go to last page']");

    @FindBy(xpath = "//div[@data-slot='dialog-content']")
    public WebElement Modal;

    public By ModalLocator = By.xpath("//div[@data-slot='dialog-content']");

    @FindBy(xpath = "//div[@role='dialog']//h2")
    public WebElement modalHeader;

    public By modalHeaderLocator = By.xpath("//div[@role='dialog']//h2");

    @FindBy(xpath = "//button[@data-slot='dialog-close']")
    public WebElement closeButton;

    public By closeButtonLocator = By.xpath("//button[@data-slot='dialog-close']");

    // View Modal Detail Fields
    @FindBy(xpath = "(//th[text()='S.No']) [2]")
    public WebElement ViewSerialNumber;

    @FindBy(xpath = "//th[text()='Name']")
    public WebElement ViewName;

    @FindBy(xpath = "//th[text()='Role']")
    public WebElement ViewRole;

    @FindBy(xpath = "//div[@role='dialog']//p[text()='Status']/following-sibling::p")
    public WebElement viewStatus;


    @FindBy(xpath = "(//span[text()='Unregistered']) [2]")
    public WebElement OptionInStatusDropdown;

    public By OptionInStatusDropdownLocator = By.xpath("(//span[text()='Unregistered']) [2]");


    @FindBy(xpath = "//button[text()='Register']")
    public WebElement RegisterButton;

    public By RegisterButtonLocator = By.xpath("");

    // ==================== TOAST/NOTIFICATION ELEMENTS ====================

    @FindBy(xpath = "//section[@aria-label='Notifications alt+T']")
    public WebElement notificationSection;

    public By toastMessageLocator = By.xpath("//div[contains(@class,'toast')]");

    public By successToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'success') or contains(text(),'Success')]");

    public By errorToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'error') or contains(text(),'Error') or contains(text(),'failed')]");

    // ==================== SIDEBAR NAVIGATION ====================

    @FindBy(xpath = "//a[@href='/keySafes']")
    public WebElement keyboxNavLink;

    public By keyboxNavLinkLocator = By.xpath("//span[text()='Key Box']");

    // ==================== STATUS BADGES IN TABLE ====================

    @FindBy(xpath = "//tbody//span[contains(@class,'badge') or contains(@class,'status')]")
    public List<WebElement> statusBadges;

    // ==================== LOADING ELEMENTS ====================

    @FindBy(xpath = "//div[contains(@class,'spinner') or contains(@class,'loading')]")
    public WebElement loadingSpinner;

    public By loadingSpinnerLocator = By.xpath("//div[contains(@class,'spinner') or contains(@class,'loading')]");

    // ==================== SCROLL AREA ====================

    @FindBy(xpath = "//div[@data-slot='scroll-area']")
    public WebElement scrollArea;

    // ==================== HELPER METHODS FOR DYNAMIC LOCATORS ====================

    /**
     * Get locator for specific table row by index (1-based)
     */
    public By getTableRowLocator(int rowIndex) {
        return By.xpath("//tbody/tr[" + rowIndex + "]");
    }

    /**
     * Get locator for specific cell by row and column index (1-based)
     */
    public By getTableCellLocator(int rowIndex, int colIndex) {
        return By.xpath("//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]");
    }

    /**
     * Get action button for specific row (0-based index)
     */
    public By getActionButtonLocator(int rowIndex) {
        return By.xpath("(//tbody/tr//button[contains(@class,'dropdown') or @aria-haspopup='menu'])[" + (rowIndex + 1) + "]");
    }

    /**
     * Get locator for searching by serial number in table
     */
    public By getRowBySerialNumber(String serialNumber) {
        return By.xpath("//tbody/tr[td[contains(text(),'" + serialNumber + "')]]");
    }

    /**
     * Get locator for rows with specific status
     */
    public By getRowsByStatus(String status) {
        return By.xpath("//tbody/tr[.//span[contains(text(),'" + status + "')]]");
    }
}
