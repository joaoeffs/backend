package com.joaoeffs.teste.tecnico.validation.quantidade;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = QuantidadePositivaConstraintValidator.class)
public @interface QuantidadePositiva {

    String message() default "Quantidade informada deve ser positiva e maior que zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
