package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.dao.mapper.NodeRowMapper;
import tchepannou.mora.insidesoccer.domain.Node;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
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
    public Node findById (long id){
        String sql = String.format("SELECT N.*, R.* FROM %s N JOIN nprel R ON %s=R.nprel_node_fk WHERE R.nprel_id=?", getTableName(), getIdColumn());
        return findSingle(sql, new Object[]{id});
    }

    @Override
    public List<Node> findByIds(Collection<Long> ids) {
        StringBuilder sql = new StringBuilder(String.format("SELECT N.*, R.* FROM %s N JOIN nprel R ON %s=R.nprel_node_fk WHERE N.node_deleted=?", getTableName(), getIdColumn()));
        List params = new LinkedList<>();
        params.add(false);
        if (!ids.isEmpty()) {
            sql.append(" AND ");
            whereIn(sql, "R.nprel_id", ids, params);
        }
        return template.query(sql.toString(), params.toArray(), getRowMapper());
    }

    @Override
    public List<Long> findIdsByRelationshhipTypeByParties(long relationshipTypeId, Collection<Long> partyIds, int limit, int offset) {
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
