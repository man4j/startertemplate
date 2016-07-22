package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import starter.profile.ApplicationProfile;

@Component
@Profile("prod")
public class CustomProdApplicationProfile implements ApplicationProfile {
    @Override
    public int getMessagesCacheInterval() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isTemplateCacheEnabled() {
        return true;
    }
}
