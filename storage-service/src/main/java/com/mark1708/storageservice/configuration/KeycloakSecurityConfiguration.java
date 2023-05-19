package com.mark1708.storageservice.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


//@Order(2)
@Configuration
public class KeycloakSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .requestMatchers(getRequestMatchers()).authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .and()
        .csrf()
        .disable()
        .oauth2ResourceServer()
        .jwt();
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
