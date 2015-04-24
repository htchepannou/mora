package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.jdbc.mapper.JdbcDao;
import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.SoftDeleteSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class JdbcReadOnlyModelDao<T extends Model> extends JdbcDao{
    //-- Abstract
    protected abstract String getTableName ();

    protected abstract RowMapper<T> getRowMapper();


    //-- Public
    public T findById (long id){
        String sql = String.format("SELECT * FROM %s WHERE %s=?", getTableName(), getIdColumn());
        return findSingle(sql, new Object[] {id});
    }

    public List<T> findByIds (Collection<Long> ids){
        /* query */
        StringBuilder sql = new StringBuilder(String.format("SELECT * FROM %s WHERE ", getTableName()));
        List params = new ArrayList<>();
        if (!ids.isEmpty()) {
            whereIn(sql, getIdColumn(), ids, params);
        }

        List<T> lst = template.query(sql.toString(), params.toArray(), getRowMapper());

        /* filter deleted */
        List<T> result = new ArrayList<>();
        for (T item : lst){
            if (!(item instanceof SoftDeleteSupport) || !((SoftDeleteSupport) item).isDeleted()){
                result.add(item);
            }
        }
        return result;
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
}
