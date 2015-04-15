package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.exception.MemberOwnershipException;
import tchepannou.mora.core.exception.MemberDuplicationException;
import tchepannou.mora.core.exception.MemberException;
import tchepannou.mora.core.service.MemberService;
import tchepannou.mora.core.service.SpaceService;

import java.util.Date;
import java.util.List;

public class MemberServiceImpl implements MemberService {
    //-- Attributes
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private SpaceService spaceService;


    //-- MemberService overrides
    @Override
    public Member findBySpaceByUserByRole(long spaceId, long userId, long roleId) {
        return memberDao.findBySpaceByUserByRole(spaceId, userId, roleId);
    }

    @Override
    public List<Member> findBySpaceByUser(long spaceId, long userId) {
        return memberDao.findBySpaceByUser(spaceId, userId);
    }

    @Override
    public List<Member> findBySpace(long spaceId) {
        return memberDao.findBySpace(spaceId);
    }

    @Override
    public List<Member> findByUser(long userId) {
        return memberDao.findByUser(userId);
    }

    @Override
    public void create(Member member)  throws MemberException {
        try {
            member.setCreationDate(new Date());
            memberDao.create(member);
        }catch (DuplicateKeyException e){
            throw new MemberDuplicationException("duplicate member", e);
        }
    }

    @Override
    public void update(Member member) throws MemberException {
        try {
            memberDao.update(member);
        }catch (DuplicateKeyException e){
            throw new MemberDuplicationException("duplicate member", e);
        }
    }

    @Override
    public void delete(Member member) throws MemberException{
        Space space = spaceService.findById(member.getSpaceId());
        if (space != null) {
            if (space.getUserId() == member.getUserId()){
                throw new MemberOwnershipException("Can't delete the owner of the space");
            }
            memberDao.delete(member);
        }
    }
}
