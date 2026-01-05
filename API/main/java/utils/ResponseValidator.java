package utils;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

/**
 * Utility class for validating API responses.
 * Provides reusable methods for common response validations including
 * status codes, headers, body content, and response time checks.
 */
public class ResponseValidator {

    private final Response response;

    /**
     * Constructor to initialize ResponseValidator with a response object
     * @param response the API response to validate
     */
    public ResponseValidator(Response response) {
        this.response = response;
    }

    /**
     * Static factory method to create a ResponseValidator instance
     * @param response the API response
     * @return ResponseValidator instance
     */
    public static ResponseValidator validate(Response response) {
        return new ResponseValidator(response);
    }

    // ==================== Status Code Validations ====================

    /**
     * Validate that the response has the expected status code
     * @param expectedStatusCode the expected HTTP status code
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
        return this;
    }

    /**
     * Validate that the response status code is one of the expected values
     * @param expectedStatusCodes array of acceptable status codes
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertStatusCodeIn(int... expectedStatusCodes) {
        int actualCode = response.getStatusCode();
        boolean found = false;
        StringBuilder expectedStr = new StringBuilder();

        for (int code : expectedStatusCodes) {
            expectedStr.append(code).append(", ");
            if (actualCode == code) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found, "Expected status code to be one of [" +
                expectedStr.substring(0, expectedStr.length() - 2) + "] but got " + actualCode);
        return this;
    }

    /**
     * Validate successful response (2xx status codes)
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertSuccess() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 200 && statusCode < 300,
                "Expected successful response (2xx) but got " + statusCode);
        return this;
    }

    /**
     * Validate client error response (4xx status codes)
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertClientError() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 400 && statusCode < 500,
                "Expected client error response (4xx) but got " + statusCode);
        return this;
    }

    /**
     * Validate server error response (5xx status codes)
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertServerError() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 500 && statusCode < 600,
                "Expected server error response (5xx) but got " + statusCode);
        return this;
    }

    // ==================== Content Type Validations ====================

    /**
     * Validate that the response content type is JSON
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertJsonContentType() {
        String contentType = response.getContentType();
        Assert.assertNotNull(contentType, "Content-Type header should not be null");
        Assert.assertTrue(contentType.toLowerCase().contains("application/json"),
                "Expected JSON content type but got " + contentType);
        return this;
    }

    /**
     * Validate response content type
     * @param expectedContentType the expected content type
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertContentType(String expectedContentType) {
        String contentType = response.getContentType();
        Assert.assertNotNull(contentType, "Content-Type header should not be null");
        Assert.assertTrue(contentType.toLowerCase().contains(expectedContentType.toLowerCase()),
                "Expected content type containing '" + expectedContentType + "' but got " + contentType);
        return this;
    }

    // ==================== Response Time Validations ====================

    /**
     * Validate that response time is within the acceptable limit
     * @param maxTimeInMs maximum acceptable response time in milliseconds
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertResponseTimeBelow(long maxTimeInMs) {
        long actualTime = response.getTime();
        Assert.assertTrue(actualTime < maxTimeInMs,
                "Expected response time < " + maxTimeInMs + "ms but was " + actualTime + "ms");
        return this;
    }

    /**
     * Validate response time is within acceptable SLA (Service Level Agreement)
     * Default SLA is 5 seconds
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertWithinSLA() {
        return assertResponseTimeBelow(5000);
    }

    // ==================== Body Content Validations ====================

    /**
     * Validate that the response body is not empty
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertBodyNotEmpty() {
        String body = response.getBody().asString();
        Assert.assertNotNull(body, "Response body should not be null");
        Assert.assertFalse(body.trim().isEmpty(), "Response body should not be empty");
        return this;
    }

    /**
     * Validate that a specific field exists in the response
     * @param jsonPath the JSON path to the field
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertFieldExists(String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value, "Field '" + jsonPath + "' should exist in response");
        return this;
    }

    /**
     * Validate that a specific field has the expected value
     * @param jsonPath the JSON path to the field
     * @param expectedValue the expected value
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertFieldEquals(String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actualValue, expectedValue,
                "Field '" + jsonPath + "' should equal '" + expectedValue + "' but was '" + actualValue + "'");
        return this;
    }

    /**
     * Validate that a string field contains expected substring
     * @param jsonPath the JSON path to the field
     * @param expectedSubstring the substring to look for
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertFieldContains(String jsonPath, String expectedSubstring) {
        String actualValue = response.jsonPath().getString(jsonPath);
        Assert.assertNotNull(actualValue, "Field '" + jsonPath + "' should not be null");
        Assert.assertTrue(actualValue.contains(expectedSubstring),
                "Field '" + jsonPath + "' should contain '" + expectedSubstring + "' but was '" + actualValue + "'");
        return this;
    }

    /**
     * Validate that a field is not null
     * @param jsonPath the JSON path to the field
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertFieldNotNull(String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value, "Field '" + jsonPath + "' should not be null");
        return this;
    }

    /**
     * Validate that an array field has the expected size
     * @param jsonPath the JSON path to the array field
     * @param expectedSize the expected array size
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertArraySize(String jsonPath, int expectedSize) {
        List<?> array = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull(array, "Array at '" + jsonPath + "' should not be null");
        Assert.assertEquals(array.size(), expectedSize,
                "Array at '" + jsonPath + "' should have size " + expectedSize + " but had " + array.size());
        return this;
    }

    /**
     * Validate that an array field is not empty
     * @param jsonPath the JSON path to the array field
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertArrayNotEmpty(String jsonPath) {
        List<?> array = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull(array, "Array at '" + jsonPath + "' should not be null");
        Assert.assertFalse(array.isEmpty(), "Array at '" + jsonPath + "' should not be empty");
        return this;
    }

    /**
     * Validate that an array field has at least the minimum number of elements
     * @param jsonPath the JSON path to the array field
     * @param minSize the minimum array size
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertArraySizeAtLeast(String jsonPath, int minSize) {
        List<?> array = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull(array, "Array at '" + jsonPath + "' should not be null");
        Assert.assertTrue(array.size() >= minSize,
                "Array at '" + jsonPath + "' should have at least " + minSize + " elements but had " + array.size());
        return this;
    }

    // ==================== Header Validations ====================

    /**
     * Validate that a specific header exists
     * @param headerName the header name
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertHeaderExists(String headerName) {
        String headerValue = response.getHeader(headerName);
        Assert.assertNotNull(headerValue, "Header '" + headerName + "' should exist");
        return this;
    }

    /**
     * Validate that a header has the expected value
     * @param headerName the header name
     * @param expectedValue the expected header value
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertHeaderEquals(String headerName, String expectedValue) {
        String actualValue = response.getHeader(headerName);
        Assert.assertEquals(actualValue, expectedValue,
                "Header '" + headerName + "' should equal '" + expectedValue + "' but was '" + actualValue + "'");
        return this;
    }

    // ==================== Error Response Validations ====================

    /**
     * Validate error response contains error message
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertHasErrorMessage() {
        String body = response.getBody().asString();
        Assert.assertTrue(
                body.contains("error") || body.contains("message") || body.contains("Error") || body.contains("Message"),
                "Error response should contain error or message field");
        return this;
    }

    /**
     * Validate specific error message
     * @param expectedMessage the expected error message (or part of it)
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator assertErrorMessageContains(String expectedMessage) {
        String body = response.getBody().asString();
        Assert.assertTrue(body.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Error response should contain message: " + expectedMessage);
        return this;
    }

    // ==================== Utility Methods ====================

    /**
     * Get the underlying response object
     * @return the Response object
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Get a specific field value from the response
     * @param jsonPath the JSON path to the field
     * @param <T> the expected type
     * @return the field value
     */
    public <T> T getField(String jsonPath) {
        return response.jsonPath().get(jsonPath);
    }

    /**
     * Get the response body as a string
     * @return response body string
     */
    public String getBodyAsString() {
        return response.getBody().asString();
    }

    /**
     * Get the response body as a map
     * @return response body as Map
     */
    public Map<String, Object> getBodyAsMap() {
        return response.jsonPath().getMap("");
    }

    /**
     * Print response details for debugging
     * @return this ResponseValidator for chaining
     */
    public ResponseValidator printDetails() {
        System.out.println("========== Response Details ==========");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Status Line: " + response.getStatusLine());
        System.out.println("Response Time: " + response.getTime() + " ms");
        System.out.println("Content-Type: " + response.getContentType());
        System.out.println("Body: " + response.getBody().asPrettyString());
        System.out.println("=======================================");
        return this;
    }
}
