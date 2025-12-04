package utils;

import config.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot Utilities class for capturing screenshots during test execution.
 */
public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final ConfigReader config = ConfigReader.getInstance();

    private ScreenshotUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Captures a screenshot and saves it with the specified name.
     *
     * @param driver         WebDriver instance
     * @param screenshotName Name for the screenshot file
     * @return Path to the saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String screenshotPath = config.getScreenshotPath() + fileName;

        try {
            // Create directory if it doesn't exist
            File screenshotDir = new File(config.getScreenshotPath());
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Take screenshot
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath);
            FileUtils.copyFile(sourceFile, destinationFile);

            logger.info("Screenshot captured: {}", screenshotPath);
            return screenshotPath;

        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Captures a screenshot as Base64 string (useful for reports).
     *
     * @param driver WebDriver instance
     * @return Base64 encoded screenshot string
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            logger.debug("Screenshot captured as Base64");
            return base64Screenshot;
        } catch (Exception e) {
            logger.error("Failed to capture Base64 screenshot: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Captures a screenshot on test failure.
     *
     * @param driver   WebDriver instance
     * @param testName Name of the failed test
     * @return Path to the saved screenshot
     */
    public static String captureFailureScreenshot(WebDriver driver, String testName) {
        if (config.isScreenshotOnFailure()) {
            return captureScreenshot(driver, "FAILED_" + testName);
        }
        return null;
    }
}
