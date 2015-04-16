package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartyAttributeRowMapper implements RowMapper<PartyAttribute> {
    @Override
    public PartyAttribute mapRow(ResultSet rs, int i) throws SQLException {
        PartyAttribute result = new PartyAttribute();
        result.setId(rs.getLong("pattr_id"));
        result.setPartyId(rs.getLong("pattr_party_fk"));
        result.setName(rs.getString("pattr_name"));
        result.setValue(rs.getString("pattr_value"));
        return result;
    }
}
