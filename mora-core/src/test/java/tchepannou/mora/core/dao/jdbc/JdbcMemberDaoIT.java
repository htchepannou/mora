package tchepannou.mora.core.dao.jdbc;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcMemberDao.sql"}),
})
public class JdbcMemberDaoIT {
    @Autowired
    private MemberDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testFindBySpaceByUserByRole() throws Exception {
        // Given

        // When
        Member member = dao.findBySpaceByUserByRole(1, 1, 1);

        // Then
        User user = new User (1);
        SpaceType spaceType = new SpaceType(1);
        Member expected = new Member (10, new Space(1, spaceType, user), user, new Role(1));
        expected.setCreationDate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));
        assertThat(member, equalTo(expected));
    }

    @Test
    public void testFindBySpaceByUserByRole_deletedSpace_returnsNull() throws Exception {
        // Given

        // When
        Member member = dao.findBySpaceByUserByRole(2, 1, 1);

        // Then
        assertThat(member, nullValue());
    }

    @Test
    public void testFindBySpaceByUserByRole_deletedUser_returnsNull() throws Exception {
        // Given

        // When
        Member member = dao.findBySpaceByUserByRole(1, 10, 1);

        // Then
        assertThat(member, nullValue());
    }



    @Test
    public void testFindBySpaceByUser() throws Exception {
        // Given

        // When
        List<Member> members = dao.findBySpaceByUser(1, 1);

        // Then
        User user = new User (1);
        SpaceType spaceType = new SpaceType(1);
        Space space = new Space(1, spaceType, user);
        Date date = new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime());

        Member expected1 = new Member (10, space, user, new Role(1));
        expected1.setCreationDate(date);

        Member expected2 = new Member (11, space, user, new Role(2));
        expected2.setCreationDate(date);

        assertThat(members, hasSize(2));
        assertThat(members, hasItems(expected1, expected2));
    }

    @Test
    public void testFindBySpaceByUser_deletedSpace_returnsEmptyList() throws Exception {
        // Given

        // When
        List<Member> members = dao.findBySpaceByUser(2, 1);

        // Then
        assertThat(members, hasSize(0));
    }

    @Test
    public void testFindBySpaceByUser_deletedUser_returnsEmptyList() throws Exception {
        // Given

        // When
        List<Member> members = dao.findBySpaceByUser(1, 10);

        // Then
        assertThat(members, hasSize(0));
    }


    @Test
    public void testFindBySpace() throws Exception {
        // Given

        // When
        List<Member> members = dao.findBySpace(1);

        // Then
        User user1 = new User (1);
        User user2 = new User (2);
        SpaceType spaceType = new SpaceType(1);
        Space space = new Space(1, spaceType, user1);
        Date date = new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime());

        Member expected1 = new Member (10, space, user1, new Role(1));
        expected1.setCreationDate(date);

        Member expected2 = new Member (11, space, user1, new Role(2));
        expected2.setCreationDate(date);

        Member expected3 = new Member (20, space, user2, new Role(1));
        expected3.setCreationDate(date);

        assertThat(members, hasSize(3));
        assertThat(members, hasItems(expected1, expected2, expected3));
    }

    @Test
    public void testFindBySpace_deletedSpace_returnsEmptyList() throws Exception {
        // Given

        // When
        List<Member> members = dao.findBySpace(2);

        // Then
        assertThat(members, hasSize(0));
    }



    @Test
    public void testFindByUser() throws Exception {
        // Given

        // When
        List<Member> members = dao.findByUser(1);

        // Then
        User user = new User (1);
        SpaceType spaceType = new SpaceType(1);
        Space space1 = new Space(1, spaceType, user);
        Space space2 = new Space(2, spaceType, user);
        Date date = new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime());

        Member expected1 = new Member (10, space1, user, new Role(1));
        expected1.setCreationDate(date);

        Member expected2 = new Member (11, space1, user, new Role(2));
        expected2.setCreationDate(date);

        assertThat(members, hasSize(2));
        assertThat(members, hasItems(expected1, expected2));
    }
    @Test
    public void testFindByUser_deletedUser_returnsEmptyList() throws Exception {
        // Given

        // When
        List<Member> members = dao.findByUser(10);

        // Then
        System.out.println(members);
        assertThat(members, hasSize(0));
    }

    @Test
    public void testCreate() throws Exception {
        // Given
        Date now = new Date ();

        Member member = new Member();
        member.setUserId(3);
        member.setSpaceId(1);
        member.setRoleId(1);
        member.setCreationDate(now);

        // Given
        long id = dao.create(member);
        Member result = ((JdbcMemberDao)dao).findById(id);

        // Then
        assertThat(member.getId(), equalTo(id));

        Member expected = new Member(member);
        expected.setId(id);
        expected.setCreationDate(result.getCreationDate());
        assertThat(result, equalTo(expected));
    }

    @Test(expected = DuplicateKeyException.class)
    public void testCreate_duplicateMember() throws Exception {
        // Given
        Date now = new Date ();

        Member member = new Member();
        member.setUserId(1);
        member.setSpaceId(1);
        member.setRoleId(1);
        member.setCreationDate(now);

        // Given
        dao.create(member);
    }

    @Test
    public void testUpdate () throws Exception{
        // Given
        Member member = ((JdbcMemberDao)dao).findById(20);
        member.setRoleId(2);

        Member expected = new Member(member);

        // Given
        dao.update(member);
        Member result = ((JdbcMemberDao)dao).findById(20);

        // Then
        expected.setRoleId(2);
        assertThat(result, equalTo(expected));
    }


    @Test
    public void testDelete () throws Exception{
        // Given
        Member member = ((JdbcMemberDao)dao).findById(10);

        // Given
        dao.delete(member);

        // Then
        Member result = ((JdbcMemberDao)dao).findById(10);
        assertThat(result, Matchers.nullValue());
    }
}