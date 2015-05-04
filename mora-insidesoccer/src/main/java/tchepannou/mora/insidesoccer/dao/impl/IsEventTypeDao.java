package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.domain.EventType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IsEventTypeDao implements EventTypeDao{
    private static final List<EventType> ALL = Arrays.asList(
            new EventType(EventType.GAME, "game")
            ,new EventType(EventType.PRACTICE, "practice")
            ,new EventType(EventType.OTHER, "other")
    );

    @Override
    public List<EventType> findAll() {
        return ALL;
    }

    @Override
    public EventType findById(long id) {
        Optional<EventType> result = findAll().stream()
                .filter(f -> f.getId() == id)
                .findFirst();
        return result.isPresent() ? result.get() : null;
    }
}
