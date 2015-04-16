package tchepannou.mora.insidesoccer.domain;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Model;

public class PartyAttribute extends Model {
    //-- Attributes
    private long partyId;
    private String name;
    private String value;

    public PartyAttribute(){

    }
    public PartyAttribute(long id, Party party, String name, String value){
        super(id);

        Preconditions.checkArgument(party != null, "party == null");
        Preconditions.checkArgument(party.getId()>0, "party.id <= 0");

        this.partyId = party.getId();
        this.name = name;
        this.value = value;
    }

    //-- Getter/Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
