package TestRun;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.RolePage;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static Base.BaseTest.getDriver;

public class RoleTest extends BaseTest {

    private LoginPage loginPage;
    private RolePage rolePage;

    @BeforeMethod
    public void setupRoleTest() {

        navigateToApp();
        loginPage = new LoginPage(getDriver());
        rolePage = new RolePage(getDriver());
        loginPage.login("admin@gmail.com","Sterna@123");
        getExtentTest().log(Status.INFO, "Navigated to Login Page");
    }

    @Test(priority = 1, description = "Navigate to Role page")
    public void NavigateToRolePager() {
        getExtentTest().log(Status.INFO, "Test: Navigate to Role page");
        rolePage.navigatetorolepage();
        boolean Addrolebuttondisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(Addrolebuttondisplayed, "Add Role button should be displayed and enabled");
        getExtentTest().log(Status.PASS, "Successfully Navigate to Role page");
    }
    @Test(priority = 2, description = "Create a new Key box user Role")
    public void addNewKeyBoxRole() {
        getExtentTest().log(Status.INFO, "Test: Create a new Key box user Role");
        rolePage.Addnewkeyboxusertype();
        Assert.assertTrue(rolePage.isAddRoleButtonDisplayedAndEnabled(), "Should return to role page after creating a key box user");
        getExtentTest().log(Status.PASS, "Successfully created a new key box user");

    }
    @Test(priority = 3, description = "Create a new web user Role")
    public void addNewWebUserRole() {
        getExtentTest().log(Status.INFO, "Test: Create a new web user Role");
        rolePage.Addnewwebusertype();
        Assert.assertTrue(rolePage.isAddRoleButtonDisplayedAndEnabled(), "Should return to role page after creating a web user");
        getExtentTest().log(Status.PASS, "Successfully created a new web user role");
    }
    @Test(priority = 4, description = "Create a new Role with both user type")
    public void addNewRolewithybothusertype() {
        getExtentTest().log(Status.INFO, "Test: Create a new Role with both user type");
        rolePage.Addnewrolewithbothusertype();
        Assert.assertTrue(rolePage.isAddRoleButtonDisplayedAndEnabled(), "Should return to role page after creating a both type user");
        getExtentTest().log(Status.PASS, "Successfully created a role with both user type");
    }
    @Test(priority = 5, description = "Edit an existing Role")
    public void editRole() {
        getExtentTest().log(Status.INFO, "Test: Edit an existing Role");
        rolePage.editAnRole();
        Assert.assertTrue(rolePage.isAddRoleButtonDisplayedAndEnabled(), "Should return to role page after editing a detail of an user");
        getExtentTest().log(Status.PASS, "Edited the details of the role successfully");
    }
    @Test(priority = 6, description = "Delete a Role")
    public void deleteRole() {
        getExtentTest().log(Status.INFO, "Test: Delete a Role");
        rolePage.deleteAnRole();
        Assert.assertTrue(rolePage.isAddRoleButtonDisplayedAndEnabled(), "Should return to role page after deleting a user");
        getExtentTest().log(Status.PASS, "Deleted the role successfully");
    }

    @Test(priority = 7, description = "Verify role creation fails with empty role name")
    public void addRoleWithEmptyName() {
        getExtentTest().log(Status.INFO, "Test: Verify role creation fails with empty role name");
        rolePage.addRoleWithEmptyName();
        Assert.assertTrue(rolePage.iserrormsgofemptyrolenameDisplayed(),
                "Should show validation for role name is being empty");
        getExtentTest().log(Status.PASS, "Verified the error message when the role name is empty");
    }

    @Test(priority = 8, description = "Verify role creation fails without selecting user type")
    public void addRoleWithoutUserType() {
        getExtentTest().log(Status.INFO, "Test: Verify role creation fails without selecting user type");
        rolePage.addRoleWithoutUserType();
        Assert.assertTrue(rolePage.iserrormsgofemptyusertypedisplayed(),
                "Should show validation for user type is being empty");
        getExtentTest().log(Status.PASS, "Verrified the error message when the user type is empty");
    }

    @Test(priority = 9, description = "Verify role creation fails without selecting Pages")
    public void addRoleWithoutSelectingPages() {
        getExtentTest().log(Status.INFO, "Test: Verify role creation fails without selecting Pages");
        rolePage.navigatetorolepage().clickaddrole().enterrolename("TESTER").clickwebusertype().clicksubmitbutton();
        Assert.assertTrue(rolePage.iserrormsgofnotselectingpagedisplayed(),
                "Should show validation for user type is being empty");
        getExtentTest().log(Status.PASS, "Verified the user page when the pages is not selected");
    }

    @Test(priority = 10, description = "Verify cancel button works on Add Role form")
    public void cancelAddRoleForm() {
        getExtentTest().log(Status.INFO, "Test: Verify cancel button works on Add Role form");
        rolePage.cancelinAddRole();
        boolean addRoleButtonDisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(addRoleButtonDisplayed, "Add Role button should be displayed after canceling");
        getExtentTest().log(Status.PASS, "verified the cancel button functionality in add role");
    }

    @Test(priority = 11, description = "Verify cancel button works on Delete confirmation")
    public void cancelDeleteRole() {
        getExtentTest().log(Status.INFO, "Test: Verify cancel button works on Delete confirmation");
        rolePage.cancelDeleteRole();
        boolean addRoleButtonDisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(addRoleButtonDisplayed, "Should return to role list after canceling delete");
        getExtentTest().log(Status.PASS, "Successfully Verified cancel button functionality on Delete confirmation");
    }


    @Test(priority = 12, description = "Verify search functionality with valid role name")
    public void searchRoleWithValidName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with valid role name");
        rolePage.searchavalidvalue();
        int rowCount = rolePage.getRoleTableRowCount();
        Assert.assertTrue(rowCount > 0, "Search should return results or empty table");
        getExtentTest().log(Status.PASS, "Successfully Verified the search functionality with valid role name");
    }

    @Test(priority = 13, description = "Verify search functionality with non-existent role name")
    public void searchRoleWithNonExistentName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with non-existent role name");
         try {
             Thread.sleep(3000);
         } catch (InterruptedException e) {
         }
        int rowCount = rolePage.getRoleTableRowCount();
        Assert.assertTrue(rowCount == 0, "Search with non-existent name should handle gracefully");
        getExtentTest().log(Status.PASS, "Successfully Verified the search functionality with non-existent role name");
    }

    @Test(priority = 14, description = "Verify role creation with special characters in name")
    public void addRoleWithSpecialCharacters() {
        getExtentTest().log(Status.INFO, "Test: Verify role creation with special characters in name");
        rolePage.addRoleWithSpecialCharacters();
        boolean errormsgofusingspecialcharacters = rolePage.iserrormsgofspecialcharactersinrolenamedisplayed();
        Assert.assertTrue(errormsgofusingspecialcharacters, "Validation is displayed for entering special characters in role name");
        getExtentTest().log(Status.PASS, "Verified the error message when the role name contains special characters");
    }

    @Test(priority = 15, description = "Verify role creation with very long role name")
    public void addRoleWithLongName() {
        getExtentTest().log(Status.INFO, "Test: Verify role creation with very long role name");
        rolePage.addRoleWithLongName();
        Assert.assertTrue(rolePage.iserrormsgoflongrolenamedisplayed(),
                "Should show error msg for entering the long name in role name field");
        getExtentTest().log(Status.PASS, "Verified the error message when the role name is long");
    }

    @Test(priority = 16, description = "Verify duplicate role creation is prevented")
    public void addDuplicateRole() {
        getExtentTest().log(Status.INFO, "Test: Verify duplicate role creation is prevented");
        rolePage.addDuplicateRole();
        Assert.assertTrue(rolePage.isaddrolelabeldisplayed(),
                "Should show error msg and stays on the add role page");
        getExtentTest().log(Status.PASS, "verified the duplicate role creation is prevented");
    }


    @Test(priority = 17, description = "Verify role table is displayed on Role page")
    public void verifyRoleTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verify role table is displayed on Role page");
        rolePage.navigatetorolepage();
        boolean isTableDisplayed = rolePage.isroletableDisplayed();
        Assert.assertTrue(isTableDisplayed, "Role table should be displayed on the Role page");
        getExtentTest().log(Status.PASS, "Verified the role table is displayed");
    }

    @Test(priority = 18, description = "Verify Add Role form elements are displayed")
    public void verifyAddRoleFormElements() {
        getExtentTest().log(Status.INFO, "Test: Verify Add Role form elements are displayed");
        rolePage.navigatetorolepage().clickaddrole();
        boolean isSubmitDisplayed = rolePage.isSubmitButtonDisplayed();
        Assert.assertTrue(isSubmitDisplayed, "Submit button should be displayed on Add Role form");
        getExtentTest().log(Status.PASS, "Verified the Add Role form elements are displayed");
    }

    @Test(priority = 19, description = "Verify editing role with empty name is prevented")
    public void editRoleWithEmptyName() {
        getExtentTest().log(Status.INFO, "Test: Verify editing role with empty name is prevented");
        rolePage.navigatetorolepage().clickmanagebutton().clickeditbutton().clearrolename();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        rolePage.clicksubmitbutton();
        // Should show validation error or prevent submission
        boolean displayerrorofemptyrolenameineditscreen =  rolePage.iserrormsgofemptyrolenameDisplayed();
        Assert.assertTrue(displayerrorofemptyrolenameineditscreen,
                "Should prevent saving role with empty name");
        getExtentTest().log(Status.PASS, "Verfied the error message when the role name is empty in edit screen");
    }

    @Test(priority = 20, description = "Verify cancel button works on Edit Role form")
    public void cancelEditRoleForm() {
        getExtentTest().log(Status.INFO, "Test: Verify cancel button works on Edit Role form");
        rolePage.navigatetorolepage().clickmanagebutton().clickeditbutton();
        rolePage.clickcancelbutton();
        boolean addRoleButtonDisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(addRoleButtonDisplayed, "Add Role button should be displayed after canceling edit");
        getExtentTest().log(Status.PASS, "Verified the cancel button functionality in edit role");
    }

    @Test(priority = 21, description = "Verify role creation with numeric-only name")
    public void addRoleWithNumericName() {
        getExtentTest().log(Status.INFO, "Test: Verify role creation with numeric-only name");
        rolePage.addRoleWithonlynumeric();
        Assert.assertTrue(rolePage.isAddRoleButtonDisplayedAndEnabled(), "Should return to role page after creating a user with numeric only");
        getExtentTest().log(Status.PASS, "Verified the role creation with numeric only");
    }

    @Test(priority = 22, description = "Verify role creation with leading/trailing spaces")
    public void addRoleWithLeadingTrailingSpaces() {
        getExtentTest().log(Status.INFO, "Test: Verify role creation with leading/trailing spaces");
        rolePage.addRoleWithWhitespaceOnlyName();
        boolean iserrormsgofspacesdisplayed = rolePage.iserrormsgofspacesdisplayed();
        Assert.assertTrue(iserrormsgofspacesdisplayed, "Error message for entering spaces in start & end in role name should be displayed");
        getExtentTest().log(Status.PASS, "Verified the error message when the role name contains leading/trailing spaces");
    }
    @Test(priority = 23, description = "Verify the column header names in list screen")
    public void verifycolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in list screen");
        rolePage.navigatetorolepage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> expectedHeaders = Arrays.asList("S.NO", "NAME", "CREATED BY", "UPDATED BY","ACTION");
        List<String> actualHeaders = rolePage.getHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "Verified the column header names");
    }
    @Test(priority = 24, description = "Verify the Total role count")
    public void verifyTotalRoleCount() throws InterruptedException {
        getExtentTest().log(Status.INFO, "Test: Verify the Total role count");
        logger.info("Starting verifyRoleCount");
        getExtentTest().log(Status.INFO, "Verifying the total role count");
        String Expectedcount = "26";
        String Actualcount = rolePage.totalRoleCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual role count is matched");
        TestLogger.success("role count verification passed");
        logger.info("role count verification PASSED" );
    }

    @Test(priority = 25, description = "Verify the Presence of page checkbox under Pages section")
    public void verifyPagenames() {
        getExtentTest().log(Status.INFO, "Test: Verify the Presence of page checkbox under Pages section");
        rolePage.navigatetorolepage().clickaddrole().clickwebusertype();

        List<String> expected = Arrays.asList("All", "Dashboard", "Levels", "Role","User","KeySafe","Activity","Reports","Settings");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        List<String> actual = rolePage.getPagescheckboxesTexts();
        Assert.assertEquals(actual,expected,"The Page names does not matches");
        getExtentTest().log(Status.PASS, "Verified the presence of the page names");
    }

}
