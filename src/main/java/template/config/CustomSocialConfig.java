package template.config;

import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import starter.config.SocialConfig;

public class CustomSocialConfig extends SocialConfig {
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer conf, Environment env) {
        //f.e.
        //conf.addConnectionFactory(new FacebookConnectionFactory("id", "secret"));
    }
}
