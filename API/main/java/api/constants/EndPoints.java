package api.constants;

/**
 * Constants class containing all API endpoints.
 * Centralizes endpoint management for easy maintenance.
 */
public final class EndPoints {

    // Private constructor to prevent instantiation
    private EndPoints() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // ==================== Authentication Endpoints ====================
    public static final String LOGIN = "/api/auth/login";
    public static final String LOGOUT = "/api/auth/logout";
    public static final String REFRESH_TOKEN = "/api/auth/refresh";
    public static final String FORGOT_PASSWORD = "/api/auth/resetPassword";
    public static final String RESET_PASSWORD = "/api/auth/reset-password";
    public static final String REQUEST_OTP= "/api/auth/requestOtp";

    // ==================== User Management Endpoints ====================
    public static final String USERS = "/api/user/get";
    public static final String USER_BY_Email = "/api/user/get?search=";
    public static final String USER_CREATE = "/api/user/create";
    public static final String USER_UPDATE = "/api/user/update";
    public static final String USER_DELETE = "/api/user/delete";
    public static final String USER_LIST = "/api/user/get?page=1&search=";

    // ==================== Report Endpoints ====================
    public static final String REPORTS = "/api/reports";
    public static final String REPORT_BY_ID = "/api/reports/{id}";
    public static final String REPORT_GENERATE = "/api/reports/generate";
    public static final String REPORT_DOWNLOAD = "/api/reports/{id}/download";
    public static final String REPORT_LIST = "/api/reports/list";

    // ==================== Dashboard Endpoints ====================
    public static final String DASHBOARD = "/api/dashboard/get";
    public static final String DASHBOARD_STATS = "/api/dashboard/get";
    public static final String DASHBOARD_SUMMARY = "/api/dashboard/get";

    // ==================== Access Management Endpoints ====================
    public static final String ACCESS = "/api/access/get";
    public static final String ACCESS_BY_ID = "/api/access/get";
    public static final String ACCESS_CREATE = "/api/access/create";
    public static final String ACCESS_UPDATE = "/api/access/update";
    public static final String ACCESS_DELETE = "/api/access/{id}";
    public static final String ACCESS_LIST = "/api/access/get";


    // ==================== Activity Endpoints ====================
    public static final String ACTIVITIES = "/api/access/get";
    public static final String ACTIVITY_BY_NAME = "/api/access/get";
    public static final String ACTIVITY_CREATE = "/api/access/create";
    public static final String ACTIVITY_UPDATE = "/api/access/update";
    public static final String ACTIVITY_LIST = "/api/access/get?page=1&search=";

    // ==================== Level Endpoints ====================
    public static final String LEVELS_REGION = "/api/level/location?page=0&locationId=1";
    public static final String LEVELS_STATE = "/api/level/location?page=0&locationId=39";
    public static final String LEVELS_TERRITORY = "/api/level/location?page=0&locationId=40";
    public static final String LEVELS_CLUSTER = "/api/level/location?page=0&locationId=41";
    public static final String LEVELS_LOCATION="/api/level/location?page=0&locationId=42";
    public static final String LEVEL_BY_NAME = "/api/level/location?page=1&search=&locationId=1";
    public static final String LEVEL_CREATE = "/api/level";
    public static final String LEVEL_UPDATE = "/api/level";
    public static final String LEVEL_LIST = "/api/level/location?page=1&locationId=1";

    // ==================== Role Endpoints ====================
    public static final String ROLES = "/api/role/get";
    public static final String ROLE_BY_NAME = "/api/role/get?search=";
    public static final String ROLE_CREATE = "/api/role/create";
    public static final String ROLE_UPDATE = "/api/role/update";
    public static final String ROLE_DELETE = "/api/role/delete";
    public static final String ROLE_LIST = "/api/role/get?page=1&search=";

    // ==================== Keybox Endpoints ====================
    public static final String KEYBOXES = "/api/keyboxes";
    public static final String KEYBOX_BY_ID = "/api/keyboxes/{id}";
    public static final String KEYBOX_CREATE = "/api/keyboxes";
    public static final String KEYBOX_UPDATE = "/api/keyboxes/{id}";
    public static final String KEYBOX_DELETE = "/api/keyboxes/{id}";
    public static final String KEYBOX_LIST = "/api/keyboxes/list";
    public static final String KEYBOX_REGISTER = "/api/keyboxes/register";

    // ==================== Key Position Template Endpoints ====================
    public static final String KEY_POSITION_TEMPLATES = "/api/keyPattern";
    public static final String KEY_POSITION_TEMPLATE_BY_NAME = "/api/keyPattern";
    public static final String KEY_POSITION_TEMPLATE_CREATE = "/api/keyPattern";
    public static final String KEY_POSITION_TEMPLATE_UPDATE = "/api/keyPattern";


    // ==================== Device Activity Endpoints ====================
    public static final String DEVICE_ACTIVITIES = "/api/device-activities";
    public static final String DEVICE_ACTIVITY_BY_ID = "/api/device-activities/{id}";
    public static final String DEVICE_ACTIVITY_LIST = "/api/device-activities/list";
    public static final String DEVICE_ACTIVITY_BY_DEVICE = "/api/device-activities/device/{deviceId}";

    // ==================== Common Endpoints ====================
    public static final String HEALTH_CHECK = "/api/health";
    public static final String VERSION = "/api/version";
}
