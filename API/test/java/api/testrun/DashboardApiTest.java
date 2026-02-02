package api.testrun;

import api.pages.DashboardApi;
import api.utils.ResponseValidator;
import api.utils.TokenManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Test class for Dashboard API endpoint: /api/dashboard/get
 * Contains comprehensive tests for dashboard data retrieval.
 * Covers positive, negative, and edge case scenarios.
 */
public class DashboardApiTest extends BaseTest {

    private DashboardApi dashboardApi;

    @BeforeClass
    public void setUpDashboardTests() {
        // Ensure user is logged in before running dashboard tests
        ensureLoggedIn();

        // Initialize DashboardApi with authentication
        dashboardApi = new DashboardApi();
        System.out.println("DashboardApiTest setup completed");
    }
    @Test(priority = 1, description = "Test fetching main dashboard data")
    public void testGetDashboard() {
        System.out.println("Testing get dashboard API");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200,
                "Get dashboard should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            ResponseValidator.validate(response)
                    .assertJsonContentType()
                    .assertBodyNotEmpty();
        }
    }

    @Test(priority = 2, description = "Test fetching dashboard summary")
    public void testGetDashboardSummary() {
        System.out.println("Testing get dashboard summary API");
        Response response = dashboardApi.getDashboardSummary();
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.getString("message");
        Assert.assertEquals(statusCode, 200, "Expected status code 200");
        Assert.assertEquals(message, "Dashboard data fetched successfully", "Message mismatch");
    }


    @Test(priority = 3, description = "Verify the dashboard response time for an Request")
    public void testDashboardResponseTime() {
        System.out.println("Testing dashboard API response time");
        Response response = dashboardApi.getDashboard();
        long responseTime = response.getTime();
        System.out.println("Dashboard API response time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < 10000,
                "Dashboard API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 4, description = "Test dashboard response content type is JSON")
    public void testDashboardResponseContentType() {
        System.out.println("Testing dashboard response content type");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 5, description = "Test accessing dashboard without authentication token")
    public void testDashboardWithoutAuthentication() {
        System.out.println("Testing dashboard API without authentication token");
        // Clear the token temporarily
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();
        // Create new DashboardApi instance without token
        DashboardApi unauthenticatedApi = new DashboardApi(false);
        Response response = unauthenticatedApi.getDashboard();
        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 401, "The status code mismatches for an API withgout authentication");
        // Restore the token
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }

    @Test(priority = 6, description = "Test dashboard concurrent requests")
    public void testDashboardConcurrentRequests() throws InterruptedException {
        int requestCount = 25;
        ExecutorService executorService = Executors.newFixedThreadPool(requestCount);
        CountDownLatch latch = new CountDownLatch(requestCount);
        for (int i = 0; i < requestCount; i++) {
            final int requestNumber = i + 1;
            executorService.submit(() -> {
                try {
                    Response response = dashboardApi.getDashboard();
                    int statusCode = response.getStatusCode();
                    System.out.println(" | Thread: " + Thread.currentThread().getName() + " | Request #" + requestNumber + " | Status Code: " + statusCode);
                    Assert.assertEquals(statusCode, 200, "Unexpected status code for request #" + requestNumber);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();
    }
    @Test(priority = 7, description = "Verify the Activity count")
    public void VerifyActivityCount() {
        System.out.println("Verify the Activity count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalAccess");
        Assert.assertEquals(failureCount, "71", "The Activity count mismatches");

    }
    @Test(priority = 8, description = "Verify the Total Roles count")
    public void VerifyTotalRolesCount() {
        System.out.println("Verify the Total Roles count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalRoles");
        Assert.assertEquals(failureCount, "29", "The Total Roles count mismatches");

    }
    @Test(priority = 9, description = "Verify the Total Users count")
    public void VerifyTotalUsersCount() {
        System.out.println("Verify the Total Users count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalUsers");
        Assert.assertEquals(failureCount, "40", "The Total Users count mismatches");

    }
    @Test(priority = 10, description = "Verify the Web Users count")
    public void VerifyWebUsersCount() {
        System.out.println("Verify the Web Users count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalWebUsers");
        Assert.assertEquals(failureCount, "35", "The Web Users count mismatches");

    }
    @Test(priority = 11, description = "Verify the Key Safe Users count")
    public void VerifyKeySafeUsersCount() {
        System.out.println("Verify the Key Safe Users count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalKeySafeUsers");
        Assert.assertEquals(failureCount, "23", "The Key Safe Users count mismatches");

    }
    @Test(priority = 12, description = "Verify the Total Key Safes count")
    public void VerifyTotalKeySafesCount() {
        System.out.println("Verify the Total Key Safes count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalKeySafes");
        Assert.assertEquals(failureCount, "22", "The Total Key Safes count mismatches");

    }
    @Test(priority = 13, description = "Verify the Registered Key Safes count")
    public void VerifyRegisteredKeySafesCount() {
        System.out.println("Verify the Registered Key Safes count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalRegisteredKeySafes");
        Assert.assertEquals(failureCount, "14", "The Registered Key Safes count mismatches");

    }
    @Test(priority = 14, description = "Verify the Unregistered Key Safes")
    public void VerifyUnregisteredKeySafesCount() {
        System.out.println("Verify the Unregistered Key Safes count");
        Response response = dashboardApi.getDashboard();
        int statusCode = response.getStatusCode();
        String failureCount =  response.jsonPath().getString("data.totalUnregisteredKeySafes");
        Assert.assertEquals(failureCount, "8", "The Unregistered Key Safes count mismatches");

    }

}
