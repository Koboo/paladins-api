package eu.koboo.paladins.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter {

    // Used for the url timeStamp parameter
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    // Used by the api-endpoint "gethirezserverstatus"
    private static final SimpleDateFormat STATUS_PARSE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // Used by the api-paths as parameter
    private static final SimpleDateFormat API_PATH_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    // Used by the api-endpoints for date-fields
    private static final SimpleDateFormat[] API_RESPONSE_DATE_FORMATS = new SimpleDateFormat[]{
            new SimpleDateFormat("M/d/yyyy HH:mm:ss a"),
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a"),
            new SimpleDateFormat("MM/d/yyyy HH:mm:ss a"),
            new SimpleDateFormat("M/dd/yyyy HH:mm:ss a"),
    };

    static {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SIMPLE_DATE_FORMAT.setTimeZone(timeZone);
        STATUS_PARSE_DATE_FORMAT.setTimeZone(timeZone);
        API_PATH_DATE_FORMAT.setTimeZone(timeZone);
        for (SimpleDateFormat dateFormat : API_RESPONSE_DATE_FORMATS) {
            dateFormat.setTimeZone(timeZone);
        }
    }

    public static String formatDate() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static long parseFrom(String string) {
        for (SimpleDateFormat dateFormat : API_RESPONSE_DATE_FORMATS) {
            try {
                Date parsedDate = dateFormat.parse(string);
                if(parsedDate == null) {
                    continue;
                }
                return parsedDate.getTime();
            } catch (Exception ignored) { }
        }
        throw new RuntimeException("Couldn't find parseable date for string \"" + string + "\":");
    }

    public static long parseFromServerStatus(String string) {
        try {
            return STATUS_PARSE_DATE_FORMAT.parse(string).getTime();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't parse date from string \"" + string + "\": ", e);
        }
    }

    public static String formatPathDate(long timeStamp) {
        return API_PATH_DATE_FORMAT.format(new Date(timeStamp));
    }
}
