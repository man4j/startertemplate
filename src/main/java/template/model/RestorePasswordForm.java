package template.model;

import java.util.Locale;

import org.hibernate.validator.constraints.NotBlank;

public class RestorePasswordForm {
    @NotBlank(message = "{form.email.empty}")
    private String email = "";

    public String getEmail() {
        return email.toLowerCase(Locale.US);
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
