package com.example.Mirco.services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Mirco.services.service.UserServices;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	
	private final UserServices userDetailsServices;
	private final BCryptPasswordEncoder bCryptPassword;
	
	public WebSecurity(UserServices userDetailsServices, BCryptPasswordEncoder bCryptPassword) {
		this.userDetailsServices = userDetailsServices;
		this.bCryptPassword = bCryptPassword;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.cors()
		.and().csrf().disable();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/**").permitAll()
		.anyRequest().authenticated().and()
		.addFilter(getAuthticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	protected AuthenticationFilter getAuthticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		//filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService( userDetailsServices).passwordEncoder(bCryptPassword);
	}
	
	@Override
	public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web)
			throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
		
	}
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
