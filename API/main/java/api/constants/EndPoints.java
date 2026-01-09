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
    public static final String USERS = "/api/users";
    public static final String USER_BY_ID = "/api/users/{id}";
    public static final String USER_PROFILE = "/api/users/profile";
    public static final String USER_UPDATE = "/api/users/{id}";
    public static final String USER_DELETE = "/api/users/{id}";
    public static final String USER_LIST = "/api/users/list";

    // ==================== Report Endpoints ====================
    public static final String REPORTS = "/api/reports";
    public static final String REPORT_BY_ID = "/api/reports/{id}";
    public static final String REPORT_GENERATE = "/api/reports/generate";
    public static final String REPORT_DOWNLOAD = "/api/reports/{id}/download";
    public static final String REPORT_LIST = "/api/reports/list";

    // ==================== Dashboard Endpoints ====================
    public static final String DASHBOARD = "/api/dashboard/get";


    // ==================== Access Management Endpoints ====================
    public static final String ACCESS = "/api/access/get";
    public static final String ACCESS_BY_ID = "/api/access/get";
    public static final String ACCESS_CREATE = "/api/access/create";
        public static final String ACCESS_UPDATE = "/api/access/update";
    public static final String ACCESS_DELETE = "/api/access/{id}";
    public static final String ACCESS_LIST = "/api/access/get";


    // ==================== Activity Endpoints ====================
    public static final String ACTIVITIES = "/api/activities";
    public static final String ACTIVITY_BY_ID = "/api/activities/{id}";
    public static final String ACTIVITY_CREATE = "/api/activities";
    public static final String ACTIVITY_UPDATE = "/api/activities/{id}";
    public static final String ACTIVITY_DELETE = "/api/activities/{id}";
    public static final String ACTIVITY_LIST = "/api/activities/list";

    // ==================== Level Endpoints ====================
    public static final String LEVELS = "/api/levels";
    public static final String LEVEL_BY_ID = "/api/levels/{id}";
    public static final String LEVEL_CREATE = "/api/levels";
    public static final String LEVEL_UPDATE = "/api/levels/{id}";
    public static final String LEVEL_DELETE = "/api/levels/{id}";
    public static final String LEVEL_LIST = "/api/levels/list";

    // ==================== Role Endpoints ====================
    public static final String ROLES = "/api/roles";
    public static final String ROLE_BY_ID = "/api/roles/{id}";
    public static final String ROLE_CREATE = "/api/roles";
    public static final String ROLE_UPDATE = "/api/roles/{id}";
    public static final String ROLE_DELETE = "/api/roles/{id}";
    public static final String ROLE_LIST = "/api/roles/list";

    // ==================== Keybox Endpoints ====================
    public static final String KEYBOXES = "/api/keyboxes";
    public static final String KEYBOX_BY_ID = "/api/keyboxes/{id}";
    public static final String KEYBOX_CREATE = "/api/keyboxes";
    public static final String KEYBOX_UPDATE = "/api/keyboxes/{id}";
    public static final String KEYBOX_DELETE = "/api/keyboxes/{id}";
    public static final String KEYBOX_LIST = "/api/keyboxes/list";
    public static final String KEYBOX_REGISTER = "/api/keyboxes/register";

    // ==================== Key Position Template Endpoints ====================
    public static final String KEY_POSITION_TEMPLATES = "/api/key-position-templates";
    public static final String KEY_POSITION_TEMPLATE_BY_ID = "/api/key-position-templates/{id}";
    public static final String KEY_POSITION_TEMPLATE_CREATE = "/api/key-position-templates";
    public static final String KEY_POSITION_TEMPLATE_UPDATE = "/api/key-position-templates/{id}";
    public static final String KEY_POSITION_TEMPLATE_DELETE = "/api/key-position-templates/{id}";
    public static final String KEY_POSITION_TEMPLATE_LIST = "/api/key-position-templates/list";

    // ==================== Device Activity Endpoints ====================
    public static final String DEVICE_ACTIVITIES = "/api/device-activities";
    public static final String DEVICE_ACTIVITY_BY_ID = "/api/device-activities/{id}";
    public static final String DEVICE_ACTIVITY_LIST = "/api/device-activities/list";
    public static final String DEVICE_ACTIVITY_BY_DEVICE = "/api/device-activities/device/{deviceId}";

    // ==================== Common Endpoints ====================
    public static final String HEALTH_CHECK = "/api/health";
    public static final String VERSION = "/api/version";
}
