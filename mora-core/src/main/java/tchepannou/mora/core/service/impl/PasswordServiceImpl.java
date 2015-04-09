package tchepannou.mora.core.service.impl;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.service.PasswordService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class PasswordServiceImpl implements PasswordService {
    //-- Static
    public static final String DIGEST_ALGORITHM = "MD5";


    //-- Attributes
    @Autowired
    private PasswordDao passwordDao;


    //-- Main
    public static void main(String[] args){
        System.out.println(new PasswordServiceImpl().encrypt("_secret_"));
    }

    //-- PasswordService overrides
    @Override
    public Password findByUser(long userId) {
        return passwordDao.findByUser(userId);
    }

    @Override
    public void create(Password password) {
        Date now = new Date ();
        password.setCreationDate(now);
        password.setLastUpdate(now);

        passwordDao.create(password);
    }


    @Override
    public void update(Password password) {
        Date now = new Date ();
        password.setLastUpdate(now);

        passwordDao.create(password);
    }


    @Override
    public String encrypt(String clearValue) {
        if (clearValue == null){
            return null;
        }

        String algo = getAlgorithm();
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            md.update(clearValue.getBytes());
            byte[] bytes = md.digest();
            return Hex.encodeHexString(bytes);
        } catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("Encryption algorithm not supported: " + algo, e);
        }
    }

    @Override
    public boolean matches(Password password, String cleaValue) {
        return cleaValue != null ? encrypt(cleaValue).equals(password.getValue()) : false;
    }


    //-- Getter/Setter
    public String getAlgorithm (){
        return DIGEST_ALGORITHM;
    }

    @Required
    public void setPasswordDao(PasswordDao passwordDao) {
        this.passwordDao = passwordDao;
    }
}
