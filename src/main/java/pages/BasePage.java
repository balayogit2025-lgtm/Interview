package pages;

import utils.ElementUtils;
import utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Base Page class containing common methods and utilities for all page objects.
 * All page classes should extend this class.
 */
public abstract class BasePage {

    protected final Logger logger;
    protected final WebDriver driver;
    protected final WaitUtils waitUtils;
    protected final ElementUtils elementUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.logger = LogManager.getLogger(this.getClass());
        this.waitUtils = new WaitUtils(driver);
        this.elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the current page URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Gets the current page title.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Navigates to a specific URL.
     */
    public void navigateTo(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
        waitUtils.waitForPageLoad();
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
        waitUtils.waitForPageLoad();
    }

    /**
     * Navigates back in browser history.
     */
    public void navigateBack() {
        logger.info("Navigating back");
        driver.navigate().back();
        waitUtils.waitForPageLoad();
    }

    /**
     * Navigates forward in browser history.
     */
    public void navigateForward() {
        logger.info("Navigating forward");
        driver.navigate().forward();
        waitUtils.waitForPageLoad();
    }

    /**
     * Clicks on an element.
     */
    protected void click(By locator) {
        elementUtils.click(locator);
    }

    protected void click(WebElement element) {
        elementUtils.click(element);
    }

    /**
     * Enters text into a field.
     */
    protected void enterText(By locator, String text) {
        elementUtils.enterText(locator, text);
    }

    protected void enterText(WebElement element, String text) {
        elementUtils.enterText(element, text);
    }

    /**
     * Gets text from an element.
     */
    protected String getText(By locator) {
        return elementUtils.getText(locator);
    }

    protected String getText(WebElement element) {
        return elementUtils.getText(element);
    }

    /**
     * Checks if element is displayed.
     */
    protected boolean isDisplayed(By locator) {
        return elementUtils.isDisplayed(locator);
    }

    protected boolean isDisplayed(WebElement element) {
        return elementUtils.isDisplayed(element);
    }

    /**
     * Checks if element is enabled.
     */
    protected boolean isEnabled(By locator) {
        return elementUtils.isEnabled(locator);
    }

    /**
     * Waits for element to be visible.
     */
    protected WebElement waitForVisible(By locator) {
        return waitUtils.waitForElementVisible(locator);
    }

    protected WebElement waitForVisible(WebElement element) {
        return waitUtils.waitForElementVisible(element);
    }

    /**
     * Waits for element to be clickable.
     */
    protected WebElement waitForClickable(By locator) {
        return waitUtils.waitForElementClickable(locator);
    }

    /**
     * Waits for URL to contain specific text.
     */
    protected boolean waitForUrlContains(String urlPart) {
        return waitUtils.waitForUrlContains(urlPart);
    }

    /**
     * Waits for page to load completely.
     */
    protected void waitForPageLoad() {
        waitUtils.waitForPageLoad();
    }

    /**
     * Abstract method to verify page is loaded - must be implemented by each page.
     */
    public abstract boolean isPageLoaded();
}
