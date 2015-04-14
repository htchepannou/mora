package tchepannou.mora.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.HashService;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.core.service.impl.AccessTokenServiceImpl;
import tchepannou.mora.core.service.impl.Md5HashService;
import tchepannou.mora.core.service.impl.PasswordServiceImpl;
import tchepannou.mora.core.service.impl.UserServiceImpl;

@Configuration
public class SecurityServicesConfig {
    @Bean
    public UserService userService (){
        return new UserServiceImpl();
    }

    @Bean
    public PasswordService passwordService (){
        return new PasswordServiceImpl();
    }

    @Bean
    public HashService hashService (){
        return new Md5HashService();
    }

    @Bean
    public AccessTokenService accessTokenService () {
        return new AccessTokenServiceImpl();
    }

}
