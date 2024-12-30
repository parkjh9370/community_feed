package main.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HH:mm:ss");

    private TimeCalculator() {
    }

    public static LocalDate getDateDaysAgo(int daysAgo) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.minusDays(daysAgo);
    }

    public static String getFormattedDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        return dateTime.format(FORMATTER);
    }
}
