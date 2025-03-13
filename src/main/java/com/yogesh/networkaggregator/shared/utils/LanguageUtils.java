package com.yogesh.networkaggregator.shared.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Utility class for handling internationalization (i18n) message resolution.
 * This class provides static methods for retrieving localized messages from
 * the application's message source.
 */
public final class LanguageUtils {
    /**
     * Private constructor to prevent instantiation of utility class.
     *
     * @throws IllegalStateException if an attempt is made to instantiate the class
     */
    private LanguageUtils() {
        throw new IllegalStateException("Cannot instantiate utils class");
    }

    /**
     * Retrieves a localized message for a specific locale.
     *
     * @param messageSource the message source containing localized messages
     * @param locale        the target locale for message resolution
     * @param key           the message key to look up
     * @param params        parameters to be substituted into the message template
     * @return the localized message with parameters substituted
     */
    public static String getLocaleMessage(final MessageSource messageSource, final Locale locale, final String key,
            final Object... params) {
        return messageSource.getMessage(key, params, locale);
    }

    /**
     * Retrieves a localized message using English locale.
     * TODO: Currently only supports English locale, should be extended for
     * multi-language support.
     *
     * @param messageSource the message source containing localized messages
     * @param key           the message key to look up
     * @param params        parameters to be substituted into the message template
     * @return the localized message with parameters substituted
     */
    public static String getLocaleMessage(final MessageSource messageSource, final String key, final Object... params) {
        Locale locale = Locale.ENGLISH;
        return messageSource.getMessage(key, params, locale);
    }
}