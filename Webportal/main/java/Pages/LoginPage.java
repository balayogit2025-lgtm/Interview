package Pages;

import Elements.LoginPageElements;
import Utils.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * LoginPage - Contains all actions/functionality for Login Page
 * This class uses LoginPageElements and implements business logic
 * Separation of concerns: Elements are in LoginPageElements, Actions are here
 *
 * @author Sterna QA Team
 * @version 2.0
 */
public class LoginPage {
    private WebDriver driver;
    private LoginPageElements elements;
    private WaitHelper waitHelper;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    /**
     * Constructor to initialize page actions
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elements = new LoginPageElements(driver);
        this.waitHelper = new WaitHelper(driver);
        logger.info("LoginPage initialized");
    }

    // ==================== Input Actions ====================

    /**
     * Enter email address in email field
     * @param email Email address to enter
     * @return LoginPage for method chaining
     */
    public LoginPage enterEmail(String email) {
        try {
            waitHelper.waitForElementVisible(elements.getEmailFieldLocator());
            WebElement emailField = elements.getEmailField();
            emailField.clear();
            emailField.sendKeys(email);
            logger.info("Entered email: " + email);
        } catch (Exception e) {
            logger.error("Failed to enter email: " + e.getMessage());
            throw new RuntimeException("Unable to enter email: " + email, e);
        }
        return this;
    }

    /**
     * Enter password in password field
     * @param password Password to enter
     * @return LoginPage for method chaining
     */
    public LoginPage enterPassword(String password) {
        try {
            waitHelper.waitForElementVisible(elements.getPasswordFieldLocator());
            WebElement passwordField = elements.getPasswordField();
            passwordField.clear();
            passwordField.sendKeys(password);
            logger.info("Entered password (hidden for security)");
        } catch (Exception e) {
            logger.error("Failed to enter password: " + e.getMessage());
            throw new RuntimeException("Unable to enter password", e);
        }
        return this;
    }

    /**
     * Clear email field
     * @return LoginPage for method chaining
     */
    public LoginPage clearEmailField() {
        try {
            waitHelper.waitForElementVisible(elements.getEmailFieldLocator());
            elements.getEmailField().clear();
            logger.info("Cleared email field");
        } catch (Exception e) {
            logger.error("Failed to clear email field: " + e.getMessage());
            throw new RuntimeException("Unable to clear email field", e);
        }
        return this;
    }

    /**
     * Clear password field
     * @return LoginPage for method chaining
     */
    public LoginPage clearPasswordField() {
        try {
            waitHelper.waitForElementVisible(elements.getPasswordFieldLocator());
            elements.getPasswordField().clear();
            logger.info("Cleared password field");
        } catch (Exception e) {
            logger.error("Failed to clear password field: " + e.getMessage());
            throw new RuntimeException("Unable to clear password field", e);
        }
        return this;
    }

    /**
     * Clear all input fields
     * @return LoginPage for method chaining
     */
    public LoginPage clearAllFields() {
        clearEmailField();
        clearPasswordField();
        logger.info("Cleared all input fields");
        return this;
    }

    // ==================== Click Actions ====================

    /**
     * Click login button
     * @return LoginPage for method chaining
     */
    public LoginPage clickLoginButton() {
        try {
            waitHelper.waitForElementClickable(elements.getLoginButtonLocator());
            elements.getLoginButton().click();
            logger.info("Clicked login button");
        } catch (Exception e) {
            logger.error("Failed to click login button: " + e.getMessage());
            throw new RuntimeException("Unable to click login button", e);
        }
        return this;
    }

    /**
     * Click forgot password link
     * @return LoginPage for method chaining
     */
    public LoginPage clickForgotPasswordLink() {
        try {
            waitHelper.waitForElementClickable(elements.getForgotPasswordLinkLocator());
            elements.getForgotPasswordLink().click();
            logger.info("Clicked forgot password link");
        } catch (Exception e) {
            logger.error("Failed to click forgot password link: " + e.getMessage());
            throw new RuntimeException("Unable to click forgot password link", e);
        }
        return this;
    }

    /***
     * Click remember me checkbox
     * @return LoginPage for method chaining
     */
    public LoginPage clickRememberMeCheckbox() {
        try {
            elements.getRememberMeCheckbox().click();
            logger.info("Clicked remember me checkbox");
        } catch (Exception e) {
            logger.warn("Remember me checkbox not available or clickable");
        }
        return this;
    }

    /**
     * Toggle show/hide password
     * @return LoginPage for method chaining
     */
    public LoginPage toggleShowPassword() {
        try {
            elements.getShowPasswordToggle().click();
            logger.info("Toggled show/hide password");
        } catch (Exception e) {
            logger.warn("Show password toggle not available");
        }
        return this;
    }

    // ==================== Complex Actions ====================

    /**
     * Perform complete login action
     * Enters email, password, and clicks login button
     * @param email Email address
     * @param password Password
     * @return LoginPage for method chaining
     */
    public LoginPage login(String email, String password) {
        logger.info("Performing login with email: " + email);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login action completed");
        return this;
    }

    /**
     * Perform login with remember me option
     * @param email Email address
     * @param password Password
     * @param rememberMe Whether to check remember me
     * @return LoginPage for method chaining
     */
    public LoginPage loginWithRememberMe(String email, String password, boolean rememberMe) {
        logger.info("Performing login with remember me option");
        enterEmail(email);
        enterPassword(password);
        if (rememberMe && isRememberMeCheckboxAvailable()) {
            clickRememberMeCheckbox();
        }
        clickLoginButton();
        return this;
    }

    /**
     * Attempt login and wait for result
     * @param email Email address
     * @param password Password
     * @param waitTimeSeconds Time to wait after login
     * @return LoginPage for method chaining
     */
    public LoginPage loginAndWait(String email, String password, int waitTimeSeconds) {
        login(email, password);
        try {
            Thread.sleep(waitTimeSeconds * 1000L);
        } catch (InterruptedException e) {
            logger.warn("Wait interrupted: " + e.getMessage());
        }
        return this;
    }

    // ==================== Verification Actions ====================

    /**
     * Check if email field is displayed
     * @return true if email field is displayed
     */
    public boolean isEmailFieldDisplayed() {
        try {
            waitHelper.waitForElementVisible(elements.getEmailFieldLocator());
            boolean isDisplayed = elements.getEmailField().isDisplayed();
            logger.info("Email field displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Email field not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if password field is displayed
     * @return true if password field is displayed
     */
    public boolean isPasswordFieldDisplayed() {
        try {
            waitHelper.waitForElementVisible(elements.getPasswordFieldLocator());
            boolean isDisplayed = elements.getPasswordField().isDisplayed();
            logger.info("Password field displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Password field not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if login button is displayed
     * @return true if login button is displayed
     */
    public boolean isLoginButtonDisplayed() {
        try {
            waitHelper.waitForElementVisible(elements.getLoginButtonLocator());
            boolean isDisplayed = elements.getLoginButton().isDisplayed();
            logger.info("Login button displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Login button not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if login button is enabled
     * @return true if login button is enabled
     */
    public boolean isLoginButtonEnabled() {
        try {
            boolean isEnabled = elements.getLoginButton().isEnabled();
            logger.info("Login button enabled: " + isEnabled);
            return isEnabled;
        } catch (Exception e) {
            logger.error("Failed to check login button status: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if error message is displayed
     * @return true if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        try {
            boolean isDisplayed = elements.getErrorMessage().isDisplayed();
            logger.info("Message displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("No message displayed");
            return false;
        }
    }

    /**
     * Check if success message is displayed
     * @return true if success message is displayed
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            boolean isDisplayed = elements.getSuccessMessage().isDisplayed();
            logger.info("Success message displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("No success message displayed");
            return false;
        }
    }

    /**
     * Get success message text
     * @return Success message text or empty string if not found
     */
    public String getSuccessMessageText() {
        try {
            String successText = elements.getSuccessMessage().getText();
            logger.info("Success message text: " + successText);
            return successText;
        } catch (Exception e) {
            logger.warn("No success message found");
            return "";
        }
    }

    public boolean isEmailFieldErrorMessageDisplayed() {
        try {
            boolean isDisplayed = elements.getEmailErrorMessage().isDisplayed();
            logger.info("Error message displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("No error message displayed");
            return false;
        }
    }

    public boolean isPasswordFieldErrorMessageDisplayed() {
        try {
            boolean isDisplayed = elements.getPasswordErrorMessage().isDisplayed();
            logger.info("Error message displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("No error message displayed");
            return false;
        }
    }

    /**
     * Check if forgot password link is displayed
     * @return true if forgot password link is displayed
     */
    public boolean isForgotPasswordLinkDisplayed() {
        try {
            boolean isDisplayed = elements.getForgotPasswordLink().isDisplayed();
            logger.info("Forgot password link displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("Forgot password link not displayed");
            return false;
        }
    }

    /**
     * Check if sign up link is displayed
     * @return true if sign up link is displayed
     */
    public boolean isSignUpLinkDisplayed() {
        try {
            boolean isDisplayed = elements.getSignUpLink().isDisplayed();
            logger.info("Sign up link displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("Sign up link not displayed");
            return false;
        }
    }

    /**
     * Check if remember me checkbox is available
     * @return true if remember me checkbox is available
     */
    public boolean isRememberMeCheckboxAvailable() {
        try {
            return elements.getRememberMeCheckbox().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if remember me checkbox is selected
     * @return true if remember me is checked
     */
    public boolean isRememberMeChecked() {
        try {
            boolean isSelected = elements.getRememberMeCheckbox().isSelected();
            logger.info("Remember me checked: " + isSelected);
            return isSelected;
        } catch (Exception e) {
            logger.warn("Remember me checkbox not available");
            return false;
        }
    }

    /**
     * Verify all mandatory login fields are displayed
     * @return true if email, password, and login button are all displayed
     */
    public boolean areAllMandatoryFieldsDisplayed() {
        boolean allDisplayed = isEmailFieldDisplayed() &&
                isPasswordFieldDisplayed() &&
                isLoginButtonDisplayed();
        logger.info("All mandatory fields displayed: " + allDisplayed);
        return allDisplayed;
    }

    // ==================== Get Text Actions ====================

    /**
     * Get error message text
     * @return Error message text or empty string if not found
     */
    public String getErrorMessageText() {
        try {
            String errorText = elements.getErrorMessage().getText();
            logger.info("message text: " + errorText);
            return errorText;
        } catch (Exception e) {
            logger.warn("No message found");
            return "";
        }
    }

    public String getFieldErrorMessageText() {
        try {
            String errorText = elements.getErrorMessage().getText();
            logger.info("Error message text: " + errorText);
            return errorText;
        } catch (Exception e) {
            logger.warn("No error message found");
            return "";
        }
    }

    public String getEmailFieldErrorMessageText() {
        try {
            String errorText = elements.getEmailErrorMessage().getText();
            logger.info("Error message text: " + errorText);
            return errorText;
        } catch (Exception e) {
            logger.warn("No error message found");
            return "";
        }
    }

    public String getPasswordFieldErrorMessageText() {
        try {
            String errorText = elements.getPasswordErrorMessage().getText();
            logger.info("Error message text: " + errorText);
            return errorText;
        } catch (Exception e) {
            logger.warn("No error message found");
            return "";
        }
    }

    /**
     * Get page header text
     * @return Page header text
     */
    public String getPageHeaderText() {
        try {
            String headerText = elements.getPageHeader().getText();
            logger.info("Page header text: " + headerText);
            return headerText;
        } catch (Exception e) {
            logger.warn("Page header not found");
            return "";
        }
    }

    /**
     * Get email field placeholder text
     * @return Email placeholder text
     */
    public String getEmailPlaceholder() {
        try {
            String placeholder = elements.getEmailField().getAttribute("placeholder");
            logger.info("Email placeholder: " + placeholder);
            return placeholder != null ? placeholder : "";
        } catch (Exception e) {
            logger.error("Failed to get email placeholder: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get password field placeholder text
     * @return Password placeholder text
     */
    public String getPasswordPlaceholder() {
        try {
            String placeholder = elements.getPasswordField().getAttribute("placeholder");
            logger.info("Password placeholder: " + placeholder);
            return placeholder != null ? placeholder : "";
        } catch (Exception e) {
            logger.error("Failed to get password placeholder: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get login button text
     * @return Login button text
     */
    public String getLoginButtonText() {
        try {
            String buttonText = elements.getLoginButton().getText();
            logger.info("Login button text: " + buttonText);
            return buttonText;
        } catch (Exception e) {
            logger.error("Failed to get login button text: " + e.getMessage());
            return "";
        }
    }

    // ==================== Page Navigation Actions ====================

    /**
     * Get current page title
     * @return Page title
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Page title: " + title);
        return title;
    }

    /**
     * Get current page URL
     * @return Current URL
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL: " + url);
        return url;
    }

    /**
     * Refresh the page
     * @return LoginPage for method chaining
     */
    public LoginPage refreshPage() {
        driver.navigate().refresh();
        logger.info("Page refreshed");
        return this;
    }

    /**
     * Navigate back
     * @return LoginPage for method chaining
     */
    public LoginPage navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back");
        return this;
    }

    // ==================== Wait Actions ====================

    /**
     * Wait for page to load completely
     * Waits for email field to be visible
     * @return LoginPage for method chaining
     */
    public LoginPage waitForPageToLoad() {
        waitHelper.waitForElementVisible(elements.getEmailFieldLocator());
        logger.info("Page loaded completely");
        return this;
    }

    /**
     * Wait for error message to appear
     * @param timeoutSeconds Maximum time to wait
     * @return true if error message appears within timeout
     */
    public boolean waitForErrorMessage(int timeoutSeconds) {
        try {
            waitHelper.waitForElementVisible(elements.getErrorMessageLocator(), timeoutSeconds);
            return true;
        } catch (Exception e) {
            logger.info("Error message did not appear within " + timeoutSeconds + " seconds");
            return false;
        }
    }

    /**
     * Wait for specific seconds (use sparingly)
     * @param seconds Number of seconds to wait
     * @return LoginPage for method chaining
     */
    public LoginPage waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info("Waited for " + seconds + " seconds");
        } catch (InterruptedException e) {
            logger.warn("Wait interrupted: " + e.getMessage());
        }
        return this;
    }

    // ==================== Attribute Actions ====================

    /**
     * Get email field value
     * @return Current value in email field
     */
    public String getEmailFieldValue() {
        try {
            String value = elements.getEmailField().getAttribute("value");
            logger.info("Email field value retrieved");
            return value != null ? value : "";
        } catch (Exception e) {
            logger.error("Failed to get email field value: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get password field type attribute
     * @return Type attribute value (password or text)
     */
    public String getPasswordFieldType() {
        try {
            String type = elements.getPasswordField().getAttribute("type");
            logger.info("Password field type: " + type);
            return type;
        } catch (Exception e) {
            logger.error("Failed to get password field type: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if password is masked
     * @return true if password type is "password"
     */
    public boolean isPasswordMasked() {
        return "password".equals(getPasswordFieldType());
    }

    /**
     * Get login button class attribute
     * @return Class attribute value
     */
    public String getLoginButtonClass() {
        try {
            String classValue = elements.getLoginButton().getAttribute("class");
            logger.info("Login button class: " + classValue);
            return classValue != null ? classValue : "";
        } catch (Exception e) {
            logger.error("Failed to get login button class: " + e.getMessage());
            return "";
        }
    }

    public LoginPage navigateToDashboardPage() {
        logger.info("Navigating to Dashboard Page");
        elements.getDashboardMenu().click();
        logger.info("Navigated to Dashboard Page");
        return this;
    }

    public LoginPage navigateToLocationPage() {
        logger.info("Navigating to Location Page");
        elements.getLocationMenu().click();
        logger.info("Navigated to Location Page");
        return this;
    }

    public LoginPage navigateToDepartmentPage() {
        logger.info("Navigating to Department Page");
        elements.getDeparmentMenu().click();
        logger.info("Navigated to Department Page");
        return this;
    }

    public LoginPage navigateToEmployeePage() {
        logger.info("Navigating to Department Page");
        elements.getEmployeetMenu().click();
        logger.info("Navigated to Department Page");
        return this;
    }

    public LoginPage navigateToControllerPage() {
        logger.info("Navigating to Controller Page");
        elements.getControllerMenu().click();
        logger.info("Navigated to Controller Page");
        return this;
    }

    public LoginPage navigateEventToPage() {
        logger.info("Navigating to reports menu");
        elements.getEventsMenu().click();
        logger.info("Navigated to reports menu");
        logger.info("Navigating to Events Page");
        elements.getEventsMenu().click();
        logger.info("Navigated to Events Page");
        return this;
    }

    public LoginPage navigateToAttendancePage() {
        logger.info("Navigating to Attendance Page");
        elements.getAttendanceMenu().click();
        logger.info("Navigated to Attendance Page");
        return this;
    }

    public LoginPage navigateToCompanyPage() {
        logger.info("Navigating to Company Page");
        elements.getCompanyMenu().click();
        logger.info("Navigated to Company Page");
        return this;
    }

}
