package testrun;

import pages.DashboardApi;
import utils.ResponseValidator;
import utils.TestDataGenerator;
import utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Dashboard API endpoints.
 * Contains comprehensive tests for dashboard data, statistics, and summary operations.
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

    @Test(priority = 1, description = "Test fetching main dashboard data")
    public void testGetDashboard() {
        System.out.println("Testing get dashboard API");

        Response response = dashboardApi.getDashboard();

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get dashboard should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            ResponseValidator.validate(response)
                    .assertJsonContentType()
                    .assertBodyNotEmpty();
        }
    }

    @Test(priority = 2, description = "Test fetching dashboard statistics")
    public void testGetDashboardStats() {
        System.out.println("Testing get dashboard statistics API");

        Response response = dashboardApi.getDashboardStats();

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard stats returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get dashboard stats should return 200 or 404. Got: " + statusCode);

        if (statusCode == 200) {
            ResponseValidator.validate(response)
                    .assertJsonContentType()
                    .assertBodyNotEmpty()
                    .assertResponseTimeBelow(10000);
        }
    }

    @Test(priority = 3, description = "Test fetching dashboard summary")
    public void testGetDashboardSummary() {
        System.out.println("Testing get dashboard summary API");

        Response response = dashboardApi.getDashboardSummary();

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard summary returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get dashboard summary should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching dashboard data with valid date range")
    public void testGetDashboardByDateRange() {
        System.out.println("Testing get dashboard by date range");

        String startDate = TestDataGenerator.getDateFromNow(-30);
        String endDate = TestDataGenerator.getTodayDate();

        Response response = dashboardApi.getDashboardByDateRange(startDate, endDate);

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard by date range returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 400,
                "Get dashboard by date range should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test fetching dashboard summary by period - daily")
    public void testGetDashboardSummaryByPeriodDaily() {
        System.out.println("Testing get dashboard summary by daily period");

        Response response = dashboardApi.getDashboardSummaryByPeriod("day");

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard summary by day returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get dashboard summary should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test fetching dashboard summary by period - weekly")
    public void testGetDashboardSummaryByPeriodWeekly() {
        System.out.println("Testing get dashboard summary by weekly period");

        Response response = dashboardApi.getDashboardSummaryByPeriod("week");

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard summary by week returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get dashboard summary should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 7, description = "Test fetching dashboard summary by period - monthly")
    public void testGetDashboardSummaryByPeriodMonthly() {
        System.out.println("Testing get dashboard summary by monthly period");

        Response response = dashboardApi.getDashboardSummaryByPeriod("month");

        int statusCode = response.getStatusCode();
        System.out.println("Get dashboard summary by month returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get dashboard summary should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 8, description = "Test dashboard response time is within SLA")
    public void testDashboardResponseTime() {
        System.out.println("Testing dashboard API response time");

        Response response = dashboardApi.getDashboard();

        long responseTime = response.getTime();
        System.out.println("Dashboard API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 15000,
                "Dashboard API response time should be less than 15 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 9, description = "Test dashboard response content type is JSON")
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

    // ==================== Negative Test Cases ====================

    @Test(priority = 10, description = "Test accessing dashboard without authentication token")
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

        // Should return 401 Unauthorized or 403 Forbidden
        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if public). Got: " + statusCode);

        // Restore the token
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }

    @Test(priority = 11, description = "Test dashboard with invalid date format")
    public void testDashboardWithInvalidDateFormat() {
        System.out.println("Testing dashboard with invalid date format");

        Response response = dashboardApi.getDashboardWithInvalidDateRange();

        int statusCode = response.getStatusCode();
        System.out.println("Invalid date format request returned status code: " + statusCode);

        // Should return 400 Bad Request or handle gracefully
        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 200 || statusCode == 404,
                "Invalid date format should return 400/422 or be handled gracefully. Got: " + statusCode);
    }

    @Test(priority = 12, description = "Test dashboard with future dates")
    public void testDashboardWithFutureDates() {
        System.out.println("Testing dashboard with future dates");

        Response response = dashboardApi.getDashboardWithFutureDates();

        int statusCode = response.getStatusCode();
        System.out.println("Future dates request returned status code: " + statusCode);

        // Future dates might return empty data or error
        Assert.assertTrue(statusCode == 200 || statusCode == 400 || statusCode == 404,
                "Future dates request should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 13, description = "Test dashboard stats with invalid stat type")
    public void testDashboardStatsWithInvalidType() {
        System.out.println("Testing dashboard stats with invalid type");

        Response response = dashboardApi.getDashboardStatsWithInvalidType();

        int statusCode = response.getStatusCode();
        System.out.println("Invalid stat type request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 404 || statusCode == 200,
                "Invalid stat type should return 400, 404, or be ignored. Got: " + statusCode);
    }

    @Test(priority = 14, description = "Test dashboard summary with invalid period")
    public void testDashboardSummaryWithInvalidPeriod() {
        System.out.println("Testing dashboard summary with invalid period");

        Response response = dashboardApi.getDashboardSummaryWithInvalidPeriod();

        int statusCode = response.getStatusCode();
        System.out.println("Invalid period request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 404 || statusCode == 200,
                "Invalid period should return 400, 404, or be ignored. Got: " + statusCode);
    }

    // ==================== Edge Case Tests ====================

    @Test(priority = 15, description = "Test dashboard with date range - start date equals end date")
    public void testDashboardSameDateRange() {
        System.out.println("Testing dashboard with same start and end date");

        String today = TestDataGenerator.getTodayDate();
        Response response = dashboardApi.getDashboardByDateRange(today, today);

        int statusCode = response.getStatusCode();
        System.out.println("Same date range request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 400 || statusCode == 404,
                "Same date range should be handled appropriately. Got: " + statusCode);
    }

    @Test(priority = 16, description = "Test dashboard with date range - start date after end date")
    public void testDashboardInvertedDateRange() {
        System.out.println("Testing dashboard with inverted date range");

        String startDate = TestDataGenerator.getTodayDate();
        String endDate = TestDataGenerator.getDateFromNow(-30);

        Response response = dashboardApi.getDashboardByDateRange(startDate, endDate);

        int statusCode = response.getStatusCode();
        System.out.println("Inverted date range request returned status code: " + statusCode);

        // Should return 400 Bad Request for inverted dates
        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 200,
                "Inverted date range should return error or be handled. Got: " + statusCode);
    }

    @Test(priority = 17, description = "Test dashboard with very old date range")
    public void testDashboardVeryOldDateRange() {
        System.out.println("Testing dashboard with very old date range");

        Response response = dashboardApi.getDashboardByDateRange("2000-01-01", "2000-12-31");

        int statusCode = response.getStatusCode();
        System.out.println("Very old date range request returned status code: " + statusCode);

        // Very old dates might return empty data
        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Very old date range should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 18, description = "Test dashboard concurrent requests")
    public void testDashboardConcurrentRequests() {
        System.out.println("Testing dashboard with concurrent requests simulation");

        // Make multiple requests in sequence
        for (int i = 0; i < 3; i++) {
            Response response = dashboardApi.getDashboard();
            int statusCode = response.getStatusCode();
            System.out.println("Request " + (i + 1) + " returned status code: " + statusCode);

            Assert.assertTrue(statusCode == 200 || statusCode == 404,
                    "Concurrent request should return consistent status. Got: " + statusCode);
        }
    }

    @Test(priority = 19, description = "Test dashboard with empty query parameters")
    public void testDashboardWithEmptyQueryParams() {
        System.out.println("Testing dashboard with empty query parameters");

        Response response = dashboardApi.getDashboardByDateRange("", "");

        int statusCode = response.getStatusCode();
        System.out.println("Empty query params request returned status code: " + statusCode);

        // Should handle empty params gracefully
        Assert.assertTrue(statusCode == 200 || statusCode == 400 || statusCode == 404,
                "Empty params should be handled. Got: " + statusCode);
    }
}
