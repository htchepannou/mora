package tchepannou.mora.core.dao.jdbc;

import tchepannou.mora.core.dao.AttachmentTypeDao;
import tchepannou.mora.core.domain.AttachmentType;

public class JdbcAttachmentTypeDao extends JdbcEnumModelDao<AttachmentType> implements AttachmentTypeDao{
    //-- Constructor
    public JdbcAttachmentTypeDao() {
        super(AttachmentType.class);
    }

    //-- JdbcModelDao overrides
    @Override
    protected String getTableName() {
        return "t_attachment_type";
    }
}
