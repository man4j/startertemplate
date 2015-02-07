package template.model;

import org.hibernate.validator.constraints.NotBlank;

public class SigninForm {
    @NotBlank(message = "{form.email.empty}")
    private String email = "";

    @NotBlank(message = "{form.password.empty}")
    private String password = "";

    private boolean rememberMe;

    public String getEmail() {
        return email.toLowerCase();
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

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
