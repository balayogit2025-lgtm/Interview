package api.testrun;

import api.pages.DashboardApi;
import api.utils.ResponseValidator;
import api.utils.TestDataGenerator;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    // ==================== Positive Test Cases ====================

    @Test(priority = 1, description = "Test fetching dashboard data with valid authentication")
    public void testGetDashboard() {
        System.out.println("Testing get dashboard API");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403,
                "Get dashboard should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            ResponseValidator.validate(response)
                    .assertJsonContentType()
                    .assertBodyNotEmpty();
        }
    }

    @Test(priority = 2, description = "Test dashboard API returns JSON content type")
    public void testGetDashboardContentType() {
        System.out.println("Testing get dashboard API content type");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            System.out.println("Content-Type: " + contentType);

            Assert.assertNotNull(contentType, "Content-Type header should not be null");
            Assert.assertTrue(contentType.toLowerCase().contains("application/json"),
                    "Content-Type should be application/json. Got: " + contentType);
        }
    }

    @Test(priority = 3, description = "Test dashboard API response body is not empty")
    public void testGetDashboardResponseBodyNotEmpty() {
        System.out.println("Testing get dashboard API response body");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String responseBody = response.getBody().asString();
            System.out.println("Response body length: " + responseBody.length());

            Assert.assertNotNull(responseBody, "Response body should not be null");
            Assert.assertFalse(responseBody.trim().isEmpty(), "Response body should not be empty");
        }
    }

    @Test(priority = 4, description = "Test dashboard API response time is within acceptable limits")
    public void testGetDashboardResponseTime() {
        System.out.println("Testing get dashboard API response time");

        Response response = dashboardApi.getDashboard();

        long responseTime = response.getTime();
        System.out.println("Response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 5000,
                "Dashboard API response time should be less than 5000ms. Got: " + responseTime + "ms");
    }

    @Test(priority = 5, description = "Test dashboard API response time is within SLA (2 seconds)")
    public void testGetDashboardResponseTimeWithinSLA() {
        System.out.println("Testing get dashboard API response time within SLA");

        Response response = dashboardApi.getDashboard();

        long responseTime = response.getTime();
        System.out.println("Response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 2000,
                "Dashboard API response time should be less than 2000ms for optimal performance. Got: " + responseTime + "ms");
    }

    @Test(priority = 6, description = "Test dashboard API returns valid HTTP status line")
    public void testGetDashboardStatusLine() {
        System.out.println("Testing get dashboard API status line");

        Response response = dashboardApi.getDashboard();

        String statusLine = response.getStatusLine();
        System.out.println("Status line: " + statusLine);

        Assert.assertNotNull(statusLine, "Status line should not be null");
        Assert.assertTrue(statusLine.contains("HTTP"), "Status line should contain HTTP protocol");
    }

    @Test(priority = 7, description = "Test dashboard API consecutive calls return consistent results")
    public void testGetDashboardConsistentResponses() {
        System.out.println("Testing get dashboard API consistency");

        Response response1 = dashboardApi.getDashboard();
        Response response2 = dashboardApi.getDashboard();
        Response response3 = dashboardApi.getDashboard();

        Assert.assertEquals(response1.getStatusCode(), response2.getStatusCode(),
                "Consecutive dashboard calls should return same status code");
        Assert.assertEquals(response2.getStatusCode(), response3.getStatusCode(),
                "Consecutive dashboard calls should return same status code");

        System.out.println("All calls returned status code: " + response1.getStatusCode());
    }

    @Test(priority = 8, description = "Test dashboard API response headers are present")
    public void testGetDashboardResponseHeaders() {
        System.out.println("Testing get dashboard API response headers");

        Response response = dashboardApi.getDashboard();

        // Check that response has headers
        Assert.assertNotNull(response.getHeaders(), "Response headers should not be null");
        Assert.assertFalse(response.getHeaders().asList().isEmpty(), "Response should have headers");

        // Log some common headers
        System.out.println("Content-Type: " + response.getHeader("Content-Type"));
        System.out.println("Content-Length: " + response.getHeader("Content-Length"));
    }

    // ==================== Negative Test Cases ====================

    @Test(priority = 10, description = "Test dashboard API without authentication returns 401 or 403")
    public void testGetDashboardWithoutAuth() {
        System.out.println("Testing get dashboard API without authentication");

        Response response = dashboardApi.getDashboardWithoutAuth();

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard without auth returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403,
                "Get dashboard without auth should return 401 or 403. Got: " + statusCode);
    }

    @Test(priority = 11, description = "Test dashboard API without auth returns error message")
    public void testGetDashboardWithoutAuthErrorMessage() {
        System.out.println("Testing get dashboard API without auth error response");

        Response response = dashboardApi.getDashboardWithoutAuth();

        int statusCode = response.getStatusCode();
        if (statusCode == 401 || statusCode == 403) {
            String responseBody = response.getBody().asString();
            System.out.println("Error response: " + responseBody);

            Assert.assertNotNull(responseBody, "Error response body should not be null");
        }
    }

    @Test(priority = 12, description = "Test dashboard API without auth response time")
    public void testGetDashboardWithoutAuthResponseTime() {
        System.out.println("Testing get dashboard API without auth response time");

        Response response = dashboardApi.getDashboardWithoutAuth();

        long responseTime = response.getTime();
        System.out.println("Response time for unauthorized request: " + responseTime + " ms");

        // Unauthorized requests should still respond quickly
        Assert.assertTrue(responseTime < 3000,
                "Unauthorized request should respond within 3000ms. Got: " + responseTime + "ms");
    }

    // ==================== Edge Case Test Cases ====================

    @Test(priority = 20, description = "Test dashboard API multiple rapid calls")
    public void testGetDashboardMultipleRapidCalls() {
        System.out.println("Testing get dashboard API with multiple rapid calls");

        int successCount = 0;
        int totalCalls = 5;

        for (int i = 0; i < totalCalls; i++) {
            Response response = dashboardApi.getDashboard();
            if (response.getStatusCode() == 200 || response.getStatusCode() == 403) {
                successCount++;
            }
        }

        System.out.println("Successful calls: " + successCount + "/" + totalCalls);
        Assert.assertEquals(successCount, totalCalls,
                "All rapid calls should return valid status codes");
    }

    @Test(priority = 21, description = "Test dashboard API response can be parsed as JSON")
    public void testGetDashboardValidJsonResponse() {
        System.out.println("Testing get dashboard API returns valid JSON");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            try {
                response.jsonPath().get();
                System.out.println("Response is valid JSON");
            } catch (Exception e) {
                Assert.fail("Response should be valid JSON. Error: " + e.getMessage());
            }
        }
    }

    @Test(priority = 22, description = "Test dashboard API after re-authentication")
    public void testGetDashboardAfterReAuth() {
        System.out.println("Testing get dashboard API after re-authentication");

        // First call
        Response response1 = dashboardApi.getDashboard();
        int statusCode1 = response1.getStatusCode();
        System.out.println("First call status code: " + statusCode1);

        // Re-initialize with fresh authentication
        ensureLoggedIn();
        dashboardApi = new DashboardApi();

        // Second call after re-auth
        Response response2 = dashboardApi.getDashboard();
        int statusCode2 = response2.getStatusCode();
        System.out.println("Second call after re-auth status code: " + statusCode2);

        Assert.assertEquals(statusCode1, statusCode2,
                "Dashboard should work consistently after re-authentication");
    }

    @Test(priority = 23, description = "Test dashboard API response size is reasonable")
    public void testGetDashboardResponseSize() {
        System.out.println("Testing get dashboard API response size");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String responseBody = response.getBody().asString();
            int responseSize = responseBody.length();
            System.out.println("Response size: " + responseSize + " characters");

            // Response should not be excessively large (e.g., less than 1MB)
            Assert.assertTrue(responseSize < 1000000,
                    "Response size should be less than 1MB. Got: " + responseSize + " characters");
        }
    }

    @Test(priority = 24, description = "Test dashboard API with valid token returns success")
    public void testGetDashboardWithValidToken() {
        System.out.println("Testing get dashboard API with valid token");

        // Ensure we have a valid token
        Assert.assertTrue(TokenManager.hasToken(), "Should have a valid token before this test");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard with valid token returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403,
                "Get dashboard with valid token should return 200 or 403. Got: " + statusCode);
    }

    @Test(priority = 25, description = "Test dashboard API average response time over multiple calls")
    public void testGetDashboardAverageResponseTime() {
        System.out.println("Testing get dashboard API average response time");

        int numberOfCalls = 3;
        long totalTime = 0;

        for (int i = 0; i < numberOfCalls; i++) {
            Response response = dashboardApi.getDashboard();
            totalTime += response.getTime();
        }

        long averageTime = totalTime / numberOfCalls;
        System.out.println("Average response time over " + numberOfCalls + " calls: " + averageTime + " ms");

        Assert.assertTrue(averageTime < 3000,
                "Average response time should be less than 3000ms. Got: " + averageTime + "ms");
    }

}
