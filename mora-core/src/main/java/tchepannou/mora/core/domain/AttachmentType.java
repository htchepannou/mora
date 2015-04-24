package tchepannou.mora.core.domain;

public class AttachmentType extends EnumModel{
    //-- Static
    public static final long POST = 1;

    //-- Constructor
    public AttachmentType() {
    }

    public AttachmentType(long id, String name) {
        super(id, name);
    }
}
