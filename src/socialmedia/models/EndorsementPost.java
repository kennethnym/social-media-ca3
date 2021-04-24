package socialmedia.models;

/**
 * Endorsement Posts
 */
public class EndorsementPost extends Post {
    private Post originalPost;

    /**
     * @param originalPost The original post
     * @param id the post id
     * @param endorser endorser account
     */
    public EndorsementPost(Post originalPost, int id, Account endorser) {
        super(id, "EP@" + originalPost.getAuthor().getHandle() + ": " + originalPost.getMessage(), endorser);
        this.originalPost = originalPost;
        originalPost.endorse();
        originalPost.getAuthor().endorse();
    }
}
