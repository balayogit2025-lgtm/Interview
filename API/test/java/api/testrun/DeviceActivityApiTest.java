package api.testrun;

import api.pages.DeviceActivityApi;
import api.utils.TokenManager;
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
        Assert.assertEquals(statusCode, 200,"The status code mismatches");

    }

    @Test(priority = 2, description = "Test fetching device activities with pagination")
    public void testGetDeviceActivitiesWithPagination() {
        System.out.println("Testing get device activities with pagination");
        Response response = deviceActivityApi.getAllDeviceActivities(1, 10);
        int statusCode = response.getStatusCode();
        System.out.println("Get device activities with pagination returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 200,"The status code mismatches");
    }

    @Test(priority = 3, description = "Test fetching device activity by valid serial number")
    public void testGetDeviceActivityByserialnumber() {
        System.out.println("Testing get device activity by serial number");
        Response response = deviceActivityApi.getDeviceActivityByserialnumber("Q1.A0001");
         int totalcount = response.jsonPath().getInt("data.totalCount");
        System.out.println("The total number of device activity with the serial number is " +totalcount);
        Assert.assertTrue(totalcount > 0, "The total count mismatches");
    }

    @Test(priority = 4, description = "Test fetching device activity with invalid serial number")
    public void testGetDeviceActivityWithInvalidserialnumber() {
        System.out.println("Testing get device activity with invalid serial number");
        Response response = deviceActivityApi.getDeviceActivityByserialnumber("12345");
        int totalcount = response.jsonPath().getInt("data.totalCount");
        System.out.println("The total number of device activity with the serial number is " +totalcount);
        Assert.assertTrue(totalcount ==0, "The total count mismatches");
    }
    @Test(priority = 5, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");
        Response response = deviceActivityApi.getAllDeviceActivities();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType.contains("application/json"), "Response is not in JSON format");
        }
    }

    @Test(priority = 6, description = "Test device activity API response time")
    public void testDeviceActivityApiResponseTime() {
        System.out.println("Testing device activity API response time");
        Response response = deviceActivityApi.getAllDeviceActivities();
        long responseTime = response.getTime();
        System.out.println("Device Activity API response time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < 10000, "Device Activity API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }
    @Test(priority = 7, description = "Test accessing device activity API without token")
    public void testDeviceActivityApiWithoutToken() {
        System.out.println("Testing device activity API without authentication token");
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();
        DeviceActivityApi unauthenticatedApi = new DeviceActivityApi(false);
        Response response = unauthenticatedApi.getAllDeviceActivities();
        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 401,"The status code mismatches");
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
}
