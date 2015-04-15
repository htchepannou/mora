package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.MemberDuplicationException;
import tchepannou.mora.core.service.MemberService;
import tchepannou.mora.core.service.SpaceService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith (MockitoJUnitRunner.class)
public class MemberServiceImplTest {
    @Mock
    private MemberDao dao;

    @Mock
    private SpaceService spaceService;

    @InjectMocks
    private MemberService service = new MemberServiceImpl();


    //-- Test
    @Test
    public void testFindBySpaceByUserByRole() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Role role = new Role(1);
        Member m1 = new Member (1, space, new User(1), role);
        when (dao.findBySpaceByUserByRole(1, 1, 1)).thenReturn(m1);

        // When
        Member result = service.findBySpaceByUserByRole(1, 1, 1);

        // Then
        assertThat(result, equalTo(m1));
    }

    @Test
    public void testFindBySpaceByUser() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m1 = new Member (1, space, new User(1), new Role(1));
        Member m2 = new Member (2, space, new User(1), new Role(2));
        when (dao.findBySpaceByUser(1, 1)).thenReturn(Arrays.asList(m1, m2));

        // When
        List<Member> result = service.findBySpaceByUser(1, 1);

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(m1, m2));
    }

    @Test
    public void testFindBySpace() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m1 = new Member (1, space, new User(1), new Role(1));
        Member m2 = new Member (2, space, new User(1), new Role(2));
        when (dao.findBySpace(1)).thenReturn(Arrays.asList(m1, m2));

        // When
        List<Member> result = service.findBySpace(1);

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(m1, m2));
    }

    @Test
    public void testFindByUser() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m1 = new Member (1, space, new User(1), new Role(1));
        Member m2 = new Member (2, space, new User(1), new Role(2));
        when (dao.findByUser(1)).thenReturn(Arrays.asList(m1, m2));

        // When
        List<Member> result = service.findByUser(1);

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(m1, m2));
    }

    @Test
    public void testCreate() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m = new Member (0, space, new User(1), new Role(1));

        // When
        service.create(m);

        // Then
        assertThat(m.getCreationDate(), notNullValue());
        verify(dao).create(m);
    }

    @Test(expected = MemberDuplicationException.class)
    public void testCreate_duplicateMember_shouldThrowDuplicateMemberException() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m = new Member (0, space, new User(1), new Role(1));

        when(dao.create(m)).thenThrow(DuplicateKeyException.class);

        // When
        service.create(m);
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m = new Member (1, space, new User(1), new Role(1));

        // When
        service.update(m);

        // Then
        verify(dao).update(m);
    }

    @Test(expected = MemberDuplicationException.class)
    public void testUpdate_duplicateMember_shouldThrowDuplicateMemberException() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m = new Member (1, space, new User(1), new Role(1));

        doThrow(DuplicateKeyException.class).when(dao).update(m);

        // When
        service.update(m);

        // Then
    }

    @Test
    public void testDelete() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        Member m = new Member (1, space, new User(2), new Role(1));

        when (spaceService.findById(1)).thenReturn(space);

        // When
        service.delete(m);

        // Then
        verify(dao).delete(m);
    }
}