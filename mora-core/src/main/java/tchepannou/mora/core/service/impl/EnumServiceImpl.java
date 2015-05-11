package tchepannou.mora.core.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import tchepannou.mora.core.dao.EnumModelDao;
import tchepannou.mora.core.domain.EnumModel;

import java.util.List;

public abstract class EnumServiceImpl<T extends EnumModel> {
    protected abstract EnumModelDao<T> getDao ();

    @Cacheable("Enum")
    public T findById(long id) {
        LoggerFactory.getLogger(getClass()).debug("findById({})", id);
        return getDao().findById(id);
    }

    @Cacheable ("Enum")
    public List<T> findAll() {
        LoggerFactory.getLogger(getClass()).debug("findByAll()");
        return getDao().findAll();
    }
}
