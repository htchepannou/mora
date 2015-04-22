package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.dao.jdbc.mapper.MediaRowMapper;
import tchepannou.mora.core.domain.Media;

public class JdbcMediaDao extends JdbcReadOnlyModelDao<Media> implements MediaDao{
    private static final RowMapper<Media> MAPPER = new MediaRowMapper();

    @Override
    protected String getTableName() {
        return "t_media";
    }

    @Override
    protected RowMapper<Media> getRowMapper() {
        return MAPPER;
    }
}
