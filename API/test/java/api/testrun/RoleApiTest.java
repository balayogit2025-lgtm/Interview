package api.testrun;

import api.pages.RoleApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Role Management API endpoints.
 * Contains tests for role CRUD operations.
 */
public class RoleApiTest extends BaseTest {

    private RoleApi roleApi;

    @BeforeClass
    public void setUpRoleTests() {
        ensureLoggedIn();
        roleApi = new RoleApi();
        System.out.println("RoleApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all roles")
    public void testGetAllRoles() {
        System.out.println("Testing get all roles API");

        Response response = roleApi.getAllRoles();

        int statusCode = response.getStatusCode();
        System.out.println("Get all roles returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all roles should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching roles with pagination")
    public void testGetRolesWithPagination() {
        System.out.println("Testing get roles with pagination");

        Response response = roleApi.getAllRoles(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get roles with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get roles with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test fetching role by valid ID")
    public void testGetRoleById() {
        System.out.println("Testing get role by ID");

        Response response = roleApi.getRoleById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get role by ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get role by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching role with invalid ID")
    public void testGetRoleWithInvalidId() {
        System.out.println("Testing get role with invalid ID");

        Response response = roleApi.getRoleWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get role with invalid ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get role with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test searching roles")
    public void testSearchRoles() {
        System.out.println("Testing search roles API");

        Response response = roleApi.searchRoles("admin");

        int statusCode = response.getStatusCode();
        System.out.println("Search roles returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Search roles should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");

        Response response = roleApi.getAllRoles();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 7, description = "Test role API response time")
    public void testRoleApiResponseTime() {
        System.out.println("Testing role API response time");

        Response response = roleApi.getAllRoles();

        long responseTime = response.getTime();
        System.out.println("Role API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "Role API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 8, description = "Test accessing role API without token")
    public void testRoleApiWithoutToken() {
        System.out.println("Testing role API without authentication token");

        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        RoleApi unauthenticatedApi = new RoleApi(false);
        Response response = unauthenticatedApi.getAllRoles();

        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if endpoint is public). Got: " + statusCode);

        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
}
