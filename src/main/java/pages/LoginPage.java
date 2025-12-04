package pages;

import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Login Page Object class representing the Sterna AMS Login page.
 * Contains all page elements and methods for login functionality.
 */
public class LoginPage extends BasePage {

    // ==================== Page Elements (using @FindBy) ====================

    @FindBy(id = LoginPageLocators.EMAIL_ID)
    private WebElement emailInput;

    @FindBy(name = LoginPageLocators.EMAIL_NAME)
    private WebElement emailInputByName;

    @FindBy(xpath = LoginPageLocators.EMAIL_XPATH)
    private WebElement emailInputByType;

    @FindBy(id = LoginPageLocators.PASSWORD_ID)
    private WebElement passwordInput;

    @FindBy(name = LoginPageLocators.PASSWORD_NAME)
    private WebElement passwordInputByName;

    @FindBy(xpath = LoginPageLocators.PASSWORD_XPATH)
    private WebElement passwordInputByType;

    @FindBy(xpath = LoginPageLocators.LOGIN_BUTTON_XPATH)
    private WebElement loginButton;

    @FindBy(xpath = LoginPageLocators.LOGIN_BUTTON_BY_TEXT_XPATH)
    private WebElement loginButtonByText;

    @FindBy(id = LoginPageLocators.REMEMBER_ME_ID)
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = LoginPageLocators.REMEMBER_ME_XPATH)
    private WebElement rememberMeCheckboxByType;

    @FindBy(xpath = LoginPageLocators.FORGOT_PASSWORD_XPATH)
    private WebElement forgotPasswordLink;

    @FindBy(xpath = LoginPageLocators.ERROR_MESSAGE_XPATH)
    private WebElement errorMessage;

    @FindBy(xpath = LoginPageLocators.VALIDATION_ERROR_XPATH)
    private WebElement validationError;

    @FindBy(xpath = LoginPageLocators.PAGE_HEADER_XPATH)
    private WebElement pageHeader;

    // ==================== Constructor ====================

    public LoginPage(WebDriver driver) {
        super(driver);
        logger.info("LoginPage initialized");
    }

    // ==================== Page Actions ====================

    /**
     * Enters email/username in the email field.
     */
    public LoginPage enterEmail(String email) {
        logger.info("Entering email: {}", email);
        try {
            waitForVisible(LoginPageLocators.EMAIL_INPUT);
            enterText(LoginPageLocators.EMAIL_INPUT, email);
        } catch (Exception e) {
            enterText(emailInput, email);
        }
        return this;
    }

    /**
     * Enters password in the password field.
     */
    public LoginPage enterPassword(String password) {
        logger.info("Entering password");
        try {
            waitForVisible(LoginPageLocators.PASSWORD_INPUT);
            enterText(LoginPageLocators.PASSWORD_INPUT, password);
        } catch (Exception e) {
            enterText(passwordInput, password);
        }
        return this;
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        logger.info("Clicking login button");
        try {
            waitForClickable(LoginPageLocators.LOGIN_BUTTON);
            click(LoginPageLocators.LOGIN_BUTTON);
        } catch (Exception e) {
            click(loginButton);
        }
    }

    /**
     * Performs complete login with provided credentials.
     * @return DashboardPage if login is successful
     */
    public DashboardPage login(String email, String password) {
        logger.info("Performing login with email: {}", email);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        waitUtils.waitForPageLoad();
        return new DashboardPage(driver);
    }

    /**
     * Performs login and stays on login page (for negative tests).
     */
    public LoginPage loginExpectingFailure(String email, String password) {
        logger.info("Performing login expecting failure with email: {}", email);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    /**
     * Clicks on the Remember Me checkbox.
     */
    public LoginPage clickRememberMe() {
        logger.info("Clicking Remember Me checkbox");
        try {
            click(rememberMeCheckbox);
        } catch (Exception e) {
            click(rememberMeCheckboxByType);
        }
        return this;
    }

    /**
     * Checks if Remember Me checkbox is selected.
     */
    public boolean isRememberMeSelected() {
        try {
            return rememberMeCheckbox.isSelected();
        } catch (Exception e) {
            return rememberMeCheckboxByType.isSelected();
        }
    }

    /**
     * Clicks on the Forgot Password link.
     */
    public void clickForgotPassword() {
        logger.info("Clicking Forgot Password link");
        click(forgotPasswordLink);
    }

    /**
     * Clears both email and password fields.
     */
    public LoginPage clearAllFields() {
        logger.info("Clearing all input fields");
        try {
            elementUtils.clearText(LoginPageLocators.EMAIL_INPUT);
            elementUtils.clearText(LoginPageLocators.PASSWORD_INPUT);
        } catch (Exception e) {
            emailInput.clear();
            passwordInput.clear();
        }
        return this;
    }

    // ==================== Verification Methods ====================

    /**
     * Checks if the login page is loaded.
     */
    @Override
    public boolean isPageLoaded() {
        logger.info("Verifying login page is loaded");
        try {
            waitUtils.waitForPageLoad();
            return waitUtils.waitAndCheckElementDisplayed(LoginPageLocators.EMAIL_INPUT, 10) &&
                    waitUtils.waitAndCheckElementDisplayed(LoginPageLocators.PASSWORD_INPUT, 5) &&
                    waitUtils.waitAndCheckElementDisplayed(LoginPageLocators.LOGIN_BUTTON, 5);
        } catch (Exception e) {
            logger.error("Login page not loaded properly: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if email field is displayed.
     */
    public boolean isEmailFieldDisplayed() {
        try {
            return isDisplayed(LoginPageLocators.EMAIL_INPUT) || isDisplayed(emailInput);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if password field is displayed.
     */
    public boolean isPasswordFieldDisplayed() {
        try {
            return isDisplayed(LoginPageLocators.PASSWORD_INPUT) || isDisplayed(passwordInput);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if login button is displayed.
     */
    public boolean isLoginButtonDisplayed() {
        try {
            return isDisplayed(LoginPageLocators.LOGIN_BUTTON) || isDisplayed(loginButton);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if login button is enabled.
     */
    public boolean isLoginButtonEnabled() {
        try {
            return isEnabled(LoginPageLocators.LOGIN_BUTTON);
        } catch (Exception e) {
            return loginButton.isEnabled();
        }
    }

    /**
     * Checks if error message is displayed.
     */
    public boolean isErrorMessageDisplayed() {
        try {
            waitUtils.waitForElementVisible(LoginPageLocators.ERROR_MESSAGE, 5);
            return isDisplayed(LoginPageLocators.ERROR_MESSAGE);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the error message text.
     */
    public String getErrorMessage() {
        logger.info("Getting error message");
        try {
            waitUtils.waitForElementVisible(LoginPageLocators.ERROR_MESSAGE, 5);
            return getText(LoginPageLocators.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.warn("Could not get error message: {}", e.getMessage());
            return "";
        }
    }

    /**
     * Gets the page title.
     */
    public String getLoginPageTitle() {
        return getPageTitle();
    }

    /**
     * Gets the page header text.
     */
    public String getPageHeaderText() {
        try {
            waitForVisible(pageHeader);
            return getText(pageHeader);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Checks if the URL contains 'login'.
     */
    public boolean isOnLoginPage() {
        return getCurrentUrl().toLowerCase().contains("login");
    }

    /**
     * Gets the value entered in email field.
     */
    public String getEmailFieldValue() {
        try {
            return elementUtils.getValue(LoginPageLocators.EMAIL_INPUT);
        } catch (Exception e) {
            return emailInput.getAttribute("value");
        }
    }

    /**
     * Gets the value entered in password field.
     */
    public String getPasswordFieldValue() {
        try {
            return elementUtils.getValue(LoginPageLocators.PASSWORD_INPUT);
        } catch (Exception e) {
            return passwordInput.getAttribute("value");
        }
    }

    /**
     * Checks if Forgot Password link is displayed.
     */
    public boolean isForgotPasswordLinkDisplayed() {
        try {
            return isDisplayed(forgotPasswordLink);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the placeholder text of email field.
     */
    public String getEmailPlaceholder() {
        try {
            return elementUtils.getAttribute(LoginPageLocators.EMAIL_INPUT, "placeholder");
        } catch (Exception e) {
            return emailInput.getAttribute("placeholder");
        }
    }

    /**
     * Gets the placeholder text of password field.
     */
    public String getPasswordPlaceholder() {
        try {
            return elementUtils.getAttribute(LoginPageLocators.PASSWORD_INPUT, "placeholder");
        } catch (Exception e) {
            return passwordInput.getAttribute("placeholder");
        }
    }
}
