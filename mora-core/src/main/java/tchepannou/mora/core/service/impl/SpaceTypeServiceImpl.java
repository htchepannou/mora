package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.EnumModelDao;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.service.SpaceTypeService;

@Service
public class SpaceTypeServiceImpl extends EnumServiceImpl<SpaceType> implements SpaceTypeService {
    @Autowired
    private SpaceTypeDao spaceTypeDao;

    //-- EnumServiceImpl overrides
    @Override
    protected EnumModelDao<SpaceType> getDao() {
        return spaceTypeDao;
    }
}
