package tchepannou.mora.core.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.SoftDeleteSupport;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class JdbcReadOnlyModelDao<T extends Model> {
    //-- Attributes
    protected JdbcTemplate template;


    //-- Abstract
    protected abstract String getTableName ();

    protected abstract RowMapper<T> getRowMapper();


    //-- Public
    public T findById (long id){
        String sql = String.format("SELECT * FROM %s WHERE %s=?", getTableName(), getIdColumn());
        return findSingle(sql, new Object[] {id});
    }

    public List<T> findByIds (Collection<Long> ids){
        StringBuilder sql = new StringBuilder(String.format("SELECT * FROM %s WHERE ", getTableName()));

        List params = new ArrayList<>();
        whereIn(sql, getIdColumn(), ids, params);

        return template.query(sql.toString(), params.toArray(), getRowMapper());
    }

    //-- Protected
    protected  String getIdColumn(){
        return "id";
    }

    protected T findSingle (String sql, Object[] params){
        try {
            T result = template.queryForObject(sql, params, getRowMapper());
            if ((result instanceof SoftDeleteSupport) && ((SoftDeleteSupport) result).isDeleted()){
                return null;
            }
            return result;
        } catch (org.springframework.dao.EmptyResultDataAccessException e){ // NOSONAR - Each exception intentionally
            return null;
        }
    }

    protected StringBuilder whereIn(StringBuilder sql, String name, Collection values, List params){
        int i=0;
        sql.append(name).append(" IN (");
        for (Object value : values){
            if (i++ > 0){
                sql.append(',');
            }
            sql.append('?');
            params.add(value);
        }
        sql.append(")");
        return sql;
    }

    //-- Setter
    @Autowired
    @Required
    public void setDatasource(DataSource ds){
        this.template = new JdbcTemplate(ds);
    }



}
