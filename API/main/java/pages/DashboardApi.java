package pages;

import base.BaseApi;
import constants.EndPoints;
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



}
