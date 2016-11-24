package template.config;

import java.util.Collections;
import java.util.Set;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import starter.config.DbConfig;

@Configuration//need for correct proxy configuration class
@ComponentScan("template.dao")
public class CustomDbConfig extends DbConfig {
    @Override
    public Set<String> packagesToScan() {
        return Collections.singleton("template.model");
    }
}
