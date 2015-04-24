package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.domain.IsRole;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;



@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
public class IsRoleDaoIT {
    @Autowired
    private RoleDao roleDao;


    @Test
    public void testFindById (){
        // Given

        // When
        Role result = roleDao.findById(1);

        // Then
        assertThat(result, equalTo(new IsRole(1, "admin", true)));
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
                new IsRole(1, "admin", true)
                , new IsRole(2, "coach", false)
                , new IsRole(3, "player", false)
                , new IsRole(6, "member", false)
                , new IsRole(7, "technical_director", true)
                , new IsRole(8, "team_manager", false)
                , new IsRole(9, "treasurer", false)
                , new IsRole(11, "head_coach", false)
                , new IsRole(12, "volunteer_coach", false)
        ));
    }

}