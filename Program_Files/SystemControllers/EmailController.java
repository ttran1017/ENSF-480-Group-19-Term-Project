package SystemControllers;
import java.util.*;
import InteractionControllers.*;


public class EmailController {


    public EmailController() {
    }


   // private static Socket socket;

    public static void sendEmail(String address) {

      // Check for valid email
       if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()){
         Output.outputMessage("Invalid Email!");
         break;
       }

       // TODO
    }


    public static void setupMeeting(String address) {
        // TODO implement here
    }


    public static void startUpEmailController() {
        // TODO implement here
    }

}
