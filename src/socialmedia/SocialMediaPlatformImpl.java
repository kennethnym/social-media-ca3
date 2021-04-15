package socialmedia;

import java.util.ArrayList;

import socialmedia.exceptions.*;
import socialmedia.models.Account;

import java.io.IOException;

public class SocialMediaPlatformImpl implements SocialMediaPlatform {

    private ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        int arrayLength = this.accounts.size();
        int accountID = arrayLength + 1;
        this.accounts.add(new Account(accountID, handle, ""));
        return accountID;
    }

    //DELETE THIS JUST FOR TESTING
    public int showArraylists(){
        int total = this.accounts.size();
        return total;
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        //Loops through and checks if there is the same id
        for (int i = 0; i < this.accounts.size(); i++) {
            if (id == this.accounts.get(i).getId()) {
                this.accounts.remove(i);
            }
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        //Loops through and checks the handles, if true then set handle with new handle
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getHandle().equals(oldHandle)) {
                this.accounts.get(i).setHandle(newHandle);
            }
        }
    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {

        String formattedSummary = "default mate";

        String currentAccountsHandle;

//        accounts.get(i).getHandle().equals(handle)

        for (int i = 0; i < accounts.size(); i++) {
            currentAccountsHandle = accounts.get(i).getHandle();
            if (currentAccountsHandle.equals(handle)) {

                //Grab all of the account data:
                int accountID = accounts.get(i).getId();
                String accountHandle = accounts.get(i).getHandle();
                String accountDescription = accounts.get(i).getDescription();

                //Need Kens Code:
                int accountPostCount = 0;
                int accountEndorseCount = 0;

                formattedSummary = (
                        "\n"
                        +"\n"
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
        int arrayLength = this.accounts.size();
        int accountID = arrayLength + 1;
        this.accounts.add(new Account(accountID, handle, description));
        return accountID;
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        for (int i = 0; i < this.accounts.size(); i++) {
            if (handle.equals(this.accounts.get(i).getHandle())){
                accounts.remove(i);
            }
        }
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {

    }

    @Override
    public int getNumberOfAccounts() {
        int totalAccounts = this.accounts.size();
        return totalAccounts;
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
