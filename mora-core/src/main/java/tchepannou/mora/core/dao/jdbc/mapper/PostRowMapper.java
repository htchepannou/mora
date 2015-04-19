package tchepannou.mora.core.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.domain.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post>{
    @Override
    public Post mapRow(ResultSet rs, int i) throws SQLException {
        Post result = new Post();
        result.setId(rs.getLong("id"));
        result.setUserId(rs.getLong("user_id"));
        result.setSpaceId(rs.getLong("space_id"));
        result.setDeleted(rs.getBoolean("deleted"));
        result.setTitle(rs.getString("title"));
        result.setSummary(rs.getString("summary"));
        result.setContent(rs.getString("content"));
        result.setCreationDate(rs.getTimestamp("creation_date"));
        result.setLastUpdate(rs.getTimestamp("last_update"));
        return result;
    }
}
