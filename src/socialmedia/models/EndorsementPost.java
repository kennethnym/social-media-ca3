package socialmedia.models;

public class EndorsementPost extends Post {
    private Post originalPost;

    public EndorsementPost(Post originalPost, int id, Account endorser) {
        super(id, "EP@" + originalPost.getAuthor().getHandle() + ": " + originalPost.getMessage(), endorser);
        this.originalPost = originalPost;
        originalPost.endorse();
        originalPost.getAuthor().endorse();
    }
}
