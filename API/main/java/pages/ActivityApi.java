package pages;

import base.BaseApi;
import constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Activity Management related API operations.
 * Handles CRUD operations for activities.
 */
public class ActivityApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public ActivityApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public ActivityApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all activities
     * @return API response with list of activities
     */
    public Response getAllActivities() {
        System.out.println("Fetching all activities");

        Response response = getRequestSpec()
                .get(EndPoints.ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Get activities with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated activities
     */
    public Response getAllActivities(int page, int size) {
        System.out.println("Fetching activities - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.ACTIVITY_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get activity by ID
     * @param activityId the activity ID
     * @return API response with activity details
     */
    public Response getActivityById(String activityId) {
        System.out.println("Fetching activity with ID: " + activityId);

        Response response = getRequestSpec()
                .pathParam("id", activityId)
                .get(EndPoints.ACTIVITY_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get activity by ID (integer)
     * @param activityId the activity ID
     * @return API response with activity details
     */
    public Response getActivityById(int activityId) {
        return getActivityById(String.valueOf(activityId));
    }

    /**
     * Create a new activity
     * @param name activity name
     * @param description activity description
     * @param type activity type
     * @return API response
     */
    public Response createActivity(String name, String description, String type) {
        System.out.println("Creating new activity: " + name);

        Map<String, Object> activityDetails = new HashMap<>();
        activityDetails.put("name", name);
        activityDetails.put("description", description);
        activityDetails.put("type", type);

        Response response = getRequestSpec()
                .body(activityDetails)
                .post(EndPoints.ACTIVITY_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new activity with custom body
     * @param activityBody map containing activity details
     * @return API response
     */
    public Response createActivity(Map<String, Object> activityBody) {
        System.out.println("Creating new activity with custom body");

        Response response = getRequestSpec()
                .body(activityBody)
                .post(EndPoints.ACTIVITY_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Update activity by ID
     * @param activityId the activity ID
     * @param updateData map containing fields to update
     * @return API response
     */
    public Response updateActivity(String activityId, Map<String, Object> updateData) {
        System.out.println("Updating activity with ID: " + activityId);

        Response response = getRequestSpec()
                .pathParam("id", activityId)
                .body(updateData)
                .put(EndPoints.ACTIVITY_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Delete activity by ID
     * @param activityId the activity ID
     * @return API response
     */
    public Response deleteActivity(String activityId) {
        System.out.println("Deleting activity with ID: " + activityId);

        Response response = getRequestSpec()
                .pathParam("id", activityId)
                .delete(EndPoints.ACTIVITY_DELETE);

        logResponse(response);
        return response;
    }

    /**
     * Delete activity by ID (integer)
     * @param activityId the activity ID
     * @return API response
     */
    public Response deleteActivity(int activityId) {
        return deleteActivity(String.valueOf(activityId));
    }

    /**
     * Search activities by name
     * @param searchQuery the search query
     * @return API response with matching activities
     */
    public Response searchActivities(String searchQuery) {
        System.out.println("Searching activities with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Get activities by type
     * @param type the activity type to filter by
     * @return API response with filtered activities
     */
    public Response getActivitiesByType(String type) {
        System.out.println("Fetching activities with type: " + type);

        Response response = getRequestSpec()
                .queryParam("type", type)
                .get(EndPoints.ACTIVITIES);

        logResponse(response);
        return response;
    }

    /**
     * Get activity with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getActivityWithInvalidId() {
        return getActivityById("invalid-id-12345");
    }
}
