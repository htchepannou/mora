package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Password;

public interface PasswordDao {
    Password findByUser (long userId);
    long create(Password password);
    void update (Password password);
}
