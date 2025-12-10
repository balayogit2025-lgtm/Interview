package Pages;

import Elements.RolePageElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RolePage {

    private WebDriver driver;
    private RolePageElements elements;
    private static final Logger logger =  LogManager.getLogger(RolePage.class);

    public RolePage(WebDriver driver) {
        this.driver = driver;
        this.elements = new RolePageElements(driver);
        logger.info("RolePage initialized");
    }

    public RolePage navigatetorolepage() {
        try {
            WebElement role = elements.getRole();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            role.click();
            logger.info("Clicked the role and it is navigated to the role page");
        } catch (Exception e) {
            logger.error("Failed to click role: " + e.getMessage());
            throw new RuntimeException("Unable to click role: ", e);
        }
        return this;
    }

    public RolePage clickaddrole() {
        try {
            WebElement addrole = elements.getAddrole();
            addrole.click();
            logger.info("Clicked add Role");
        } catch (Exception e) {
            logger.error("Failed to click add Role: " + e.getMessage());
            throw new RuntimeException("Unable to click add Role: ", e);
        }
        return this;
    }

    public RolePage enterrolename(String nameoftherole) {
        try {
            WebElement roleName = elements.getRoleName();
            roleName.sendKeys(nameoftherole);
            logger.info("Entered the role name");
        } catch (Exception e) {
            logger.error("Failed to enter the role name: " + e.getMessage());
            throw new RuntimeException("Unable to enter role name: ", e);
        }
        return this;
    }
    public RolePage clickKeyboxusertype() {
        try {
            WebElement keyboxuser = elements.getKeyboxuser();
            keyboxuser.click();
            logger.info("Selected the Keysafe user type");
        } catch (Exception e) {
            logger.error("Failed to select the Keysafe user type: " + e.getMessage());
            throw new RuntimeException("Unable to select the Keysafe user type: ", e);
        }
        return this;
    }

    public RolePage clickwebusertype() {
        try {
            WebElement webuser = elements.getWebuser();
            webuser.click();
            logger.info("Selected the web user type");
        } catch (Exception e) {
            logger.error("Failed to select the web user type: " + e.getMessage());
            throw new RuntimeException("Unable to select the web user type: ", e);
        }
        return this;
    }
    public RolePage clickallpagescheckbox() {
        try {
            WebElement allpagescheckbox = elements.getAllpagecheckbox();
            allpagescheckbox.click();
            logger.info("Clicked the All pages checkbox");
        } catch (Exception e) {
            logger.error("Failed to click the all pages checkbox: " + e.getMessage());
            throw new RuntimeException("Unable to click all pages checkbox: ", e);
        }
        return this;
    }
    public RolePage clicksubmitbutton() {
        try {
            WebElement submit = elements.getSubmit();
            submit.click();
            logger.info("Clicked the submit button");
        } catch (Exception e) {
            logger.error("Failed to click the submit button: " + e.getMessage());
            throw new RuntimeException("Unable to click the submit button: ", e);
        }
        return this;
    }
    public RolePage Addnewwebusertype() {
        navigatetorolepage().clickaddrole().clickwebusertype().enterrolename("TEST ROLE 2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickallpagescheckbox().clicksubmitbutton();
        return this;
    }
    public RolePage Addnewkeyboxusertype() {
        navigatetorolepage().clickaddrole().clickKeyboxusertype().enterrolename("TEST ROLE 2").clicksubmitbutton();
        return this;
    }
    public RolePage Addnewrolewithbothusertype() {
        navigatetorolepage().clickaddrole().clickwebusertype().enterrolename("TEST ROLE 3").clickKeyboxusertype();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickallpagescheckbox().clicksubmitbutton();
        return this;
    }
    public RolePage clickmanagebutton() {
        try {
            WebElement managebutton = elements.getManagebutton();
            managebutton.click();
            logger.info("Clicked the manage button");
        } catch (Exception e) {
            logger.error("Failed to click the manage button: " + e.getMessage());
            throw new RuntimeException("Unable to click the manage button: ", e);
        }
        return this;
    }
    public RolePage clickeditbutton() {
        try {
            WebElement editbutton = elements.getEditbutton();
            editbutton.click();
            logger.info("Clicked the edit button");
        } catch (Exception e) {
            logger.error("Failed to click the edit button: " + e.getMessage());
            throw new RuntimeException("Unable to click the edit button: ", e);
        }
        return this;
    }
    public RolePage editAnRole() {
        navigatetorolepage().clickmanagebutton().clickeditbutton().clicksubmitbutton();
        return this;
    }
    public RolePage clickdeletebutton() {
        try {
            WebElement deletebutton = elements.getDeletebutton();
            deletebutton.click();
            logger.info("Clicked the delete button");
        } catch (Exception e) {
            logger.error("Failed to click the delete button: " + e.getMessage());
            throw new RuntimeException("Unable to click the delete button: ", e);
        }
        return this;
    }
    public RolePage clickdeleteconfirmbutton() {
        try {
            WebElement deleteconfirmbutton = elements.getDeleteconfirmbutton();
            deleteconfirmbutton.click();
            logger.info("Clicked the delete confirm button");
        } catch (Exception e) {
            logger.error("Failed to click the delete confirm button: " + e.getMessage());
            throw new RuntimeException("Unable to click the delete confirm button: ", e);
        }
        return this;
    }

    public RolePage deleteAnRole() {
        navigatetorolepage().clickmanagebutton().clickdeletebutton().clickdeleteconfirmbutton();
        return this;
    }

    public boolean isAddRoleButtonDisplayedAndEnabled() {
        try {
            WebElement rolebutton = elements.getAddrole();
            return rolebutton.isDisplayed() && rolebutton.isEnabled();
        } catch (Exception e) {
            logger.debug("Add Role button not displayed: " + e.getMessage());
            return false;
        }
    }

    public RolePage clickcancelbutton() {
        try {
            WebElement cancelButton = elements.getCancelButton();
            cancelButton.click();
            logger.info("Clicked the cancel button");
        } catch (Exception e) {
            logger.error("Failed to click the cancel button: " + e.getMessage());
            throw new RuntimeException("Unable to click the cancel button: ", e);
        }
        return this;
    }

    public RolePage clicksearchicon() {
        try {
            WebElement searchIcon = elements.getSearchIcon();
            searchIcon.click();
            logger.info("Clicked the search icon");
        } catch (Exception e) {
            logger.error("Failed to click search icon: " + e.getMessage());
            throw new RuntimeException("Unable to click search icon: ", e);
        }
        return this;
    }

    public RolePage entersearchvalue(String value) {
        try {
            WebElement search = elements.getSearch();
            search.sendKeys(value);
            logger.info("Entered the search value");
        } catch (Exception e) {
            logger.error("Failed to enter value in search box: " + e.getMessage());
            throw new RuntimeException("Unable to enter value in search box: ", e);
        }
        return this;
    }
    public RolePage searchavalidvalue() {
        navigatetorolepage().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchvalue("Demo User");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        return this;
    }
    public RolePage searchainvalidvalue() {
        navigatetorolepage().clicksearchicon().entersearchvalue("1528GVT");
        return this;
    }

    public RolePage clearrolename() {
        try {
            WebElement clearroleName = elements.getEditscreenroleName();
            clearroleName.sendKeys(Keys.CONTROL+"a");
            clearroleName.sendKeys(Keys.DELETE);
            logger.info("Cleared the role name field");
        } catch (Exception e) {
            logger.error("Failed to clear the role name field: " + e.getMessage());
            throw new RuntimeException("Unable to clear role name field: ", e);
        }
        return this;
    }

    public int getRoleTableRowCount() {
        try {
              return elements.getRoleTableRows().size();
        } catch (Exception e) {
            logger.debug("Unable to get role table rows: " + e.getMessage());
        }
        return 0;
    }

    public boolean isroletableDisplayed() {
        try {
            WebElement roletable = elements.getRoletable();
            return roletable.isDisplayed();
        } catch (Exception e) {
            logger.debug("Role table not displayed: " + e.getMessage());
            return false;
        }
    }

    public RolePage clickdeletecancelbutton() {
        try {
            WebElement deleteCancelButton = elements.getDeleteCancelButton();
            deleteCancelButton.click();
            logger.info("Clicked the delete cancel button (No)");
        } catch (Exception e) {
            logger.error("Failed to click the delete cancel button: " + e.getMessage());
            throw new RuntimeException("Unable to click the delete cancel button: ", e);
        }
        return this;
    }

    public boolean isRoleNameFieldEmpty() {
        try {
            WebElement roleName = elements.getRoleName();
            String value = roleName.getAttribute("value");
            return value == null || value.isEmpty();
        } catch (Exception e) {
            logger.debug("Unable to check role name field: " + e.getMessage());
            return true;
        }
    }

    public boolean isSubmitButtonDisplayed() {
        try {
            WebElement submit = elements.getSubmit();
            return submit.isDisplayed();
        } catch (Exception e) {
            logger.debug("Submit button not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean iserrormsgofemptyrolenameDisplayed() {
        try {
            WebElement errormsgofemptyrolename = elements.getErrormsgofemptyrolename();
            return errormsgofemptyrolename.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error Message of empty rolename not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean iserrormsgofemptyusertypedisplayed() {
        try {
            WebElement errormsgofemptyusertype = elements.getErrormsgofemptyusertype();
            return errormsgofemptyusertype.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error Message of empty rolename not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean iserrormsgofnotselectingpagedisplayed() {
        try {
            WebElement errormsgofnotselectingthepages = elements.getErrormsgofnotselectingthepages();
            return errormsgofnotselectingthepages.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error Message for not selecting the page is not displayed: " + e.getMessage());
            return false;
        }
    }

    public List<String> getHeaderTexts() {
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : elements.getListOfheaders()) {
            headerTexts.add(header.getText().trim());
        }
        return headerTexts;
    }

    public boolean iserrormsgofspacesdisplayed() {
        try {
            WebElement errormsgofspaces = elements.getErrormsgofspaces();
            return errormsgofspaces.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error Message for entering the role namw with spaces in start & end is not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean iserrormsgofspecialcharactersinrolenamedisplayed() {
        try {
            WebElement errormsgofspecialcharactersusertype = elements.getErrormsgofspecialcharactersinrolename();
            return errormsgofspecialcharactersusertype.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error Message for using special characters in rolename not displayed: " + e.getMessage());
            return false;
        }
    }

    public RolePage addRoleWithEmptyName() {
        navigatetorolepage().clickaddrole().clickKeyboxusertype().clicksubmitbutton();
        return this;
    }

    public RolePage addRoleWithoutUserType(String roleName) {
        navigatetorolepage().clickaddrole().enterrolename("TEST Role 8").clicksubmitbutton();
        return this;
    }

    public RolePage cancelinAddRole() {
        navigatetorolepage().clickaddrole().enterrolename("ROLE 7").clickKeyboxusertype().clickcancelbutton();
        return this;
    }

    public RolePage cancelDeleteRole() {
        navigatetorolepage().clickmanagebutton().clickdeletebutton().clickdeletecancelbutton();
        return this;
    }



    public RolePage addRoleWithSpecialCharacters() {
        navigatetorolepage().clickaddrole().enterrolename("#$@!").clickKeyboxusertype().clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        return this;
    }

    public RolePage addDuplicateRole() {
        navigatetorolepage().clickaddrole().enterrolename("Test role").clickKeyboxusertype().clicksubmitbutton();
        return this;
    }

    public RolePage addRoleWithLongName() {
        String longRoleName = "THIS IS A VERY LONG ROLE NAME THAT EXCEEDS NORMAL LENGTH LIMITS FOR TESTING PURPOSES VERY LONG ROLE NAME THAT EXCEEDS NORMAL LENGTH LIMITS FOR TESTING PURPOSES";
        navigatetorolepage().clickaddrole().enterrolename(longRoleName).clickKeyboxusertype().clicksubmitbutton();
        return this;
    }
    public RolePage addRoleWithonlynumeric(){
        String num = "1234567";
        navigatetorolepage().clickaddrole().enterrolename(num).clickKeyboxusertype().clicksubmitbutton();
        return this;
    }
    public RolePage addRoleWithWhitespaceOnlyName() {
        String whitespaceName = " QA TEST ";
        navigatetorolepage().clickaddrole().enterrolename(whitespaceName).clickKeyboxusertype().clicksubmitbutton();
        return this;
    }

    public boolean isKeysafeUserTypeSelected() {
        try {
            WebElement keysafeUser = elements.getKeyboxuser();
            String ariaChecked = keysafeUser.getAttribute("aria-checked");
            String dataState = keysafeUser.getAttribute("data-state");
            return "true".equals(ariaChecked) || "checked".equals(dataState);
        } catch (Exception e) {
            logger.debug("Unable to check keysafe user type selection: " + e.getMessage());
            return false;
        }
    }

    public boolean isWebUserTypeSelected() {
        try {
            WebElement webUser = elements.getWebuser();
            String ariaChecked = webUser.getAttribute("aria-checked");
            String dataState = webUser.getAttribute("data-state");
            return "true".equals(ariaChecked) || "checked".equals(dataState);
        } catch (Exception e) {
            logger.debug("Unable to check web user type selection: " + e.getMessage());
            return false;
        }
    }

}
