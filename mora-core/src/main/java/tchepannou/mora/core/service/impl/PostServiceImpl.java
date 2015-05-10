package tchepannou.mora.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.service.PostService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostDao postDao;


    //-- PostService overrides
    @Override
    @Cacheable("Post")
    public Post findById(long id) {
        LOG.debug("findById({})", id);

        return postDao.findById(id);
    }

    @Override
    public List<Post> findByIds(Collection<Long> ids) {
        LOG.debug("findByIds({})", ids);

        return postDao.findByIds(ids);
    }

    @Override
    public List<Long> findIdsPublishedForUser(long userId, int limit, int offset) {
        LOG.debug("findIdsPublishedForUser({})", userId, limit, offset);

        return postDao.findIdsPublishedForUser(userId, limit, offset);
    }

    @Override
    @Transactional
    @CachePut(value="Post", key="#post.id")
    public Post create(Post post) {
        LOG.debug("create({})", post);

        Date now = new Date ();
        post.setLastUpdate(now);
        post.setCreationDate(now);
        postDao.create(post);

        return post;
    }

    @Override
    @Transactional
    @CacheEvict(value="Post", key="#post.id")
    public Post update(Post post) {
        LOG.debug("update({})", post);

        Date now = new Date ();
        post.setLastUpdate(now);
        postDao.update(post);

        return post;
    }

    @Override
    @Transactional
    @CacheEvict(value="Post", key="#post.id")
    public Post delete(Post post) {
        LOG.debug("delete({})", post);

        postDao.delete(post);
        return post;
    }
}
