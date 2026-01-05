package testrun;

import pages.LoginApi;
import utils.ConfigReader;
import utils.TokenManager;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * Base test class that provides common setup and teardown for all test classes.
 */
public class BaseTest {

    protected LoginApi loginApi;

    @BeforeSuite
    public void globalSetup() {
        System.out.println("========== Starting Test Suite ==========");
        System.out.println("Base URL: " + ConfigReader.getBaseUrl());

        // Configure RestAssured global settings
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeClass
    public void setUp() {
        System.out.println("Setting up test class: " + this.getClass().getSimpleName());
        loginApi = new LoginApi();
    }

    /**
     * Ensure user is logged in before running tests that require authentication.
     * Call this method in @BeforeClass of test classes that need authentication.
     */
    protected void ensureLoggedIn() {
        if (!TokenManager.hasToken()) {
            System.out.println("No token found, performing login...");
            loginApi.loginAndStoreToken();
        } else {
            System.out.println("Token already available, skipping login");
        }
    }

    @AfterSuite
    public void globalTearDown() {
        // Clear token after all tests
        TokenManager.clearToken();
        System.out.println("========== Test Suite Completed ==========");
    }
}
