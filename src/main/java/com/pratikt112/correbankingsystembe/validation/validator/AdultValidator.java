package com.pratikt112.correbankingsystembe.validation.validator;

import com.pratikt112.correbankingsystembe.validation.annotation.Adult;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    private int minAge;


    @Override
    public void initialize(Adult constraintAnnotation) {
        this.minAge = constraintAnnotation.minAge();
    }

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
        if(dob == null){
            return true;
        }
        LocalDate today = LocalDate.now();
        if(dob.isAfter(today)){
            return false;
        }
        return Period.between(dob, today).getYears() >= minAge;
    }
}
