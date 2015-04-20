package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Space;

import java.util.Collection;
import java.util.List;

public interface SpaceDao {
    Space findById (long id);
    List<Space> findByIds (Collection<Long> ids);
    long create(Space space);
    void update(Space space);
    void delete(Space space);
}
