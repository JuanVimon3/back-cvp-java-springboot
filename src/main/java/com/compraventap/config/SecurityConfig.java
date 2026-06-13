package com.compraventap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors(cors -> cors.configurationSource(request -> {
            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
            corsConfiguration.setAllowedOrigins(java.util.List.of(
                "http://localhost:3000",
                "https://compra-venta-propiedades.vercel.app"
            )); 
            corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
            corsConfiguration.setAllowCredentials(true); 
            return corsConfiguration;
        }))
        .csrf(csrf -> csrf.disable())
        
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
        .authorizeHttpRequests(auth -> auth
            // Permite todas las peticiones OPTIONS (Pre-flight de CORS) para evitar bloqueos tempranos
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

            // 1. RUTAS TOTALMENTE PÚBLICAS (No requieren Token)
            .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/usuarios/login").permitAll()
            .requestMatchers("/error").permitAll()
            
            .requestMatchers(HttpMethod.GET, "/api/propiedades", "/api/propiedades/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/propiedad", "/api/propiedad/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/usuarios", "/api/usuarios/**").permitAll()

            // 2. RUTAS PROTEGIDAS (Exigen Token Bearer Válido - OPCIÓN A)
            .requestMatchers("/api/propiedades/**").authenticated() // Aplica a POST, PUT, DELETE
            
            // Unificamos el matcher de chats para que cubra cualquier método HTTP (GET, POST, etc.) bajo .authenticated()
            .requestMatchers("/api/chats", "/api/chats/**").authenticated() 
            
            // 3. REGLA DE CIERRE
            .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}