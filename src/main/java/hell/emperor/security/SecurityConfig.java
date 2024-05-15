package hell.emperor.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable for simplicity, consider enabling for production
                .authorizeRequests()
                .antMatchers("/api/customers/createOrUpdateCustomer").permitAll()// Allow anyone to create a customer
                .antMatchers("/api/customers/getAllCustomer").permitAll()
                .antMatchers("/api/customers/createOrUpdateCustomer/{phone}").permitAll()
                .antMatchers("/api/customers/name/{name}").permitAll()
                .antMatchers("/api/customers/{name}").permitAll()
                .antMatchers("/api/customers/{phone}").permitAll()
                .anyRequest().authenticated() // Require authentication for other requests
                .and()
                .httpBasic(); // Use HTTP Basic Authentication
    }
}
