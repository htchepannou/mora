package tchepannou.mora.core.service.video;

import tchepannou.mora.core.service.VideoProvider;

public class DailymotionProvider extends DefaultVideoProvider implements VideoProvider {
    //-- Attributes
    private static final String VIDEO_URL_PATTERN = "dailymotion.*/video/([a-zA-Z0-9-]*)";
    private static final String EMBED_URL_FORMAT = "https://www.dailymotion.com/embed/video/%s";

    //-- DefaultVideoProvider overrides
    @Override
    protected String getEmbedUrlFormat(){
        return EMBED_URL_FORMAT;
    }

    @Override
    protected String getVideoUrlPattern(){
        return VIDEO_URL_PATTERN;
    }
}
