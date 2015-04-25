package tchepannou.mora.core.service.video;

import tchepannou.mora.core.service.VideoProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DailymotionProvider implements VideoProvider {
    //-- Attributes
    private static final String VIDEO_URL_PATTERN = "dailymotion.*/video/([a-zA-Z0-9-]*)";
    private static final String EMBED_URL_FORMAT = "https://www.dailymotion.com/embed/video/%s";

    //-- VideoProvider overrides
    @Override
    public String getEmbedUrl(String videoId) {
        return String.format(EMBED_URL_FORMAT, videoId);
    }

    @Override
    public String getVideoId(String url) {
        Pattern compiledPattern = Pattern.compile(VIDEO_URL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        while(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
