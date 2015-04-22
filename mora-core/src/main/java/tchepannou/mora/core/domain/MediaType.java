package tchepannou.mora.core.domain;

public class MediaType extends EnumModel{
    public MediaType() {
    }

    public MediaType(long id) {
        super(id);
    }

    public MediaType(long id, String name) {
        super(id, name);
    }

    public MediaType(EnumModel model) {
        super(model);
    }
}
