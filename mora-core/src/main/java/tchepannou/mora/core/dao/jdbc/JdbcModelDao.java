package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tchepannou.mora.core.domain.Model;

public abstract class JdbcModelDao<T extends Model> extends JdbcReadOnlyModelDao<T>{
    //-- Abstract
    protected abstract PreparedStatementCreator getPreparedStatementCreator(T model);


    //-- Public
    public final long create(T model){
        KeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator ps = getPreparedStatementCreator(model);
        template.update(ps, holder);

        long id = holder.getKey().longValue();
        model.setId(id);
        return id;
    }
}
