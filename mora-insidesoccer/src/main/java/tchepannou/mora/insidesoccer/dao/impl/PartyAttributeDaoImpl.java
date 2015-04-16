package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.dao.mapper.PartyAttributeRowMapper;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.ArrayList;
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
        StringBuilder sql = new StringBuilder(String.format("SELECT * FROM %s WHERE pattr_party_fk=?", getTableName()));

        List params = new ArrayList();
        if (names.length>0) {
            sql.append(" AND pattr_name IN(");
            params.add(partyId);
            int i = 0;
            for (String name : names) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append("?");
                params.add(name);
            }
            sql.append(")");
        }
        return template.query(sql.toString(), params.toArray(), getRowMapper());
    }

}
