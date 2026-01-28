package api.testrun;

import api.pages.KeyPositionTemplateApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for Key Position Template Management API endpoints.
 * Contains tests for key position template CRUD operations.
 */
public class KeyPositionTemplateApiTest extends BaseTest {

    private KeyPositionTemplateApi templateApi;

    @BeforeClass
    public void setUpTemplateTests() {
        ensureLoggedIn();
        templateApi = new KeyPositionTemplateApi();
        System.out.println("KeyPositionTemplateApiTest setup completed");
    }

    @Test(priority = 1, description = "Test fetching all templates")
    public void testGetAllTemplates() {
        System.out.println("Testing get all key position templates API");
        Response response = templateApi.getAllTemplates("0");
        int statusCode = response.getStatusCode();
        System.out.println("Get all templates returned status code: " + statusCode);
        int  actualcount = response.jsonPath().getInt("data.totalCount");
        Assert.assertEquals(actualcount,25,"The count mismatches");

    }

    @Test(priority = 2, description = "Test fetching templates with pagination")
    public void testGetTemplatesWithPagination() {
        System.out.println("Testing get templates with pagination");
        Response response = templateApi.getAllTemplates(1, 10);
        int statusCode = response.getStatusCode();
        System.out.println("Get templates with pagination returned status code: " + statusCode);
        int actualcount = response.jsonPath().getInt("data.to");
        Assert.assertEquals(actualcount,10,"The count mismatches");
    }

    @Test(priority = 3, description = "Test fetching template by valid name")
    public void testGetTemplateBy() {
        System.out.println("Testing get template by name");
        Response response = templateApi.getTemplateByName("Key Position Template 1");
        int statusCode = response.getStatusCode();
        System.out.println("Get template by ID returned status code: " + statusCode);
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode,200,"The status code mismatches");
    }

    @Test(priority = 4, description = "Test fetching template with invalid name")
    public void testGetTemplateWithInvalidName() {
        System.out.println("Testing get template with invalid name");
        Response response = templateApi.getTemplateByName("No Template");
        int statusCode = response.getStatusCode();
        System.out.println("Get template with invalid ID returned status code: " + statusCode);
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode,200,"The status code mismatches");
    }

    @Test(priority = 5, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");
        Response response = templateApi.getAllTemplates("0");
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType.contains("application/json"), "Response should be JSON format");
        }
    }

    @Test(priority = 6, description = "Test template API response time")
    public void testTemplateApiResponseTime() {
        System.out.println("Testing template API response time");
        Response response = templateApi.getAllTemplates("0");
        long responseTime = response.getTime();
        System.out.println("Template API response time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < 10000, "Template API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 7, description = "Test accessing template API without token")
    public void testTemplateApiWithoutToken() {
        System.out.println("Testing template API without authentication token");
        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();
        KeyPositionTemplateApi unauthenticatedApi = new KeyPositionTemplateApi(false);
        Response response = unauthenticatedApi.getAllTemplates("0");
        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);
        Assert.assertEquals(statusCode,401,"The status code mismatches");
        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
    @Test(priority = 8, description = "Creating a new key position template")
    public void CreateKeyPositionTemplate() {
        System.out.println("creating a new key position template");
        List<Map<String, Object>> patterns = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Map<String, Object> pattern = new HashMap<>();
            pattern.put("keyId", i);
            pattern.put("positionId", i);
            pattern.put("isActive", true);
            patterns.add(pattern);
        }
        for (int i = 9; i <= 24; i++) {
            Map<String, Object> pattern = new HashMap<>();
            pattern.put("keyId", i);
            pattern.put("positionId", 0);
            pattern.put("isActive", false);
            patterns.add(pattern);
        }
        Response response = templateApi.createkeypositiontemplate("Key Template 1",patterns);
        int statuscode = response.getStatusCode();
        String actualcreatemessage =  response.jsonPath().getString("message");
        Assert.assertEquals(actualcreatemessage,"Key position created successfully","The response message mismatches");
    }
    @Test(priority = 9, description = "Update the key position template details")
    public void EditKeyPositionTemplate() {
        System.out.println("updating the key position template details");
        List<Map<String, Object>> patterns = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            Map<String, Object> pattern = new HashMap<>();
            pattern.put("keyId", i);
            pattern.put("positionId", i);
            pattern.put("isActive", true);
            patterns.add(pattern);
        }
        Response response = templateApi.updatekeypositiontemplate(30,"Edit Template",patterns);
        int statuscode = response.getStatusCode();
        String actualmessage =  response.jsonPath().getString("message");
        Assert.assertEquals(actualmessage,"Key position updated successfully","The response message mismatches");
    }
    @Test(priority = 10, description = "Creating a duplicate key position template")
    public void CreateDuplicateKeyPositionTemplate() {
        System.out.println("creating a new key position template");
        List<Map<String, Object>> patterns = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Map<String, Object> pattern = new HashMap<>();
            pattern.put("keyId", i);
            pattern.put("positionId", i);
            pattern.put("isActive", true);
            patterns.add(pattern);
        }
        for (int i = 9; i <= 24; i++) {
            Map<String, Object> pattern = new HashMap<>();
            pattern.put("keyId", i);
            pattern.put("positionId", 0);
            pattern.put("isActive", false);
            patterns.add(pattern);
        }
        Response response = templateApi.createkeypositiontemplate("new template",patterns);
        int statuscode = response.getStatusCode();
        String actualcreatemessage =  response.jsonPath().getString("message");
        System.out.println("The status code is: " + statuscode + " and the Response message is" +actualcreatemessage);
        Assert.assertEquals(actualcreatemessage,"key pattern with this name already exists","The response message mismatches");
    }
}
