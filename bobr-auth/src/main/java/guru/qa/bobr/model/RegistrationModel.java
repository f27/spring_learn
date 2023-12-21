package guru.qa.bobr.model;

import guru.qa.bobr.model.validator.EqualPasswords;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@EqualPasswords
public record RegistrationModel(
        @NotNull(message = "Username can not be null")
        @NotEmpty(message = "Username can not be empty")
        @Size(max = 50, message = "Username can`t be longer than 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_-]*$",  message = "Username can contain only letters, numbers, _ and - symbols")
        String username,
        @NotNull(message = "Password can not be null")
        @Size(min = 3, max = 12, message = "Allowed password length should be from 3 to 12 characters")
        String password,
        @NotNull(message = "Password submit can not be null")
        String passwordSubmit) {

}
