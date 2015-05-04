package tchepannou.mora.rest.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.UserService;

public abstract class AbstractRestSecurityConfig extends WebSecurityConfigurerAdapter{
    //-- Attributes
    private static final String[] ACTUATOR_ENDPOINTS = new String[]{
            "/autoconfig/**"
            ,"/beans/**"
            ,"/configprops/**"
            ,"/dump/**"
            ,"/env/**"
            ,"/health/**"
            ,"/info/**"
            ,"/metrics/**"
            ,"/mappings/**"
            ,"/trace/**"
    };

    @Autowired
    protected UserService userService;

    @Autowired
    protected AccessTokenService accessTokenService;


    //-- Abstract
    protected abstract void configureAuthorization(HttpSecurity http);

    //-- WebSecurityConfigurerAdapter overrides
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/api-docs/**").permitAll()
                    .antMatchers(getActuatorEndpoints()).permitAll().antMatchers("/docs/**").permitAll()
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint())
        ;

        configureAuthorization(http);

        http
                .addFilterBefore(new CorsFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
        ;
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

    protected String[] getActuatorEndpoints(){
        return ACTUATOR_ENDPOINTS;
    }
}
