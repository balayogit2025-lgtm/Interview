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
    public void setupUserTest() {

        navigateToApp();
        loginPage = new LoginPage(getDriver());
        userPage = new UserPage(getDriver());
        loginPage.login("admin@gmail.com","Sterna@123");
        getExtentTest().log(Status.INFO, "Navigated to Login Page");
    }

    @Test(priority = 1, description = "Navigate to User page")
    public void NavigateToUserPager() {
        getExtentTest().log(Status.INFO, "Test: Navigate to User page");
        userPage.navigatetouserpage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
        getExtentTest().log(Status.PASS, "Successfully Navigated to User page");
    }

    @Test(priority = 2, description = "Create a new User of web user type")
    public void CreateaNewwebUser() {
        getExtentTest().log(Status.INFO, "Test: Create a new User of web user type");
        userPage.addnewwebuser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
        getExtentTest().log(Status.PASS, "Successfully Created a new User of web user type");
    }
    @Test(priority = 3, description = "Create a new User of key box user type")
    public void CreateaNewkeyboxUser() {
        getExtentTest().log(Status.INFO, "Test: Create a new User of key box user type");
        userPage.addnewkeyboxuser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
        getExtentTest().log(Status.PASS, "Successfully Created a new User of key box type");
    }
    @Test(priority = 4, description = "Create a new User of both key box & web user type")
    public void CreateaUserofbothusertype() {
        getExtentTest().log(Status.INFO, "Test: Create a new User of both key box & web user type");
        userPage.addnewbothusertype();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
        getExtentTest().log(Status.PASS, "Successfully Create a new User of bothy web & key box user type");
    }
    @Test(priority = 5, description = "Edit existing user")
    public void EditUser() {
        getExtentTest().log(Status.INFO, "Test: Edit existing user");
        userPage.edituser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
        getExtentTest().log(Status.PASS, "Successfully edited the details of the user");
    }
    @Test(priority = 6, description = "Delete User")
    public void DeleteUser() {
        getExtentTest().log(Status.INFO, "Test: Delete User");
        userPage.deleteuser();
        boolean Adduserbuttonenabledanddisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(Adduserbuttonenabledanddisplayed, "Add User button is not displayed and enabled");
        getExtentTest().log(Status.PASS, "Successfully deleted the user");
    }
    @Test(priority = 7, description = "Verify cancel button works on Add User")
    public void cancelButtonInAddUser() {
        getExtentTest().log(Status.INFO, "Test: Verify cancel button works on Add User");
        userPage.CancelinAddUser();
        // After cancel, should be back on role list page
        boolean adduserButtonDisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(adduserButtonDisplayed, "Add Role button is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the cancel button functionality in add user");
    }
    @Test(priority = 8, description = "Verify cancel button works on Edit User")
    public void cancelButtonInEditUser() {
        getExtentTest().log(Status.INFO, "Test: Verify cancel button works on Edit User");
        userPage.CancelInEditUser();
        // After cancel, should be back on role list page
        boolean adduserButtonDisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(adduserButtonDisplayed, "Add Role button is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the cancel button functionality in edit user");
    }
    @Test(priority = 9, description = "Verify cancel button in Delete")
    public void cancelButtonInDeleteUser() {
        getExtentTest().log(Status.INFO, "Test: Verify cancel button in Delete");
        userPage.Canceldeleteuser();
        // After cancel, should be back on role list page
        boolean adduserButtonDisplayed = userPage.isadduserbuttonanabledanddisplayed();
        Assert.assertTrue(adduserButtonDisplayed, "Add Role button is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the cancel button functionality in delete user");
    }
    @Test(priority = 10, description = "View User Details")
    public void ViewUserDetails() {
        getExtentTest().log(Status.INFO, "Test: View User Details");
        userPage.Viewuser();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean  Viewscreendisplayed= userPage.isRoleandLocationdisplayedinView();
        Assert.assertTrue(Viewscreendisplayed, "View User details are not displayed");
        getExtentTest().log(Status.PASS, "Successfully viewed the user details");
    }
    @Test(priority = 11, description = "Verify search functionality with valid user name")
    public void searchUserWithValidName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with valid user name");
        userPage.SearchaValidValue();
        int rowCount = userPage.getUserTableRowCount();
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid user name");
    }

    @Test(priority = 12, description = "Verify search functionality with non-existent user name")
    public void searchUserWithNonExistentName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with non-existent user name");
        userPage.SearchaInvalidValue();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        int rowCount = userPage.getUserTableRowCount();
        Assert.assertTrue(rowCount == 0, "it should return an non empty table");
        getExtentTest().log(Status.PASS, "Verfied the search results with non-existent user name");
    }
    @Test(priority = 13, description = "Verify user creation when all the mandatory fields are empty")
    public void adduserwithemptyfields() {
        getExtentTest().log(Status.INFO, "Test: Verify user creation when all the mandatory fields are empty");
        userPage.addnewuserwithemptyfields();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofemptyfieldsdisplayed = userPage.iserrormsgofemptyfieldsdisplayed();
        Assert.assertTrue(iserrormsgofemptyfieldsdisplayed, "Error message for all mandatory fields is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the mandatory fields are empty");
    }
    @Test(priority = 14, description = "Verify user creation with leading/trailing spaces in user name field")
    public void adduserWithLeadingTrailingSpaces() {
        getExtentTest().log(Status.INFO, "Test: Verify user creation with leading/trailing spaces in user name field");
        userPage.addnewuserwithspaces();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofspacesdisplayed = userPage.iserrormsgofleadingandspacesdisplayed();
        Assert.assertTrue(iserrormsgofspacesdisplayed, "Error message for user name should not be displayed");
        getExtentTest().log(Status.PASS, "Verified the error message for entering the spaces in the user name");
    }
  @Test(priority = 15, description = "Verify duplicate user creation is prevented")
    public void addDuplicateuser() {
      getExtentTest().log(Status.INFO, "Test: Verify duplicate user creation is prevented");
        userPage.addduplicateuser();
        Assert.assertTrue(userPage.isAddUserLabelDisplayed(),
                "Should show error or prevent duplicate role creation");
      getExtentTest().log(Status.PASS, "Verified the duplicate user creation is prevented");
    }

    @Test(priority = 16, description = "Verify user table is displayed on user page")
    public void verifyUserTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verify user table is displayed on user page");
        userPage.navigatetouserpage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean isTableDisplayed = userPage.isUserTableRowsdisplayed();
        Assert.assertTrue(isTableDisplayed, "User table is not displayed");
        getExtentTest().log(Status.PASS, "Verified the user table is displayed");
    }

    @Test(priority = 17, description = "Verify the column header names in User list screen")
    public void verifycolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in User list screen");
        userPage.navigatetouserpage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> expectedHeaders = Arrays.asList("S.NO", "NAME", "LOCATION", "EMAIL","MOBILE","USER TYPE","CREATED BY","UPDATED BY","ACTION");
        List<String> actualHeaders = userPage.getHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }
     @Test(priority = 18, description = "Verify user creation with special characters in firstname")
    public void addUserWithSpecialCharacters() {
         getExtentTest().log(Status.INFO, "Test: Verify user creation with special characters in firstname");
        userPage.adduserwithspecialcharacters();
        boolean errormsgofusingspecialcharacters = userPage.iserrormsgofusingspecialcharactersdisplayed();
        Assert.assertTrue(errormsgofusingspecialcharacters, "No validation message");
         getExtentTest().log(Status.PASS, "Verified the error message of using special characters in firstname");

    }

    @Test(priority = 19, description = "Verify the presence of Webuser section after selecting the role as Only Web user")
    public void verifyThePresenceOfWebuserSection() {
        getExtentTest().log(Status.INFO, "Test: Verify the presence of Webuser section after selecting the role as Only Web user");
        userPage.selectthewebuserrole();
        boolean  webusersectiondisplayed= userPage.iswebusersectiondisplayed();
        Assert.assertTrue(webusersectiondisplayed, "Webuser section is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the presence of web user section");

    }
    @Test(priority = 20, description = "Verify the presence of Keysafeuser section after selecting the role as Only  Keysafeuser")
    public void VerifyThePresenceOfKeysafeuserSection() {
        getExtentTest().log(Status.INFO, "Test: Verify the presence of Keysafeuser section after selecting the role as Only Keysafeuser");
        userPage.selectthekeyboxuserrole();
        boolean  keysafeusersectiondisplayed= userPage.iskeysafeusersectiondisplayed();
        Assert.assertTrue(keysafeusersectiondisplayed, "Keysafeuser section is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the presence of keysafe user section");
    }
    @Test(priority = 21, description = "Verify the presence of both section after selecting the role as both")
    public void VerifyThePresenceOfBothUsersSection() {
        getExtentTest().log(Status.INFO, "Test: Verify the presence of Webuser section after selecting the role as Only Web user");
        userPage.selectthebothuserrole();
        boolean bothsectionsdisplayed = userPage.iswebusersectiondisplayed() && userPage.iskeysafeusersectiondisplayed();
        Assert.assertTrue(bothsectionsdisplayed, "Both Keysafe and Web user section is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the presence of web user & key box section");
    }
    @Test(priority = 22, description = "Verify the Error message of invalid user code")
    public void VerifyTheErrorMessageofInvalidUserCode() {
        getExtentTest().log(Status.INFO, "Test: Verify the Error message of invalid user code");
        userPage.addkeyboxuserrolewithinvalidusercode();
        boolean errormsgofinvalidusercode = userPage.isErrormsgofinvalidusercodedisplayed();
        Assert.assertTrue(errormsgofinvalidusercode, "Error message of invalid user code is not displayed");
        getExtentTest().log(Status.PASS, "Verified the error message of invalid user code");
    }
    @Test(priority = 23, description = "Verify the Error message of invalid pin")
    public void VerifyTheErrorMessageofInvalidPin() {
        getExtentTest().log(Status.INFO, "Test: Verify the Error message of invalid pin");
        userPage.addkeyboxuserrolewithinvalidpin();
        boolean errormsgofinvalidpin = userPage.isErrormsgofInvalidpindisplayed();
        Assert.assertTrue(errormsgofinvalidpin, "Error message of invalid pin is not displayed");
        getExtentTest().log(Status.PASS, "Verified the error message of invalid pin");
    }
    @Test(priority = 24, description = "Verify the Total users count")
    public void verifyTotalUsersCount() throws InterruptedException {
        logger.info("Starting verifyUsersCount");
        getExtentTest().log(Status.INFO, "Test: Verify the Total users count");
        getExtentTest().log(Status.INFO, "Verifying the total users count");
        String Expectedcount = userPage.totalresults();
        String Actualcount = userPage.TotalUserCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual user count is matched");
        TestLogger.success("User count verification passed");
        logger.info("User count verification PASSED" );
    }
    @Test(priority = 25, description = "Verify password and confirm password mismatch error")
    public void addUserWithPasswordMismatch() {
        getExtentTest().log(Status.INFO, "Test: Verify password and confirm password mismatch error");
        userPage.addnewwebuserwithpasswordmismatch();
        boolean isErrorDisplayed = userPage.isErrormsgofpasswordmismatchdisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message for password mismatch is not displayed");
        getExtentTest().log(Status.PASS, "Verified the error message of password mismatch");
    }

}
