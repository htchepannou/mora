package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.service.SpaceTypeService;

import java.util.List;

@Service
public class SpaceTypeServiceImpl implements SpaceTypeService {
    @Autowired
    private SpaceTypeDao spaceTypeDao;

    //-- SpaceTypeService overrides  ---
    @Override
    @Cacheable("SpaceType")
    public SpaceType findById(long id) {
        return spaceTypeDao.findById(id);
    }

    @Override
    @Cacheable("SpaceType")
    public List<SpaceType> findAll() {
        return spaceTypeDao.findAll();
    }


    //-- Setters
    @Required
    public void setSpaceTypeDao(SpaceTypeDao spaceTypeDao) {
        this.spaceTypeDao = spaceTypeDao;
    }
}
