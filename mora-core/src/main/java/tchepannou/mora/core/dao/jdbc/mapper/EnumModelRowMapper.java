package tchepannou.mora.core.dao.jdbc.mapper;

import com.google.common.base.Preconditions;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.EnumModel;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EnumModelRowMapper<T extends EnumModel> implements RowMapper<T> {
    private Class<T> type;

    public EnumModelRowMapper (Class<T> type){
        Preconditions.checkArgument(type != null, "type == null");
        this.type = type;
    }
    @Override
    public T mapRow(ResultSet rs, int i) throws SQLException {
        try {
            T result = type.newInstance();
            result.setId(rs.getLong("id"));
            result.setName(rs.getString("name"));
            return result;
        } catch(InstantiationException | IllegalAccessException e){
            throw new IllegalStateException("Can't instantiate " + type, e);
        }
    }
}
