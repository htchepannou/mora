package tchepannou.mora.insidesoccer.service.impl;

import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.service.PasswordService;

public class IsPasswordService implements PasswordService{
    @Override
    public void create(Password password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encrypt(String clearValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Password findByUser(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Password password) {
        throw new UnsupportedOperationException();
    }
}
