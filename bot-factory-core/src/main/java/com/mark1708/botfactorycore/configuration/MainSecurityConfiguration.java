package com.mark1708.botfactorycore.configuration;

import feign.RequestInterceptor;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Order(1)
@Configuration
public class MainSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .requestMatchers(getRequestMatchers()).authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors().configurationSource(corsConfigurationSource())
        .and()
        .csrf().disable()
        .oauth2ResourceServer().jwt();
  }
  private CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(
        "https://mark1708.ru", "https://mark1708.ru/*",
        "http://localhost:3000", "http://localhost:3000/*"
    ));
    configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(Collections.singletonList("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  private RequestMatcher getRequestMatchers() {
    return new OrRequestMatcher(
        new AntPathRequestMatcher("/api/v1/factory/**"),
        new AntPathRequestMatcher("/api/v1/admin/**")
    );
  }

  @Bean
  public RequestInterceptor requestTokenBearerInterceptor() {
    return requestTemplate -> {
      JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext()
          .getAuthentication();

      requestTemplate.header("Authorization", "Bearer " + token.getToken().getTokenValue());
    };
  }
}
