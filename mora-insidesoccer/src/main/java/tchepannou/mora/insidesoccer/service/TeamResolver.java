package tchepannou.mora.insidesoccer.service;

import java.util.Set;

public interface TeamResolver {
    Set<Long> getTeamIdsForUser(long userId);
}
