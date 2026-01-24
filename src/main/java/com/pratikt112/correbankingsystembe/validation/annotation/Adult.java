package com.pratikt112.correbankingsystembe.validation.annotation;


import com.pratikt112.correbankingsystembe.validation.validator.AdultValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdultValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Adult {
    int minAge() default 18;

    String message() default "User must be at least {minAge} years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
