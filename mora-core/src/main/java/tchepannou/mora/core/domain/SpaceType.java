package tchepannou.mora.core.domain;

public class SpaceType extends Model{
    //-- Attributes
    private String name;


    //-- Constructor
    public SpaceType(){

    }

    public SpaceType(long id){
        super(id);
    }

    public SpaceType(SpaceType type){
        super(type);

        this.name = type.getName();
    }


    //-- Getter/Stter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
