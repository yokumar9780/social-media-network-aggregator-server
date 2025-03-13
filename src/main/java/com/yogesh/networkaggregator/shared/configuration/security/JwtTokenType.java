package com.yogesh.networkaggregator.shared.configuration.security;

/**
 * Enumeration of JWT token types supported by the application.
 * This enum defines the different types of JSON Web Tokens that can be
 * issued and validated by the authentication system.
 */
public enum JwtTokenType {
    /**
     * Token type for authenticated user sessions.
     */
    USER,

    /**
     * Token type for authenticated client applications.
     */
    CLIENT
}
