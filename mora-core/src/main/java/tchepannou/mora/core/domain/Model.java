package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    //-- Public
    public static List<Long> idList(Collection<? extends Model> models){
        return models.stream()
                .map(Model::getId)
                .collect(Collectors.toList());
    }

    public static <T extends Model> Map<Long, T> map(Collection<T> spaces){
        return spaces.stream()
                .collect(Collectors.toMap(space -> space.getId(), space -> space));
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
        long key = getId();
        return key > 0 ? Long.hashCode(key) : HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
