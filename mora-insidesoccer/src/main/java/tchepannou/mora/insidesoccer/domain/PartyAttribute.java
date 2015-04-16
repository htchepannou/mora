package tchepannou.mora.insidesoccer.domain;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Model;
import tchepannou.mora.core.domain.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PartyAttribute extends Model {
    //-- Attributes
    public static final String EMAIL = "email";
    public static final String USERNAME = "uname";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

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

    //-- Public
    public static void toUser(Collection<PartyAttribute> attributes, User user){
        for (PartyAttribute attr : attributes){
            if (attr.getPartyId() != user.getId()){
                continue;
            }
            String name = attr.getName();
            String value = attr.getValue();
            if (EMAIL.equalsIgnoreCase(name)){
                user.setEmail(value);
            } else if (USERNAME.equalsIgnoreCase(name)){
                user.setUsername(value);
            } else if (FIRST_NAME.equalsIgnoreCase(name)){
                user.setFirstName(value);
            } else if (LAST_NAME.equalsIgnoreCase(name)){
                user.setLastName(value);
            }
        }
    }
    public static Set<Long> toPartyIdSet(Collection<PartyAttribute> attributes){
        Set<Long> result = new HashSet<>();
        for (PartyAttribute attribute : attributes){
            result.add(attribute.getPartyId());
        }
        return result;
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