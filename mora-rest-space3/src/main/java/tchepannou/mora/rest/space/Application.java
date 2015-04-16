package tchepannou.mora.rest.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.core.config.CacheConfig;
import tchepannou.mora.core.config.DaoConfig;
import tchepannou.mora.core.config.EhCacheConfig;
import tchepannou.mora.core.config.ServiceConfig;
import tchepannou.mora.swagger.config.SwaggerConfig;

@Configuration
@SpringBootApplication
@EnableCaching
@Import ({DaoConfig.class, SwaggerConfig.class, ServiceConfig.class, EhCacheConfig.class, CacheConfig.class})
public class Application {
    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
