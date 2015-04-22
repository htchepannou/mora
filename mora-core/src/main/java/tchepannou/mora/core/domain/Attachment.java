package tchepannou.mora.core.domain;

public class Attachment extends Model{
    //-- Attributes
    private long ownerId;
    private long mediaId;
    private long typeId;
    private int rank;

    //-- Constructor
    public Attachment(){
    }
    public Attachment(long id, Model owner, Media media, AttachmentType type){
        super(id);

        ownerId = owner.getId();
        mediaId = media.getId();
        typeId = type.getId();
    }

    //-- Getter/Setter
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
