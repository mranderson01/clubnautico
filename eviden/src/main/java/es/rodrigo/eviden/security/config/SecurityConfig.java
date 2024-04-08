package es.rodrigo.eviden.security.config;

import es.rodrigo.eviden.security.Models.CustomAccessDeniedHandler;
import es.rodrigo.eviden.security.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                        //AUTH--
                        .requestMatchers("/auth/login","/auth/registration","/").permitAll()

                        //full
                        .requestMatchers("/home").hasAnyAuthority("ROLE_SOCIO")
                        .requestMatchers("/departure/**").hasAnyAuthority("ROLE_MANAGER")
                        .requestMatchers("/boat/**","/departure/**","/partner/**").hasAnyAuthority("ROLE_OWNER")
                        .requestMatchers("/users/**").hasAnyAuthority("ROLE_ADMIN")

                        //boat
                        .requestMatchers(
                                "/boat/**").hasAnyAuthority("ROLE_USER")
                        //Departue
                        .requestMatchers(
                                "/departue/**").hasAnyAuthority("ROLE_USER")
                        .anyRequest().anonymous()

                ) .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/").permitAll()
                ).logout(form -> form
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) // Invalidar la sesión HTTP
                        .deleteCookies("JSESSIONID") // Eliminar cookies, si las hay
                        .permitAll()
                ).userDetailsService(userService)
                .csrf(crf -> crf.disable());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };
};
