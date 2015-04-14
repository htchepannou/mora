package tchepannou.mora.rest.space.dto;

import tchepannou.mora.core.domain.Space;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateSpaceDto extends SaveSpaceDto{
    //-- Attributes
    private long typeId;


    //-- SaveSpaceDto overrides


    @Override
    public void toSpace(Space space) {
        super.toSpace(space);

        space.setTypeId(typeId);
    }

    //-- Getter/Setter
    @NotNull
    @Size (min=1, max=100)
    public String getName() {
        return super.getName();
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }
}
