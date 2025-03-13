package com.yogesh.networkaggregator.shared.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

public final class LanguageUtils {
    private LanguageUtils() {
        throw new IllegalStateException("Cannot instantiate utils class");
    }


    public static String getLocaleMessage(final MessageSource messageSource, final Locale locale, final String key, final Object... params) {
        return messageSource.getMessage(key, params, locale);
    }

    public static String getLocaleMessage(final MessageSource messageSource, final String key, final Object... params) {
        //TODO: Supported English Locale for now
        Locale locale = Locale.ENGLISH;
        return messageSource.getMessage(key, params, locale);
    }
}