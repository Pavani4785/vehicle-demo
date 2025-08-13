package com.subaru.cv.vehicleservice.configuration;

import com.subaru.cv.cafe.microservice.handler.CafeRestResponseExceptionHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Test configuration for controller unit tests ensuring that JWT authentication is not required.
 * Also loads the {@link CafeRestResponseExceptionHandler} into context for proper exception handling
 * by cafe framework.
 */
@TestConfiguration
@EnableWebSecurity
public class TestRestConfiguration {

  /**
   * Allows all requests to be processed without authentication.
   *
   * @param http The http
   * @return the security filter chain
   * @throws Exception The exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(
            request -> new CorsConfiguration().applyPermitDefaultValues()));
    return http.build();
  }

  /**
   * Loads {@link CafeRestResponseExceptionHandler} into context for proper exception handling by cafe framework.
   *
   * @return the {@link CafeRestResponseExceptionHandler}
   */
  @Bean
  public CafeRestResponseExceptionHandler cafeRestResponseExceptionHandler() {
    return new CafeRestResponseExceptionHandler();
  }
}
