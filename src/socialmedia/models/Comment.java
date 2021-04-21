package socialmedia.models;

public class Comment extends Post {
    private Post parent;

    public Comment(Post parent, int id, Account commenter, String comment) {
        super(id, comment, commenter);
        this.parent = parent;
    }
}
