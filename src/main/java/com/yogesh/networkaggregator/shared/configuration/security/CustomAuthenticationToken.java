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

/**
 * Custom implementation of JwtAuthenticationToken that adds support for token
 * type
 * and convenient methods for accessing common JWT claims.
 * This class extends Spring Security's JwtAuthenticationToken to provide
 * additional
 * functionality specific to the application's authentication needs.
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CustomAuthenticationToken extends JwtAuthenticationToken {
    /**
     * The type of JWT token (USER or CLIENT).
     */
    private final JwtTokenType tokenType;

    /**
     * Constructs a new CustomAuthenticationToken.
     *
     * @param jwt         the JWT token
     * @param authorities the granted authorities
     * @param type        the type of the token (USER or CLIENT)
     */
    public CustomAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, JwtTokenType type) {
        super(jwt, authorities);
        this.tokenType = type;
    }

    /**
     * Retrieves the Cognito user ID from the JWT subject claim.
     *
     * @return the Cognito user ID
     */
    public String getUserCognitoId() {
        return this.getToken()
                .getSubject();
    }

    /**
     * Retrieves the client ID from the JWT claims.
     *
     * @return the client ID
     */
    public String getClientId() {
        return this.getToken()
                .getClaim("client_id");
    }

    /**
     * Retrieves a specific claim from the JWT token.
     *
     * @param claimName the name of the claim to retrieve
     * @return the value of the specified claim
     * @throws AuthenticationServiceException if the claim is not found
     */
    public String getClaim(String claimName) {
        String claim = this.getToken().getClaimAsString(claimName);
        if (Objects.isNull(claim)) {
            throw new AuthenticationServiceException("%s not found".formatted(claimName));
        } else {
            return claim;
        }
    }
}
