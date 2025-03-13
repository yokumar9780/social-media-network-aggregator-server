package com.yogesh.networkaggregator.shared.utils;

import lombok.Getter;

/**
 * Enumeration of internationalization (i18n) message keys.
 * This enum defines the keys used to look up localized messages in the
 * application's
 * message bundles. Each constant represents a specific message type.
 */
@Getter
public enum I18Constants {
    /**
     * Key for "not found" error messages.
     */
    NOT_FOUND("not.found");

    /**
     * The message key used for message bundle lookup.
     */
    private final String key;

    /**
     * Constructs an I18Constants enum value with the specified message key.
     *
     * @param key the message key for bundle lookup
     */
    I18Constants(final String key) {
        this.key = key;
    }
}
