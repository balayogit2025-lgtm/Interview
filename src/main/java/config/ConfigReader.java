package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Reader utility class to read properties from config file.
 * Implements singleton pattern for efficient resource management.
 */
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static ConfigReader instance;
    private final Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    private ConfigReader() {
        properties = new Properties();
        loadProperties();
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
            logger.info("Configuration properties loaded successfully from: {}", CONFIG_PATH);
        } catch (IOException e) {
            logger.error("Failed to load configuration properties: {}", e.getMessage());
            throw new RuntimeException("Could not load config.properties file", e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found in configuration", key);
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // Application Configuration
    public String getAppUrl() {
        return getProperty("app.url");
    }

    public String getAppName() {
        return getProperty("app.name");
    }

    // Browser Configuration
    public String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    // Timeout Configuration
    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "20"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "30"));
    }

    // Test Data - Valid Credentials
    public String getValidUsername() {
        return getProperty("valid.username");
    }

    public String getValidPassword() {
        return getProperty("valid.password");
    }

    // Test Data - Invalid Credentials
    public String getInvalidUsername() {
        return getProperty("invalid.username");
    }

    public String getInvalidPassword() {
        return getProperty("invalid.password");
    }

    // Test Data - Invalid Credential Scenarios
    public String getInvalidEmailFormat() {
        return getProperty("test.invalid.email.format", "notanemail");
    }

    public String getShortPassword() {
        return getProperty("test.short.password", "short");
    }

    public String getWrongEmail() {
        return getProperty("test.wrong.email", "wrong@email.com");
    }

    public String getWrongPassword() {
        return getProperty("test.wrong.password", "wrongpassword");
    }

    // Test Data - SQL Injection Payloads
    public String getSqlInjectionEmail1() {
        return getProperty("test.sql.injection.email1", "admin'--");
    }

    public String getSqlInjectionEmail2() {
        return getProperty("test.sql.injection.email2", "' OR '1'='1");
    }

    public String getSqlInjectionEmail3() {
        return getProperty("test.sql.injection.email3", "'; DROP TABLE users; --");
    }

    public String getSqlInjectionPassword() {
        return getProperty("test.sql.injection.password", "' OR '1'='1");
    }

    // Test Data - Special Characters
    public String getSpecialCharsPassword() {
        return getProperty("test.special.chars.password", "!@#$%^&*()_+");
    }

    // Environment
    public String getEnvironment() {
        return getProperty("environment", "qa");
    }

    // Screenshot Configuration
    public boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshot.on.failure", "true"));
    }

    public String getScreenshotPath() {
        return getProperty("screenshot.path", "./test-output/screenshots/");
    }
}
