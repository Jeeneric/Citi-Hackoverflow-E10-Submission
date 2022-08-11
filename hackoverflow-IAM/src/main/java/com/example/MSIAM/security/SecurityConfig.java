package com.example.MSIAM.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.MSIAM.jwt.JwtRequestFilter;

@EnableWebSecurity
@Configuration
@ConfigurationProperties(prefix = "server.servlet")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserDetailsService userSvc) {
        this.userDetailsService = userSvc;
    }

    /**
     * Attach the user details and password encoder.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/account").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().permitAll()// all request will not need authentication
                .and().csrf().disable() // CSRF protection is needed only for browser based attacks
                .formLogin().disable() // Disables default login page
                .headers().disable() // Disable the security headers, as we do not return HTML in our service

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Spring security dont
                                                                                             // create sessions
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * @Bean annotation is used to declare a PasswordEncoder bean in the Spring
     *       application context. Any calls to encoder() will then be intercepted to
     *       return the bean instance.
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        // auto-generate a random salt internally
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
