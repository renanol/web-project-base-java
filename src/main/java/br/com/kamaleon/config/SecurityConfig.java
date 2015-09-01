package br.com.kamaleon.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/assets/js/**")
                .antMatchers("/assets/css/**")
                .antMatchers("/assets/avatars/**")
                .antMatchers("/assets/font/**")
                .antMatchers("/assets/img/**")
                .antMatchers("/assets/images/**")                
                //.antMatchers("/files/**")
                .and()
            .debug(false);
    }
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new CustomAuthenticationManager();
    }
      
   /* @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
            .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,true from users where username = ?")
                //.authoritiesByUsernameQuery("select username,authority from authorities where username = ?")
                .and()
            .eraseCredentials(false);
        
        auth
        .inMemoryAuthentication()
            .withUser("user").password("password").roles("ADMIN");
    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/ws/**",  "/login", "logout", "/assets/js/**", "/assets/css/**", "/assets/avatars/**", "/assets/font/**", "/assets/img/**", "/assets/images/**")
                    .permitAll()
            .anyRequest()
                .authenticated();
        http
            .csrf().disable()
            .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/?login_error=true")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                    .permitAll()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
            .rememberMe();
    }

}
