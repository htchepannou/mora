package tchepannou.mora.insidesoccer.dao;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
public class IsSpaceTypeDaoIT {
    @Autowired
    private SpaceTypeDao dao;


    @Test
    public void testFindById (){
        // Given

        // When
        SpaceType result = dao.findById(3);

        // Then
        assertThat(result, equalTo(new SpaceType(3, "team")));
    }

    @Test
    public void testFindById_id1_returnsNull (){
        // Given

        // When
        SpaceType result = dao.findById(1);

        // Then
        assertThat(result, Matchers.nullValue());
    }
    @Test
    public void testFindById_id2_returnsNull (){
        // Given

        // When
        SpaceType result = dao.findById(2);

        // Then
        assertThat(result, Matchers.nullValue());
    }

    @Test
    public void testFindById_notFound_returnsNull (){
        // Given

        // When
        SpaceType result = dao.findById(999);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindAll (){
        // Given

        // When
        List<SpaceType> result = dao.findAll();

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(
                new SpaceType(3, "team")
                , new SpaceType(4, "club")
        ));
    }

}