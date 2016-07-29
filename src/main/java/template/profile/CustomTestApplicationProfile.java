package template.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class CustomTestApplicationProfile extends CustomProdApplicationProfile {

}
