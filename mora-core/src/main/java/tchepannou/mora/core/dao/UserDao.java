package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.User;

import java.util.Collection;
import java.util.List;

public interface UserDao {
    User findById(long id);
    List<User> findByIds (Collection<Long> ids);
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
    long create(User user);
    void update(User user);
    void delete(User user);
}
