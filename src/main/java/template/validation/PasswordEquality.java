package template.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualityValidator.class)
@Documented
public @interface PasswordEquality {
    String message() default "{form.password.notEquals}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
