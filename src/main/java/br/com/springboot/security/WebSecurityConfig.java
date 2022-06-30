package br.com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private ImplementesUserDetailsService userDetailsService;
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http. authorizeRequests()
            .antMatchers(HttpMethod.GET,"/").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/dados-acesso").hasAnyRole("EDITOR")
            .antMatchers("/lista-usuarios").hasAnyRole("ADMIN").antMatchers("/usuarios").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.GET,"/cadastrarUsuarios").permitAll()
            .antMatchers(HttpMethod.POST,"/cadastrarUsuarios").permitAll()
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
        .rememberMe();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        /*.inMemoryAuthentication()
        .withUser("eduardo").password("$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm")
        .roles("EDITOR", "ADMIN")
            .and()
            .withUser("robsu").password("$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm")
            .roles("ADMIN","EDITOR")
        .and()
        .withUser("fernanda").password("$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm").roles("EDITOR");
        */
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
