package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateUtils {

    public static String formatDate(LocalDate date) {
        return formatDate("dd-MM-yyyy", date);
    }

    public static String formatDate(String format, LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());

        return date.format(dateTimeFormatter);
    }

    public static String formatDate(Instant time) {
        return formatDate("dd-MM-yyyy HH:mm:ss", time);
    }

    public static String formatDate(String format, Instant time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());

        return dateTimeFormatter.format(time);
    }
}
