package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.domain.SpaceType;

public class JdbcSpaceTypeDao extends JdbcEnumModelDao<SpaceType> implements SpaceTypeDao{
    //-- Constructor
    public JdbcSpaceTypeDao() {
        super(SpaceType.class);
    }

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_space_type";
    }
}
