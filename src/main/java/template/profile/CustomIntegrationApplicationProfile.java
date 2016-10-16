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
        String url =  isAutomated() ? System.getProperty("db.url") : super.getDbUrl();
        
        System.out.println("Database URL for Spring integration tests: " + url);
        
        return url;
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
        return Boolean.getBoolean("test.automated");
    }
}
