package com.example.demo.config;

import com.example.demo.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    @Lazy
    private UserDetailsService UserDetailsService;

    @Autowired
    @Lazy
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable());
       // http.authorizeHttpRequests(request -> request
       //         .requestMatchers("register", "login","/web-scraper/**")
       //         .permitAll()
       //         .anyRequest().authenticated());
        http.authorizeHttpRequests(request -> request
                .anyRequest().permitAll()
        );
       // http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults())//this is for Postman
        .sessionManagement(sesion ->
                sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//
//    @Bean()
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("djole")
//                .password("djole")
//                .roles("USER")
//                .build();
//        UserDetails user2= User.withDefaultPasswordEncoder()
//                .username("ivana")
//                .password("ivana")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder encoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(UserDetailsService);
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
}
