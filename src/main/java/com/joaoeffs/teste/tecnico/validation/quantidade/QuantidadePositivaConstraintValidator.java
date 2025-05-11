package com.joaoeffs.teste.tecnico.validation.quantidade;

import static java.util.Objects.isNull;

import jakarta.validation.ConstraintValidator;

public class QuantidadePositivaConstraintValidator implements ConstraintValidator<QuantidadePositiva, Integer> {

    @Override
    public boolean isValid(Integer quantidade, jakarta.validation.ConstraintValidatorContext context) {
        if (isNull(quantidade))
            return true;

        return quantidade.compareTo(0) > 0;
    }
}
