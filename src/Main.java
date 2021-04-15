import socialmedia.SocialMediaPlatformImpl;
import socialmedia.exceptions.AccountIDNotRecognisedException;
import socialmedia.exceptions.HandleNotRecognisedException;
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
            socialMediaPlatform.createAccount("ban", "I am banana");
            System.out.println(socialMediaPlatform.showArraylists());

            socialMediaPlatform.createAccount("tooty", "I am tooty");
            System.out.println(socialMediaPlatform.showArraylists());

            socialMediaPlatform.createAccount("jam", "I am jam");
            System.out.println(socialMediaPlatform.showArraylists());

            System.out.print("total: ");
            System.out.println(socialMediaPlatform.showArraylists());


            System.out.println(socialMediaPlatform.showAccount("jam"));


        } catch (InvalidHandleException e) {
            e.printStackTrace();
        } catch (IllegalHandleException e) {
            e.printStackTrace();
        } catch (HandleNotRecognisedException e) {
            e.printStackTrace();
        }


    }
}