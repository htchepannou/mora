package tchepannou.mora.core.domain;

public class AttachmentType extends EnumModel{
    public AttachmentType() {
    }

    public AttachmentType(long id) {
        super(id);
    }

    public AttachmentType(long id, String name) {
        super(id, name);
    }

    public AttachmentType(AttachmentType model) {
        super(model);
    }
}
