package az.developia.comp_shop_mashallah_isgenderli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().requestMatchers(HttpMethod.POST, "/users/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/computers/**").permitAll()
				.requestMatchers(HttpMethod.DELETE, "/computers/**").permitAll()
				.requestMatchers(HttpMethod.PUT, "/computers/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/computers/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/files/**").permitAll().requestMatchers(HttpMethod.GET, "/files/**")
				.permitAll().requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().requestMatchers("/error")
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);

		jdbcUserDetailsManager
				.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
		jdbcUserDetailsManager
				.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");

		return jdbcUserDetailsManager;
	}

}
