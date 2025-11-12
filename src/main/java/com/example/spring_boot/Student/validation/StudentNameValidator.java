package com.example.spring_boot.Student.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StudentNameValidator implements ConstraintValidator<ValidateNonNumeric, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^[a-zA-Z]*$");
    }
}
