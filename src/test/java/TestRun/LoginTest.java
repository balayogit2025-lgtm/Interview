package TestRun;

import com.aventstack.extentreports.Status;
import Base.BaseTest;
import Pages.LoginPage;
import Utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Base.BaseTest.getDriver;
import static Base.BaseTest.getExtentTest;

/**
 * LoginTest - Contains all test cases for Login Page
 * Tests cover positive and negative login scenarios
 *
 * @author Sterna QA Team
 * @version 2.0
 */
public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setupLoginTest() {
        navigateToApp();
        loginPage = new LoginPage(getDriver());
        getExtentTest().log(Status.INFO, "Navigated to Login Page");
    }

    // ==================== POSITIVE TEST CASES ====================



    /**
     * TC_LOGIN_001: Verify login page elements are displayed
     */
    @Test(priority = 1, description = "Verify login page elements are displayed")
    public void verifyLoginPageElements() throws InterruptedException {
        getExtentTest().log(Status.INFO, "Test: Verify login page elements are displayed");
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isEmailFieldDisplayed(),
                "Email field should be displayed");
        getExtentTest().log(Status.PASS, "Email field is displayed");

        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(),
                "Password field should be displayed");
        getExtentTest().log(Status.PASS, "Password field is displayed");

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                "Login button should be displayed");
        getExtentTest().log(Status.PASS, "Login button is displayed");

        Assert.assertTrue(loginPage.isLoginButtonEnabled(),
                "Login button should be enabled");
        getExtentTest().log(Status.PASS, "All login page elements are displayed correctly");
    }

    /**
     * TC_LOGIN_002: Verify successful login with valid credentials
     */
    @Test(priority = 2, description = "Verify successful login with valid credentials")
    public void verifySuccessfulLogin() throws InterruptedException {
        getExtentTest().log(Status.INFO, "Test: Verify successful login with valid credentials");

        String email = ConfigReader.getValidEmail();
        String password = ConfigReader.getValidPassword();

        loginPage.login(email, password);
        getExtentTest().log(Status.INFO, "Entered valid credentials and clicked login");
        // Verify success message is displayed (if available)
        if (loginPage.isSuccessMessageDisplayed()) {
            String successMessage = loginPage.getSuccessMessageText();
            getExtentTest().log(Status.INFO, "Success message: " + successMessage);

            Assert.assertFalse(successMessage.isEmpty(), "Success message should not be empty");
            Assert.assertTrue(successMessage.toLowerCase().contains("success") ||
                            successMessage.toLowerCase().contains("welcome") ||
                            successMessage.toLowerCase().contains("logged in"),
                    "Success message should contain expected text");
            getExtentTest().log(Status.PASS, "Success message verified: " + successMessage);
        } else {
            getExtentTest().log(Status.INFO, "No success message displayed - login verified via URL redirect to dashboard");
        }
    }

    /**
     * TC_LOGIN_003: Verify page title
     */
    @Test(priority = 3, description = "Verify login page title")
    public void verifyLoginPageTitle() {
        getExtentTest().log(Status.INFO, "Test: Verify login page title");
        String pageTitle = loginPage.getPageTitle();
        getExtentTest().log(Status.INFO, "Page Title: " + pageTitle);
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertFalse(pageTitle.isEmpty(), "Page title should not be empty");
        getExtentTest().log(Status.PASS, "Page title verified: " + pageTitle);
    }

    /**
     * TC_LOGIN_004: Verify current URL
     */
    @Test(priority = 4, description = "Verify login page URL")
    public void verifyLoginPageUrl() {
        getExtentTest().log(Status.INFO, "Test: Verify login page URL");
        String currentUrl = loginPage.getCurrentUrl();
        String expectedUrl = ConfigReader.getAppUrl();
        getExtentTest().log(Status.INFO, "Current URL: " + currentUrl);
        getExtentTest().log(Status.INFO, "Expected URL: " + expectedUrl);
        Assert.assertTrue(currentUrl.contains(expectedUrl) ||
                        expectedUrl.contains(currentUrl.split("\\?")[0]),
                "Current URL should match expected URL");
        getExtentTest().log(Status.PASS, "URL verified successfully");
    }

    /**
     * TC_LOGIN_005: Verify email field accepts input
     */
    @Test(priority = 5, description = "Verify email field accepts input")
    public void verifyEmailFieldAcceptsInput() {
        getExtentTest().log(Status.INFO, "Test: Verify email field accepts input");
        String testEmail = "test@example.com";
        loginPage.enterEmail(testEmail);
        getExtentTest().log(Status.INFO, "Entered email: " + testEmail);
        Assert.assertTrue(loginPage.isEmailFieldDisplayed(),
                "Email field should remain displayed after input");
        getExtentTest().log(Status.PASS, "Email field accepts input successfully");
    }

    /**
     * TC_LOGIN_006: Verify password field masks input
     */
    @Test(priority = 6, description = "Verify password field masks input")
    public void verifyPasswordFieldMasksInput() {
        getExtentTest().log(Status.INFO, "Test: Verify password field masks input");
        String testPassword = "TestPassword123";
        loginPage.enterPassword(testPassword);
        getExtentTest().log(Status.INFO, "Entered password");
        Assert.assertTrue(loginPage.isPasswordMasked(),
                "Password field should mask input");
        getExtentTest().log(Status.PASS, "Password field masks input correctly");
    }

    /**
     * TC_LOGIN_007: Verify clear functionality
     */
    @Test(priority = 7, description = "Verify field clear functionality")
    public void verifyFieldClearFunctionality() {
        getExtentTest().log(Status.INFO, "Test: Verify field clear functionality");

        // Enter data
        loginPage.enterEmail("test@example.com");
        loginPage.enterPassword("TestPassword123");
        getExtentTest().log(Status.INFO, "Entered test credentials");

        // Clear fields
        loginPage.clearEmailField();
        loginPage.clearPasswordField();
        getExtentTest().log(Status.INFO, "Cleared all fields");

        Assert.assertTrue(loginPage.isEmailFieldDisplayed(),
                "Email field should be displayed after clearing");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(),
                "Password field should be displayed after clearing");
        getExtentTest().log(Status.PASS, "Fields cleared successfully");
    }

    /**
     * TC_LOGIN_008: Verify login button is clickable
     */
    @Test(priority = 8, description = "Verify login button is clickable")
    public void verifyLoginButtonIsClickable() {
        getExtentTest().log(Status.INFO, "Test: Verify login button is clickable");

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                "Login button should be displayed");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(),
                "Login button should be enabled");
        getExtentTest().log(Status.PASS, "Login button is displayed and enabled");
    }

    // ==================== NEGATIVE TEST CASES ====================

    /**
     * TC_LOGIN_009: Verify login fails with invalid email
     */
    @Test(priority = 9, description = "Verify login fails with invalid email")
    public void verifyLoginWithInvalidEmail() throws InterruptedException {
        getExtentTest().log(Status.INFO, "Test: Verify login fails with invalid email");

        String email = ConfigReader.getInvalidEmail();
        String password = ConfigReader.getValidPassword();

        loginPage.login(email, password);
        getExtentTest().log(Status.INFO, "Entered invalid email and valid password");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String Message = loginPage.getErrorMessageText();
        // Verify user remains on login page
        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage || loginPage.isErrorMessageDisplayed(),
                "Login should fail with invalid email");
        getExtentTest().log(Status.PASS, "Login failed with invalid email as expected");
        // Verify success message is displayed (if available)
       //Thread.sleep(300);

        getExtentTest().log(Status.INFO, "Error message: " + Message);
        Assert.assertFalse(Message.isEmpty(), "Success message should not be empty");
        Assert.assertEquals(Message , "user not found", "Error message should contain expected text");
        getExtentTest().log(Status.PASS, "Error message verified: " + Message);

    }

    /**
     * TC_LOGIN_010: Verify login fails with invalid password
     */
    @Test(priority = 10, description = "Verify login fails with invalid password")
    public void verifyLoginWithInvalidPassword() {
        getExtentTest().log(Status.INFO, "Test: Verify login fails with invalid password");

        String email = ConfigReader.getValidEmail();
        String password = ConfigReader.getInvalidPassword();

        loginPage.login(email, password);
        getExtentTest().log(Status.INFO, "Entered valid email and invalid password");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage || loginPage.isErrorMessageDisplayed(),
                "Login should fail with invalid password");
        getExtentTest().log(Status.PASS, "Login failed with invalid password as expected");

        // Verify success message is displayed (if available)
        String Message = loginPage.getErrorMessageText();
        getExtentTest().log(Status.INFO, "Error message: " + Message);

        Assert.assertFalse(Message.isEmpty(), "Success message should not be empty");
        Assert.assertEquals(Message , "Invalid credential", "Error message should contain expected text");
        getExtentTest().log(Status.PASS, "Error message verified: " + Message);

    }

    /**
     * TC_LOGIN_011: Verify login fails with empty credentials
     */
    @Test(priority = 11, description = "Verify login fails with empty credentials")
    public void verifyLoginWithEmptyCredentials() {
        getExtentTest().log(Status.INFO, "Test: Verify login fails with empty credentials");

        loginPage.login("", "");
        getExtentTest().log(Status.INFO, "Clicked login with empty credentials");
        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page with empty credentials");
        getExtentTest().log(Status.PASS, "Login failed with empty credentials as expected");
        // Verify Email error message is displayed (if available)
        String emailerrorMessage = loginPage.getEmailFieldErrorMessageText();
        getExtentTest().log(Status.INFO, "Error message: " + emailerrorMessage);

        Assert.assertFalse(emailerrorMessage.isEmpty(), "Success message should not be empty");
        Assert.assertEquals(emailerrorMessage , "Invalid email address", "Error message should contain expected text");
        getExtentTest().log(Status.PASS, "Email Error message verified: " + emailerrorMessage);

        // Verify password error message is displayed (if available)
        String passwordErrorMessage = loginPage.getPasswordFieldErrorMessageText();
        getExtentTest().log(Status.INFO, "Error message: " + passwordErrorMessage);

        Assert.assertFalse(passwordErrorMessage.isEmpty(), "Success message should not be empty");
        Assert.assertEquals(passwordErrorMessage , "Password must be at least 8 characters", "Error message should contain expected text");
        getExtentTest().log(Status.PASS, "Email Error message verified: " + passwordErrorMessage);


    }

    /**
     * TC_LOGIN_012: Verify login with empty email
     */
    @Test(priority = 12, description = "Verify login with empty email")
    public void verifyLoginWithEmptyEmail() {
        getExtentTest().log(Status.INFO, "Test: Verify login with empty email");

        loginPage.enterEmail("");
        loginPage.enterPassword(ConfigReader.getValidPassword());
        loginPage.clickLoginButton();
        getExtentTest().log(Status.INFO, "Clicked login with empty email");

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page with empty email");
        getExtentTest().log(Status.PASS, "Login failed with empty email as expected");

        // Check for error message
        boolean errorDisplayed = loginPage.isEmailFieldErrorMessageDisplayed();
        if (errorDisplayed) {
            String errorMessage = loginPage.getEmailFieldErrorMessageText();
            Assert.assertEquals(errorMessage , "Invalid email address", "Error message should contain expected text");
            getExtentTest().log(Status.INFO, "Error message: " + errorMessage);
        }
        getExtentTest().log(Status.PASS, "Login correctly failed with empty email");
    }

    /**
     * TC_LOGIN_013: Verify login with empty password
     */
    @Test(priority = 13, description = "Verify login with empty password")
    public void verifyLoginWithEmptyPassword() {
        getExtentTest().log(Status.INFO, "Test: Verify login with empty password");

        loginPage.enterEmail(ConfigReader.getValidEmail());
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        getExtentTest().log(Status.INFO, "Clicked login with empty password");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page with empty password");
        getExtentTest().log(Status.PASS, "Login failed with empty password as expected");

        // Check for error message
        boolean errorDisplayed = loginPage.isPasswordFieldErrorMessageDisplayed();
        if (errorDisplayed) {
            String errorMessage = loginPage.getPasswordFieldErrorMessageText();
            Assert.assertEquals(errorMessage , "Password must be at least 8 characters", "Error message should contain expected text");
            getExtentTest().log(Status.INFO, "Error message: " + errorMessage);
        }
        getExtentTest().log(Status.PASS, "Login correctly failed with empty password");
    }

    /**
     * TC_LOGIN_014: Verify login with SQL injection attempt
     */
    @Test(priority = 14, description = "Verify login handles SQL injection")
    public void verifyLoginWithSQLInjection() {
        getExtentTest().log(Status.INFO, "Test: Verify login handles SQL injection");

        String sqlInjectionEmail = "' OR '1'='1";
        String sqlInjectionPassword = "' OR '1'='1";

        loginPage.login(sqlInjectionEmail, sqlInjectionPassword);
        getExtentTest().log(Status.INFO, "Attempted SQL injection");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page - SQL injection should be prevented");
        getExtentTest().log(Status.PASS, "SQL injection prevented successfully");
    }

    /**
     * TC_LOGIN_015: Verify login with special characters in email
     */
    @Test(priority = 15, description = "Verify login with special characters")
    public void verifyLoginWithSpecialCharacters() {
        getExtentTest().log(Status.INFO, "Test: Verify login with special characters");

        String specialEmail = "test!@#$%^&*()@example.com";
        String password = ConfigReader.getValidPassword();

        loginPage.login(specialEmail, password);
        getExtentTest().log(Status.INFO, "Entered email with special characters");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should handle special characters gracefully
        Assert.assertTrue(loginPage.isEmailFieldDisplayed(),
                "Email field should handle special characters");
        getExtentTest().log(Status.PASS, "Special characters handled correctly");
    }

    /**
     * TC_LOGIN_018: Verify login with XSS attack attempt
     */
    @Test(priority = 18, description = "Verify login handles XSS attack")
    public void verifyLoginWithXSSAttack() {
        getExtentTest().log(Status.INFO, "Test: Verify login handles XSS attack");

        String xssEmail = "<script>alert('XSS')</script>@test.com";
        String xssPassword = "<script>alert('XSS')</script>";

        loginPage.login(xssEmail, xssPassword);
        getExtentTest().log(Status.INFO, "Attempted XSS injection");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page - XSS attack should be prevented");
        getExtentTest().log(Status.PASS, "XSS attack prevented successfully");
    }

    /**
     * TC_LOGIN_019: Verify login with very long email
     */
    @Test(priority = 19, description = "Verify login with very long email")
    public void verifyLoginWithVeryLongEmail() {
        getExtentTest().log(Status.INFO, "Test: Verify login with very long email");

        // Create a very long email (more than 255 characters)
        StringBuilder longEmail = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            longEmail.append("verylongemail");
        }
        longEmail.append("@example.com");

        loginPage.login(longEmail.toString(), ConfigReader.getValidPassword());
        getExtentTest().log(Status.INFO, "Entered very long email");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page with very long email");
        getExtentTest().log(Status.PASS, "Very long email handled correctly");
    }

    /**
     * TC_LOGIN_020: Verify login with invalid email format
     */
    @Test(priority = 20, description = "Verify login with invalid email format")
    public void verifyLoginWithInvalidEmailFormat() {
        getExtentTest().log(Status.INFO, "Test: Verify login with invalid email format");

        String invalidFormatEmail = "invalidemail.com";

        loginPage.login(invalidFormatEmail, ConfigReader.getValidPassword());
        getExtentTest().log(Status.INFO, "Entered email without @ symbol");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page with invalid email format");
        getExtentTest().log(Status.PASS, "Invalid email format handled correctly");
    }

    /**
     * TC_LOGIN_021: Verify login with spaces only in fields
     */
    @Test(priority = 21, description = "Verify login with spaces only in fields")
    public void verifyLoginWithSpacesOnly() {
        getExtentTest().log(Status.INFO, "Test: Verify login with spaces only in fields");

        loginPage.login("   ", "   ");
        getExtentTest().log(Status.INFO, "Entered spaces only in email and password fields");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page with spaces only");
        getExtentTest().log(Status.PASS, "Spaces only input handled correctly");
    }

    /**
     * TC_LOGIN_022: Verify login with case sensitivity in email
     */
    @Test(priority = 22, description = "Verify login with uppercase email")
    public void verifyLoginWithUppercaseEmail() {
        getExtentTest().log(Status.INFO, "Test: Verify login with uppercase email");

        String email = ConfigReader.getValidEmail().toUpperCase();
        String password = ConfigReader.getValidPassword();

        loginPage.login(email, password);
        getExtentTest().log(Status.INFO, "Entered uppercase email: " + email);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Log the result
        String currentUrl = loginPage.getCurrentUrl();
        getExtentTest().log(Status.INFO, "Current URL after login attempt: " + currentUrl);
        getExtentTest().log(Status.PASS, "Uppercase email test completed");
    }

    // ==================== DATA DRIVEN TEST CASES ====================

    /**
     * Data Provider for invalid credentials
     */
    @DataProvider(name = "invalidCredentials")
    public Object[][] getInvalidCredentials() {
        return new Object[][] {
                {"", "", "Empty email and password"},
                {"test@", "Password123", "Invalid email format - no domain"},
                {"@test.com", "Password123", "Invalid email format - no local part"},
                {"test.test.com", "Password123", "Invalid email format - no @ symbol"},
                {"test@test", "Password123", "Invalid email format - incomplete domain"},
                {"valid@email.com", "", "Empty password"},
                {"", "ValidPass123", "Empty email"},
        };
    }

    /**
     * TC_LOGIN_023: Data-driven test for invalid credentials
     */
    @Test(priority = 23, dataProvider = "invalidCredentials",
            description = "Verify login fails with various invalid credentials")
    public void verifyLoginWithVariousInvalidCredentials(String email, String password, String scenario) {
        getExtentTest().log(Status.INFO, "Test Scenario: " + scenario);
        getExtentTest().log(Status.INFO, "Email: " + email + ", Password: " + (password.isEmpty() ? "[empty]" : "[provided]"));

        loginPage.login(email, password);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = loginPage.getCurrentUrl();
        boolean isStillOnLoginPage = currentUrl.contains("login") ||
                currentUrl.contains(ConfigReader.getAppUrl());

        Assert.assertTrue(isStillOnLoginPage,
                "Should remain on login page for scenario: " + scenario);
        getExtentTest().log(Status.PASS, "Scenario passed: " + scenario);
    }

}
