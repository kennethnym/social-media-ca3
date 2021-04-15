import socialmedia.SocialMediaPlatformImpl;
import socialmedia.exceptions.AccountIDNotRecognisedException;
import socialmedia.exceptions.IllegalHandleException;
import socialmedia.exceptions.InvalidHandleException;
import socialmedia.models.Account;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //This is for testing
        final var socialMediaPlatform = new SocialMediaPlatformImpl();


        //Create a new account
        try {
            socialMediaPlatform.createAccount("Banan", "I am banana");

        } catch (InvalidHandleException e) {
            e.printStackTrace();
        } catch (IllegalHandleException e) {
            e.printStackTrace();
        }



    }
}