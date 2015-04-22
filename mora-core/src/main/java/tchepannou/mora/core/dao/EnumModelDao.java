package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.EnumModel;

import java.util.List;

public interface EnumModelDao<T extends EnumModel> {
    T findById(long id);
    List<T> findAll();
}
