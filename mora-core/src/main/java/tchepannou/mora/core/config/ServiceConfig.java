package tchepannou.mora.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.HashService;
import tchepannou.mora.core.service.MemberService;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.RoleService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.SpaceTypeService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.core.service.impl.AccessTokenServiceImpl;
import tchepannou.mora.core.service.impl.Md5HashService;
import tchepannou.mora.core.service.impl.MemberServiceImpl;
import tchepannou.mora.core.service.impl.PasswordServiceImpl;
import tchepannou.mora.core.service.impl.RoleServiceImpl;
import tchepannou.mora.core.service.impl.SpaceServiceImpl;
import tchepannou.mora.core.service.impl.SpaceTypeServiceImpl;
import tchepannou.mora.core.service.impl.UserServiceImpl;

@Configuration
public class ServiceConfig {
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

    @Bean
    public RoleService roleService () {
        return new RoleServiceImpl();
    }

    @Bean
    public SpaceTypeService spaceTypeService () {
        return new SpaceTypeServiceImpl();
    }

    @Bean
    public SpaceService spaceService () {
        return new SpaceServiceImpl();
    }

    @Bean
    public MemberService memberService () {
        return new MemberServiceImpl();
    }
}
