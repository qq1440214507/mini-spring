package com.wy.springframework.core.convert.converter;

import com.wy.springframework.core.convert.converter.Converter;

public interface ConverterFactory<S,R> {
    <T extends R> Converter<S,T> getConverter(Class<T> targetType);
}
