package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class CustomDevApplicationProfile extends CustomApplicationProfile {
    @Override
    public boolean isShowSql() {
        return true;
    }

    @Override
    public boolean isTemplateCacheEnabled() {
        return false;
    }

    @Override
    public int getMessagesCacheInterval() {
        return 0;
    }
}
