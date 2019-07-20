package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    public static String formatDate(LocalDate date) {
        return formatDate("dd-MM-yyyy", date);
    }

    public static String formatDate(String format, LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());

        return date.format(dateTimeFormatter);
    }
}
