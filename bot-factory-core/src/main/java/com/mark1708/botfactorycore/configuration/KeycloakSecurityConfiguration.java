package com.mark1708.botfactorycore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Order(2)
@Configuration
public class KeycloakSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("super-keycloak")
        .password("x2ONnSWmncAldBR")
        .roles("KEYCLOAK");
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.
        httpBasic()
        .and()
        .authorizeRequests()
        .requestMatchers(getRequestMatchers())
        .authenticated();
  }

  private RequestMatcher getRequestMatchers() {
    return new OrRequestMatcher(
        new AntPathRequestMatcher("/api/v1/keycloak/**")
    );
  }

  @Bean
  public PasswordEncoder getPasswordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }
}
