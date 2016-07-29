package template.profile;

import starter.profile.ApplicationProfile;

public abstract class CustomApplicationProfile implements ApplicationProfile {
  public abstract String getCouchDbUrl();
}
