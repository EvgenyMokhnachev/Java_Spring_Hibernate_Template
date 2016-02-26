package utils;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TimeHelper {
    // 99:59:59
    private final static long MAX_VALUE = 99 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000;

    /**
     * returns time from millis in "hh:mm:ss" format
     *
     */
    public String getTimeFromMillis(long millis) {
        if (millis < 0 || millis > MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        Duration duration = new Duration(millis);

        long hours = duration.getStandardHours();
        long minutes = duration.getStandardMinutes() % 60;
        long seconds = duration.getStandardSeconds() % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * returns time passed from start to current time
     *
     */
    public String getTimeAgo(DateTime start) {
        return getTimeAgo(start, DateTime.now());
    }

    /**
     * returns time passed from start to end
     *
     */
    public String getTimeAgo(DateTime start, DateTime end) {
        Duration duration = new Duration(start, end);

        long days = duration.getStandardDays();
        if (days > 0) {
            String pattern = start.getYear() == end.getYear() ?
                                                    "MMMM, d" :
                                                "yyyy MMMM, d";

            return DateTimeFormat.forPattern(pattern).withLocale(Locale.ENGLISH).print(start);
        }

        long hours = duration.getStandardHours();
        if (hours > 1) return hours + " hours ago";
        if (hours == 1) return "1 hour ago";

        long minutes = duration.getStandardMinutes();
        if (minutes > 1) return minutes + " minutes ago";
        if (minutes == 1) return "1 minute ago";

        return "a moment ago";
    }

}