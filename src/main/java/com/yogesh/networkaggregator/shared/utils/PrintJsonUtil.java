package com.yogesh.networkaggregator.shared.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * Utility class for pretty-printing objects as JSON in debug logs.
 * This class provides asynchronous JSON formatting and logging capabilities,
 * with support for Java 8 date/time types and indented output.
 */
@Slf4j
public final class PrintJsonUtil {
    /**
     * Private constructor to prevent instantiation of utility class.
     *
     * @throws IllegalStateException if an attempt is made to instantiate the class
     */
    private PrintJsonUtil() {
        throw new IllegalStateException("PrintJsonUtil class");
    }

    /**
     * Asynchronously logs an object as formatted JSON when debug logging is
     * enabled.
     * The method uses Jackson's ObjectMapper to convert the object to
     * pretty-printed JSON
     * and logs it along with optional context parameters. The conversion and
     * logging
     * are performed asynchronously to minimize impact on the calling thread.
     *
     * @param source     the object to be logged as JSON
     * @param parameters optional context parameters to be logged with the JSON
     */
    public static void log(Object source, String... parameters) {
        if (log.isDebugEnabled()) {
            // Asynchronous execution using CompletableFuture
            CompletableFuture.runAsync(() -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    log.debug("JSON MESSAGE FROM CLASS ::[{}]", (Object) parameters);
                    log.debug(objectMapper
                            .writerWithDefaultPrettyPrinter()
                            .writeValueAsString(source));
                } catch (Exception ignore) {
                    // ignore
                    log.error("Failed to pretty print JSON. [{}]", ignore.getMessage());
                }
            });
        }
    }
}
