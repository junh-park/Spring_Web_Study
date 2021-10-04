package spittr.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ChannelSecurityConfigurer.RequiresChannelUrl;

import spittr.domain.data.SpitterRepository;
import spittr.domain.web.SpitterUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	SpitterRepository spitterRepo;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new SpitterUserService(spitterRepo));
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.and()
			.httpBasic()
				.realmName("Spittr")
			.and()
			.rememberMe()
				.tokenValiditySeconds(2000000)
				.key("spittrKey")
			.and()
			.authorizeRequests()
				.antMatchers("spitter/me").hasRole("SPITTER")
				.antMatchers(HttpMethod.POST,"/spittles").hasRole("SPITTER")
				.anyRequest().permitAll()
			.and()
			.requiresChannel()
				.antMatchers("/spitter/form").requiresSecure();
	}
	
}
