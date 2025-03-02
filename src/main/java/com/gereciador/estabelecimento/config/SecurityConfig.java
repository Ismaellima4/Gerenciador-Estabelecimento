package com.gereciador.estabelecimento.config;

import com.gereciador.estabelecimento.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/categorias", "/forncedores", "/produtos").hasAnyRole(UserRole.ADMIN_ESTOQUE.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, "/categorias/{id}","/fornecedores/{id}", "/produtos/{id}").hasAnyRole(UserRole.ADMIN_ESTOQUE.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/categorias/{id}","/fornecedores/{id}", "/produtos/{id}").hasAnyRole(UserRole.ADMIN_ESTOQUE.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/pedidos", "/pagamentos", "/clientes").hasAnyRole(UserRole.ADMIN_CAIXA.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, "/pedidos/{id}","/pagamentos/{id}", "/clientes/{id}").hasAnyRole(UserRole.ADMIN_CAIXA.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/pedidos/{id}","/pagamentos/{id}", "/clientes/{id}").hasAnyRole(UserRole.ADMIN_CAIXA.name(), UserRole.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
