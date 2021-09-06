package com.wy.springframework.core.convert.support;

import com.wy.springframework.core.convert.ConversionService;
import com.wy.springframework.core.convert.converter.Converter;
import com.wy.springframework.core.convert.converter.ConverterFactory;
import com.wy.springframework.core.convert.converter.ConverterRegistry;
import com.wy.springframework.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class GenericConversionService implements ConversionService, ConverterRegistry {
    private Map<GenericConverter.ConvertiblePair, GenericConverter> converters = new HashMap<>();

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        final GenericConverter converter = getConverter(sourceType, targetType);
        return null != converter;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        final Class<?> sourceType = source.getClass();
        final GenericConverter converter = getConverter(sourceType, targetType);
        return (T) converter.convert(source,sourceType,targetType);
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
        final GenericConverter.ConvertiblePair typeInfo = getRequireTypeInfo(converter);
        ConvertAdapter convertAdapter = new ConvertAdapter(typeInfo,converter);
        for (GenericConverter.ConvertiblePair convertibleType : convertAdapter.getConvertibleTypes()) {
            converters.put(convertibleType,convertAdapter);
        }
    }

    @Override
    public void addConverter(GenericConverter converter) {
        for (GenericConverter.ConvertiblePair convertibleType : converter.getConvertibleTypes()) {
            converters.put(convertibleType,converter);
        }
    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        final GenericConverter.ConvertiblePair typeInfo = getRequireTypeInfo(converterFactory);
        ConverterFactoryAdapter converterFactoryAdapter = new ConverterFactoryAdapter(typeInfo,converterFactory);
        for (GenericConverter.ConvertiblePair convertibleType : converterFactoryAdapter.getConvertibleTypes()) {
            converters.put(convertibleType,converterFactoryAdapter);
        }
    }

    private GenericConverter.ConvertiblePair getRequireTypeInfo(Object object){
        final Type[] types = object.getClass().getGenericInterfaces();
        final ParameterizedType parameterized = (ParameterizedType) types[0];
        final Type[] actualTypeArguments = parameterized.getActualTypeArguments();
        Class sourceType = (Class) actualTypeArguments[0];
        Class targetType = (Class) actualTypeArguments[1];
        return new GenericConverter.ConvertiblePair(sourceType,targetType);
    }

    protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType) {
        List<Class<?>> sourceCandidates = getClassHierarchy(sourceType);
        List<Class<?>> targetCandidates = getClassHierarchy(targetType);
        for (Class<?> sourceCandidate : sourceCandidates) {
            for (Class<?> targetCandidate : targetCandidates) {
                GenericConverter.ConvertiblePair convertiblePair = new GenericConverter.ConvertiblePair(sourceCandidate,targetCandidate);
                final GenericConverter converter = converters.get(convertiblePair);
                if (converter!=null){
                    return converter;
                }
            }
        }
        return null;
    }

    private List<Class<?>> getClassHierarchy(Class<?> clazz) {
        List<Class<?>> hierarchy = new ArrayList<>();
        while (clazz != null) {
            hierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return hierarchy;
    }

    private final class ConvertAdapter implements GenericConverter {
        private final ConvertiblePair typeInfo;
        private final Converter<Object, Object> converter;

        private ConvertAdapter(ConvertiblePair typeInfo, Converter<?, ?> converter) {
            this.typeInfo = typeInfo;
            this.converter = (Converter<Object, Object>) converter;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converter.convert(source);
        }
    }

    private final class ConverterFactoryAdapter implements GenericConverter {
        private final ConvertiblePair typeInfo;
        private final ConverterFactory<Object, Object> converterFactory;

        private ConverterFactoryAdapter(ConvertiblePair typeInfo, ConverterFactory<?, ?> converterFactory) {
            this.typeInfo = typeInfo;
            this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converterFactory.getConverter(targetType).convert(source);
        }
    }

}
