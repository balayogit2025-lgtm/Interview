package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.http.ContentType;
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
    public Response getUsersInAPage(int page, int size) {
        System.out.println("Fetching users - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.USER_LIST);

        logResponse(response);
        return response;
    }


    public Response getUserByEmail(String Emailid) {
        System.out.println("Fetching user with EmailID: " + Emailid);

        Response response = getRequestSpec()
                .queryParam("search", Emailid)
                .get(EndPoints.USER_BY_Email);

        logResponse(response);
        return response;
    }


    public Response createUser(String firstname, String lastname, String email,String mobile, int roleid, boolean isWebUser, int locationId, String code, boolean isKeySafeUser, String displayName, String cardNumber, String pin, String password) {
        System.out.println("Creating a new user");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("email", email);
        requestBody.put("mobile", mobile);
        requestBody.put("roleid", roleid);
        requestBody.put("isWebUser", isWebUser);
        requestBody.put("locationId", locationId);
        requestBody.put("code", code);
        requestBody.put("isKeySafeUser", isKeySafeUser);
        requestBody.put("displayName", displayName);
        requestBody.put("cardNumber", cardNumber);
        requestBody.put("pin", pin);
        requestBody.put("password", password);


        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.USER_CREATE);

        logResponse(response);
        return response;
    }
    public Response updateUserInPut(String firstname, String lastname, String email,String mobile, int roleid, boolean isWebUser, int locationId, String code, boolean isKeySafeUser, String displayName, String cardNumber, String pin, String password, int id) {
        System.out.println("Update a user details using PUT");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("email", email);
        requestBody.put("mobile", mobile);
        requestBody.put("roleid", roleid);
        requestBody.put("isWebUser", isWebUser);
        requestBody.put("locationId", locationId);
        requestBody.put("code", code);
        requestBody.put("isKeySafeUser", isKeySafeUser);
        requestBody.put("displayName", displayName);
        requestBody.put("cardNumber", cardNumber);
        requestBody.put("pin", pin);
        requestBody.put("password", password);
        requestBody.put("id", id);

        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .put(EndPoints.USER_UPDATE);

        logResponse(response);
        return response;
    }
    public Response deleteUser(int id) {
        System.out.println("Deleting a user");

        Response response = getRequestSpec()
                .accept(ContentType.JSON)
                .delete(EndPoints.USER_DELETE + "/" + id);

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
}
