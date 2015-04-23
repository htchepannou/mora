package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Media;

import java.util.List;

public interface MediaDao {
    Media findById (long id);
    List<Media> findByOwnerByAttachmentType(long ownerId, long typeId);
}
