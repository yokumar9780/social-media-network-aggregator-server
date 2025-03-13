package com.yogesh.networkaggregator.shared.utils;

import lombok.Getter;

@Getter
public enum I18Constants {
    NOT_FOUND("not.found");

    private final String key;

    I18Constants(final String key) {
        this.key = key;
    }

}
