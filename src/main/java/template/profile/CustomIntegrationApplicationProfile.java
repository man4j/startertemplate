package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("integration")
public class CustomIntegrationApplicationProfile extends CustomApplicationProfile {
    @Override
    public String getDbUser() {
        String dbUser =  System.getProperty("db.user");
        
        System.out.println("Database user for Spring integration tests: " + dbUser);
        
        return dbUser;
    }
    
    @Override
    public String getDbUrl() {
        String dbUrl =  System.getProperty("db.url");
        
        System.out.println("Database URL for Spring integration tests: " + dbUrl);
        
        return dbUrl;
    }

    @Override
    public String getDbPassword() {
        String dbPassword = System.getProperty("db.password");
        
        return dbPassword;
    }
    
    @Override
    public boolean isShowSql() {
        return true;
    }
}
