package tchepannou.mora.insidesoccer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.exception.AccessTokenException;
import tchepannou.mora.core.exception.AuthFailedException;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.HttpRequest;
import tchepannou.mora.core.service.HttpResponse;
import tchepannou.mora.core.service.UrlFetchService;
import tchepannou.mora.rest.core.dto.ModelDto;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class IsAccessTokenService implements AccessTokenService{
    //-- Attributes
    @Value ("${insidesoccer.api.url}")
    private String apiUrl;

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Autowired
    private UrlFetchService urlService;


    //-- Public
    @Override
    @Cacheable("AccessToken")
    public AccessToken findByValue(String value) {
        return value != null ? accessTokenDao.findByValue(value) : null;
    }

    @Override
    @CachePut("AccessToken")
    public AccessToken authenticate(String usernameOrEmail, String clearPassword) throws AccessTokenException {
        try {
            String path = String.format("/login/signin.json?name=%s&password=%s", encode(usernameOrEmail), encode(clearPassword));
            URL url = buildUrl(path);
            HttpResponse resp = urlService.fetch(new HttpRequest.Builder().withUrl(url).build());
            try {
                if (resp.getStatusCode() != 200) {
                    throw new AccessTokenException("Status code:" + resp.getStatusCode());
                }

                LoginDto dto = getContentAsObject(resp, LoginDto.class);
                if (dto.error_code == 0){
                    return accessTokenDao.findByValue(String.valueOf(dto.login_id));
                } else {
                    throw new AuthFailedException(dto.error_text);
                }
            } finally{
                resp.close();
            }
        } catch (IOException e){
            throw new AccessTokenException("IO error", e);
        }
    }

    @Override
    @CacheEvict("AccessToken")
    public AccessToken expire(AccessToken token) throws AccessTokenException {
        try {
            URL url = buildUrl("/login/signout.json?id=" + token.getId());
            urlService.fetch(new HttpRequest.Builder().withUrl(url).build());

            return accessTokenDao.findByValue(String.valueOf(token.getId()));
        } catch (IOException e){
            throw new AccessTokenException("IO error", e);
        }
    }


    //-- Private
    private URL buildUrl (String path) throws IOException {
        return new URL(apiUrl + path);
    }

    private String encode (String value) throws IOException{
        return URLEncoder.encode(value, "utf-8");
    }

    private <T> T getContentAsObject(HttpResponse resp, Class<T> type) throws IOException{
        return new ObjectMapper().readValue(resp.getInputStream(), type);
    }


    //-- Setter
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }


    //-- Inner classes
    public static class UserDto extends ModelDto{
        public int id;              // NOSONAR
        public String display_name; // NOSONAR
        public String email;        // NOSONAR
        public String super_user;   // NOSONAR
    }

    public static class LoginDto extends ModelDto {
        public int error_code;      // NOSONAR
        public String error_text;   // NOSONAR;
        public int login_id;        // NOSONAR;
        public UserDto user;        // NOSONAR
    }
}
