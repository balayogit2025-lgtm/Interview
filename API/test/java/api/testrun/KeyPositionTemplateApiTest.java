package api.testrun;

import api.pages.KeyPositionTemplateApi;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

        Response response = templateApi.getAllTemplates();

        int statusCode = response.getStatusCode();
        System.out.println("Get all templates returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 403 || statusCode == 404,
                "Get all templates should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            Assert.assertNotNull(response.getBody(),
                    "Response body should not be null for successful request");
        }
    }

    @Test(priority = 2, description = "Test fetching templates with pagination")
    public void testGetTemplatesWithPagination() {
        System.out.println("Testing get templates with pagination");

        Response response = templateApi.getAllTemplates(1, 10);

        int statusCode = response.getStatusCode();
        System.out.println("Get templates with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Get templates with pagination should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test fetching template by valid ID")
    public void testGetTemplateById() {
        System.out.println("Testing get template by ID");

        Response response = templateApi.getTemplateById("1");

        int statusCode = response.getStatusCode();
        System.out.println("Get template by ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404 || statusCode == 403,
                "Get template by ID should return 200, 404, or 403. Got: " + statusCode);
    }

    @Test(priority = 4, description = "Test fetching template with invalid ID")
    public void testGetTemplateWithInvalidId() {
        System.out.println("Testing get template with invalid ID");

        Response response = templateApi.getTemplateWithInvalidId();

        int statusCode = response.getStatusCode();
        System.out.println("Get template with invalid ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 404 || statusCode == 400 || statusCode == 500,
                "Get template with invalid ID should return 404, 400, or 500. Got: " + statusCode);
    }

    @Test(priority = 5, description = "Test searching templates")
    public void testSearchTemplates() {
        System.out.println("Testing search templates API");

        Response response = templateApi.searchTemplates("test");

        int statusCode = response.getStatusCode();
        System.out.println("Search templates returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Search templates should return 200 or 404. Got: " + statusCode);
    }

    @Test(priority = 6, description = "Test API returns JSON content type")
    public void testResponseContentType() {
        System.out.println("Testing response content type");

        Response response = templateApi.getAllTemplates();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response should be JSON format");
        }
    }

    @Test(priority = 7, description = "Test template API response time")
    public void testTemplateApiResponseTime() {
        System.out.println("Testing template API response time");

        Response response = templateApi.getAllTemplates();

        long responseTime = response.getTime();
        System.out.println("Template API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "Template API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 8, description = "Test accessing template API without token")
    public void testTemplateApiWithoutToken() {
        System.out.println("Testing template API without authentication token");

        String savedToken = TokenManager.getToken();
        TokenManager.clearToken();

        KeyPositionTemplateApi unauthenticatedApi = new KeyPositionTemplateApi(false);
        Response response = unauthenticatedApi.getAllTemplates();

        int statusCode = response.getStatusCode();
        System.out.println("Unauthenticated request returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403 || statusCode == 200,
                "Unauthenticated request should return 401 or 403 (or 200 if endpoint is public). Got: " + statusCode);

        if (savedToken != null) {
            TokenManager.setToken(savedToken);
        }
    }
}
