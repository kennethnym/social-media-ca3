package socialmedia.models;

public class Account {

    private int id;
    private String handle;
    private String description;

    public Account(int id, String handle, String description){
        this.id = id;
        this.handle = handle;
        this.description = description;
    }

    public void setDescription(String newDesc){ this.description = newDesc; }

    public void setHandle(String newHandle){
        this.handle = newHandle;
    }

    public int getId(){
        return id;
    }

    public String getHandle(){
        return handle;
    }

    public String getDescription(){
        return description;
    }

}
