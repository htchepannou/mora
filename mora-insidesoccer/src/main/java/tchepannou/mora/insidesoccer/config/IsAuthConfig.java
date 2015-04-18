package tchepannou.mora.insidesoccer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.insidesoccer.service.impl.IsAccessTokenService;
import tchepannou.mora.insidesoccer.service.impl.IsPasswordService;

@Configuration
public class IsAuthConfig {
    @Bean
    public AccessTokenService accessTokenService () {
        return new IsAccessTokenService();
    }

    @Bean
    public PasswordService passwordService (){
        return new IsPasswordService();
    }

//    @Bean
//    public HashService hashService (){
//        return new Md5HashService();
//    }
}
