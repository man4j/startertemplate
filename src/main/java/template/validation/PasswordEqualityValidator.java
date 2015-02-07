package template.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import template.model.SignUpForm;

public class PasswordEqualityValidator implements ConstraintValidator<PasswordEquality, SignUpForm> {
    @Override
    public void initialize(PasswordEquality constraintAnnotation) {
        // empty
    }

    @Override
    public boolean isValid(SignUpForm form, ConstraintValidatorContext context) {                
        if (form.getPassword() == null || form.getPassword().trim().isEmpty()) return true;
        if (form.getConfirmPassword() == null || form.getConfirmPassword().trim().isEmpty()) return true;

        return form.getPassword().equals(form.getConfirmPassword());
    }
}
