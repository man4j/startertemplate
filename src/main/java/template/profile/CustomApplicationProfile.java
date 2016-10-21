package template.profile;

import starter.profile.ApplicationProfile;

public abstract class CustomApplicationProfile implements ApplicationProfile {
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
        return "jdbc:mysql://localhost:3306/dev_db";
    }
    
    @Override
    public String getDbUser() {
        return "root";
    }
    
    @Override
    public String getDbPassword() {
        return "root";
    }
    
    @Override
    public boolean isShowSql() {
        return false;
    }
}
