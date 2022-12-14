package com.codecool.dadsinventory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*",
                        "/webjars/jquery/3.0.0/*", "/webjars/bootstrap/4.3.1/*/*")
                .permitAll()
                .antMatchers("/search", "/item/details/*").hasRole(ApplicationUserRole.DAD.name())
                .antMatchers("/home/privacy").hasRole(ApplicationUserRole.MOM.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dadUser = User.builder()
                .username("dad")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.DAD.name())
                .build();

        UserDetails momUser = User.builder()
                .username("mom")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.MOM.name())
                .build();
        return new InMemoryUserDetailsManager(dadUser, momUser);
    }
}

