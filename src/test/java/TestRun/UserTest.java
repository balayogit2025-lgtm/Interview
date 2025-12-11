package TestRun;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.UserPage;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class UserTest extends BaseTest {

    private LoginPage loginPage;
    private UserPage userPage;

    @BeforeMethod
    public void setupRoleTest() {

        navigateToApp();
        loginPage = new LoginPage(getDriver());
        userPage = new UserPage(getDriver());
        loginPage.login("admin@gmail.com","Sterna@123");
        //getExtentTest().log(Status.INFO, "Navigated to Login Page");
    }

    @Test(priority = 1, description = "Navigate to User page")
    public void NavigateToUserPager() {

        userPage.navigatetouserpage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
    }

    @Test(priority = 2, description = "Create a new User of web user type")
    public void CreateaNewwebUser() {
        userPage.addnewwebuser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
    }
    @Test(priority = 3, description = "Create a new User of key box user type")
    public void CreateaNewkeyboxUser() {
        userPage.addnewkeyboxuser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
    }
    @Test(priority = 4, description = "Create a new User of both key box & web user type")
    public void CreateaUserofbothusertype() {
        userPage.addnewbothusertype();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
    }
    @Test(priority = 5, description = "Edit existing user")
    public void EditUser() {
        userPage.edituser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
    }
    @Test(priority = 6, description = "Delete User")
    public void DeleteUser() {
        userPage.deleteuser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
    }
    @Test(priority = 7, description = "Verify cancel button works on Add User")
    public void cancelButtonInAddUser() {
        userPage.CancelinAddUser();
        // After cancel, should be back on role list page
        boolean adduserButtonDisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(adduserButtonDisplayed, "Add Role button is not displayed");
    }
    @Test(priority = 8, description = "Verify cancel button works on Edit User")
    public void cancelButtonInEditUser() {
        userPage.CancelInEditUser();
        // After cancel, should be back on role list page
        boolean adduserButtonDisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(adduserButtonDisplayed, "Add Role button is not displayed");
    }
    @Test(priority = 9, description = "Verify cancel button in Delete")
    public void cancelButtonInDeleteUser() {
        userPage.Canceldeleteuser();
        // After cancel, should be back on role list page
        boolean adduserButtonDisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(adduserButtonDisplayed, "Add Role button is not displayed");
    }
    @Test(priority = 10, description = "View User Details")
    public void ViewUserDetails() {
        userPage.Viewuser();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean  Viewscreendisplayed= userPage.isRoleandLocationdisplayedinView();
        Assert.assertTrue(Viewscreendisplayed, "View User details are not displayed");
    }
    @Test(priority = 11, description = "Verify search functionality with valid user name")
    public void searchUserWithValidName() {
        userPage.SearchaValidValue();
        int rowCount = userPage.getUserTableRowCount();
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
    }

    @Test(priority = 12, description = "Verify search functionality with non-existent user name")
    public void searchUserWithNonExistentName() {
        userPage.SearchaInvalidValue();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        int rowCount = userPage.getUserTableRowCount();
        Assert.assertTrue(rowCount == 0, "it should return an non empty table");
    }
    @Test(priority = 13, description = "Verify user creation when all the mandatory fields are empty")
    public void adduserwithemptyfields() {
        userPage.addnewuserwithemptyfields();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofemptyfieldsdisplayed = userPage.iserrormsgofemptyfieldsdisplayed();
        Assert.assertTrue(iserrormsgofemptyfieldsdisplayed, "Error message for all mandatory fields is not displayed");
    }
    @Test(priority = 14, description = "Verify user creation with leading/trailing spaces in user name field")
    public void adduserWithLeadingTrailingSpaces() {
        userPage.addnewuserwithspaces();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofspacesdisplayed = userPage.iserrormsgofleadingandspacesdisplayed();
        Assert.assertTrue(iserrormsgofspacesdisplayed, "Error message for user name should not be displayed");
    }
  @Test(priority = 15, description = "Verify duplicate user creation is prevented")
    public void addDuplicateuser() {
        userPage.addduplicateuser();
        Assert.assertTrue(userPage.isAddUserLabelDisplayed(),
                "Should show error or prevent duplicate role creation");
    }

    @Test(priority = 16, description = "Verify user table is displayed on user page")
    public void verifyUserTableDisplayed() {
        userPage.navigatetouserpage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean isTableDisplayed = userPage.isUserTableRowsdisplayed();
        Assert.assertTrue(isTableDisplayed, "User table is not displayed");
    }

    @Test(priority = 17, description = "Verify the column header names in User list screen")
    public void verifycolumnheadernames() {
        userPage.navigatetouserpage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> expectedHeaders = Arrays.asList("S.NO", "NAME", "LOCATION", "EMAIL","MOBILE","USER TYPE","CREATED BY","UPDATED BY","ACTION");
        List<String> actualHeaders = userPage.getHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");
    }
     @Test(priority = 18, description = "Verify user creation with special characters in firstname")
    public void addUserWithSpecialCharacters() {
        userPage.adduserwithspecialcharacters();
        boolean errormsgofusingspecialcharacters = userPage.iserrormsgofusingspecialcharactersdisplayed();
        Assert.assertTrue(errormsgofusingspecialcharacters, "No validation message");

    }

    @Test(priority = 19, description = "Verify the presence of Webuser section after selecting the role as Only Web user")
    public void verifyThePresenceOfWebuserSection() {
        userPage.selectthewebuserrole();
        boolean  webusersectiondisplayed= userPage.iswebusersectiondisplayed();
        Assert.assertTrue(webusersectiondisplayed, "Webuser section is not displayed");

    }
    @Test(priority = 20, description = "Verify the presence of Keysafeuser section after selecting the role as Only  Keysafeuser")
    public void VerifyThePresenceOfKeysafeuserSection() {
        userPage.selectthekeyboxuserrole();
        boolean  keysafeusersectiondisplayed= userPage.iskeysafeusersectiondisplayed();
        Assert.assertTrue(keysafeusersectiondisplayed, "Keysafeuser section is not displayed");
    }
    @Test(priority = 21, description = "Verify the presence of Webuser section after selecting the role as Only Web user")
    public void VerifyThePresenceOfBothUsersSection() {
        userPage.selectthebothuserrole();
        boolean bothsectionsdisplayed = userPage.iswebusersectiondisplayed() && userPage.iskeysafeusersectiondisplayed();
        Assert.assertTrue(bothsectionsdisplayed, "Both Keysafe and Web user section is not displayed");
    }
    @Test(priority = 22, description = "Verify the Error message of invalid user code")
    public void VerifyTheErrorMessageofInvalidUserCode() {
        userPage.addkeyboxuserrolewithinvalidusercode();
        boolean errormsgofinvalidusercode = userPage.isErrormsgofinvalidusercodedisplayed();
        Assert.assertTrue(errormsgofinvalidusercode, "Error message of invalid user code is not displayed");
    }
    @Test(priority = 23, description = "Verify the Error message of invalid pin")
    public void VerifyTheErrorMessageofInvalidPin() {
        userPage.addkeyboxuserrolewithinvalidpin();
        boolean errormsgofinvalidpin = userPage.isErrormsgofInvalidpindisplayed();
        Assert.assertTrue(errormsgofinvalidpin, "Error message of invalid pin is not displayed");
    }
    @Test(priority = 24, description = "Verify the Total users count")
    public void verifyTotalUsersCount() throws InterruptedException {
        logger.info("Starting verifyUsersCount");
        getExtentTest().log(Status.INFO, "Verifying the total users count");
        String Expectedcount = "22";
        String Actualcount = userPage.TotalUserCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual user count is matched");
        TestLogger.success("User count verification passed");
        logger.info("User count verification PASSED" );
    }
    }
