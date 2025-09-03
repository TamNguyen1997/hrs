package hrs.parcel.management.api.configuration;

import org.springframework.context.annotation.Configuration;

// TODO: There should be security configuration
// This authentication is using basic auth.
// I disable security due to limit time.
@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                    .anyRequest().authenticated()
//            )
//            .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
}