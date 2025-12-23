package Pages;

import Utils.CommonUtil;
import Elements.UserPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserPage {

    private WebDriver driver;
    private UserPageElements elements;
    private WaitHelper waitHelper;
    private CommonUtil commonUtil;
    private static final Logger logger =  LogManager.getLogger(UserPage.class);

    public UserPage(WebDriver driver) {
        this.driver = driver;
        this.elements = new UserPageElements(driver);
        this.waitHelper = new WaitHelper(driver);
        this.commonUtil = new CommonUtil(driver);
        logger.info("UserPage initialized");
    }

    public UserPage navigatetouserpage() {
        try {
            Thread.sleep(500);
            WebElement user = elements.getUser();
             user.click();
            logger.info("Clicked the User and it is navigated to the user page");
        } catch (Exception e) {
            logger.error("Failed to click user: " + e.getMessage());
            throw new RuntimeException("Unable to click user: ", e);
        }
        return this;
    }
    public boolean isadduserbuttonanabledanddisplayed() {
        try {
            WebElement addUser = elements.getAddUser();
            return addUser.isDisplayed() && addUser.isEnabled();
        } catch (Exception e) {
            logger.debug("Add user button not displayed: " + e.getMessage());
            return false;
        }
    }
    public UserPage clickadduserbutton() {
        try {
            WebElement addUseruser = elements.getAddUser();
            addUseruser.click();
            logger.info("Clicked the Add User button");
        } catch (Exception e) {
            logger.error("Failed to click add user button: " + e.getMessage());
            throw new RuntimeException("Unable to click add user button: ", e);
        }
        return this;
    }
    public UserPage enterfirstname(String firstname) {
        try {
            WebElement userfirstname = elements.getFirstname();
            userfirstname.sendKeys(firstname);
            logger.info("Entered the firstname");
        } catch (Exception e) {
            logger.error("Failed to enter the firstname: " + e.getMessage());
            throw new RuntimeException("Unable to enter firstname: ", e);
        }
        return this;
    }
    public UserPage enterlastname(String lastname) {
        try {
            WebElement userlastname = elements.getLastname();
            userlastname.sendKeys(lastname);
            logger.info("Entered the lastname");
        } catch (Exception e) {
            logger.error("Failed to enter the lastname: " + e.getMessage());
            throw new RuntimeException("Unable to enter the lastname: ", e);
        }
        return this;
    }
    public UserPage enteremail (String email) {
        try {
            WebElement useremail = elements.getEmail();
            useremail.sendKeys(email);
            logger.info("Entered the email");
        } catch (Exception e) {
            logger.error("Failed to enter email: " + e.getMessage());
            throw new RuntimeException("Unable to enter email: ", e);
        }
        return this;
    }
    public UserPage entermobile(String mobile) {
        try {
            WebElement usermobile = elements.getMobile();
            usermobile.sendKeys(mobile);
            logger.info("Entered the mobile");
        } catch (Exception e) {
            logger.error("Failed to enter the mobile: " + e.getMessage());
            throw new RuntimeException("Unable to enter mobile: ", e);
        }
        return this;
    }
    public UserPage clickroledropdown() {
        try {
            WebElement role = elements.getRole();
            role.click();
            logger.info("Clicked the role dropdown");
        } catch (Exception e) {
            logger.error("Failed to click Role dropdown: " + e.getMessage());
            throw new RuntimeException("Unable to click Role dropdown: ", e);
        }
        return this;
    }
    public UserPage Selectwebusertype() {
        try {
            WebElement webuser = elements.getWebuser();
            webuser.click();
            logger.info("Selected the Web user Role");
        } catch (Exception e) {
            logger.error("Failed to select the web suer role: " + e.getMessage());
            throw new RuntimeException("Unable to select web user role: ", e);
        }
        return this;
    }
    public UserPage Selectkeyboxusertype() {
        try {
            WebElement keyboxuser = elements.getKeyboxuser();
            keyboxuser.click();
            logger.info("Selected the key box user Role");
        } catch (Exception e) {
            logger.error("Failed to select the key box user role: " + e.getMessage());
            throw new RuntimeException("Unable to select the key box user role: ", e);
        }
        return this;
    }
    public UserPage Selectbothusertype() {
        try {
            WebElement bothusertype = elements.getBothtypeuser();
            bothusertype.click();
            logger.info("Selected the both user type role");
        } catch (Exception e) {
            logger.error("Failed to select both user type role: " + e.getMessage());
            throw new RuntimeException("Unable to select Both user type role: ", e);
        }
        return this;
    }
    public UserPage clicklocationleveldropdown() {
        try {
            WebElement Locationlevel = elements.getLocationlevel();
            Locationlevel.click();
            logger.info("Clicked the Location level dropdown");
        } catch (Exception e) {
            logger.error("Failed to click the location level dropdown: " + e.getMessage());
            throw new RuntimeException("Unable to click the location level dropdown: ", e);
        }
        return this;
    }
    public UserPage clicklocationlevelvalue() {
        try {
            elements.getLocationlevelvalue().click();
            logger.info("Selected the Location level value");
        } catch (Exception e) {
            logger.error("Failed to select the location level value: " + e.getMessage());
            throw new RuntimeException("Unable to select the location level value: ", e);
        }
        return this;
    }
    public UserPage clicklocationdropdown() {
        try {
            WebElement location = elements.getLocation();
            location.click();
            logger.info("Clicked the location dropdown");
        } catch (Exception e) {
            logger.error("Failed to click the locatiion dropdown: " + e.getMessage());
            throw new RuntimeException("Unable to click the location dropdown: ", e);
        }
        return this;
    }
    public UserPage clicklocationvalue() {
        try {
            elements.getLocationvalue().click();
            logger.info("Selected the location value");
        } catch (Exception e) {
            logger.error("Failed to select the locatiion value: " + e.getMessage());
            throw new RuntimeException("Unable to select the location value: ", e);
        }
        return this;
    }
    public UserPage enterpassword(String password) {
        try {
            WebElement userpassword = elements.getPassword();
            userpassword.sendKeys(password);
            logger.info("Entered the password");
        } catch (Exception e) {
            logger.error("Failed to enter the password: " + e.getMessage());
            throw new RuntimeException("Unable to enter password: ", e);
        }
        return this;
    }
    public UserPage enterconfirmpassword  (String confirmpassowrd) {
        try {
            WebElement userconfirmpassword = elements.getConfirmpassword();
            userconfirmpassword.sendKeys(confirmpassowrd);
            logger.info("Entered the confirmpassword");
        } catch (Exception e) {
            logger.error("Failed to enter the confirm password: " + e.getMessage());
            throw new RuntimeException("Unable to enter the confirm password: ", e);
        }
        return this;
    }
    public UserPage enterdisplayname(String displayname) {
        try {
            WebElement displayname1 = elements.getDisplayname();
            displayname1.sendKeys(displayname);
            logger.info("Entered the Display name");
        } catch (Exception e) {
            logger.error("Failed to enter the Display name: " + e.getMessage());
            throw new RuntimeException("Unable to enter the Display name: ", e);
        }
        return this;
    }
    public UserPage enterusercode(String usercode) {
        try {
            WebElement usercode1 = elements.getUsercode();
            usercode1.sendKeys(usercode);
            logger.info("Entered the usercode");
        } catch (Exception e) {
            logger.error("Failed to enter the usercode: " + e.getMessage());
            throw new RuntimeException("Unable to enter the usercode: ", e);
        }
        return this;
    }
    public UserPage clickcardnumbercheckbox() {
        try {
            WebElement cardnumbercheckbox = elements.getCardnumbercheckbox();
            cardnumbercheckbox.click();
            logger.info("Clicked the card number checkbox");
        } catch (Exception e) {
            logger.error("Failed to click the card number checkbox: " + e.getMessage());
            throw new RuntimeException("Unable to click the card number checkbox: ", e);
        }
        return this;
    }
    public UserPage entercardnumber(String cardnumber) {
        try {
            WebElement cardnumber1 = elements.getCardnumber();
            cardnumber1.sendKeys(cardnumber);
            logger.info("Entered the cardnumber");
        } catch (Exception e) {
            logger.error("Failed to enter the cardnumber: " + e.getMessage());
            throw new RuntimeException("Unable to enter the card number: ", e);
        }
        return this;
    }
    public UserPage enterpin(String pin) {
        try {
            WebElement pin1 = elements.getPin();
            pin1.sendKeys(pin);
            logger.info("Entered the pin");
        } catch (Exception e) {
            logger.error("Failed to enter the pin: " + e.getMessage());
            throw new RuntimeException("Unable to enter the pin: ", e);
        }
        return this;
    }
    public UserPage clickwebusericon() {
        try {
            WebElement webusericon = elements.getWebusericon();
            webusericon.click();
            logger.info("clicked the webusericon");
        } catch (Exception e) {
            logger.error("Failed to click the webusericon: " + e.getMessage());
            throw new RuntimeException("Unable to click the webusericon: ", e);
        }
        return this;
    }
    public UserPage clicksubmitbutton() {
        try {
            WebElement submitbutton = elements.getSubmitbutton();
            submitbutton.click();
            logger.info("clicked the submitbutton");
        } catch (Exception e) {
            logger.error("Failed to click the submit button: " + e.getMessage());
            throw new RuntimeException("Unable to click the submit button: ", e);
        }
        return this;
    }

    public UserPage addnewwebuser() {
  navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 10").enterlastname("10").enteremail("demouser10@gmail.com").entermobile("9999999999")
          .clickroledropdown().Selectwebusertype().clicklocationleveldropdown().clicklocationlevelvalue();
  clicklocationdropdown().clicklocationvalue().enterpassword("Sterna@123").enterconfirmpassword("Sterna@123").clicksubmitbutton();
        return this;
    }
    public UserPage addnewkeyboxuser() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 11").enterlastname("11").enteremail("demouser11@gmail.com").entermobile("9999999998")
                .clickroledropdown().Selectkeyboxusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterdisplayname("5286").enterusercode("5286").clickcardnumbercheckbox().entercardnumber("5286").enterpin("5286").clicksubmitbutton();
        return this;
    }
    public UserPage addnewbothusertype() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 12").enterlastname("12").enteremail("demouser12@gmail.com").entermobile("9999999997")
                .clickroledropdown().Selectbothusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterdisplayname("2025").enterusercode("2025").clickcardnumbercheckbox().entercardnumber("2025").enterpin("2025").clickwebusericon().enterpassword("Sterna@123").enterconfirmpassword("Sterna@123").clicksubmitbutton();
        return this;
    }
    public UserPage clickmanagebutton() {
        try {
            WebElement managebutton = elements.getManagebutton();
            managebutton.click();
            logger.info("clicked the manage button");
        } catch (Exception e) {
            logger.error("Failed to click the manage button: " + e.getMessage());
            throw new RuntimeException("Unable to click the manage button: ", e);
        }
        return this;
    }
    public UserPage clickeditbutton() {
        try {
            WebElement editbuttont = elements.getEditbutton();
            editbuttont.click();
            logger.info("clicked the edit button");
        } catch (Exception e) {
            logger.error("Failed to click the edit button: " + e.getMessage());
            throw new RuntimeException("Unable to click the edit button: ", e);
        }
        return this;
    }
    public UserPage clearfirstname() {
        try {
            WebElement fname = elements.getFirstname();
            fname.sendKeys(Keys.CONTROL+"a");
            fname.sendKeys(Keys.DELETE);
            logger.info("cleared the first name in edit screen");
        } catch (Exception e) {
            logger.error("Failed to clear the first name: " + e.getMessage());
            throw new RuntimeException("Unable to clear the first name: ", e);
        }
        return this;
    }
    public UserPage clickdeletebutton() {
        try {
            WebElement Deletebutton = elements.getDeletebutton();
            Deletebutton.click();
            logger.info("clicked the Delete button");
        } catch (Exception e) {
            logger.error("Failed to click the Delete button: " + e.getMessage());
            throw new RuntimeException("Unable to click the Delete button: ", e);
        }
        return this;
    }
    public UserPage clickdeleteconfirmbutton() {
        try {
            WebElement Deleteconfirmbutton = elements.getDeleteconfirmbutton();
            Deleteconfirmbutton.click();
            logger.info("clicked the Delete confirm button");
        } catch (Exception e) {
            logger.error("Failed to click the Delete confirm button: " + e.getMessage());
            throw new RuntimeException("Unable to click the Delete confirm button: ", e);
        }
        return this;
    }
    public UserPage clickdeletecancelbutton() {
        try {
            WebElement Deletecancelbutton = elements.getDeletecancelbutton();
            Deletecancelbutton.click();
            logger.info("clicked the cancel Delete button");
        } catch (Exception e) {
            logger.error("Failed to click the cancel Delete button: " + e.getMessage());
            throw new RuntimeException("Unable to click the cancel Delete button: ", e);
        }
        return this;
    }
    public UserPage clickcancelbutton() {
        try {
            WebElement cancelbutton = elements.getCancelbutton();
            cancelbutton.click();
            logger.info("clicked the Cancel button");
        } catch (Exception e) {
            logger.error("Failed to click the cancel button: " + e.getMessage());
            throw new RuntimeException("Unable to click the cancel button: ", e);
        }
        return this;
    }
    public UserPage clickviewbutton() {
        try {
            WebElement viewbutton = elements.getViewbutton();
            viewbutton.click();
            logger.info("clicked the view button");
        } catch (Exception e) {
            logger.error("Failed to click the view button: " + e.getMessage());
            throw new RuntimeException("Unable to click the view button: ", e);
        }
        return this;
    }
    public boolean isRoleandLocationdisplayedinView() {
        try {
            WebElement RoleandLocationinView = elements.getRoleandLocationInView();
            return RoleandLocationinView.isDisplayed();
        } catch (Exception e) {
            logger.debug("Add user button not displayed: " + e.getMessage());
            return false;
        }
    }
    public UserPage Viewuser() {
        navigatetouserpage().clickmanagebutton().clickviewbutton();
        return this;
    }

    public UserPage edituser() {
       navigatetouserpage().clickmanagebutton().clickeditbutton().clearfirstname().enterfirstname("Edited User");
       try {
           Thread.sleep(500);
       } catch (InterruptedException e) {
       }
       clicksubmitbutton();
        return this;
    }
    public UserPage deleteuser() {
        navigatetouserpage().clickmanagebutton().clickdeletebutton().clickdeleteconfirmbutton();
        return this;
    }

    public UserPage CancelinAddUser() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 10").enterlastname("10").enteremail("demouser10@gmail.com").entermobile("9999999999")
                .clickroledropdown().Selectwebusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterpassword("Sterna@123").enterconfirmpassword("Sterna@123").clickcancelbutton();
        return this;
    }
    public UserPage CancelInEditUser() {
        navigatetouserpage().clickmanagebutton().clickeditbutton().clearfirstname().enterfirstname("Edited User");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        clickcancelbutton();
        return this;
    }

    public UserPage Canceldeleteuser() {
        navigatetouserpage().clickmanagebutton().clickdeletebutton().clickdeletecancelbutton();
        return this;
    }
    public int getUserTableRowCount() {
        try {
            return elements.getUserTableRows().size();
        } catch (Exception e) {
            logger.debug("Unable to get role table rows: " + e.getMessage());
        }
        return 0;
    }
    public UserPage clickSearchIcon() {
        try {
            WebElement searchicon = elements.getSearchicon();
            searchicon.click();
            logger.info("clicked the search icon");
        } catch (Exception e) {
            logger.error("Failed to click the search icon: " + e.getMessage());
            throw new RuntimeException("Unable to click the search icon: ", e);
        }
        return this;
    }
    public UserPage entersearchvalue(String searchvalue) {
        try {
            WebElement search = elements.getSearch();
            search.sendKeys(searchvalue);
            logger.info("Entered a value in search box");
        } catch (Exception e) {
            logger.error("Failed to enter the value in search box: " + e.getMessage());
            throw new RuntimeException("Unable to enter the value in search box: ", e);
        }
        return this;
    }
    public UserPage SearchaValidValue() {
        navigatetouserpage().clickSearchIcon().entersearchvalue("Demo User");
        return this;
    }
    public UserPage SearchaInvalidValue() {
     navigatetouserpage().clickSearchIcon().entersearchvalue("Rahul");
        return this;
    }
    public boolean iserrormsgofemptyfieldsdisplayed() {
        try {
            WebElement Errormsgofemptyusername = elements.getErrormsgofemptyusername();
            return Errormsgofemptyusername.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message of empty user name field is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean iserrormsgofleadingandspacesdisplayed() {
        try {
            WebElement Errormsgofspaces = elements.getErrormsgforspaces();
            return Errormsgofspaces.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message of leading and trailing spaces is not displayed: " + e.getMessage());
            return false;
        }
    }
    public UserPage addnewuserwithemptyfields() {
        navigatetouserpage().clickadduserbutton().clicksubmitbutton();
        return this;
    }

    public UserPage addnewuserwithspaces() {
        navigatetouserpage().clickadduserbutton().enterfirstname("  Demo user  ").clicksubmitbutton();
        return this;
    }
    public UserPage addduplicateuser() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 10").enterlastname("10").enteremail("demouser10@gmail.com").entermobile("8596321020")
                .clickroledropdown().Selectwebusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterpassword("Sterna@123").enterconfirmpassword("Sterna@123").clicksubmitbutton();
        return this;
    }
    public boolean isUserTableRowsdisplayed() {
            try {
                List<WebElement> Userrows = elements.getUserTableRows();
                return !Userrows.isEmpty() && Userrows.get(0).isDisplayed();
            } catch (Exception e) {
                logger.debug("Error while checking table rows: " + e.getMessage());
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

    public boolean iserrormsgofusingspecialcharactersdisplayed() {
        try {
            WebElement ErrormsgofUsingspecialcharacters = elements.getErrormsgofusingspecialcharacters();
            return ErrormsgofUsingspecialcharacters.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message for using special chaqracters is not displayed: " + e.getMessage());
            return false;
        }
    }

    public UserPage adduserwithspecialcharacters() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user #!123").enterlastname("10").enteremail("demouser10@gmail.com").entermobile("8596321020")
                .clickroledropdown().Selectwebusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterpassword("Sterna@123").enterconfirmpassword("Sterna@123").clicksubmitbutton();
        return this;
    }
    public boolean iswebusersectiondisplayed() {
        try {
            WebElement Webusersection = elements.getWebusericon();
            return Webusersection.isDisplayed();
        } catch (Exception e) {
            logger.debug("Web user section is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean iskeysafeusersectiondisplayed() {
        try {
            WebElement keysafeusericon = elements.getKeysafeusericon();
            return keysafeusericon.isDisplayed();
        } catch (Exception e) {
            logger.debug("Key safe user section is not displayed: " + e.getMessage());
            return false;
        }
    }
    public UserPage selectthekeyboxuserrole() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 11").enterlastname("11").enteremail("demouser11@gmail.com").entermobile("9999999998")
                .clickroledropdown().Selectkeyboxusertype();
        return this;
    }
    public UserPage selectthewebuserrole() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 11").enterlastname("11").enteremail("demouser11@gmail.com").entermobile("9999999998")
                .clickroledropdown().Selectwebusertype();
        return this;
    }
    public UserPage selectthebothuserrole() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 11").enterlastname("11").enteremail("demouser11@gmail.com").entermobile("9999999998")
                .clickroledropdown().Selectbothusertype();
        return this;
    }
    public boolean isErrormsgofinvalidusercodedisplayed() {
        try {
            WebElement errormsgofinvalidusercode = elements.getErrormsgofinvalidusercode();
            return errormsgofinvalidusercode.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error Message of invalid user code not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isErrormsgofInvalidpindisplayed() {
        try {
            WebElement errormsgofinvalidpin = elements.getErrormsgofinvalidpin();
            return errormsgofinvalidpin.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message of invalid pin is not displayed: " + e.getMessage());
            return false;
        }
    }
    public UserPage addkeyboxuserrolewithinvalidusercode() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 11").enterlastname("11").enteremail("demouser11@gmail.com").entermobile("9999999998")
                .clickroledropdown().Selectkeyboxusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterdisplayname("5286").enterusercode("12").clickcardnumbercheckbox().entercardnumber("5286").enterpin("5286").clicksubmitbutton();
        return this;
    }
    public UserPage addkeyboxuserrolewithinvalidpin() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 11").enterlastname("11").enteremail("demouser11@gmail.com").entermobile("9999999998")
                .clickroledropdown().Selectkeyboxusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterdisplayname("5286").enterusercode("12").clickcardnumbercheckbox().entercardnumber("5286").enterpin("52").clicksubmitbutton();
        return this;
    }

    public boolean isAddUserLabelDisplayed() {
        try {
            WebElement Adduserlabel = elements.getAdduserlabel();
            return  Adduserlabel.isDisplayed();
        } catch (Exception e) {
            logger.debug("Add User label is not displayed: " + e.getMessage());
            return false;
        }
    }

    public UserPage clicklastpagebutton() {
        try {
            WebElement lastpagebutton = elements.getLastpagebutton();
            lastpagebutton.click();
            logger.info("clicked the lastpage button");
        } catch (Exception e) {
            logger.error("Failed to click the lastpage button: " + e.getMessage());
            throw new RuntimeException("Unable to click the lastpage button: ", e);
        }
        return this;
    }

    public String TotalUserCount() throws InterruptedException {
        TestLogger.info("Getting total activity count");
        navigatetouserpage();
        commonUtil.clickLastPageButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        String actualcount = commonUtil.getLastRowFirstColumnValue();
        return actualcount;

    }
    public boolean isErrormsgofpasswordmismatchdisplayed() {
        try {
            WebElement Errormsofpasswordmismatch = elements.getErrormessageofpasswordmismatch();
            return  Errormsofpasswordmismatch.isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message of password mismatch is not displayed: " + e.getMessage());
            return false;
        }
    }
    public UserPage addnewwebuserwithpasswordmismatch() {
        navigatetouserpage().clickadduserbutton().enterfirstname("Demo user 10").enterlastname("10").enteremail("demouser10@gmail.com").entermobile("9999999999")
                .clickroledropdown().Selectwebusertype().clicklocationleveldropdown().clicklocationlevelvalue();
        clicklocationdropdown().clicklocationvalue().enterpassword("Sterna@123").enterconfirmpassword("Sterna@234").clicksubmitbutton();
        return this;
    }
/*
    public UserPage clicklastpagebutton() {
        try {
            WebElement lastpagebutton = elements.getLastpagebutton();
            lastpagebutton.click();
            logger.info("clicked the lastpage button");
        } catch (Exception e) {
            logger.error("Failed to click the lastpage button: " + e.getMessage());
            throw new RuntimeException("Unable to click the lastpage button: ", e);
        }
        return this;
    }*/



   private int countUsersByType(String userType) {
        TestLogger.info("Counting users of type: " + userType);
        navigatetouserpage();
        int count = 0;
        boolean hasNextPage = true;
        while (hasNextPage) {
            waitHelper.waitForElementVisible(By.xpath("//table/tbody/tr"));

            List<WebElement> rows = elements.getUserTableRows();
            if (rows.isEmpty()) {
                logger.info("No users found on the current page.");
                break;
            }

            for (WebElement row : rows) {
                try {
                    // This XPath finds the cell and then specifically looks for the span with the user type text
                    List<WebElement> userTypeSpans = row.findElements(By.xpath(".//td[6]//span[text()='" + userType + "']"));
                    if (!userTypeSpans.isEmpty()) {
                        logger.info(userTypeSpans.get(0).getText());
                        count++;
                    }
                } catch (Exception e) {
                    logger.info("Error processing a row: " + e.getMessage());
                }
            }

            try {

                WebElement nextButton = driver.findElement(By.xpath("//a[@aria-label='Go to next page']"));
                boolean enabled = nextButton.isEnabled() && nextButton.getCssValue("pointer-events").equals("auto");
                //         WebElement nextButton = driver.findElement(By.xpath("//a[@aria-label='Go to next page']"));
                if (enabled==true) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
                    Thread.sleep(200); // Brief pause before JS click
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
                    logger.info("Clicked the Next button");
                    Thread.sleep(300);
                    hasNextPage =true;// CRITICAL: Wait for the next page's content to load
                } else {
                    hasNextPage =false;
                    break; // Exit loop if Next button is disabled
                }
            } catch (NoSuchElementException e) {
                break; // Exit loop if Next button is not found
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        TestLogger.success("Total count for user type '" + userType + "' is: " + count);
        return count;
    }


    public String getWebUsersCount() {
        TestLogger.info("Getting total web users count from user page.");
        return String.valueOf(countUsersByType("Web User"));
    }

    public String getKeySafeUsersCount() {
        TestLogger.info("Getting total key safe users count from user page.");
        return String.valueOf(countUsersByType("Key Safe User"));
    }
    public String totalresults()  {
        TestLogger.info("Getting total user count");
        navigatetouserpage();
        String text = driver.findElement(By.xpath("//div[contains(text(), 'Showing From')]")).getText();
        String value = text.split(" ")[6];
        return value;
    }
    }
