package com.starzplay.video.payment.config;


import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Configuration {
}
