package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.SpaceType;

import java.util.List;

public interface SpaceTypeDao {
    SpaceType findById (long id);

    List<SpaceType> findAll ();
}
