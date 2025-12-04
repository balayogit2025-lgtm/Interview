package listeners;

import driver.DriverManager;
import utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener class for capturing test execution events.
 * Provides logging and screenshot capture on test failures.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("========================================");
        logger.info("Test Suite Started: {}", context.getName());
        logger.info("========================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("========================================");
        logger.info("Test Suite Finished: {}", context.getName());
        logger.info("Total Tests: {}", context.getAllTestMethods().length);
        logger.info("Passed: {}", context.getPassedTests().size());
        logger.info("Failed: {}", context.getFailedTests().size());
        logger.info("Skipped: {}", context.getSkippedTests().size());
        logger.info("========================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("----------------------------------------");
        logger.info("Test Started: {}", result.getName());
        logger.info("Description: {}", result.getMethod().getDescription());
        logger.info("----------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test PASSED: {}", result.getName());
        long duration = result.getEndMillis() - result.getStartMillis();
        logger.info("Duration: {} ms", duration);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test FAILED: {}", result.getName());
        logger.error("Failure Reason: {}", result.getThrowable().getMessage());

        // Capture screenshot on failure
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                String screenshotPath = ScreenshotUtils.captureFailureScreenshot(driver, result.getName());
                if (screenshotPath != null) {
                    logger.info("Failure Screenshot: {}", screenshotPath);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }

        // Log the full stack trace
        logger.error("Stack Trace:", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test SKIPPED: {}", result.getName());
        if (result.getThrowable() != null) {
            logger.warn("Skip Reason: {}", result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("Test Failed but within success percentage: {}", result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.error("Test FAILED due to timeout: {}", result.getName());
        onTestFailure(result);
    }
}
