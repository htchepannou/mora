package tchepannou.mora.core.service.impl;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.AccessTokenException;
import tchepannou.mora.core.exception.AuthFailedException;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.HashService;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.UserService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Date;
import java.util.List;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private HashService hashService;

    @Value ("${access_token.ttl.minutes:30}")
    private int tokenTTLMinutes = 30;

    //-- AuthService overrides
    @Override
    @Cacheable("AccessToken")
    public AccessToken findByValue(String key) {
        LOG.debug("findByValue({})", key);

        return key != null ? accessTokenDao.findByValue(key) : null;
    }

    @Override
    @Transactional
    @CachePut (value="AccessToken", key="#result.value")
    public AccessToken authenticate(String usernameOrEmail, String clearPassword) throws AccessTokenException {
        LOG.debug("authenticate({}, '*********')", usernameOrEmail);

        Preconditions.checkArgument(usernameOrEmail != null, "usernameOrEmail == null");
        Preconditions.checkArgument(clearPassword != null, "password == null");

        User user = findUser(usernameOrEmail);
        if (user == null) {
            throw new AuthFailedException("No user found with username or email: " + usernameOrEmail);
        }

        Password password = passwordService.findByUser(user.getId());
        if (password == null) {
            throw new AuthFailedException("No password assigned to " + user);
        } else if (!matches(password, clearPassword)) {
            throw new AuthFailedException("Password mismatch");
        }

        expireTokens(user);
        return createToken(user);
    }


    @Override
    @Transactional
    @CacheEvict (value="AccessToken", key="#token.value")
    public AccessToken expire(AccessToken token) {
        LOG.debug("expire({})", token);

        token.expire();
        accessTokenDao.update(token);

        return token;
    }

    //-- Setter
    public void setTokenTTLMinutes(int tokenTTLMinutes) {
        this.tokenTTLMinutes = tokenTTLMinutes;
    }



    //-- Private
    private void expireTokens(User user) {
        List<AccessToken> tokens = accessTokenDao.findByUserByExpired(user.getId(), false);
        for (AccessToken token : tokens) {
            token.expire();
        }
        accessTokenDao.update(tokens);
    }

    private AccessToken createToken(User user) {
        Date now = new Date();
        String key = String.format("%d:%s", user.getId(), now);

        AccessToken result = new AccessToken(user);
        result.setCreationDate(now);
        result.setExpiryDate(DateUtils.addMinutes(now, tokenTTLMinutes));
        result.setValue(hashService.generate(key));
        accessTokenDao.create(result);

        return result;
    }

    private User findUser(String usernameOrEmail) {
        User result = null;
        if (isEmail(usernameOrEmail)) {
            result = userService.findByEmail(usernameOrEmail);
        }
        if (result == null) {
            result = userService.findByUsername(usernameOrEmail);
        }
        return result;
    }

    private boolean isEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) { // NOSONAR - It's OK to eat this exception
            return false;
        }
    }

    private boolean matches (Password password, String clearPassword){
        String xpassword = passwordService.encrypt(clearPassword);
        return xpassword != null && xpassword.equals(password.getValue());
    }

}
