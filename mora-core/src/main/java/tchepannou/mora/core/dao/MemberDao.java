package tchepannou.mora.core.dao;

import tchepannou.mora.core.domain.Member;

import java.util.List;

public interface MemberDao {
    Member findById (long id);
    Member findBySpaceByUserByRole(long spaceId, long userId, long roleId);
    List<Member> findBySpaceByUser (long spaceId, long userId);
    List<Member> findBySpace (long spaceId);
    List<Member> findByUser (long userId);
    long create (Member member);
    void update (Member member);
    void delete (Member member);
}
