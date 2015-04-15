package tchepannou.mora.core.domain;

public class Role extends Model{
    public static final String AUTHORITY_ROLE_USER = "ROLE_USER";
    public static final String AUTHORITY_ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    //-- Attributes
    private String name;

    //-- Public
    public Role (){
    }
    public Role (long id){
        super(id);
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
