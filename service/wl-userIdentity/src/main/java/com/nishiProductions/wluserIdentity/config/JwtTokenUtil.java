package com.nishiProductions.wluserIdentity.config;

import com.nishiProductions.wluserIdentity.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    @Autowired
    private Environment env;

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 3600;
    public static final long REFRESH_TOKEN_VALIDITY = 604800;

    @Value("${jwt.secret}")
    private String secret;

    private String getSecret() {
        return this.secret = env.getProperty("jwt.secret");
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public boolean getIsRefresh(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return (boolean) claims.get("refresh", Object.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), "access");
    }

    public String generateToken(String userName, UserDto user) {

        Map<String, Object> claims = new HashMap<>();
//		claims.put("client_code", clientDto.getClientCode());
        claims.put("userId", user.getUserId());
//		claims.put("isTfaEnabled", user.());
//		claims.put("tfaDefaultType", user.getTfaDefaultType());
        return createToken(claims, userName, "access");
    }

    public String generateRefreshToken(UserDto userDetails, UserDto user) {

        Map<String, Object> claims = new HashMap<>();
//		claims.put("client_code", clientDto.getClientCode());
        claims.put("refresh", true);
        claims.put("user_id", user.getUserId());
//		claims.put("is_tfa_enabled", user.getIsTfaEnabled());
//		claims.put("tfaDefaultType", user.getTfaDefaultType());
        return createToken(claims, userDetails.getUserName(), "refresh");
    }

    private String createToken(Map<String, Object> claims, String subject, String tokenType) {
        log.info("JwtTokenUtil.doGenerateToken() invoked." + env.getProperty("jwt.secret"));
        Date tokenExp = null;

        if (tokenType.equals("access")) {
            tokenExp = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
        } else if (tokenType.equals("refresh")) {
            tokenExp = new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY * 1000);
        }

        String encodedString = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, encodedString).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
