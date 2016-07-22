package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import starter.profile.TestApplicationProfile;

@Component
@Profile("test")
public class CustomTestApplicationProfile extends TestApplicationProfile {
    //empty
}
