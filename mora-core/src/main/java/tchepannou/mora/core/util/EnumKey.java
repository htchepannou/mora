package tchepannou.mora.core.util;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public class EnumKey implements Serializable {
    private Class type;
    private Object[] params;

    public EnumKey(Class type, Object...params){
        this.type = type;
        this.params = params;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return type.getSimpleName() + "[" + StringUtils.arrayToCommaDelimitedString(params) + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }
}
