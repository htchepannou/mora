package tchepannou.mora.insidesoccer.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.domain.PartyRelationship;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartyRelationshipRowMapper implements RowMapper<PartyRelationship> {
    @Override
    public PartyRelationship mapRow(ResultSet rs, int i) throws SQLException {
        PartyRelationship result = new PartyRelationship();
        result.setId(rs.getLong("prel_id"));
        result.setTypeId(rs.getLong("prel_type_fk"));
        result.setSourceId(rs.getLong("prel_source_fk"));
        result.setDestinationId(rs.getLong("prel_dest_fk"));
        result.setRank(rs.getLong("prel_rank"));
        result.setQualifier(rs.getString("prel_qualifier"));
        return result;
    }
}
