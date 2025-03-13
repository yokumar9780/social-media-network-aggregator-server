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

@Configuration
@EnableTransactionManagement
public class MongoConfig {


    /*@Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }*/

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new OffsetDateTimeWriter(),
                new OffsetDateTimeReader(),
                new ZonedDateTimeWriter(),
                new ZonedDateTimeReader()
        ));
    }


    public static class OffsetDateTimeWriter implements Converter<OffsetDateTime, String> {
        @Override
        public String convert(@Nullable final OffsetDateTime source) {
            if (null == source) return null;
            return source.toString();
        }
    }

    public static class OffsetDateTimeReader implements Converter<String, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(@Nullable final String source) {
            if (null == source) return null;
            return OffsetDateTime.parse(source);
        }
    }

    public static class ZonedDateTimeWriter implements Converter<ZonedDateTime, String> {
        @Override
        public String convert(@Nullable final ZonedDateTime source) {
            if (null == source) return null;
            return source.toString();
        }
    }

    public static class ZonedDateTimeReader implements Converter<String, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(@Nullable final String source) {
            if (null == source) return null;
            return ZonedDateTime.parse(source);
        }
    }
}
