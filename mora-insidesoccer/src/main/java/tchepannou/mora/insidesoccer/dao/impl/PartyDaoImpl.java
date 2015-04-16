package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.dao.mapper.PartyRowMapper;
import tchepannou.mora.insidesoccer.domain.Party;

public class PartyDaoImpl extends IsReadOnlyModelDao<Party> implements PartyDao{
    private static final RowMapper<Party> MAPPER = new PartyRowMapper();

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
