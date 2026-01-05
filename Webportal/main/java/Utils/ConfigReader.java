package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Utility class for reading configuration properties
 * Provides centralized access to test configuration and test data
 *
 * @author Sterna QA Team
 * @version 2.0
 */
public class ConfigReader {

    private static Properties properties;
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fis);
            fis.close();
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties: " + e.getMessage());
            throw new RuntimeException("Configuration file not found at: " + CONFIG_FILE_PATH);
        }
    }

    /**
     * Get property value by key
     *
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found for key: " + key);
        }
        return value;
    }

    /**
     * Get property value with default fallback
     *
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url");
    }

    /**
     * Get valid email for testing
     */
    public static String getValidEmail() {
        return getProperty("valid.email");
    }

    /**
     * Get valid password for testing
     */
    public static String getValidPassword() {
        return getProperty("valid.password");
    }

    public static String getDashboardValidEmail() {
        return getProperty("dashboardvalid.email");
    }

    public static String getRoleValidEmail() {
        return getProperty("rolevalid.email");
    }

    public static String getUserValidEmail() {
        return getProperty("uservalid.email");
    }

    public static String getActivityValidEmail() {
        return getProperty("activityvalid.email");
    }

    public static String getDeviceActivityValidEmail() {
        return getProperty("devicevalid.email");
    }

    public static String getLevelValidEmail() {
        return getProperty("levelvalid.email");
    }

    public static String getkeyboxValidEmail() {
        return getProperty("keyboxvalid.email");
    }

    public static String getkeyPositionValidEmail() {
        return getProperty("keyboxposition.email");
    }







    /**
     * Get invalid email for testing
     */
    public static String getInvalidEmail() {
        return getProperty("invalid.email");
    }

    /**
     * Get invalid password for testing
     */
    public static String getInvalidPassword() {
        return getProperty("invalid.password");
    }

    /**
     * Get inactive user email for testing
     */
    public static String getInactiveEmail() {
        return getProperty("inactive.email");
    }

    /**
     * Get inactive user password for testing
     */
    public static String getInactivePassword() {
        return getProperty("inactive.password");
    }

    /**
     * Get implicit wait timeout
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    /**
     * Get explicit wait timeout
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "20"));
    }

    /**
     * Get page load timeout
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "30"));
    }

    /**
     * Get browser type
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Check if headless mode is enabled
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
}
