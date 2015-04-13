package tchepannou.mora.rest.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.service.SpaceTypeService;
import tchepannou.mora.core.service.impl.SpaceTypeServiceImpl;
import tchepannou.mora.swagger.config.SwaggerConfig;

@Configuration
@SpringBootApplication
@Import ({DaoConfig.class, SwaggerConfig.class})
public class Application {
    //-- Beans
    @Bean
    public SpaceTypeService spaceTypeService () {
        return new SpaceTypeServiceImpl();
    }

    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
