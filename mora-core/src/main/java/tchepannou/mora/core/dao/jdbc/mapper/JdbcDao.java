package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

public abstract class JdbcDao {
    //-- Attributes
    protected JdbcTemplate template;


    //-- Protected
    protected StringBuilder whereAnd(StringBuilder sql){
        return sql.append(" AND ");
    }

    protected StringBuilder whereIn(StringBuilder sql, String name, Collection values, List params){
        int i=0;
        if (values.size() == 1){
            sql.append(name).append("=?");
            params.add(values.iterator().next());
        } else {
            sql.append(name).append(" IN (");
            for (Object value : values) {
                if (i++ > 0) {
                    sql.append(',');
                }
                sql.append('?');
                params.add(value);
            }
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
