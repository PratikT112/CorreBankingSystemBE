package com.pratikt112.correbankingsystembe.utility;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static LocalDate toLocalDate(String dateStr){
        if(dateStr == null || dateStr.trim().isEmpty()){
            throw new IllegalArgumentException("Dat String cannot be null or empty");
        }
        try{
            return LocalDate.parse(dateStr, FORMATTER);
        } catch(DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date format, expected ddMMyyyy: " + dateStr, e);
        }
    }

    public static String toString(LocalDate date){
        if(date == null) return null;
        return date.format(FORMATTER);
    }

    public static int compareDate(String d1, String d2){
        return toLocalDate(d1).compareTo(toLocalDate(d2));
    }
}
