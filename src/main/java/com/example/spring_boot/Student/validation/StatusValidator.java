package com.example.spring_boot.Student.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<ValidateStatus, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext context) {
        return status.equals("PENDING") || status.equals("DONE");
    }
}
