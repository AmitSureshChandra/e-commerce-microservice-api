package com.github.amitsureshchandra.orderservice.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
public class ObjConverter<T> implements AttributeConverter<T, String> {
    private Class<T> clazz;
    private final ObjectMapper mapper;

    public ObjConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String convertToDatabaseColumn(T t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert ItemDto to string: " + e.getMessage());
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.equals("")) return null;
        try {
            return mapper.readValue(dbData, this.clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert string to "+ this.clazz + " : " + e.getMessage());
        }
    }
}
