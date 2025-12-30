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
 * KeyPositionTemplatePageElements - Contains all element locators for Key Position Template page
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class KeyPositionTemplatePageElements {

    private static final Logger logger = LogManager.getLogger(KeyPositionTemplatePageElements.class);

    public KeyPositionTemplatePageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        logger.info("Key Position Template Page Elements initialized");
    }

    // ==================== HEADER SECTION ====================

    @FindBy(xpath = "//h1[text()='Key Position Template']")
    public WebElement pageHeader;

    public By pageHeaderLocator = By.xpath("//h1[text()='Key Position Template']");

    @FindBy(xpath = "//p[text()='Manage key position template for activity']")
    public WebElement pageSubHeader;

    public By pageSubHeaderLocator = By.xpath("//p[text()='Manage key position template for activity']");

    // ==================== SEARCH ELEMENTS ====================

    @FindBy(xpath = "//button[@aria-label='Expand search']")
    public WebElement searchButton;

    public By searchButtonLocator = By.xpath("//button[@aria-label='Expand search']");

    @FindBy(xpath = "//input[@data-slot='input']")
    public WebElement searchInput;

    public By searchInputLocator = By.xpath("//input[@data-slot='input']");

    // ==================== ADD TEMPLATE BUTTON ====================

    @FindBy(xpath = "//button[contains(.,'Add Template')]")
    public WebElement addTemplateButton;

    public By addTemplateButtonLocator = By.xpath("//button[contains(.,'Add Template')]");

    // ==================== TABLE ELEMENTS ====================

    @FindBy(xpath = "//table[@class='w-full text-left  border border-primary-50 rounded-lg table']")
    public WebElement templateTable;

    public By templateTableLocator = By.xpath("//table[@class='w-full text-left  border border-primary-50 rounded-lg table']");

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

    // ==================== INDIVIDUAL TABLE HEADERS ====================

    @FindBy(xpath = "//th[contains(text(),'S.No')]")
    public WebElement snoHeader;

    public By snoHeaderLocator = By.xpath("//th[contains(text(),'S.No')]");

    @FindBy(xpath = "//th[contains(text(),'Name')]")
    public WebElement nameHeader;

    public By nameHeaderLocator = By.xpath("//th[contains(text(),'Name')]");

    @FindBy(xpath = "//th[contains(text(),'Created By')]")
    public WebElement createdByHeader;

    public By createdByHeaderLocator = By.xpath("//th[contains(text(),'Created By')]");

    @FindBy(xpath = "//th[contains(text(),'Updated By')]")
    public WebElement updatedByHeader;

    public By updatedByHeaderLocator = By.xpath("//th[contains(text(),'Updated By')]");

    @FindBy(xpath = "//th[contains(text(),'Action')]")
    public WebElement actionHeader;

    public By actionHeaderLocator = By.xpath("//th[contains(text(),'Action')]");

    // ==================== TABLE BODY ELEMENTS ====================

    @FindBy(xpath = "//td[@class='table-body-active ']")
    public List<WebElement> templateNameCells;

    public By templateNameCellsLocator = By.xpath("//td[@class='table-body-active ']");

    @FindBy(xpath = "(//td[@class='table-body-active '])[1]")
    public WebElement firstTemplateName;

    public By firstTemplateNameLocator = By.xpath("(//td[@class='table-body-active '])[1]");

    // ==================== ACTION BUTTONS ====================

    @FindBy(xpath = "//button[@data-slot='popover-trigger']")
    public List<WebElement> actionButtons;

    public By actionButtonsLocator = By.xpath("//button[@data-slot='popover-trigger']");

    @FindBy(xpath = "(//button[@data-slot='popover-trigger'])[1]")
    public WebElement firstActionButton;

    public By firstActionButtonLocator = By.xpath("(//button[@data-slot='popover-trigger'])[1]");

    @FindBy(xpath = "//button[text()='View']")
    public WebElement viewButton;

    public By viewButtonLocator = By.xpath("//button[text()='View']");

    @FindBy(xpath = "//button[text()='Edit']")
    public WebElement editButton;

    public By editButtonLocator = By.xpath("//button[text()='Edit']");

    @FindBy(xpath = "//button[text()='Delete']")
    public WebElement deleteButton;

    public By deleteButtonLocator = By.xpath("//button[text()='Delete']");

    // ==================== PAGINATION ELEMENTS ====================

    @FindBy(xpath = "//div[contains(text(),'Showing From')]")
    public WebElement paginationInfo;

    public By paginationInfoLocator = By.xpath("//div[contains(text(),'Showing From')]");

    @FindBy(xpath = "//div[contains(text(),'Page') and contains(text(),'of')]")
    public WebElement pageNumberInfo;

    public By pageNumberInfoLocator = By.xpath("//div[contains(text(),'Page') and contains(text(),'of')]");

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

    // ==================== MODAL ELEMENTS ====================

    @FindBy(xpath = "//div[@data-slot='dialog-content']")
    public WebElement modal;

    public By modalLocator = By.xpath("//div[@data-slot='dialog-content']");

    @FindBy(xpath = "//div[@role='dialog']//h2")
    public WebElement modalHeader;

    public By modalHeaderLocator = By.xpath("//div[@role='dialog']//h2");

    @FindBy(xpath = "//button[@data-slot='dialog-close']")
    public WebElement modalCloseButton;

    public By modalCloseButtonLocator = By.xpath("//button[@data-slot='dialog-close']");

    // ==================== ADD/EDIT TEMPLATE FORM ELEMENTS ====================

    @FindBy(xpath = "//label[text()='Template Name']/following-sibling::input | //input[@placeholder='Enter template name']")
    public WebElement templateNameInput;

    public By templateNameInputLocator = By.xpath("//label[text()='Template Name']/following-sibling::input | //input[@placeholder='Enter template name']");

    @FindBy(xpath = "//button[text()='Cancel']")
    public WebElement cancelButton;

    public By cancelButtonLocator = By.xpath("//button[text()='Cancel']");

    @FindBy(xpath = "//button[text()='Submit']")
    public WebElement submitButton;

    public By submitButtonLocator = By.xpath("//button[text()='Submit']");

    @FindBy(xpath = "//button[text()='Save']")
    public WebElement saveButton;

    public By saveButtonLocator = By.xpath("//button[text()='Save']");

    // ==================== ADD KEY POSITION TEMPLATE MODAL ELEMENTS ====================

    @FindBy(xpath = "//h2[text()='Add Key Position Template']")
    public WebElement addTemplateModalTitle;

    public By addTemplateModalTitleLocator = By.xpath("//h2[text()='Add Key Position Template']");

    @FindBy(xpath = "//p[text()='Create a new key position template by selecting positions']")
    public WebElement addTemplateModalDescription;

    public By addTemplateModalDescriptionLocator = By.xpath("//p[text()='Create a new key position template by selecting positions']");

    @FindBy(xpath = "//label[text()='Name']")
    public WebElement nameLabel;

    public By nameLabelLocator = By.xpath("//label[text()='Name']");

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    public WebElement nameInput;

    public By nameInputLocator = By.xpath("//input[@placeholder='Enter Name']");

    // ==================== KEY POSITIONS TABLE ELEMENTS ====================

    @FindBy(xpath = "//div[@role='dialog']//table")
    public WebElement keyPositionsTable;

    public By keyPositionsTableLocator = By.xpath("//div[@role='dialog']//table");

    @FindBy(xpath = "//div[@role='dialog']//thead//th")
    public List<WebElement> keyPositionsTableHeaders;

    public By keyPositionsTableHeadersLocator = By.xpath("//div[@role='dialog']//thead//th");

    @FindBy(xpath = "//th[text()='Enable']")
    public WebElement enableHeader;

    public By enableHeaderLocator = By.xpath("//th[text()='Enable']");

    @FindBy(xpath = "//div[@role='dialog']//th[text()='Name']")
    public WebElement keyPositionNameHeader;

    public By keyPositionNameHeaderLocator = By.xpath("//div[@role='dialog']//th[text()='Name']");

    // ==================== KEY POSITION ROWS ====================

    @FindBy(xpath = "//div[@role='dialog']//tbody//tr")
    public List<WebElement> keyPositionRows;

    public By keyPositionRowsLocator = By.xpath("//div[@role='dialog']//tbody//tr");

    @FindBy(xpath = "//div[@role='dialog']//tbody//tr//button[@role='checkbox']")
    public List<WebElement> enableCheckboxes;

    public By enableCheckboxesLocator = By.xpath("//div[@role='dialog']//tbody//tr//button[@role='checkbox']");

    @FindBy(xpath = "//div[@role='dialog']//tbody//tr//td[2]")
    public List<WebElement> keyPositionNames;

    public By keyPositionNamesLocator = By.xpath("//div[@role='dialog']//tbody//tr//td[2]");

    // ==================== POSITION SELECTOR BUTTONS (1-24) ====================

    @FindBy(xpath = "//div[@role='dialog']//tbody//tr//button[contains(@class,'rounded-full')]")
    public List<WebElement> positionSelectorButtons;

    public By positionSelectorButtonsLocator = By.xpath("//div[@role='dialog']//tbody//tr//button[contains(@class,'rounded-full')]");

    // ==================== MODAL FOOTER BUTTONS ====================

    @FindBy(xpath = "//div[@role='dialog']//button[text()='Cancel']")
    public WebElement modalCancelButton;

    public By modalCancelButtonLocator = By.xpath("//div[@role='dialog']//button[text()='Cancel']");

    @FindBy(xpath = "//div[@role='dialog']//button[text()='Submit']")
    public WebElement modalSubmitButton;

    public By modalSubmitButtonLocator = By.xpath("//div[@role='dialog']//button[text()='Submit']");

    // ==================== SPECIFIC KEY POSITION NAMES ====================

    @FindBy(xpath = "//td[contains(text(),'Fill p.tank')]")
    public List<WebElement> fillPTankPositions;

    public By fillPTankPositionsLocator = By.xpath("//td[contains(text(),'Fill p.tank')]");

    @FindBy(xpath = "//td[contains(text(),'El.head dis.')]")
    public List<WebElement> elHeadDisPositions;

    public By elHeadDisPositionsLocator = By.xpath("//td[contains(text(),'El.head dis.')]");

    @FindBy(xpath = "//td[contains(text(),'hyd.ch.dis.')]")
    public List<WebElement> hydChDisPositions;

    public By hydChDisPositionsLocator = By.xpath("//td[contains(text(),'hyd.ch.dis.')]");

    @FindBy(xpath = "//td[contains(text(),'Post mix oil')]")
    public WebElement postMixOilPosition;

    public By postMixOilPositionLocator = By.xpath("//td[contains(text(),'Post mix oil')]");

    @FindBy(xpath = "//td[contains(text(),'Tank truck key')]")
    public WebElement tankTruckKeyPosition;

    public By tankTruckKeyPositionLocator = By.xpath("//td[contains(text(),'Tank truck key')]");

    @FindBy(xpath = "//td[contains(text(),'UG tank padlock')]")
    public WebElement ugTankPadlockPosition;

    public By ugTankPadlockPositionLocator = By.xpath("//td[contains(text(),'UG tank padlock')]");

    @FindBy(xpath = "//td[contains(text(),'Spare (IT room)')]")
    public WebElement spareITRoomPosition;

    public By spareITRoomPositionLocator = By.xpath("//td[contains(text(),'Spare (IT room)')]");

    // ==================== DELETE CONFIRMATION ELEMENTS ====================

    @FindBy(xpath = "//div[@role='alertdialog']")
    public WebElement deleteConfirmDialog;

    public By deleteConfirmDialogLocator = By.xpath("//div[@role='alertdialog']");

    @FindBy(xpath = "//button[text()='Confirm']")
    public WebElement confirmDeleteButton;

    public By confirmDeleteButtonLocator = By.xpath("//button[text()='Confirm']");

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement yesButton;

    public By yesButtonLocator = By.xpath("//button[text()='Yes']");

    @FindBy(xpath = "//button[text()='No']")
    public WebElement noButton;

    public By noButtonLocator = By.xpath("//button[text()='No']");

    // ==================== EMPTY STATE ELEMENTS ====================

    @FindBy(xpath = "//td[contains(text(),'No results found')]")
    public WebElement noResultsMessage;

    public By noResultsMessageLocator = By.xpath("//td[contains(text(),'No results found')]");

    @FindBy(xpath = "//td[contains(text(),'No data available')]")
    public WebElement noDataMessage;

    public By noDataMessageLocator = By.xpath("//td[contains(text(),'No data available')]");

    // ==================== TOAST/NOTIFICATION ELEMENTS ====================

    @FindBy(xpath = "//section[@aria-label='Notifications alt+T']")
    public WebElement notificationSection;

    public By toastMessageLocator = By.xpath("//div[contains(@class,'toast')]");

    public By successToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'success') or contains(text(),'Success') or contains(text(),'created') or contains(text(),'Created')]");

    public By errorToastLocator = By.xpath("//div[contains(@class,'toast')]//div[contains(text(),'error') or contains(text(),'Error') or contains(text(),'failed') or contains(text(),'Failed')]");

    @FindBy(xpath = "//ol/li/div/div[contains(@class, 'toast')]")
    public WebElement toastMessage;

    // ==================== SIDEBAR NAVIGATION ====================

    @FindBy(xpath = "//a[@href='/key-position-template']")
    public WebElement keyPositionTemplateNavLink;

    public By keyPositionTemplateNavLinkLocator = By.xpath("//a[@href='/key-position-template']");

    @FindBy(xpath = "//span[text()='Key Position Template']")
    public WebElement keyPositionTemplateMenuText;

    public By keyPositionTemplateMenuTextLocator = By.xpath("//span[text()='Key Position Template']");

    @FindBy(xpath = "//span[text()='Settings']")
    public WebElement settingsMenu;

    public By settingsMenuLocator = By.xpath("//span[text()='Settings']");

    // ==================== LOADING ELEMENTS ====================

    @FindBy(xpath = "//div[contains(@class,'spinner') or contains(@class,'loading')]")
    public WebElement loadingSpinner;

    public By loadingSpinnerLocator = By.xpath("//div[contains(@class,'spinner') or contains(@class,'loading')]");

    // ==================== SCROLL AREA ====================

    @FindBy(xpath = "//div[@data-slot='scroll-area']")
    public WebElement scrollArea;

    // ==================== VALIDATION ERROR ELEMENTS ====================

    @FindBy(xpath = "//p[contains(@class,'text-destructive') or contains(@class,'error')]")
    public WebElement validationError;

    public By validationErrorLocator = By.xpath("//p[contains(@class,'text-destructive') or contains(@class,'error')]");

    @FindBy(xpath = "//span[contains(@class,'text-destructive') or contains(@class,'error')]")
    public WebElement fieldError;

    public By fieldErrorLocator = By.xpath("//span[contains(@class,'text-destructive') or contains(@class,'error')]");

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
     * Get action button for specific row (1-based index)
     */
    public By getActionButtonLocator(int rowIndex) {
        return By.xpath("(//button[@data-slot='popover-trigger'])[" + rowIndex + "]");
    }

    /**
     * Get locator for template by name
     */
    public By getTemplateByName(String templateName) {
        return By.xpath("//td[contains(@class,'table-body-active') and contains(text(),'" + templateName + "')]");
    }

    /**
     * Get locator for row containing specific template name
     */
    public By getRowByTemplateName(String templateName) {
        return By.xpath("//tbody/tr[td[contains(text(),'" + templateName + "')]]");
    }

    /**
     * Get created by name from specific row (1-based index)
     */
    public By getCreatedByNameLocator(int rowIndex) {
        return By.xpath("//tbody/tr[" + rowIndex + "]/td[3]//div[contains(@class,'capitalize')]");
    }

    /**
     * Get updated by name from specific row (1-based index)
     */
    public By getUpdatedByNameLocator(int rowIndex) {
        return By.xpath("//tbody/tr[" + rowIndex + "]/td[4]//div[contains(@class,'capitalize')]");
    }

    // ==================== MODAL DYNAMIC LOCATOR HELPERS ====================

    /**
     * Get locator for position header column by position number (1-24)
     */
    public By getPositionHeaderLocator(int positionNumber) {
        return By.xpath("//div[@role='dialog']//th[text()='" + positionNumber + "']");
    }

    /**
     * Get locator for enable checkbox in specific row (1-based index)
     */
    public By getEnableCheckboxLocator(int rowIndex) {
        return By.xpath("(//div[@role='dialog']//tbody//tr//button[@role='checkbox'])[" + rowIndex + "]");
    }

    /**
     * Get locator for key position name in specific row (1-based index)
     */
    public By getKeyPositionNameLocator(int rowIndex) {
        return By.xpath("//div[@role='dialog']//tbody//tr[" + rowIndex + "]//td[2]");
    }

    /**
     * Get locator for position selector button by row and position number
     */
    public By getPositionSelectorLocator(int rowIndex, int positionNumber) {
        return By.xpath("//div[@role='dialog']//tbody//tr[" + rowIndex + "]//td[" + (positionNumber + 2) + "]//button");
    }

    /**
     * Get locator for specific key position row by name
     */
    public By getKeyPositionRowByName(String positionName) {
        return By.xpath("//div[@role='dialog']//tbody//tr[td[contains(text(),'" + positionName + "')]]");
    }

    /**
     * Get locator for enable checkbox by key position name
     */
    public By getEnableCheckboxByPositionName(String positionName) {
        return By.xpath("//div[@role='dialog']//tbody//tr[td[contains(text(),'" + positionName + "')]]//button[@role='checkbox']");
    }

    /**
     * Get all position selector buttons for a specific row (1-based index)
     */
    public By getRowPositionSelectorsLocator(int rowIndex) {
        return By.xpath("//div[@role='dialog']//tbody//tr[" + rowIndex + "]//button[contains(@class,'rounded-full')]");
    }

    // ==================== EDIT KEY POSITION TEMPLATE MODAL ELEMENTS ====================

    @FindBy(xpath = "//h2[text()='Edit Key Position Template']")
    public WebElement editTemplateModalTitle;

    public By editTemplateModalTitleLocator = By.xpath("//h2[text()='Edit Key Position Template']");

    @FindBy(xpath = "//p[text()='Update key position template']")
    public WebElement editTemplateModalDescription;

    public By editTemplateModalDescriptionLocator = By.xpath("//p[text()='Update key position template']");

    @FindBy(xpath = "//label[text()='Template Name']")
    public WebElement templateNameLabel;

    public By templateNameLabelLocator = By.xpath("//label[text()='Template Name']");

    @FindBy(xpath = "//input[@placeholder='Enter Template name']")
    public WebElement editTemplateNameInput;

    public By editTemplateNameInputLocator = By.xpath("//input[@placeholder='Enter Template name']");

    @FindBy(xpath = "//label[text()='Key Positions']")
    public WebElement keyPositionsLabel;

    public By keyPositionsLabelLocator = By.xpath("//label[text()='Key Positions']");

    // ==================== ACTION POPOVER ELEMENTS ====================

    @FindBy(xpath = "//div[@data-slot='popover-content']")
    public WebElement actionPopover;

    public By actionPopoverLocator = By.xpath("//div[@data-slot='popover-content']");

    @FindBy(xpath = "//p[text()='Actions']")
    public WebElement actionsHeader;

    public By actionsHeaderLocator = By.xpath("//p[text()='Actions']");

    @FindBy(xpath = "//div[@data-slot='popover-content']//button[text()='Edit']")
    public WebElement popoverEditButton;

    public By popoverEditButtonLocator = By.xpath("//div[@data-slot='popover-content']//button[text()='Edit']");
}
