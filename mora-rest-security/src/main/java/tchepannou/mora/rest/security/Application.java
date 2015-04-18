package tchepannou.mora.rest.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tchepannou.mora.rest.security.config.Config;

@Configuration
@SpringBootApplication
@EnableCaching
@Import ({Config.class})
public class Application {
    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
