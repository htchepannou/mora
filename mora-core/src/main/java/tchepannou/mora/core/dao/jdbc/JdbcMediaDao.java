package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.dao.jdbc.mapper.MediaRowMapper;
import tchepannou.mora.core.domain.Media;

import java.util.List;

public class JdbcMediaDao extends JdbcReadOnlyModelDao<Media> implements MediaDao{
    private static final RowMapper<Media> MAPPER = new MediaRowMapper();

    //-- JdbcReadOnlyModelDao overrides
    @Override
    protected String getTableName() {
        return "t_media";
    }

    @Override
    protected RowMapper<Media> getRowMapper() {
        return MAPPER;
    }


    //-- MediaDao overrides
    @Override
    public List<Media> findByOwnerByAttachmentType(long ownerId, long typeId){
        String sql = "SELECT M.* FROM t_media M JOIN t_attachment A ON M.id=A.media_id WHERE A.owner_id=? AND A.type_id=? AND M.deleted=? ORDER BY A.rank";

        return template.query(sql.toString(), new Object[]{ownerId, typeId, false}, getRowMapper());
    }
}
