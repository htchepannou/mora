package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.domain.Party;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartyRowMapper implements RowMapper<Party>{
    @Override
    public Party mapRow(ResultSet rs, int i) throws SQLException {
        Party result = new Party();
        result.setId(rs.getLong("party_id"));
        result.setTypeId(rs.getLong("party_type_fk"));
        result.setOwnerId(rs.getLong("party_owner_fk"));
        result.setStatus(rs.getInt("party_status"));
        result.setDeleted(rs.getBoolean("party_deleted"));
        result.setCreationDate(rs.getTimestamp("party_creation_date"));
        result.setDate(rs.getTimestamp("party_date"));
        return result;
    }
}
