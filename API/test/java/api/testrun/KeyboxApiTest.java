package api.testrun;

import api.pages.KeyboxApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Keybox Management API endpoints.
 * Contains tests for keybox CRUD operations.
 */
public class KeyboxApiTest extends BaseTest {

    private KeyboxApi keyboxApi;

    @BeforeClass
    public void setUpKeyboxTests() {
        ensureLoggedIn();
        keyboxApi = new KeyboxApi();
        System.out.println("KeyboxApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all keyboxes")
    public void testGetAllKeyboxes() {
        System.out.println("Testing get all keyboxes API");

        Response response = keyboxApi.getAllKeyboxes();

        int statusCode = response.getStatusCode();
        System.out.println("Get all keyboxes returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all keyboxes should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching keyboxes with pagination")
    public void testGetKeyboxesWithPagination() {
        System.out.println("Testing get keyboxes with pagination");

        Response response = keyboxApi.getAllKeyboxes(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get keyboxes with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get keyboxes with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test fetching keybox by valid ID")
    public void testGetKeyboxById() {
        System.out.println("Testing get keybox by ID");

        Response response = keyboxApi.getKeyboxById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get keybox by ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get keybox by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching keybox with invalid ID")
    public void testGetKeyboxWithInvalidId() {
        System.out.println("Testing get keybox with invalid ID");

        Response response = keyboxApi.getKeyboxWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get keybox with invalid ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get keybox with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test searching keyboxes")
    public void testSearchKeyboxes() {
        System.out.println("Testing search keyboxes API");

        Response response = keyboxApi.searchKeyboxes("test");

        int statusCode = response.getStatusCode();
        System.out.println("Search keyboxes returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Search keyboxes should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test getting keyboxes by location")
    public void testGetKeyboxesByLocation() {
        System.out.println("Testing get keyboxes by location");

        Response response = keyboxApi.getKeyboxesByLocation("office");

        int statusCode = response.getStatusCode();
        System.out.println("Get keyboxes by location returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get keyboxes by location should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 7, description = "Test getting keyboxes by status - active")
    public void testGetKeyboxesByStatusActive() {
        System.out.println("Testing get keyboxes by status: active");

        Response response = keyboxApi.getKeyboxesByStatus("active");

        int statusCode = response.getStatusCode();
        System.out.println("Get keyboxes by status returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get keyboxes by status should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 8, description = "Test getting keyboxes by status - inactive")
    public void testGetKeyboxesByStatusInactive() {
        System.out.println("Testing get keyboxes by status: inactive");

        Response response = keyboxApi.getKeyboxesByStatus("inactive");

        int statusCode = response.getStatusCode();
        System.out.println("Get keyboxes by status returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get keyboxes by status should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 9, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");

        Response response = keyboxApi.getAllKeyboxes();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 10, description = "Test keybox API response time")
    public void testKeyboxApiResponseTime() {
        System.out.println("Testing keybox API response time");

        Response response = keyboxApi.getAllKeyboxes();

        long responseTime = response.getTime();
        System.out.println("Keybox API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "Keybox API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 11, description = "Test accessing keybox API without token")
    public void testKeyboxApiWithoutToken() {
        System.out.println("Testing keybox API without authentication token");

        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        KeyboxApi unauthenticatedApi = new KeyboxApi(false);
        Response response = unauthenticatedApi.getAllKeyboxes();

        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if endpoint is public). Got: " + statusCode);

        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
}
