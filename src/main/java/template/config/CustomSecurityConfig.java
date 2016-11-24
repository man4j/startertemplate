package template.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import starter.config.SecurityConfig;

@Configuration//need for correct proxy configuration class
@ComponentScan("template.security")
public class CustomSecurityConfig extends SecurityConfig {
    //empty
}
