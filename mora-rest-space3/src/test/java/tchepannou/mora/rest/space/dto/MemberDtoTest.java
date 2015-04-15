package tchepannou.mora.rest.space.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class MemberDtoTest {
    @Test
    public void testBuilder (){
        Date now = new Date ();
        Member member = new Member(1, new Space(2, new SpaceType(1), new User(1)), new User(10), new Role(100));
        member.setCreationDate(now);

        MemberDto result = new MemberDto.Builder().withMember(member).build();

        assertThat(result.getId(), equalTo(member.getId()));
        assertThat(result.getUserId(), equalTo(member.getUserId()));
        assertThat(result.getRoleId(), equalTo(member.getRoleId()));
        assertThat(result.getSpaceId(), equalTo(member.getSpaceId()));
        assertThat(result.getCreationDate(), equalTo(member.getCreationDate()));
    }
}