package Utils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestLogger {

        private static final String LOG_PREFIX_INFO = "[INFO]";
        private static final String LOG_PREFIX_DEBUG = "[DEBUG]";
        private static final String LOG_PREFIX_ERROR = "[ERROR]";
        private static final String LOG_PREFIX_SUCCESS = "[SUCCESS]";
        private static final String LOG_PREFIX_WARNING = "[WARNING]";

        /**
         * Get current timestamp
         * @return Formatted timestamp string
         */
        private static String getTimestamp() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }

        /**
         * Log info message
         * @param message Message to log
         */
        public static void info(String message) {
            System.out.println(getTimestamp() + " " + LOG_PREFIX_INFO + " " + message);
        }

        /**
         * Log debug message
         * @param message Message to log
         */
        public static void debug(String message) {
            System.out.println(getTimestamp() + " " + LOG_PREFIX_DEBUG + " " + message);
        }

        /**
         * Log error message
         * @param message Message to log
         */
        public static void error(String message) {
            System.err.println(getTimestamp() + " " + LOG_PREFIX_ERROR + " " + message);
        }

        /**
         * Log success message
         * @param message Message to log
         */
        public static void success(String message) {
            System.out.println(getTimestamp() + " " + LOG_PREFIX_SUCCESS + " " + message);
        }

        /**
         * Log warning message
         * @param message Message to log
         */
        public static void warning(String message) {
            System.out.println(getTimestamp() + " " + LOG_PREFIX_WARNING + " " + message);
        }

        /**
         * Log test step
         * @param stepNumber Step number
         * @param description Step description
         */
        public static void step(int stepNumber, String description) {
            System.out.println(getTimestamp() + " [STEP " + stepNumber + "] " + description);
        }

}
