package Elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RolePageElements {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(RolePageElements.class);

    @FindBy(xpath = "//span[text()='Role']")
    protected WebElement Role;

    @FindBy(xpath = "//button[text()='Add Role']")
    protected WebElement Addrole;

    @FindBy(xpath = "//input[@placeholder='Enter Role Name']")
    protected WebElement roleName;

    @FindBy(xpath = "//label[text()='Key Box User']")
    protected WebElement keyboxuser;

    @FindBy(xpath = "//label[text()='Web User']")
    protected WebElement webuser;

    @FindBy(xpath = "(//button[@role='checkbox']) [3]")
    protected WebElement allpagecheckbox;

    @FindBy(xpath = "//button[text()='Submit']")
    protected WebElement submit;

    @FindBy(xpath = "(//button[@data-slot='popover-trigger']) [1]")
    protected WebElement Managebutton;

    @FindBy(xpath = "//button[text()='Edit']")
    protected WebElement editbutton;

    @FindBy(xpath = "//button[text()='Delete']")
    protected WebElement deletebutton;

    @FindBy(xpath = "//button[text()='Yes']")
    protected WebElement deleteconfirmbutton;

    @FindBy(xpath = "//button[text()='Cancel']")
    protected WebElement cancelButton;

    @FindBy(xpath = "//button[@aria-label='Expand search']")
    protected WebElement searchIcon;

    @FindBy(xpath = "//input[@placeholder='Search Roles...']")
    protected WebElement search;

    @FindBy(xpath = "//button[text()='No']")
    protected WebElement deleteCancelButton;

    @FindBy(xpath = "//p[text()='name should contain minimum 2 characters']")
    protected WebElement errormsgofemptyrolename;

    @FindBy(xpath = "//p[text()='Please select at least one user type (Key Safe User or Web User)']")
    protected WebElement errormsgofemptyusertype;

    @FindBy(xpath = "//p[text()='Please select at least one page']")
    protected WebElement errormsgofnotselectingthepages;

    @FindBy(xpath = "//p[text()='name should start and end with an alphanumeric character']")
    protected WebElement errormsgofspecialcharactersinrolename;

    @FindBy(xpath = "//p[text()='name should start and end with an alphanumeric character']")
    protected WebElement errormsgofspaces;

    @FindBy(xpath = "//table[@class='w-full text-left  border border-primary-50 rounded-lg table']")
    protected WebElement roletable;

    @FindBy(xpath = "//input[@name='name']")
    protected WebElement editscreenroleName;

    @FindBy(xpath = "//th[@class='border border-primary-50 whitespace-nowrap bg-primary/80 px-4 py-1.5 uppercase text-primary-foreground text-xs+ lg:px-5']")
    protected List<WebElement> listOfheaders;

    public RolePageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("RolePageElement initialized");
    }

    public WebElement getRole() {
        return Role;
    }

    public WebElement getAddrole() {
        return Addrole;
    }

    public WebElement getRoleName() {
        return roleName;
    }

    public WebElement getKeyboxuser() {
        return keyboxuser;
    }

    public WebElement getWebuser() {
        return webuser;
    }

    public WebElement getSubmit() {
        return submit;
    }

    public WebElement getAllpagecheckbox() {
        return allpagecheckbox;
    }

    public WebElement getManagebutton() {
        return Managebutton;
    }

    public WebElement getEditbutton() {
        return editbutton;
    }

    public WebElement getDeletebutton() {
        return deletebutton;
    }

    public WebElement getDeleteconfirmbutton() {
        return deleteconfirmbutton;
    }

    public WebElement getCancelButton() {return cancelButton;}

    public WebElement getDeleteCancelButton() {return deleteCancelButton;}

    public WebElement getErrormsgofemptyrolename() {
        return errormsgofemptyrolename;
    }

    public WebElement getErrormsgofemptyusertype() {
        return errormsgofemptyusertype;
    }

    public WebElement getErrormsgofnotselectingthepages() {return errormsgofnotselectingthepages;}

    public WebElement getErrormsgofspaces() {
        return errormsgofspaces;
    }

    public WebElement getSearchIcon() {
        return searchIcon;
    }

    public WebElement getSearch() {
        return search;
    }

    public WebElement getErrormsgofspecialcharactersinrolename() {
        return errormsgofspecialcharactersinrolename;
    }

    public WebElement getRoletable() {return roletable;}

    public WebElement getEditscreenroleName() {
        return editscreenroleName;
    }

    public List<WebElement> getListOfheaders() {return listOfheaders;}


}
