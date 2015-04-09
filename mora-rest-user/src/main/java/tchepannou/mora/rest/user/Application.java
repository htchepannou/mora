package tchepannou.mora.rest.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.dao.jdbc.JdbcPasswordDao;
import tchepannou.mora.core.dao.jdbc.JdbcUserDao;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.core.service.impl.PasswordServiceImpl;
import tchepannou.mora.core.service.impl.UserServiceImpl;

import javax.sql.DataSource;

@Configuration
@SpringBootApplication
public class Application {
    //-- Attributes
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
    public DataSource dataSource (){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        return ds;
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
    public UserService userService (){
        return new UserServiceImpl();
    }

    @Bean
    public PasswordService passwordService (){
        return new PasswordServiceImpl();
    }


    //-- Main
    public static void main (String [] args){
        SpringApplication.run(Application.class, args);
    }
}
