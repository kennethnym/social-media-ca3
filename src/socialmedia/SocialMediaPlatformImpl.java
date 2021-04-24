package socialmedia;

import java.io.*;
import java.nio.Buffer;
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

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        //IllegalHandleException
        for (int j = 0; j < this.accounts.size(); j++) {
            if (handle.equals(accounts.get(j).getHandle())){
                throw new IllegalHandleException();
            }
        }

        //InvalidHandleException
        for (int i = 0; i < this.accounts.size(); i++) {
            if (handle.contains(" ")){
                throw new InvalidHandleException();
            }
            if (handle != null && !handle.isEmpty()){
                throw new InvalidHandleException();
            }
        }
        //Create account code:
        int arrayLength = this.accounts.size();
        int accountID = arrayLength + 1;
        this.accounts.add(new Account(accountID, handle, "empty"));
        return accountID;
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        //IllegalHandleException
        for (int j = 0; j < this.accounts.size(); j++) {
            if (handle.equals(accounts.get(j).getHandle())){
                throw new IllegalHandleException();
            }
        }

        //InvalidHandleException
        for (int i = 0; i < this.accounts.size(); i++) {
            if (handle.contains(" ")){
                throw new InvalidHandleException();
            }
            if (handle != null && !handle.isEmpty()){
                throw new InvalidHandleException();
            }
        }

        //Create new account code
        int arrayLength = this.accounts.size();
        int accountID = arrayLength + 1;
        this.accounts.add(new Account(accountID, handle, description));

        return accountID;
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        //AccountIDNotRecognisedException
        for (int i = 0; i < accounts.size(); i++) {
            if (!accounts.contains(accounts.get(i).getId())){
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

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        //HandleNotRecognisedException
        for (int i = 0; i < accounts.size(); i++) {
            if (!handle.equals(this.accounts.get(i).getHandle())){
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

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        //HandleNotRecognisedException
        for (int i = 0; i < accounts.size(); i++) {
            if (!oldHandle.equals(this.accounts.get(i).getHandle())){
                throw new HandleNotRecognisedException();
            }
        }

        //IllegalHandleException
        for (int j = 0; j < this.accounts.size(); j++) {
            if (oldHandle.equals(accounts.get(j).getHandle())){
                throw new IllegalHandleException();
            }
        }

        //InvalidHandleException
        for (int i = 0; i < this.accounts.size(); i++) {
            if (oldHandle.contains(" ")){
                throw new InvalidHandleException();
            }
            if (oldHandle != null && !oldHandle.isEmpty()){
                throw new InvalidHandleException();
            }
        }

        //Loops through and checks the handles, if true then set handle with new handle
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getHandle().equals(oldHandle)) {
                this.accounts.get(i).setHandle(newHandle);
            }
        }
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        //HandleNotRecognisedException
        for (int i = 0; i < accounts.size(); i++) {
            if (!handle.equals(this.accounts.get(i).getHandle())){
                throw new HandleNotRecognisedException();
            }
        }

        //Loops through and checks if the handle is the same, if it is update the description.
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getHandle().equals(handle)) {
                accounts.get(i).setDescription(description);
            }
        }

    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        String formattedSummary = "default mate";
//        accounts.get(i).getHandle().equals(handle)

        for (int i = 0; i < accounts.size(); i++) {
            String currentAccountsHandle = accounts.get(i).getHandle();
            if (currentAccountsHandle.equals(handle)) {

                //Grab all of the account data:
                int accountID = accounts.get(i).getId();
                String accountHandle = accounts.get(i).getHandle();

                //Problem Code is this line below:
                String accountDescription = accounts.get(i).getDescription();

                //Need Kens Code:
                int accountPostCount = 0;
                int accountEndorseCount = 0;

                formattedSummary = (
                        "\n"
                                + "\n"
                                + "ID: " + accountID + "\n"
                                + "Handle: " + accountHandle + "\n"
                                + "Description: " + accountDescription + "\n"
                                + "Post count: " + accountPostCount + "\n"
                                + "Endorse count: " + accountEndorseCount + "\n"
                                + "\n"
                );
            }
        }
        return formattedSummary;
    }

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

    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        final var originalPost = findPostById(id);
        final var endorser = findAccountByHandle(handle);

        //PostIDNotRecognisedException
        if (originalPost == null)
            throw new PostIDNotRecognisedException();

        //PostIDNotRecognisedException
        if (endorser == null)
            throw new HandleNotRecognisedException();

        //PostIDNotRecognisedException
        if (originalPost instanceof EndorsementPost)
            throw new NotActionablePostException();

        //Creates new endorsementPost and appends it
        final var endorsementPost = new EndorsementPost(originalPost, postId++, endorser);
        posts.add(endorsementPost);

        return endorsementPost.getId();
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        final var parent = findPostById(id);
        final var commenter = findAccountByHandle(handle);


        //PostIDNotRecognisedException
        if (parent == null)
            throw new PostIDNotRecognisedException();

        //HandleNotRecognisedException
        if (commenter == null)
            throw new HandleNotRecognisedException();

        //NotActionablePostException
        if (parent instanceof EndorsementPost)
            throw new NotActionablePostException();

        //InvalidPostException
        if (message.isEmpty() || message.length() > MAX_COMMENT_LENGTH)
            throw new InvalidPostException();

        //Creates new comment object and appends the comment
        final var comment = new Comment(parent, postId++, commenter, message);
        posts.add(comment);
        parent.addComment(comment);

        return comment.getId();
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        final var post = findPostById(id);

        //PostIDNotRecognisedException
        if (post == null)
            throw new PostIDNotRecognisedException();

        posts.remove(post);
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        final var post = findPostById(id);

        //PostIDNotRecognisedException
        if (post == null)
            throw new PostIDNotRecognisedException();

        return post.toString();
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        final var post = findPostById(id);

        //PostIDNotRecognisedException
        if (post == null)
            throw new PostIDNotRecognisedException();

        //NotActionablePostException
        if (post instanceof EndorsementPost)
            throw new NotActionablePostException();

        //Creates post children details using string builder
        final var stringBuilder = new StringBuilder();
        showPostChildrenDetails(post, 0, stringBuilder);

        return stringBuilder;
    }

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

        //If the parent object has comments then create different string
        if (parent.hasComments()) {
            builder.append(indentation).append("|\n");
            for (final var comment : parent.getComments()) {
                builder.append(indentation).append("| > ");
                showPostChildrenDetails(comment, level + 1, builder);
            }
        }

        return builder;
    }

    @Override
    public int getMostEndorsedPost() {

        //Returns the most endorsed post
        Post mostEndorsedPost = null;
        for (final var post : posts) {
            if (mostEndorsedPost.getEndorsements() < post.getEndorsements()) {
                mostEndorsedPost = post;
            }
        }
        return mostEndorsedPost.getId();
    }

    @Override
    public int getMostEndorsedAccount() {
        return 0;
    }

    @Override
    public void erasePlatform() {
        //Clears the arrays
        accounts.clear();
        posts.clear();
        postId = 0;
    }

    @Override
    public void savePlatform(String filename) throws IOException {
        //Saves the platform to the specified name
        final var stream = new ObjectOutputStream(new FileOutputStream(filename));
        stream.writeObject(this);
    }

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

    @Override
    public int getNumberOfAccounts() {
        int totalAccounts = this.accounts.size();
        return totalAccounts;
    }

    @Override
    public int getTotalOriginalPosts() {
        //Filters through and sorts all of the original posts
        return posts.stream()
                .filter(post -> !(post instanceof EndorsementPost || post instanceof Comment))
                .collect(Collectors.toCollection(ArrayList::new))
                .size();
    }

    @Override
    public int getTotalEndorsmentPosts() {
        return posts.stream()
                .filter(post -> post instanceof EndorsementPost)
                .collect(Collectors.toCollection(ArrayList::new))
                .size();
    }

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
     * @return the account with the given handle.
     */
    private Account findAccountByHandle(String handle) {
        return accounts.stream()
                .filter(account -> account.getHandle().equals(handle))
                .findFirst()
                .orElse(null);
    }

    private Post findPostById(int postId) {
        return posts.stream()
                .filter(post -> post.getId() == postId)
                .findFirst()
                .orElse(null);
    }
}
