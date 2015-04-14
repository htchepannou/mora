package tchepannou.mora.rest.space.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import tchepannou.mora.core.config.SecurityServicesConfig;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.security.AuthenticationEntryPointImpl;
import tchepannou.mora.rest.core.security.TokenAuthenticationFilter;
import tchepannou.mora.rest.core.security.TokenAuthenticationProvider;

@Configuration
@EnableWebMvcSecurity
@Import (SecurityServicesConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    //-- Attributes
    @Autowired
    private UserService userService;

    @Autowired
    private AccessTokenService accessTokenService;


    //-- WebSecurityConfigurerAdapter overrides
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
            .and()
                .anonymous().disable()
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
        ;

        http.addFilterBefore(new TokenAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider());
    }


    //-- Beans
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider(accessTokenService, userService);
    }
}
