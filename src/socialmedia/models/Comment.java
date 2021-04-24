package socialmedia.models;

/**
 * Comment Object
 */
public class Comment extends Post {
    private Post parent;

    /**
     * @param parent comment parent
     * @param id comment id
     * @param commenter comment commenter
     * @param comment the string comment
     */
    public Comment(Post parent, int id, Account commenter, String comment) {
        super(id, comment, commenter);
        this.parent = parent;
    }
}
