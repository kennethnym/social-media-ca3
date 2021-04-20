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

            //Create account Handle: Jam, Description: I am jam
            socialMediaPlatform.createAccount("chrisydaboy", "I am christopher");
            socialMediaPlatform.updateAccountDescription("chrisydaboy", "updated description");

            socialMediaPlatform.createAccount("jam", "I am jam");
            socialMediaPlatform.updateAccountDescription("chrisydaboy", "this is a new new description");
            String showAccount1 = socialMediaPlatform.showAccount("chrisydaboy");
            String showAccount = socialMediaPlatform.showAccount("jam");

            System.out.println(showAccount1);
            System.out.println(showAccount);


            socialMediaPlatform.changeAccountHandle("chrisydaboy", "chrisyladspogchamp");
            String showAccount1Updated = socialMediaPlatform.showAccount("chrisyladspogchamp");
            System.out.println(showAccount1Updated);


        } catch (InvalidHandleException e) {
            e.printStackTrace();
        } catch (IllegalHandleException e) {
            e.printStackTrace();
        } catch (HandleNotRecognisedException e) {
            e.printStackTrace();
        }


    }
}