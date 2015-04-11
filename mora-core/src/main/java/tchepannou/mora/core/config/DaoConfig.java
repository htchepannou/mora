package tchepannou.mora.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.dao.jdbc.JdbcAccessTokenDao;
import tchepannou.mora.core.dao.jdbc.JdbcPasswordDao;
import tchepannou.mora.core.dao.jdbc.JdbcSpaceTypeDao;
import tchepannou.mora.core.dao.jdbc.JdbcUserDao;

import javax.sql.DataSource;

@Configuration
public class DaoConfig {
    @Value ("${database.driver}")
    private String driver;

    @Value ("${database.url}")
    private String url;

    @Value ("${database.username}")
    private String username;

    @Value ("${database.password}")
    private String password;


    //-- Beans
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    public DataSource dataSource (){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        return ds;
    }

    @Bean
    public AccessTokenDao accessTokenDao (){
        return new JdbcAccessTokenDao();
    }

    @Bean
    public PasswordDao passwordDao (){
        return new JdbcPasswordDao();
    }

    @Bean
    public UserDao userDao (){
        return new JdbcUserDao();
    }

    @Bean
    public SpaceTypeDao spaceTypeDao(){
        return new JdbcSpaceTypeDao();
    }
}
