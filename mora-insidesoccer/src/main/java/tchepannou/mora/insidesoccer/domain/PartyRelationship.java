package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PartyRelationship extends Model {
    //-- Attributes
    public static final long TYPE_PARENT = 1;
    public static final long TYPE_TEAM  = 3;
    public static final long TYPE_CLUB   = 4;
    public static final long TYPE_MEMBER = 10;

    private long sourceId;
    private long destinationId;
    private long typeId;
    private long rank;
    private String qualifier;

    //-- Constructor
    public PartyRelationship(){

    }
    public PartyRelationship(long sourceId, long destinationId, long typeId){
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.typeId = typeId;
    }
    public PartyRelationship(long sourceId, long destinationId, long typeId, String qualifier){
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.typeId = typeId;
        this.qualifier = qualifier;
    }

    //-- Public
    public static Set<Long> destinationIds (Collection<PartyRelationship> rels){
        Set<Long> result = new HashSet<>();
        for (PartyRelationship rel : rels){
            result.add(rel.destinationId);
        }
        return result;
    }

    //-- Getter/Setter
    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }
}
