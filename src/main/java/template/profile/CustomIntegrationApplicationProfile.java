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
        String dbUrl =  isAutomated() ? System.getProperty("db.url") : super.getDbUrl();
        
        System.out.println("Database URL: " + dbUrl);
        
        return dbUrl;
    }

    @Override
    public String getDbName() {
        return isAutomated() ? "test_db" : "dev_db";
    }

    @Override
    public String getDbPassword() {
        String password = isAutomated() ?  System.getProperty("db.password") : "root";
        
        System.out.println("Database password: " + password);
        
        return password;
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
