package api.testrun;

import api.pages.LevelApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LevelsApiTest extends BaseTest {

    private LevelApi levelApi;

    @BeforeClass
    public void setUpLevelTests() {
        ensureLoggedIn();
        levelApi = new LevelApi();
        System.out.println("LevelApiTest setup completed");
    }


    @Test(priority = 1, description = "get all regions")
    public void getRegions() {
        System.out.println("get all region ");
        Response response = levelApi.getRegions();
        int statusCode = response.getStatusCode();
        System.out.println("Get all region returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code mismatches");
    }
    @Test(priority = 2, description = "get all states")
    public void getStates() {
        System.out.println("get all states");
        Response response = levelApi.getState();
        int statusCode = response.getStatusCode();
        System.out.println("Get all state returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code mismatches");
    }
    @Test(priority = 3, description = "get all territory")
    public void getTerritory() {
        System.out.println("get all territory");
        Response response = levelApi.getTerritory();
        int statusCode = response.getStatusCode();
        System.out.println("Get all territory returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code mismatches");
    }
    @Test(priority = 4, description = "get all cluster")
    public void getCluster() {
        System.out.println("get all cluster");
        Response response = levelApi.getCluster();
        int statusCode = response.getStatusCode();
        System.out.println("Get all cluster returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code mismatches");
    }
    @Test(priority = 5 , description = "get all location")
    public void getLocation() {
        System.out.println("get all location");
        Response response = levelApi.getLocation();
        int statusCode = response.getStatusCode();
        System.out.println("Get all location returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code mismatches");
    }
    @Test(priority = 6, description = "Test fetching regions with pagination")
    public void testGetRegionsWithPagination() {
        System.out.println("Testing get Regions with pagination");
        Response response = levelApi.getAllLevels(1, 10);
        int statusCode = response.getStatusCode();
        System.out.println("Get Regions with pagination returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code mismatches");
    }

    @Test(priority = 7 , description = "Test fetching level by valid region name")
    public void testGetLevelByName() {
        System.out.println("Testing get level by region name");
        Response response = levelApi.getLevelByName("QA Region");
        int totalcount = response.jsonPath().getInt("data.totalCount");
        System.out.println("The toital count is " +totalcount);
        Assert.assertEquals(totalcount, 1, "Total count mismatches");
    }

    @Test(priority = 8 , description = "Test fetching level with invalid region name")
    public void testGetLevelWithInvalidName() {
        System.out.println("Testing get level with invalid region name");
        Response response = levelApi.getLevelByName("None Region");
        int totalcount = response.jsonPath().getInt("data.totalCount");
        System.out.println("The toital count is " +totalcount);
        Assert.assertEquals(totalcount, 0,"Total count mismatches");
    }

    @Test(priority = 9, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");
        Response response = levelApi.getRegions();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType.contains("application/json"), "Response is not in JSON format");
        }
    }

    @Test(priority = 10 , description = "Test level API response time")
    public void testLevelApiResponseTime() {
        System.out.println("Testing level API response time");
        Response response = levelApi.getRegions();
        long responseTime = response.getTime();
        System.out.println("Level API response time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < 10000, "Level API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 11, description = "Test accessing level API without token")
    public void testLevelApiWithoutToken() {
        System.out.println("Testing level API without authentication token");
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();
        LevelApi unauthenticatedApi = new LevelApi(false);
        Response response = unauthenticatedApi.getRegions();
        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);
        Assert.assertEquals(statusCode, 401, "Status code mismatches");

        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
    @Test(priority = 12 , description = "Create a new region")
    public void createNewRegion() {
        System.out.println("Create a new region");
        Response response = levelApi.createNewLevel(1,"South India");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level created successfully","Message mismatches");
    }
    @Test(priority = 13 , description = "Create a new State")
    public void createNewState() {
        System.out.println("Create a new State");
        Response response = levelApi.createNewLevel(39,"Karnataka");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level created successfully","Message mismatches");
    }
    @Test(priority = 14 , description = "Create a new Territory")
    public void createNewTerritory() {
        System.out.println("Create a new Territory");
        Response response = levelApi.createNewLevel(40,"Bangalore");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level created successfully","Message mismatches");
    }
    @Test(priority = 15 , description = "Create a new Cluster")
    public void createNewCluster() {
        System.out.println("Create a new Cluster");
        Response response = levelApi.createNewLevel(41,"Electronic city");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level created successfully","Message mismatches");
    }
    @Test(priority = 16 , description = "Create a new Location")
    public void createNewLocation() {
        System.out.println("Create a new Location");
        Response response = levelApi.createNewLevel(42,"hebbal");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level created successfully","Message mismatches");
    }

    @Test(priority = 17 , description = "Edit region details")
    public void editRegion() {
        System.out.println("Edit region details");
        Response response = levelApi.editLevelDetails(1,156 ,"Edit Region");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level updated successfully","Message mismatches");
    }
    @Test(priority = 18 , description = "Edit State details")
    public void editState() {
        System.out.println("Edit State details");
        Response response = levelApi.editLevelDetails(39, 160,"Edited state");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level updated successfully","Message mismatches");
    }
    @Test(priority = 19 , description = "Edit Territory details")
    public void editTerritory() {
        System.out.println("Edit Territory details");
        Response response = levelApi.editLevelDetails(40,161,"Edited Territory");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level updated successfully","Message mismatches");
    }
    @Test(priority = 20 , description = "Edit Cluster details")
    public void editCluster() {
        System.out.println("Edit Cluster details");
        Response response = levelApi.editLevelDetails(41,162,"Edited Cluster");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level updated successfully","Message mismatches");
    }
    @Test(priority = 21 , description = "Edit Location details")
    public void editLocation() {
        System.out.println("Edit Location details");
        Response response = levelApi.editLevelDetails(42,163,"Edited Location");
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Level updated successfully","Message mismatches");
    }
    @Test(priority = 22 , description = "Create a duplicate region")
    public void createDuplicateRegion() {
        System.out.println("Create a duplicate region");
        Response response = levelApi.createDuplicateLevel(1,"South Indian Region");
        String actualmessage = response.jsonPath().getString("message");
         int statuscode = response.getStatusCode();
        System.out.println("The status code is: " + statuscode + " and the Response message is" +actualmessage);
        Assert.assertEquals(actualmessage,"location level name already exist","Response Message mismatches");
    }
    @Test(priority = 23 , description = "Create a duplicate State")
    public void createDuplicateState() {
        System.out.println("Create a duplicate State");
        Response response = levelApi.createDuplicateLevel(39,"kerala");
        String actualmessage = response.jsonPath().getString("message");
        int statuscode = response.getStatusCode();
        System.out.println("The status code is: " + statuscode + " and the Response message is" +actualmessage);
        Assert.assertEquals(actualmessage,"location level name already exist","Response Message mismatches");
    }
    @Test(priority = 24 , description = "Create a duplicate Territory")
    public void createDuplicateTerritory() {
        System.out.println("Create a duplicate Territory");
        Response response = levelApi.createDuplicateLevel(40,"Madurai");
        String actualmessage = response.jsonPath().getString("message");
        int statuscode = response.getStatusCode();
        System.out.println("The status code is: " + statuscode + " and the Response message is" +actualmessage);
        Assert.assertEquals(actualmessage,"location level name already exist","Response Message mismatches");
    }
    @Test(priority = 25 , description = "Create a duplicate Cluster")
    public void createDuplicateCluster() {
        System.out.println("Create a duplicate Cluster");
        Response response = levelApi.createDuplicateLevel(41,"RS puram");
        String actualmessage = response.jsonPath().getString("message");
        int statuscode = response.getStatusCode();
        System.out.println("The status code is: " + statuscode + " and the Response message is" +actualmessage);
        Assert.assertEquals(actualmessage,"location level name already exist","Response Message mismatches");
    }
    @Test(priority = 26 , description = "Create a duplicate Location")
    public void createDuplicateLocation() {
        System.out.println("Create a duplicate Location");
        Response response = levelApi.createDuplicateLevel(42,"Coastal side Ro");
        String actualmessage = response.jsonPath().getString("message");
        int statuscode = response.getStatusCode();
        System.out.println("The status code is: " + statuscode + " and the Response message is" +actualmessage);
        Assert.assertEquals(actualmessage,"location level name already exist","Response Message mismatches");
    }





}
