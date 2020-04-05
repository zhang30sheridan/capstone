package ca.sheridancollege.zhang30.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		
		http.csrf().disable(); 
		http.headers().frameOptions().disable(); 
		
		http.authorizeRequests()
		.antMatchers("/user/**").hasRole("USER") 
		.antMatchers("/", "/js/**", "/css/**", "/images/**", "/**").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
			.loginPage("/login").permitAll()
		.and().logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout").permitAll();
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.inMemoryAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
				.withUser("test").password("1234").roles("USER")
				.and()
				.withUser("manager").password("password").roles("MANAGER"); 
	}
	

	

}
