package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.Media;

import java.time.LocalDate;
import java.util.Calendar;

public class Node extends AbstractNode{
    //-- Attribute
    public static final long TYPE_ATTACHMENT = 100;

    private long channelId;

    //--- Constructor
    public Node(){
    }
    public Node(long id){
        super(id);
    }

    //-- Public
    public void toMedia(Media media) {
        media.setId(this.getId());
        media.setLastUpdate(this.getDate());
        media.setUserId(this.ownerId);
        media.setDeleted(this.deleted);
        media.setSpaceId(this.channelId);
    }

    public void toEvent(Event event){
        event.setId(getId());
        event.setLastUpdate(this.getDate());
        event.setUserId(this.ownerId);
        event.setDeleted(this.deleted);
        event.setSpaceId(this.channelId);

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getDate());
        event.setStartDate(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH)));
        event.setTimezone(null);
    }

    //-- Getter/Setter
    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
