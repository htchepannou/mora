package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.domain.EnumModel;

import java.util.List;
import java.util.Optional;

public abstract class IsMemoryEnumDao<T extends EnumModel> {
    public abstract List<T> findAll();

    public T findById(long id) {
        Optional<T> result = findAll().stream()
                .filter(f -> f.getId() == id)
                .findFirst();
        return result.isPresent() ? result.get() : null;
    }
}
