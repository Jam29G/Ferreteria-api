package com.dev.ferreteriaapi.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
		/*
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/productos").permitAll()
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSourced()); */

        /*http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/productos").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/usuarios/user").permitAll(); */

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/productos/image/{filename}").permitAll().anyRequest().authenticated()
                .and().cors().configurationSource(corsConfigurationSourced());

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSourced() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTION"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        //config.setAllowedHeaders(Arrays.asList("Content-Type", "application/x-www-form-urlencoded"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSourced()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
