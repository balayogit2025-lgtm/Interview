package pages;

import base.BaseApi;
import constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Report related API operations.
 * Handles report generation, listing, and download operations.
 */
public class ReportApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public ReportApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public ReportApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all reports
     * @return API response with list of reports
     */
    public Response getAllReports() {
        System.out.println("Fetching all reports");

        Response response = getRequestSpec()
                .get(EndPoints.REPORTS);

        logResponse(response);
        return response;
    }

    /**
     * Get reports with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated reports
     */
    public Response getAllReports(int page, int size) {
        System.out.println("Fetching reports - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.REPORT_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get report by ID
     * @param reportId the report ID
     * @return API response with report details
     */
    public Response getReportById(String reportId) {
        System.out.println("Fetching report with ID: " + reportId);

        Response response = getRequestSpec()
                .pathParam("id", reportId)
                .get(EndPoints.REPORT_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get report by ID (integer)
     * @param reportId the report ID
     * @return API response with report details
     */
    public Response getReportById(int reportId) {
        return getReportById(String.valueOf(reportId));
    }

    /**
     * Generate a new report
     * @param reportType type of report (e.g., "daily", "weekly", "monthly")
     * @param startDate start date for report data
     * @param endDate end date for report data
     * @return API response
     */
    public Response generateReport(String reportType, String startDate, String endDate) {
        System.out.println("Generating " + reportType + " report from " + startDate + " to " + endDate);

        Map<String, Object> reportRequest = new HashMap<>();
        reportRequest.put("type", reportType);
        reportRequest.put("startDate", startDate);
        reportRequest.put("endDate", endDate);

        Response response = getRequestSpec()
                .body(reportRequest)
                .post(EndPoints.REPORT_GENERATE);

        logResponse(response);
        return response;
    }

    /**
     * Generate report with custom parameters
     * @param reportParams map containing report parameters
     * @return API response
     */
    public Response generateReport(Map<String, Object> reportParams) {
        System.out.println("Generating report with custom parameters");

        Response response = getRequestSpec()
                .body(reportParams)
                .post(EndPoints.REPORT_GENERATE);

        logResponse(response);
        return response;
    }

    /**
     * Download report by ID
     * @param reportId the report ID
     * @return API response with report file
     */
    public Response downloadReport(String reportId) {
        System.out.println("Downloading report with ID: " + reportId);

        Response response = getRequestSpec()
                .pathParam("id", reportId)
                .get(EndPoints.REPORT_DOWNLOAD);

        logResponse(response);
        return response;
    }

    /**
     * Download report by ID (integer)
     * @param reportId the report ID
     * @return API response with report file
     */
    public Response downloadReport(int reportId) {
        return downloadReport(String.valueOf(reportId));
    }

    /**
     * Get reports filtered by type
     * @param reportType the report type to filter by
     * @return API response with filtered reports
     */
    public Response getReportsByType(String reportType) {
        System.out.println("Fetching reports of type: " + reportType);

        Response response = getRequestSpec()
                .queryParam("type", reportType)
                .get(EndPoints.REPORTS);

        logResponse(response);
        return response;
    }

    /**
     * Get reports within date range
     * @param startDate start date
     * @param endDate end date
     * @return API response with filtered reports
     */
    public Response getReportsByDateRange(String startDate, String endDate) {
        System.out.println("Fetching reports from " + startDate + " to " + endDate);

        Response response = getRequestSpec()
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .get(EndPoints.REPORTS);

        logResponse(response);
        return response;
    }

    /**
     * Delete report by ID
     * @param reportId the report ID
     * @return API response
     */
    public Response deleteReport(String reportId) {
        System.out.println("Deleting report with ID: " + reportId);

        Response response = getRequestSpec()
                .pathParam("id", reportId)
                .delete(EndPoints.REPORT_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get report with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getReportWithInvalidId() {
        return getReportById("invalid-report-id-12345");
    }

    /**
     * Generate report with missing parameters for negative testing
     * @return API response
     */
    public Response generateReportWithMissingParams() {
        System.out.println("Generating report with missing parameters (negative test)");

        Map<String, Object> reportRequest = new HashMap<>();
        // Intentionally empty to test validation

        Response response = getRequestSpec()
                .body(reportRequest)
                .post(EndPoints.REPORT_GENERATE);

        logResponse(response);
        return response;
    }
}
