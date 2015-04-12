package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Space;

public interface SpaceDao {
    Space findById (long id);
    long create(Space space);
    void update(Space space);
    void delete(Space space);
}
