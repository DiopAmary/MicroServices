package ma.enset.authservice.security;

public class SecurityConstants {
    public static final String SECRET = "dioppp___@secret";
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 600_000; // 10 minutes
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 120_000; // 2 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLE_PREFIX = "ROLE_";
}
