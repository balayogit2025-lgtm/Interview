package pages;

import base.BaseApi;
import constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Device Activity related API operations.
 * Handles read operations for device activities and logs.
 */
public class DeviceActivityApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public DeviceActivityApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public DeviceActivityApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all device activities
     * @return API response with list of device activities
     */
    public Response getAllDeviceActivities() {
        System.out.println("Fetching all device activities");

        Response response = getRequestSpec()
                .get(EndPoints.DEVICE_ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Get device activities with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated device activities
     */
    public Response getAllDeviceActivities(int page, int size) {
        System.out.println("Fetching device activities - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.DEVICE_ACTIVITY_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get device activity by ID
     * @param activityId the device activity ID
     * @return API response with device activity details
     */
    public Response getDeviceActivityById(String activityId) {
        System.out.println("Fetching device activity with ID: " + activityId);

        Response response = getRequestSpec()
                .pathParam("id", activityId)
                .get(EndPoints.DEVICE_ACTIVITY_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get device activity by ID (integer)
     * @param activityId the device activity ID
     * @return API response with device activity details
     */
    public Response getDeviceActivityById(int activityId) {
        return getDeviceActivityById(String.valueOf(activityId));
    }

    /**
     * Get device activities by device ID
     * @param deviceId the device ID
     * @return API response with activities for the device
     */
    public Response getActivitiesByDevice(String deviceId) {
        System.out.println("Fetching activities for device: " + deviceId);

        Response response = getRequestSpec()
                .pathParam("deviceId", deviceId)
                .get(EndPoints.DEVICE_ACTIVITY_BY_DEVICE);

        logResponse(response);
        return response;
    }

    /**
     * Get device activities by device ID (integer)
     * @param deviceId the device ID
     * @return API response with activities for the device
     */
    public Response getActivitiesByDevice(int deviceId) {
        return getActivitiesByDevice(String.valueOf(deviceId));
    }

    /**
     * Get device activities within a date range
     * @param startDate start date (format: yyyy-MM-dd)
     * @param endDate end date (format: yyyy-MM-dd)
     * @return API response with filtered activities
     */
    public Response getActivitiesByDateRange(String startDate, String endDate) {
        System.out.println("Fetching device activities from " + startDate + " to " + endDate);

        Response response = getRequestSpec()
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .get(EndPoints.DEVICE_ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Get device activities by activity type
     * @param activityType the type of activity (e.g., "KEY_PICKUP", "KEY_RETURN")
     * @return API response with filtered activities
     */
    public Response getActivitiesByType(String activityType) {
        System.out.println("Fetching device activities of type: " + activityType);

        Response response = getRequestSpec()
                .queryParam("type", activityType)
                .get(EndPoints.DEVICE_ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Get device activities by user
     * @param userId the user ID
     * @return API response with activities by the user
     */
    public Response getActivitiesByUser(String userId) {
        System.out.println("Fetching device activities for user: " + userId);

        Response response = getRequestSpec()
                .queryParam("userId", userId)
                .get(EndPoints.DEVICE_ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Search device activities
     * @param searchQuery the search query
     * @return API response with matching activities
     */
    public Response searchDeviceActivities(String searchQuery) {
        System.out.println("Searching device activities with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.DEVICE_ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Get device activity with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getDeviceActivityWithInvalidId() {
        return getDeviceActivityById("invalid-id-12345");
    }

    /**
     * Get device activities with filters
     * @param filters map containing filter parameters
     * @return API response with filtered activities
     */
    public Response getDeviceActivitiesWithFilters(Map<String, String> filters) {
        System.out.println("Fetching device activities with filters");

        var requestSpec = getRequestSpec();
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            requestSpec.queryParam(filter.getKey(), filter.getValue());
        }

        Response response = requestSpec.get(EndPoints.DEVICE_ACTIVITIES);

        logResponse(response);
        return response;
    }
}
