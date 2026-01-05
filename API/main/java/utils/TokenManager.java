package utils;

/**
 * Utility class to manage authentication tokens across API tests.
 * Uses thread-local storage for thread safety in parallel test execution.
 */
public class TokenManager {

    // Thread-local storage for tokens to support parallel test execution
    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    // Static token for sequential test execution
    private static String staticToken;

    /**
     * Set the authentication token
     * @param token the JWT or session token
     */
    public static void setToken(String token) {
        tokenHolder.set(token);
        staticToken = token;
        System.out.println("Token has been stored successfully");
    }

    /**
     * Get the authentication token
     * @return the stored token or null if not set
     */
    public static String getToken() {
        String token = tokenHolder.get();
        if (token == null) {
            token = staticToken;
        }
        return token;
    }

    /**
     * Check if a token is currently stored
     * @return true if token exists, false otherwise
     */
    public static boolean hasToken() {
        return getToken() != null && !getToken().isEmpty();
    }

    /**
     * Clear the stored token
     */
    public static void clearToken() {
        tokenHolder.remove();
        staticToken = null;
        System.out.println("Token has been cleared");
    }

    /**
     * Get token with Bearer prefix
     * @return "Bearer {token}" or empty string if no token
     */
    public static String getBearerToken() {
        String token = getToken();
        if (token != null && !token.isEmpty()) {
            return "Bearer " + token;
        }
        return "";
    }

    /**
     * Validate if the token appears to be in correct format
     * @return true if token format seems valid
     */
    public static boolean isTokenValid() {
        String token = getToken();
        if (token == null || token.isEmpty()) {
            return false;
        }
        // Basic JWT format check (three parts separated by dots)
        String[] parts = token.split("\\.");
        return parts.length == 3;
    }
}
