package be.intecbrussel.blogteam2.ConfigSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authz -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/createPost/**", "/createComment/**", "/saveComment/**", "/blog/**", "/home", "/profile", "/editProfile").authenticated()
                        .requestMatchers("/", "/login", "/registration", "/register", "/post/**", "/webjars/**", "/css/**").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .successHandler((request, response, authentication) -> {
                            String targetUrl = determineTargetUrl(authentication);
                            response.sendRedirect(targetUrl);
                        })
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler((request, response, authentication) -> {
                            String targetUrl = determineTargetUrl(authentication);
                            response.sendRedirect(targetUrl);
                        })
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("ADMIN")
                .password("ADMIN")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("USER")
                .password("USER")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "/admin/adminHome";
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                return "/userHome";
            }
        }
        throw new IllegalStateException();
    }
}











/*
package be.intecbrussel.blogteam2.ConfigSecurity;

import be.intecbrussel.blogteam2.service.userService.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final UserServiceImpl userDetailsService;
//
////    @Autowired
////    public SecurityConfig(@Lazy UserServiceImpl userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authz -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/createPost/**", "/createComment/**", "/saveComment/**", "/blog/**", "/home").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/", "/login", "/registration", "/error", "/post/**", "/h2-console/**", "/webjars/**", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .successHandler((request, response, authentication) -> {
                            String targetUrl = determineTargetUrl(authentication);
                            response.sendRedirect(targetUrl);
                        })
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler((request, response, authentication) -> {
                            String targetUrl = determineTargetUrl(authentication);
                            response.sendRedirect(targetUrl);
                        })
                );
       */
/*         .csrf().disable() // Disable CSRF for simplicity, but consider security implications
                .sessionManagement(session -> session
                        .maximumSessions(1) // Ensure single session per user
                        .maxSessionsPreventsLogin(true)
                )
               // .userDetailsService(userDetailsService);*//*


        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    private String determineTargetUrl(Authentication authentication) {
        return "/userHome"; // Redirect to userHome for all users
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        // Encode passwords using BCryptPasswordEncoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserDetails admin = User.builder()
                .username("ADMIN")
                .password(passwordEncoder.encode("ADMIN")) // Encode the password
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("USER")
                .password(passwordEncoder.encode("USER")) // Encode the password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

}
*/
