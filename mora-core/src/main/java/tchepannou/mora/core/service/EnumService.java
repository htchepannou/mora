package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.EnumModel;

import java.util.List;

public interface EnumService<T extends EnumModel> {
    T findById(long id);

    List<T> findAll();
}
