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
 * DeviceActivityPageElements - Contains all web element locators for the Device Activity Report page
 * URL: /device-activity
 * Follows the Page Object Model pattern with separate element definitions
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class DeviceActivityPageElements {

    private static final Logger logger = LogManager.getLogger(DeviceActivityPageElements.class);

    public DeviceActivityPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        logger.info("Device Activity Page Elements initialized");
    }

    // ==================== PAGE HEADER ELEMENTS ====================

    @FindBy(xpath = "//h1[text()='Device Activity']")
    public WebElement pageHeader;

    public By pageHeaderLocator = By.xpath("//h1[text()='Device Activity']");

    @FindBy(xpath = "//p[contains(text(),'View and track detailed activity logs for all devices')]")
    public WebElement pageSubHeader;

    public By pageSubHeaderLocator = By.xpath("//p[contains(text(),'View and track detailed activity logs for all devices')]");

    // ==================== SEARCH ELEMENTS ====================

    @FindBy(xpath = "//button[@aria-label='Expand search']")
    public WebElement searchButton;

    public By searchButtonLocator = By.xpath("//button[@aria-label='Expand search']");

    @FindBy(xpath = "//input[@placeholder='Search Serial Number...']")
    public WebElement searchInput;

    public By searchInputLocator = By.xpath("//input[@placeholder='Search Serial Number...']");

    // ==================== FILTER ELEMENTS ====================

    @FindBy(xpath = "//button[contains(.,'Filter')]")
    public WebElement filterButton;

    public By filterButtonLocator = By.xpath("//button[contains(.,'Filter')]");

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement filterDialog;

    public By filterDialogLocator = By.xpath("//div[@role='dialog']");

    // Filter Dialog Header
    @FindBy(xpath = "//h2[contains(text(),'Filter') or contains(text(),'Device Activity Filter')]")
    public WebElement filterDialogTitle;

    public By filterDialogTitleLocator = By.xpath("//h2[contains(text(),'Filter') or contains(text(),'Device Activity Filter')]");

    // Filter Dialog Close Button (X)
    @FindBy(xpath = "//div[@role='dialog']//button[@data-slot='dialog-close' or .//svg[contains(@class,'lucide-x')]]")
    public WebElement filterDialogCloseButton;

    public By filterDialogCloseButtonLocator = By.xpath("//div[@role='dialog']//button[@data-slot='dialog-close' or .//svg[contains(@class,'lucide-x')]]");

    // Filter Action Buttons
    @FindBy(xpath = "//button[text()='Clear Filter']")
    public WebElement clearFilterButton;

    public By clearFilterButtonLocator = By.xpath("//button[text()='Clear Filter']");

    @FindBy(xpath = "//button[text()='Apply Filter']")
    public WebElement applyFilterButton;

    public By applyFilterButtonLocator = By.xpath("//button[text()='Apply Filter']");

    // Status Filter Dropdown
    @FindBy(xpath = "//div [contains(@class,'flex flex-col')]/button[@role='combobox'][1]")
    public WebElement statusFilterDropdown;

    public By statusFilterDropdownLocator = By.xpath("//label[text()='Status']/following-sibling::button[@role='combobox']");

    // Type Filter Dropdown
    @FindBy(xpath = "//label[text()='Type']/following-sibling::button[@role='combobox']")
    public WebElement typeFilterDropdown;

    public By typeFilterDropdownLocator = By.xpath("//label[text()='Type']/following-sibling::button[@role='combobox']");

    // Filter Dialog Description
    @FindBy(xpath = "//p[contains(text(),'Select filter to apply on device activity list')]")
    public WebElement filterDialogDescription;

    public By filterDialogDescriptionLocator = By.xpath("//p[contains(text(),'Select filter to apply on device activity list')]");

    // Status Label in Filter Dialog
    @FindBy(xpath = "//div[@role='dialog']//label[text()='Status']")
    public WebElement filterStatusLabel;

    public By filterStatusLabelLocator = By.xpath("//div[@role='dialog']//label[text()='Status']");

    // Type Label in Filter Dialog
    @FindBy(xpath = "//div[@role='dialog']//label[text()='Type']")
    public WebElement filterTypeLabel;

    public By filterTypeLabelLocator = By.xpath("//div[@role='dialog']//label[text()='Type']");

    // Filter Dropdown Options
    public By filterDropdownOptionLocator(String optionText) {
        return  By.xpath("//*[contains(@role,'listbox')]//*[normalize-space(text())='" + optionText + "']");
        //return By.xpath("//div[@role='listbox']//div[@role='option' and contains(text(),'" + optionText + "')]");
    }

    // Status Filter Selected Value
    @FindBy(xpath = "//label[text()='Status']/following-sibling::button[@role='combobox']//span[@data-slot='select-value']")
    public WebElement statusFilterSelectedValue;

    public By statusFilterSelectedValueLocator = By.xpath("//label[text()='Status']/following-sibling::button[@role='combobox']//span[@data-slot='select-value']");

    // Type Filter Selected Value
    @FindBy(xpath = "//label[text()='Type']/following-sibling::button[@role='combobox']//span[@data-slot='select-value']")
    public WebElement typeFilterSelectedValue;

    public By typeFilterSelectedValueLocator = By.xpath("//label[text()='Type']/following-sibling::button[@role='combobox']//span[@data-slot='select-value']");

    // ==================== STATISTICS CARDS ====================

    // Total Device Activity Card
    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[contains(text(),'Total Device Activity')]]")
    public WebElement totalDeviceActivityCard;

    public By totalDeviceActivityCardLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[contains(text(),'Total Device Activity')]]");

    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[contains(text(),'Total Device Activity')]]//div[contains(@class,'text-3xl')]")
    public WebElement totalDeviceActivityCount;

    public By totalDeviceActivityCountLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[contains(text(),'Total Device Activity')]]//div[contains(@class,'text-3xl')]");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[contains(text(),'Total Device Activity')]")
    public WebElement totalDeviceActivityLabel;

    public By totalDeviceActivityLabelLocator = By.xpath("//div[contains(@class,'bg-card')]//div[contains(text(),'Total Device Activity')]");

    // Success Card
    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Success']]")
    public WebElement successCard;

    public By successCardLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[text()='Success']]");

    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Success']]//div[contains(@class,'text-3xl')]")
    public WebElement successCount;

    public By successCountLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[text()='Success']]//div[contains(@class,'text-3xl')]");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Success']")
    public WebElement successLabel;

    public By successLabelLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Success']");

    // Pending Card
    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Pending']]")
    public WebElement pendingCard;

    public By pendingCardLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[text()='Pending']]");

    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Pending']]//div[contains(@class,'text-3xl')]")
    public WebElement pendingCount;

    public By pendingCountLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[text()='Pending']]//div[contains(@class,'text-3xl')]");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Pending']")
    public WebElement pendingLabel;

    public By pendingLabelLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Pending']");

    // Failed Card
    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Failed']]")
    public WebElement failedCard;

    public By failedCardLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[text()='Failed']]");

    @FindBy(xpath = "//div[contains(@class,'bg-card') and .//div[text()='Failed']]//div[contains(@class,'text-3xl')]")
    public WebElement failedCount;

    public By failedCountLocator = By.xpath("//div[contains(@class,'bg-card') and .//div[text()='Failed']]//div[contains(@class,'text-3xl')]");

    @FindBy(xpath = "//div[contains(@class,'bg-card')]//div[text()='Failed']")
    public WebElement failedLabel;

    public By failedLabelLocator = By.xpath("//div[contains(@class,'bg-card')]//div[text()='Failed']");

    // ==================== TABLE ELEMENTS ====================

    @FindBy(xpath = "//table")
    public WebElement deviceActivityTable;

    public By deviceActivityTableLocator = By.xpath("//table");

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

    public By snoHeaderLocator = By.xpath("//th[contains(text(),'S.No')]");

    @FindBy(xpath = "//th[contains(text(),'Serial Number')]")
    public WebElement serialNumberHeader;

    public By serialNumberHeaderLocator = By.xpath("//th[contains(text(),'Serial Number')]");

    @FindBy(xpath = "//th[contains(text(),'command') or contains(text(),'Command')]")
    public WebElement commandHeader;

    public By commandHeaderLocator = By.xpath("//th[contains(text(),'command') or contains(text(),'Command')]");

    @FindBy(xpath = "//th[contains(text(),'Sync Date')]")
    public WebElement syncDateHeader;

    public By syncDateHeaderLocator = By.xpath("//th[contains(text(),'Sync Date')]");

    @FindBy(xpath = "//th[contains(text(),'Created Date')]")
    public WebElement createdDateHeader;

    public By createdDateHeaderLocator = By.xpath("//th[contains(text(),'Created Date')]");

    @FindBy(xpath = "//th[contains(text(),'Status')]")
    public WebElement statusHeader;

    public By statusHeaderLocator = By.xpath("//th[contains(text(),'Status')]");

    // ==================== EMPTY STATE ELEMENTS ====================

    @FindBy(xpath = "//td[contains(text(),'No results found') or contains(text(),'No data')]")
    public WebElement noResultsMessage;

    public By noResultsMessageLocator = By.xpath("//td[contains(text(),'No results found') or contains(text(),'No data')]");

    // ==================== STATUS BADGES IN TABLE ====================

    @FindBy(xpath = "//tbody//span[contains(@data-slot,'badge') and contains(.,'Success')]")
    public List<WebElement> successBadges;

    public By successBadgesLocator = By.xpath("//tbody//span[contains(@data-slot,'badge') and contains(.,'Success')]");

    @FindBy(xpath = "//tbody//span[contains(@data-slot,'badge') and contains(.,'Pending')]")
    public List<WebElement> pendingBadges;

    public By pendingBadgesLocator = By.xpath("//tbody//span[contains(@data-slot,'badge') and contains(.,'Pending')]");

    @FindBy(xpath = "//tbody//span[contains(@data-slot,'badge') and contains(.,'Failed')]")
    public List<WebElement> failedBadges;

    public By failedBadgesLocator = By.xpath("//tbody//span[contains(@data-slot,'badge') and contains(.,'Failed')]");

    // ==================== PAGINATION ELEMENTS ====================

    @FindBy(xpath = "//div[contains(text(),'Showing')]")
    public WebElement paginationInfo;

    public By paginationInfoLocator = By.xpath("//div[contains(text(),'Showing')]");

    @FindBy(xpath = "//div[contains(text(),'Page')]")
    public WebElement pageNumberInfo;

    public By pageNumberInfoLocator = By.xpath("//div[contains(text(),'Page')]");

    @FindBy(xpath = "//a[@aria-label='Go to first page']")
    public WebElement firstPageButton;

    public By firstPageButtonLocator = By.xpath("//a[@aria-label='Go to first page']");

    @FindBy(xpath = "//a[@aria-label='Go to previous page']")
    public WebElement previousPageButton;

    public By previousPageButtonLocator = By.xpath("//a[@aria-label='Go to previous page']");

    @FindBy(xpath = "//a[@aria-label='Go to next page']")
    public WebElement nextPageButton;

    public By nextPageButtonLocator = By.xpath("//a[@aria-label='Go to next page']");

    @FindBy(xpath = "//a[@aria-label='Go to last page']")
    public WebElement lastPageButton;

    public By lastPageButtonLocator = By.xpath("//a[@aria-label='Go to last page']");

    // ==================== SIDEBAR NAVIGATION ====================

    @FindBy(xpath = "//a[@href='/device-activity']")
    public WebElement deviceActivityNavLink;

    public By deviceActivityNavLinkLocator = By.xpath("//a[@href='/device-activity']");

    // Reports menu - try multiple locators for the collapsible menu button
    @FindBy(xpath = "//span[text()='Reports']/ancestor::button | //span[text()='Reports']/parent::* | //button[.//span[text()='Reports']]")
    public WebElement reportsMenuButton;

    public By reportsMenuButtonLocator = By.xpath("//span[text()='Reports']/ancestor::button | //span[text()='Reports']/parent::* | //button[.//span[text()='Reports']]");

    // Alternative locator - click on the span or its parent div/li
    @FindBy(xpath = "//button/span[text()='Reports']")
    public WebElement reportsMenuSpan;

    public By reportsMenuSpanLocator = By.xpath("//span[text()='Reports']");

    // Reports menu parent clickable element (div or li that contains the menu)
    @FindBy(xpath = "//span[text()='Reports']/ancestor::div[contains(@class,'cursor-pointer') or contains(@class,'menu') or @role='button'][1] | //span[text()='Reports']/ancestor::li[1] | //span[text()='Reports']/ancestor::a[1]")
    public WebElement reportsMenuClickable;

    public By reportsMenuClickableLocator = By.xpath("//span[text()='Reports']/ancestor::div[contains(@class,'cursor-pointer') or contains(@class,'menu') or @role='button'][1] | //span[text()='Reports']/ancestor::li[1] | //span[text()='Reports']/ancestor::a[1]");

    // ==================== TOAST/NOTIFICATION ELEMENTS ====================

    @FindBy(xpath = "//section[@aria-label='Notifications alt+T']")
    public WebElement notificationSection;

    public By toastMessageLocator = By.xpath("//div[contains(@class,'toast')]");

    public By successToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'success') or contains(text(),'Success')]");

    public By errorToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'error') or contains(text(),'Error') or contains(text(),'failed')]");

    // ==================== LOADING ELEMENTS ====================

    @FindBy(xpath = "//div[contains(@class,'spinner') or contains(@class,'loading')]")
    public WebElement loadingSpinner;

    public By loadingSpinnerLocator = By.xpath("//div[contains(@class,'spinner') or contains(@class,'loading')]");

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
     * Get locator for searching by serial number in table
     */
    public By getRowBySerialNumber(String serialNumber) {
        return By.xpath("//tbody/tr[td[contains(text(),'" + serialNumber + "')]]");
    }

    /**
     * Get locator for rows with specific status
     */
    public By getRowsByStatus(String status) {
        return By.xpath("//tbody/tr[.//span[contains(@data-slot,'badge') and contains(text(),'" + status + "')]]");
    }

    /**
     * Get locator for rows with specific command
     */
    public By getRowsByCommand(String command) {
        return By.xpath("//tbody/tr[td[contains(text(),'" + command + "')]]");
    }
}
