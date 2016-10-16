package template.profile;

import starter.profile.ApplicationProfile;

public abstract class CustomApplicationProfile implements ApplicationProfile {
    @Override
    public String getDbUrl() {
        return "jdbc:mysql://localhost:3306";
    }
    
    @Override
    public String getDbUser() {
        return "root";
    }
    
    @Override
    public boolean isShowSql() {
        return false;
    }
}
