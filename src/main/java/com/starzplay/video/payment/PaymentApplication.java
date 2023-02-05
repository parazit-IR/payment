package com.starzplay.video.payment;

import com.starzplay.video.payment.config.SecurityAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PaymentApplication {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SecurityAuditorAware();
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

}
