package api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.io.InputStream;

public class SchemaValidator {

    private static final String SCHEMA_BASE_PATH = "src/test/resources/schemas/";

    public static void validateSchema(Response response, String schemaFileName) {
        try {
            File schemaFile = new File(SCHEMA_BASE_PATH + schemaFileName);
            if (!schemaFile.exists()) {
                System.out.println("WARNING: Schema file not found: " + schemaFile.getAbsolutePath());
                System.out.println("Skipping schema validation for: " + schemaFileName);
                return;
            }

            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

            System.out.println("Schema validation passed for: " + schemaFileName);
        } catch (AssertionError e) {
            System.err.println("Schema validation failed for: " + schemaFileName);
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static void validateSchemaFromClasspath(Response response, String schemaPath) {
        try {
            InputStream schemaStream = SchemaValidator.class.getClassLoader()
                    .getResourceAsStream("schemas/" + schemaPath);

            if (schemaStream == null) {
                System.out.println("WARNING: Schema not found in classpath: " + schemaPath);
                System.out.println("Skipping schema validation");
                return;
            }

            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(schemaStream));

            System.out.println("Schema validation passed for: " + schemaPath);
        } catch (AssertionError e) {
            System.err.println("Schema validation failed for: " + schemaPath);
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static void validateLoginResponseSchema(Response response) {
        validateSchema(response, "login-response-schema.json");
    }

    public static void validateUserResponseSchema(Response response) {
        validateSchema(response, "user-response-schema.json");
    }

    public static void validateUserListResponseSchema(Response response) {
        validateSchema(response, "user-list-response-schema.json");
    }

    public static void validateReportResponseSchema(Response response) {
        validateSchema(response, "report-response-schema.json");
    }

    public static void validateReportListResponseSchema(Response response) {
        validateSchema(response, "report-list-response-schema.json");
    }

    public static void validateDashboardResponseSchema(Response response) {
        validateSchema(response, "dashboard-response-schema.json");
    }

    public static void validateAccessResponseSchema(Response response) {
        validateSchema(response, "access-response-schema.json");
    }

    public static void validateAccessListResponseSchema(Response response) {
        validateSchema(response, "access-list-response-schema.json");
    }

    public static void validateErrorResponseSchema(Response response) {
        validateSchema(response, "error-response-schema.json");
    }

    public static void validateRequiredFields(Response response, String... requiredFields) {
        for (String field : requiredFields) {
            Object value = response.jsonPath().get(field);
            Assert.assertNotNull(value, "Required field '" + field + "' is missing from response");
        }
        System.out.println("All required fields validated successfully");
    }

    public static void validateFieldTypes(Response response, java.util.Map<String, Class<?>> fieldTypeMap) {
        for (java.util.Map.Entry<String, Class<?>> entry : fieldTypeMap.entrySet()) {
            String field = entry.getKey();
            Class<?> expectedType = entry.getValue();
            Object value = response.jsonPath().get(field);

            if (value != null) {
                Assert.assertTrue(expectedType.isInstance(value),
                        "Field '" + field + "' expected type " + expectedType.getSimpleName() +
                                " but was " + value.getClass().getSimpleName());
            }
        }
        System.out.println("All field types validated successfully");
    }

    public static boolean schemaExists(String schemaFileName) {
        File schemaFile = new File(SCHEMA_BASE_PATH + schemaFileName);
        return schemaFile.exists();
    }
}
