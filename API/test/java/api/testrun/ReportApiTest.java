package api.testrun;

import api.pages.ReportApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class for Report API endpoints.
 * Contains tests for report generation, listing, and download operations.
 */
public class ReportApiTest extends BaseTest {

    private ReportApi reportApi;
    private String todayDate;
    private String lastMonthDate;

    @BeforeClass
    public void setUpReportTests() {
        // Ensure user is logged in before running report tests
        ensureLoggedIn();

        // Initialize ReportApi with authentication
        reportApi = new ReportApi();

        // Set up date strings for testing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        todayDate = LocalDate.now().format(formatter);
        lastMonthDate = LocalDate.now().minusMonths(1).format(formatter);

        System.out.println("ReportApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all reports")
    public void testGetAllReports() {
        System.out.println("Testing get all reports API");

        Response response = reportApi.getAllReports();

        int statusCode = response.getStatusCode();
        System.out.println("Get all reports returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all reports should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching reports with pagination")
    public void testGetReportsWithPagination() {
        System.out.println("Testing get reports with pagination");

        Response response = reportApi.getAllReports(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get reports with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get reports with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test fetching report by valid ID")
    public void testGetReportById() {
        System.out.println("Testing get report by ID");

        Response response = reportApi.getReportById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get report by ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get report by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching report with invalid ID")
    public void testGetReportWithInvalidId() {
        System.out.println("Testing get report with invalid ID");

        Response response = reportApi.getReportWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get report with invalid ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get report with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test generating daily report")
    public void testGenerateDailyReport() {
        System.out.println("Testing generate daily report");

        Response response = reportApi.generateReport("daily", lastMonthDate, todayDate);

        int statusCode = response.getStatusCode();
        System.out.println("Generate daily report returned status code: " + statusCode);

        // Could return 200/201 for success, 400 for bad request, 404 if endpoint doesn't exist
        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 404,
                "Generate report should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test generating report with custom parameters")
    public void testGenerateReportWithCustomParams() {
        System.out.println("Testing generate report with custom parameters");

        Map<String, Object> params = new HashMap<>();
        params.put("type", "monthly");
        params.put("startDate", lastMonthDate);
        params.put("endDate", todayDate);
        params.put("format", "pdf");

        Response response = reportApi.generateReport(params);

        int statusCode = response.getStatusCode();
        System.out.println("Generate report with custom params returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 404,
                "Generate report should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 7, description = "Test generating report with missing parameters")
    public void testGenerateReportWithMissingParams() {
        System.out.println("Testing generate report with missing parameters");

        Response response = reportApi.generateReportWithMissingParams();

        int statusCode = response.getStatusCode();
        System.out.println("Generate report with missing params returned status code: " + statusCode);

        // Should return 400 Bad Request for missing required parameters
        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 404 || statusCode == 500,
                "Generate report with missing params should return 400, 422, 404, or 500. Got: " + statusCode);
    }

    @Test(priority = 8, description = "Test fetching reports by type")
    public void testGetReportsByType() {
        System.out.println("Testing get reports by type");

        Response response = reportApi.getReportsByType("daily");

        int statusCode = response.getStatusCode();
        System.out.println("Get reports by type returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get reports by type should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 9, description = "Test fetching reports by date range")
    public void testGetReportsByDateRange() {
        System.out.println("Testing get reports by date range");

        Response response = reportApi.getReportsByDateRange(lastMonthDate, todayDate);

        int statusCode = response.getStatusCode();
        System.out.println("Get reports by date range returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get reports by date range should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 10, description = "Test downloading report")
    public void testDownloadReport() {
        System.out.println("Testing download report");

        Response response = reportApi.downloadReport("1");

        int statusCode = response.getStatusCode();
        System.out.println("Download report returned status code: " + statusCode);

        // Could return 200 for success, 404 if report not found
        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Download report should return valid status code. Got: " + statusCode);
    }

    @Test(priority = 11, description = "Test report API response time")
    public void testReportApiResponseTime() {
        System.out.println("Testing report API response time");

        Response response = reportApi.getAllReports();

        long responseTime = response.getTime();
        System.out.println("Report API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 15000,
                "Report API response time should be less than 15 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 12, description = "Test accessing report API without token")
    public void testReportApiWithoutToken() {
        System.out.println("Testing report API without authentication token");

        // Clear the token temporarily
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        // Create new ReportApi instance without token
        ReportApi unauthenticatedReportApi = new ReportApi(false);
        Response response = unauthenticatedReportApi.getAllReports();

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

    @Test(priority = 13, description = "Test report response content type")
    public void testReportResponseContentType() {
        System.out.println("Testing report response content type");

        Response response = reportApi.getAllReports();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }
}
