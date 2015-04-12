package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.SpaceType;

import java.util.List;

public interface SpaceTypeService {
    SpaceType findById (long id);

    List<SpaceType> findAll ();
}
