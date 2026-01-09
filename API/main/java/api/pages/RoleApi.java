package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page class for Role Management related API operations.
 * Handles CRUD operations for roles and permissions.
 */
public class RoleApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public RoleApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public RoleApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all roles
     * @return API response with list of roles
     */
    public Response getAllRoles() {
        System.out.println("Fetching all roles");

        Response response = getRequestSpec()
                .get(EndPoints.ROLES);

        logResponse(response);
        return response;
    }

    /**
     * Get roles with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated roles
     */
    public Response getAllRoles(int page, int size) {
        System.out.println("Fetching roles - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.ROLE_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get role by ID
     * @param roleId the role ID
     * @return API response with role details
     */
    public Response getRoleById(String roleId) {
        System.out.println("Fetching role with ID: " + roleId);

        Response response = getRequestSpec()
                .pathParam("id", roleId)
                .get(EndPoints.ROLE_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get role by ID (integer)
     * @param roleId the role ID
     * @return API response with role details
     */
    public Response getRoleById(int roleId) {
        return getRoleById(String.valueOf(roleId));
    }

    /**
     * Create a new role
     * @param name role name
     * @param description role description
     * @return API response
     */
    public Response createRole(String name, String description) {
        System.out.println("Creating new role: " + name);

        Map<String, Object> roleDetails = new HashMap<>();
        roleDetails.put("name", name);
        roleDetails.put("description", description);

        Response response = getRequestSpec()
                .body(roleDetails)
                .post(EndPoints.ROLE_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new role with permissions
     * @param name role name
     * @param description role description
     * @param permissions list of permission IDs
     * @return API response
     */
    public Response createRole(String name, String description, List<String> permissions) {
        System.out.println("Creating new role: " + name + " with permissions");

        Map<String, Object> roleDetails = new HashMap<>();
        roleDetails.put("name", name);
        roleDetails.put("description", description);
        roleDetails.put("permissions", permissions);

        Response response = getRequestSpec()
                .body(roleDetails)
                .post(EndPoints.ROLE_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new role with custom body
     * @param roleBody map containing role details
     * @return API response
     */
    public Response createRole(Map<String, Object> roleBody) {
        System.out.println("Creating new role with custom body");

        Response response = getRequestSpec()
                .body(roleBody)
                .post(EndPoints.ROLE_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Update role by ID
     * @param roleId the role ID
     * @param updateData map containing fields to update
     * @return API response
     */
    public Response updateRole(String roleId, Map<String, Object> updateData) {
        System.out.println("Updating role with ID: " + roleId);

        Response response = getRequestSpec()
                .pathParam("id", roleId)
                .body(updateData)
                .put(EndPoints.ROLE_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Update role name
     * @param roleId the role ID
     * @param newName the new name
     * @return API response
     */
    public Response updateRoleName(String roleId, String newName) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", newName);
        return updateRole(roleId, updateData);
    }

    /**
     * Delete role by ID
     * @param roleId the role ID
     * @return API response
     */
    public Response deleteRole(String roleId) {
        System.out.println("Deleting role with ID: " + roleId);

        Response response = getRequestSpec()
                .pathParam("id", roleId)
                .delete(EndPoints.ROLE_DELETE);

        logResponse(response);
        return response;
    }

    /**
     * Delete role by ID (integer)
     * @param roleId the role ID
     * @return API response
     */
    public Response deleteRole(int roleId) {
        return deleteRole(String.valueOf(roleId));
    }

    /**
     * Search roles by name
     * @param searchQuery the search query
     * @return API response with matching roles
     */
    public Response searchRoles(String searchQuery) {
        System.out.println("Searching roles with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.ROLES);

        logResponse(response);
        return response;
    }

    /**
     * Get role with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getRoleWithInvalidId() {
        return getRoleById("invalid-id-12345");
    }
}
