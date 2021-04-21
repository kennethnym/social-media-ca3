package socialmedia.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    private int id;

    private String message;

    private Account author;

    private ArrayList<Post> comments;

    private int endorsements;

    public Post(int id, String message, Account author) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.endorsements = 0;
        comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Account getAuthor() {
        return author;
    }

    public boolean hasComments() {
        return comments.size() > 0;
    }

    public ArrayList<Post> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public int getEndorsements() {
        return endorsements;
    }

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
