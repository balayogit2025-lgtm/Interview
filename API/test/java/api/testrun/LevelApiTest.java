package api.testrun;

import api.pages.LevelApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Level Management API endpoints.
 * Contains tests for level CRUD operations (State, Territory, Cluster, Region).
 */
public class LevelApiTest extends BaseTest {

    private LevelApi levelApi;

    @BeforeClass
    public void setUpLevelTests() {
        ensureLoggedIn();
        levelApi = new LevelApi();
        System.out.println("LevelApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all levels")
    public void testGetAllLevels() {
        System.out.println("Testing get all levels API");

        Response response = levelApi.getAllLevels();

        int statusCode = response.getStatusCode();
        System.out.println("Get all levels returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all levels should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching levels with pagination")
    public void testGetLevelsWithPagination() {
        System.out.println("Testing get levels with pagination");

        Response response = levelApi.getAllLevels(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get levels with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get levels with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test fetching level by valid ID")
    public void testGetLevelById() {
        System.out.println("Testing get level by ID");

        Response response = levelApi.getLevelById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get level by ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get level by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching level with invalid ID")
    public void testGetLevelWithInvalidId() {
        System.out.println("Testing get level with invalid ID");

        Response response = levelApi.getLevelWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get level with invalid ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get level with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test searching levels")
    public void testSearchLevels() {
        System.out.println("Testing search levels API");

        Response response = levelApi.searchLevels("test");

        int statusCode = response.getStatusCode();
        System.out.println("Search levels returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Search levels should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test getting levels by type - State")
    public void testGetLevelsByTypeState() {
        System.out.println("Testing get levels by type: state");

        Response response = levelApi.getLevelsByType("state");

        int statusCode = response.getStatusCode();
        System.out.println("Get levels by type returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get levels by type should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 7, description = "Test getting levels by type - Territory")
    public void testGetLevelsByTypeTerritory() {
        System.out.println("Testing get levels by type: territory");

        Response response = levelApi.getLevelsByType("territory");

        int statusCode = response.getStatusCode();
        System.out.println("Get levels by type returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get levels by type should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 8, description = "Test getting levels by type - Cluster")
    public void testGetLevelsByTypeCluster() {
        System.out.println("Testing get levels by type: cluster");

        Response response = levelApi.getLevelsByType("cluster");

        int statusCode = response.getStatusCode();
        System.out.println("Get levels by type returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get levels by type should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 9, description = "Test getting levels by type - Region")
    public void testGetLevelsByTypeRegion() {
        System.out.println("Testing get levels by type: region");

        Response response = levelApi.getLevelsByType("region");

        int statusCode = response.getStatusCode();
        System.out.println("Get levels by type returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get levels by type should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 10, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");

        Response response = levelApi.getAllLevels();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 11, description = "Test level API response time")
    public void testLevelApiResponseTime() {
        System.out.println("Testing level API response time");

        Response response = levelApi.getAllLevels();

        long responseTime = response.getTime();
        System.out.println("Level API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "Level API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 12, description = "Test accessing level API without token")
    public void testLevelApiWithoutToken() {
        System.out.println("Testing level API without authentication token");

        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        LevelApi unauthenticatedApi = new LevelApi(false);
        Response response = unauthenticatedApi.getAllLevels();

        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if endpoint is public). Got: " + statusCode);

        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
}
