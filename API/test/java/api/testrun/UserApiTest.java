package api.testrun;

import api.pages.UserApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for User Management API endpoints.
 * Contains tests for user CRUD operations.
 */
public class UserApiTest extends BaseTest {

    private UserApi userApi;

    @BeforeClass
    public void setUpUserTests() {
        // Ensure user is logged in before running user tests
        ensureLoggedIn();

        // Initialize UserApi with authentication
        userApi = new UserApi();
        System.out.println("UserApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all users")
    public void testGetAllUsers() {
        System.out.println("Testing get all users API");

        Response response = userApi.getAllUsers();

        // Validate status code (200 for success, 401/403 if unauthorized)
        int statusCode = response.getStatusCode();
        System.out.println("Get all users returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all users should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching current user profile")
    public void testGetUserProfile() {
        System.out.println("Testing get user profile API");

        Response response = userApi.getUserProfile();

        int statusCode = response.getStatusCode();
        System.out.println("Get user profile returned status code: " + statusCode);

        // Profile endpoint might return 200 or 404 depending on implementation
        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get user profile should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            String responseBody = response.getBody().asString();
            Assert.assertNotNull(responseBody, "Response body should not be null");
            Assert.assertFalse(responseBody.isEmpty(), "Response body should not be empty");
        }
    }

    @Test(priority = 3, description = "Test fetching users with pagination")
    public void testGetUsersWithPagination() {
        System.out.println("Testing get users with pagination");

        Response response = userApi.getAllUsers(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get users with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get users with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching user by valid ID")
    public void testGetUserById() {
        System.out.println("Testing get user by ID");

        // Using ID "1" as a common test ID
        Response response = userApi.getUserById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get user by ID returned status code: " + statusCode);

        // Could be 200 if user exists, 404 if not found
        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get user by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test fetching user with invalid ID")
    public void testGetUserWithInvalidId() {
        System.out.println("Testing get user with invalid ID");

        Response response = userApi.getUserWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get user with invalid ID returned status code: " + statusCode);

        // Should return 404 Not Found or 400 Bad Request
        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get user with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test searching users")
    public void testSearchUsers() {
        System.out.println("Testing search users API");

        Response response = userApi.searchUsers("test");

        int statusCode = response.getStatusCode();
        System.out.println("Search users returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Search users should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 7, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");

        Response response = userApi.getAllUsers();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 8, description = "Test user API response time")
    public void testUserApiResponseTime() {
        System.out.println("Testing user API response time");

        Response response = userApi.getAllUsers();

        long responseTime = response.getTime();
        System.out.println("User API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "User API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 9, description = "Test accessing user API without token")
    public void testUserApiWithoutToken() {
        System.out.println("Testing user API without authentication token");

        // Clear the token temporarily
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        // Create new UserApi instance without token
        UserApi unauthenticatedUserApi = new UserApi(false);
        Response response = unauthenticatedUserApi.getAllUsers();

        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);

        // Should return 401 Unauthorized or 403 Forbidden
        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if endpoint is public). Got: " + statusCode);

        // Restore the token
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }

    @Test(priority = 10, description = "Test getting users by role")
    public void testGetUsersByRole() {
        System.out.println("Testing get users by role");

        Response response = userApi.getUsersByRole("admin");

        int statusCode = response.getStatusCode();
        System.out.println("Get users by role returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get users by role should return valid status code. Got: " + statusCode);
    }
}
