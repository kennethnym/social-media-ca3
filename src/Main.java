import socialmedia.SocialMedia;
import socialmedia.exceptions.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //This is for testing
        final var socialMediaPlatform = new SocialMedia();

        //Create a new account
        try {
            //Create account Handle: Jam, Description: I am jam
            socialMediaPlatform.createAccount("chrisydaboy", "I am christopher");
            socialMediaPlatform.updateAccountDescription("chrisydaboy", "updated description");

            socialMediaPlatform.createAccount("jam", "I am jam");
            socialMediaPlatform.updateAccountDescription("chrisydaboy", "this is a new new description");
            String showAccount1 = socialMediaPlatform.showAccount("chrisydaboy");
            String showAccount = socialMediaPlatform.showAccount("jam");

//            System.out.println(showAccount1);
//            System.out.println(showAccount);

            socialMediaPlatform.changeAccountHandle("chrisydaboy", "chrisyladspogchamp");
            String showAccount1Updated = socialMediaPlatform.showAccount("chrisyladspogchamp");
//            System.out.println(showAccount1Updated);

            int id = socialMediaPlatform.createPost("chrisyladspogchamp", "hello world");
            int id2 = socialMediaPlatform.commentPost("chrisyladspogchamp", id, "hello world");
            socialMediaPlatform.commentPost("chrisyladspogchamp", id2, "hello worl2");
            socialMediaPlatform.commentPost("chrisyladspogchamp", id, "hello worlsalkcjaskjkasjkd");

            final var builder = socialMediaPlatform.showPostChildrenDetails(id);
            System.out.println(builder.toString());

            socialMediaPlatform.savePlatform("test.txt");
            socialMediaPlatform.erasePlatform();
            socialMediaPlatform.loadPlatform("test.txt");
        } catch (InvalidHandleException e) {
            e.printStackTrace();
        } catch (IllegalHandleException e) {
            e.printStackTrace();
        } catch (HandleNotRecognisedException e) {
            e.printStackTrace();
        } catch (InvalidPostException e) {
            e.printStackTrace();
        } catch (NotActionablePostException e) {
            e.printStackTrace();
        } catch (PostIDNotRecognisedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}