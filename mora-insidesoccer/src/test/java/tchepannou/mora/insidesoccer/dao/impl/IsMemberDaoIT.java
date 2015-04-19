package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsMemberDao.sql"}),
})
public class IsMemberDaoIT {
    @Autowired
    private MemberDao memberDao;


    @Test
    public void testFindById() throws Exception {
        // When
        Member result = memberDao.findById(101);

        // Then
        Member expected = new Member(101, new Space(100), new User(101), new Role(1));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_deletedSpace_returnsNull() throws Exception {
        // When
        Member result = memberDao.findById(201);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_deletedUser_returnsNull() throws Exception {
        // When
        Member result = memberDao.findById(301);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_invalidType_returnsNull() throws Exception {
        // When
        Member result = memberDao.findById(401);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_notFound_returnsNull() throws Exception {
        // When
        Member result = memberDao.findById(999);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindByUser() throws Exception {
        // When
        List<Member> result = memberDao.findByUser(501);

        // Then
        Member expected1 = new Member(501, new Space(500), new User(501), new Role(1));
        Member expected2 = new Member(502, new Space(510), new User(501), new Role(1));
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testFindBySpaceByUserByRole() throws Exception {
        memberDao.findBySpaceByUserByRole(1, 1, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testFindBySpaceByUser() throws Exception {
        memberDao.findBySpaceByUser(1, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testFindBySpace() throws Exception {
        memberDao.findBySpace(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        memberDao.delete(new Member());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        memberDao.update(new Member());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreate() throws Exception {
        memberDao.create(new Member());
    }
}