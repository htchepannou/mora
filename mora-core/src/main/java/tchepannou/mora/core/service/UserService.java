package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.UserException;

public interface UserService {
    User findById (long id);
    User findByUsername (String username);
    User findByEmail (String email);
    void save (User user) throws UserException;
    void delete(User user);
}
