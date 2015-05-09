package tchepannou.mora.rest.security.dto;

import tchepannou.mora.rest.core.dto.ModelDto;

@Deprecated
public class RoleDto extends ModelDto {
    //-- Public
    private long id;
    private String name;

    //-- Constructor
    public RoleDto (){
    }
    public RoleDto(long id, String name){
        this.id = id;
        this.name = name;
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
