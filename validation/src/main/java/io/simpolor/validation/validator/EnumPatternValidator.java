package io.simpolor.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, Enum<?>> {

    private EnumPattern annotation;

    @Override
    public void initialize(EnumPattern annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {

        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if(Objects.nonNull(enumValues)) {
            for(Object enumValue : enumValues) {
                if(value.equals(enumValue)){
                    return true;
                }
            }
        }

        return false;
    }
}
