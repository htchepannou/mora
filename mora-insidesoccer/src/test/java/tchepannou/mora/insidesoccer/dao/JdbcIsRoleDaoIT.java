package tchepannou.mora.insidesoccer.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;



@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
public class JdbcIsRoleDaoIT {
    @Autowired
    private RoleDao roleDao;


    @Test
    public void testFindById (){
        // Given

        // When
        Role result = roleDao.findById(1);

        // Then
        assertThat(result, equalTo(new Role(1, "admin")));
    }
    @Test
    public void testFindById_notFound_returnsNull (){
        // Given

        // When
        Role result = roleDao.findById(999);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindAll (){
        // Given

        // When
        List<Role> result = roleDao.findAll();

        // Then
        assertThat(result, hasSize(9));
        assertThat(result, hasItems(
                new Role(1, "admin")
                , new Role(2, "coach")
                , new Role(3, "player")
                , new Role(6, "member")
                , new Role(7, "technical_director")
                , new Role(8, "team_manager")
                , new Role(9, "treasurer")
                , new Role(11, "head_coach")
                , new Role(12, "volunteer_coach")
        ));
    }

}