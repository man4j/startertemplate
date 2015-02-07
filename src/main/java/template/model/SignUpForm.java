package template.model;

import template.validation.PasswordEquality;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@PasswordEquality
public class SignUpForm {
    @NotBlank(message = "{form.email.empty}")
    @Email(message = "{form.email.notCorrect}")
    private String email = "";

    @Size(min = 6, max = 18, message = "{form.password.size}")
    @Pattern(regexp = "[a-zA-Z0-9_-]*", message = "{form.password.format}")
    private String password = "";

    @Size(min = 6, max = 18, message = "{form.confirmPassword.size}")
    @Pattern(regexp = "[a-zA-Z0-9_-]*", message = "{form.confirmPassword.format}")
    private String confirmPassword = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
