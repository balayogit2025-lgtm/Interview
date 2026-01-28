package api.testrun;

import api.pages.RoleApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Test class for Role Management API endpoints.
 * Contains tests for role CRUD operations.
 */
public class RoleApiTest extends BaseTest {

    private RoleApi roleApi;

    @BeforeClass
    public void setUpRoleTests() {
        ensureLoggedIn();
        roleApi = new RoleApi();
        System.out.println("RoleApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all roles")
    public void testGetAllRoles() {
        System.out.println("Testing get all roles API");
        Response response = roleApi.getAllRoles();
        int roleCount = response.jsonPath().getList("data.data").size();
        int expectedRoleCount = 26;
        Assert.assertEquals(roleCount, expectedRoleCount, "Role count mismatch");
    }

    @Test(priority = 2, description = "Test fetching roles with pagination")
    public void testGetRolesWithPagination() {
        System.out.println("Testing get roles with pagination");
        Response response = roleApi.getAllRoles(1, 10);
        int rolecountinfirstpage = response.jsonPath().getList("data.data").size();
        int expectedRoleCountinfirstpage = 10;
        Assert.assertEquals(rolecountinfirstpage, expectedRoleCountinfirstpage, "Role count mismatch in first page");
    }

    @Test(priority = 3, description = "Test fetching role by valid name")
    public void testGetRoleByName() {
        System.out.println("Testing get role by name");
        Response response = roleApi.getRoleByName("Web user only");
        int statusCode = response.getStatusCode();
        System.out.println("Get role by ID returned status code: " + statusCode);
        int totalCount = response.jsonPath().getInt("data.totalCount");
        Assert.assertEquals(totalCount,1, "Role count mismatch");
    }

    @Test(priority = 4, description = "Test fetching role with invalid name")
    public void testGetRoleWithInvalidName() {
        System.out.println("Testing get role with invalid name");
        Response response = roleApi.getRoleByName("No User");
        int statusCode = response.getStatusCode();
        System.out.println("Get role with invalid ID returned status code: " + statusCode);
        int totalCount = response.jsonPath().getInt("data.totalCount");
        Assert.assertEquals(totalCount,0,"Role count mismatch");
    }

    @Test(priority = 5, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");
        Response response = roleApi.getAllRoles();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response is not in JSON format");
        }
    }

    @Test(priority = 6, description = "Test role API response time")
    public void testRoleApiResponseTime() {
        System.out.println("Testing role API response time");
        Response response = roleApi.getAllRoles();
        long responseTime = response.getTime();
        System.out.println("Role API response time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < 10000,
                "Role API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 7, description = "Test accessing role API without token")
    public void testRoleApiWithoutToken() {
        System.out.println("Testing role API without authentication token");
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();
        RoleApi unauthenticatedApi = new RoleApi(false);
        Response response = unauthenticatedApi.getAllRoles();
        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 401, "status code mismatches for accessing an api without authentication");
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
    @Test(priority = 7, description = "Create a new role with valid details")
    public void CreateNewRoleThroughApi() {
        System.out.println("creating a role through API");
        String name = "Automation User" + System.currentTimeMillis();
        Response response = roleApi.createRole(name,false,false,true, Collections.emptyList());
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 200, "status code mismatches for creating a role");
    }

    @Test(priority = 8, description = "Edit the role details fully")
    public void EdittheRoleDetailsFully() {
        System.out.println("updater the role details");
        String name = "Updated Automation User" + System.currentTimeMillis();
        Response response = roleApi.updateRoleInPut(name,false,false,true, Collections.emptyList(),136);
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 200, "status code mismatches for updating a role");
    }
    @Test(priority = 9, description = "Delete the role")
    public void DeletetheRole() {
        System.out.println("Delete a role");
        Response response = roleApi.deleteRole(139);
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 200, "status code mismatches for creating a role");
    }
    @Test(priority = 10, description = "Delete the role When user is mapped to role")
    public void DeletetheRoleWhenUserIsMapped() {
        System.out.println("Delete a role When an user is mapped");
        Response response = roleApi.deleteRoleMappedWithUser(2);
        int statuscode = response.getStatusCode();
         String actualmessage = response.jsonPath().getString("message");
         System.out.println("The response message is" +actualmessage);
        Assert.assertEquals(actualmessage,"user is mapped, role cannot be deleted","The Response message mismatches");
    }
    @Test(priority = 11, description = "Create a duplicate role")
    public void CreateDuplicateRole() {
        System.out.println("creating a duplicate role");
        Response response = roleApi.createRole("Only Web User",false,false,true, Collections.emptyList());
        int statuscode = response.getStatusCode();
        String actualmessage =  response.jsonPath().getString("message");
        System.out.println("The status code is: " + statuscode + "and the Response message is" +actualmessage);
        Assert.assertEquals(actualmessage,"role name already exists","The Response message mismatches");
    }

}
