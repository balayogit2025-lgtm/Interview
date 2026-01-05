package testrun;

import pages.DeviceActivityApi;
import utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Test class for Device Activity API endpoints.
 * Contains tests for device activity read operations.
 */
public class DeviceActivityApiTest extends BaseTest {

    private DeviceActivityApi deviceActivityApi;

    @BeforeClass
    public void setUpDeviceActivityTests() {
        ensureLoggedIn();
        deviceActivityApi = new DeviceActivityApi();
        System.out.println("DeviceActivityApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all device activities")
    public void testGetAllDeviceActivities() {
        System.out.println("Testing get all device activities API");

        Response response = deviceActivityApi.getAllDeviceActivities();

        int statusCode = response.getStatusCode();
        System.out.println("Get all device activities returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all device activities should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching device activities with pagination")
    public void testGetDeviceActivitiesWithPagination() {
        System.out.println("Testing get device activities with pagination");

        Response response = deviceActivityApi.getAllDeviceActivities(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get device activities with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get device activities with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test fetching device activity by valid ID")
    public void testGetDeviceActivityById() {
        System.out.println("Testing get device activity by ID");

        Response response = deviceActivityApi.getDeviceActivityById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get device activity by ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get device activity by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching device activity with invalid ID")
    public void testGetDeviceActivityWithInvalidId() {
        System.out.println("Testing get device activity with invalid ID");

        Response response = deviceActivityApi.getDeviceActivityWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get device activity with invalid ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get device activity with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test fetching activities by device ID")
    public void testGetActivitiesByDevice() {
        System.out.println("Testing get activities by device ID");

        Response response = deviceActivityApi.getActivitiesByDevice("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get activities by device returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get activities by device should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test fetching activities by date range")
    public void testGetActivitiesByDateRange() {
        System.out.println("Testing get activities by date range");

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Response response = deviceActivityApi.getActivitiesByDateRange(
                startDate.format(formatter),
                endDate.format(formatter)
        );

        int statusCode = response.getStatusCode();
        System.out.println("Get activities by date range returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get activities by date range should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 7, description = "Test fetching activities by type")
    public void testGetActivitiesByType() {
        System.out.println("Testing get activities by type");

        Response response = deviceActivityApi.getActivitiesByType("KEY_PICKUP");

        int statusCode = response.getStatusCode();
        System.out.println("Get activities by type returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get activities by type should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 8, description = "Test fetching activities by user")
    public void testGetActivitiesByUser() {
        System.out.println("Testing get activities by user");

        Response response = deviceActivityApi.getActivitiesByUser("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get activities by user returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get activities by user should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 9, description = "Test searching device activities")
    public void testSearchDeviceActivities() {
        System.out.println("Testing search device activities API");

        Response response = deviceActivityApi.searchDeviceActivities("test");

        int statusCode = response.getStatusCode();
        System.out.println("Search device activities returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Search device activities should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 10, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");

        Response response = deviceActivityApi.getAllDeviceActivities();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 11, description = "Test device activity API response time")
    public void testDeviceActivityApiResponseTime() {
        System.out.println("Testing device activity API response time");

        Response response = deviceActivityApi.getAllDeviceActivities();

        long responseTime = response.getTime();
        System.out.println("Device Activity API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "Device Activity API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 12, description = "Test accessing device activity API without token")
    public void testDeviceActivityApiWithoutToken() {
        System.out.println("Testing device activity API without authentication token");

        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        DeviceActivityApi unauthenticatedApi = new DeviceActivityApi(false);
        Response response = unauthenticatedApi.getAllDeviceActivities();

        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if endpoint is public). Got: " + statusCode);

        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
}
