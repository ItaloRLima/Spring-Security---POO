package br.com.springboot;

import br.com.springboot.security.ImplementesUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService userDetailsService(){return new ImplementesUserDetailsService();}

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http. authorizeRequests()
            .antMatchers(HttpMethod.GET,"/").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/usuarios/add").permitAll()
            .antMatchers("/usuarios/create").permitAll()
            .antMatchers("/usuarios/edit").permitAll()
            .antMatchers("/usuarios/index").permitAll()
            .antMatchers("/add/**").permitAll()
            .antMatchers("/new").permitAll()
            .antMatchers("/edit/**").permitAll()
            .antMatchers("/delete/**").permitAll()
            .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .permitAll()
        .and()
        .rememberMe()
            .and()
            .exceptionHandling().accessDeniedPage("/403");
  }
  /*@Autowired
  public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

  }
  @Autowired
  private ImplementesUserDetailsService userDetailsService;*/
}
