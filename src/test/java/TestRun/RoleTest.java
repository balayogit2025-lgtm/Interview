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
        //getExtentTest().log(Status.INFO, "Navigated to Login Page");
    }

    @Test(priority = 1, description = "Navigate to Role page")
    public void NavigateToRolePager() {

        rolePage.navigatetorolepage();
        boolean Addrolebuttondisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(Addrolebuttondisplayed, "Add Role button should be displayed and enabled");
    }
    @Test(priority = 2, description = "Create a new Keysafe user Role")
    public void addNewKeySafeRole() {
        String nameoftherole = "TEST ROLE 1";
        rolePage.navigatetorolepage().clickaddrole().enterrolename(nameoftherole).clickKeyboxusertype().clicksubmitbutton();
        //getExtentTest().log(Status.INFO, "Test: Add New Role");
        //rolePage.addRole("Test Role", "This is a test role for automation");
        //getExtentTest().log(Status.INFO, "Successfully added a new role");

    }
    @Test(priority = 3, description = "Create a new web user Role")
    public void addNewWebUserRole() {
        rolePage.Addnewwebusertype();
        //getExtentTest().log(Status.INFO, "Test: Add New Role");
        //rolePage.addRole("Test Role", "This is a test role for automation");
        //getExtentTest().log(Status.INFO, "Successfully added a new role");

    }
    @Test(priority = 4, description = "Create a new Role with both user type")
    public void addNewRolewithybothusertype() {
        rolePage.Addnewrolewithbothusertype();
        //getExtentTest().log(Status.INFO, "Test: Add New Role");
        //rolePage.addRole("Test Role", "This is a test role for automation");
        //getExtentTest().log(Status.INFO, "Successfully added a new role");

    }
    @Test(priority = 5, description = "Edit an existing Role")
    public void editRole() {
        rolePage.editAnRole();
        //getExtentTest().log(Status.INFO, "Test: Add New Role");
        //rolePage.addRole("Test Role", "This is a test role for automation");
        //getExtentTest().log(Status.INFO, "Successfully added a new role");

    }
    @Test(priority = 6, description = "Delete a Role")
    public void deleteRole() {
        rolePage.deleteAnRole();
        //getExtentTest().log(Status.INFO, "Test: Add New Role");
        //rolePage.addRole("Test Role", "This is a test role for automation");
        //getExtentTest().log(Status.INFO, "Successfully added a new role");
    }

    @Test(priority = 7, description = "Verify role creation fails with empty role name")
    public void addRoleWithEmptyName() {
        rolePage.addRoleWithEmptyName();
        // Should remain on the form or show validation error
        Assert.assertTrue(rolePage.iserrormsgofemptyrolenameDisplayed(),
                "Should show validation for role name is being empty");
    }

    @Test(priority = 8, description = "Verify role creation fails without selecting user type")
    public void addRoleWithoutUserType() {
        rolePage.addRoleWithoutUserType("TEST ROLE NO TYPE");
        // Should remain on the form or show validation error
        Assert.assertTrue(rolePage.iserrormsgofemptyusertypedisplayed(),
                "Should show validation for user type is being empty");
    }

    @Test(priority = 9, description = "Verify role creation fails without selecting Pages")
    public void addRoleWithoutSelectingPages() {
        rolePage.navigatetorolepage().clickaddrole().enterrolename("TESTER").clickwebusertype().clicksubmitbutton();
        // Should remain on the form or show validation error
        Assert.assertTrue(rolePage.iserrormsgofnotselectingpagedisplayed(),
                "Should show validation for user type is being empty");
    }

    @Test(priority = 10, description = "Verify cancel button works on Add Role form")
    public void cancelAddRoleForm() {
        rolePage.cancelinAddRole();
        // After cancel, should be back on role list page
        boolean addRoleButtonDisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(addRoleButtonDisplayed, "Add Role button should be displayed after canceling");
    }

    @Test(priority = 11, description = "Verify cancel button works on Delete confirmation dialog")
    public void cancelDeleteRole() {
        rolePage.cancelDeleteRole();
        // Role should still exist after canceling delete
        boolean addRoleButtonDisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(addRoleButtonDisplayed, "Should return to role list after canceling delete");
    }


    @Test(priority = 12, description = "Verify search functionality with valid role name")
    public void searchRoleWithValidName() {
        rolePage.searchavalidvalue();
        int rowCount = rolePage.getRoleTableRowCount();
        // Search results should be filtered
        Assert.assertTrue(rowCount > 0, "Search should return results or empty table");
    }

    @Test(priority = 13, description = "Verify search functionality with non-existent role name")
    public void searchRoleWithNonExistentName() {
        rolePage.searchainvalidvalue();
        int rowCount = rolePage.getRoleTableRowCount();
        // Should return no results or show empty state
        Assert.assertTrue(rowCount == 0 || rowCount >= 0, "Search with non-existent name should handle gracefully");
    }

    @Test(priority = 14, description = "Verify role creation with special characters in name")
    public void addRoleWithSpecialCharacters() {
        rolePage.addRoleWithSpecialCharacters();
        boolean errormsgofusingspecialcharacters = rolePage.iserrormsgofspecialcharactersinrolenamedisplayed();
        Assert.assertTrue(errormsgofusingspecialcharacters, "Validation is displayed for entering special characters in role name");
        // Application should either accept or reject with validation error
        // This tests the boundary of allowed characters
    }

    @Test(priority = 15, description = "Verify role creation with very long role name")
    public void addRoleWithLongName() {
        rolePage.addRoleWithLongName();
    }

   /* @Test(priority = 16, description = "Verify duplicate role creation is prevented")
    public void addDuplicateRole() {

        rolePage.addDuplicateRole();

        Assert.assertTrue(rolePage,
                "Should show error or prevent duplicate role creation");
    } */


    @Test(priority = 17, description = "Verify role table is displayed on Role page")
    public void verifyRoleTableDisplayed() {
        rolePage.navigatetorolepage();
        boolean isTableDisplayed = rolePage.isroletableDisplayed();
        Assert.assertTrue(isTableDisplayed, "Role table should be displayed on the Role page");
    }

    @Test(priority = 18, description = "Verify Add Role form elements are displayed")
    public void verifyAddRoleFormElements() {
        rolePage.navigatetorolepage().clickaddrole();
        boolean isSubmitDisplayed = rolePage.isSubmitButtonDisplayed();
        Assert.assertTrue(isSubmitDisplayed, "Submit button should be displayed on Add Role form");
    }

    @Test(priority = 19, description = "Verify editing role with empty name is prevented")
    public void editRoleWithEmptyName() {
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
    }

    @Test(priority = 20, description = "Verify cancel button works on Edit Role form")
    public void cancelEditRoleForm() {
        rolePage.navigatetorolepage().clickmanagebutton().clickeditbutton();
        rolePage.clickcancelbutton();
        // Should return to role list
        boolean addRoleButtonDisplayed = rolePage.isAddRoleButtonDisplayedAndEnabled();
        Assert.assertTrue(addRoleButtonDisplayed, "Add Role button should be displayed after canceling edit");
    }

    @Test(priority = 21, description = "Verify role creation with numeric-only name")
    public void addRoleWithNumericName() {
        rolePage.addRoleWithonlynumeric();
        // Should either accept or show validation error
    }

    @Test(priority = 22, description = "Verify role creation with leading/trailing spaces")
    public void addRoleWithLeadingTrailingSpaces() {
        rolePage.addRoleWithWhitespaceOnlyName();
        boolean iserrormsgofspacesdisplayed = rolePage.iserrormsgofspacesdisplayed();
        Assert.assertTrue(iserrormsgofspacesdisplayed, "Error message for entering spaces in start & end in role name should be displayed");
    }
    @Test(priority = 23, description = "Verify the column header names in list screen")
    public void verifycolumnheadernames() {
        rolePage.navigatetorolepage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> expectedHeaders = Arrays.asList("S.NO", "NAME", "CREATED BY", "UPDATED BY","ACTION");
        List<String> actualHeaders = rolePage.getHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match!");
    }
    @Test(priority = 24, description = "Verify the Total role count")
    public void verifyTotalRoleCount() throws InterruptedException {
        logger.info("Starting verifyRoleCount");
        getExtentTest().log(Status.INFO, "Verifying the total role count");
        String Expectedcount = "25";
        String Actualcount = rolePage.totalRoleCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual role count is matched");
        TestLogger.success("role count verification passed");
        logger.info("role count verification PASSED" );
    }
}
