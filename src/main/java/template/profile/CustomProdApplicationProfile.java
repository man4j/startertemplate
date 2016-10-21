package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class CustomProdApplicationProfile extends CustomApplicationProfile {
    @Override
    public String getDbUrl() {
        return null;
    }
    
    @Override
    public String getDbPassword() {
        return null;
    }
}
