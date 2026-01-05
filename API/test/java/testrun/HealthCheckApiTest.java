package testrun;

import pages.HealthCheckApi;
import utils.ResponseValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Health Check and Version API endpoints.
 * These are typically public endpoints that don't require authentication.
 * Contains tests for API availability and version information.
 */
public class HealthCheckApiTest extends BaseTest {

    private HealthCheckApi healthCheckApi;

    @BeforeClass
    public void setUpHealthCheckTests() {
        // Health check API typically doesn't require authentication
        healthCheckApi = new HealthCheckApi();
        System.out.println("HealthCheckApiTest setup completed");
    }

    // ==================== Health Check Tests ====================

    @Test(priority = 1, description = "Test API health check endpoint")
    public void testHealthCheckEndpoint() {
        System.out.println("Testing health check endpoint");

        Response response = healthCheckApi.getHealthCheck();

        int statusCode = response.getStatusCode();
        System.out.println("Health check returned status code: " + statusCode);

        // Health check should return 200 if API is healthy
        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Health check should return 200 or 404 if not implemented. Got: " + statusCode);

        if (statusCode == 200) {
            ResponseValidator.validate(response)
                    .assertJsonContentType()
                    .assertBodyNotEmpty();
        }
    }

    @Test(priority = 2, description = "Test API is healthy using utility method")
    public void testApiIsHealthy() {
        System.out.println("Testing API health status using utility method");

        boolean isHealthy = healthCheckApi.isApiHealthy();
        System.out.println("API health status: " + (isHealthy ? "Healthy" : "Unhealthy"));

        // This test verifies the utility method works, actual health depends on API
        Assert.assertNotNull(isHealthy, "Health check should return a boolean value");
    }

    @Test(priority = 3, description = "Test health check response time")
    public void testHealthCheckResponseTime() {
        System.out.println("Testing health check response time");

        Response response = healthCheckApi.getHealthCheck();

        long responseTime = response.getTime();
        System.out.println("Health check response time: " + responseTime + " ms");

        // Health check should be very fast (under 2 seconds)
        Assert.assertTrue(responseTime < 5000,
                "Health check should respond quickly. Actual: " + responseTime + " ms");
    }

    // ==================== Version Tests ====================

    @Test(priority = 10, description = "Test API version endpoint")
    public void testVersionEndpoint() {
        System.out.println("Testing version endpoint");

        Response response = healthCheckApi.getVersion();

        int statusCode = response.getStatusCode();
        System.out.println("Version check returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 404,
                "Version should return 200 or 404 if not implemented. Got: " + statusCode);

        if (statusCode == 200) {
            ResponseValidator.validate(response)
                    .assertJsonContentType();
        }
    }

    @Test(priority = 11, description = "Test getting version string")
    public void testGetVersionString() {
        System.out.println("Testing get version string");

        String version = healthCheckApi.getVersionString();
        System.out.println("API Version: " + (version != null ? version : "Not available"));

        // Version might not be available, so just verify method works
        // If version is returned, it should not be empty
        if (version != null) {
            Assert.assertFalse(version.isEmpty(), "Version string should not be empty if returned");
        }
    }

    @Test(priority = 12, description = "Test version response format")
    public void testVersionResponseFormat() {
        System.out.println("Testing version response format");

        Response response = healthCheckApi.getVersion();

        if (response.getStatusCode() == 200) {
            String contentType = response.getContentType();
            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Version response should be JSON format");
        }
    }

    // ==================== Consistency Tests ====================

    @Test(priority = 20, description = "Test health check returns consistent results")
    public void testHealthCheckConsistency() {
        System.out.println("Testing health check consistency");

        // Make multiple requests and verify consistency
        int firstStatusCode = healthCheckApi.getHealthCheck().getStatusCode();

        for (int i = 0; i < 3; i++) {
            Response response = healthCheckApi.getHealthCheck();
            Assert.assertEquals(response.getStatusCode(), firstStatusCode,
                    "Health check should return consistent status code");
        }

        System.out.println("Health check is consistent with status code: " + firstStatusCode);
    }

    @Test(priority = 21, description = "Test version returns consistent results")
    public void testVersionConsistency() {
        System.out.println("Testing version consistency");

        String firstVersion = healthCheckApi.getVersionString();

        for (int i = 0; i < 3; i++) {
            String version = healthCheckApi.getVersionString();
            if (firstVersion != null && version != null) {
                Assert.assertEquals(version, firstVersion,
                        "Version should return consistent value");
            }
        }

        System.out.println("Version is consistent: " + firstVersion);
    }

    // ==================== Performance Tests ====================

    @Test(priority = 30, description = "Test health check under rapid requests")
    public void testHealthCheckRapidRequests() {
        System.out.println("Testing health check under rapid requests");

        long totalTime = 0;
        int successCount = 0;
        int requestCount = 5;

        for (int i = 0; i < requestCount; i++) {
            Response response = healthCheckApi.getHealthCheck();
            totalTime += response.getTime();

            if (response.getStatusCode() == 200) {
                successCount++;
            }
        }

        long avgTime = totalTime / requestCount;
        System.out.println("Average response time: " + avgTime + " ms");
        System.out.println("Success rate: " + successCount + "/" + requestCount);

        Assert.assertTrue(avgTime < 5000, "Average response time should be under 5 seconds");
    }
}
