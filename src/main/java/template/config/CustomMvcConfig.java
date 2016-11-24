package template.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import starter.config.MvcConfig;

@Configuration//need for correct proxy configuration class
@ComponentScan({"template.profile", "template.controller", "template.service", "template.fixture"})
public class CustomMvcConfig extends MvcConfig {
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("esftest777@gmail.com");
//        javaMailSender.setPassword("");
        
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
                       
        javaMailSender.setJavaMailProperties(properties);        
        
        return javaMailSender;
    }
}
