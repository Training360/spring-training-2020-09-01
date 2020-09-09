package employees.employees.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    private int maxLength;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return
                value != null && value.length() > 2 && Character.isUpperCase(value.charAt(0)) &&
                        value.length() < maxLength;
    }

    @Override
    public void initialize(Name constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
    }
}
