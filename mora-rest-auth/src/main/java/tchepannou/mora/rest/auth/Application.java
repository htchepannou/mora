package tchepannou.mora.rest.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.HashService;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.core.service.impl.AccessTokenServiceImpl;
import tchepannou.mora.core.service.impl.Md5HashService;
import tchepannou.mora.core.service.impl.PasswordServiceImpl;
import tchepannou.mora.core.service.impl.UserServiceImpl;
import tchepannou.mora.swagger.config.SwaggerConfig;

@Configuration
@SpringBootApplication
@Import ({DaoConfig.class, SwaggerConfig.class})
public class Application {
    //-- Beans
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

    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
