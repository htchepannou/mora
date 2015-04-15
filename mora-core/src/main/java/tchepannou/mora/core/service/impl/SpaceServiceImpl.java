package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.service.SpaceService;

import java.util.Date;

@Service
public class SpaceServiceImpl implements SpaceService {
    //-- Attributes
    @Autowired
    private SpaceDao spaceDao;

    //-- SpaceService overrides
    @Override
    public Space findById(long id) {
        return spaceDao.findById(id);
    }

    @Override
    public void create(Space space) {
        Date now = new Date();
        space.setCreationDate(now);
        space.setLastUpdate(now);

        long id = spaceDao.create(space);
        space.setId(id);
    }

    @Override
    public void update(Space space) {
        Date now = new Date();
        space.setLastUpdate(now);
        spaceDao.update(space);

    }

    @Override
    public void delete(Space space) {
        spaceDao.delete(space);
    }
}
