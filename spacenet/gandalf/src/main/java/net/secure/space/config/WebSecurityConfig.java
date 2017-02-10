package net.secure.space.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by AjayMenon on 12/25/2016.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${gandolf.security.username.default:gandolf}")
	public String username;

	@Value("${gandolf.security.password.default:gandolf}")
	public String password;

	@Value("${gandolf.security.ldap.enabled:false}")
	public boolean isLdapEnabled;

	@Value("${gandolf.security.oauth.enabled:false}")
	public boolean isOauthEnabled;

	@Value("${gandolf.security.saml.enabled:false}")
	public boolean isSamlEnabled;

	@Value("${gandolf.security.in-memory.enabled:true}")
	public boolean isInMemoryDefaultEnabled;


	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeRequests()
				.antMatchers("/home").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(username).password(password).roles("USER");
	}
}
