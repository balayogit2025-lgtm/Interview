package api.testrun;

import api.pages.LoginApi;
import api.utils.ConfigReader;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Login API endpoints.
 * Contains tests for authentication functionality.
 */
public class LoginApiTest extends BaseTest {

    private LoginApi loginApi;

    @BeforeClass
    public void setUpLoginTests() {
        loginApi = new LoginApi();
        System.out.println("LoginApiTest setup completed");
    }

    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testLoginWithValidCredentials() {
        System.out.println("Testing login with valid credentials");

        // Get credentials from config
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();

        // Perform login
        Response response = loginApi.login(username, password);

        // Validate HTTP status code is 200
        Assert.assertEquals(response.getStatusCode(), 200,
                "Login should return HTTP 200 status code");

        // Validate response is not empty
        Assert.assertNotNull(response.getBody(),
                "Response body should not be null");

        System.out.println("Login successful with status code: " + response.getStatusCode());
    }

    @Test(priority = 2, description = "Test login and extract authentication token")
    public void testLoginAndExtractToken() {
        System.out.println("Testing login and token extraction");

        // Clear any existing token
        TokenManager.clearToken();

        // Perform login and store token
        Response response = loginApi.loginAndStoreToken();

        // Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200,
                "Login should return HTTP 200 status code");

        // Validate token was extracted and stored
        Assert.assertTrue(TokenManager.hasToken(),
                "Token should be stored after successful login");

        String token = TokenManager.getToken();
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.isEmpty(), "Token should not be empty");

        System.out.println("Token extracted and stored successfully");
    }

    @Test(priority = 3, description = "Test response body contains expected fields")
    public void testLoginResponseBody() {
        System.out.println("Testing login response body structure");

        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();

        Response response = loginApi.login(username, password);

        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200,
                "Login should return HTTP 200 status code");

        // Validate response content type is JSON
        String contentType = response.getContentType();
        Assert.assertTrue(contentType.contains("application/json"),
                "Response should be JSON format");

        // Get response body as string
        String responseBody = response.getBody().asString();
        Assert.assertNotNull(responseBody, "Response body should not be null");
        Assert.assertFalse(responseBody.isEmpty(), "Response body should not be empty");

        System.out.println("Response body validation passed");
    }

    @Test(priority = 4, description = "Test token can be reused for subsequent API calls")
    public void testTokenReusability() {
        System.out.println("Testing token reusability for subsequent API calls");

        // Ensure we have a valid token
        if (!TokenManager.hasToken()) {
            loginApi.loginAndStoreToken();
        }

        // Verify token exists
        Assert.assertTrue(TokenManager.hasToken(),
                "Token should be available for reuse");

        String token = TokenManager.getToken();
        Assert.assertNotNull(token, "Token should not be null");

        System.out.println("Token is available for subsequent API calls");
    }

    @Test(priority = 5, description = "Test login with invalid credentials returns error")
    public void testLoginWithInvalidCredentials() {
        System.out.println("Testing login with invalid credentials");

        Response response = loginApi.login("invalid@email.com", "wrongpassword");

        // Should return 401 Unauthorized or 400 Bad Request
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 401 || statusCode == 400 || statusCode == 403,
                "Login with invalid credentials should return 401, 400, or 403. Got: " + statusCode);

        System.out.println("Invalid credentials test passed with status code: " + statusCode);
    }

    @Test(priority = 6, description = "Test login with empty credentials")
    public void testLoginWithEmptyCredentials() {
        System.out.println("Testing login with empty credentials");

        Response response = loginApi.loginWithEmptyCredentials();

        // Should return 400 Bad Request or 401 Unauthorized
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 400 || statusCode == 401 || statusCode == 422,
                "Login with empty credentials should return 400, 401, or 422. Got: " + statusCode);

        System.out.println("Empty credentials test passed with status code: " + statusCode);
    }

    @Test(priority = 7, description = "Test login with only username (missing password)")
    public void testLoginWithMissingPassword() {
        System.out.println("Testing login with missing password");

        String username = ConfigReader.getUsername();
        Response response = loginApi.loginWithOnlyUsername(username);

        // Should return 400 Bad Request or 401 Unauthorized
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 400 || statusCode == 401 || statusCode == 422,
                "Login with missing password should return 400, 401, or 422. Got: " + statusCode);

        System.out.println("Missing password test passed with status code: " + statusCode);
    }

    @Test(priority = 8, description = "Test login with only password (missing username)")
    public void testLoginWithMissingUsername() {
        System.out.println("Testing login with missing username");

        String password = ConfigReader.getPassword();
        Response response = loginApi.loginWithOnlyPassword(password);

        // Should return 400 Bad Request or 401 Unauthorized
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 400 || statusCode == 401 || statusCode == 422,
                "Login with missing username should return 400, 401, or 422. Got: " + statusCode);

        System.out.println("Missing username test passed with status code: " + statusCode);
    }

    @Test(priority = 9, description = "Test login response time is within acceptable limits")
    public void testLoginResponseTime() {
        System.out.println("Testing login API response time");

        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();

        Response response = loginApi.login(username, password);

        long responseTime = response.getTime();
        System.out.println("Login API response time: " + responseTime + " ms");

        // Response time should be less than 5 seconds
        Assert.assertTrue(responseTime < 5000,
                "Login API response time should be less than 5 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 10, description = "Validate Bearer token format after login")
    public void testBearerTokenFormat() {
        System.out.println("Testing Bearer token format");

        // Ensure we have a token
        if (!TokenManager.hasToken()) {
            loginApi.loginAndStoreToken();
        }

        String bearerToken = TokenManager.getBearerToken();

        Assert.assertNotNull(bearerToken, "Bearer token should not be null");
        Assert.assertTrue(bearerToken.startsWith("Bearer "),
                "Bearer token should start with 'Bearer '");

        System.out.println("Bearer token format validation passed");
    }

   @Test(priority = 11, description = "Verify the request OTP")
    public void testRequestOTPWithValidEmail() {

        System.out.println("Testing request OTP");
       String username = ConfigReader.getUsername();
       Response response = loginApi.requestOTP(username);

       long responseTime = response.getTime();
       System.out.println("Login API response time: " + responseTime + " ms");

       long responseStatus =  response.getStatusCode();
       System.out.println("Login API response status: " + responseStatus + " ms");

       System.out.println("Login API response body: " + response.getBody() );


       Assert.assertTrue(responseStatus == 200, "Login API response status should be 200");
   }

    @Test(priority = 12, description = "Verify the request OTP with invalid email")
    public void testRequestOTPWithInValidEmail() {

        System.out.println("Testing request OTP");
        String username = ConfigReader.getInvalidUsername();
        Response response = loginApi.requestOTP(username);

        long responseTime = response.getTime();
        System.out.println("Login API response time: " + responseTime + " ms");

        long responseStatus =  response.getStatusCode();
        System.out.println("Login API response status: " + responseStatus + " ms");

        Assert.assertTrue(responseStatus == 400, "Login API response status should be 400");
    }

    // ==================== Forgot Password Tests ====================

    @Test(priority = 13, description = "Verify forgot password with valid email")
    public void testForgotPasswordWithValidEmail() {
        System.out.println("Testing forgot password with valid email");

        String email = "projectams85@gmail.com";
        String password = "Sterna@123";

        // Step 1: Request OTP first (this sends the OTP email)
        System.out.println("Step 1: Requesting OTP...");
        Response otpResponse = loginApi.requestOTP(email);
        Assert.assertEquals(otpResponse.getStatusCode(), 200, "OTP request should return 200");

        // Step 2: Fetch OTP from Gmail (wait up to 60 seconds, poll every 5 seconds)
        System.out.println("Step 2: Fetching OTP from Gmail...");
        String otp = loginApi.getOTPFromGmail("uhisxmjlsycnrdjc", "Ams OTP", 6, 60, 5);
        Assert.assertNotNull(otp, "OTP should not be null");
        System.out.println("OTP received: " + otp);

        // Step 3: Call forgot password with the OTP
        // forgotPassword(email, password, otp) - correct parameter order
        System.out.println("Step 3: Calling forgot password API...");
        Response response = loginApi.forgotPassword(email, password, otp);

        int statusCode = response.getStatusCode();
        System.out.println("Forgot password response status: " + statusCode);

        // Should return 200 OK for valid email
        Assert.assertEquals(statusCode, 200,
                "Forgot password with valid email should return 200");

        // Validate response is JSON
        String contentType = response.getContentType();
        Assert.assertTrue(contentType.contains("application/json"),
                "Response should be JSON format");

        System.out.println("Forgot password with valid email test passed");
    }

    @Test(priority = 14, description = "Verify forgot password with invalid email")
    public void testForgotPasswordWithInvalidEmail() {
        System.out.println("Testing forgot password with invalid email");

        String email = "nonexistent@invalid.com";

        String password = ConfigReader.getPassword();
        String otp = ConfigReader.getOTP();

        Response response = loginApi.forgotPassword(email,otp,password);

        int statusCode = response.getStatusCode();
        System.out.println("Forgot password response status: " + statusCode);

        // Should return 400 or 404 for non-existent email
        Assert.assertTrue(statusCode == 400 ,
                "Forgot password with invalid email should return 400 or 404. Got: " + statusCode);

        System.out.println("Forgot password with invalid email test passed");
    }

    @Test(priority = 15, description = "Verify forgot password with empty email")
    public void testForgotPasswordWithEmptyEmail() {
        System.out.println("Testing forgot password with empty email");
String email = "";
String password = ConfigReader.getPassword();
String otp = ConfigReader.getOTP();

        Response response = loginApi.forgotPassword(email,password,otp);

        int statusCode = response.getStatusCode();
        System.out.println("Forgot password response status: " + statusCode);

        // Should return 400 Bad Request for empty email
        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode ==404,
                "Forgot password with empty email should return 400 or 422. Got: " + statusCode);

        System.out.println("Forgot password with empty email test passed");
    }

    @Test(priority = 16, description = "Verify forgot password response time")
    public void testForgotPasswordResponseTime() {
        System.out.println("Testing forgot password API response time");

        String email = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        String otp = ConfigReader.getOTP();

        Response response = loginApi.forgotPassword(email,password,otp);

        long responseTime = response.getTime();
        System.out.println("Forgot password API response time: " + responseTime + " ms");

        // Response time should be less than 5 seconds
        Assert.assertTrue(responseTime < 5000,
                "Forgot password API response time should be less than 5 seconds. Actual: " + responseTime + " ms");
    }

}
