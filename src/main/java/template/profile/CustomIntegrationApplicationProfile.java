package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("integration")
public class CustomIntegrationApplicationProfile extends CustomApplicationProfile {
    @Override
    public int getMessagesCacheInterval() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isTemplateCacheEnabled() {
        return true;
    }
    
    @Override
    public String getDbUrl() {
        return isAutomated() ? System.getProperty("db.url") : super.getDbUrl();
    }

    @Override
    public String getDbName() {
        return isAutomated() ? "test_db" : "dev_db";
    }

    @Override
    public String getDbPassword() {
        return isAutomated() ?  System.getProperty("db.password") : "root";
    }
    
    @Override
    public boolean isShowSql() {
        return !isAutomated();
    }
    
    private boolean isAutomated() {
        String property = System.getProperty("test.automated");
        
        return property != null && Boolean.getBoolean(property);
    }
}
