package tchepannou.mora.core.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tchepannou.mora.core.domain.Model;

import javax.sql.DataSource;

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

    //-- Setter
    @Autowired
    @Required
    public void setDatasource(DataSource ds){
        this.template = new JdbcTemplate(ds);
    }
}
