package employees;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

    @Override
    public void initialize(Name constraintAnnotation) {

    }
}
