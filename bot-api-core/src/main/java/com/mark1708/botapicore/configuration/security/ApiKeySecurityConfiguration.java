package com.mark1708.botapicore.configuration.security;

import javax.servlet.Filter;
import com.mark1708.botapicore.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Order(1)
@Configuration
@RequiredArgsConstructor
public class ApiKeySecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final BotService botService;

  @Bean
  AuthenticationProvider apiKeyAuthenticationProvider() {
    return new ApiKeyAuthenticationProvider(botService);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(apiKeyAuthenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable()
        .csrf().disable()
        .formLogin().disable()
        .logout().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(getFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests()
        .requestMatchers(getRequestMatchers())
        .authenticated();
  }

  private RequestMatcher getRequestMatchers() {
    return new OrRequestMatcher(new AntPathRequestMatcher("/api/v1/bot/**"));
  }

  private Filter getFilter() throws Exception {
    return new ApiKeyAuthenticationFilter(authenticationManager());
  }
}
