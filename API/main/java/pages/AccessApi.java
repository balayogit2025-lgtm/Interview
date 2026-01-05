package pages;

import base.BaseApi;
import constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for Access Management related API operations.
 * Handles CRUD operations for access including create, read, update, and delete.
 */
public class AccessApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public AccessApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public AccessApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    // ==================== GET Operations ====================

    /**
     * Get all access
     * @return API response with list of access
     */
    public Response getAllAccess() {
        System.out.println("Fetching all access");

        Response response = getRequestSpec()
                .get(EndPoints.ACCESS);

        logResponse(response);
        return response;
    }

    /**
     * Get access with pagination
     * @param page page number
     * @param search search query
     * @return API response with paginated access
     */
    public Response getAllAccess(int page, String search) {
        System.out.println("Fetching access - page: " + page + ", search: " + search);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("search", search)
                .get(EndPoints.ACCESS_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get access by ID
     * @param accessId the access ID
     * @return API response with access details
     */
    public Response getAccessById(String accessId) {
        System.out.println("Fetching access with ID: " + accessId);

        Response response = getRequestSpec()
                .pathParam("id", accessId)
                .get(EndPoints.ACCESS_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get access by ID (integer)
     * @param accessId the access ID
     * @return API response with access details
     */
    public Response getAccessById(int accessId) {
        return getAccessById(String.valueOf(accessId));
    }

    /**
     * Search access by query string
     * @param searchQuery the search query
     * @return API response with matching access
     */
    public Response searchAccess(String searchQuery) {
        System.out.println("Searching access with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.ACCESS);

        logResponse(response);
        return response;
    }

    /**
     * Get access filtered by type
     * @param accessType the access type to filter by
     * @return API response with filtered access
     */
    public Response getAccessByType(String accessType) {
        System.out.println("Fetching access of type: " + accessType);

        Response response = getRequestSpec()
                .queryParam("type", accessType)
                .get(EndPoints.ACCESS);

        logResponse(response);
        return response;
    }

    /**
     * Get access filtered by status
     * @param status the status to filter by
     * @return API response with filtered access
     */
    public Response getAccessByStatus(String status) {
        System.out.println("Fetching access with status: " + status);

        Response response = getRequestSpec()
                .queryParam("status", status)
                .get(EndPoints.ACCESS);

        logResponse(response);
        return response;
    }

    /**
     * Get access by location
     * @param location the location to filter by
     * @return API response with filtered access
     */
    public Response getAccessByLocation(String location) {
        System.out.println("Fetching access at location: " + location);

        Response response = getRequestSpec()
                .queryParam("location", location)
                .get(EndPoints.ACCESS);

        logResponse(response);
        return response;
    }

    // ==================== CREATE Operations ====================

    /**
     * Create a new access
     * @param name access name
     * @param serialNumber access serial number
     * @param type access type
     * @param status access status
     * @return API response
     */
    public Response createAccess(String name, String serialNumber, String type, String status) {
        System.out.println("Creating new access: " + name);

        Map<String, Object> accessDetails = new HashMap<>();
        accessDetails.put("name", name);
        accessDetails.put("serialNumber", serialNumber);
        accessDetails.put("type", type);
        accessDetails.put("status", status);

        Response response = getRequestSpec()
                .body(accessDetails)
                .post(EndPoints.ACCESS_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new access with custom body
     * @param accessBody map containing access details
     * @return API response
     */
    public Response createAccess(Map<String, Object> accessBody) {
        System.out.println("Creating new access with custom body");

        Response response = getRequestSpec()
                .body(accessBody)
                .post(EndPoints.ACCESS_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create access using alternative endpoint
     * @param accessBody map containing access details
     * @return API response
     */
    public Response createAccessAlt(Map<String, Object> accessBody) {
        System.out.println("Creating new access (alternative endpoint)");

        Response response = getRequestSpec()
                .body(accessBody)
                .post(EndPoints.ACCESS);

        logResponse(response);
        return response;
    }

    // ==================== UPDATE Operations ====================

    /**
     * Update access by ID with String body
     * @param updateData JSON string containing fields to update
     * @param accessId the access ID
     * @return API response
     */
    public Response updateAccess(String updateData, int accessId) {
        System.out.println("Updating access with ID: " + accessId);

        Response response = getRequestSpec()
                .queryParam("id", accessId)
                .body(updateData)
                .patch(EndPoints.ACCESS_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Update access by ID with Map body (String accessId)
     * @param updateData map containing fields to update
     * @param accessId the access ID as String
     * @return API response
     */
    public Response updateAccess(Map<String, Object> updateData, String accessId) {
        System.out.println("Updating access with ID: " + accessId);

        Response response = getRequestSpec()
                .queryParam("id", accessId)
                .body(updateData)
                .patch(EndPoints.ACCESS_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Update access by ID with Map body (int accessId)
     * @param updateData map containing fields to update
     * @param accessId the access ID as int
     * @return API response
     */
    public Response updateAccess(Map<String, Object> updateData, int accessId) {
        System.out.println("Updating access with ID: " + accessId);

        Response response = getRequestSpec()
                .queryParam("id", accessId)
                .body(updateData)
                .patch(EndPoints.ACCESS_UPDATE);

        logResponse(response);
        return response;
    }



    /**
     * Update access status
     * @param accessId the access ID
     * @param newName the new name
     * @return API response
     */
    public Response updateAccessStatus(String accessId, String newName) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", newName);
        return updateAccess(updateData, accessId);
    }

    /**
     * Update access location
     * @param accessId the access ID
     * @param newLocation the new location
     * @return API response
     */
    public Response updateAccessLocation(String accessId, String newLocation) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("location", newLocation);
        return updateAccess(updateData, accessId);
    }

    /**
     * Update access name
     * @param accessId the access ID
     * @param newName the new name
     * @return API response
     */
    public Response updateAccessName(String accessId, String newName) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", newName);
        return updateAccess(updateData, accessId);
    }

    /**
     * Partial update access using PATCH
     * @param accessId the access ID
     * @param updateData map containing fields to update
     * @return API response
     */
    public Response patchAccess(String accessId, Map<String, Object> updateData) {
        System.out.println("Patching access with ID: " + accessId);

        Response response = getRequestSpec()
                .pathParam("id", accessId)
                .body(updateData)
                .patch(EndPoints.ACCESS_UPDATE);

        logResponse(response);
        return response;
    }

    // ==================== DELETE Operations ====================

    /**
     * Delete access by ID
     * @param accessId the access ID
     * @return API response
     */
    public Response deleteAccess(String accessId) {
        System.out.println("Deleting access with ID: " + accessId);

        Response response = getRequestSpec()
                .pathParam("id", accessId)
                .delete(EndPoints.ACCESS_DELETE);

        logResponse(response);
        return response;
    }

    /**
     * Delete access by ID (integer)
     * @param accessId the access ID
     * @return API response
     */
    public Response deleteAccess(int accessId) {
        return deleteAccess(String.valueOf(accessId));
    }

    // ==================== Negative Test Methods ====================

    /**
     * Get access with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getAccessWithInvalidId() {
        return getAccessById("invalid-access-id-xyz-12345");
    }

    /**
     * Create access with missing required fields for negative testing
     * @return API response
     */
    public Response createAccessWithMissingFields() {
        System.out.println("Creating access with missing fields (negative test)");

        Map<String, Object> incompleteAccess = new HashMap<>();
        // Intentionally missing required fields

        Response response = getRequestSpec()
                .body(incompleteAccess)
                .post(EndPoints.ACCESS_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create access with invalid data for negative testing
     * @return API response
     */
    public Response createAccessWithInvalidData() {
        System.out.println("Creating access with invalid data (negative test)");

        Map<String, Object> invalidAccess = new HashMap<>();
        invalidAccess.put("name", "");
        invalidAccess.put("serialNumber", "");
        invalidAccess.put("type", "invalid_type_xyz");
        invalidAccess.put("status", "invalid_status");

        Response response = getRequestSpec()
                .body(invalidAccess)
                .post(EndPoints.ACCESS_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Update non-existent access for negative testing
     * @return API response
     */
    public Response updateNonExistentAccess() {
        System.out.println("Updating non-existent access (negative test)");

        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", "Updated Name");
        return updateAccess(updateData, "non-existent-id-999999");
    }

    /**
     * Delete non-existent access for negative testing
     * @return API response
     */
    public Response deleteNonExistentAccess() {
        System.out.println("Deleting non-existent access (negative test)");
        return deleteAccess("non-existent-id-999999");
    }

    /**
     * Create access with SQL injection attempt for security testing
     * @return API response
     */
    public Response createAccessWithSqlInjection() {
        System.out.println("Creating access with SQL injection attempt (security test)");

        Map<String, Object> maliciousAccess = new HashMap<>();
        maliciousAccess.put("name", "'; DROP TABLE access; --");
        maliciousAccess.put("serialNumber", "1 OR 1=1");
        maliciousAccess.put("type", "Hardware");
        maliciousAccess.put("status", "Active");

        Response response = getRequestSpec()
                .body(maliciousAccess)
                .post(EndPoints.ACCESS_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create access with XSS attempt for security testing
     * @return API response
     */
    public Response createAccessWithXssAttempt() {
        System.out.println("Creating access with XSS attempt (security test)");

        Map<String, Object> xssAccess = new HashMap<>();
        xssAccess.put("name", "<script>alert('XSS')</script>");
        xssAccess.put("serialNumber", "XSS-TEST-123");
        xssAccess.put("type", "Hardware");
        xssAccess.put("status", "Active");

        Response response = getRequestSpec()
                .body(xssAccess)
                .post(EndPoints.ACCESS_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Get access without authentication for negative testing
     * @return API response
     */
    public Response getAccessWithoutAuth() {
        System.out.println("Fetching access without authentication (negative test)");
        AccessApi unauthApi = new AccessApi(false);
        return unauthApi.getAllAccess();
    }
}
