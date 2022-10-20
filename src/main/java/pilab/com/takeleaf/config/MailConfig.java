package pilab.com.takeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pilab.com.takeleaf.Utility.EmailConstructor;

@Configuration
public class MailConfig {
    @Bean
    public EmailConstructor constructor(){
        return new EmailConstructor();
    }
}
