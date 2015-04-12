package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.dao.jdbc.mapper.SpaceTypeRowMapper;
import tchepannou.mora.core.domain.SpaceType;

import java.util.List;

public class JdbcSpaceTypeDao extends JdbcReadOnlyModelDao<SpaceType> implements SpaceTypeDao{
    private static final RowMapper<SpaceType> MAPPER = new SpaceTypeRowMapper();

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_space_type";
    }

    @Override
    protected RowMapper<SpaceType> getRowMapper() {
        return MAPPER;
    }

    @Override
    public List<SpaceType> findAll() {
        return template.query("SELECT * FROM t_space_type", new Object[]{}, MAPPER);
    }
}
