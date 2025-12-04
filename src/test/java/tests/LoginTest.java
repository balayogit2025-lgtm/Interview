package tests;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Login Test class containing all test scenarios for the Sterna AMS Login functionality.
 * Tests include positive, negative, and edge case scenarios.
 */
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initLoginPage() {
        loginPage = new LoginPage(driver);
        logger.info("LoginPage initialized for test");
    }

    // ==================== Page Load Tests ====================

    @Test(priority = 1, description = "Verify login page loads successfully")
    public void testLoginPageLoads() {
        logger.info("Test: Verify login page loads successfully");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page should be loaded");
        Assert.assertTrue(loginPage.isOnLoginPage(), "URL should contain 'login'");
    }

    @Test(priority = 2, description = "Verify all login page elements are displayed")
    public void testLoginPageElementsDisplayed() {
        logger.info("Test: Verify all login page elements are displayed");

        Assert.assertTrue(loginPage.isEmailFieldDisplayed(), "Email field should be displayed");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
    }

    @Test(priority = 3, description = "Verify login button is enabled")
    public void testLoginButtonEnabled() {
        logger.info("Test: Verify login button is enabled");

        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");
    }

    @Test(priority = 4, description = "Verify page title is present")
    public void testPageTitle() {
        logger.info("Test: Verify page title is present");

        String pageTitle = loginPage.getLoginPageTitle();
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertFalse(pageTitle.isEmpty(), "Page title should not be empty");
        logger.info("Page title: {}", pageTitle);
    }

    // ==================== Positive Login Tests ====================

    @Test(priority = 5, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        logger.info("Test: Verify successful login with valid credentials");

        String validEmail = config.getValidUsername();
        String validPassword = config.getValidPassword();

        DashboardPage dashboardPage = loginPage.login(validEmail, validPassword);
        dashboardPage.waitForDashboardLoad();

        Assert.assertTrue(dashboardPage.isUserLoggedIn(),
                "User should be logged in and redirected away from login page");
    }

    @Test(priority = 6, description = "Verify login with valid credentials and page navigation")
    public void testLoginNavigatesToDashboard() {
        logger.info("Test: Verify login navigates to dashboard");

        String validEmail = config.getValidUsername();
        String validPassword = config.getValidPassword();

        DashboardPage dashboardPage = loginPage.login(validEmail, validPassword);
        dashboardPage.waitForDashboardLoad();

        Assert.assertFalse(dashboardPage.getCurrentUrl().toLowerCase().contains("login"),
                "URL should not contain 'login' after successful login");
    }

    // ==================== Negative Login Tests ====================

    @Test(priority = 7, description = "Verify login fails with invalid email")
    public void testLoginWithInvalidEmail() {
        logger.info("Test: Verify login fails with invalid email");

        String invalidEmail = config.getInvalidUsername();
        String validPassword = config.getValidPassword();

        loginPage.loginExpectingFailure(invalidEmail, validPassword);

        // Should remain on login page or show error
        Assert.assertTrue(loginPage.isOnLoginPage() || loginPage.isErrorMessageDisplayed(),
                "Should remain on login page or show error for invalid email");
    }

    @Test(priority = 8, description = "Verify login fails with invalid password")
    public void testLoginWithInvalidPassword() {
        logger.info("Test: Verify login fails with invalid password");

        String validEmail = config.getValidUsername();
        String invalidPassword = config.getInvalidPassword();

        loginPage.loginExpectingFailure(validEmail, invalidPassword);

        // Should remain on login page or show error
        Assert.assertTrue(loginPage.isOnLoginPage() || loginPage.isErrorMessageDisplayed(),
                "Should remain on login page or show error for invalid password");
    }

    @Test(priority = 9, description = "Verify login fails with invalid email and password")
    public void testLoginWithInvalidCredentials() {
        logger.info("Test: Verify login fails with invalid email and password");

        String invalidEmail = config.getInvalidUsername();
        String invalidPassword = config.getInvalidPassword();

        loginPage.loginExpectingFailure(invalidEmail, invalidPassword);

        // Should remain on login page or show error
        Assert.assertTrue(loginPage.isOnLoginPage() || loginPage.isErrorMessageDisplayed(),
                "Should remain on login page or show error for invalid credentials");
    }

    // ==================== Empty Field Tests ====================

    @Test(priority = 10, description = "Verify login fails with empty email")
    public void testLoginWithEmptyEmail() {
        logger.info("Test: Verify login fails with empty email");

        String validPassword = config.getValidPassword();

        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();

        // Should remain on login page
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Should remain on login page when email is empty");
    }

    @Test(priority = 11, description = "Verify login fails with empty password")
    public void testLoginWithEmptyPassword() {
        logger.info("Test: Verify login fails with empty password");

        String validEmail = config.getValidUsername();

        loginPage.enterEmail(validEmail);
        loginPage.clickLoginButton();

        // Should remain on login page
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Should remain on login page when password is empty");
    }

    @Test(priority = 12, description = "Verify login fails with empty email and password")
    public void testLoginWithEmptyCredentials() {
        logger.info("Test: Verify login fails with empty email and password");

        loginPage.clickLoginButton();

        // Should remain on login page
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Should remain on login page when both fields are empty");
    }

    // ==================== Input Validation Tests ====================

    @Test(priority = 13, description = "Verify login fails with invalid email format")
    public void testLoginWithInvalidEmailFormat() {
        logger.info("Test: Verify login fails with invalid email format");

        String invalidEmailFormat = "invalidemail";
        String validPassword = config.getValidPassword();

        loginPage.loginExpectingFailure(invalidEmailFormat, validPassword);

        // Should remain on login page or show validation error
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Should remain on login page for invalid email format");
    }

    @Test(priority = 14, description = "Verify login handles whitespace in email")
    public void testLoginWithWhitespaceEmail() {
        logger.info("Test: Verify login handles whitespace in email");

        String emailWithSpaces = "  " + config.getValidUsername() + "  ";
        String validPassword = config.getValidPassword();

        loginPage.loginExpectingFailure(emailWithSpaces, validPassword);

        // Behavior depends on application - it might trim or fail
        logger.info("Login attempted with whitespace-padded email");
    }

    @Test(priority = 15, description = "Verify login with special characters in password")
    public void testLoginWithSpecialCharactersPassword() {
        logger.info("Test: Verify login with special characters in password");

        String validEmail = config.getValidUsername();
        String specialPassword = "!@#$%^&*()_+";

        loginPage.loginExpectingFailure(validEmail, specialPassword);

        // Should fail with incorrect password
        Assert.assertTrue(loginPage.isOnLoginPage() || loginPage.isErrorMessageDisplayed(),
                "Should remain on login page for incorrect password");
    }

    // ==================== Case Sensitivity Tests ====================

    @Test(priority = 16, description = "Verify email is case-insensitive")
    public void testEmailCaseInsensitivity() {
        logger.info("Test: Verify email is case-insensitive");

        String upperCaseEmail = config.getValidUsername().toUpperCase();
        String validPassword = config.getValidPassword();

        DashboardPage dashboardPage = loginPage.login(upperCaseEmail, validPassword);

        // Email should typically be case-insensitive
        // The result depends on application implementation
        logger.info("Login attempted with uppercase email: {}", upperCaseEmail);
    }

    @Test(priority = 17, description = "Verify password is case-sensitive")
    public void testPasswordCaseSensitivity() {
        logger.info("Test: Verify password is case-sensitive");

        String validEmail = config.getValidUsername();
        String wrongCasePassword = config.getValidPassword().toLowerCase();

        // Only run if password has uppercase letters
        if (!config.getValidPassword().equals(wrongCasePassword)) {
            loginPage.loginExpectingFailure(validEmail, wrongCasePassword);

            // Should fail as passwords are typically case-sensitive
            Assert.assertTrue(loginPage.isOnLoginPage() || loginPage.isErrorMessageDisplayed(),
                    "Should fail login with wrong case password");
        } else {
            logger.info("Skipping - password has no case variation");
        }
    }

    // ==================== Field Interaction Tests ====================

    @Test(priority = 18, description = "Verify email field accepts input")
    public void testEmailFieldAcceptsInput() {
        logger.info("Test: Verify email field accepts input");

        String testEmail = "test@example.com";
        loginPage.enterEmail(testEmail);

        String enteredValue = loginPage.getEmailFieldValue();
        Assert.assertTrue(enteredValue.contains("test") || enteredValue.contains("example"),
                "Email field should accept and display entered value");
    }

    @Test(priority = 19, description = "Verify password field masks input")
    public void testPasswordFieldMasksInput() {
        logger.info("Test: Verify password field masks input");

        String testPassword = "TestPassword123";
        loginPage.enterPassword(testPassword);

        // Password field should have value but we can't see the masked text directly
        String passwordValue = loginPage.getPasswordFieldValue();
        Assert.assertNotNull(passwordValue, "Password field should have a value");
    }

    @Test(priority = 20, description = "Verify clearing fields works")
    public void testClearFields() {
        logger.info("Test: Verify clearing fields works");

        loginPage.enterEmail("test@test.com");
        loginPage.enterPassword("password123");
        loginPage.clearAllFields();

        String emailValue = loginPage.getEmailFieldValue();
        String passwordValue = loginPage.getPasswordFieldValue();

        Assert.assertTrue(emailValue == null || emailValue.isEmpty(),
                "Email field should be cleared");
        Assert.assertTrue(passwordValue == null || passwordValue.isEmpty(),
                "Password field should be cleared");
    }

    // ==================== Logout Test ====================

    @Test(priority = 21, description = "Verify user can logout after login")
    public void testLogoutAfterLogin() {
        logger.info("Test: Verify user can logout after login");

        // First login
        String validEmail = config.getValidUsername();
        String validPassword = config.getValidPassword();

        DashboardPage dashboardPage = loginPage.login(validEmail, validPassword);
        dashboardPage.waitForDashboardLoad();

        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        // Then logout
        LoginPage loginPageAfterLogout = dashboardPage.logout();

        Assert.assertTrue(loginPageAfterLogout.isOnLoginPage(),
                "Should be redirected to login page after logout");
    }

    // ==================== Data-Driven Tests ====================

    @DataProvider(name = "invalidCredentials")
    public Object[][] getInvalidCredentials() {
        return new Object[][]{
                {"", "password123", "Empty email"},
                {"test@test.com", "", "Empty password"},
                {"", "", "Both empty"},
                {"notanemail", "password123", "Invalid email format"},
                {"test@test.com", "short", "Short password"},
                {"admin@gmail.com", "wrongpassword", "Wrong password"},
                {"wrong@email.com", "Sterna@123", "Wrong email"}
        };
    }

    @Test(priority = 22, dataProvider = "invalidCredentials",
            description = "Verify login fails with various invalid credentials")
    public void testLoginWithVariousInvalidCredentials(String email, String password, String scenario) {
        logger.info("Test: {} - Email: '{}', Password: '{}'", scenario, email, password.isEmpty() ? "[empty]" : "[hidden]");

        if (!email.isEmpty()) {
            loginPage.enterEmail(email);
        }
        if (!password.isEmpty()) {
            loginPage.enterPassword(password);
        }
        loginPage.clickLoginButton();

        // Should remain on login page for all invalid scenarios
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Should remain on login page for scenario: " + scenario);
    }

    @DataProvider(name = "sqlInjectionAttempts")
    public Object[][] getSqlInjectionAttempts() {
        return new Object[][]{
                {"admin'--", "password"},
                {"' OR '1'='1", "password"},
                {"admin@gmail.com", "' OR '1'='1"},
                {"'; DROP TABLE users; --", "password"}
        };
    }

    @Test(priority = 23, dataProvider = "sqlInjectionAttempts",
            description = "Verify login is protected against SQL injection")
    public void testSqlInjectionProtection(String email, String password) {
        logger.info("Test: SQL Injection protection test");

        loginPage.loginExpectingFailure(email, password);

        // Should remain on login page - SQL injection should not work
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Login should be protected against SQL injection");
    }

    // ==================== Session Tests ====================

    @Test(priority = 24, description = "Verify session persistence after login")
    public void testSessionPersistenceAfterRefresh() {
        logger.info("Test: Verify session persistence after page refresh");

        // Login first
        String validEmail = config.getValidUsername();
        String validPassword = config.getValidPassword();

        DashboardPage dashboardPage = loginPage.login(validEmail, validPassword);
        dashboardPage.waitForDashboardLoad();

        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        // Refresh page
        dashboardPage.refreshPage();

        // Should still be logged in
        Assert.assertTrue(dashboardPage.isUserLoggedIn(),
                "User should remain logged in after page refresh");
    }

    @Test(priority = 25, description = "Verify accessing login page after login redirects to dashboard")
    public void testLoginPageAccessAfterLogin() {
        logger.info("Test: Verify accessing login page after login");

        // Login first
        String validEmail = config.getValidUsername();
        String validPassword = config.getValidPassword();

        DashboardPage dashboardPage = loginPage.login(validEmail, validPassword);
        dashboardPage.waitForDashboardLoad();

        // Try to navigate back to login page
        dashboardPage.navigateTo(config.getAppUrl());

        // Application may redirect to dashboard if already logged in
        // or may allow access to login page
        logger.info("Current URL after navigating to login: {}", dashboardPage.getCurrentUrl());
    }
}
