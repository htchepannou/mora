package tchepannou.mora.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tchepannou.mora.core.service.*;
import tchepannou.mora.core.service.impl.*;
import tchepannou.mora.core.service.video.DailymotionProvider;
import tchepannou.mora.core.service.video.VimeoProvider;
import tchepannou.mora.core.service.video.YouTubeProvider;
import tchepannou.mora.core.util.EnumKeyGenerator;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ServiceConfig {
    @Bean
    public UserService userService (){
        return new UserServiceImpl();
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

    @Bean
    public PostService postService (){
        return new PostServiceImpl();
    }

    @Bean
    public UrlFetchService urlFetchService (){
        return new UrlFetchServiceImpl();
    }

    @Bean
    public MediaService mediaService () {
        return new MediaServiceImpl();
    }

    @Bean
    public MediaTypeService mediaTypeService (){
        return new MediaTypeServiceImpl();
    }

    @Bean
    public OembedService oembedService(){
        OembedServiceImpl result = new OembedServiceImpl();
        for (VideoProvider provider : videoProviders()){
            result.register(provider);
        }
        return result;
    }

    public List<VideoProvider> videoProviders(){
        return Arrays.asList(new YouTubeProvider(), new DailymotionProvider(), new VimeoProvider());
    }

    @Bean
    public EventService eventService (){
        return new EventServiceImpl();
    }

    @Bean
    public EventTypeService eventTypeService () {
        return new EventTypeServiceImpl();
    }

    @Bean
    public EnumKeyGenerator enumKeyGenerator(){
        return new EnumKeyGenerator();
    }
}
