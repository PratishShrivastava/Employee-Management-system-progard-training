package com.EmployeeManagementSystem.EmployeeManagementSystem.Config;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Security.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeDetails employeeDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/organization").hasAnyAuthority("admin","Employee","Hr")
                .antMatchers(HttpMethod.PUT,"/api/organization/**").hasAnyAuthority("admin","Hr")
                .antMatchers(HttpMethod.POST,"/api/organization/**").hasAnyAuthority("admin","Hr")
                .antMatchers(HttpMethod.DELETE,"/api/organization/**").hasAnyAuthority("admin")

//                .antMatchers(HttpMethod.GET,"/api/employee/login/**").hasAnyAuthority("Employee","admin","Hr")
                .antMatchers(HttpMethod.GET, "/api/employee/login/{id}").access("@userSecurity.hasUserId(authentication,#id)")
                .antMatchers(HttpMethod.GET,"/api/employee").hasAnyAuthority("admin","Hr")
                .antMatchers(HttpMethod.POST,"/api/employee/**").hasAnyAuthority("admin","Hr")
                .antMatchers(HttpMethod.PUT,"/api/employee/**").hasAnyAuthority("admin","Hr")
                .antMatchers(HttpMethod.DELETE,"/api/employee/**").hasAnyAuthority("admin")

                .antMatchers(HttpMethod.GET,"/api/assets").hasAnyAuthority("admin","Employee","Hr")
                .antMatchers(HttpMethod.PUT,"/api/assets/**").hasAnyAuthority("admin","Hr")
                .antMatchers(HttpMethod.POST,"/api/assets/**").hasAnyAuthority("admin","Hr")
                .antMatchers(HttpMethod.DELETE,"/api/assets/**").hasAnyAuthority("admin")
                .and().httpBasic();

        http.csrf().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(this.employeeDetails).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}


