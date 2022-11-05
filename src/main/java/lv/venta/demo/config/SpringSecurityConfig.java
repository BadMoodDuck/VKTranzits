package lv.venta.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lv.venta.demo.services.impl.MyUserDetailsService;

import org.springframework.security.core.userdetails.UserDetailsService;

@Configurable
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return new MyUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/home").hasAnyAuthority("ROLE_IMPLEMENTER","ROLE_EMPLOYEE","ROLE_ADMIN")
		.antMatchers("/course").hasAnyAuthority("ROLE_IMPLEMENTER","ROLE_EMPLOYEE","ROLE_ADMIN")
		.antMatchers("/course/**").hasAnyAuthority("ROLE_IMPLEMENTER","ROLE_EMPLOYEE","ROLE_ADMIN")
		.antMatchers("/employee").hasAuthority("ROLE_ADMIN")
		.antMatchers("/employee/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/company").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/company/**").hasAnyAuthority("ROLE_ADMIN")
		
		.antMatchers("/employees").hasAuthority("ROLE_ADMIN")
		.antMatchers("/employees/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/courses").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/courses/**").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/companies/").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/companies/**").hasAnyAuthority("ROLE_ADMIN")
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
