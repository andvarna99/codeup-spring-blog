package com.codeup.codeupspringblog;

import com.codeup.codeupspringblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsLoader usersLoader; //injected from UserDetailsLoader

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    //when spring wants a way to hash the password it calls this function
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //spring stuff
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //this is where we make the access control policy; for this particular thing and this is where we will configure it
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true) // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page

                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/") // append a query string value

                /* Pages that require authentication */
                .and()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/posts/create", // only authenticated users can create posts
                        "/posts/edit/{id}" // only authenticated users can edit posts
                )
                .authenticated()

                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/", "/posts", "/posts/{id}", "/sign-up", "/css/**", "/js/**", "/img/**") // anyone can see home, the posts pages, sign up, and css and js files
                .permitAll()
        ;
        return http.build();
    }

}

