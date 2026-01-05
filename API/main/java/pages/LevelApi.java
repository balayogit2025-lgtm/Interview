package pages;

import base.BaseApi;
import constants.EndPoints;
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
    public Response getAllLevels() {
        System.out.println("Fetching all levels");

        Response response = getRequestSpec()
                .get(EndPoints.LEVELS);

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

    /**
     * Get level by ID
     * @param levelId the level ID
     * @return API response with level details
     */
    public Response getLevelById(String levelId) {
        System.out.println("Fetching level with ID: " + levelId);

        Response response = getRequestSpec()
                .pathParam("id", levelId)
                .get(EndPoints.LEVEL_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get level by ID (integer)
     * @param levelId the level ID
     * @return API response with level details
     */
    public Response getLevelById(int levelId) {
        return getLevelById(String.valueOf(levelId));
    }

    /**
     * Create a new level (State)
     * @param name level name
     * @param type level type (e.g., "state", "territory", "cluster", "region")
     * @return API response
     */
    public Response createLevel(String name, String type) {
        System.out.println("Creating new level: " + name + " of type: " + type);

        Map<String, Object> levelDetails = new HashMap<>();
        levelDetails.put("name", name);
        levelDetails.put("type", type);

        Response response = getRequestSpec()
                .body(levelDetails)
                .post(EndPoints.LEVEL_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new level with parent
     * @param name level name
     * @param type level type
     * @param parentId parent level ID
     * @return API response
     */
    public Response createLevel(String name, String type, String parentId) {
        System.out.println("Creating new level: " + name + " under parent: " + parentId);

        Map<String, Object> levelDetails = new HashMap<>();
        levelDetails.put("name", name);
        levelDetails.put("type", type);
        levelDetails.put("parentId", parentId);

        Response response = getRequestSpec()
                .body(levelDetails)
                .post(EndPoints.LEVEL_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new level with custom body
     * @param levelBody map containing level details
     * @return API response
     */
    public Response createLevel(Map<String, Object> levelBody) {
        System.out.println("Creating new level with custom body");

        Response response = getRequestSpec()
                .body(levelBody)
                .post(EndPoints.LEVEL_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Update level by ID
     * @param levelId the level ID
     * @param updateData map containing fields to update
     * @return API response
     */
    public Response updateLevel(String levelId, Map<String, Object> updateData) {
        System.out.println("Updating level with ID: " + levelId);

        Response response = getRequestSpec()
                .pathParam("id", levelId)
                .body(updateData)
                .put(EndPoints.LEVEL_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Delete level by ID
     * @param levelId the level ID
     * @return API response
     */
    public Response deleteLevel(String levelId) {
        System.out.println("Deleting level with ID: " + levelId);

        Response response = getRequestSpec()
                .pathParam("id", levelId)
                .delete(EndPoints.LEVEL_DELETE);

        logResponse(response);
        return response;
    }

    /**
     * Delete level by ID (integer)
     * @param levelId the level ID
     * @return API response
     */
    public Response deleteLevel(int levelId) {
        return deleteLevel(String.valueOf(levelId));
    }

    /**
     * Search levels by name
     * @param searchQuery the search query
     * @return API response with matching levels
     */
    public Response searchLevels(String searchQuery) {
        System.out.println("Searching levels with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.LEVELS);

        logResponse(response);
        return response;
    }

    /**
     * Get levels by type
     * @param type the level type (state, territory, cluster, region)
     * @return API response with filtered levels
     */
    public Response getLevelsByType(String type) {
        System.out.println("Fetching levels with type: " + type);

        Response response = getRequestSpec()
                .queryParam("type", type)
                .get(EndPoints.LEVELS);

        logResponse(response);
        return response;
    }

    /**
     * Get child levels of a parent
     * @param parentId the parent level ID
     * @return API response with child levels
     */
    public Response getChildLevels(String parentId) {
        System.out.println("Fetching child levels of parent: " + parentId);

        Response response = getRequestSpec()
                .queryParam("parentId", parentId)
                .get(EndPoints.LEVELS);

        logResponse(response);
        return response;
    }

    /**
     * Get level with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getLevelWithInvalidId() {
        return getLevelById("invalid-id-12345");
    }
}
