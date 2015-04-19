package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Model;

public class Attribute extends Model {
    //-- Attributes
    private String name;
    private String value;

    public Attribute(){
    }
    public Attribute(long id, String name, String value){
        super(id);

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
