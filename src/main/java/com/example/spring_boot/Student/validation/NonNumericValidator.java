package com.example.spring_boot.Student.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NonNumericValidator implements ConstraintValidator<validateNonNumeric, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^[a-zA-Z]*$");
    }
}
