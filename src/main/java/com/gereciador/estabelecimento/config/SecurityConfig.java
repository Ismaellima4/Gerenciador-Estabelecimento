package com.gereciador.estabelecimento.config;

import com.gereciador.estabelecimento.enums.UserRole;
import com.gereciador.estabelecimento.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final String[] ESTOQUE_BASE_PATHS =  {
            "/categorias",
            "/fornecedores",
            "/produtos"
    };

    private static final String[] CAIXA_BASE_PATHS = {
            "/pedidos",
            "/pagamentos",
            "/clientes"
    };

    private static final String ID_PATHS = "/{id}";

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
                        //  Configuração para o Swagger/OpenAPI
                                .requestMatchers(
                                    "/swagger-ui.html",
                                            "/swagger-ui/**",
                                            "/v3/api-docs/**",
                                            "/swagger-resources/**",
                                             "/webjars/**"
                                ).permitAll()

                        // Configuração para Estoque
                        .requestMatchers(HttpMethod.POST, ESTOQUE_BASE_PATHS).hasAnyAuthority(ROLES_ESTOQUE)
                        .requestMatchers(HttpMethod.PATCH, addIdToPaths(ESTOQUE_BASE_PATHS)).hasAnyAuthority(ROLES_ESTOQUE)
                        .requestMatchers(HttpMethod.DELETE, addIdToPaths(ESTOQUE_BASE_PATHS)).hasAnyAuthority(ROLES_ESTOQUE)

                        // Configuração para Caixa
                        .requestMatchers(HttpMethod.POST, CAIXA_BASE_PATHS).hasAnyAuthority(ROLES_CAIXA)
                        .requestMatchers(HttpMethod.PATCH, addIdToPaths(CAIXA_BASE_PATHS)).hasAnyAuthority(ROLES_CAIXA)
                        .requestMatchers(HttpMethod.DELETE, addIdToPaths(CAIXA_BASE_PATHS)).hasAnyAuthority(ROLES_CAIXA)
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userService);
        provider.setPasswordEncoder(this.passwordEncoder);
        return authenticationConfiguration.getAuthenticationManager();
    }


    private String[] addIdToPaths(String[] basePaths){
        return Arrays.stream(basePaths)
                .map(path -> path.concat(ID_PATHS))
                .toArray(String[]::new);
    }
}
