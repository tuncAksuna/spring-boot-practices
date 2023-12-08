package com.interview.tuncode.configurations.entityauditing;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class EntityAuditorConfiguration {

    public CustomAuditorAware auditorProvider() {
        return new CustomAuditorAware();
    }
}
