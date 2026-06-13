package com.compraventap.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets; // <-- Importante para la conversión de texto a bytes
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // 1. DEFINIMOS UN TEXTO FIJO (Mínimo de 256 bits / 32 caracteres para HS256)
    // RECOMENDACIÓN: En producción, este string debe venir de tu application.properties o variables de entorno.
    private static final String SECRET_STRING = "mi_clave_secreta_super_segura_y_larga_para_el_proyecto_CVP_2026";

    // 2. CONVERTIMOS ESE STRING EN UNA LLAVE SEGURA Y FIJA
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    // 1. EXTRAER EL USUARIO (Username/Email) DEL TOKEN
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 2. EXTRAER LA FECHA DE EXPIRACIÓN
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 3. VERIFICAR SI EL TOKEN YA EXPIRÓ
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 4. GENERAR EL TOKEN CUANDO EL USUARIO HACE LOGIN
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expira en 10 horas
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 5. VALIDAR SI EL TOKEN PERTENECE AL USUARIO Y ES VÁLIDO
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}