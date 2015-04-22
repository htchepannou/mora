package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.MediaProviderDao;
import tchepannou.mora.core.domain.MediaProvider;

public class JdbcMediaProviderDao extends JdbcEnumModelDao<MediaProvider> implements MediaProviderDao{
    //-- Constructor
    public JdbcMediaProviderDao() {
        super(MediaProvider.class);
    }

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_media_provider";
    }
}
