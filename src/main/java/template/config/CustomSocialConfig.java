package template.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;

import starter.config.SocialConfig;

@Configuration//need for correct proxy configuration class
public class CustomSocialConfig extends SocialConfig {
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer conf, Environment env) {
        //conf.addConnectionFactory(new FacebookConnectionFactory("id", "secret"));
    }
}
