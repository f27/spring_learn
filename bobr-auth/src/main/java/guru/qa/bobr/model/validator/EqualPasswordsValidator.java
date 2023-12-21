package guru.qa.bobr.model.validator;

import guru.qa.bobr.model.RegistrationModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, RegistrationModel> {
    @Override
    public boolean isValid(RegistrationModel form, ConstraintValidatorContext context) {
        boolean isValid = form.password().equals(form.passwordSubmit());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("password")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
