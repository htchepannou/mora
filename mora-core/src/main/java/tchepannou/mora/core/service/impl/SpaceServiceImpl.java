package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.service.SpaceService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class SpaceServiceImpl implements SpaceService {
    //-- Attributes
    @Autowired
    private SpaceDao spaceDao;

    //-- SpaceService overrides
    @Override
    @Cacheable ("Space")
    public Space findById(long id) {
        return spaceDao.findById(id);
    }

    @Override
    public List<Space> findByIds (Collection<Long> ids){
        return spaceDao.findByIds(ids);
    }

    @Override
    @Transactional
    @CachePut (value="Space", key="#space.id")
    public Space create(Space space) {
        Date now = new Date();
        space.setCreationDate(now);
        space.setLastUpdate(now);

        long id = spaceDao.create(space);
        space.setId(id);
        return space;
    }

    @Override
    @Transactional
    @CacheEvict (value="Space", key="#space.id")
    public Space update(Space space) {
        Date now = new Date();
        space.setLastUpdate(now);
        spaceDao.update(space);
        return space;
    }

    @Override
    @Transactional
    @CacheEvict (value="Space", key="#space.id")
    public Space delete(Space space) {
        spaceDao.delete(space);
        return space;
    }
}
