package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class CustomDevApplicationProfile extends CustomApplicationProfile {
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
        return "dev_db";
    }

    @Override
    public String getDbPassword() {
        return "root";
    }
    
    @Override
    public boolean isShowSql() {
        return true;
    }
}
