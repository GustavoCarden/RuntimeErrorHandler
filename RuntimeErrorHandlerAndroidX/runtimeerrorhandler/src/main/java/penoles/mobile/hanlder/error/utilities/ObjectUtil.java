package penoles.mobile.hanlder.error.utilities;


import java.util.Calendar;

public class ObjectUtil {

    public static String currentDate() {
        return currentDate(System.currentTimeMillis());
    }

    public static String currentDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
    }

    public static String currentTime() {
        return currentTime(System.currentTimeMillis());
    }

    public static String currentTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return String.format("%02d:%02d:%02d.%03d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND));
    }

}
