package testrun;

import pages.AccessApi;
import utils.ResponseValidator;
import utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Test class for Access Management API endpoints.
 * Contains comprehensive tests for access CRUD operations.
 * Covers positive, negative, edge case, and security scenarios.
 */
public class AccessApiTest extends BaseTest {

    private AccessApi accessApi;
    private String createdAccessName; // Store created access ID for update/delete tests

    @BeforeClass
    public void setUpAccessTests() {
        // Ensure user is logged in before running access tests
        ensureLoggedIn();

        // Initialize AccessApi with authentication
        accessApi = new AccessApi();
        System.out.println("AccessApiTest setup completed");
    }

    // ==================== Positive Test Cases - GET Operations ====================

    @Test(priority = 1, description = "Test fetching all access")
    public void testGetAllAccess() {
        System.out.println("Testing get all access API");

        Response response = accessApi.getAllAccess();

        int statusCode = response.getStatusCode();
        System.out.println("Get all access returned status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Get all access returned body: " + responseBody);

        Assert.assertTrue(statusCode == 200,
                "Get all access should return valid status code. Got: " + statusCode);

        if (statusCode == 200) {
            ResponseValidator.validate(response)
                    .assertJsonContentType()
                    .assertBodyNotEmpty();
        }
    }

    @Test(priority = 2, description = "Test fetching access with pagination")
    public void testGetAccessWithPagination() {
        System.out.println("Testing get access with pagination");

        Response response = accessApi.getAllAccess(1, "");

        int statusCode = response.getStatusCode();
        System.out.println("Get access with pagination returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200,
                "Get access with pagination should return 200. Got: " + statusCode);
    }

    @Test(priority = 3, description = "Test searching access")
    public void testSearchAccess() {
        System.out.println("Testing search access API");

        Response response = accessApi.searchAccess("test");

        int statusCode = response.getStatusCode();
        System.out.println("Search access returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 ,
                "Search access should return 200 or 404. Got: " + statusCode);
    }


    // ==================== Positive Test Cases - CREATE Operations ====================

    @Test(priority = 4, description = "Test creating a new access with valid data")
    public void testCreateAccessWithValidData() {
        System.out.println("Testing create access with valid data");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 ,
                "Create access should return 200 or 201. Got: " + statusCode);

        // Store created access name for later tests
        if (statusCode == 200 || statusCode == 201) {
            // Use the name from the request body since we generated it
            createdAccessName = (String) accessBody.get("name");
            System.out.println("Created access name: " + createdAccessName);
        }
    }

    // ==================== Additional Positive Test Cases - CREATE Operations ====================

    @Test(priority = 5, description = "Test creating access with minimum required fields")
    public void testCreateAccessWithMinimumRequiredFields() {
        System.out.println("Testing create access with minimum required fields");

        Map<String, Object> accessBody = new HashMap<>();
        accessBody.put("name", "MinFieldsTest" + TestDataGenerator.generateRandomNumber(1, 999));
        accessBody.put("code", String.valueOf(TestDataGenerator.generateRandomNumber(100, 999)));
        accessBody.put("locationId", 6);
        accessBody.put("roleId", Arrays.asList(59));
        accessBody.put("keyId", Arrays.asList(2));
        accessBody.put("fromDate", TestDataGenerator.getTodayDate());
        accessBody.put("toDate", TestDataGenerator.getDateFromNow(7));
        accessBody.put("fromTime", "09:00");
        accessBody.put("toTime", "18:00");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with minimum fields returned status code: " + statusCode);

        Assert.assertTrue( statusCode == 400,
                "Create access with minimum fields should return 200, 201, or 400 if fields missing. Got: " + statusCode);
    }

    @Test(priority = 13, description = "Test creating access with multiple role IDs")
    public void testCreateAccessWithMultipleRoleIds() {
        System.out.println("Testing create access with multiple role IDs");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("roleId", Arrays.asList(59, 57, 56));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with multiple roles returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with multiple roles should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 14, description = "Test creating access with multiple key IDs")
    public void testCreateAccessWithMultipleKeyIds() {
        System.out.println("Testing create access with multiple key IDs");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("keyId", Arrays.asList(2, 3, 4));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with multiple keys returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with multiple keys should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 15, description = "Test creating access with weekday only access")
    public void testCreateAccessWithWeekdayOnlyAccess() {
        System.out.println("Testing create access with weekday only access");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("canAccessMonday", true);
        accessBody.put("canAccessTuesday", true);
        accessBody.put("canAccessWednesday", true);
        accessBody.put("canAccessThursday", true);
        accessBody.put("canAccessFriday", true);
        accessBody.put("canAccessSaturday", false);
        accessBody.put("canAccessSunday", false);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with weekday only returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with weekday only should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 16, description = "Test creating access with weekend only access")
    public void testCreateAccessWithWeekendOnlyAccess() {
        System.out.println("Testing create access with weekend only access");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("canAccessMonday", false);
        accessBody.put("canAccessTuesday", false);
        accessBody.put("canAccessWednesday", false);
        accessBody.put("canAccessThursday", false);
        accessBody.put("canAccessFriday", false);
        accessBody.put("canAccessSaturday", true);
        accessBody.put("canAccessSunday", true);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with weekend only returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with weekend only should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 17, description = "Test creating access with extended date range")
    public void testCreateAccessWithExtendedDateRange() {
        System.out.println("Testing create access with extended date range (30 days)");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromDate", TestDataGenerator.getTodayDate());
        accessBody.put("toDate", TestDataGenerator.getDateFromNow(30));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with extended date range returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with extended date range should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 18, description = "Test creating access with same from and to date")
    public void testCreateAccessWithSameDayAccess() {
        System.out.println("Testing create access with same from and to date");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        String today = TestDataGenerator.getTodayDate();
        accessBody.put("fromDate", today);
        accessBody.put("toDate", today);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with same day returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with same day should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 19, description = "Test creating access with different duration limits")
    public void testCreateAccessWithDifferentDurationLimits() {
        System.out.println("Testing create access with different duration limits");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("durationLimit", "60");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with 60 min duration returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with 60 min duration should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 20, description = "Test creating access with custom access events")
    public void testCreateAccessWithCustomAccessEvents() {
        System.out.println("Testing create access with custom access events");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();

        Map<String, Object> accessEvent = new HashMap<>();
        accessEvent.put("frequency", 2);
        accessEvent.put("event", 2);
        accessEvent.put("per", "week");
        accessBody.put("accessEvents", Arrays.asList(accessEvent));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with custom events returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with custom events should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 21, description = "Test verifying response body after creating access")
    public void testCreateAccessVerifyResponseBody() {
        System.out.println("Testing create access and verifying response body");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        String expectedName = (String) accessBody.get("name");
System.out.println("Expected Name :"+expectedName);
        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access returned status code: " + statusCode);

        if (statusCode == 200 || statusCode == 201) {
            ResponseValidator.validate(response)
                    .assertJsonContentType()
                    .assertBodyNotEmpty();

            String responseBody = response.getBody().asString();
            System.out.println("Response body: " + responseBody);

            // Verify response is not null
            Assert.assertNotNull(responseBody, "Response body should not be null");
        }

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access should return 200 or 201. Got: " + statusCode);
    }

    // ==================== Negative Test Cases - CREATE Operations ====================

    @Test(priority = 22, description = "Test creating access with empty name")
    public void testCreateAccessWithEmptyName() {
        System.out.println("Testing create access with empty name");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with empty name returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with empty name should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 23, description = "Test creating access with null name")
    public void testCreateAccessWithNullName() {
        System.out.println("Testing create access with null name");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", null);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with null name returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with null name should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 24, description = "Test creating access with empty code")
    public void testCreateAccessWithEmptyCode() {
        System.out.println("Testing create access with empty code");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("code", "");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with empty code returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with empty code should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 25, description = "Test creating access with invalid location ID")
    public void testCreateAccessWithInvalidLocationId() {
        System.out.println("Testing create access with invalid location ID");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("locationId", -1);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with invalid location ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 404,
                "Create access with invalid location ID should return 400, 422 or 404. Got: " + statusCode);
    }

    @Test(priority = 26, description = "Test creating access with non-existent location ID")
    public void testCreateAccessWithNonExistentLocationId() {
        System.out.println("Testing create access with non-existent location ID");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("locationId", 999999);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with non-existent location ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 404,
                "Create access with non-existent location ID should return 400, 422 or 404. Got: " + statusCode);
    }

    @Test(priority = 27, description = "Test creating access with invalid role ID")
    public void testCreateAccessWithInvalidRoleId() {
        System.out.println("Testing create access with invalid role ID");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("roleId", Arrays.asList(-1));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with invalid role ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 404,
                "Create access with invalid role ID should return 400, 422 or 404. Got: " + statusCode);
    }

    @Test(priority = 28, description = "Test creating access with non-existent role ID")
    public void testCreateAccessWithNonExistentRoleId() {
        System.out.println("Testing create access with non-existent role ID");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("roleId", Arrays.asList(999999));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with non-existent role ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 404,
                "Create access with non-existent role ID should return 400, 422 or 404. Got: " + statusCode);
    }

    @Test(priority = 29, description = "Test creating access with invalid key ID")
    public void testCreateAccessWithInvalidKeyId() {
        System.out.println("Testing create access with invalid key ID");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("keyId", Arrays.asList(-1));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with invalid key ID returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 404,
                "Create access with invalid key ID should return 400, 422 or 404. Got: " + statusCode);
    }

    @Test(priority = 30, description = "Test creating access with invalid date format")
    public void testCreateAccessWithInvalidDateFormat() {
        System.out.println("Testing create access with invalid date format");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromDate", "01-01-2025"); // Invalid format, should be yyyy-MM-dd
        accessBody.put("toDate", "31-12-2025");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with invalid date format returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with invalid date format should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 31, description = "Test creating access with toDate before fromDate")
    public void testCreateAccessWithToDateBeforeFromDate() {
        System.out.println("Testing create access with toDate before fromDate");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromDate", TestDataGenerator.getDateFromNow(10));
        accessBody.put("toDate", TestDataGenerator.getTodayDate());

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with toDate before fromDate returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with toDate before fromDate should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 32, description = "Test creating access with invalid time format")
    public void testCreateAccessWithInvalidTimeFormat() {
        System.out.println("Testing create access with invalid time format");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromTime", "9:00 AM"); // Invalid format, should be HH:mm
        accessBody.put("toTime", "6:00 PM");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with invalid time format returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 200 || statusCode == 201,
                "Create access with invalid time format should be handled. Got: " + statusCode);
    }

    @Test(priority = 33, description = "Test creating access with toTime before fromTime")
    public void testCreateAccessWithToTimeBeforeFromTime() {
        System.out.println("Testing create access with toTime before fromTime");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromTime", "18:00");
        accessBody.put("toTime", "09:00");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with toTime before fromTime returned status code: " + statusCode);

        // Some APIs allow overnight time ranges, so we accept success or validation error
        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 422,
                "Create access with toTime before fromTime should be handled. Got: " + statusCode);
    }

    @Test(priority = 34, description = "Test creating access with empty role ID list")
    public void testCreateAccessWithEmptyRoleIdList() {
        System.out.println("Testing create access with empty role ID list");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("roleId", Arrays.asList());

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with empty role ID list returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with empty role ID list should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 35, description = "Test creating access with empty key ID list")
    public void testCreateAccessWithEmptyKeyIdList() {
        System.out.println("Testing create access with empty key ID list");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("keyId", Arrays.asList());

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with empty key ID list returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with empty key ID list should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 36, description = "Test creating access with negative duration limit")
    public void testCreateAccessWithNegativeDurationLimit() {
        System.out.println("Testing create access with negative duration limit");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("durationLimit", "-60");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with negative duration limit returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with negative duration limit should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 37, description = "Test creating access with zero duration limit")
    public void testCreateAccessWithZeroDurationLimit() {
        System.out.println("Testing create access with zero duration limit");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("durationLimit", "0");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with zero duration limit returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 422,
                "Create access with zero duration limit should be handled. Got: " + statusCode);
    }

    @Test(priority = 38, description = "Test creating access with all day access permissions false")
    public void testCreateAccessWithAllDaysAccessFalse() {
        System.out.println("Testing create access with all day access permissions false");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("canAccessMonday", false);
        accessBody.put("canAccessTuesday", false);
        accessBody.put("canAccessWednesday", false);
        accessBody.put("canAccessThursday", false);
        accessBody.put("canAccessFriday", false);
        accessBody.put("canAccessSaturday", false);
        accessBody.put("canAccessSunday", false);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with all days false returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 200 || statusCode == 201,
                "Create access with all days false should be handled. Got: " + statusCode);
    }

    @Test(priority = 39, description = "Test creating access with past dates")
    public void testCreateAccessWithPastDates() {
        System.out.println("Testing create access with past dates");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromDate", TestDataGenerator.getDateFromNow(-30));
        accessBody.put("toDate", TestDataGenerator.getDateFromNow(-10));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with past dates returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 422,
                "Create access with past dates should be handled. Got: " + statusCode);
    }

    @Test(priority = 40, description = "Test creating access with invalid access events structure")
    public void testCreateAccessWithInvalidAccessEventsStructure() {
        System.out.println("Testing create access with invalid access events structure");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("accessEvents", "invalid_structure");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with invalid access events returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 500,
                "Create access with invalid access events should return 400, 422 or 500. Got: " + statusCode);
    }

    // ==================== Edge Case Test Cases - CREATE Operations ====================

    @Test(priority = 41, description = "Test creating access with very long name")
    public void testCreateAccessWithVeryLongName() {
        System.out.println("Testing create access with very long name (255 characters)");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", TestDataGenerator.generateLongString(255));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with long name returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 422,
                "Create access with long name should be handled. Got: " + statusCode);
    }

    @Test(priority = 42, description = "Test creating access with name exceeding max length")
    public void testCreateAccessWithNameExceedingMaxLength() {
        System.out.println("Testing create access with name exceeding max length (500 characters)");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", TestDataGenerator.generateLongString(500));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with exceeding name length returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with exceeding name length should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 43, description = "Test creating access with special characters in name")
    public void testCreateAccessWithSpecialCharactersInName() {
        System.out.println("Testing create access with special characters in name");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "Test@#$%^&*()_+-=[]{}|;':\",./<>?" + TestDataGenerator.generateRandomNumber(1, 99));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with special characters returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 422,
                "Create access with special characters should be handled. Got: " + statusCode);
    }

    @Test(priority = 44, description = "Test creating access with unicode characters in name")
    public void testCreateAccessWithUnicodeCharactersInName() {
        System.out.println("Testing create access with unicode characters in name");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "Tëst Àccéss 日本語 中文 العربية " + TestDataGenerator.generateRandomNumber(1, 99));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with unicode characters returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 422,
                "Create access with unicode characters should be handled. Got: " + statusCode);
    }

    @Test(priority = 45, description = "Test creating access with whitespace only name")
    public void testCreateAccessWithWhitespaceOnlyName() {
        System.out.println("Testing create access with whitespace only name");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "   ");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with whitespace name returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 || statusCode == 422,
                "Create access with whitespace name should return 400 or 422. Got: " + statusCode);
    }

    @Test(priority = 46, description = "Test creating access with leading/trailing spaces in name")
    public void testCreateAccessWithLeadingTrailingSpacesInName() {
        System.out.println("Testing create access with leading/trailing spaces in name");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "  Test Access  " + TestDataGenerator.generateRandomNumber(1, 99));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with leading/trailing spaces returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with leading/trailing spaces should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 47, description = "Test creating access with very large duration limit")
    public void testCreateAccessWithVeryLargeDurationLimit() {
        System.out.println("Testing create access with very large duration limit");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("durationLimit", "999999999");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with large duration limit returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 400 || statusCode == 422,
                "Create access with large duration limit should be handled. Got: " + statusCode);
    }

    @Test(priority = 48, description = "Test creating access with far future dates")
    public void testCreateAccessWithFarFutureDates() {
        System.out.println("Testing create access with far future dates (1 year ahead)");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromDate", TestDataGenerator.getDateFromNow(365));
        accessBody.put("toDate", TestDataGenerator.getDateFromNow(395));

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with far future dates returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with far future dates should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 49, description = "Test creating access with boundary time values")
    public void testCreateAccessWithBoundaryTimeValues() {
        System.out.println("Testing create access with boundary time values (00:00 to 23:59)");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("fromTime", "00:00");
        accessBody.put("toTime", "23:59");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with boundary times returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with boundary times should return 200 or 201. Got: " + statusCode);
    }

    @Test(priority = 50, description = "Test creating access with single day access only")
    public void testCreateAccessWithSingleDayAccessOnly() {
        System.out.println("Testing create access with single day (Monday) access only");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("canAccessMonday", true);
        accessBody.put("canAccessTuesday", false);
        accessBody.put("canAccessWednesday", false);
        accessBody.put("canAccessThursday", false);
        accessBody.put("canAccessFriday", false);
        accessBody.put("canAccessSaturday", false);
        accessBody.put("canAccessSunday", false);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with single day access returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with single day access should return 200 or 201. Got: " + statusCode);
    }

    // ==================== Security Test Cases - CREATE Operations ====================

    @Test(priority = 51, description = "Test creating access with SQL injection in name field")
    public void testCreateAccessWithSqlInjectionInName() {
        System.out.println("Testing create access with SQL injection in name field");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "'; DROP TABLE access; --");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with SQL injection returned status code: " + statusCode);

        // The API should either reject the input or sanitize it, not fail with 500
        Assert.assertTrue(statusCode != 500,
                "Create access should handle SQL injection safely. Got: " + statusCode);
    }

    @Test(priority = 52, description = "Test creating access with SQL injection in code field")
    public void testCreateAccessWithSqlInjectionInCode() {
        System.out.println("Testing create access with SQL injection in code field");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("code", "1 OR 1=1; --");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with SQL injection in code returned status code: " + statusCode);

        Assert.assertTrue(statusCode != 500,
                "Create access should handle SQL injection in code safely. Got: " + statusCode);
    }

    @Test(priority = 53, description = "Test creating access with XSS attack in name field")
    public void testCreateAccessWithXssInName() {
        System.out.println("Testing create access with XSS attack in name field");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "<script>alert('XSS')</script>");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with XSS returned status code: " + statusCode);

        Assert.assertTrue(statusCode != 500,
                "Create access should handle XSS attack safely. Got: " + statusCode);

        // If successful, verify the response doesn't contain the script tags unescaped
        if (statusCode == 200 || statusCode == 201) {
            String responseBody = response.getBody().asString();
            Assert.assertFalse(responseBody.contains("<script>"),
                    "Response should not contain unescaped script tags");
        }
    }

    @Test(priority = 54, description = "Test creating access with HTML injection in name")
    public void testCreateAccessWithHtmlInjectionInName() {
        System.out.println("Testing create access with HTML injection in name field");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "<img src=x onerror=alert('XSS')>");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with HTML injection returned status code: " + statusCode);

        Assert.assertTrue(statusCode != 500,
                "Create access should handle HTML injection safely. Got: " + statusCode);
    }

    @Test(priority = 55, description = "Test creating access with LDAP injection attempt")
    public void testCreateAccessWithLdapInjectionAttempt() {
        System.out.println("Testing create access with LDAP injection attempt");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "admin)(|(password=*))");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with LDAP injection returned status code: " + statusCode);

        Assert.assertTrue(statusCode != 500,
                "Create access should handle LDAP injection safely. Got: " + statusCode);
    }

    @Test(priority = 56, description = "Test creating access with command injection attempt")
    public void testCreateAccessWithCommandInjectionAttempt() {
        System.out.println("Testing create access with command injection attempt");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        accessBody.put("name", "test; rm -rf /;");

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with command injection returned status code: " + statusCode);

        Assert.assertTrue(statusCode != 500,
                "Create access should handle command injection safely. Got: " + statusCode);
    }

    @Test(priority = 57, description = "Test creating access without authentication")
    public void testCreateAccessWithoutAuthentication() {
        System.out.println("Testing create access without authentication");

        AccessApi unauthApi = new AccessApi(false);
        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();

        Response response = unauthApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access without auth returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 401 || statusCode == 403,
                "Create access without authentication should return 401 or 403. Got: " + statusCode);
    }

    // ==================== Performance Test Cases - CREATE Operations ====================

    @Test(priority = 58, description = "Test create access API response time")
    public void testCreateAccessApiResponseTime() {
        System.out.println("Testing create access API response time");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        Response response = accessApi.createAccess(accessBody);

        long responseTime = response.getTime();
        System.out.println("Create access API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 10000,
                "Create access API response time should be less than 10 seconds. Actual: " + responseTime + " ms");
    }

    @Test(priority = 59, description = "Test creating access with JSON content type verification")
    public void testCreateAccessContentTypeVerification() {
        System.out.println("Testing create access content type verification");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();
        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();

        if (statusCode == 200 || statusCode == 201) {
            String contentType = response.getContentType();
            System.out.println("Response Content-Type: " + contentType);

            Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                    "Response Content-Type should be application/json. Got: " + contentType);
        }
    }

    @Test(priority = 60, description = "Test creating access with multiple access events")
    public void testCreateAccessWithMultipleAccessEvents() {
        System.out.println("Testing create access with multiple access events");

        Map<String, Object> accessBody = TestDataGenerator.generateAssetBody();

        List<Map<String, Object>> accessEvents = new ArrayList<>();

        Map<String, Object> event1 = new HashMap<>();
        event1.put("frequency", 2);
        event1.put("event", 1);
        event1.put("per", "day");
        accessEvents.add(event1);

        Map<String, Object> event2 = new HashMap<>();
        event2.put("frequency", 5);
        event2.put("event", 2);
        event2.put("per", "week");
        accessEvents.add(event2);

        accessBody.put("accessEvents", accessEvents);

        Response response = accessApi.createAccess(accessBody);

        int statusCode = response.getStatusCode();
        System.out.println("Create access with multiple events returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Create access with multiple events should return 200 or 201. Got: " + statusCode);
    }

    // ==================== Positive Test Cases - UPDATE Operations ====================

    @Test(priority = 5, description = "Test updating access name")
    public void testUpdateAccessStatus() {
        System.out.println("Testing update access name");

        Response response = accessApi.getAllAccess(1, "Test activity 5");

        Map<String, Object> responseBody = response.jsonPath().getMap("data.data[0]");
        int accessId = response.jsonPath().getInt("data.data[0].id");
        System.out.println("Access Id: " + accessId);
        System.out.println("Response Body: " + responseBody);
        Random randomNumber = new Random();
        String code = String.valueOf(101 + randomNumber.nextInt(898));

        Map<String, Object> accessData =
                response.jsonPath().getMap("data.data[0]");
        Map<String, Object> updateRequest = new HashMap<>();

        updateRequest.put("id", accessData.get("id"));
        updateRequest.put("name", accessData.get("name"));
       // updateRequest.put("code", accessData.get("code"));
        updateRequest.put("code",code );
        updateRequest.put("locationId", accessData.get("locationId"));
        updateRequest.put("durationLimit", accessData.get("durationLimit"));

// Dates
        updateRequest.put("fromDate",
                accessData.get("fromDate").toString().split("T")[0]);
        updateRequest.put("toDate",
                accessData.get("toDate").toString().split("T")[0]);

// Times
        updateRequest.put("fromTime",
                accessData.get("fromTime").toString().substring(11, 16));
        updateRequest.put("toTime",
                accessData.get("toTime").toString().substring(11, 16));

// Access flags
        updateRequest.put("canAccessMonday", accessData.get("canAccessMonday"));
        updateRequest.put("canAccessTuesday", accessData.get("canAccessTuesday"));
        updateRequest.put("canAccessWednesday", accessData.get("canAccessWednesday"));
        updateRequest.put("canAccessThursday", accessData.get("canAccessThursday"));
        updateRequest.put("canAccessFriday", accessData.get("canAccessFriday"));
        updateRequest.put("canAccessSaturday", accessData.get("canAccessSaturday"));
        updateRequest.put("canAccessSunday", accessData.get("canAccessSunday"));
        List<Map<String, Object>> roles =
                (List<Map<String, Object>>) accessData.get("accessRoles");

        List<Integer> roleIds = roles.stream()
                .map(r -> (Integer) r.get("roleId"))
                .collect(Collectors.toList());

        updateRequest.put("roleId", roleIds);


        List<Map<String, Object>> keys =
                (List<Map<String, Object>>) accessData.get("accessKeys");

        List<Integer> keyIds = keys.stream()
                .map(k -> (Integer) k.get("keyId"))
                .collect(Collectors.toList());

        updateRequest.put("keyId", keyIds);


        Response updateResponse = accessApi.updateAccess(updateRequest, accessId);

        int statusCode = updateResponse.getStatusCode();
        System.out.println("Update access name returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200,
                "Update access name should return valid status code. Got: " + statusCode);
    }

    // ==================== Negative Test Cases ====================

    @Test(priority = 6, description = "Test creating access with missing required fields")
    public void testCreateAccessWithMissingFields() {
        System.out.println("Testing create access with missing fields");

        Response response = accessApi.createAccessWithMissingFields();

        int statusCode = response.getStatusCode();
        System.out.println("Create access with missing fields returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 400 ,
                "Create access with missing fields should return 400 or 422. Got: " + statusCode);
    }


    // ==================== Edge Case Test Cases ====================

    @Test(priority = 7, description = "Test pagination with invalid page number")
    public void testPaginationWithInvalidPageNumber() {
        System.out.println("Testing pagination with invalid page number");

        Response response = accessApi.getAllAccess(-1, "");

        int statusCode = response.getStatusCode();
        System.out.println("Invalid page number returned status code: " + statusCode);

        Assert.assertTrue( statusCode == 400 ,
                "Invalid page number should be handled. Got: " + statusCode);
    }

    @Test(priority = 8, description = "Test pagination with empty search")
    public void testPaginationWithEmptySearch() {
        System.out.println("Testing pagination with empty search");

        Response response = accessApi.getAllAccess(1, "");

        int statusCode = response.getStatusCode();
        System.out.println("Empty search returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 ,
                "Empty search should be handled. Got: " + statusCode);
    }

    @Test(priority = 9, description = "Test pagination with large search string")
    public void testPaginationWithLargeSearch() {
        System.out.println("Testing pagination with large search string");

        Response response = accessApi.getAllAccess(1, "test_search_query_123456789425555");

        int statusCode = response.getStatusCode();
        System.out.println("Large search string returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200 ,
                "Large search string should be handled. Got: " + statusCode);
    }

    @Test(priority = 10, description = "Test search with special characters")
    public void testSearchWithSpecialCharacters() {
        System.out.println("Testing search with special characters");

        Response response = accessApi.searchAccess("@#$%^&*()");

        int statusCode = response.getStatusCode();
        System.out.println("Special characters search returned status code: " + statusCode);

        Assert.assertTrue(statusCode == 200,
                "Special characters should be handled. Got: " + statusCode);
    }

    @Test(priority = 11, description = "Test access API response time")
    public void testGetAccessApiResponseTime() {
        System.out.println("Testing access API response time");

        Response response = accessApi.getAllAccess();

        long responseTime = response.getTime();
        System.out.println("Access API response time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 15000,
                "Access API response time should be less than 15 seconds. Actual: " + responseTime + " ms");
    }

}
