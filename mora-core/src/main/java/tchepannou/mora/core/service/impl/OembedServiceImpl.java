package tchepannou.mora.core.service.impl;

import tchepannou.mora.core.service.VideoProvider;
import tchepannou.mora.core.service.OembedService;

import java.util.ArrayList;
import java.util.List;

public class OembedServiceImpl implements OembedService {
    private List<VideoProvider> providers = new ArrayList<>();


    //-- Public
    public void register(VideoProvider provider){
        providers.add(provider);
    }

    //--  VideoProviderFactory overrides
    @Override
    public String getEmbedUrl(String url) {
        for (VideoProvider provider : providers){
            String id = provider.getVideoId(url);
            if (id != null){
                return provider.getEmbedUrl(id);
            }
        }
        return null;
    }
}
