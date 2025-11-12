package com.example.spring_boot.Student.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented

@Constraint(validatedBy = StudentNameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateNonNumeric {
    String message() default "Field must not contain numeric characters";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
