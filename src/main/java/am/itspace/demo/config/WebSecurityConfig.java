package am.itspace.demo.config;

import am.itspace.demo.filters.JwtRequestFilter;
import am.itspace.demo.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "users/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/categories").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/categories").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/categories/{id}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/categories/{id}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/listings").authenticated()
                .antMatchers(HttpMethod.GET, "/listings").permitAll()
                .antMatchers(HttpMethod.GET, "/listings/byUser/{email}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/listings/byCategory/{categoryId}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/listings/{id}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/listings/{id}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/listings/{id}").hasAuthority("ADMIN")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }

   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
   }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
