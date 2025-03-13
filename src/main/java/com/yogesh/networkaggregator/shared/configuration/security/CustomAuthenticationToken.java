package com.yogesh.networkaggregator.shared.configuration.security;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CustomAuthenticationToken extends JwtAuthenticationToken {
    private final JwtTokenType tokenType;

    public CustomAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, JwtTokenType type) {
        super(jwt, authorities);
        this.tokenType = type;
    }

    public String getUserCognitoId() {
        return this.getToken()
                .getSubject();
    }

    public String getClientId() {
        return this.getToken()
                .getClaim("client_id");
    }

    public String getClaim(String claimName) {
        String claim = this.getToken().getClaimAsString(claimName);
        if (Objects.isNull(claim)) {
            throw new AuthenticationServiceException("%s not found".formatted(claimName));
        } else {
            return claim;
        }
    }

}
