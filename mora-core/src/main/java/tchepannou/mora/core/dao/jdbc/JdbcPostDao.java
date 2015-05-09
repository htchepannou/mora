package tchepannou.mora.core.dao.jdbc;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.dao.jdbc.mapper.PostRowMapper;
import tchepannou.mora.core.domain.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class JdbcPostDao extends JdbcModelDao<Post> implements PostDao{
    //--- Attributes
    private static final RowMapper<Post> MAPPER = new PostRowMapper();


    //---  JdbcModelDao overrides
    @Override
    protected PreparedStatementCreator getPreparedStatementCreator(final Post model) {
        final String sql = "INSERT INTO t_post(user_id, space_id, title, summary, content, creation_date, last_update, deleted) VALUES(?,?,?,?,?,?,?,?)";
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, model.getUserId());
                ps.setLong(2, model.getSpaceId());
                ps.setString(3, model.getTitle());
                ps.setString(4, model.getSummary());
                ps.setString(5, model.getContent());
                ps.setTimestamp(6, new Timestamp(model.getCreationDate().getTime()));
                ps.setTimestamp(7, new Timestamp(model.getLastUpdate().getTime()));
                ps.setBoolean(8, false);
                return ps;
            }
        };
    }

    @Override
    protected RowMapper<Post> getRowMapper() {
        return MAPPER;
    }

    @Override
    protected String getTableName() {
        return "t_post";
    }


    //-- PostDao overrides
    @Override
    public List<Long> findIdsPublishedForUser(long userId, int limit, int offset) {
        String sql = "SELECT DISTINCT P.id" +
                " FROM t_post P JOIN t_space S ON P.space_id=S.id" +
                "   JOIN t_member M ON M.space_id=S.id" +
                " WHERE M.user_id=?" +
                "   AND P.deleted=?" +
                " ORDER BY P.last_update DESC" +
                " LIMIT " + limit + " OFFSET " + offset;
        return template.query(sql, new Object[] {userId, false}, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getLong("id");
            }
        });
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE t_post SET title=?, summary=?, content=?, last_update=? WHERE id=?";
        template.update(sql, post.getTitle(), post.getSummary(), post.getContent(), post.getLastUpdate(), post.getId());
    }
}
