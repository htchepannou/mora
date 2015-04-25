package tchepannou.mora.core.service;

public interface VideoProvider {
    String getVideoId (String url);
    String getEmbedUrl (String videoId);
}