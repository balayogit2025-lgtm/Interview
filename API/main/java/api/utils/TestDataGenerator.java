package api.utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateEmailWithDomain(String domain) {
        return faker.name().username().toLowerCase() + "@" + domain;
    }

    public static String generateUsername() {
        return faker.name().username();
    }

    public static String generateFullName() {
        return faker.name().fullName();
    }

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generatePassword() {
        return faker.internet().password(8, 16, true, true, true);
    }

    public static String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    public static String generateCompanyName() {
        return faker.company().name();
    }

    public static Map<String, Object> generateUserBody() {
        Map<String, Object> userBody = new HashMap<>();
        userBody.put("name", generateFullName());
        userBody.put("email", generateEmail());
        userBody.put("password", generatePassword());
        userBody.put("role", "user");
        return userBody;
    }

    public static Map<String, Object> generateUserBody(String role) {
        Map<String, Object> userBody = generateUserBody();
        userBody.put("role", role);
        return userBody;
    }

    public static Map<String, Object> generateAdminUserBody() {
        return generateUserBody("admin");
    }

    public static String getTodayDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    public static String getDateFromNow(int days) {
        return LocalDate.now().plusDays(days).format(DATE_FORMATTER);
    }

    public static String getDateMonthsFromNow(int months) {
        return LocalDate.now().plusMonths(months).format(DATE_FORMATTER);
    }

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    public static String getRandomPastDate(int maxDaysAgo) {
        int daysAgo = faker.number().numberBetween(1, maxDaysAgo);
        return LocalDate.now().minusDays(daysAgo).format(DATE_FORMATTER);
    }

    public static String getRandomFutureDate(int maxDaysAhead) {
        int daysAhead = faker.number().numberBetween(1, maxDaysAhead);
        return LocalDate.now().plusDays(daysAhead).format(DATE_FORMATTER);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateNumericId() {
        return String.valueOf(faker.number().numberBetween(1, 999999));
    }

    public static int generateNumericId(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static Map<String, Object> generateAssetBody() {
        String name = "Test" + generateRandomNumber(0, 100);
        String code = String.valueOf(generateRandomNumber(100, 999));
        Map<String, Object> accessBody = new HashMap<>();
        accessBody.put("name", name);
        accessBody.put("code", code);
        accessBody.put("locationId", 6);
        accessBody.put("roleId", Arrays.asList(59));
        accessBody.put("keyId", Arrays.asList(2));
        accessBody.put("levelId", 6);

        Map<String, Object> accessEvent = new HashMap<>();
        accessEvent.put("frequency", 1);
        accessEvent.put("event", 1);
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
        accessBody.put("fromDate", getTodayDate());
        accessBody.put("toDate", getDateFromNow(2));
        accessBody.put("fromTime", getTimeNow());
        accessBody.put("toTime", getTimeNow());

        return accessBody;
    }

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

    public static Map<String, Object> generateAssetBody(String name) {
        Map<String, Object> accessBody = generateAssetBody();
        accessBody.put("name", name);
        return accessBody;
    }

    public static Map<String, Object> generateReportBody() {
        Map<String, Object> reportBody = new HashMap<>();
        reportBody.put("type", faker.options().option("daily", "weekly", "monthly", "quarterly", "annual"));
        reportBody.put("startDate", getRandomPastDate(30));
        reportBody.put("endDate", getTodayDate());
        reportBody.put("format", faker.options().option("pdf", "excel", "csv"));
        return reportBody;
    }

    public static Map<String, Object> generateReportBody(String reportType) {
        Map<String, Object> reportBody = generateReportBody();
        reportBody.put("type", reportType);
        return reportBody;
    }

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

    public static String generateWeakPassword() {
        return faker.options().option(
                "123",
                "password",
                "abc",
                "1234567",
                ""
        );
    }

    public static String generateLongString(int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(faker.lorem().word());
        }
        return sb.substring(0, length);
    }

    public static String generateSpecialCharacterString() {
        return "<script>alert('XSS')</script>";
    }

    public static String generateSqlInjectionString() {
        return "'; DROP TABLE users; --";
    }

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

    public static String generateEmptyValue() {
        return "";
    }

    public static String generateWhitespaceString() {
        return "   ";
    }

    public static int generateValidPageNumber() {
        return faker.number().numberBetween(1, 100);
    }

    public static int generateValidPageSize() {
        return faker.options().option(10, 20, 50, 100);
    }

    public static int generateInvalidPageNumber() {
        return faker.options().option(-1, 0, -100);
    }

    public static int generateInvalidPageSize() {
        return faker.options().option(-1, 0, 10000);
    }
}
