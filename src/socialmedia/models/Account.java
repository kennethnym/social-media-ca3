package socialmedia.models;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String handle;
    private String description;

    /**
     * The number of endorsements that this {@link Account} has received.
     */
    private int endorsementCount;

    /**
     * The number of posts that this {@link Account} has posted.
     */
    private int postCount;

    public Account(int id, String handle, String description) {
        this.id = id;
        this.handle = handle;
        this.description = description;
        endorsementCount = 0;
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

    public int getEndorsementCount() {
        return endorsementCount;
    }

    /**
     * Endorse this {@link Account}
     */
    public void endorse() {
        endorsementCount++;
    }

    public void increasePostCount() {
        postCount++;
    }

    public void decreasePostCount() {
        postCount--;
    }

    @Override
    public String toString() {
        return "\n"
                + "\n"
                + "ID: " + id + "\n"
                + "Handle: " + handle + "\n"
                + "Description: " + description + "\n"
                + "Post count: " + postCount + "\n"
                + "Endorse count: " + endorsementCount + "\n"
                + "\n";
    }
}
