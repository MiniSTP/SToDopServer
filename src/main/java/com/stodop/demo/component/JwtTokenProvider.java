package com.stodop.demo.component;

import com.stodop.demo.dao.UserDao;
import com.stodop.demo.model.Users;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Autowired
    private UserDao userDao;
    public String genarateJwtToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String authToken) {
        try {
            String jwtToken = null;
            if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")) {
                jwtToken = authToken.substring(7);
            }
            String email= getEmail(jwtToken);
            List<Users> users= userDao.findByEmail(email);
//            if(users==null|| users.isEmpty()){
//                throw new MalformedJwtException("Email Not Found");
//            }
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
