package eu.koboo.paladins.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat API_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

    static {
        SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static String formatDate() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static long parseFrom(String string) {
        try {
            return API_DATE_FORMAT.parse(string).getTime();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't parse date from string \"" + string + "\": ", e);
        }
    }
}
