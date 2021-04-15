package socialmedia;
import java.util.ArrayList;
import socialmedia.exceptions.*;
import socialmedia.models.Account;
import java.io.IOException;

public class SocialMediaPlatformImpl implements SocialMediaPlatform{

    private ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        int arrayLength = accounts.size();
        int accountID = arrayLength + 1;
        accounts.add(new Account(accountID, handle, ""));
        return accountID;
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        //Loops through and checks if there is the same id
        for (int i = 0; i < accounts.size(); i++) {
            if (id == accounts.get(i).getId()){
                accounts.remove(i);
            }
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        String oldH = oldHandle;
        String newH = newHandle;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getHandle().equals(oldHandle)){

            }
        }


    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        return null;
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        return 0;
    }

    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        return 0;
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        return 0;
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {

    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        return null;
    }

    @Override
    public int getMostEndorsedPost() {
        return 0;
    }

    @Override
    public int getMostEndorsedAccount() {
        return 0;
    }

    @Override
    public void erasePlatform() {

    }

    @Override
    public void savePlatform(String filename) throws IOException {

    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {

    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        return 0;
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {

    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {

    }

    @Override
    public int getNumberOfAccounts() {
        return 0;
    }

    @Override
    public int getTotalOriginalPosts() {
        return 0;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        return 0;
    }

    @Override
    public int getTotalCommentPosts() {
        return 0;
    }
}
