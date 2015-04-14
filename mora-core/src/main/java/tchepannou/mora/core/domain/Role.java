package tchepannou.mora.core.domain;

public class Role extends Model{
    //-- Attributes
    public String name;

    //-- Public
    public Role (){
    }
    public Role (long id, String name){
        super(id);
        this.name = name;
    }

    //-- Public
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
