package tchepannou.mora.core.dao.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.dao.jdbc.mapper.MemberRowMapper;
import tchepannou.mora.core.domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class JdbcMemberDao extends JdbcModelDao<Member> implements MemberDao {
    private static final RowMapper<Member> MAPPER = new MemberRowMapper();
    private static final String SQL_PREFIX = "SELECT M.* FROM t_member M JOIN t_space S ON S.id=M.space_id JOIN t_user U ON U.id=M.user_id ";

    //-- JdbcModelDao overrides
    @Override
    protected PreparedStatementCreator getPreparedStatementCreator(final Member model) {
        final String sql = "INSERT INTO t_member(user_id, space_id, role_id, creation_date) VALUES(?,?,?,?)";
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, model.getUserId());
                ps.setLong(2, model.getSpaceId());
                ps.setLong(3, model.getRoleId());
                ps.setTimestamp(4, new Timestamp(model.getCreationDate().getTime()));
                return ps;
            }
        };
    }

    @Override
    protected String getTableName() {
        return "t_member";
    }

    @Override
    protected RowMapper getRowMapper() {
        return MAPPER;
    }


    //-- MemberDao overrides
    @Override
    public Member findBySpaceByUserByRole(long spaceId, long userId, long roleId) {
        try{
            String sql = SQL_PREFIX + " WHERE M.space_id=? AND M.user_id=? AND M.role_id=? AND S.deleted=? AND U.deleted=?";
            return template.queryForObject(sql, new Object[] {spaceId, userId, roleId, false, false}, MAPPER);
        } catch (EmptyResultDataAccessException e){ // NOSONAR - This exception intentionally
            return null;
        }
    }

    @Override
    public List<Member> findBySpaceByUser(long spaceId, long userId) {
        String sql = SQL_PREFIX + " WHERE M.space_id=? AND M.user_id=? AND S.deleted=? AND U.deleted=?";
        return template.query(sql, new Object[]{
                spaceId,
                userId,
                false, false
        }, MAPPER);
    }

    @Override
    public List<Member> findBySpace(long spaceId) {
        String sql = SQL_PREFIX + " WHERE M.space_id=? AND S.deleted=? AND U.deleted=?";
        return template.query(sql, new Object[] {spaceId, false, false}, MAPPER);
    }

    @Override
    public List<Member> findByUser(long userId) {
        String sql = SQL_PREFIX + " WHERE M.user_id=? AND S.deleted=? AND U.deleted=?";
        return template.query(sql, new Object[] {userId, false, false}, MAPPER);
    }

    @Override
    public void update(Member member) {
        String sql = "UPDATE t_member SET role_id=? WHERE id=?";
        template.update(sql, member.getRoleId(), member.getId());
    }
}
