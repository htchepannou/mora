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
    public SpaceType(long id, String name){
        super(id);
        this.name = name;
    }


    //-- Getter/Stter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
