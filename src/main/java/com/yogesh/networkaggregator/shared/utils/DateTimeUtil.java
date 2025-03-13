package com.yogesh.networkaggregator.shared.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTimeUtil {

    public static final DateTimeFormatter ISO_OFFSET_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    // Define the formatter for parsing and formatting
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    public static final String EUROPE_STOCKHOLM_TIME_ZONE = "Europe/Stockholm";

    public static final DateTimeFormatter FORMATTER_UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneOffset.UTC);

    public static String getCurrentDateTime(String zoneId) {
        ZonedDateTime zonedDateTime;
        if (StringUtils.hasText(zoneId)) {
            zonedDateTime = ZonedDateTime.now(ZoneId.of(zoneId));
        } else {
            zonedDateTime = ZonedDateTime.now(ZoneId.of(EUROPE_STOCKHOLM_TIME_ZONE));
        }
        return zonedDateTime.format(FORMATTER);
    }

    public static FormattedDateTime getFormattedDateTimeWithZone(String zoneId) {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of(zoneId));
        return getFormattedDateTime(zonedDateTime, FORMATTER);
    }

    public static FormattedDateTime getFormattedDateTime(ZonedDateTime zonedDateTime, DateTimeFormatter formatter) {
        String creationDateTime = zonedDateTime.format(formatter);
        String creationTimeZone = zonedDateTime.getZone().toString();
        return new FormattedDateTime(creationDateTime, creationTimeZone);
    }

    public static FormattedDateTime getFormattedDateTimeWithZone(LocalDateTime localDateTime, String zoneId) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(zoneId));
        return getFormattedDateTime(zonedDateTime, FORMATTER);
    }

    public static String buildShipmentDateTime(String inputDateTime, Integer plusMinutes) {
        if (ObjectUtils.isEmpty(plusMinutes)) {
            plusMinutes = 0;
        }
        // Parse the input to a ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDateTime, FORMATTER);

        ZonedDateTime finalZonedDateTime = zonedDateTime.plusMinutes(plusMinutes);
        log.debug("finalZonedDateTime {}", finalZonedDateTime);
        return finalZonedDateTime.format(FORMATTER);
    }

    public static String buildShipmentDateTime(String inputDateTime, LocalTime predefinedTime,
                                               Integer plusMinutes, String timeZone) {
        if (!StringUtils.hasText(timeZone)) {
            timeZone = EUROPE_STOCKHOLM_TIME_ZONE;
        }
        if (ObjectUtils.isEmpty(plusMinutes)) {
            plusMinutes = 0;
        }
        if (ObjectUtils.isEmpty(predefinedTime)) {
            return inputDateTime;
        }
        // Parse the input to a ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDateTime, FORMATTER);

        // Extract only the date part
        LocalDate dateOnly = zonedDateTime.toLocalDate();

        // Define a predefined time to add (e.g., 09:00:00)
        //LocalTime predefinedTime = LocalTime.of(9, 0);
        log.debug("predefinedTime {}", predefinedTime);
        // Combine the date with the predefined time
        LocalDateTime dateTimeWithPredefinedTime = dateOnly.atTime(predefinedTime);
        log.debug("dateTimeWithPredefinedTime {}", dateTimeWithPredefinedTime);
        // Convert to ZonedDateTime with the original zone

        ZonedDateTime updatedZonedDateTime = dateTimeWithPredefinedTime.atZone(ZoneId.of(timeZone));

        // Add a few hours
        ZonedDateTime finalZonedDateTime = updatedZonedDateTime.plusMinutes(plusMinutes);
        log.debug("finalZonedDateTime {}", finalZonedDateTime);
        // Format the output back to the desired pattern
        return finalZonedDateTime.format(FORMATTER);
    }

    public record FormattedDateTime(String creationDateTime, String creationTimeZone) {
    }


}
