package com.pratikt112.correbankingsystembe.utility;

import com.pratikt112.correbankingsystembe.exception.IncompleteDataException;

import java.util.function.Supplier;

public class NullBlankUtility {
    public static void validateNotNull(Object value, String entity, String field){
        if(value == null) throw new IncompleteDataException(entity, field);
    }

    public static void validateNotNull(Object value, Supplier<? extends RuntimeException> exceptionSupplier){
        if(value == null) throw exceptionSupplier.get();
    }

    public static void validateNotBlank(String value, String entity, String field){
        if(value == null || value.isBlank()) throw new IncompleteDataException(entity, field);
    }

    public static void validateNotBlank(String value, Supplier<? extends RuntimeException> exceptionSupplier){
        if(value == null || value.isBlank()) throw exceptionSupplier.get();
    }
}
