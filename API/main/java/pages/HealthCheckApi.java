package pages;

import base.BaseApi;
import constants.EndPoints;
import io.restassured.response.Response;

/**
 * Page class for Health Check and common API operations.
 * Handles health check and version information endpoints.
 */
public class HealthCheckApi extends BaseApi {

    /**
     * Default constructor - no authentication required for health checks
     */
    public HealthCheckApi() {
        super(false);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public HealthCheckApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Check API health status
     * @return API response with health status
     */
    public Response getHealthCheck() {
        System.out.println("Checking API health status");

        Response response = getRequestSpec()
                .get(EndPoints.HEALTH_CHECK);

        logResponse(response);
        return response;
    }

    /**
     * Get API version information
     * @return API response with version info
     */
    public Response getVersion() {
        System.out.println("Fetching API version information");

        Response response = getRequestSpec()
                .get(EndPoints.VERSION);

        logResponse(response);
        return response;
    }

    /**
     * Check if API is responsive by checking health endpoint
     * @return true if API returns 200 status code
     */
    public boolean isApiHealthy() {
        try {
            Response response = getHealthCheck();
            return response.getStatusCode() == 200;
        } catch (Exception e) {
            System.err.println("Health check failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get version string from response
     * @return version string or null if not available
     */
    public String getVersionString() {
        try {
            Response response = getVersion();
            if (response.getStatusCode() == 200) {
                // Try common paths for version info
                String version = response.jsonPath().getString("version");
                if (version == null) {
                    version = response.jsonPath().getString("data.version");
                }
                if (version == null) {
                    version = response.jsonPath().getString("appVersion");
                }
                return version;
            }
        } catch (Exception e) {
            System.err.println("Failed to get version: " + e.getMessage());
        }
        return null;
    }
}
