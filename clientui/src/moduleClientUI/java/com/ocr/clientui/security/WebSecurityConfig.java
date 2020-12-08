package com.ocr.clientui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UtilisateurSecurityService userDetailsService;

    @Autowired
    public WebSecurityConfig(UtilisateurSecurityService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/Login")
                .defaultSuccessUrl("/EspacePerso")
                .failureUrl("/Connection")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().invalidateHttpSession(true)
                .logoutUrl("/Deconnection")
                .logoutSuccessUrl("/Accueil")
                .and()
                .csrf()
                .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/Connection");
    }



}

