package tchepannou.mora.core.domain;

public abstract class EnumModel extends Model{
    //-- Attributes
    private String name;

    //-- Public
    public EnumModel(){
    }
    public EnumModel(long id){
        super(id);
    }
    public EnumModel(long id, String name){
        super(id);
        this.name = name;
    }
    public EnumModel(EnumModel model){
        super(model);

        this.name = model.getName();
    }

    //-- Public
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
