package pages;

import base.BaseApi;
import constants.EndPoints;
import utils.ConfigReader;
import utils.GmailEmailHelper;
import utils.OutlookEmailHelper;
import utils.OutlookOAuthHelper;
import utils.TokenManager;
import io.restassured.response.Response;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Login/Authentication related API operations.
 * Handles login, logout, token refresh, and password operations.
 */
public class LoginApi extends BaseApi {

    /**
     * Default constructor - no authentication required for login
     */
    public LoginApi() {
        super(false);
    }

    /**
     * Perform login with credentials from config file
     * @return API response
     */
    public Response login() {
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        return login(username, password);
    }

    /**
     * Perform login with specified credentials
     * @param username the username/email
     * @param password the password
     * @return API response
     */
    public Response login(String username, String password) {
        System.out.println("Attempting login for user: " + username);

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", username);
        credentials.put("password", password);

        Response response = getRequestSpec()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(credentials).log().uri()
                .post(EndPoints.LOGIN);


        logResponse(response);
        return response;
    }

    /**
     * Perform login and automatically extract and store the token
     * @param username the username/email
     * @param password the password
     * @return API response
     */
    public Response loginAndStoreToken(String username, String password) {
        Response response = login(username, password);

        if (response.getStatusCode() == 200) {
            extractAndStoreToken(response);
        } else {
            System.out.println("Login failed with status code: " + response.getStatusCode());
        }

        return response;
    }

    /**
     * Perform login with config credentials and store token
     * @return API response
     */
    public Response loginAndStoreToken() {
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        return loginAndStoreToken(username, password);
    }

    /**
     * Extract token from login response and store it
     * @param response the login response
     * @return the extracted token or null
     */
    public String extractAndStoreToken(Response response) {
        String token = null;

        // Get body as string first
        String responseBody = response.getBody().asString();

        // Print response details immediately for debugging
        System.out.println("========== LOGIN RESPONSE DEBUG ==========");
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Content-Type: " + response.getContentType());
        System.out.println("Response Body (first 1000 chars): " +
            (responseBody != null ? responseBody.substring(0, Math.min(responseBody.length(), 1000)) : "null"));
        System.out.println("===========================================");

        if (responseBody == null || responseBody.trim().isEmpty()) {
            System.err.println("Error: Response body is empty");
            return null;
        }

        // Check if response is HTML instead of JSON
        String trimmedBody = responseBody.trim();
        if (trimmedBody.startsWith("<") || trimmedBody.startsWith("<!")) {
            System.err.println("ERROR: Server returned HTML instead of JSON!");
            System.err.println("This usually means:");
            System.err.println("  1. Wrong API endpoint");
            System.err.println("  2. Server redirect to login page");
            System.err.println("  3. Server error page");
            System.err.println("  4. Firewall/proxy blocking the request");
            return null;
        }

        try {
            // Parse JSON from the string directly
            io.restassured.path.json.JsonPath jsonPath = new io.restassured.path.json.JsonPath(responseBody);

            // Try common token paths in response JSON
            token = jsonPath.getString("token");
            if (token == null) {
                token = jsonPath.getString("data.token");
            }
            if (token == null) {
                token = jsonPath.getString("accessToken");
            }
            if (token == null) {
                token = jsonPath.getString("data.accessToken");
            }
            if (token == null) {
                token = jsonPath.getString("access_token");
            }

            if (token != null && !token.isEmpty()) {
                TokenManager.setToken(token);
                System.out.println("Token extracted and stored successfully");
            } else {
                System.out.println("WARNING: No token found in response with tried paths: token, data.token, accessToken, data.accessToken, access_token");
            }
        } catch (Exception e) {
            System.err.println("Error extracting token from response: " + e.getMessage());
            e.printStackTrace();
        }

        return token;
    }

    /**
     * Perform logout operation
     * @return API response
     */
    public Response logout() {
        System.out.println("Performing logout");

        // Create new request spec with auth for logout
        Response response = getRequestSpec()
                .header("Authorization", TokenManager.getBearerToken())
                .post(EndPoints.LOGOUT);

        if (response.getStatusCode() == 200) {
            TokenManager.clearToken();
            System.out.println("Logout successful, token cleared");
        }

        logResponse(response);
        return response;
    }

    /**
     * Refresh the authentication token
     * @return API response with new token
     */
    public Response refreshToken() {
        System.out.println("Refreshing authentication token");

        Response response = getRequestSpec()
                .header("Authorization", TokenManager.getBearerToken())
                .post(EndPoints.REFRESH_TOKEN);

        if (response.getStatusCode() == 200) {
            extractAndStoreToken(response);
        }

        logResponse(response);
        return response;
    }

    /**
     * Request password reset for a user
     * @param email the user's email
     * @return API response
     */
    public Response forgotPassword(String email, String password, String otp) {
        System.out.println("Requesting password reset for: " + email);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("otp", otp);



        Response response = getRequestSpec()
                .body(requestBody).log().uri()
                .post(EndPoints.FORGOT_PASSWORD);

        logResponse(response);
        return response;
    }

    /**
     * Reset password with token
     * @param resetToken the password reset token
     * @param newPassword the new password
     * @return API response
     */
    public Response resetPassword(String resetToken, String newPassword) {
        System.out.println("Resetting password");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", resetToken);
        requestBody.put("password", newPassword);

        Response response = getRequestSpec()
                .body(requestBody)
                .post(EndPoints.RESET_PASSWORD);

        logResponse(response);
        return response;
    }

    /**
     * Perform login with invalid credentials for negative testing
     * @return API response
     */
    public Response loginWithInvalidCredentials() {
        return login("invalid@email.com", "wrongpassword");
    }

    /**
     * Perform login with empty credentials
     * @return API response
     */
    public Response loginWithEmptyCredentials() {
        return login("", "");
    }

    /**
     * Perform login with only username
     * @param username the username/email
     * @return API response
     */
    public Response loginWithOnlyUsername(String username) {
        System.out.println("Attempting login with only username: " + username);

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", username);

        Response response = getRequestSpec()
                .body(credentials)
                .post(EndPoints.LOGIN);

        logResponse(response);
        return response;
    }

    /**
     * Perform login with only password
     * @param password the password
     * @return API response
     */
    public Response loginWithOnlyPassword(String password) {
        System.out.println("Attempting login with only password");

        Map<String, String> credentials = new HashMap<>();
        credentials.put("password", password);

        Response response = getRequestSpec()
                .body(credentials)
                .post(EndPoints.LOGIN);

        logResponse(response);
        return response;
    }
public Response requestOTP(String email)
    {
        System.out.println("Attempting request OTP: " + email);

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);

        Response response = getRequestSpec()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(credentials).log().uri()
                .post(EndPoints.REQUEST_OTP);


        logResponse(response);
        return response;
    }

    /**
     * Get OTP from Outlook email for sandhiyar@sternadevices.in
     * @param emailPassword the email password or app password
     * @param subjectKeyword keyword to search in email subject (e.g., "OTP", "verification")
     * @param otpLength expected OTP length (e.g., 4, 6, 8)
     * @return extracted OTP or null if not found
     */
    public String getOtpFromEmail(String emailPassword, String subjectKeyword, int otpLength) {
        String email = "sandhiyar@sternadevices.in";
        OutlookEmailHelper emailHelper = new OutlookEmailHelper(email, emailPassword);
        String otp = null;

        try {
            emailHelper.connect();
            emailHelper.openInbox();
            otp = emailHelper.getOtpFromLatestEmail(subjectKeyword, otpLength);
            System.out.println("OTP extracted from email: " + otp);
        } catch (MessagingException | IOException e) {
            System.err.println("Error fetching OTP from email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            emailHelper.disconnect();
        }

        return otp;
    }

    /**
     * Get OTP from Outlook email with wait/retry mechanism
     * @param emailPassword the email password or app password
     * @param subjectKeyword keyword to search in email subject
     * @param otpLength expected OTP length
     * @param maxWaitSeconds maximum time to wait for email
     * @param pollIntervalSeconds interval between checks
     * @return extracted OTP or null if not found within timeout
     */
    public String waitAndGetOtpFromEmail(  String emailPassword, String subjectKeyword, int otpLength,
                                          int maxWaitSeconds, int pollIntervalSeconds) {
        String email = "projectams85@gmail.com";

        OutlookEmailHelper emailHelper = new OutlookEmailHelper(email, emailPassword);
        String otp = null;

        try {
            emailHelper.connect();
            emailHelper.openInbox();
            otp = emailHelper.waitAndGetOtp(subjectKeyword, otpLength, maxWaitSeconds, pollIntervalSeconds);
            System.out.println("OTP extracted from email: " + otp);
        } catch (MessagingException | IOException | InterruptedException e) {
            System.err.println("Error fetching OTP from email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            emailHelper.disconnect();
        }

        return otp;
    }

    /**
     * Request OTP and wait to fetch it from email
     * @param emailPassword the email password for sandhiyar@sternadevices.in
     * @param subjectKeyword keyword to search in OTP email subject
     * @param otpLength expected OTP length
     * @return extracted OTP or null if not found
     */
    public String requestAndGetOtp(String emailPassword, String subjectKeyword, int otpLength) {
        String email = "projectams85@gmail.com";

        // First request the OTP
        Response response = requestOTP(email);

        if (response.getStatusCode() == 200) {
            System.out.println("OTP request sent successfully, waiting for email...");
            // Wait for OTP email (max 60 seconds, poll every 5 seconds)
            return waitAndGetOtpFromEmail(emailPassword, subjectKeyword, otpLength, 60, 5);
        } else {
            System.err.println("Failed to request OTP. Status: " + response.getStatusCode());
            return null;
        }
    }

    // ==================== Gmail OTP Methods ====================

    /**
     * Get OTP from Gmail email for projectams85@gmail.com
     * @param emailPassword the Gmail password or App Password (if 2FA is enabled)
     * @param subjectKeyword keyword to search in email subject (e.g., "Ams OTP")
     * @param otpLength expected OTP length (e.g., 6)
     * @param maxWaitSeconds maximum time to wait for email
     * @param pollIntervalSeconds interval between checks
     * @return extracted OTP or null if not found
     */
    public String getOTPFromGmail(String emailPassword, String subjectKeyword, int otpLength,
                                   int maxWaitSeconds, int pollIntervalSeconds) {
        String email = "projectams85@gmail.com";
        GmailEmailHelper emailHelper = new GmailEmailHelper(email, emailPassword);
        String otp = null;

        try {
            System.out.println("Connecting to Gmail: " + email);
            emailHelper.connect();
            emailHelper.openInbox();

            System.out.println("Waiting for OTP email with subject: " + subjectKeyword);
            otp = emailHelper.waitAndGetOtp(subjectKeyword, otpLength, maxWaitSeconds, pollIntervalSeconds);

            if (otp != null) {
                System.out.println("OTP extracted from Gmail: " + otp);
            } else {
                System.out.println("No OTP found in Gmail");
            }
        } catch (MessagingException | IOException | InterruptedException e) {
            System.err.println("Error fetching OTP from Gmail: " + e.getMessage());
            e.printStackTrace();
        } finally {
            emailHelper.disconnect();
        }

        return otp;
    }

    /**
     * Get OTP from latest Gmail email (no waiting)
     * @param emailPassword the Gmail password or App Password
     * @param subjectKeyword keyword to search in email subject
     * @param otpLength expected OTP length
     * @return extracted OTP or null if not found
     */
    public String getOTPFromGmailLatest(String emailPassword, String subjectKeyword, int otpLength) {
        String email = "projectams85@gmail.com";
        GmailEmailHelper emailHelper = new GmailEmailHelper(email, emailPassword);
        String otp = null;

        try {
            System.out.println("Connecting to Gmail: " + email);
            emailHelper.connect();
            emailHelper.openInbox();
            otp = emailHelper.getOtpFromLatestEmail(subjectKeyword, otpLength);

            if (otp != null) {
                System.out.println("OTP extracted from Gmail: " + otp);
            }
        } catch (MessagingException | IOException e) {
            System.err.println("Error fetching OTP from Gmail: " + e.getMessage());
            e.printStackTrace();
        } finally {
            emailHelper.disconnect();
        }

        return otp;
    }

    // ==================== OAuth2 Methods (Support MFA/Authenticator) ====================

    /**
     * Get OTP from Outlook email using OAuth2 authentication (supports MFA/Authenticator)
     * This will prompt you to authenticate via browser with your authenticator code
     * @param subjectKeyword keyword to search in email subject (e.g., "OTP", "verification")
     * @param otpLength expected OTP length (e.g., 4, 6, 8)
     * @return extracted OTP or null if not found
     */
    public String getOtpFromEmailWithOAuth(String subjectKeyword, int otpLength) {
        String email = "projectams85@gmail.com";
        OutlookOAuthHelper emailHelper = new OutlookOAuthHelper(email);
        String otp = null;

        try {
            System.out.println("Starting OAuth2 authentication for: " + email);
            System.out.println("You will be prompted to authenticate via browser...");

            emailHelper.authenticateAndConnect();
            emailHelper.openInbox();
            otp = emailHelper.getOtpFromLatestEmail(subjectKeyword, otpLength);
            System.out.println("OTP extracted from email: " + otp);
        } catch (Exception e) {
            System.err.println("Error fetching OTP from email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            emailHelper.disconnect();
        }

        return otp;
    }

    /**
     * Get OTP from Outlook email with wait/retry mechanism using OAuth2 (supports MFA/Authenticator)
     * @param subjectKeyword keyword to search in email subject
     * @param otpLength expected OTP length
     * @param maxWaitSeconds maximum time to wait for email
     * @param pollIntervalSeconds interval between checks
     * @return extracted OTP or null if not found within timeout
     */
    public String waitAndGetOtpFromEmailWithOAuth(String subjectKeyword, int otpLength,
                                                   int maxWaitSeconds, int pollIntervalSeconds) {
        String email = "projectams85@gmail.com";
        OutlookOAuthHelper emailHelper = new OutlookOAuthHelper(email);
        String otp = null;

        try {
            System.out.println("Starting OAuth2 authentication for: " + email);
            System.out.println("You will be prompted to authenticate via browser...");

            emailHelper.authenticateAndConnect();
            emailHelper.openInbox();
            otp = emailHelper.waitAndGetOtp(subjectKeyword, otpLength, maxWaitSeconds, pollIntervalSeconds);
            System.out.println("OTP extracted from email: " + otp);
        } catch (Exception e) {
            System.err.println("Error fetching OTP from email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            emailHelper.disconnect();
        }

        return otp;
    }

    /**
     * Request OTP and wait to fetch it from email using OAuth2 (supports MFA/Authenticator)
     * This is the recommended method when your Outlook account has MFA enabled
     * @param subjectKeyword keyword to search in OTP email subject
     * @param otpLength expected OTP length
     * @return extracted OTP or null if not found
     */
    public String requestAndGetOtpWithOAuth(String subjectKeyword, int otpLength) {
        String email = "sandhiyar@sternadevices.in";

        // First request the OTP via API
        Response response = requestOTP(email);

        if (response.getStatusCode() == 200) {
            System.out.println("OTP request sent successfully, waiting for email...");
            // Wait for OTP email (max 60 seconds, poll every 5 seconds)
            return waitAndGetOtpFromEmailWithOAuth(subjectKeyword, otpLength, 60, 5);
        } else {
            System.err.println("Failed to request OTP. Status: " + response.getStatusCode());
            return null;
        }
    }


}
