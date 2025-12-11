package Elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPageElements - Contains all web elements/locators for Login Page
 * This class only holds element definitions, no business logic
 *
 * @author Sterna QA Team
 * @version 2.0
 */
public class LoginPageElements {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LoginPageElements.class);

    // ==================== Page Factory Locators ====================

    /**
     * Email/Username input field
     * Locator Strategy: Multiple attributes to handle different implementations
     */
    @FindBy(xpath = "//input[@type='email' or @type='text' or @name='email' or @name='username' or @id='email' or @id='username']")
    protected WebElement emailField;

    /**
     * Password input field
     * Locator Strategy: Type and name attributes
     */
    @FindBy(xpath = "//input[@type='password' or @name='password' or @id='password']")
    protected WebElement passwordField;

    /**
     * Login/Submit button
     * Locator Strategy: Button type and text content
     */
    @FindBy(xpath = "//button[text()='Login']")
    protected WebElement loginButton;

    /**
     * Forgot Password link
     * Locator Strategy: Link text content
     */
    @FindBy(xpath = "//a[contains(text(), 'Forgot') or contains(text(), 'forgot') or contains(@href, 'forgot')]")
    protected WebElement forgotPasswordLink;

    /**
     * Error/Alert message container
     * Locator Strategy: Class names commonly used for error messages
     */
    @FindBy(xpath = "//ol/li/div/div[contains(@class, 'toast')]")
    protected WebElement errorMessage;

    /**
     * Sign Up/Register link
     * Locator Strategy: Link text content
     */
    @FindBy(xpath = "//a[contains(text(), 'Sign up') or contains(text(), 'Register') or contains(text(), 'register') or contains(@href, 'register')]")
    protected WebElement signUpLink;

    /**
     * Remember Me checkbox (optional)
     * Locator Strategy: Checkbox type and related text
     */
    @FindBy(xpath = "//input[@type='checkbox' and (contains(@name, 'remember') or contains(@id, 'remember'))]")
    protected WebElement rememberMeCheckbox;

    /**
     * Show/Hide Password toggle button (optional)
     * Locator Strategy: Button or icon near password field
     */
    @FindBy(xpath = "//button[contains(@class, 'show-password') or contains(@class, 'toggle-password')] | //i[contains(@class, 'eye')]")
    protected WebElement showPasswordToggle;

    /**
     * Login form container
     * Locator Strategy: Form element or login container div
     */
    @FindBy(xpath = "//form[contains(@class, 'login') or contains(@id, 'login')] | //div[contains(@class, 'login-form')]")
    protected WebElement loginForm;

    /**
     * Page header/title
     * Locator Strategy: H1 or main heading element
     */
    @FindBy(xpath = "//h1 | //h2[contains(@class, 'title')] | //*[contains(@class, 'heading')]")
    protected WebElement pageHeader;

    // ==================== By Locators (Alternative approach) ====================

    /**
     * Email field locator (By)
     */
    protected By emailFieldLocator = By.xpath("//input[@type='email' or @type='text' or @name='email' or @name='username']");

    /**
     * Password field locator (By)
     */
    protected By passwordFieldLocator = By.xpath("//input[@type='password' or @name='password']");

    /**
     * Login button locator (By)
     */
    protected By loginButtonLocator = By.xpath("//button[text()=\"Login\"]");

    /**
     * Error message locator (By)
     */
    protected By errorMessageLocator = By.xpath("//*[contains(@class, 'error') or contains(@class, 'alert') or contains(@class, 'message')]");

    /**
     * Forgot password link locator (By)
     */
    protected By forgotPasswordLinkLocator = By.xpath("//a[contains(text(), 'Forgot') or contains(text(), 'forgot')]");

    /**
     * Sign up link locator (By)
     */
    protected By signUpLinkLocator = By.xpath("//a[contains(text(), 'Sign up') or contains(text(), 'Register')]");

    /**
     * Remember me checkbox locator (By)
     */
    protected By rememberMeCheckboxLocator = By.xpath("//input[@type='checkbox' and (contains(@name, 'remember') or contains(@id, 'remember'))]");

    /**
     * Login form locator (By)
     */
    protected By loginFormLocator = By.xpath("//form[contains(@class, 'login') or contains(@id, 'login')]");

    // ==================== CSS Selectors (Alternative locator strategy) ====================

    /**
     * Email field CSS selector
     */
    protected By emailFieldCSS = By.cssSelector("input[type='email'], input[name='email'], input[id='email']");

    /**
     * Password field CSS selector
     */
    protected By passwordFieldCSS = By.cssSelector("input[type='password'], input[name='password']");

    /**
     * Login button CSS selector
     */
    protected By loginButtonCSS = By.cssSelector("button[type='submit'], button.login-btn, input[type='submit']");

    /**
     * Error message CSS selector
     */
    protected By errorMessageCSS = By.cssSelector(".error, .alert, .error-message, .alert-danger");

    // ==================== ID-based Locators (Most stable) ====================

    /**
     * Email field by ID (if available)
     */
    protected By emailFieldById = By.id("email");

    /**
     * Password field by ID (if available)
     */
    protected By passwordFieldById = By.id("password");

    /**
     * Login button by ID (if available)
     */
    protected By loginButtonById = By.id("loginButton");

    // ==================== Name-based Locators ====================

    /**
     * Email field by name attribute
     */
    protected By emailFieldByName = By.name("email");

    /**
     * Password field by name attribute
     */
    protected By passwordFieldByName = By.name("password");


    public By getLoginButtonCSS() {
        return loginButtonCSS;
    }

    public By activityPage = By.xpath("//span[text()='Activity']");

    @FindBy(xpath = "//div[.='Login successful']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(), 'email')]")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//p[contains(text(), 'Password')]")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = "//a/span[text()='Location']")
    private WebElement locationMenu;

    public WebElement getDashboardMenu() {
        return dashboardMenu;
    }

    @FindBy(xpath = "//a/span[text()='Dashboard']")
    private WebElement dashboardMenu;

    public WebElement getDeparmentMenu() {
        return deparmentMenu;
    }

    @FindBy(xpath = "//a/span[text()='Department']")
    private WebElement deparmentMenu;

    public WebElement getEmployeetMenu() {
        return employeetMenu;
    }

    @FindBy(xpath = "//a/span[text()='Employee']")
    private WebElement employeetMenu;

    public WebElement getReportsMenu() {
        return reportsMenu;
    }

    @FindBy(xpath = "//a/span[text()='Reports']")
    private WebElement reportsMenu;

    public WebElement getEventsMenu() {
        return eventsMenu;
    }

    @FindBy(xpath = "//span[text()='Events']")
    private WebElement eventsMenu;

    public WebElement getControllerMenu() {
        return controllerMenu;
    }

    @FindBy(xpath = "//a/span[text()='Controller']")
    private WebElement controllerMenu;

    public WebElement getAttendanceMenu() {
        return attendanceMenu;
    }

    @FindBy(xpath = "//a/span[text()='Attendance']")
    private WebElement attendanceMenu;

    public WebElement getCompanyMenu() {
        return companyMenu;
    }

    @FindBy(xpath = "//a/span[text()='Company']")
    private WebElement companyMenu;

    // ==================== Constructor ====================

    /**
     * Constructor to initialize page elements
     * @param driver WebDriver instance
     */
    public LoginPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("LoginPageElements initialized");
    }

    // ==================== Getter Methods for WebElements ====================

    /**
     * Get email field element
     * @return WebElement for email field
     */
    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getLocationMenu() {
        return locationMenu;
    }

    /**
     * Get password field element
     * @return WebElement for password field
     */
    public WebElement getPasswordField() {
        return passwordField;
    }

    /**
     * Get login button element
     * @return WebElement for login button
     */
    public WebElement getLoginButton() {
        return loginButton;
    }

    /**
     * Get forgot password link element
     * @return WebElement for forgot password link
     */
    public WebElement getForgotPasswordLink() {
        return forgotPasswordLink;
    }

    /**
     * Get error message element
     * @return WebElement for error message
     */
    public WebElement getErrorMessage() {
        return errorMessage;
    }

    /**
     * Get sign up link element
     * @return WebElement for sign up link
     */
    public WebElement getSignUpLink() {
        return signUpLink;
    }

    /**
     * Get remember me checkbox element
     * @return WebElement for remember me checkbox
     */
    public WebElement getRememberMeCheckbox() {
        return rememberMeCheckbox;
    }

    /**
     * Get show password toggle element
     * @return WebElement for show password toggle
     */
    public WebElement getShowPasswordToggle() {
        return showPasswordToggle;
    }

    /**
     * Get login form element
     * @return WebElement for login form
     */
    public WebElement getLoginForm() {
        return loginForm;
    }

    /**
     * Get page header element
     * @return WebElement for page header
     */
    public WebElement getPageHeader() {
        return pageHeader;
    }

    // ==================== Getter Methods for By Locators ====================

    /**
     * Get email field By locator
     * @return By locator for email field
     */
    public By getEmailFieldLocator() {
        return emailFieldLocator;
    }

    /**
     * Get password field By locator
     * @return By locator for password field
     */
    public By getPasswordFieldLocator() {
        return passwordFieldLocator;
    }

    /**
     * Get login button By locator
     * @return By locator for login button
     */
    public By getLoginButtonLocator() {
        return loginButtonLocator;
    }

    /**
     * Get error message By locator
     * @return By locator for error message
     */
    public By getErrorMessageLocator() {
        return errorMessageLocator;
    }

    /**
     * Get forgot password link By locator
     * @return By locator for forgot password link
     */
    public By getForgotPasswordLinkLocator() {
        return forgotPasswordLinkLocator;
    }

    /**
     * Get WebDriver instance
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getEmailErrorMessage() {
        return emailErrorMessage;
    }

    public WebElement getPasswordErrorMessage() {
        return passwordErrorMessage;
    }


}

