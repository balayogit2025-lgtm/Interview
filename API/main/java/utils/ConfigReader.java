package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to read configuration properties from config.properties file
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "API/test/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Load properties from the configuration file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
            System.out.println("Configuration properties loaded successfully");
        } catch (IOException e) {
            System.err.println("Failed to load configuration properties: " + e.getMessage());
            throw new RuntimeException("Could not load config.properties file", e);
        }
    }

    /**
     * Get a property value by key
     * @param key the property key
     * @return the property value, or null if not found
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            System.out.println("WARNING: Property '" + key + "' not found in configuration");
        }
        return value;
    }

    /**
     * Get a property value with a default fallback
     * @param key the property key
     * @param defaultValue the default value if key not found
     * @return the property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get base URL from configuration
     * @return the base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    /**
     * Get username from configuration
     * @return the username
     */
    public static String getUsername() {
        return getProperty("username");
    }

    public static String getInvalidUsername() {
        return getProperty("invalidusername");
    }

    public static String getOTP() {
        return getProperty("otp");
    }

    /**
     * Get password from configuration
     * @return the password
     */
    public static String getPassword() {
        return getProperty("password");
    }

    /**
     * Reload the configuration properties
     */
    public static void reloadProperties() {
        loadProperties();
        System.out.println("Configuration properties reloaded");
    }
}
