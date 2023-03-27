package com.github.amitsureshchandra.productservice.convertor;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.UUID;

public class UUIDToLongConverter implements Converter<UUID, Long> {
    @Override
    public Long convert(MappingContext<UUID, Long> context) {
        UUID uuid = context.getSource();
        if (uuid == null) {
            return null;
        }
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        return mostSignificantBits ^ leastSignificantBits;
    }
}
