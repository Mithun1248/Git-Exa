package com.api.chat.security;

import com.api.chat.filters.JWTTokenGenerationFilter;
import com.api.chat.filters.JWTTokenValidatorFilter;
import com.api.chat.service.UserDetailsServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@Log4j2
public class UserConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(
                request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of(""));
                    configuration.setAllowedMethods(List.of("*"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setExposedHeaders(List.of("*"));
                    return configuration;
                }
        ));

        http.rememberMe(
                remember -> remember
                        .key("AnyKey")
                        .rememberMeParameter("remember_me")
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(10000)
                        .userDetailsService(userDetailsService())
        );

//        http.rememberMe(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/register").permitAll()
//                                .requestMatchers("/actuator/*").permitAll()
//                                .requestMatchers("/actuator").permitAll()
//                                .requestMatchers("/user").authenticated()
                                .anyRequest().authenticated()
                );
        http.addFilterAfter(new JWTTokenGenerationFilter(), BasicAuthenticationFilter.class);
        http.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class);
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        log.info(()-> "Using persistentTokenRepository");
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
