package tchepannou.mora.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.exception.MemberDuplicationException;
import tchepannou.mora.core.exception.MemberException;
import tchepannou.mora.core.service.MemberService;

import java.util.Date;
import java.util.List;

public class MemberServiceImpl implements MemberService {
    //-- Attributes
    @Autowired
    private MemberDao memberDao;


    //-- MemberService overrides
    @Override
    @Cacheable ("Member")
    public Member findById (long id){
        return memberDao.findById (id);
    }

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
    @CachePut("Member")
    public Member create(Member member)  throws MemberException {
        try {
            member.setCreationDate(new Date());
            memberDao.create(member);

            return member;
        }catch (DuplicateKeyException e){
            throw new MemberDuplicationException("duplicate member", e);
        }
    }

    @Override
    @CacheEvict("Member")
    public Member update(Member member) throws MemberException {
        try {
            memberDao.update(member);
            return member;
        }catch (DuplicateKeyException e){
            throw new MemberDuplicationException("duplicate member", e);
        }
    }

    @Override
    @CacheEvict("Member")
    public Member delete(Member member){
        memberDao.delete(member);
        return member;
    }
}
