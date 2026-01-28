package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.http.ContentType;
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
        Response response = getRequestSpec().get(EndPoints.ROLES);
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
     * Get role by name
     * @param rolename the role name
     * @return API response with role details
     */
    public Response getRoleByName(String rolename) {
        Response response = getRequestSpec()
                .queryParam("search", rolename)
                .get(EndPoints.ROLE_BY_NAME);
        logResponse(response);
        return response;
    }


    public Response createRole(String roleName, boolean hasWebAccess, boolean hasMobileAccess, boolean hasKeySafeAccess, List<Integer> pageIds) {
        System.out.println("Creating a new role");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", roleName);
        requestBody.put("hasWebAccess", hasWebAccess);
        requestBody.put("hasMobileAccess", hasMobileAccess);
        requestBody.put("hasKeySafeAccess", hasKeySafeAccess);
        requestBody.put("pageIds", pageIds);

        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.ROLE_CREATE);

        logResponse(response);
        return response;
    }

    public Response createDuplicateRole(String roleName, boolean hasWebAccess, boolean hasMobileAccess, boolean hasKeySafeAccess, List<Integer> pageIds) {
        System.out.println("Creating a duplicate role");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", roleName);
        requestBody.put("hasWebAccess", hasWebAccess);
        requestBody.put("hasMobileAccess", hasMobileAccess);
        requestBody.put("hasKeySafeAccess", hasKeySafeAccess);
        requestBody.put("pageIds", pageIds);

        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.ROLE_CREATE);

        logResponse(response);
        return response;
    }

    public Response updateRoleInPut(String roleName, boolean hasWebAccess, boolean hasMobileAccess, boolean hasKeySafeAccess, List<Integer> pageIds, int id) {
        System.out.println("Updating role using PUT");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", roleName);
        requestBody.put("hasWebAccess", hasWebAccess);
        requestBody.put("hasMobileAccess", hasMobileAccess);
        requestBody.put("hasKeySafeAccess", hasKeySafeAccess);
        requestBody.put("pageIds", pageIds);
        requestBody.put("id", id);

        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .put(EndPoints.ROLE_UPDATE);

        logResponse(response);
        return response;
    }

    public Response updateRoleInPatch(String roleName) {
        System.out.println("Updating role using PATCH");
        Map<String, Object> requestBody = new HashMap<>();
        if (roleName != null) requestBody.put("name", roleName);
        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .patch(EndPoints.ROLE_UPDATE); // e.g. /roles/{id}

        logResponse(response);
        return response;
    }

    public Response deleteRole(int id) {
        System.out.println("Deleting a role");

        Response response = getRequestSpec()
                .accept(ContentType.JSON)
                .delete(EndPoints.ROLE_DELETE + "/" + id);

        logResponse(response);
        return response;
    }
    public Response deleteRoleMappedWithUser(int id) {
        System.out.println("Deleting a role which is mappped with user");

        Response response = getRequestSpec()
                .accept(ContentType.JSON)
                .delete(EndPoints.ROLE_DELETE + "/" + id);

        logResponse(response);
        return response;
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
        return getRoleByName("invalid-id-12345");
    }
}
