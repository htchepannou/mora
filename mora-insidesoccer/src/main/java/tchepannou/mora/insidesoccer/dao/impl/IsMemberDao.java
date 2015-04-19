package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.insidesoccer.dao.mapper.IsMemberRowMapper;

import java.util.List;

public class IsMemberDao extends IsReadOnlyModelDao<Member> implements MemberDao{
    //-- Attributes
    public static final int TYPE_ID=10;
    private static final RowMapper<Member> MAPPER = new IsMemberRowMapper();

    //-- IsReadOnlyModelDao overrides
    @Override
    protected String getIdColumn() {
        return "prel_id";
    }

    @Override
    protected String getTableName() {
        return "prel";
    }

    @Override
    protected RowMapper<Member> getRowMapper() {
        return MAPPER;
    }

    //-- MemberDao overrides

    @Override
    public Member findById(long id) {
        String sql = "SELECT prel.* FROM prel JOIN party P1 ON prel_dest_fk=P1.party_id JOIN party P2 ON prel_source_fk=P2.party_id WHERE prel_id=? AND P1.party_deleted=? AND P2.party_deleted=? AND prel_type_fk=?";
        return findSingle(sql, new Object[] {id, false, false, TYPE_ID});
    }

    @Override
    public Member findBySpaceByUserByRole(long spaceId, long userId, long roleId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Member> findBySpaceByUser(long spaceId, long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Member> findBySpace(long spaceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Member> findByUser(long userId) {
        String sql = "SELECT prel.* FROM prel JOIN party ON prel_dest_fk=party_id WHERE prel_source_fk=? AND prel_type_fk=? AND party_deleted=?";
        return template.query(sql, new Object[]{userId, TYPE_ID, false}, getRowMapper());
    }
}
