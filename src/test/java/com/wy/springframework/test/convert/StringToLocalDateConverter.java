package com.wy.springframework.test.convert;

import com.wy.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private final DateTimeFormatter dateTimeFormatter;

    public StringToLocalDateConverter(String pattern) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source,dateTimeFormatter);
    }
}
