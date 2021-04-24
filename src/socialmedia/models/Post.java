package socialmedia.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Post object
 */
public class Post implements Serializable {
    private int id;

    private String message;

    private Account author;

    private ArrayList<Post> comments;

    private int endorsements;

    /**
     * @param id Post id
     * @param message post message
     * @param author post author
     */
    public Post(int id, String message, Account author) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.endorsements = 0;
        comments = new ArrayList<>();
    }

    /**
     * @return returns post id
     */
    public int getId() {
        return id;
    }

    /**
     * @return returns message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return returns author
     */
    public Account getAuthor() {
        return author;
    }

    /**
     * @return checks if there is comments
     */
    public boolean hasComments() {
        return comments.size() > 0;
    }

    /**
     * @return returns comments
     */
    public ArrayList<Post> getComments() {
        return comments;
    }

    /**
     * @param comment comment to add
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * @return returns endorsements
     */
    public int getEndorsements() {
        return endorsements;
    }

    /**
     * increase endorsement counter
     */
    protected void endorse() {
        endorsements++;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Account: " + author.getHandle() + "\n" +
                "No. endorsements: " + endorsements + " | No. comments: " + comments.size() + "\n" +
                message;
    }
}
