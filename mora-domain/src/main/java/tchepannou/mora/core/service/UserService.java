package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.User;

public interface UserService {
    User findById (long id);
    User findByUsername (String username);
    User findByEmail (String email);
    void save (User user);
    void delete(User user);
}
