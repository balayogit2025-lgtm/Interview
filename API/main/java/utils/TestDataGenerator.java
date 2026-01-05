package utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Utility class for generating test data.
 * Uses JavaFaker library to generate realistic random data for API testing.
 * Provides methods for creating user data, dates, and edge case values.
 */
public class TestDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // ==================== User Data Generation ====================

    /**
     * Generate a random valid email address
     * @return random email address
     */
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generate a random email with specific domain
     * @param domain the email domain
     * @return email address with specified domain
     */
    public static String generateEmailWithDomain(String domain) {
        return faker.name().username().toLowerCase() + "@" + domain;
    }

    /**
     * Generate a random username
     * @return random username
     */
    public static String generateUsername() {
        return faker.name().username();
    }

    /**
     * Generate a random full name
     * @return random full name
     */
    public static String generateFullName() {
        return faker.name().fullName();
    }

    /**
     * Generate a random first name
     * @return random first name
     */
    public static String generateFirstName() {
        return faker.name().firstName();
    }

    /**
     * Generate a random last name
     * @return random last name
     */
    public static String generateLastName() {
        return faker.name().lastName();
    }

    /**
     * Generate a random valid password
     * @return random password meeting common password requirements
     */
    public static String generatePassword() {
        return faker.internet().password(8, 16, true, true, true);
    }

    /**
     * Generate a random phone number
     * @return random phone number
     */
    public static String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    /**
     * Generate a random address
     * @return random street address
     */
    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    /**
     * Generate a random company name
     * @return random company name
     */
    public static String generateCompanyName() {
        return faker.company().name();
    }

    // ==================== User Request Body Generation ====================

    /**
     * Generate a complete user request body with random data
     * @return Map containing user details
     */
    public static Map<String, Object> generateUserBody() {
        Map<String, Object> userBody = new HashMap<>();
        userBody.put("name", generateFullName());
        userBody.put("email", generateEmail());
        userBody.put("password", generatePassword());
        userBody.put("role", "user");
        return userBody;
    }

    /**
     * Generate a user request body with specific role
     * @param role the user role
     * @return Map containing user details
     */
    public static Map<String, Object> generateUserBody(String role) {
        Map<String, Object> userBody = generateUserBody();
        userBody.put("role", role);
        return userBody;
    }

    /**
     * Generate admin user request body
     * @return Map containing admin user details
     */
    public static Map<String, Object> generateAdminUserBody() {
        return generateUserBody("admin");
    }

    // ==================== Date/Time Generation ====================

    /**
     * Get today's date in yyyy-MM-dd format
     * @return today's date string
     */
    public static String getTodayDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * Get date X days from now
     * @param days number of days (positive for future, negative for past)
     * @return formatted date string
     */
    public static String getDateFromNow(int days) {
        return LocalDate.now().plusDays(days).format(DATE_FORMATTER);
    }

    /**
     * Get date X months from now
     * @param months number of months
     * @return formatted date string
     */
    public static String getDateMonthsFromNow(int months) {
        return LocalDate.now().plusMonths(months).format(DATE_FORMATTER);
    }

    /**
     * Get current date-time in ISO format
     * @return current datetime string
     */
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * Get a random past date within the last X days
     * @param maxDaysAgo maximum days in the past
     * @return random past date string
     */
    public static String getRandomPastDate(int maxDaysAgo) {
        int daysAgo = faker.number().numberBetween(1, maxDaysAgo);
        return LocalDate.now().minusDays(daysAgo).format(DATE_FORMATTER);
    }

    /**
     * Get a random future date within the next X days
     * @param maxDaysAhead maximum days in the future
     * @return random future date string
     */
    public static String getRandomFutureDate(int maxDaysAhead) {
        int daysAhead = faker.number().numberBetween(1, maxDaysAhead);
        return LocalDate.now().plusDays(daysAhead).format(DATE_FORMATTER);
    }

    // ==================== ID/UUID Generation ====================

    /**
     * Generate a random UUID
     * @return UUID string
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a random numeric ID
     * @return random numeric ID string
     */
    public static String generateNumericId() {
        return String.valueOf(faker.number().numberBetween(1, 999999));
    }

    /**
     * Generate a random numeric ID within range
     * @param min minimum value
     * @param max maximum value
     * @return random numeric ID
     */
    public static int generateNumericId(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    // ==================== Asset Data Generation ====================

    /**
     * Generate access request body for create access API
     * @return Map containing access details
     */

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static Map<String, Object> generateAssetBody() {

        String name = "Test"+generateRandomNumber(0,100);
        String code = String.valueOf(generateRandomNumber(100,999));
        Map<String, Object> accessBody = new HashMap<>();
        accessBody.put("name", name);
        accessBody.put("code",code );
        accessBody.put("locationId", 6);
        accessBody.put("roleId", Arrays.asList(59));
        accessBody.put("keyId", Arrays.asList(2));
        accessBody.put("levelId", 6);

        // Access events
        Map<String, Object> accessEvent = new HashMap<>();
        accessEvent.put("frequency", 1);
        accessEvent.put("event", 1);
        accessEvent.put("per", "day");
        accessBody.put("accessEvents", Arrays.asList(accessEvent));

        // Day access permissions
        accessBody.put("canAccessMonday", true);
        accessBody.put("canAccessTuesday", true);
        accessBody.put("canAccessWednesday", true);
        accessBody.put("canAccessThursday", true);
        accessBody.put("canAccessFriday", true);
        accessBody.put("canAccessSaturday", true);
        accessBody.put("canAccessSunday", true);

        // Duration and time constraints
        accessBody.put("durationLimit", "120");
        accessBody.put("fromDate", getTodayDate());
        accessBody.put("toDate", getDateFromNow(2));
        accessBody.put("fromTime", getTimeNow());
        accessBody.put("toTime", getTimeNow());

        return accessBody;
    }

//    public static Map<String, Object> generateupdateAccessBody() {
//
//        String id =''
//        String name = "Test Activity 4";
//        String code = "333";
//        Map<String, Object> accessBody = new HashMap<>();
//        accessBody.put("name", name);
//        accessBody.put("code",code );
//        accessBody.put("locationId", 39);
//        accessBody.put("roleId", 57);
//        accessBody.put("keyId",24);
//        accessBody.put("levelId", 2);
//
//        // Access events
//        Map<String, Object> accessEvent = new HashMap<>();
//        accessEvent.put("frequency", 1);
//        accessEvent.put("event", 1);
//        accessEvent.put("per", "day");
//        accessBody.put("accessEvents", Arrays.asList(accessEvent));
//
//        // Day access permissions
//        accessBody.put("canAccessMonday", true);
//        accessBody.put("canAccessTuesday", true);
//        accessBody.put("canAccessWednesday", true);
//        accessBody.put("canAccessThursday", true);
//        accessBody.put("canAccessFriday", true);
//        accessBody.put("canAccessSaturday", true);
//        accessBody.put("canAccessSunday", true);
//
//        // Duration and time constraints
//        accessBody.put("durationLimit", "120");
//        accessBody.put("fromDate", getTodayDate());
//        accessBody.put("toDate", getDateFromNow(2));
//        accessBody.put("fromTime", getTimeNow());
//        accessBody.put("toTime", getTimeNow());
//
//        return accessBody;
//    }



    public static Map<String, Object> generateUpdateAccessBody(int accessId) {

        Map<String, Object> accessBody = new HashMap<>();

        accessBody.put("id", accessId);
        accessBody.put("name", "Test Activity Updated");
        accessBody.put("code", "297");
        accessBody.put("locationId", 6);

        accessBody.put("roleId", Arrays.asList(59));
        accessBody.put("keyId", Arrays.asList(2));

        accessBody.put("levelId", 6);

        Map<String, Object> accessEvent = new HashMap<>();
        accessEvent.put("frequency", 1);
        accessEvent.put("event", 10);
        accessEvent.put("per", "day");

        accessBody.put("accessEvents", Arrays.asList(accessEvent));

        accessBody.put("canAccessMonday", true);
        accessBody.put("canAccessTuesday", true);
        accessBody.put("canAccessWednesday", true);
        accessBody.put("canAccessThursday", true);
        accessBody.put("canAccessFriday", true);
        accessBody.put("canAccessSaturday", true);
        accessBody.put("canAccessSunday", true);

        accessBody.put("durationLimit", "120");
        accessBody.put("fromDate", "2025-12-31");
        accessBody.put("toDate", "2026-01-02");
        accessBody.put("fromTime", "11:30");
        accessBody.put("toTime", "18:30");

        return accessBody;
    }


    private static String getTimeNow() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.now().format(timeFormatter);
    }


    /**
     * Generate access request body with specific name
     * @param name the access name
     * @return Map containing access details
     */
    public static Map<String, Object> generateAssetBody(String name) {
        Map<String, Object> accessBody = generateAssetBody();
        accessBody.put("name", name);
        return accessBody;
    }

    // ==================== Report Data Generation ====================

    /**
     * Generate report request body
     * @return Map containing report request details
     */
    public static Map<String, Object> generateReportBody() {
        Map<String, Object> reportBody = new HashMap<>();
        reportBody.put("type", faker.options().option("daily", "weekly", "monthly", "quarterly", "annual"));
        reportBody.put("startDate", getRandomPastDate(30));
        reportBody.put("endDate", getTodayDate());
        reportBody.put("format", faker.options().option("pdf", "excel", "csv"));
        return reportBody;
    }

    /**
     * Generate report request body with specific type
     * @param reportType the report type
     * @return Map containing report request details
     */
    public static Map<String, Object> generateReportBody(String reportType) {
        Map<String, Object> reportBody = generateReportBody();
        reportBody.put("type", reportType);
        return reportBody;
    }

    // ==================== Edge Case / Invalid Data Generation ====================

    /**
     * Generate an invalid email format
     * @return invalid email string
     */
    public static String generateInvalidEmail() {
        return faker.options().option(
                "invalidemail",
                "invalid@",
                "@nodomain.com",
                "spaces in@email.com",
                "missing.domain@",
                ""
        );
    }

    /**
     * Generate a weak password
     * @return weak password string
     */
    public static String generateWeakPassword() {
        return faker.options().option(
                "123",
                "password",
                "abc",
                "1234567",
                ""
        );
    }

    /**
     * Generate an extremely long string
     * @param length the desired length
     * @return string of specified length
     */
    public static String generateLongString(int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(faker.lorem().word());
        }
        return sb.substring(0, length);
    }

    /**
     * Generate a string with special characters
     * @return string with special characters
     */
    public static String generateSpecialCharacterString() {
        return "<script>alert('XSS')</script>";
    }

    /**
     * Generate SQL injection attempt string
     * @return SQL injection string for testing
     */
    public static String generateSqlInjectionString() {
        return "'; DROP TABLE users; --";
    }

    /**
     * Generate an invalid/non-existent ID
     * @return invalid ID string
     */
    public static String generateInvalidId() {
        return faker.options().option(
                "invalid-id-" + System.currentTimeMillis(),
                "-1",
                "0",
                "999999999",
                "abc123xyz",
                ""
        );
    }

    /**
     * Generate empty/null values for testing
     * @return empty string
     */
    public static String generateEmptyValue() {
        return "";
    }

    /**
     * Generate whitespace-only string
     * @return whitespace string
     */
    public static String generateWhitespaceString() {
        return "   ";
    }

    // ==================== Pagination Test Data ====================

    /**
     * Generate valid page number
     * @return valid page number
     */
    public static int generateValidPageNumber() {
        return faker.number().numberBetween(1, 100);
    }

    /**
     * Generate valid page size
     * @return valid page size
     */
    public static int generateValidPageSize() {
        return faker.options().option(10, 20, 50, 100);
    }

    /**
     * Generate invalid page number
     * @return invalid page number
     */
    public static int generateInvalidPageNumber() {
        return faker.options().option(-1, 0, -100);
    }

    /**
     * Generate invalid page size
     * @return invalid page size
     */
    public static int generateInvalidPageSize() {
        return faker.options().option(-1, 0, 10000);
    }
}
