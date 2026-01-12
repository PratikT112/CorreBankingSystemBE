package com.pratikt112.correbankingsystembe.validation.annotation;


import com.pratikt112.correbankingsystembe.validation.validator.PanOrForm60Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PanOrForm60Validator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPanOrForm60 {
    String message() default "Either PAN or Form60 can be provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
