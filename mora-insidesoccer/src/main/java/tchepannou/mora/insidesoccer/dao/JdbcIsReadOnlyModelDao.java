package tchepannou.mora.insidesoccer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import tchepannou.mora.core.dao.jdbc.JdbcReadOnlyModelDao;
import tchepannou.mora.core.domain.Model;

import javax.sql.DataSource;

public abstract class JdbcIsReadOnlyModelDao<T extends Model> extends JdbcReadOnlyModelDao<T> {
    //-- Abstract
    protected abstract String getIdColumn();


    //-- Public
    public T findById (long id){
        String sql = String.format("SELECT * FROM %s WHERE %s=?", getTableName(), getIdColumn());
        return findSingle(sql, new Object[]{id});
    }

    //-- Setter
    @Autowired
    @Required
    public void setDatasource(DataSource ds){
        this.template = new JdbcTemplate(ds);
    }
}
