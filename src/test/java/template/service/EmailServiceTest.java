package template.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import starter.email.EmailService;
import starter.email.SimpleMessage;
import template.controller.ControllerIntegrationTestsSupport;

public class EmailServiceTest extends ControllerIntegrationTestsSupport {
    @Autowired
    private EmailService emailService;
    
    @Test
    public void shouldSendEmail() throws UnsupportedEncodingException, MessagingException {
        //emailService.sendEmail(new SimpleMessage("support@example.com", "SUPPORT", "man4j@ya.ru", "Hello!", "Test test"));
    }
}
