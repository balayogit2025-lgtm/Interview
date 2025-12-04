package pages;

import locators.DashboardPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Dashboard Page Object class representing the Sterna AMS Dashboard/Home page after login.
 * Contains all page elements and methods for dashboard functionality.
 */
public class DashboardPage extends BasePage {

    // ==================== Page Elements (using @FindBy) ====================

    @FindBy(xpath = DashboardPageLocators.DASHBOARD_HEADER_XPATH)
    private WebElement dashboardHeader;

    @FindBy(xpath = DashboardPageLocators.USER_PROFILE_XPATH)
    private WebElement userProfile;

    @FindBy(xpath = DashboardPageLocators.USERNAME_DISPLAY_XPATH)
    private WebElement usernameDisplay;

    @FindBy(xpath = DashboardPageLocators.LOGOUT_BUTTON_XPATH)
    private WebElement logoutButton;

    @FindBy(xpath = DashboardPageLocators.LOGOUT_ELEMENT_XPATH)
    private WebElement logoutElement;

    @FindBy(xpath = DashboardPageLocators.NAVIGATION_MENU_XPATH)
    private WebElement navigationMenu;

    @FindBy(xpath = DashboardPageLocators.SETTINGS_LINK_XPATH)
    private WebElement settingsLink;

    @FindBy(xpath = DashboardPageLocators.WELCOME_MESSAGE_XPATH)
    private WebElement welcomeMessage;

    @FindBy(xpath = DashboardPageLocators.MAIN_CONTENT_XPATH)
    private WebElement mainContent;

    // ==================== Constructor ====================

    public DashboardPage(WebDriver driver) {
        super(driver);
        logger.info("DashboardPage initialized");
    }

    // ==================== Page Actions ====================

    /**
     * Clicks on the user profile to open the menu.
     */
    public DashboardPage clickUserProfile() {
        logger.info("Clicking user profile");
        try {
            click(DashboardPageLocators.USER_MENU);
        } catch (Exception e) {
            click(userProfile);
        }
        return this;
    }

    /**
     * Performs logout action.
     * @return LoginPage after successful logout
     */
    public LoginPage logout() {
        logger.info("Performing logout");
        try {
            // Try to click user menu first if logout is in dropdown
            try {
                click(DashboardPageLocators.USER_MENU);
                waitUtils.sleep(500);
            } catch (Exception ignored) {
                // Continue even if no user menu dropdown
            }

            // Click logout
            waitForClickable(DashboardPageLocators.LOGOUT);
            click(DashboardPageLocators.LOGOUT);
        } catch (Exception e) {
            click(logoutButton);
        }

        waitUtils.waitForPageLoad();
        return new LoginPage(driver);
    }

    /**
     * Clicks on settings link.
     */
    public void clickSettings() {
        logger.info("Clicking Settings");
        click(settingsLink);
    }

    /**
     * Navigates to a menu item by text.
     */
    public void navigateToMenuItem(String menuItemText) {
        logger.info("Navigating to menu item: {}", menuItemText);
        By menuItem = By.xpath("//*[contains(text(),'" + menuItemText + "')]");
        click(menuItem);
        waitForPageLoad();
    }

    // ==================== Verification Methods ====================

    /**
     * Checks if the dashboard page is loaded successfully.
     */
    @Override
    public boolean isPageLoaded() {
        logger.info("Verifying dashboard page is loaded");
        try {
            waitUtils.waitForPageLoad();
            // Check for any dashboard indicator
            boolean isDashboard = !getCurrentUrl().toLowerCase().contains("login");
            if (isDashboard) {
                logger.info("Dashboard page loaded - URL does not contain 'login'");
                return true;
            }
            // Try to find dashboard elements
            return waitUtils.waitAndCheckElementDisplayed(DashboardPageLocators.DASHBOARD_INDICATOR, 10);
        } catch (Exception e) {
            logger.error("Dashboard page not loaded properly: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if user has successfully logged in by verifying not on login page.
     */
    public boolean isUserLoggedIn() {
        logger.info("Checking if user is logged in");
        try {
            waitUtils.waitForPageLoad();
            // Give page time to redirect
            waitUtils.sleep(2000);
            String currentUrl = getCurrentUrl();
            boolean notOnLoginPage = !currentUrl.toLowerCase().contains("login");
            logger.info("Current URL: {} - Not on login page: {}", currentUrl, notOnLoginPage);
            return notOnLoginPage;
        } catch (Exception e) {
            logger.error("Error checking login status: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Gets the dashboard page title.
     */
    public String getDashboardTitle() {
        return getPageTitle();
    }

    /**
     * Gets the welcome message text.
     */
    public String getWelcomeMessage() {
        try {
            waitForVisible(welcomeMessage);
            return getText(welcomeMessage);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Gets the displayed username.
     */
    public String getDisplayedUsername() {
        try {
            waitForVisible(usernameDisplay);
            return getText(usernameDisplay);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Checks if navigation menu is displayed.
     */
    public boolean isNavigationMenuDisplayed() {
        try {
            return isDisplayed(navigationMenu);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if logout button is displayed.
     */
    public boolean isLogoutButtonDisplayed() {
        try {
            // Try clicking user menu first to expose logout
            try {
                click(DashboardPageLocators.USER_MENU);
                waitUtils.sleep(500);
            } catch (Exception ignored) {
            }
            return isDisplayed(DashboardPageLocators.LOGOUT);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifies the current URL contains expected text.
     */
    public boolean urlContains(String urlPart) {
        return getCurrentUrl().toLowerCase().contains(urlPart.toLowerCase());
    }

    /**
     * Waits for dashboard to load after login.
     */
    public void waitForDashboardLoad() {
        logger.info("Waiting for dashboard to load");
        waitUtils.waitForPageLoad();
        try {
            // Wait until URL no longer contains 'login'
            waitUtils.fluentWait(
                    driver -> !driver.getCurrentUrl().toLowerCase().contains("login"),
                    15,
                    500
            );
        } catch (Exception e) {
            logger.warn("Timeout waiting for URL to change from login");
        }
    }
}
