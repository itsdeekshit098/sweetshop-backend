package com.Incubyte.sweetshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        // ✅ Add all allowed frontend origins here
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "https://sweetshop-frontend-pink.vercel.app",
                "https://sweetshop-frontend-deekshiths-projects-eaba341b.vercel.app",
                "https://sweetshop-frontend-git-main-deekshiths-projects-eaba341b.vercel.app/",
                "https://sweetshop-frontend-f249kmot5-deekshiths-projects-eaba341b.vercel.app/"
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
