package tchepannou.mora.insidesoccer.dao;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.insidesoccer.dao.mapper.IsSpaceTypeRowMapper;

import java.util.List;

public class IsSpaceTypeDao extends IsReadOnlyModelDao<SpaceType> implements SpaceTypeDao {
    private static final RowMapper<SpaceType> MAPPER = new IsSpaceTypeRowMapper();
    private static final long MIN_ID=3;

    //-- JdbcIsReadOnlyModelDao overrides
    @Override
    protected String getTableName() {
        return "ptype";
    }

    @Override
    protected String getIdColumn() {
        return "ptype_id";
    }

    @Override
    protected RowMapper<SpaceType> getRowMapper() {
        return MAPPER;
    }

    //-- SpaceType overrides

    @Override
    public SpaceType findById(long id) {
        return id>=MIN_ID ? super.findById(id) : null;
    }

    @Override
    public List<SpaceType> findAll() {
        return template.query("SELECT * FROM ptype WHERE ptype_id>=?", new Object[]{MIN_ID}, MAPPER);
    }

}
