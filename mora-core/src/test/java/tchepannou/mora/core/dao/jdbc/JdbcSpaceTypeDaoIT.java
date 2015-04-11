package tchepannou.mora.core.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.domain.SpaceType;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcSpaceTypeDao.sql"}),
})
public class JdbcSpaceTypeDaoIT {
    @Autowired
    private SpaceTypeDao spaceTypeDao;


    @Test
    public void testFindAll (){
        // Given

        // When
        List<SpaceType> result = spaceTypeDao.findAll();

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(new SpaceType(1, "club"), new SpaceType(2, "team")));
    }
}
