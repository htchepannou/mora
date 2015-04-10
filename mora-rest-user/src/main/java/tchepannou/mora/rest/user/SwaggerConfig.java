package tchepannou.mora.rest.user;

import com.wordnik.swagger.model.ApiInfo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.swagger.config.AbstractSwaggerConfig;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan (basePackages = "com.mangofactory.swagger")
public class SwaggerConfig extends AbstractSwaggerConfig{
    public static final List<String> DEFAULT_INCLUDE_PATTERNS = Arrays.asList("/users/.*");
    public static final String SWAGGER_GROUP = "users";

    //-- AbstractSwaggerConfig overrides
    protected ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("User API", "REST User API", "http://www.google.ca", "herve@tchepannou.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html");
        return apiInfo;
    }

    @Override
    protected List<String> getIncludePatterns() {
        return DEFAULT_INCLUDE_PATTERNS;
    }

    @Override
    protected String getSwaggerGroup() {
        return SWAGGER_GROUP;
    }
}
