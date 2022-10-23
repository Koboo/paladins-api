package eu.koboo.paladins.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter {

    // Used for the url timeStamp parameter
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    // Used by the api-endpoints for date-fields
    private static final SimpleDateFormat API_PARSE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
    // Used by the api-paths as parameter
    private static final SimpleDateFormat API_PATH_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    static {
        SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static String formatDate() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static long parseFrom(String string) {
        try {
            return API_PARSE_DATE_FORMAT.parse(string).getTime();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't parse date from string \"" + string + "\": ", e);
        }
    }

    public static String formatPathDate(long timeStamp) {
        return API_PATH_DATE_FORMAT.format(new Date(timeStamp));
    }
}
