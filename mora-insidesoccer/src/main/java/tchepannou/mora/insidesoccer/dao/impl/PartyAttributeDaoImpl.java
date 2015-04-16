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
        StringBuilder sql = new StringBuilder("SELECT A.* FROM pattr A JOIN party P ON A.pattr_party_fk=P.party_id WHERE A.pattr_party_fk=? AND P.party_deleted=?");

        List params = new ArrayList();
        params.add(partyId);
        params.add(false);

        if (names.length>0) {
            sql.append(" AND pattr_name IN(");
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

    @Override
    public List<PartyAttribute> findByNameByValue(String name, String value) {
        String sql = "SELECT A.* FROM pattr A JOIN party P ON A.pattr_party_fk=P.party_id WHERE P.party_deleted=? AND A.pattr_name=? AND A.pattr_value=?";

        return template.query(sql, new Object[] {false, name, value}, getRowMapper());
    }
}
