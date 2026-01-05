package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WaitHelper - Utility class for handling explicit waits
 * Provides methods for various wait conditions
 *
 * @author Sterna QA Team
 * @version 2.0
 */
public class WaitHelper {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait fastWait;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        int timeout = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        this.wait.pollingEvery(Duration.ofMillis(200));
        this.fastWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.fastWait.pollingEvery(Duration.ofMillis(100));
    }

    /**
     * Wait for element to be visible
     */
    public  WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present
     */
    public WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to be invisible
     */
    public boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for page title to contain text
     */
    public boolean waitForTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Wait for URL to contain text
     */
    public boolean waitForUrlContains(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }

    /**
     * Wait with custom timeout
     */
    public WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        if (timeoutInSeconds <= 3) {
            return fastWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.pollingEvery(Duration.ofMillis(200));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Fast wait for element visible (3 seconds max)
     */
    public WebElement fastWaitForElementVisible(By locator) {
        return fastWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Fast wait for element clickable (3 seconds max)
     */
    public WebElement fastWaitForElementClickable(By locator) {
        return fastWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element with FluentWait (handles stale elements)
     */
    public WebElement waitForElementStable(By locator) {
        return wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    /**
     * Wait for page to be fully loaded using JavaScript
     */
    public void waitForPageReady() {
        fastWait.until(webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return js.executeScript("return document.readyState").equals("complete");
        });
    }

    /**
     * Wait for AJAX calls to complete
     */
    public void waitForAjaxComplete() {
        try {
            fastWait.until(webDriver -> {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return (Boolean) js.executeScript(
                    "return (typeof jQuery === 'undefined' || jQuery.active === 0)");
            });
        } catch (Exception e) {
            // jQuery not present, continue
        }
    }

    /**
     * Short pause for UI animations (replacement for Thread.sleep)
     */
    public void briefPause() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
