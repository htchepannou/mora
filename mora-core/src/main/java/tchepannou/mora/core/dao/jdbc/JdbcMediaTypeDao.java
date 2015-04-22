package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.MediaTypeDao;
import tchepannou.mora.core.domain.MediaType;

public class JdbcMediaTypeDao extends JdbcEnumModelDao<MediaType> implements MediaTypeDao{
    //-- Constructor
    public JdbcMediaTypeDao() {
        super(MediaType.class);
    }

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_media_type";
    }
}
