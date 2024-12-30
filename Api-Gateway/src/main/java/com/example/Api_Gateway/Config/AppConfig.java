package com.example.Api_Gateway.Config;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Capability capability(final MeterRegistry registry){
        return new MicrometerCapability(registry);
    }
}
