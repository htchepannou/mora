package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Space;

public interface SpaceService {
    Space findById (long id);
    void create(Space space);
    void update(Space space);
    void delete(Space space);
}
