package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeRowMapper;
import tchepannou.mora.insidesoccer.domain.Node;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NodeDaoImpl extends IsReadOnlyModelDao<Node> implements NodeDao {
    //-- Attributes
    private static final RowMapper<Node> MAPPER = new NodeRowMapper();

    //-- IsReadOnlyModelDao overrides
    @Override
    protected String getIdColumn() {
        return "node_id";
    }

    @Override
    protected String getTableName() {
        return "node";
    }

    @Override
    protected RowMapper<Node> getRowMapper() {
        return MAPPER;
    }

    //-- NodeDao overrides
    @Override
    public List<Long> findIdsByRelationshhipTypeByParties(long relationshipTypeId, Collection<Long> partyIds, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT N.node_id FROM node N JOIN nprel R ON N.node_id=R.nprel_node_fk WHERE N.node_deleted=? AND R.nprel_type_fk=?");

        List params = new ArrayList<>();
        params.add(false);
        params.add(relationshipTypeId);
        if (!partyIds.isEmpty()){
            int i=0;

            sql.append(" AND nprel_party_fk IN (");
            for (long partyId : partyIds){
                if (i++ > 0){
                    sql.append(',');
                }
                sql.append('?');
                params.add(partyId);
            }
            sql.append(")");
        }

        sql.append(" ORDER BY node_date DESC");
        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        return template.query(sql.toString(), params.toArray(), new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getLong("node_id");
            }
        });
    }
}
