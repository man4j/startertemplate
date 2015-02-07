package template.model;

import org.hibernate.validator.constraints.NotBlank;

public class RestorePasswordForm {
    @NotBlank(message = "{form.email.empty}")
    private String email = "";

    public String getEmail() {
        return email.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
