package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil - Utility class for capturing screenshots
 * Used for capturing screenshots on test failure and for reporting
 *
 * @author Sterna QA Team
 * @version 2.0
 */
public class ScreenshotUtil {

    /**
     * Capture screenshot with timestamp
     *
     * @param driver WebDriver instance
     * @param testName Test method name
     * @return Screenshot file path
     * @throws IOException If screenshot cannot be saved
     */
    public static String captureScreenshot(WebDriver driver, String testName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String screenshotPath = ConfigReader.getProperty("screenshot.path") + fileName;

        // Create screenshot directory if not exists
        File directory = new File(ConfigReader.getProperty("screenshot.path"));
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Capture screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotPath);
        FileUtils.copyFile(source, destination);

        return screenshotPath;
    }

    /**
     * Capture screenshot with custom file name
     *
     * @param driver WebDriver instance
     * @param fileName Custom file name
     * @return Screenshot file path
     * @throws IOException If screenshot cannot be saved
     */
    public static String captureScreenshotWithName(WebDriver driver, String fileName) throws IOException {
        String screenshotPath = ConfigReader.getProperty("screenshot.path") + fileName + ".png";

        File directory = new File(ConfigReader.getProperty("screenshot.path"));
        if (!directory.exists()) {
            directory.mkdirs();
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotPath);
        FileUtils.copyFile(source, destination);
        return screenshotPath;
    }

}
