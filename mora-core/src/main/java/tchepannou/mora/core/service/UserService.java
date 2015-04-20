package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.UserException;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User findById (long id);
    List<User> findByIds (Collection<Long> ids);
    User findByUsername (String username);
    User findByEmail (String email);
    User create (User user) throws UserException;
    User update (User user) throws UserException;
    User delete(User user);
}
