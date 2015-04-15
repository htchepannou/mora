package tchepannou.mora.rest.space.config;

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
    protected void configureAuthorization(HttpSecurity http){
        try {
            // @formatter:off
            http
                .authorizeRequests()
                    .antMatchers("/spaces/types/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/spaces/*").permitAll()
                    .anyRequest()
                        .authenticated()
                    .and()
                        .anonymous().disable()
            ;
            // @formatter:on
        } catch (Exception e){
            throw new IllegalStateException("Unable to configure authorizations", e);
        }
    }
}

