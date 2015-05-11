package tchepannou.mora.core.domain;

public class EventType extends EnumModel{
    public EventType(){
    }
    public EventType(long id){
        super(id);
    }

    public EventType(long id, String name) {
        super(id, name);
    }

    public EventType(EventType model) {
        super(model);
    }
}
