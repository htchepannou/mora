package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.jdbc.mapper.JdbcDao;
import tchepannou.mora.insidesoccer.dao.NodePartyRelationshipDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeRelationshipRowMapper;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class NodeRelationshipDaoImpl extends JdbcDao implements NodePartyRelationshipDao {
    //-- Attributes
    private static final RowMapper<NodePartyRelationship> MAPPER = new NodeRelationshipRowMapper();

    //-- NodeDao overrides
    @Override
    public NodePartyRelationship findById (long id){
        String sql = "SELECT N.*, R.* FROM node N JOIN nprel R ON N.node_id=R.nprel_node_fk WHERE R.nprel_id=? AND N.node_deleted=?";
        try {
            return template.queryForObject(sql, new Object[] {id, false}, MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){ // NOSONAR - Each exception intentionally
            return null;
        }
    }

    @Override
    public List<NodePartyRelationship> findByIds(Collection<Long> ids) {
        StringBuilder sql = new StringBuilder("SELECT N.*, R.* FROM node N JOIN nprel R ON N.node_id=R.nprel_node_fk WHERE N.node_deleted=?");
        List params = new LinkedList<>();
        params.add(false);
        if (!ids.isEmpty()) {
            sql.append(" AND ");
            whereIn(sql, "R.nprel_id", ids, params);
        }
        return template.query(sql.toString(), params.toArray(), MAPPER);
    }

    @Override
    public List<Long> findIdsByTypeByParties(long relationshipTypeId, Collection<Long> partyIds, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT R.nprel_id FROM node N JOIN nprel R ON N.node_id=R.nprel_node_fk WHERE N.node_deleted=? AND R.nprel_type_fk=?");

        List params = new ArrayList<>();
        params.add(false);
        params.add(relationshipTypeId);
        if (!partyIds.isEmpty()){
            sql.append(" AND ");
            whereIn(sql, "nprel_party_fk", partyIds, params);
        }

        sql.append(" ORDER BY node_date DESC");
        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        return template.query(sql.toString(), params.toArray(), new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getLong(1);
            }
        });
    }
}
