package tchepannou.mora.rest.space.dto;

import tchepannou.mora.core.domain.Space;

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
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }
}
