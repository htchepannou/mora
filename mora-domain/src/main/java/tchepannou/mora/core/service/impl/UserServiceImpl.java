package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.UserService;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{
    //-- Attributes
    public static final String DELETED_PREFIX = "##";

    @Autowired
    private UserDao userDao;


    //-- UserService overrides
    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void save(User user) {
        Date now = new Date ();
        if (user.getCreationDate() == null) {
            user.setCreationDate(now);
        }
        user.setLastUpdate(now);

        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        user.setDeleted(true);
        user.setEmail(String.format("%s%s", DELETED_PREFIX, user.getEmail()));
        user.setUsername(String.format("%s%s", DELETED_PREFIX, user.getUsername()));
        userDao.save(user);
    }


    //-- Getter/Setter
    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
