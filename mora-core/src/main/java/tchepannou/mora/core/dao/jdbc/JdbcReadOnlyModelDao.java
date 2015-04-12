package tchepannou.mora.core.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.SoftDeleteSupport;

import javax.sql.DataSource;

public abstract class JdbcReadOnlyModelDao<T extends Model> {
    //-- Attributes
    protected JdbcTemplate template;


    //-- Abstract
    protected abstract String getTableName ();

    protected abstract RowMapper<T> getRowMapper();


    //-- Public
    public final T findById (long id){
        try {
            String sql = String.format("SELECT * FROM %s WHERE id=?", getTableName());
            T result = template.queryForObject(sql, new Object[] {id}, getRowMapper());
            if ((result instanceof SoftDeleteSupport) && ((SoftDeleteSupport) result).isDeleted()){
                return null;
            }
            return result;
        } catch (org.springframework.dao.EmptyResultDataAccessException e){ // NOSONAR - Each exception intentionally
            return null;
        }
    }

    //-- Setter
    @Autowired
    @Required
    public void setDatasource(DataSource ds){
        this.template = new JdbcTemplate(ds);
    }
}