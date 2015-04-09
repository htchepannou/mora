package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Password;

public interface PasswordService {
    String encrypt (String clearValue);
    Password findByUser (long userId);
    void create(Password password);
    void update(Password password);
}
