package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.dao.mapper.PartyRowMapper;
import tchepannou.mora.insidesoccer.domain.Party;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PartyDaoImpl extends IsReadOnlyModelDao<Party> implements PartyDao{
    private static final RowMapper<Party> MAPPER = new PartyRowMapper();

    //-- PartyDao overrides
    @Override
    public List<Party> findByIds (Collection<Long> ids){
        StringBuilder sql = new StringBuilder(String.format("SELECT * FROM %s WHERE party_deleted=? AND %s IN (", getTableName(), getIdColumn()));

        List params = new ArrayList<>();
        params.add(false);
        int i = 0;
        for (long id : ids){
            if (i++>0){
                sql.append(',');
            }
            sql.append('?');
            params.add(id);
        }
        sql.append(")");
        return template.query(sql.toString(), params.toArray(), getRowMapper());
    }

    //-- IsReadOnlyModelDao overrides
    @Override
    protected String getIdColumn() {
        return "party_id";
    }

    @Override
    protected String getTableName() {
        return "party";
    }

    @Override
    protected RowMapper<Party> getRowMapper() {
        return MAPPER;
    }
}
