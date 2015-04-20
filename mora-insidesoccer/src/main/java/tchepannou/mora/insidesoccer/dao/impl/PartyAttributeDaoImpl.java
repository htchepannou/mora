package tchepannou.mora.insidesoccer.dao.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.dao.mapper.PartyAttributeRowMapper;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PartyAttributeDaoImpl extends IsReadOnlyModelDao<PartyAttribute> implements PartyAttributeDao {
    //-- Attributes
    private static final RowMapper<PartyAttribute> MAPPER = new PartyAttributeRowMapper();

    //-- IsReadOnlyModelDao overrides
    @Override
    protected String getIdColumn() {
        return "pattr_id";
    }

    @Override
    protected String getTableName() {
        return "pattr";
    }

    @Override
    protected RowMapper<PartyAttribute> getRowMapper() {
        return MAPPER;
    }

    @Override
    public List<PartyAttribute> findByPartyByNames(long partyId, String... names) {

        Multimap<Long, PartyAttribute> result = findByPartiesByNames(Collections.singletonList(partyId), names);
        return !result.isEmpty()
                ? new ArrayList(result.get(partyId))
                : Collections.emptyList();
    }

    @Override
    public Multimap<Long, PartyAttribute> findByPartiesByNames(Collection<Long> partyIds, String... names) {
        StringBuilder sql = new StringBuilder("SELECT A.* FROM pattr A JOIN party P ON A.pattr_party_fk=P.party_id WHERE P.party_deleted=?");

        List params = new ArrayList();
        params.add(false);

        if (!partyIds.isEmpty()) {
            sql.append(" AND ");
            whereIn(sql, "pattr_party_fk", partyIds, params);
        }
        if (names.length>0){
            sql.append(" AND ");
            whereIn(sql, "pattr_name", Arrays.asList(names), params);
        }
        List<PartyAttribute> attributes = template.query(sql.toString(), params.toArray(), getRowMapper());

        Multimap<Long, PartyAttribute> result = ArrayListMultimap.create();
        for (PartyAttribute attr : attributes){
            result.put(attr.getPartyId(), attr);
        }
        return result;
    }

    
    @Override
    public List<PartyAttribute> findByNameByValueByPartyType(String name, String value, long partyTypeId){
        String sql = "SELECT A.* FROM pattr A JOIN party P ON A.pattr_party_fk=P.party_id WHERE P.party_deleted=? AND A.pattr_name=? AND A.pattr_value=? AND P.party_type_fk=?";

        return template.query(sql, new Object[] {false, name, value, partyTypeId}, getRowMapper());
    }
}
