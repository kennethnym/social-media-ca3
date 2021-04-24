package socialmedia;

import java.io.*;
import java.util.ArrayList;

import socialmedia.exceptions.*;
import socialmedia.models.Account;
import socialmedia.models.Comment;
import socialmedia.models.EndorsementPost;
import socialmedia.models.Post;

import java.util.stream.Collectors;

public class SocialMediaPlatformImpl implements SocialMediaPlatform, Serializable {
    private static final int MAX_COMMENT_LENGTH = 100;

    private ArrayList<Account> accounts = new ArrayList<>();

    private ArrayList<Post> posts = new ArrayList<>();

    /**
     * Indicates what the ID of the next new post should be.
     */
    private int postId = 0;

    /**
     * Create an account.
     *
     * @param handle the accounts handle as input
     * @return it returns the accountID
     * @throws IllegalHandleException if the handle has a space
     * @throws InvalidHandleException if the handle exists already
     */
    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

        //IllegalHandleException
        for (int j = 0; j < this.accounts.size(); j++) {
            if (handle.equals(accounts.get(j).getHandle())) {
                throw new IllegalHandleException();
            }
        }

        //InvalidHandleException
        for (int i = 0; i < this.accounts.size(); i++) {
            if (handle.contains(" ")) {
                throw new InvalidHandleException();
            }
            if (handle != null && !handle.isEmpty()) {
                throw new InvalidHandleException();
            }
        }

        //Create account code:
        int arrayLength = this.accounts.size();
        int accountID = arrayLength + 1;
        this.accounts.add(new Account(accountID, handle, "empty"));
        return accountID;
    }

    /**
     * Create an account with the given handle
     *
     * @param handle      account's handle.
     * @param description account's description.
     * @return returns the accountID
     * @throws IllegalHandleException if the handle is illegal
     * @throws InvalidHandleException If the handle is inavlid handle
     */
    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        //IllegalHandleException
        for (int j = 0; j < this.accounts.size(); j++) {
            if (handle.equals(accounts.get(j).getHandle())) {
                throw new IllegalHandleException();
            }
        }

        //InvalidHandleException
        for (int i = 0; i < this.accounts.size(); i++) {
            if (handle.contains(" ")) {
                throw new InvalidHandleException();
            }
            if (handle != null && !handle.isEmpty()) {
                throw new InvalidHandleException();
            }
        }

        //Create new account code
        int arrayLength = this.accounts.size();
        int accountID = arrayLength + 1;
        this.accounts.add(new Account(accountID, handle, description));

        return accountID;
    }

    /**
     * Remove the account
     *
     * @param id ID of the account.
     * @throws AccountIDNotRecognisedException account id not recognized excpetion
     */
    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        //AccountIDNotRecognisedException
        for (int i = 0; i < accounts.size(); i++) {
            if (!accounts.contains(accounts.get(i).getId())) {
                throw new AccountIDNotRecognisedException();
            }

        }

        // Loops through and checks if there is the same id
        for (int i = 0; i < this.accounts.size(); i++) {
            if (id == this.accounts.get(i).getId()) {
                this.accounts.remove(i);
            }
        }
    }

    /**
     * Remove the account
     *
     * @param handle account's handle.
     * @throws HandleNotRecognisedException handle not recongized excpetion thrown
     */
    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        //HandleNotRecognisedException
        for (int i = 0; i < accounts.size(); i++) {
            if (!handle.equals(this.accounts.get(i).getHandle())) {
                throw new HandleNotRecognisedException();
            }

        }
        //Loops through and removes account
        for (int i = 0; i < this.accounts.size(); i++) {
            if (handle.equals(this.accounts.get(i).getHandle())) {
                accounts.remove(i);
            }
        }
    }

    /**
     * Change the handle of the account.
     *
     * @param oldHandle account's old handle.
     * @param newHandle account's new handle.
     * @throws HandleNotRecognisedException handle not recongized excpetion
     * @throws IllegalHandleException       illegal handle expection thrown
     * @throws InvalidHandleException       illegal handle exception
     */
    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        // Loops through and checks the handles, if true then set handle with new handle
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getHandle().equals(oldHandle)) {
                this.accounts.get(i).setHandle(newHandle);
            }
        }
    }

    /**
     * Update the description of the given account
     *
     * @param handle      handle to identify the account.
     * @param description new text for description.
     * @throws HandleNotRecognisedException handle not recognised expection
     */
    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        //HandleNotRecognisedException
        for (int i = 0; i < accounts.size(); i++) {
            if (!handle.equals(this.accounts.get(i).getHandle())) {
                throw new HandleNotRecognisedException();
            }
        }

        for (int i = 0; i < accounts.size(); i++) {
            //Loops through and checks if the handle is the same, if it is update the description.
            if (accounts.get(i).getHandle().equals(handle)) {
                accounts.get(i).setDescription(description);
            }
        }
    }

    /**
     * Show an account
     *
     * @param handle handle to identify the account.
     * @return returns a string
     * @throws HandleNotRecognisedException Handle Not Recognised Exception
     */
    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        final var account = findAccountByHandle(handle);

        if (account == null)
            // no account was found with the given handle
            throw new HandleNotRecognisedException();

        return account.toString();
    }

    /**
     * Create a new post
     *
     * @param handle  handle to identify the account.
     * @param message post message.
     * @return The ID of the created post.
     * @throws HandleNotRecognisedException Handle Not Recognised Exception
     * @throws InvalidPostException         Thrown when the post is invalid
     */
    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        final var account = findAccountByHandle(handle);

        //HandleNotRecognisedException
        if (account == null)
            throw new HandleNotRecognisedException();

        //Creates new post object and appends it
        final var newPost = new Post(postId++, message, account);
        posts.add(newPost);

        return newPost.getId();
    }

    /**
     * Endorse a post
     *
     * @param handle of the account endorsing a post.
     * @param id     of the post being endorsed.
     * @return The ID of the created endorsement post.
     * @throws HandleNotRecognisedException Handle Not Recognized
     * @throws PostIDNotRecognisedException Post ID Not Recognized
     * @throws NotActionablePostException   The post is not actionable.
     */
    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        final var originalPost = findPostById(id);
        final var endorser = findAccountByHandle(handle);

        //PostIDNotRecognisedException
        if (originalPost == null)
            // no post was found with the given id
            throw new PostIDNotRecognisedException();

        //PostIDNotRecognisedException
        if (endorser == null)
            // no account was found with the given handle
            throw new HandleNotRecognisedException();

        //PostIDNotRecognisedException
        if (originalPost instanceof EndorsementPost)
            // the post found is an endorsement post
            throw new NotActionablePostException();

        //Creates new endorsementPost and appends it
        final var endorsementPost = new EndorsementPost(originalPost, postId++, endorser);

        posts.add(endorsementPost);

        return endorsementPost.getId();
    }

    /**
     * Comment on the given post.
     *
     * @param handle  of the account commenting a post.
     * @param id      of the post being commented.
     * @param message the comment post message.
     * @return The ID of the created comment.
     * @throws HandleNotRecognisedException Handle is not recognized
     * @throws PostIDNotRecognisedException Post id is not recognized
     * @throws NotActionablePostException   The post is not actionable
     * @throws InvalidPostException         The post is invalid
     */
    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        final var parent = findPostById(id);
        final var commenter = findAccountByHandle(handle);


        //PostIDNotRecognisedException
        if (parent == null)
            // no post was found with the given id
            throw new PostIDNotRecognisedException();

        //HandleNotRecognisedException
        if (commenter == null)
            // no account was found with the given handle
            throw new HandleNotRecognisedException();

        //NotActionablePostException
        if (parent instanceof EndorsementPost)
            // the post found is an endorsement post
            throw new NotActionablePostException();

        //InvalidPostException
        if (message.isEmpty() || message.length() > MAX_COMMENT_LENGTH)
            // the given message is empty or the message is too long
            throw new InvalidPostException();

        //Creates new comment object and appends the comment
        final var comment = new Comment(parent, postId++, commenter, message);

        posts.add(comment);
        parent.addComment(comment);

        return comment.getId();
    }

    /**
     * Delete the post with the given ID
     *
     * @param id ID of post to be removed.
     * @throws PostIDNotRecognisedException The given ID is not recognized.
     */
    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        final var post = findPostById(id);

        //PostIDNotRecognisedException
        if (post == null)
            // no post was found with the given id
            throw new PostIDNotRecognisedException();

        posts.remove(post);
    }

    /**
     * Show details of a post with the given ID.
     *
     * @param id of the post to be shown.
     * @return The details of the post in string.
     * @throws PostIDNotRecognisedException
     */
    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        final var post = findPostById(id);

        //PostIDNotRecognisedException
        if (post == null)
            // no post was found with the given id
            throw new PostIDNotRecognisedException();

        return post.toString();
    }

    /**
     * Show details of a post and its childre nposts.
     *
     * @param id of the post to be shown.
     * @return The details in string.
     * @throws PostIDNotRecognisedException
     * @throws NotActionablePostException
     */
    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        final var post = findPostById(id);

        //PostIDNotRecognisedException
        if (post == null)
            // no post was found with the given id
            throw new PostIDNotRecognisedException();

        //NotActionablePostException
        if (post instanceof EndorsementPost)
            // the post found is an endorsement post
            throw new NotActionablePostException();

        //Creates post children details using string builder
        final var stringBuilder = new StringBuilder();

        showPostChildrenDetails(post, 0, stringBuilder);

        return stringBuilder;
    }

    /**
     * Recursive entry of showPostChildrenDetails. params are used to track states of recursion.
     *
     * @param parent  The current parent {@link Post} of children.
     * @param level   How deep the recursion is. Used for calculating indentation.
     * @param builder The instance of {@link StringBuilder} for storing the string representation of post-children details.
     * @return A {@link StringBuilder} with (partial) post children details.
     */
    private StringBuilder showPostChildrenDetails(Post parent, int level, StringBuilder builder) {
        final var indentation = " ".repeat(4 * level);

        //Checks the level of the object and creates a string
        if (level == 0) {
            builder.append(parent.toString()).append("\n");
        } else {
            final var lines = parent.toString().split("\n");
            builder.append(lines[0]).append("\n");
            for (int i = 1; i < 4; i++) {
                builder.append(indentation).append(lines[i]).append("\n");
            }
        }

        // only do a recursive call if this parent has comments aka children posts.
        if (parent.hasComments()) {
            builder.append(indentation).append("|\n");
            for (final var comment : parent.getComments()) {
                // for each comment, print out their details (they will be treated as a parent)
                builder.append(indentation).append("| > ");
                showPostChildrenDetails(comment, level + 1, builder);
            }
        }

        return builder;
    }

    /**
     * @return the most endorsed post in social media.
     */
    @Override
    public int getMostEndorsedPost() {
        //Returns the most endorsed post
        // the last most endorsed post found.
        Post mostEndorsedPost = null;
        for (final var post : posts) {
            // if the endorsement count of this post is larger than the one we found previously
            // replace that with this post for future comparison
            if (mostEndorsedPost == null || mostEndorsedPost.getEndorsements() < post.getEndorsements()) {
                mostEndorsedPost = post;
            }
        }
        return mostEndorsedPost.getId();
    }

    /**
     * @return the most endorsed account in social media.
     */
    @Override
    public int getMostEndorsedAccount() {
        // the last most endorsed account found.
        Account mostEndorsedAccount = null;
        for (final var account : accounts) {
            // if the endorsement count of this account is larger than the one we found previously
            // replace that with this account for future comparison
            if (mostEndorsedAccount == null || mostEndorsedAccount.getEndorsementCount() < account.getEndorsementCount()) {
                mostEndorsedAccount = account;
            }
        }
        return mostEndorsedAccount.getId();
    }

    /**
     * Clears all data in social media.
     */
    @Override
    public void erasePlatform() {
        //Clears the arrays
        accounts.clear();
        posts.clear();
        postId = 0;
    }

    /**
     * Save the data of social media.
     *
     * @param filename location of the file to be saved
     * @throws IOException An error occurred when saving data.
     */
    @Override
    public void savePlatform(String filename) throws IOException {
        //Saves the platform to the specified name
        final var stream = new ObjectOutputStream(new FileOutputStream(filename));
        stream.writeObject(this);
    }

    /**
     * Loads a platform from a file.
     *
     * @param filename location of the file to be loaded
     * @throws IOException            An error occurred when reading the file.
     * @throws ClassNotFoundException
     */
    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        //Loads the specified file name that has been serialized
        final var stream = new ObjectInputStream(new FileInputStream(filename));
        final var obj = stream.readObject();

        if (obj instanceof SocialMediaPlatformImpl) {
            accounts = ((SocialMediaPlatformImpl) obj).accounts;
            posts = ((SocialMediaPlatformImpl) obj).posts;
        }
    }

    /**
     * @return The number of account registered on the social media.
     */
    @Override
    public int getNumberOfAccounts() {
        int totalAccounts = this.accounts.size();
        return totalAccounts;
    }

    /**
     * @return The number of posts made on the social media.
     */
    @Override
    public int getTotalOriginalPosts() {
        //Filters through and sorts all of the original posts
        return posts.stream()
                .filter(post -> !(post instanceof EndorsementPost || post instanceof Comment))
                .collect(Collectors.toCollection(ArrayList::new))
                .size();
    }

    /**
     * @return number of endorsement posts on social media.
     */
    @Override
    public int getTotalEndorsmentPosts() {
        return posts.stream()
                .filter(post -> post instanceof EndorsementPost)
                .collect(Collectors.toCollection(ArrayList::new))
                .size();
    }

    /**
     * @return number of comments on social media.
     */
    @Override
    public int getTotalCommentPosts() {
        return posts.stream()
                .filter(post -> post instanceof Comment)
                .collect(Collectors.toCollection(ArrayList::new))
                .size();
    }


    /**
     * Finds the account with the given handle.
     *
     * @param handle The handle of the account
     * @return the account with the given handle, or null if none is found.
     */
    private Account findAccountByHandle(String handle) {
        return accounts.stream()
                .filter(account -> account.getHandle().equals(handle))
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds a post on social media with the given id.
     *
     * @param postId The ID of the post
     * @return The post found, or null if none is found.
     */
    private Post findPostById(int postId) {
        return posts.stream()
                .filter(post -> post.getId() == postId)
                .findFirst()
                .orElse(null);
    }
}
