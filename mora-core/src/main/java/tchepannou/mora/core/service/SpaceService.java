package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Space;

public interface SpaceService {
    Space findById (long id);
    Space create(Space space);
    Space update(Space space);
    Space delete(Space space);
}
