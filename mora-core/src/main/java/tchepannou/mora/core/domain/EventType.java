package tchepannou.mora.core.domain;

public class EventType extends EnumModel{
    public static final long GAME = 100;
    public static final long PRACTICE = 101;
    public static final long OTHER = 999;

    public EventType(long id, String name) {
        super(id, name);
    }

    public EventType(EventType model) {
        super(model);
    }
}
