package com.pratikt112.correbankingsystembe.validation.validator;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.validation.annotation.ValidPanOrForm60;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PanOrForm60Validator implements ConstraintValidator<ValidPanOrForm60, CobData> {
    @Override
    public boolean isValid(CobData cobData, ConstraintValidatorContext constraintValidatorContext) {
        if(cobData == null) return true;

        boolean hasPan = cobData.getCustPanNo() != null  && !cobData.getCustPanNo().isBlank();
        boolean hasForm60 = cobData.getCustForm60() != null;

        return !(hasPan && hasForm60);
    }
}
