package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public abstract class Model implements Serializable {
    //-- Attriubtes
    private long id;

    //-- Constructor
    public Model (){

    }
    public Model(long id){
        this.id = id;
    }
    public Model(Model model){
        Preconditions.checkArgument(model != null, "model==null");

        this.id = model.getId();
    }

    //-- Getter/Setter
    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final boolean isTransient (){
        return id == 0;
    }

    //-- Object overrides
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        Serializable id = getId();
        if (id != null){
            return id.hashCode();
        } else {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
