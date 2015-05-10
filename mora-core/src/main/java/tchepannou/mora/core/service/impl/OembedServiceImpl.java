package tchepannou.mora.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tchepannou.mora.core.service.VideoProvider;
import tchepannou.mora.core.service.OembedService;

import java.util.ArrayList;
import java.util.List;

public class OembedServiceImpl implements OembedService {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(PostServiceImpl.class);

    private List<VideoProvider> providers = new ArrayList<>();


    //-- Public
    public void register(VideoProvider provider){
        providers.add(provider);
    }

    //--  VideoProviderFactory overrides
    @Override
    public String getEmbedUrl(String url) {
        LOG.debug("getEmbedUrl({})", url);

        String result = null;
        for (VideoProvider provider : providers){
            String id = provider.getVideoId(url);
            if (id != null){
                result = provider.getEmbedUrl(id);
                if (result != null) {
                    break;
                }
            }
        }

        LOG.debug("{} -> {}", url, result);
        return result;
    }
}
