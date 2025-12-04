package com.sterna.ams.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Element Utilities class providing common element interaction methods.
 */
public class ElementUtils {

    private static final Logger logger = LogManager.getLogger(ElementUtils.class);
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Clicks on an element after waiting for it to be clickable.
     */
    public void click(By locator) {
        logger.debug("Clicking element: {}", locator);
        WebElement element = waitUtils.waitForElementClickable(locator);
        element.click();
    }

    public void click(WebElement element) {
        logger.debug("Clicking element");
        waitUtils.waitForElementClickable(element).click();
    }

    /**
     * Clicks on element using JavaScript (useful for hidden/overlapped elements).
     */
    public void clickJS(By locator) {
        logger.debug("Clicking element using JavaScript: {}", locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickJS(WebElement element) {
        logger.debug("Clicking element using JavaScript");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Enters text into an input field after clearing it.
     */
    public void enterText(By locator, String text) {
        logger.debug("Entering text '{}' into element: {}", text, locator);
        WebElement element = waitUtils.waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void enterText(WebElement element, String text) {
        logger.debug("Entering text '{}' into element", text);
        waitUtils.waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Enters text using JavaScript (useful for special input fields).
     */
    public void enterTextJS(By locator, String text) {
        logger.debug("Entering text using JavaScript: {}", locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, text);
    }

    /**
     * Clears the text from an input field.
     */
    public void clearText(By locator) {
        logger.debug("Clearing text from element: {}", locator);
        WebElement element = waitUtils.waitForElementVisible(locator);
        element.clear();
    }

    /**
     * Gets the text content of an element.
     */
    public String getText(By locator) {
        logger.debug("Getting text from element: {}", locator);
        WebElement element = waitUtils.waitForElementVisible(locator);
        return element.getText();
    }

    public String getText(WebElement element) {
        logger.debug("Getting text from element");
        waitUtils.waitForElementVisible(element);
        return element.getText();
    }

    /**
     * Gets the value attribute of an element.
     */
    public String getValue(By locator) {
        logger.debug("Getting value from element: {}", locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        return element.getAttribute("value");
    }

    /**
     * Gets an attribute value from an element.
     */
    public String getAttribute(By locator, String attributeName) {
        logger.debug("Getting attribute '{}' from element: {}", attributeName, locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        return element.getAttribute(attributeName);
    }

    /**
     * Checks if an element is displayed.
     */
    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Checks if an element is enabled.
     */
    public boolean isEnabled(By locator) {
        logger.debug("Checking if element is enabled: {}", locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        return element.isEnabled();
    }

    /**
     * Checks if an element is selected (for checkboxes/radio buttons).
     */
    public boolean isSelected(By locator) {
        logger.debug("Checking if element is selected: {}", locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        return element.isSelected();
    }

    /**
     * Scrolls element into view.
     */
    public void scrollToElement(By locator) {
        logger.debug("Scrolling to element: {}", locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElement(WebElement element) {
        logger.debug("Scrolling to element");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Hovers over an element.
     */
    public void hoverOver(By locator) {
        logger.debug("Hovering over element: {}", locator);
        WebElement element = waitUtils.waitForElementVisible(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Double clicks on an element.
     */
    public void doubleClick(By locator) {
        logger.debug("Double clicking element: {}", locator);
        WebElement element = waitUtils.waitForElementClickable(locator);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    /**
     * Right clicks on an element.
     */
    public void rightClick(By locator) {
        logger.debug("Right clicking element: {}", locator);
        WebElement element = waitUtils.waitForElementClickable(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    /**
     * Selects dropdown option by visible text.
     */
    public void selectByVisibleText(By locator, String text) {
        logger.debug("Selecting '{}' from dropdown: {}", text, locator);
        WebElement element = waitUtils.waitForElementVisible(locator);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * Selects dropdown option by value.
     */
    public void selectByValue(By locator, String value) {
        logger.debug("Selecting value '{}' from dropdown: {}", value, locator);
        WebElement element = waitUtils.waitForElementVisible(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    /**
     * Selects dropdown option by index.
     */
    public void selectByIndex(By locator, int index) {
        logger.debug("Selecting index '{}' from dropdown: {}", index, locator);
        WebElement element = waitUtils.waitForElementVisible(locator);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    /**
     * Sends keyboard keys to an element.
     */
    public void sendKeys(By locator, Keys... keys) {
        logger.debug("Sending keys to element: {}", locator);
        WebElement element = waitUtils.waitForElementPresent(locator);
        element.sendKeys(keys);
    }

    /**
     * Highlights an element (useful for debugging).
     */
    public void highlightElement(By locator) {
        WebElement element = waitUtils.waitForElementPresent(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**
     * Gets all elements matching the locator.
     */
    public List<WebElement> getElements(By locator) {
        logger.debug("Getting all elements: {}", locator);
        return driver.findElements(locator);
    }

    /**
     * Gets the count of elements matching the locator.
     */
    public int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }
}
