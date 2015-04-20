package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Post;

import java.util.Collection;
import java.util.List;

public interface PostDao {
    Post findById (long id);
    List<Post> findByIds (Collection<Long> ids);
    List<Long> findIdsPublishedForUser(long userId, int limit, int offset);
    long create (Post post);
    void update(Post post);
    void delete(Post post);
}
