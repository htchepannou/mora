package tchepannou.mora.core.dao.jdbc;

import com.google.common.base.Preconditions;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.EnumModelDao;
import tchepannou.mora.core.dao.jdbc.mapper.EnumModelRowMapper;
import tchepannou.mora.core.domain.EnumModel;

import java.util.List;

public abstract class JdbcEnumModelDao<T extends EnumModel> extends JdbcReadOnlyModelDao<T> implements EnumModelDao<T>{
    //-- ConstructorSpaceTypeDao
    private Class<T> type;

    //-- Constructor
    public JdbcEnumModelDao (Class type){
        Preconditions.checkArgument(type != null, "type == null");

        this.type = type;
    }

    //-- JdbcModelDao overrides
    @Override
    protected RowMapper<T> getRowMapper() {
        return new EnumModelRowMapper(type);
    }

    @Override
    public List<T> findAll() {
        return template.query("SELECT * FROM " + getTableName(), new Object[]{}, getRowMapper());
    }
}
