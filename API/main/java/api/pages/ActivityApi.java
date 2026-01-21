package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
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
    public Response getActivitiesInAPage(int page, int size) {
        System.out.println("Fetching activities - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.ACTIVITY_LIST);

        logResponse(response);
        return response;
    }


    public Response getActivityByName(String name) {
        System.out.println("Fetching activity with ID: " + name);

        Response response = getRequestSpec()
                .queryParam("search", name)
                .get(EndPoints.ACTIVITY_BY_NAME);

        logResponse(response);
        return response;
    }
    public Response createActivity(String name, String code, int locationId, List<Integer> roleid, List<Integer> keyId, int levelId, String fromDate, String toDate, String fromTime, String toTime, String durationLimit, boolean canAccessMonday, boolean canAccessTuesday, boolean canAccessWednesday, boolean canAccessThursday, boolean canAccessFriday, boolean canAccessSaturday, boolean canAccessSunday) {
        System.out.println("Creating a new user");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("code", code);
        requestBody.put("locationId", locationId);
        requestBody.put("roleId", roleid);
        requestBody.put("keyId", keyId);
        requestBody.put("levelId", levelId);
        requestBody.put("fromDate", fromDate);
        requestBody.put("toDate", toDate);
        requestBody.put("fromTime", fromTime);
        requestBody.put("toTime", toTime);
        requestBody.put("durationLimit", durationLimit);
        requestBody.put("canAccessMonday", canAccessMonday);
        requestBody.put("canAccessTuesday", canAccessTuesday);
        requestBody.put("canAccessWednesday", canAccessWednesday);
        requestBody.put("canAccessThursday", canAccessThursday);
        requestBody.put("canAccessFriday", canAccessFriday);
        requestBody.put("canAccessSaturday", canAccessSaturday);
        requestBody.put("canAccessSunday", canAccessSunday);


        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.ACTIVITY_CREATE);

        logResponse(response);
        return response;
    }
    public Response updateActivity(String name, String code, int locationId,List<Integer> roleId,List<Integer>
            keyId, int levelId, String fromDate, String toDate, String fromTime, String toTime, String durationLimit,boolean canAccessMonday, boolean canAccessTuesday, boolean canAccessWednesday, boolean canAccessThursday, boolean canAccessFriday, boolean canAccessSaturday, boolean canAccessSunday, int id ) {
        System.out.println("Update a user details using PUT");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("code", code);
        requestBody.put("locationId", locationId);
        requestBody.put("roleId", roleId);
        requestBody.put("keyId", keyId);
        requestBody.put("levelId", levelId);
        requestBody.put("fromDate", fromDate);
        requestBody.put("toDate", toDate);
        requestBody.put("fromTime", fromTime);
        requestBody.put("toTime", toTime);
        requestBody.put("durationLimit", durationLimit);
        requestBody.put("canAccessMonday", canAccessMonday);
        requestBody.put("canAccessTuesday", canAccessTuesday);
        requestBody.put("canAccessWednesday", canAccessWednesday);
        requestBody.put("canAccessThursday", canAccessThursday);
        requestBody.put("canAccessFriday", canAccessFriday);
        requestBody.put("canAccessSaturday", canAccessSaturday);
        requestBody.put("canAccessSunday", canAccessSunday);
        requestBody.put("id", id);


        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .queryParams("id",107)
                .patch(EndPoints.ACTIVITY_UPDATE);

        logResponse(response);
        return response;
    }
}
