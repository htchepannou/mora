package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.User;

public interface UserDao {
    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);
    void save(User user);
}
