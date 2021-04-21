package socialmedia.models;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String handle;
    private String description;
    private int endorsements;

    public Account(int id, String handle, String description) {
        this.id = id;
        this.handle = handle;
        this.description = description;
        endorsements = 0;
    }

    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    public void setHandle(String newHandle) {
        this.handle = newHandle;
    }

    public int getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getDescription() {
        return description;
    }

    public void endorse() {
        endorsements++;
    }
}
