package tchepannou.mora.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.service.MediaService;

import java.util.List;

public class MediaServiceImpl implements MediaService {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private MediaDao mediaDao;

    //-- MediaService overrides
    @Override
    public List<Media> findByOwnerByAttachmentType(long ownerId, long typeId) {
        LOG.debug("findByOwnerByAttachmentType({},{})", ownerId, typeId);

        return mediaDao.findByOwnerByAttachmentType(ownerId, typeId);
    }
}
