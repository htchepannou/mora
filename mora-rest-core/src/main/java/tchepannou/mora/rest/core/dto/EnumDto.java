package tchepannou.mora.rest.core.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.EnumModel;

public class EnumDto extends ModelDto {
    //-- Attributes
    private long id;
    private String name;


    //-- Constructor
    public EnumDto (){
    }
    private EnumDto(Builder builder){
        this.id = builder.enumModel.getId();
        this.name = builder.enumModel.getName();
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //-- Builder
    public static class Builder {
        private EnumModel enumModel;

        public EnumDto build (){
            Preconditions.checkState(enumModel != null, "enumModel == null");

            return new EnumDto(this);
        }

        public Builder withEnum (EnumModel enumModel){
            this.enumModel = enumModel;
            return this;
        }
    }
}
