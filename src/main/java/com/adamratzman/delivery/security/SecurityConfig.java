package com.adamratzman.delivery.security;

import com.adamratzman.delivery.authentication.TacoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final TacoUserDetailsService userDetailsService;

  public SecurityConfig(TacoUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider())
    ;
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(encoder());

    return provider;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/me/**").authenticated()
            .antMatchers("/orders/**").authenticated()
            .antMatchers("/manage/**").hasRole("ADMIN")
            .antMatchers("/h2-console/**").permitAll()
            .and()
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .formLogin()
            .loginPage("/auth/login")
            .loginProcessingUrl("/auth/login")
            .usernameParameter("phoneNumber")
            .passwordParameter("password")
            .defaultSuccessUrl("/me/")
            .failureUrl("/auth/login?failure=true")
            .and()
            .logout()
            .logoutUrl("/auth/logout")
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/")
            .permitAll();

  }
}
