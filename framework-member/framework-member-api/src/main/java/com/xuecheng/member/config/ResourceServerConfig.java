package com.xuecheng.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(null)
                .and()
                .authorizeRequests().antMatchers("/oauth/**",
                "/token/**",
                "/actuator/**","/v2/api-docs", "/swagger-resources/configuration/ui",
                "/swagger-resources","/swagger-resources/configuration/security",
                "/swagger-ui.html","/course/coursebase/**","/webjars/springfox-swagger-ui/**").permitAll().anyRequest().authenticated()
                .and()
                .httpBasic().disable();
    }

}
