package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Utils.ConfigReader;
import Utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.*;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

/**
 * BaseTest class - Contains common setup and teardown methods
 * All test classes should extend this class
 *
 * @author Sterna QA Team
 * @version 1.0
 */
public class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    protected static ExtentReports extentReports;
    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    protected ConfigReader configReader;

    /**
     * Initialize Extent Reports before test suite
     */
    @BeforeSuite
    public void setupExtentReports() {
        logger.info("Setting up Extent Reports");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                ConfigReader.getProperty("extent.report.path")
        );

        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Sterna Automation Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "Sterna AMS");
        extentReports.setSystemInfo("Environment", ConfigReader.getProperty("environment"));
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Tester", System.getProperty("user.name"));
    }

    /**
     * Setup WebDriver once per test class (single browser instance for all methods)
     */
    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        logger.info("Setting up WebDriver for browser: " + browser);

        configReader = new Utils.ConfigReader();
        String browserName = browser != null ? browser : ConfigReader.getProperty("browser");

        WebDriver webDriver = initializeDriver(browserName);
        driver.set(webDriver);

        // Configure timeouts
        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait")))
        );
        getDriver().manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("page.load.timeout")))
        );

        // Maximize window
        getDriver().manage().window().maximize();

        logger.info("WebDriver setup completed successfully");
    }

    /**
     * Initialize WebDriver based on browser type
     */
    private WebDriver initializeDriver(String browser) {
        WebDriver webDriver;
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--start-maximized");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                webDriver = new EdgeDriver(edgeOptions);
                break;

            default:
                logger.warn("Browser not supported. Defaulting to Chrome");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
        }
        return webDriver;
    }

    /**
     * Create extent test for each test method
     */
    @BeforeMethod
    public void createTest(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        logger.info("Starting test: " + result.getMethod().getMethodName());
    }

    /**
     * Get current WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Get current ExtentTest instance
     */
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    /**
     * Navigate to application URL
     */
    public void navigateToApp() {
        String url = ConfigReader.getProperty("app.url");
        logger.info("Navigating to URL: " + url);
        getDriver().get(url);
        getExtentTest().log(Status.INFO, "Navigated to: " + url);
    }

    /**
     * Log test results after each test method (keeps reporting per-method)
     */
    @AfterMethod
    public void logTestResult(ITestResult result) {
        logger.info("Completed test: " + result.getMethod().getMethodName());

        if (result.getStatus() == ITestResult.FAILURE) {
            getExtentTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());

            // Capture screenshot on failure
            if (Boolean.parseBoolean(ConfigReader.getProperty("screenshot.on.failure"))) {
                try {
                    String screenshotPath = ScreenshotUtil.captureScreenshot(
                            getDriver(),
                            result.getMethod().getMethodName()
                    );
                    getExtentTest().addScreenCaptureFromPath(screenshotPath);
                    logger.info("Screenshot captured: " + screenshotPath);
                } catch (IOException e) {
                    logger.error("Failed to capture screenshot: " + e.getMessage());
                }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            getExtentTest().log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            getExtentTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }
    }

    /**
     * Teardown WebDriver after all tests in the class complete
     */
    @AfterClass
    public void teardown() {
        logger.info("Tearing down WebDriver for test class");
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            logger.info("WebDriver closed successfully");
        }
    }

    /**
     * Flush extent reports after test suite
     */
    @AfterSuite
    public void tearDownExtentReports() {
        if (extentReports != null) {
            extentReports.flush();
            logger.info("Extent Reports flushed successfully");
        }
    }
}
