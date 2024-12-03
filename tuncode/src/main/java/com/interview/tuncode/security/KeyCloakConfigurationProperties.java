package com.interview.tuncode.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt-auth-converter")
public class KeyCloakConfigurationProperties {

    private String resourceId;
    private String principleAttribute;
}
