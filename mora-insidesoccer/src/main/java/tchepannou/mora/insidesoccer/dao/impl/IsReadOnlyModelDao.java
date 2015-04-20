package tchepannou.mora.insidesoccer.dao.impl;

import tchepannou.mora.core.dao.jdbc.JdbcReadOnlyModelDao;
import tchepannou.mora.core.domain.Model;

public abstract class IsReadOnlyModelDao<T extends Model> extends JdbcReadOnlyModelDao<T> {
    //-- Abstract
    @Override
    protected abstract String getIdColumn();


    //-- Public
    @Override
    public T findById (long id){
        String sql = String.format("SELECT * FROM %s WHERE %s=?", getTableName(), getIdColumn());
        return findSingle(sql, new Object[]{id});
    }




    public void update(T token) {
        throw new UnsupportedOperationException();
    }

    public long create(T token) {
        throw new UnsupportedOperationException();
    }

    public void delete(T token) {
        throw new UnsupportedOperationException();
    }
}
