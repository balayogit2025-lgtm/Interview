package com.sterna.ams.base;

import com.sterna.ams.config.ConfigReader;
import com.sterna.ams.driver.DriverManager;
import com.sterna.ams.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Base Test class containing common setup and teardown methods.
 * All test classes should extend this class.
 */
public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeSuite
    public void beforeSuite() {
        logger.info("========================================");
        logger.info("Starting Test Suite Execution");
        logger.info("========================================");
    }

    @BeforeClass
    public void beforeClass() {
        logger.info("Initializing test class: {}", this.getClass().getSimpleName());
        config = ConfigReader.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        logger.info("----------------------------------------");
        logger.info("Setting up test method");

        // Initialize WebDriver
        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();

        // Navigate to application URL
        String appUrl = config.getAppUrl();
        logger.info("Navigating to application URL: {}", appUrl);
        driver.get(appUrl);

        logger.info("Browser launched and navigated to application");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        logger.info("Tearing down test method: {}", result.getName());

        // Capture screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test FAILED: {}", result.getName());
            logger.error("Failure reason: {}", result.getThrowable() != null ?
                    result.getThrowable().getMessage() : "Unknown");

            if (driver != null) {
                String screenshotPath = ScreenshotUtils.captureFailureScreenshot(driver, result.getName());
                if (screenshotPath != null) {
                    logger.info("Failure screenshot saved: {}", screenshotPath);
                }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test PASSED: {}", result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test SKIPPED: {}", result.getName());
        }

        // Quit WebDriver
        DriverManager.quitDriver();
        logger.info("Browser closed");
        logger.info("----------------------------------------");
    }

    @AfterClass
    public void afterClass() {
        logger.info("Completed test class: {}", this.getClass().getSimpleName());
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("========================================");
        logger.info("Test Suite Execution Completed");
        logger.info("========================================");
    }

    /**
     * Gets the current WebDriver instance.
     */
    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * Gets the ConfigReader instance.
     */
    protected ConfigReader getConfig() {
        return config;
    }

    /**
     * Navigates to the application URL.
     */
    protected void navigateToApp() {
        driver.get(config.getAppUrl());
    }

    /**
     * Captures a screenshot with the specified name.
     */
    protected String captureScreenshot(String screenshotName) {
        return ScreenshotUtils.captureScreenshot(driver, screenshotName);
    }
}
