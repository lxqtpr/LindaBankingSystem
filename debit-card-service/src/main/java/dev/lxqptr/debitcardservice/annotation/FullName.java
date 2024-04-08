package dev.lxqptr.debitcardservice.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotNull(
        message = "This field is required."
)
@Pattern(
        regexp = "^[а-яА-ЯёЁa-zA-Z]+\\s+[а-яА-ЯёЁa-zA-Z]+\\s+[а-яА-ЯёЁa-zA-Z]+$",
        message = "Please enter your full name as First Name Last Name Middle Name."
)
public @interface FullName {

    String message() default "Invalid full name.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}