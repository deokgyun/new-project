package my.adg.backend.global.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.authentication.infrastructure.CustomLogoutHandler;
import my.adg.backend.global.exception.FilterExceptionHandler;
import my.adg.backend.authentication.infrastructure.JwtFilter;
import my.adg.backend.authentication.infrastructure.JwtTokenProvider;
import my.adg.backend.authentication.infrastructure.LoginFilter;
import my.adg.backend.authentication.infrastructure.RedisUtil;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	//AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
	private final AuthenticationConfiguration authenticationConfiguration;
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisUtil redisUtil;
	private final FilterExceptionHandler filterExceptionHandler;

	private final CustomLogoutHandler customLogoutHandler;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
		throws Exception {

		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((auth) -> auth
			.requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**",
				"/api-docs ", "/api-docs/**", "/v3/api-docs/**").permitAll()

			.requestMatchers("/api/login", "/api/signup", "/login", "/api/reissue").permitAll()

			.anyRequest().authenticated());

		http.csrf(AbstractHttpConfigurer::disable);

		http.formLogin(AbstractHttpConfigurer::disable);

		http.httpBasic(AbstractHttpConfigurer::disable);

		http.addFilterBefore(new JwtFilter(jwtTokenProvider, redisUtil), LoginFilter.class);
		http.addFilterBefore(filterExceptionHandler, JwtFilter.class);
		http.addFilterBefore(filterExceptionHandler, LogoutFilter.class);

		http.addFilterAt(
			new LoginFilter(authenticationManager(authenticationConfiguration), jwtTokenProvider, redisUtil),
			UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(
			(session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

		http.logout(logout -> logout.logoutUrl("/logout")
			.addLogoutHandler(customLogoutHandler));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.setAllowedOrigins(List.of("http://localhost:3000"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
		config.setAllowedHeaders(List.of("*"));
		config.setExposedHeaders(List.of("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
