package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Member;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int i) throws SQLException {
        Member result = new Member ();
        result.setId(rs.getLong("id"));
        result.setSpaceId(rs.getLong("space_id"));
        result.setRoleId(rs.getLong("role_id"));
        result.setUserId(rs.getLong("user_id"));
        result.setCreationDate(rs.getTimestamp("creation_date"));
        return result;
    }
}
