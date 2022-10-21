package pilab.com.takeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private static final String[] PUBLIC_MATCHERS={"/user/login","/user/register","/user/resetPassword/**","/image/**"};
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(bcrypt);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        JwtAuthentication jwtAuthentication = new JwtAuthentication(authenticationManager());
		jwtAuthentication.setFilterProcessesUrl(PUBLIC_MATCHERS[0]);
        http.csrf().disable().cors().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(jwtAuthentication)
        .addFilterBefore(new JwtAuthorization(),UsernamePasswordAuthenticationFilter.class);
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.csrf()
    //             .disable()
    //             .authorizeRequests()
    //             .antMatchers(HttpMethod.DELETE)
    //             .hasRole("ADMIN")
    //             .antMatchers("/admin/**")
    //             .hasAnyRole("ADMIN")
    //             .antMatchers("/user/**")
    //             .hasAnyRole("USER", "ADMIN")
    //             .antMatchers("/login/**")
    //             .anonymous()
    //             .anyRequest()
    //             .authenticated()
    //             .and()
    //             .httpBasic()
    //             .and()
    //             .sessionManagement()
    //             .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    //     return http.build();
    // }
}
