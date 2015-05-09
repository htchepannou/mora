package tchepannou.mora.rest.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import tchepannou.mora.core.config.CacheConfig;
import tchepannou.mora.core.config.ExtConfig;
import tchepannou.mora.core.config.ServiceConfig;
import tchepannou.mora.insidesoccer.config.IsConfig;
import tchepannou.mora.rest.core.security.AbstractRestSecurityConfig;

@SpringBootApplication
@EnableCaching
@EnableWebMvcSecurity
@Configuration
@Import ({ExtConfig.class, IsConfig.class, ServiceConfig.class, CacheConfig.class})
public class Application  extends AbstractRestSecurityConfig {
    //-- AbstractSecurityConfig overrides
    @Override
    protected void configureAuthorization(HttpSecurity http){
        try {
            // @formatter:off
            http
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/spaces/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/members/**").permitAll()
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

    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
