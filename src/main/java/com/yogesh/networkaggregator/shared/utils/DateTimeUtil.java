package com.yogesh.networkaggregator.shared.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for handling date and time operations.
 * This class provides methods for formatting, parsing, and manipulating dates
 * and times
 * with support for different time zones and formats.
 */
@Slf4j
public class DateTimeUtil {
    /**
     * Formatter for ISO-8601 date-time with offset.
     */
    public static final DateTimeFormatter ISO_OFFSET_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * Custom formatter for date-time with offset in format
     * "yyyy-MM-dd'T'HH:mm:ssXXX".
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    /**
     * Default time zone ID for Stockholm, Sweden.
     */
    public static final String EUROPE_STOCKHOLM_TIME_ZONE = "Europe/Stockholm";

    /**
     * Formatter for UTC date-time in format "yyyy-MM-dd'T'HH:mm:ss'Z'".
     */
    public static final DateTimeFormatter FORMATTER_UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneOffset.UTC);

    /**
     * Gets the current date-time formatted string for a specified time zone.
     *
     * @param zoneId the time zone ID, defaults to Europe/Stockholm if null or empty
     * @return formatted date-time string
     */
    public static String getCurrentDateTime(String zoneId) {
        ZonedDateTime zonedDateTime;
        if (StringUtils.hasText(zoneId)) {
            zonedDateTime = ZonedDateTime.now(ZoneId.of(zoneId));
        } else {
            zonedDateTime = ZonedDateTime.now(ZoneId.of(EUROPE_STOCKHOLM_TIME_ZONE));
        }
        return zonedDateTime.format(FORMATTER);
    }

    /**
     * Gets the current date-time formatted with zone information.
     *
     * @param zoneId the time zone ID
     * @return FormattedDateTime containing date-time string and zone information
     */
    public static FormattedDateTime getFormattedDateTimeWithZone(String zoneId) {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of(zoneId));
        return getFormattedDateTime(zonedDateTime, FORMATTER);
    }

    /**
     * Formats a ZonedDateTime using the specified formatter.
     *
     * @param zonedDateTime the date-time to format
     * @param formatter     the formatter to use
     * @return FormattedDateTime containing formatted date-time string and zone
     *         information
     */
    public static FormattedDateTime getFormattedDateTime(ZonedDateTime zonedDateTime, DateTimeFormatter formatter) {
        String creationDateTime = zonedDateTime.format(formatter);
        String creationTimeZone = zonedDateTime.getZone().toString();
        return new FormattedDateTime(creationDateTime, creationTimeZone);
    }

    /**
     * Gets a formatted date-time with zone information from a LocalDateTime.
     *
     * @param localDateTime the local date-time
     * @param zoneId        the time zone ID
     * @return FormattedDateTime containing formatted date-time string and zone
     *         information
     */
    public static FormattedDateTime getFormattedDateTimeWithZone(LocalDateTime localDateTime, String zoneId) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(zoneId));
        return getFormattedDateTime(zonedDateTime, FORMATTER);
    }

    /**
     * Builds a shipment date-time by adding minutes to an input date-time.
     *
     * @param inputDateTime the input date-time string
     * @param plusMinutes   minutes to add, defaults to 0 if null
     * @return formatted date-time string with added minutes
     */
    public static String buildShipmentDateTime(String inputDateTime, Integer plusMinutes) {
        if (ObjectUtils.isEmpty(plusMinutes)) {
            plusMinutes = 0;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDateTime, FORMATTER);
        ZonedDateTime finalZonedDateTime = zonedDateTime.plusMinutes(plusMinutes);
        log.debug("finalZonedDateTime {}", finalZonedDateTime);
        return finalZonedDateTime.format(FORMATTER);
    }

    /**
     * Builds a shipment date-time by combining a date with a predefined time and
     * adding minutes.
     *
     * @param inputDateTime  the input date-time string
     * @param predefinedTime the time to set
     * @param plusMinutes    minutes to add after setting the time, defaults to 0 if
     *                       null
     * @param timeZone       the time zone ID, defaults to Europe/Stockholm if null
     *                       or empty
     * @return formatted date-time string
     */
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
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDateTime, FORMATTER);
        LocalDate dateOnly = zonedDateTime.toLocalDate();
        log.debug("predefinedTime {}", predefinedTime);
        LocalDateTime dateTimeWithPredefinedTime = dateOnly.atTime(predefinedTime);
        log.debug("dateTimeWithPredefinedTime {}", dateTimeWithPredefinedTime);
        ZonedDateTime updatedZonedDateTime = dateTimeWithPredefinedTime.atZone(ZoneId.of(timeZone));
        ZonedDateTime finalZonedDateTime = updatedZonedDateTime.plusMinutes(plusMinutes);
        log.debug("finalZonedDateTime {}", finalZonedDateTime);
        return finalZonedDateTime.format(FORMATTER);
    }

    /**
     * Record for holding formatted date-time information.
     *
     * @param creationDateTime the formatted date-time string
     * @param creationTimeZone the time zone string
     */
    public record FormattedDateTime(String creationDateTime, String creationTimeZone) {
    }
}
