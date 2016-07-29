package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("home1")
public class Man4JProfile extends CustomApplicationProfile {
    @Override
    public String getCouchDbUrl() {
        return "http://91.201.42.128:7777";
    }

    @Override
    public int getMessagesCacheInterval() {
        return 0;
    }

    @Override
    public boolean isTemplateCacheEnabled() {
        return false;
    }
}
