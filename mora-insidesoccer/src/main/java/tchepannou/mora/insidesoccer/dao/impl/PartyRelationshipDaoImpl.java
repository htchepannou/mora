package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.PartyRelationshipDao;
import tchepannou.mora.insidesoccer.dao.mapper.PartyRelationshipRowMapper;
import tchepannou.mora.insidesoccer.domain.PartyRelationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PartyRelationshipDaoImpl extends IsReadOnlyModelDao<PartyRelationship> implements PartyRelationshipDao{//-- Attributes
    //-- Attributes
    private static final RowMapper<PartyRelationship> MAPPER = new PartyRelationshipRowMapper();

    //-- PartyRelationshipDao overrides
    @Override
    public List<PartyRelationship> findBySourceByType(long sourceId, long typeId) {
        return findBySourceByDestinationsByType(sourceId, Collections.emptyList(), typeId);
    }

    @Override
    public PartyRelationship findBySourceByDestinationByType(long sourceId, long destinationId, long typeId) {
        List<PartyRelationship> result = findBySourceByDestinationsByType(sourceId, Collections.singletonList(destinationId), typeId);
        return result.isEmpty() ? null : result.get(0);
    }


    @Override
    public List<PartyRelationship> findBySourceByDestinationsByType(long sourceId, Collection<Long> destinationIds, long typeId) {
        StringBuilder sql  = new StringBuilder("SELECT *" +
                " FROM prel JOIN party ON prel_dest_fk=party_id" +
                " WHERE prel_source_fk=?" +
                "   AND prel_type_fk=?" +
                "   AND party_deleted=?");

        List params = new ArrayList<>();
        params.add(sourceId);
        params.add(typeId);
        params.add(false);
        if (!destinationIds.isEmpty()){
            sql.append(" AND ");
            whereIn(sql, "prel_dest_fk", destinationIds, params);
        }
        return template.query(sql.toString(), params.toArray(), getRowMapper());
    }

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
    protected RowMapper<PartyRelationship> getRowMapper() {
        return MAPPER;
    }
}