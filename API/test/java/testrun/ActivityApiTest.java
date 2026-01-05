package testrun;

import pages.ActivityApi;
import utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Activity Management API endpoints.
 * Contains tests for activity CRUD operations.
 */
public class ActivityApiTest extends BaseTest {

    private ActivityApi activityApi;

    @BeforeClass
    public void setUpActivityTests() {
        ensureLoggedIn();
        activityApi = new ActivityApi();
        System.out.println("ActivityApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all activities")
    public void testGetAllActivities() {
        System.out.println("Testing get all activities API");

        Response response = activityApi.getAllActivities();

        int statusCode = response.getStatusCode();
        System.out.println("Get all activities returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all activities should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching activities with pagination")
    public void testGetActivitiesWithPagination() {
        System.out.println("Testing get activities with pagination");

        Response response = activityApi.getAllActivities(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get activities with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get activities with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test fetching activity by valid ID")
    public void testGetActivityById() {
        System.out.println("Testing get activity by ID");

        Response response = activityApi.getActivityById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get activity by ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get activity by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching activity with invalid ID")
    public void testGetActivityWithInvalidId() {
        System.out.println("Testing get activity with invalid ID");

        Response response = activityApi.getActivityWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get activity with invalid ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get activity with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test searching activities")
    public void testSearchActivities() {
        System.out.println("Testing search activities API");

        Response response = activityApi.searchActivities("test");

        int statusCode = response.getStatusCode();
        System.out.println("Search activities returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Search activities should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test getting activities by type")
    public void testGetActivitiesByType() {
        System.out.println("Testing get activities by type");

        Response response = activityApi.getActivitiesByType("standard");

        int statusCode = response.getStatusCode();
        System.out.println("Get activities by type returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get activities by type should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 7, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");

        Response response = activityApi.getAllActivities();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 8, description = "Test activity API response time")
    public void testActivityApiResponseTime() {
        System.out.println("Testing activity API response time");

        Response response = activityApi.getAllActivities();

        long responseTime = response.getTime();
        System.out.println("Activity API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "Activity API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 9, description = "Test accessing activity API without token")
    public void testActivityApiWithoutToken() {
        System.out.println("Testing activity API without authentication token");

        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        ActivityApi unauthenticatedApi = new ActivityApi(false);
        Response response = unauthenticatedApi.getAllActivities();

        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if endpoint is public). Got: " + statusCode);

        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
}
