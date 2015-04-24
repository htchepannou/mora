package tchepannou.mora.core.domain;

public class MediaType extends EnumModel{
    public static final long IMAGE = 1;
    public static final long VIDEO = 2;
    public static final long AUDIO = 3;
    public static final long UNKNOWN = 999;

    public MediaType() {
    }

    public MediaType(long id, String name) {
        super(id, name);
    }
}
