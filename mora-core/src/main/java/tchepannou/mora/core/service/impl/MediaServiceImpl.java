package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.service.MediaService;

import java.util.List;

public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaDao mediaDao;

    @Override
    public List<Media> findByOwnerByAttachmentType(long ownerId, long typeId) {
        return mediaDao.findByOwnerByAttachmentType(ownerId, typeId);
    }
}
