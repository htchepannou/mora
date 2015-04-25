package tchepannou.mora.core.service.video;


import tchepannou.mora.core.service.VideoProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DefaultVideoProvider implements VideoProvider {
    //-- Abstract
    protected abstract String getEmbedUrlFormat();
    protected abstract String getVideoUrlPattern();

    //-- VideoProvider overrides
    @Override
    public String getEmbedUrl(String videoId) {
        return String.format(getEmbedUrlFormat(), videoId);
    }

    @Override
    public String getVideoId(String url) {
        Pattern compiledPattern = Pattern.compile(getVideoUrlPattern(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        while(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
