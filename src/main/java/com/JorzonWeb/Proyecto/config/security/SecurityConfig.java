package com.JorzonWeb.Proyecto.config.security;

import com.JorzonWeb.Proyecto.servicio.UsuarioSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioSecurityService usuarioSecurityService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> {
                    authorize.requestMatchers(toH2Console()).permitAll();
                    authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/api/usuario/**")).permitAll();
                    authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/api/login/**")).permitAll();
                    authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/api/public/noticia/**")).permitAll();
                    authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/api/public/noticia/popular/**")).permitAll();
                    // TODO: Revisar como manejar la autenticacion para admin
                    authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/api/admin/**")).permitAll();
                    authorize.anyRequest().authenticated()
                            .and().authenticationProvider(authenticationProvider());
                }).httpBasic(withDefaults());
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers(toH2Console()).disable());
        http.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> this.usuarioSecurityService.findByCorreoForSecurity(username);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.userDetailsService());
        authenticationProvider.setPasswordEncoder(this.passwordEncoder());

        return authenticationProvider;
    }
}