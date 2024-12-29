//package com.interview.tuncode.security;
//
//import lombok.NonNull;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtClaimNames;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Component
//public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
//
//    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//    private final KeyCloakConfigurationProperties keyCloakConfigurationProperties;
//
//    public JwtAuthConverter(KeyCloakConfigurationProperties properties) {
//        this.keyCloakConfigurationProperties = properties;
//    }
//
//    /**
//     * It takes a JWT token and converts it into a JwtAuthenticationToken object that Spring Security can use for authentication and authorization operations.
//     * During conversion, it extracts user information and roles from the token.
//     */
//    @Override
//    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
//        Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
//                        extractRoles(jwt).stream())
//                .collect(Collectors.toSet());
//
//        return new JwtAuthenticationToken(jwt, authorities, getPrincipleName(jwt));
//    }
//
//    /**
//     * Determines the claim used to obtain the username in the token.
//     * By default it uses the sub claim, but according to properties.getPrincipleAttribute()
//     * can be customized. It works with the KeyCloakConfiguration class and the configuration received from this class
//     * This customization process is done according to the values.
//     */
//    private String getPrincipleName(Jwt jwt) {
//        String claimName  = JwtClaimNames.SUB;
//
//        if (keyCloakConfigurationProperties.getPrincipleAttribute() != null) {
//            claimName  = keyCloakConfigurationProperties.getPrincipleAttribute();
//        }
//
//        return jwt.getClaim(claimName );
//    }
//
//    /**
//     * This method allows extracting resource server-specific roles from the resource_access claim in the jwt token.
//     * That is, it converts these roles into GrantedAuthority objects that Spring Security can understand.
//     * There must be ROLE_ at the beginning of the roles, we ensure this with this method.
//     */
//    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
//        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
//        Map<String, Object> resource;
//        Collection<String> resourceRoles;
//
//        if (resourceAccess == null || (resource = (Map<String, Object>) resourceAccess.get(keyCloakConfigurationProperties.getResourceId())) == null || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
//            return Set.of();
//        }
//
//        return resourceRoles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toSet());
//    }
//}
//
