package tchepannou.mora.core.service;

import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.exception.MemberException;

import java.util.List;

public interface MemberService {
    Member findById (long id);
    Member findBySpaceByUserByRole(long spaceId, long userId, long roleId);
    List<Member> findBySpaceByUser (long spaceId, long userId);
    List<Member> findBySpace (long spaceId);
    List<Member> findByUser (long userId);
    Member create (Member member) throws MemberException;
    Member update (Member member) throws MemberException;
    Member delete (Member member);
}
