package tchepannou.mora.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tchepannou.mora.core.dao.*;
import tchepannou.mora.core.dao.jdbc.*;

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
    public DataSourceTransactionManager transactionManager (){
        return new DataSourceTransactionManager(dataSource());
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

    @Bean
    public SpaceDao spaceDao(){
        return new JdbcSpaceDao();
    }

    @Bean
    public RoleDao roleDao() {
        return new JdbcRoleDao();
    }

    @Bean
    public MemberDao memberDao (){
        return new JdbcMemberDao();
    }

    @Bean
    public PostDao postDao (){
        return new JdbcPostDao();
    }

    @Bean
    public MediaTypeDao mediaTypeDao (){
        return new JdbcMediaTypeDao();
    }

    @Bean
    public MediaDao mediaDao (){
        return new JdbcMediaDao();
    }
}
