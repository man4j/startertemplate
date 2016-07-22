package template.config;

import org.springframework.context.annotation.ComponentScan;

import starter.config.SecurityConfig;

@ComponentScan({"template.security"})
public class CustomSecurityConfig extends SecurityConfig {
    //empty
}
