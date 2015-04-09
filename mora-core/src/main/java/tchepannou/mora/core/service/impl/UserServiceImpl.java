package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.EmailAlreadyAssignedException;
import tchepannou.mora.core.exception.UserException;
import tchepannou.mora.core.exception.UsernameAlreadyAssignedException;
import tchepannou.mora.core.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    //-- Attributes
    @Autowired
    private UserDao userDao;


    //-- UserService overrides
    @Override
    public User findById(long id) {
        User user = userDao.findById(id);
        return user != null && !user.isDeleted() ? user : null;
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = userDao.findByEmail(email, false);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = userDao.findByUsername(username, false);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    @Transactional
    public void create(User user) throws UserException {
        ensureEmailNotAssigned(user);
        ensureUsernameNotAssigned(user);

        Date now = new Date ();
        user.setCreationDate(now);
        user.setLastUpdate(now);

        userDao.create(user);
    }


    @Override
    @Transactional
    public void update(User user) throws UserException {
        ensureEmailNotAssigned(user);
        ensureUsernameNotAssigned(user);

        Date now = new Date ();
        user.setLastUpdate(now);

        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }


    //-- Private
    private void ensureEmailNotAssigned (User user) throws UserException{
        User xuser = findByEmail(user.getEmail());
        if (xuser != null && xuser.getId() != user.getId()){
            throw new EmailAlreadyAssignedException(user.getEmail());
        }
    }

    private void ensureUsernameNotAssigned(User user) throws UserException{
        User xuser = findByUsername(user.getUsername());
        if (xuser != null && xuser.getId() != user.getId()){
            throw new UsernameAlreadyAssignedException(user.getUsername());
        }
    }

    //-- Getter/Setter
    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
