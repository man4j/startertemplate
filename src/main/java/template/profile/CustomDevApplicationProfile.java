package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import starter.profile.DevApplicationProfile;

@Component
@Profile("dev")
public class CustomDevApplicationProfile extends DevApplicationProfile {
    //empty
}
