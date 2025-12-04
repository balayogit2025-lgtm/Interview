package com.sterna.ams.utils;

import com.sterna.ams.config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

/**
 * Wait Utilities class providing various wait mechanisms for stable test execution.
 */
public class WaitUtils {

    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private final WebDriver driver;
    private final int defaultTimeout;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.defaultTimeout = ConfigReader.getInstance().getExplicitWait();
    }

    private WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    private WebDriverWait getWait() {
        return getWait(defaultTimeout);
    }

    // Wait for element to be visible
    public WebElement waitForElementVisible(By locator) {
        logger.debug("Waiting for element to be visible: {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be visible (timeout: {}s): {}", timeoutInSeconds, locator);
        return getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementVisible(WebElement element) {
        logger.debug("Waiting for element to be visible");
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be clickable
    public WebElement waitForElementClickable(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be clickable (timeout: {}s): {}", timeoutInSeconds, locator);
        return getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementClickable(WebElement element) {
        logger.debug("Waiting for element to be clickable");
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for element to be present in DOM
    public WebElement waitForElementPresent(By locator) {
        logger.debug("Waiting for element to be present: {}", locator);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementPresent(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be present (timeout: {}s): {}", timeoutInSeconds, locator);
        return getWait(timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to disappear
    public boolean waitForElementInvisible(By locator) {
        logger.debug("Waiting for element to be invisible: {}", locator);
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitForElementInvisible(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be invisible (timeout: {}s): {}", timeoutInSeconds, locator);
        return getWait(timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait for all elements to be visible
    public List<WebElement> waitForAllElementsVisible(By locator) {
        logger.debug("Waiting for all elements to be visible: {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // Wait for URL to contain specific text
    public boolean waitForUrlContains(String urlPart) {
        logger.debug("Waiting for URL to contain: {}", urlPart);
        return getWait().until(ExpectedConditions.urlContains(urlPart));
    }

    public boolean waitForUrlContains(String urlPart, int timeoutInSeconds) {
        logger.debug("Waiting for URL to contain (timeout: {}s): {}", timeoutInSeconds, urlPart);
        return getWait(timeoutInSeconds).until(ExpectedConditions.urlContains(urlPart));
    }

    // Wait for URL to match exactly
    public boolean waitForUrlToBe(String url) {
        logger.debug("Waiting for URL to be: {}", url);
        return getWait().until(ExpectedConditions.urlToBe(url));
    }

    // Wait for title to contain
    public boolean waitForTitleContains(String title) {
        logger.debug("Waiting for title to contain: {}", title);
        return getWait().until(ExpectedConditions.titleContains(title));
    }

    // Wait for text to be present in element
    public boolean waitForTextPresent(By locator, String text) {
        logger.debug("Waiting for text '{}' in element: {}", text, locator);
        return getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public boolean waitForTextPresent(WebElement element, String text) {
        logger.debug("Waiting for text '{}' in element", text);
        return getWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // Wait for attribute to contain value
    public boolean waitForAttributeContains(By locator, String attribute, String value) {
        logger.debug("Waiting for attribute '{}' to contain '{}': {}", attribute, value, locator);
        return getWait().until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    // Fluent wait for custom conditions
    public <T> T fluentWait(Function<WebDriver, T> condition, int timeoutInSeconds, int pollingIntervalMs) {
        logger.debug("Executing fluent wait (timeout: {}s, polling: {}ms)", timeoutInSeconds, pollingIntervalMs);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalMs))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(condition);
    }

    // Wait for page to load completely
    public void waitForPageLoad() {
        logger.debug("Waiting for page to load completely");
        getWait().until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    // Wait for Ajax calls to complete (jQuery)
    public void waitForAjaxComplete() {
        logger.debug("Waiting for Ajax calls to complete");
        try {
            getWait().until(driver -> {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            });
        } catch (Exception e) {
            logger.debug("jQuery not found or Ajax wait not applicable");
        }
    }

    // Check if element is displayed without waiting
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    // Check if element is present in DOM
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Wait for element and check if displayed
    public boolean waitAndCheckElementDisplayed(By locator, int timeoutInSeconds) {
        try {
            waitForElementVisible(locator, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Static sleep (use sparingly)
    public static void sleep(long milliseconds) {
        logger.debug("Static sleep for {} ms", milliseconds);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Sleep interrupted", e);
        }
    }
}
