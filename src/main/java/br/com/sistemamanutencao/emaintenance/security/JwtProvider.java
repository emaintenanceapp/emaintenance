package br.com.sistemamanutencao.emaintenance.security;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.sistemamanutencao.emaintenance.cache.LoggedOutJwtTokenCache;
import br.com.sistemamanutencao.emaintenance.event.OnUserLogoutSuccessEvent;
import br.com.sistemamanutencao.emaintenance.exception.InvalidTokenRequestException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.service.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
 
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    
    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;
    
    @Autowired
    private LoggedOutJwtTokenCache loggedOutJwtTokenCache;
 
    public String generateJwtToken(Authentication authentication) {
 
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);
        String authoritie = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        
        return Jwts.builder()
                    .setSubject((userPrincipal.getUsername()))
                    .setIssuer("emaintenance")
                    .setId(Long.toString(userPrincipal.getId()))
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .claim("role", authoritie)
                    .signWith(SignatureAlgorithm.HS512, "emaintenance")
                    .compact();
    }
    
    public String generateTokenFromUser(User user) {
        Instant expiryDate = Instant.now().plusMillis(3600000);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer("emaintenance")
                .setId(Long.toString(user.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, "emaintenance")
                .compact();
    }
 
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                      .setSigningKey("emaintenance")
                      .parseClaimsJws(token)
                      .getBody().getSubject();
    }
    
    public Date getTokenExpiryFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("emaintenance")
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }
 
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey("emaintenance").parseClaimsJws(authToken);
            validateTokenIsNotForALoggedOutDevice(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {MalformedJwtException}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {ExpiredJwtException}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {UnsupportedJwtException}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {IllegalArgumentException}", e);
        }
        
        return false;
    }
    
    private void validateTokenIsNotForALoggedOutDevice(String authToken) {
        OnUserLogoutSuccessEvent previouslyLoggedOutEvent = loggedOutJwtTokenCache.getLogoutEventForToken(authToken);
        if (previouslyLoggedOutEvent != null) {
            String userEmail = previouslyLoggedOutEvent.getUserEmail();
            Date logoutEventDate = previouslyLoggedOutEvent.getEventTime();
            String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", userEmail, logoutEventDate);
            throw new InvalidTokenRequestException("JWT", authToken, errorMessage);
        }
    }
    
    public long getExpiryDuration() {
        return 3600000;
    }
    
    public Authentication getAuthentication(HttpServletRequest request)
    {
        String token = resolveToken(request);
        if (token == null)
        {
            return null;
        }
        Claims claim = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String username = claim.getSubject();
        System.out.println(username);
        List<GrantedAuthority> authorities = Arrays.stream(claim.get("roles").toString().split(","))
            .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return username != null
                        ? new UsernamePasswordAuthenticationToken(username, null, authorities)
                        : null;
    }
    
    private String resolveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
