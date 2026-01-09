package api.utils;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class ResponseValidator {

    private final Response response;

    public ResponseValidator(Response response) {
        this.response = response;
    }

    public static ResponseValidator validate(Response response) {
        return new ResponseValidator(response);
    }

    public ResponseValidator assertStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
        return this;
    }

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

    public ResponseValidator assertSuccess() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 200 && statusCode < 300,
                "Expected successful response (2xx) but got " + statusCode);
        return this;
    }

    public ResponseValidator assertClientError() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 400 && statusCode < 500,
                "Expected client error response (4xx) but got " + statusCode);
        return this;
    }

    public ResponseValidator assertServerError() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 500 && statusCode < 600,
                "Expected server error response (5xx) but got " + statusCode);
        return this;
    }

    public ResponseValidator assertJsonContentType() {
        String contentType = response.getContentType();
        Assert.assertNotNull(contentType, "Content-Type header should not be null");
        Assert.assertTrue(contentType.toLowerCase().contains("application/json"),
                "Expected JSON content type but got " + contentType);
        return this;
    }

    public ResponseValidator assertContentType(String expectedContentType) {
        String contentType = response.getContentType();
        Assert.assertNotNull(contentType, "Content-Type header should not be null");
        Assert.assertTrue(contentType.toLowerCase().contains(expectedContentType.toLowerCase()),
                "Expected content type containing '" + expectedContentType + "' but got " + contentType);
        return this;
    }

    public ResponseValidator assertResponseTimeBelow(long maxTimeInMs) {
        long actualTime = response.getTime();
        Assert.assertTrue(actualTime < maxTimeInMs,
                "Expected response time < " + maxTimeInMs + "ms but was " + actualTime + "ms");
        return this;
    }

    public ResponseValidator assertWithinSLA() {
        return assertResponseTimeBelow(5000);
    }

    public ResponseValidator assertBodyNotEmpty() {
        String body = response.getBody().asString();
        Assert.assertNotNull(body, "Response body should not be null");
        Assert.assertFalse(body.trim().isEmpty(), "Response body should not be empty");
        return this;
    }

    public ResponseValidator assertFieldExists(String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value, "Field '" + jsonPath + "' should exist in response");
        return this;
    }

    public ResponseValidator assertFieldEquals(String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actualValue, expectedValue,
                "Field '" + jsonPath + "' should equal '" + expectedValue + "' but was '" + actualValue + "'");
        return this;
    }

    public ResponseValidator assertFieldContains(String jsonPath, String expectedSubstring) {
        String actualValue = response.jsonPath().getString(jsonPath);
        Assert.assertNotNull(actualValue, "Field '" + jsonPath + "' should not be null");
        Assert.assertTrue(actualValue.contains(expectedSubstring),
                "Field '" + jsonPath + "' should contain '" + expectedSubstring + "' but was '" + actualValue + "'");
        return this;
    }

    public ResponseValidator assertFieldNotNull(String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value, "Field '" + jsonPath + "' should not be null");
        return this;
    }

    public ResponseValidator assertArraySize(String jsonPath, int expectedSize) {
        List<?> array = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull(array, "Array at '" + jsonPath + "' should not be null");
        Assert.assertEquals(array.size(), expectedSize,
                "Array at '" + jsonPath + "' should have size " + expectedSize + " but had " + array.size());
        return this;
    }

    public ResponseValidator assertArrayNotEmpty(String jsonPath) {
        List<?> array = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull(array, "Array at '" + jsonPath + "' should not be null");
        Assert.assertFalse(array.isEmpty(), "Array at '" + jsonPath + "' should not be empty");
        return this;
    }

    public ResponseValidator assertArraySizeAtLeast(String jsonPath, int minSize) {
        List<?> array = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull(array, "Array at '" + jsonPath + "' should not be null");
        Assert.assertTrue(array.size() >= minSize,
                "Array at '" + jsonPath + "' should have at least " + minSize + " elements but had " + array.size());
        return this;
    }

    public ResponseValidator assertHeaderExists(String headerName) {
        String headerValue = response.getHeader(headerName);
        Assert.assertNotNull(headerValue, "Header '" + headerName + "' should exist");
        return this;
    }

    public ResponseValidator assertHeaderEquals(String headerName, String expectedValue) {
        String actualValue = response.getHeader(headerName);
        Assert.assertEquals(actualValue, expectedValue,
                "Header '" + headerName + "' should equal '" + expectedValue + "' but was '" + actualValue + "'");
        return this;
    }

    public ResponseValidator assertHasErrorMessage() {
        String body = response.getBody().asString();
        Assert.assertTrue(
                body.contains("error") || body.contains("message") || body.contains("Error") || body.contains("Message"),
                "Error response should contain error or message field");
        return this;
    }

    public ResponseValidator assertErrorMessageContains(String expectedMessage) {
        String body = response.getBody().asString();
        Assert.assertTrue(body.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Error response should contain message: " + expectedMessage);
        return this;
    }

    public Response getResponse() {
        return response;
    }

    public <T> T getField(String jsonPath) {
        return response.jsonPath().get(jsonPath);
    }

    public String getBodyAsString() {
        return response.getBody().asString();
    }

    public Map<String, Object> getBodyAsMap() {
        return response.jsonPath().getMap("");
    }

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
