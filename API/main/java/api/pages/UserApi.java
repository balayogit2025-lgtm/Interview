package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Page class for User Management related API operations.
 * Handles CRUD operations for users.
 */
public class UserApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public UserApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public UserApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all users
     * @return API response with list of users
     */
    public Response getAllUsers() {
        System.out.println("Fetching all users");

        Response response = getRequestSpec()
                .get(EndPoints.USERS);

        logResponse(response);
        return response;
    }

    /**
     * Get users with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated users
     */
    public Response getAllUsers(int page, int size) {
        System.out.println("Fetching users - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.USER_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get user by ID
     * @param userId the user ID
     * @return API response with user details
     */
    public Response getUserById(String userId) {
        System.out.println("Fetching user with ID: " + userId);

        Response response = getRequestSpec()
                .pathParam("id", userId)
                .get(EndPoints.USER_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get user by ID (integer)
     * @param userId the user ID
     * @return API response with user details
     */
    public Response getUserById(int userId) {
        return getUserById(String.valueOf(userId));
    }

    /**
     * Get current user profile
     * @return API response with current user profile
     */
    public Response getUserProfile() {
        System.out.println("Fetching current user profile");

        Response response = getRequestSpec()
                .get(EndPoints.USER_PROFILE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new user
     * @param name user's name
     * @param email user's email
     * @param password user's password
     * @param role user's role
     * @return API response
     */
    public Response createUser(String name, String email, String password, String role) {
        System.out.println("Creating new user: " + email);

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("email", email);
        userDetails.put("password", password);
        userDetails.put("role", role);

        Response response = getRequestSpec()
                .body(userDetails)
                .post(EndPoints.USERS);

        logResponse(response);
        return response;
    }

    /**
     * Create a new user with custom body
     * @param userBody map containing user details
     * @return API response
     */
    public Response createUser(Map<String, Object> userBody) {
        System.out.println("Creating new user with custom body");

        Response response = getRequestSpec()
                .body(userBody)
                .post(EndPoints.USERS);

        logResponse(response);
        return response;
    }

    /**
     * Update user by ID
     * @param userId the user ID
     * @param updateData map containing fields to update
     * @return API response
     */
    public Response updateUser(String userId, Map<String, Object> updateData) {
        System.out.println("Updating user with ID: " + userId);

        Response response = getRequestSpec()
                .pathParam("id", userId)
                .body(updateData)
                .put(EndPoints.USER_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Update user name
     * @param userId the user ID
     * @param newName the new name
     * @return API response
     */
    public Response updateUserName(String userId, String newName) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", newName);
        return updateUser(userId, updateData);
    }

    /**
     * Delete user by ID
     * @param userId the user ID
     * @return API response
     */
    public Response deleteUser(String userId) {
        System.out.println("Deleting user with ID: " + userId);

        Response response = getRequestSpec()
                .pathParam("id", userId)
                .delete(EndPoints.USER_DELETE);

        logResponse(response);
        return response;
    }

    /**
     * Delete user by ID (integer)
     * @param userId the user ID
     * @return API response
     */
    public Response deleteUser(int userId) {
        return deleteUser(String.valueOf(userId));
    }

    /**
     * Search users by name or email
     * @param searchQuery the search query
     * @return API response with matching users
     */
    public Response searchUsers(String searchQuery) {
        System.out.println("Searching users with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.USERS);

        logResponse(response);
        return response;
    }

    /**
     * Get users by role
     * @param role the role to filter by
     * @return API response with filtered users
     */
    public Response getUsersByRole(String role) {
        System.out.println("Fetching users with role: " + role);

        Response response = getRequestSpec()
                .queryParam("role", role)
                .get(EndPoints.USERS);

        logResponse(response);
        return response;
    }

    /**
     * Get user with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getUserWithInvalidId() {
        return getUserById("invalid-id-12345");
    }
}
