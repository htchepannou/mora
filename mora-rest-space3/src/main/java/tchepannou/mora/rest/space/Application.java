package tchepannou.mora.rest.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.core.config.CacheConfig;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.config.SecurityServicesConfig;
import tchepannou.mora.core.service.MemberService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.SpaceTypeService;
import tchepannou.mora.core.service.impl.MemberServiceImpl;
import tchepannou.mora.core.service.impl.SpaceServiceImpl;
import tchepannou.mora.core.service.impl.SpaceTypeServiceImpl;
import tchepannou.mora.swagger.config.SwaggerConfig;

@Configuration
@SpringBootApplication
@EnableCaching
@Import ({DaoConfig.class, SwaggerConfig.class, SecurityServicesConfig.class, CacheConfig.class})
public class Application {
    //-- Beans
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


    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
