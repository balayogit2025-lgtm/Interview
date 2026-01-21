package api.testrun;

import api.pages.UserApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for User Management API endpoints.
 * Contains tests for user CRUD operations.
 */
public class UserApiTest extends BaseTest {

    private UserApi userApi;

    @BeforeClass
    public void setUpUserTests() {
        // Ensure user is logged in before running user tests
        ensureLoggedIn();

        // Initialize UserApi with authentication
        userApi = new UserApi();
        System.out.println("UserApiTest setup completed");
    }


    @Test(priority = 1, description = "Test fetching all users")
    public void testGetAllUsers() {
        System.out.println("Testing get all users API");
        Response response = userApi.getAllUsers();
        int statusCode = response.getStatusCode();
        System.out.println("Get all users returned status code: " + statusCode);
        String actualmessage = response.jsonPath().getString("message");
        System.out.println(actualmessage);
        String expectedmessage = "Users fetched successfully";
        Assert.assertEquals(actualmessage, expectedmessage, "message mismatches for getting all users");
    }

    @Test(priority = 2, description = "Test fetching users with pagination")
    public void testGetUsersWithPagination() {
        System.out.println("Testing get users with pagination");
        Response response = userApi.getUsersInAPage(1, 10);
        int statusCode = response.getStatusCode();
        System.out.println("Get users with pagination returned status code: " + statusCode);
        int actualusercountinpageone = response.jsonPath().getList("data.data").size();
        int Expectedusercountinapage = 10;
        Assert.assertEquals(actualusercountinpageone, Expectedusercountinapage, "User count in a page mismatches");
    }

    @Test(priority = 3, description = "Test fetching user by valid email")
    public void testGetUserByEmail() {
        System.out.println("Testing get user by valid email");
        Response response = userApi.getUserByEmail("keypositionadmin@gmail.com");
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        String Firstname = response.jsonPath().getString("data.data[0].firstName");
        System.out.println("Th firstname of the given email is " + Firstname);
        Assert.assertEquals(statusCode, 200, "Status code is mismatched for getting users by valid email");
    }

    @Test(priority = 4, description = "Test fetching user with invalid Email")
    public void testGetUserWithInvalidEmail() {
        System.out.println("Testing get user with invalid Email");
        Response response = userApi.getUserByEmail("nouser@gmail.com");
        int statusCode = response.getStatusCode();
        System.out.println("Get user with invalid Email returned status code: " + statusCode);
        int actualusercountwithgiinvalidemailid = response.jsonPath().getInt("data.totalCount");
        System.out.println(response.jsonPath().getString("data"));
        System.out.println("The no of user with invalid email is " + actualusercountwithgiinvalidemailid);
        Assert.assertEquals(actualusercountwithgiinvalidemailid, 0, "User count is mismatched ");
    }

    @Test(priority = 5, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");
        Response response = userApi.getAllUsers();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            System.out.println(contentType);
            Assert.assertTrue(contentType != null && contentType.contains("application/json"), "Response should be JSON format");
        }
    }

    @Test(priority = 6, description = "Test user API response time")
    public void testUserApiResponseTime() {
        System.out.println("Testing user API response time");
        Response response = userApi.getAllUsers();
        long responseTime = response.getTime();
        System.out.println("User API response time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < 10000, "User API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 7, description = "Test accessing user API without token")
    public void testUserApiWithoutToken() {
        System.out.println("Testing user API without authentication token");
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();
        UserApi unauthenticatedUserApi = new UserApi(false);
        Response response = unauthenticatedUserApi.getAllUsers();
        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);
        Assert.assertEquals(statusCode,401, "Status code mismatches while accessing the api without authentication");
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
    @Test(priority = 8, description = "Create a new user with valid details")
    public void CreateNewUser() {
        System.out.println("creating a new user");
        Response response = userApi.createUser("Automation","User","Automationuser@gmail.com","8541203695",59,true,1,"5810",true,"5810","5810","5810","Sterna@123");
        int statuscode = response.getStatusCode();
        String actualcreatemessage =  response.jsonPath().getString("message");
        Assert.assertEquals(actualcreatemessage,"User created successfully","Success message mismatch for creating a new user");
    }
    @Test(priority = 9, description = "Update the user details")
    public void EditUser() {
        System.out.println("updating the user details");
        Response response = userApi.updateUserInPut("Updated","User","Automationuser@gmail.com","8541203695",59,true,1,"5810",true,"5810","5810","5810","Sterna@123",152);
        int statuscode = response.getStatusCode();
        String actualcreatemessage =  response.jsonPath().getString("message");
        Assert.assertEquals(actualcreatemessage,"User updated successfully","Success message mismatch for updating user details");
    }
    @Test(priority = 10, description = "Delete a user")
    public void DeleteUser() {
        System.out.println("Delete a user");
        Response response = userApi.deleteUser(159);
        int statuscode = response.getStatusCode();
        String actualcreatemessage =  response.jsonPath().getString("message");
        Assert.assertEquals(actualcreatemessage,"User deleted successfully ","Success message mismatch for deleting a user");
    }
}
