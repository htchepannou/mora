package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Media;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaRowMapper implements RowMapper<Media> {
    @Override
    public Media mapRow(ResultSet rs, int i) throws SQLException {
        Media result = new Media();

        result.setId(rs.getLong("id"));
        result.setUserId(rs.getLong("user_id"));
        result.setSpaceId(rs.getLong("space_id"));
        result.setDeleted(rs.getBoolean("deleted"));
        result.setTitle(rs.getString("title"));
        result.setDescription(rs.getString("description"));
        result.setContentType(rs.getString("content_type"));
        result.setUrl(rs.getString("url"));
        result.setThumbnailUrl(rs.getString("thumbnail_url"));
        result.setImageUrl(rs.getString("image_url"));
        result.setSize(rs.getLong("size"));
        result.setOembed(rs.getBoolean("oembed"));
        result.setCreationDate(rs.getTimestamp("creation_date"));
        result.setLastUpdate(rs.getTimestamp("last_update"));

        return result;
    }
}
