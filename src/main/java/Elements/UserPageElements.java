package Elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserPageElements {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(UserPageElements.class);

    @FindBy(xpath = "//span[text()='User']")
    protected WebElement User;

    @FindBy(xpath = "//button[text()='Add User']")
    protected WebElement AddUser;

    @FindBy(xpath = "//input[@placeholder='Enter First Name']")
    protected WebElement Firstname;

    @FindBy(xpath = "//input[@placeholder='Enter Last Name']")
    protected WebElement Lastname;

    @FindBy(xpath = "//input[@placeholder='Enter Email']")
    protected WebElement Email;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile']")
    protected WebElement Mobile;

    @FindBy(xpath = "(//button[@role='combobox']) [1]")
    protected WebElement Role;

    @FindBy(xpath = "//span[text()='Only Web User']")
    protected WebElement Webuser;

    @FindBy(xpath = "//span[text()='Only Key Box User']")
    protected WebElement Keyboxuser;

    @FindBy(xpath = "//span[text()='Both Type User']")
    protected WebElement Bothtypeuser;

    @FindBy(xpath = "(//button[@role='combobox']) [2]")
    protected WebElement Locationlevel;

    @FindBy(xpath = "//span[text()='Location']")
    protected WebElement Locationlevelvalue;

    @FindBy(xpath = "(//button[@role='combobox']) [3]")
    protected WebElement Location;

    @FindBy(xpath = "//span[text()='Location A']")
    protected WebElement Locationvalue;

    @FindBy(xpath = "//input[@placeholder='Enter Password']")
    protected WebElement Password;

    @FindBy(xpath = "//input[@placeholder='Enter Confirm Password']")
    protected WebElement Confirmpassword;

    @FindBy(xpath = "//input[@placeholder='Enter Display Name']")
    protected WebElement Displayname;

    @FindBy(xpath = "//input[@placeholder='Enter User Code']")
    protected WebElement Usercode;

    @FindBy(xpath = "//button[@role='checkbox']")
    protected WebElement Cardnumbercheckbox;

    @FindBy(xpath = "//input[@placeholder='Enter Card Number']")
    protected WebElement Cardnumber;

    @FindBy(xpath = "//input[@placeholder='Enter Pin']")
    protected WebElement Pin;

    @FindBy(xpath = "//button[text()='Web User']")
    protected WebElement Webusericon;

    @FindBy(xpath = "//button[text()='Key Safe User']")
    protected WebElement Keysafeusericon;

    @FindBy(xpath = "//button[text()='Submit']")
    protected WebElement Submitbutton;

    @FindBy(xpath = "(//*[@id=\"root\"]/div/div/main/div/div/div[2]/div/div/div[1]/div/div/table/tbody/tr/td/button) [1]")
    protected WebElement Managebutton;

    @FindBy(xpath = "//button[text()='View']")
    protected WebElement Viewbutton;

    @FindBy(xpath = "//h3[text()='Role & Location']")
    protected WebElement RoleandLocationInView;

    @FindBy(xpath = "//button[text()='Edit']")
    protected WebElement Editbutton;

    @FindBy(xpath = "//button[text()='Delete']")
    protected WebElement Deletebutton;

    @FindBy(xpath = "//button[text()='Yes']")
    protected WebElement Deleteconfirmbutton;

    @FindBy(xpath = "//button[text()='Cancel']")
    protected WebElement Cancelbutton;

    @FindBy(xpath = "//button[text()='No']")
    protected WebElement Deletecancelbutton;

    @FindBy(xpath = "//button[@aria-label='Expand search']")
    protected WebElement Searchicon;

    @FindBy(xpath = "//input[@placeholder='Search Users...']")
    protected WebElement Search;

    @FindBy(xpath = "//tr[@class='border-y border-transparent border-b-slate-100 ']")
    private List<WebElement> UserTableRows;

    @FindBy(xpath = "//p[text()='First name should contain minimum 2 characters']")
    protected WebElement Errormsgofemptyusername;

    @FindBy(xpath = "//p[text()='First name should start and end with an alphanumeric character']")
    protected WebElement Errormsgforspaces;

    @FindBy(xpath = "//th[@class='border border-primary-50 whitespace-nowrap bg-primary/80 px-4 py-1.5 uppercase text-primary-foreground text-xs+ lg:px-5']")
    protected List<WebElement> listOfheaders;

    @FindBy(xpath = "//p[text()='First name can only contain letters, numbers, periods (.), hyphens (-) and underscores (_)']")
    protected WebElement Errormsgofusingspecialcharacters;

    @FindBy(xpath = "//p[text()='User code must be exactly 4 digits']")
    protected WebElement Errormsgofinvalidusercode;

    @FindBy(xpath = "//p[text()='PIN must be 4-6 digits']")
    protected WebElement Errormsgofinvalidpin;

    @FindBy(xpath = "//h2[text()='Add User']")
    protected WebElement Adduserlabel;

    @FindBy(xpath = "(//span[@class='text-xl']) [4]")
    protected WebElement Lastpagebutton;

    public UserPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("UserPageElement initialized");
    }

    public WebElement getUser() {
        return User;
    }

    public WebElement getAddUser() {
        return AddUser;
    }

    public WebElement getFirstname() {
        return Firstname;
    }

    public WebElement getLastname() {
        return Lastname;
    }

    public WebElement getEmail() {
        return Email;
    }

    public WebElement getMobile() {
        return Mobile;
    }

    public WebElement getRole() {
        return Role;
    }

    public WebElement getWebuser() {
        return Webuser;
    }

    public WebElement getKeyboxuser() {
        return Keyboxuser;
    }

    public WebElement getBothtypeuser() {
        return Bothtypeuser;
    }

    public WebElement getLocationlevel() {
        return Locationlevel;
    }

    public WebElement getLocationlevelvalue() {
        return Locationlevelvalue;
    }

    public WebElement getLocation() {
        return Location;
    }

    public WebElement getLocationvalue() {
        return Locationvalue;
    }

    public WebElement getPassword() {return Password;}

    public WebElement getConfirmpassword() {
        return Confirmpassword;
    }

    public WebElement getDisplayname() {return Displayname;}

    public WebElement getUsercode() {return Usercode;}

    public WebElement getCardnumbercheckbox() {return Cardnumbercheckbox;}

    public WebElement getCardnumber() {return Cardnumber;}

    public WebElement getPin() {return Pin;}

    public WebElement getWebusericon() {return Webusericon;}

    public WebElement getKeysafeusericon() {return Keysafeusericon;}

    public WebElement getSubmitbutton() {
        return Submitbutton;
    }

    public WebElement getManagebutton() {
        return Managebutton;
    }

    public WebElement getViewbutton() {
        return Viewbutton;
    }

    public WebElement getRoleandLocationInView() {
        return RoleandLocationInView;
    }

    public WebElement getEditbutton() {
        return Editbutton;
    }

    public WebElement getDeletebutton() {
        return Deletebutton;
    }

    public WebElement getDeleteconfirmbutton() {
        return Deleteconfirmbutton;
    }

    public WebElement getCancelbutton() {
        return Cancelbutton;
    }

    public WebElement getDeletecancelbutton() {
        return Deletecancelbutton;
    }

    public List<WebElement> getUserTableRows() {return UserTableRows;}

    public WebElement getSearchicon() {
        return Searchicon;
    }

    public WebElement getSearch() {
        return Search;
    }

    public WebElement getErrormsgofemptyusername() {
        return Errormsgofemptyusername;
    }

    public WebElement getErrormsgforspaces() {
        return Errormsgforspaces;
    }

    public List<WebElement> getListOfheaders() {return listOfheaders;}

    public WebElement getErrormsgofusingspecialcharacters() {return Errormsgofusingspecialcharacters;}

    public WebElement getErrormsgofinvalidusercode() {
        return Errormsgofinvalidusercode;
    }

    public WebElement getErrormsgofinvalidpin() {
        return Errormsgofinvalidpin;
    }

    public WebElement getAdduserlabel() {return Adduserlabel;}

    public WebElement getLastpagebutton() {return Lastpagebutton;}


}

