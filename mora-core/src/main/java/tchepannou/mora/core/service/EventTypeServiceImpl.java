package tchepannou.mora.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.EnumModelDao;
import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.service.impl.EnumServiceImpl;

@Service
public class EventTypeServiceImpl extends EnumServiceImpl<EventType> implements EventTypeService {
    @Autowired
    private EventTypeDao dao;

    @Override
    protected EnumModelDao<EventType> getDao() {
        return dao;
    }
}
