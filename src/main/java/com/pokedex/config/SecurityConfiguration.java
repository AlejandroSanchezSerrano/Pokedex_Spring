package com.pokedex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
           .authorizeHttpRequests(auth -> auth
               .requestMatchers("/pokemon/nuevo", "/pokemon/agregar").authenticated()
               .anyRequest().permitAll()
           )
           .formLogin(login -> login
               .loginPage("/login")
               .defaultSuccessUrl("/pokemon", true)
               .permitAll()
           )
           .logout(logout -> logout
               .logoutUrl("/logout")
               .logoutSuccessUrl("/logout-success")
           ).csrf(csrf -> csrf
           .ignoringRequestMatchers("/pokemon/agregar") // Desactivar CSRF solo en esta ruta.
       );

       return http.build();
   }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
         UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("ADMIN")
            .build();
         return new InMemoryUserDetailsManager(admin);
    }
}
