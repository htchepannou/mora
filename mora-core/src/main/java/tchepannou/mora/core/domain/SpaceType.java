package tchepannou.mora.core.domain;

public class SpaceType extends EnumModel{
    //-- Constructor
    public SpaceType() {
    }

    public SpaceType(long id) {
        super(id);
    }

    public SpaceType(long id, String name) {
        super(id, name);
    }

    public SpaceType(SpaceType model) {
        super(model);
    }
}
