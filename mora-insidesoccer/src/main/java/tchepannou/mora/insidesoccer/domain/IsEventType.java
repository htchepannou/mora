package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.EventType;

public class IsEventType extends EventType {
    public static final long MATCH = 1;
    public static final long TRAINING = 2;
    public static final long EVENT = 3;
    public static final long TOURNAMENT = 4;
    public static final long OTHER = 999;
}
