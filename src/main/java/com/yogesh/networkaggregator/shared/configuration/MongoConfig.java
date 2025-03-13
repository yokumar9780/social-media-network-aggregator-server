package com.yogesh.networkaggregator.shared.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * MongoDB configuration class that sets up custom type conversions and
 * transaction management.
 * This class provides custom converters for handling Java 8 date/time types in
 * MongoDB
 * and enables transaction management capabilities.
 */
@Configuration
@EnableTransactionManagement
public class MongoConfig {

    /*
     * @Bean
     * MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
     * return new MongoTransactionManager(dbFactory);
     * }
     */

    /**
     * Creates a custom MongoDB conversions configuration.
     * This bean registers custom converters for handling OffsetDateTime and
     * ZonedDateTime
     * types when reading from and writing to MongoDB.
     *
     * @return MongoCustomConversions instance configured with custom type
     *         converters
     */
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new OffsetDateTimeWriter(),
                new OffsetDateTimeReader(),
                new ZonedDateTimeWriter(),
                new ZonedDateTimeReader()));
    }

    /**
     * Custom converter for writing OffsetDateTime to MongoDB as a String.
     */
    public static class OffsetDateTimeWriter implements Converter<OffsetDateTime, String> {
        /**
         * Converts an OffsetDateTime to its String representation.
         *
         * @param source the OffsetDateTime to convert, may be null
         * @return the String representation of the OffsetDateTime, or null if source is
         *         null
         */
        @Override
        public String convert(@Nullable final OffsetDateTime source) {
            if (null == source)
                return null;
            return source.toString();
        }
    }

    /**
     * Custom converter for reading OffsetDateTime from MongoDB String
     * representation.
     */
    public static class OffsetDateTimeReader implements Converter<String, OffsetDateTime> {
        /**
         * Converts a String to an OffsetDateTime.
         *
         * @param source the String to convert, may be null
         * @return the parsed OffsetDateTime, or null if source is null
         */
        @Override
        public OffsetDateTime convert(@Nullable final String source) {
            if (null == source)
                return null;
            return OffsetDateTime.parse(source);
        }
    }

    /**
     * Custom converter for writing ZonedDateTime to MongoDB as a String.
     */
    public static class ZonedDateTimeWriter implements Converter<ZonedDateTime, String> {
        /**
         * Converts a ZonedDateTime to its String representation.
         *
         * @param source the ZonedDateTime to convert, may be null
         * @return the String representation of the ZonedDateTime, or null if source is
         *         null
         */
        @Override
        public String convert(@Nullable final ZonedDateTime source) {
            if (null == source)
                return null;
            return source.toString();
        }
    }

    /**
     * Custom converter for reading ZonedDateTime from MongoDB String
     * representation.
     */
    public static class ZonedDateTimeReader implements Converter<String, ZonedDateTime> {
        /**
         * Converts a String to a ZonedDateTime.
         *
         * @param source the String to convert, may be null
         * @return the parsed ZonedDateTime, or null if source is null
         */
        @Override
        public ZonedDateTime convert(@Nullable final String source) {
            if (null == source)
                return null;
            return ZonedDateTime.parse(source);
        }
    }
}
