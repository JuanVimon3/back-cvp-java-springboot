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
            )); // Permitir localhost para desarrollo y tu dominio de Vercel en producción
            
            // Asegúrate de que esta URL coincida exactamente con la de tu Vercel (sin la barra / al final)
            corsConfiguration.setAllowedOrigins(java.util.List.of("https://compra-venta-propiedades.vercel.app"));
            corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
            corsConfiguration.setAllowCredentials(true); // Opcional: permite el envío de cookies/auth headers
            return corsConfiguration;
        }))

         .csrf(csrf -> csrf.disable())

         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

               .requestMatchers("/api/usuarios/login", "/api/usuarios").permitAll()
                
                // 2. HACER PÚBLICO EL CATÁLOGO: Permitir GET a las propiedades sin token
                .requestMatchers(HttpMethod.GET, "/api/propiedades/**").permitAll()
                
                // 3. Rutas protegidas: Crear, editar o borrar propiedades SÍ requiere token
                .requestMatchers(HttpMethod.POST, "/api/propiedades/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/propiedades/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/propiedades/**").authenticated()

                .anyRequest().authenticated()
            )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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
