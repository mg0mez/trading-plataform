package com.tradingplataform.security;

import java.security.Key;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.tradingplataform.models.SecurityUser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

/**
 * 
 * Clase para generar un token, comprobar si es válido, está expirado...
 * 
 * @author mgomezgarrote
 *
 */
@Component
public class JwtProvider {
	
	private final static Logger log = Logger.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret_key;
	
	@Value("${jwt.expiration}")
	private int expiration_time;
	
	/**
	 * 
	 * Método para convertir una String key a Java Key
	 * 
	 * @return Java Key instancia
	 */
	private Key getSigningKey(String secretKey) {
		  byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		  return Keys.hmacShaKeyFor(keyBytes);
		}
	
	public String createToken(Authentication authentication) {
		
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		String token = Jwts.builder().setSubject(securityUser.getUsername())
						.setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis() + expiration_time))
						.signWith(getSigningKey(secret_key))
						.compact();
						   
		return token;
	}
	
	public String getEmailFromToken(String token) {
		
		String email = Jwts.parserBuilder().setSigningKey(getSigningKey(secret_key)).build().parseClaimsJws(token).getBody().getSubject();
		return email;
	}
	
	public boolean isValidateToken(String token) {
		
		try {
			
			Jwts.parserBuilder().setSigningKey(getSigningKey(secret_key)).build().parseClaimsJws(token);
			return true;
			
		}catch(MalformedJwtException e){
			
			log.error("Malformed Jwt");
			
		}catch(UnsupportedJwtException e){
			
			log.error("Unsupported Jwt");
			
		}catch(ExpiredJwtException e){
			
			log.error("Expired Jwt");
			
		}catch(IllegalArgumentException e){
			
			log.error("Empty Jwt");
			
		}catch(SignatureException e) {
			
			log.error("Fail Jwt signature");
			
		}
		
		return false;
	
	}
}
