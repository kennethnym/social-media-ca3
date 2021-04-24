package socialmedia.models;

import java.io.Serializable;

/**
 * Account Object
 */
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

    /**
     * @param id account id
     * @param handle account handle
     * @param description account description
     */
    public Account(int id, String handle, String description) {
        this.id = id;
        this.handle = handle;
        this.description = description;
        endorsementCount = 0;
    }

    /**
     * @param newDesc sets description
     */
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * @param newHandle new handle
     */
    public void setHandle(String newHandle) {
        this.handle = newHandle;
    }

    /**
     * @return returns account id
     */
    public int getId() {
        return id;
    }

    /**
     * @return returns account handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @return returns account description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return returns endorsement count
     */
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
