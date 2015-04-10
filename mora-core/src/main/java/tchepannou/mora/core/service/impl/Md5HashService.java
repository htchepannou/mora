package tchepannou.mora.core.service.impl;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.service.HashService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Md5HashService implements HashService {
    //-- Static
    public static final String DIGEST_ALGORITHM = "MD5";


    //-- HashGeneratorService overrides
    @Override
    public String generate(String token) {
        if (token == null){
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance(DIGEST_ALGORITHM);
            md.update(token.getBytes());
            byte[] bytes = md.digest();
            return Hex.encodeHexString(bytes);
        } catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("Encryption algorithm not supported: " + DIGEST_ALGORITHM, e);
        }
    }
}
