package com.gereciador.estabelecimento.config.security;

import com.gereciador.estabelecimento.enums.UserRole;
import com.gereciador.estabelecimento.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(SecurityFilter securityFilter, UserService userService, PasswordEncoder passwordEncoder){
        this.securityFilter = securityFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private static final String[] ROLES_ESTOQUE = {
            UserRole.ADMIN_ESTOQUE.name(),
            UserRole.ADMIN.name()
    };

    private static final String[] ROLES_CAIXA = {
            UserRole.ADMIN_CAIXA.name(),
            UserRole.ADMIN.name()
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/auth/register",
                                "/auth/login",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST, "/categorias", "/fornecedores", "/produtos").hasAnyAuthority(ROLES_ESTOQUE)
                        .requestMatchers(HttpMethod.PATCH, "/categorias/{id}", "/fornecedores/{id}", "/produtos/{id}").hasAnyAuthority(ROLES_ESTOQUE)
                        .requestMatchers(HttpMethod.DELETE, "/categorias/{id}", "/fornecedores/{id}", "/produtos/{id}").hasAnyAuthority(ROLES_ESTOQUE)

                        .requestMatchers(HttpMethod.POST, "/pedidos", "/pagamentos", "/clientes").hasAnyAuthority(ROLES_CAIXA)
                        .requestMatchers(HttpMethod.PATCH, "/pedidos/{id}", "/pagamentos/{id}", "/clientes/{id}").hasAnyAuthority(ROLES_CAIXA)
                        .requestMatchers(HttpMethod.DELETE, "/pedidos/{id}", "/pagamentos/{id}", "/clientes/{id}").hasAnyAuthority(ROLES_CAIXA)

                        .anyRequest().authenticated()
                )
                .addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userService);
        authProvider.setPasswordEncoder(this.passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}