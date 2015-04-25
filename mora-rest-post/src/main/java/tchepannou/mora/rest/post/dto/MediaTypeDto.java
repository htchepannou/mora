package tchepannou.mora.rest.post.dto;

import tchepannou.mora.rest.core.dto.ModelDto;

public class MediaTypeDto extends ModelDto{
    private long id;
    private String name;

    //-- Constructor
    public MediaTypeDto(long id, String name){
        this.id = id;
        this.name = name;
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
