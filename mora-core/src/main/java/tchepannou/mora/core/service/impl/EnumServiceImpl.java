package tchepannou.mora.core.service.impl;

import org.springframework.cache.annotation.Cacheable;
import tchepannou.mora.core.dao.EnumModelDao;
import tchepannou.mora.core.domain.EnumModel;

import java.util.List;

public abstract class EnumServiceImpl<T extends EnumModel> {
    protected abstract EnumModelDao<T> getDao ();

    //-- RoleService overrides  ---
    @Cacheable("Enum")
    public T findById(long id) {
        return getDao().findById(id);
    }

    @Cacheable ("Enum")
    public List<T> findAll() {
        return getDao().findAll();
    }
}
