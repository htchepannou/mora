package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Post;

import java.util.List;

public interface PostService {
    Post findById (long id);
    List<Long> findIdsPublishedForUser(long userId, int limit, int offset);
    Post create (Post post);
    Post update(Post post);
    Post delete(Post post);
}
