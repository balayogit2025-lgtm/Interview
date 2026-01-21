package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Dashboard related API operations.
 * Handles dashboard data retrieval, statistics, and summary operations.
 */
public class DashboardApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public DashboardApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public DashboardApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get dashboard main data
     * @return API response with dashboard data
     */
    public Response getDashboard() {
        System.out.println("Fetching dashboard data");

        Response response = getRequestSpec()
                .get(EndPoints.DASHBOARD);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard statistics
     * @return API response with dashboard statistics
     */
    public Response getDashboardStats() {
        System.out.println("Fetching dashboard statistics");

        Response response = getRequestSpec()
                .get(EndPoints.DASHBOARD_STATS);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard summary
     * @return API response with dashboard summary
     */
    public Response getDashboardSummary() {
        System.out.println("Fetching dashboard summary");

        Response response = getRequestSpec()
                .get(EndPoints.DASHBOARD_SUMMARY);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard with date range filter
     * @param startDate start date for filtering
     * @param endDate end date for filtering
     * @return API response with filtered dashboard data
     */
    public Response getDashboardByDateRange(String startDate, String endDate) {
        System.out.println("Fetching dashboard data from " + startDate + " to " + endDate);

        Response response = getRequestSpec()
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .get(EndPoints.DASHBOARD);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard stats with specific parameters
     * @param params query parameters for filtering stats
     * @return API response with filtered statistics
     */
    public Response getDashboardStats(Map<String, String> params) {
        System.out.println("Fetching dashboard statistics with parameters");

        var requestSpec = getRequestSpec();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            requestSpec.queryParam(entry.getKey(), entry.getValue());
        }

        Response response = requestSpec.get(EndPoints.DASHBOARD_STATS);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard with specific stat type
     * @param statType the type of statistic to retrieve
     * @return API response with specific statistics
     */
    public Response getDashboardStatsByType(String statType) {
        System.out.println("Fetching dashboard statistics for type: " + statType);

        Response response = getRequestSpec()
                .queryParam("type", statType)
                .get(EndPoints.DASHBOARD_STATS);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard summary with period filter
     * @param period the time period (e.g., "day", "week", "month", "year")
     * @return API response with period-based summary
     */
    public Response getDashboardSummaryByPeriod(String period) {
        System.out.println("Fetching dashboard summary for period: " + period);

        Response response = getRequestSpec()
                .queryParam("period", period)
                .get(EndPoints.DASHBOARD_SUMMARY);

        logResponse(response);
        return response;
    }

    // ==================== Negative Test Methods ====================

    /**
     * Get dashboard without authentication token for negative testing
     * @return API response
     */
    public Response getDashboardWithoutAuth() {
        System.out.println("Fetching dashboard without authentication (negative test)");

        // Create new instance without auth
        DashboardApi unauthApi = new DashboardApi(false);
        return unauthApi.getDashboard();
    }

    /**
     * Get dashboard with invalid date range for negative testing
     * @return API response
     */
    public Response getDashboardWithInvalidDateRange() {
        System.out.println("Fetching dashboard with invalid date range (negative test)");

        Response response = getRequestSpec()
                .queryParam("startDate", "invalid-date")
                .queryParam("endDate", "also-invalid")
                .get(EndPoints.DASHBOARD);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard with future dates for negative testing
     * @return API response
     */
    public Response getDashboardWithFutureDates() {
        System.out.println("Fetching dashboard with future dates (negative test)");

        Response response = getRequestSpec()
                .queryParam("startDate", "2099-01-01")
                .queryParam("endDate", "2099-12-31")
                .get(EndPoints.DASHBOARD);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard stats with invalid stat type for negative testing
     * @return API response
     */
    public Response getDashboardStatsWithInvalidType() {
        System.out.println("Fetching dashboard stats with invalid type (negative test)");

        Response response = getRequestSpec()
                .queryParam("type", "invalid_stat_type_xyz")
                .get(EndPoints.DASHBOARD_STATS);

        logResponse(response);
        return response;
    }

    /**
     * Get dashboard summary with invalid period for negative testing
     * @return API response
     */
    public Response getDashboardSummaryWithInvalidPeriod() {
        System.out.println("Fetching dashboard summary with invalid period (negative test)");

        Response response = getRequestSpec()
                .queryParam("period", "invalid_period")
                .get(EndPoints.DASHBOARD_SUMMARY);

        logResponse(response);
        return response;
    }



}
