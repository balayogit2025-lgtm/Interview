package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
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


    public Response getDeviceActivityByserialnumber(String search) {
        System.out.println("Fetching device activity with serial number");

        Response response = getRequestSpec()
                .queryParam("search",search)
                .get(EndPoints.DEVICE_ACTIVITY_BY_SERIALNUMBER);

        logResponse(response);
        return response;
    }


}
