package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Media;

import java.util.Collection;
import java.util.List;

public interface MediaDao {
    Media findById (long id);
    List<Media> findByIds (Collection<Long> ids);
}
