package com.pratikt112.correbankingsystembe.utility;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = false)
public class DdMmYyyyStringToLocalDateConverter implements AttributeConverter<LocalDate, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        if(localDate == null){
            return "        "; // return spaces in db date, no need in pre-persist function
        }
        return localDate.format(FORMATTER);
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbDate) {
        if(dbDate == null || dbDate.trim().isEmpty()){
            return null;
        }
        return LocalDate.parse(dbDate, FORMATTER);
    }
}
