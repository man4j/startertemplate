package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class CustomProdApplicationProfile extends CustomApplicationProfile {
    @Override
    public int getMessagesCacheInterval() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isTemplateCacheEnabled() {
        return true;
    }

    @Override
    public String getDbName() {
        return "prod_db";
    }

    @Override
    public String getDbPassword() {
        return null;
    }
}
