package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.EnumModelDao;
import tchepannou.mora.core.dao.MediaTypeDao;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.core.service.MediaTypeService;

@Service
public class MediaTypeServiceImpl extends EnumServiceImpl<MediaType> implements MediaTypeService {
    @Autowired
    private MediaTypeDao mediaTypeDao;

    @Override
    protected EnumModelDao<MediaType> getDao() {
        return mediaTypeDao;
    }
}
