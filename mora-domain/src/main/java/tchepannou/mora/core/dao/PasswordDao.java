package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Password;

public interface PasswordDao {
    Password findByUser (long userId);
    void save(Password password);
}
