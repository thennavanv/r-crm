package com.ridsys.rib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ridsys.rib.security.jwt.AuthEntryPointJwt;
import com.ridsys.rib.security.jwt.AuthTokenFilter;
import com.ridsys.rib.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/auth/**").permitAll().antMatchers("/api/v1/quotaplanmanage/active").permitAll()
				.antMatchers("/api/v1/user/create").permitAll().antMatchers("/api/v1/user/forgotpassword").permitAll()
				.antMatchers("/api/v1/plan/userPlanlist").permitAll().antMatchers("/api/v1/onlinepayment/request")
				.permitAll().antMatchers("/api/v1/plan/subplanByPlanPrice").permitAll()
				.antMatchers("/api/v1/plan/planDetailsByPlanid").permitAll()
				.antMatchers("/api/v1/onlinepayment/plan/onlinePayment/createdOrder").permitAll()
				.antMatchers("/api/v1/iptvplan/iptvplanActivation").permitAll().antMatchers("/api/v1/ottplan/**")
				.permitAll().antMatchers("/api/v1/onlinepayment/eazepayment/success").permitAll()
				.antMatchers("/api/v1/onlinepayment/eazepayment/failed").permitAll().antMatchers("/api/v1/plan/ottplan")
				.permitAll().antMatchers("/api/v1/paymentgateway/getActiveGateway").permitAll()
				.antMatchers("/api/v1/user/list").permitAll().anyRequest()

				.authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}