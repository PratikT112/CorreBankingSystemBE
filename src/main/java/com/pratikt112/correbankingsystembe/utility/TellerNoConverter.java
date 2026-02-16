package com.pratikt112.correbankingsystembe.utility;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import org.hibernate.annotations.ConcreteProxy;

@Converter(autoApply = false)
public class TellerNoConverter implements AttributeConverter<String, String> {

    private static final int LEN = 16;


    @Override
    public String convertToDatabaseColumn(String attribute) {
        if(attribute == null) return null;

        return String.format("%0" + LEN + "d",
                Long.parseLong(attribute));
    }


    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
