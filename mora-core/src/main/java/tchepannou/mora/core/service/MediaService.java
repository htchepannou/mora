package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Media;

import java.util.List;

public interface MediaService {
    List<Media> findByOwnerByAttachmentType(long ownerId, long typeId);
}
