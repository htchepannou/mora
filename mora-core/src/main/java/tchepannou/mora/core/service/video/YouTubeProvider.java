package tchepannou.mora.core.service.video;

import tchepannou.mora.core.service.VideoProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeProvider implements VideoProvider {
    //-- Attributes
    private static final String[] VIDEO_URL_PATTERNS = new String[]{
            "youtu\\.be\\/([a-zA-Z0-9-]*)(\\?.*)?"
            ,"youtube(\\-nocookie)?\\.com\\/embed\\/([a-zA-Z0-9-]*)(\\?.*)?"
            ,"youtube(\\-nocookie)?\\.com\\/watch\\?v=([a-zA-Z0-9-]*)(\\&.*)?"
    };
    private static final int[] VIDEO_GROUP_INDEX = new int[]{1,2,2};
    private static final String EMBED_URL_FORMAT = "https://www.youtube.com/embed/%s";

    //-- VideoProvider overrides
    @Override
    public String getEmbedUrl(String videoId) {
        return String.format(EMBED_URL_FORMAT, videoId);
    }

    @Override
    public String getVideoId(String url) {
        if (url.contains("youtube.com/user/")){
            return null;
        } else {
            for (int i=0 ; i<VIDEO_URL_PATTERNS.length ; i++){
                String pattern = VIDEO_URL_PATTERNS[i];
                int group = VIDEO_GROUP_INDEX[i];

                Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
                Matcher matcher = compiledPattern.matcher(url);
                while (matcher.find()) {
                    return matcher.group(group);
                }
            }
        }
        return null;
    }
}
