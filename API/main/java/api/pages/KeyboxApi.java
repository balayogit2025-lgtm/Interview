package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Keybox Management related API operations.
 * Handles CRUD operations for keyboxes.
 */
public class KeyboxApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public KeyboxApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public KeyboxApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all keyboxes
     * @return API response with list of keyboxes
     */
    public Response getAllKeyboxes() {
        System.out.println("Fetching all keyboxes");

        Response response = getRequestSpec()
                .get(EndPoints.KEYBOXES);

        logResponse(response);
        return response;
    }

    /**
     * Get keyboxes with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated keyboxes
     */
    public Response getAllKeyboxes(int page, int size) {
        System.out.println("Fetching keyboxes - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.KEYBOX_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get keybox by ID
     * @param keyboxId the keybox ID
     * @return API response with keybox details
     */
    public Response getKeyboxById(String keyboxId) {
        System.out.println("Fetching keybox with ID: " + keyboxId);

        Response response = getRequestSpec()
                .pathParam("id", keyboxId)
                .get(EndPoints.KEYBOX_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get keybox by ID (integer)
     * @param keyboxId the keybox ID
     * @return API response with keybox details
     */
    public Response getKeyboxById(int keyboxId) {
        return getKeyboxById(String.valueOf(keyboxId));
    }

    /**
     * Register a new keybox
     * @param serialNumber keybox serial number
     * @param name keybox name
     * @param location keybox location
     * @return API response
     */
    public Response registerKeybox(String serialNumber, String name, String location) {
        System.out.println("Registering new keybox: " + serialNumber);

        Map<String, Object> keyboxDetails = new HashMap<>();
        keyboxDetails.put("serialNumber", serialNumber);
        keyboxDetails.put("name", name);
        keyboxDetails.put("location", location);

        Response response = getRequestSpec()
                .body(keyboxDetails)
                .post(EndPoints.KEYBOX_REGISTER);

        logResponse(response);
        return response;
    }

    /**
     * Create a new keybox
     * @param keyboxBody map containing keybox details
     * @return API response
     */
    public Response createKeybox(Map<String, Object> keyboxBody) {
        System.out.println("Creating new keybox with custom body");

        Response response = getRequestSpec()
                .body(keyboxBody)
                .post(EndPoints.KEYBOX_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Update keybox by ID
     * @param keyboxId the keybox ID
     * @param updateData map containing fields to update
     * @return API response
     */
    public Response updateKeybox(String keyboxId, Map<String, Object> updateData) {
        System.out.println("Updating keybox with ID: " + keyboxId);

        Response response = getRequestSpec()
                .pathParam("id", keyboxId)
                .body(updateData)
                .put(EndPoints.KEYBOX_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Update keybox name
     * @param keyboxId the keybox ID
     * @param newName the new name
     * @return API response
     */
    public Response updateKeyboxName(String keyboxId, String newName) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", newName);
        return updateKeybox(keyboxId, updateData);
    }

    /**
     * Delete keybox by ID
     * @param keyboxId the keybox ID
     * @return API response
     */
    public Response deleteKeybox(String keyboxId) {
        System.out.println("Deleting keybox with ID: " + keyboxId);

        Response response = getRequestSpec()
                .pathParam("id", keyboxId)
                .delete(EndPoints.KEYBOX_DELETE);

        logResponse(response);
        return response;
    }

    /**
     * Delete keybox by ID (integer)
     * @param keyboxId the keybox ID
     * @return API response
     */
    public Response deleteKeybox(int keyboxId) {
        return deleteKeybox(String.valueOf(keyboxId));
    }

    /**
     * Search keyboxes by serial number or name
     * @param searchQuery the search query
     * @return API response with matching keyboxes
     */
    public Response searchKeyboxes(String searchQuery) {
        System.out.println("Searching keyboxes with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.KEYBOXES);

        logResponse(response);
        return response;
    }

    /**
     * Get keyboxes by location
     * @param location the location to filter by
     * @return API response with filtered keyboxes
     */
    public Response getKeyboxesByLocation(String location) {
        System.out.println("Fetching keyboxes at location: " + location);

        Response response = getRequestSpec()
                .queryParam("location", location)
                .get(EndPoints.KEYBOXES);

        logResponse(response);
        return response;
    }

    /**
     * Get keyboxes by status
     * @param status the status to filter by (e.g., "active", "inactive")
     * @return API response with filtered keyboxes
     */
    public Response getKeyboxesByStatus(String status) {
        System.out.println("Fetching keyboxes with status: " + status);

        Response response = getRequestSpec()
                .queryParam("status", status)
                .get(EndPoints.KEYBOXES);

        logResponse(response);
        return response;
    }

    /**
     * Get keybox with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getKeyboxWithInvalidId() {
        return getKeyboxById("invalid-id-12345");
    }
}
