package tchepannou.mora.rest.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import tchepannou.mora.core.config.SecurityServicesConfig;
import tchepannou.mora.rest.core.security.AbstractRestSecurityConfig;

@Configuration
@EnableWebMvcSecurity
@Import (SecurityServicesConfig.class)
public class SecurityConfig extends AbstractRestSecurityConfig {
    //-- AbstractSecurityConfig overrides
    @Override
    protected void configureAuthorization(HttpSecurity http) {
        try{
            // @formatter:off
            http.authorizeRequests()
                .antMatchers("/roles/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/access_token/**").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .anonymous().disable()
            ;
            // @formatter:om
        } catch (Exception e){
            throw new IllegalStateException("Unable to configure authorizations", e);
        }
    }

}
