package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.service.PostService;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    //-- Attributes
    @Autowired
    private PostDao postDao;


    //-- PostService overrides
    @Override
    @Cacheable("Post")
    public Post findById(long id) {
        return postDao.findById(id);
    }

    @Override
    public List<Long> findIdsPublishedForUser(long userId, int limit, int offset) {
        return postDao.findIdsPublishedForUser(userId, limit, offset);
    }

    @Override
    @Cacheable("Post")
    public Post create(Post post) {
        Date now = new Date ();
        post.setLastUpdate(now);
        post.setCreationDate(now);
        postDao.create(post);

        return post;
    }

    @Override
    @CacheEvict("Post")
    public Post update(Post post) {
        Date now = new Date ();
        post.setLastUpdate(now);
        postDao.update(post);

        return post;
    }

    @Override
    @CacheEvict("Post")
    public Post delete(Post post) {
        postDao.delete(post);
        return post;
    }
}
