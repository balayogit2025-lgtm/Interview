package api.testrun;

import api.pages.ActivityApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;


/**
 * Test class for Activity Management API endpoints.
 * Contains tests for activity CRUD operations.
 */
public class ActivityApiTest extends BaseTest{
    private ActivityApi activityApi;

    @BeforeClass
    public void setUpActivityTests() {
        ensureLoggedIn();
        activityApi = new ActivityApi();
        System.out.println("ActivityApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all activities")
    public void testGetAllActivities() {
        System.out.println("Testing get all activities API");
        Response response = activityApi.getAllActivities();
        int statusCode = response.getStatusCode();
        System.out.println("Get all activities returned status code: " + statusCode);
        String  totalcount = response.jsonPath().getString("data.totalCount");
        Assert.assertEquals(totalcount,"21", "The response message mismatches");

    }

    @Test(priority = 2, description = "Test fetching activities with pagination")
    public void testGetActivitiesWithPagination() {
        System.out.println("Testing get activities with pagination");
        Response response = activityApi.getActivitiesInAPage(1, 10);
        int statusCode = response.getStatusCode();
        System.out.println("Get activities with pagination returned status code: " + statusCode);
        String actualcount = response.jsonPath().getString("data.totalCount");
        Assert.assertEquals(actualcount,"10","The count mismatches");
    }

    @Test(priority = 3, description = "Test fetching activity by valid name")
    public void testGetActivityByValidname() {
        System.out.println("Testing get activity by name");
        Response response = activityApi.getActivityByName("Sampling");
        int statusCode = response.getStatusCode();
        System.out.println("Get activity by ID returned status code: " + statusCode);
        String totalcount = response.jsonPath().getString("data.totalCount");
        Assert.assertEquals(totalcount,"1","The count mismatches");
    }

    @Test(priority = 4, description = "Test fetching activity with invalid name")
    public void testGetActivityWithInvalidName() {
        System.out.println("Testing get activity with invalid name");
        Response response = activityApi.getActivityByName("No Activity");
        int statusCode = response.getStatusCode();
        System.out.println("Get activity with invalid ID returned status code: " + statusCode);
        String totalcount = response.jsonPath().getString("data.totalCount");
        Assert.assertEquals(totalcount,"0","The count mismatches");
    }

    @Test(priority = 5, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");
        Response response = activityApi.getAllActivities();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            System.out.println("The content type of the request is " + contentType);
            Assert.assertTrue(contentType != null && contentType.contains("application/json"), "Response format mismatches");
        }
    }

    @Test(priority = 6, description = "Test activity API response time")
    public void testActivityApiResponseTime() {
        System.out.println("Testing activity API response time");
        Response response = activityApi.getAllActivities();
        long responseTime = response.getTime();
        System.out.println("Activity API response time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < 10000, "Activity API response time is less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 7, description = "Test accessing activity API without token")
    public void testActivityApiWithoutToken() {
        System.out.println("Testing activity API without authentication token");
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();
        ActivityApi unauthenticatedApi = new ActivityApi(false);
        Response response = unauthenticatedApi.getAllActivities();
        int statusCode = response.getStatusCode();
        System.out.println("The status codeof a request without Authorization is: " + statusCode);
        Assert.assertEquals(statusCode,401,"The status code is mismatched");
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
    @Test(priority = 8, description = "Creating a new activity")
    public void CreateNewActivity() {
        System.out.println("creating a new activity");
        Response response = activityApi.createActivity("New Activity","518",1, List.of(59),List.of(1),1,"2026-01-01","2026-01-31","09:00","18:00","300",true,true,true,true,true,true,true);
        int statuscode = response.getStatusCode();
        String actualcreatemessage =  response.jsonPath().getString("message");
        Assert.assertEquals(actualcreatemessage,"Access created successfully","The response message mismatches");
    }
    @Test(priority = 9, description = "Update the activity details")
    public void EditActivity() {
        System.out.println("updating the activity details");
        Response response = activityApi.updateActivity("Edited Activity","519",1,List.of(59),List.of(1),1,"2026-01-17","2026-01-31","09:00","18:00","300",true,true,true,true,true,true, true,112);
        int statuscode = response.getStatusCode();
        String actualmessage =  response.jsonPath().getString("message");
        Assert.assertEquals(actualmessage,"Access updated successfully","The response message mismatches");
    }



}
