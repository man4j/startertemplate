package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class CustomTestApplicationProfile extends CustomProdApplicationProfile {
    @Override
    public String getDbUrl() {
        return "jdbc:mysql://app_db:3306/test_db";
    }

    @Override
    public String getDbPassword() {
        return System.getenv("db_password");
    }
}
