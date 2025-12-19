package Elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * KeyboxPageElements - Contains all web element locators for the Key Box page
 * URL: /keySafes
 * Follows the Page Object Model pattern with separate element definitions
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class KeyboxPageElements {

    private static final Logger logger = LogManager.getLogger(KeyboxPageElements.class);

    public KeyboxPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        logger.info("Keybox Page Elements initialized");
    }

    // ==================== PAGE HEADER ELEMENTS ====================

    @FindBy(xpath = "//h1[text()='Key Box']")
    public WebElement pageHeader;

    public By pageHeaderLocator = By.xpath("//h1[text()='Key Box']");

    @FindBy(xpath = "//p[text()='Manage and configure all registered key box devices']")
    public WebElement pageSubHeader;

    public By pageSubHeaderLocator = By.xpath("//p[text()='Manage and configure all registered key box devices']");

    // ==================== SEARCH ELEMENTS ====================

    @FindBy(xpath = "//button[@aria-label='Expand search']")
    public WebElement searchButton;

    public By searchButtonLocator = By.xpath("//button[@aria-label='Expand search']");

    @FindBy(xpath = "//input[@placeholder='Search...' or @type='search']")
    public WebElement searchInput;

    public By searchInputLocator = By.xpath("//input[@placeholder='Search...' or @type='search']");

    // ==================== FILTER ELEMENTS ====================

    @FindBy(xpath = "//button[contains(.,'Filter')]")
    public WebElement filterButton;

    public By filterButtonLocator = By.xpath("//button[contains(.,'Filter')]");

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

    @FindBy(xpath = "//label[text()='Status']/following-sibling::button[@role='combobox']")
    public WebElement statusFilterDropdown;

    public By statusFilterDropdownLocator = By.xpath("//label[text()='Status']/following-sibling::button[@role='combobox']");

    @FindBy(xpath = "//label[text()='Status']/following-sibling::button[@role='combobox']//span[@data-slot='select-value']")
    public WebElement statusFilterSelectedValue;

    // Type Filter Dropdown
    @FindBy(xpath = "//label[text()='Type']")
    public WebElement typeFilterLabel;

    public By typeFilterLabelLocator = By.xpath("//label[text()='Type']");

    @FindBy(xpath = "//label[text()='Type']/following-sibling::button[@role='combobox']")
    public WebElement typeFilterDropdown;

    public By typeFilterDropdownLocator = By.xpath("//label[text()='Type']/following-sibling::button[@role='combobox']");

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
    @FindBy(xpath = "//div[@role='dialog']//button[@data-slot='dialog-close' and .//svg[contains(@class,'lucide-x')]]")
    public WebElement filterDialogCloseButton;

    public By filterDialogCloseButtonLocator = By.xpath("//div[@role='dialog']//button[@data-slot='dialog-close' and .//svg[contains(@class,'lucide-x')]]");

    // Filter Dropdown Options (generic locators)
    public By filterDropdownOptionLocator(String optionText) {
        return By.xpath("//div[@role='listbox']//div[@role='option' and contains(text(),'" + optionText + "')]");
    }

    public By filterDropdownOptionsLocator = By.xpath("//div[@role='listbox']//div[@role='option']");

    // ==================== STATISTICS CARDS ====================

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Total Key Box']/following::div[contains(@class,'text-3xl')][1]")
    public WebElement totalKeyBoxCount;

    public By totalKeyBoxCountLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Total Key Box']/following::div[contains(@class,'text-3xl')][1]");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Registered']/following::div[contains(@class,'text-3xl')][1]")
    public WebElement registeredCount;

    public By registeredCountLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Registered']/following::div[contains(@class,'text-3xl')][1]");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Unregistered']/following::div[contains(@class,'text-3xl')][1]")
    public WebElement unregisteredCount;

    public By unregisteredCountLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Unregistered']/following::div[contains(@class,'text-3xl')][1]");

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

    @FindBy(xpath = "//table")
    public WebElement keyboxTable;

    public By keyboxTableLocator = By.xpath("//table");

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

    // ==================== ACTION BUTTONS IN TABLE ====================

    @FindBy(xpath = "//tbody/tr//button[contains(@class,'dropdown') or @aria-haspopup='menu']")
    public List<WebElement> actionButtons;

    public By actionButtonsLocator = By.xpath("//tbody/tr//button[contains(@class,'dropdown') or @aria-haspopup='menu']");

    // Action Menu Items
    @FindBy(xpath = "//button[text()='View']")
    public WebElement viewActionButton;

    public By viewActionLocator = By.xpath("//button[text()='View']");

    @FindBy(xpath = "//button[text()='Edit']")
    public WebElement editActionButton;

    public By editActionLocator = By.xpath("//button[text()='Edit']");

    @FindBy(xpath = "//button[text()='Delete']")
    public WebElement deleteActionButton;

    public By deleteActionLocator = By.xpath("//button[text()='Delete']");

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

    @FindBy(xpath = "//a[@aria-label='Go to first page'] | //button[@aria-label='Go to first page']")
    public WebElement firstPageButton;

    @FindBy(xpath = "//a[@aria-label='Go to previous page'] | //button[@aria-label='Go to previous page']")
    public WebElement previousPageButton;

    @FindBy(xpath = "//a[@aria-label='Go to next page'] | //button[@aria-label='Go to next page']")
    public WebElement nextPageButton;

    @FindBy(xpath = "//a[@aria-label='Go to last page'] | //button[@aria-label='Go to last page']")
    public WebElement lastPageButton;

    // ==================== VIEW/DETAILS MODAL ELEMENTS ====================

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement modalContainer;

    public By modalContainerLocator = By.xpath("//div[@role='dialog']");

    @FindBy(xpath = "//div[@role='dialog']//h2")
    public WebElement modalHeader;

    public By modalHeaderLocator = By.xpath("//div[@role='dialog']//h2");

    @FindBy(xpath = "//button[.//svg[contains(@class,'lucide-x')] or @aria-label='Close']")
    public WebElement closeButton;

    public By closeButtonLocator = By.xpath("//button[.//svg[contains(@class,'lucide-x')] or @aria-label='Close']");

    // View Modal Detail Fields
    @FindBy(xpath = "//div[@role='dialog']//p[text()='Serial Number']/following-sibling::p")
    public WebElement viewSerialNumber;

    @FindBy(xpath = "//div[@role='dialog']//p[text()='Location']/following-sibling::p")
    public WebElement viewLocation;

    @FindBy(xpath = "//div[@role='dialog']//p[text()='Ro Type']/following-sibling::p")
    public WebElement viewRoType;

    @FindBy(xpath = "//div[@role='dialog']//p[text()='Status']/following-sibling::p")
    public WebElement viewStatus;

    // ==================== DELETE CONFIRMATION ELEMENTS ====================

    @FindBy(xpath = "//h2[text()='Are you sure?']")
    public WebElement deleteConfirmationHeader;

    public By deleteConfirmationLocator = By.xpath("//h2[text()='Are you sure?']");

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement confirmDeleteButton;

    public By confirmDeleteButtonLocator = By.xpath("//button[text()='Yes']");

    @FindBy(xpath = "//button[text()='No']")
    public WebElement cancelDeleteButton;

    public By cancelDeleteButtonLocator = By.xpath("//button[text()='No']");

    // ==================== TOAST/NOTIFICATION ELEMENTS ====================

    @FindBy(xpath = "//section[@aria-label='Notifications alt+T']")
    public WebElement notificationSection;

    public By toastMessageLocator = By.xpath("//div[contains(@class,'toast')]");

    public By successToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'success') or contains(text(),'Success')]");

    public By errorToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'error') or contains(text(),'Error') or contains(text(),'failed')]");

    // ==================== SIDEBAR NAVIGATION ====================

    @FindBy(xpath = "//a[@href='/keySafes']")
    public WebElement keyboxNavLink;

    public By keyboxNavLinkLocator = By.xpath("//a[@href='/keySafes']");

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
