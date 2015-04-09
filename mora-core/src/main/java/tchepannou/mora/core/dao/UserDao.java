package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.User;

import java.util.List;

public interface UserDao {
    User findById(long id);
    List<User> findByUsername(String username, boolean deleted);
    List<User> findByEmail(String email, boolean deleted);
    void save(User user);
    void delete(User user);
}
