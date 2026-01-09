package api.base;

import api.utils.ConfigReader;
import api.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Base API class that provides common configurations and utilities
 * for all API page classes.
 */
public class BaseApi {

    protected RequestSpecification requestSpec;
    protected static final String BASE_URI = ConfigReader.getProperty("base.url");

    /**
     * Default constructor - initializes request specification without authentication
     */
    public BaseApi() {
        initializeRequestSpec(false);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth if true, adds authorization header with stored token
     */
    public BaseApi(boolean requiresAuth) {
        initializeRequestSpec(requiresAuth);
    }

    /**
     * Initialize the request specification with common configurations
     * @param requiresAuth whether to include authorization header
     */
    private void initializeRequestSpec(boolean requiresAuth) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("User-Agent", "AMS-API-Automation")
                .log(LogDetail.ALL);

        if (requiresAuth) {
            String token = TokenManager.getToken();
            if (token != null && !token.isEmpty()) {
                builder.addHeader("Authorization", "Bearer " + token);
                System.out.println("Authorization header added to request");
            } else {
                System.out.println("WARNING: No token available for authentication");
            }
        }

        requestSpec = builder.build();
    }

    /**
     * Get the base request specification
     * @return RequestSpecification object
     */
    protected RequestSpecification getRequestSpec() {
        return RestAssured.given().spec(requestSpec);
    }

    /**
     * Get request specification with custom headers
     * @param headerName header name
     * @param headerValue header value
     * @return RequestSpecification with additional header
     */
    protected RequestSpecification getRequestSpecWithHeader(String headerName, String headerValue) {
        return getRequestSpec().header(headerName, headerValue);
    }

    /**
     * Rebuild request specification with fresh token
     * Useful when token is updated during test execution
     */
    protected void refreshRequestSpec() {
        initializeRequestSpec(true);
        System.out.println("Request specification refreshed with new token");
    }

    /**
     * Log response details for debugging
     * @param response the API response
     */
    protected void logResponse(Response response) {
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + " ms");
    }

    /**
     * Get the base URI
     * @return base URI string
     */
    public static String getBaseUri() {
        return BASE_URI;
    }
}
