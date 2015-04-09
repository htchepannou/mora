package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.service.HashService;
import tchepannou.mora.core.service.PasswordService;

import java.util.Date;

@Service
public class PasswordServiceImpl implements PasswordService {
    //-- Attributes
    @Autowired
    private PasswordDao passwordDao;

    @Autowired
    private HashService hashService;


    //-- PasswordService overrides
    @Override
    public Password findByUser(long userId) {
        return passwordDao.findByUser(userId);
    }

    @Override
    public void create(Password password) {
        Date now = new Date();
        password.setCreationDate(now);
        password.setLastUpdate(now);

        passwordDao.create(password);
    }


    @Override
    public void update(Password password) {
        Date now = new Date();
        password.setLastUpdate(now);

        passwordDao.update(password);
    }


    @Override
    public String encrypt(String clearValue) {
        return hashService.generate(clearValue);
    }
}