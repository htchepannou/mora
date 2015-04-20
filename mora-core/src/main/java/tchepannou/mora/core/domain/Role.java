package tchepannou.mora.core.domain;

public class Role extends EnumModel{
    //-- Attributes
    public static final String AUTHORITY_ROLE_USER = "ROLE_USER";
    public static final String AUTHORITY_ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    //-- Constructors
    public Role() {
    }

    public Role(long id) {
        super(id);
    }

    public Role(long id, String name) {
        super(id, name);
    }

    public Role(Role model) {
        super(model);
    }
}
