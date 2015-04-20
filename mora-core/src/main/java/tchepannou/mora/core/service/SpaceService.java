package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Space;

import java.util.Collection;
import java.util.List;

public interface SpaceService {
    Space findById (long id);
    List<Space> findByIds (Collection<Long> ids);
    Space create(Space space);
    Space update(Space space);
    Space delete(Space space);
}
