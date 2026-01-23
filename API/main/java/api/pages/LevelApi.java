package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Level Management related API operations.
 * Handles CRUD operations for levels (State, Territory, Cluster, Region).
 */
public class LevelApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public LevelApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public LevelApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all levels
     * @return API response with list of levels
     */
    public Response getRegions() {
        System.out.println("Fetching regions");
        Response response = getRequestSpec()
                .get(EndPoints.LEVELS_REGION);
        logResponse(response);
        return response;
    }
    public Response getState() {
        System.out.println("Fetching state");
        Response response = getRequestSpec()
                .get(EndPoints.LEVELS_STATE);
        logResponse(response);
        return response;
    }
    public Response getTerritory() {
        System.out.println("Fetching territory");
        Response response = getRequestSpec()
                .get(EndPoints.LEVELS_TERRITORY);
        logResponse(response);
        return response;
    }
    public Response getCluster() {
        System.out.println("Fetching cluster");
        Response response = getRequestSpec()
                .get(EndPoints.LEVELS_CLUSTER);
        logResponse(response);
        return response;
    }
    public Response getLocation() {
        System.out.println("Fetching location");
        Response response = getRequestSpec()
                .get(EndPoints.LEVELS_LOCATION);

        logResponse(response);
        return response;
    }
    /**
     * Get levels with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated levels
     */
    public Response getAllLevels(int page, int size) {
        System.out.println("Fetching levels - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.LEVEL_LIST);

        logResponse(response);
        return response;
    }

    public Response getLevelByName(String search) {
        System.out.println("Fetching level with ID: " + search);

        Response response = getRequestSpec()
                .queryParam("search", search)
                .get(EndPoints.LEVEL_BY_NAME);

        logResponse(response);
        return response;
    }
    public Response createNewLevel(int locationId, String name) {
        System.out.println("Creating a new level");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("locationId", locationId );
        requestBody.put("name", name );

        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.LEVEL_CREATE);

        logResponse(response);
        return response;
    }
    public Response editLevelDetails(int parentId, int locationId, String name) {
        System.out.println("Creating a new level");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("parentId",parentId);
        requestBody.put("locationId", locationId );
        requestBody.put("name", name );

        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .patch(EndPoints.LEVEL_UPDATE);

        logResponse(response);
        return response;
    }

}
