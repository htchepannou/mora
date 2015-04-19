package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Member;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IsMemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int i) throws SQLException {
        Member result = new Member();
        result.setId(rs.getLong("prel_id"));
        result.setRoleId(Long.parseLong(rs.getString("prel_qualifier")));
        result.setSpaceId(rs.getLong("prel_dest_fk"));
        result.setUserId(rs.getLong("prel_source_fk"));
        return result;
    }
}
