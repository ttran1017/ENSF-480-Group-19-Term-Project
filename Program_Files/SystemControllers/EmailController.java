package SystemControllers;
import InteractionControllers.*;
import java.util.*;
import java.util.regex.Pattern;


public class EmailController {


    public EmailController() {
    }


   // private static Socket socket;
//Fee 
//Filter
//Email

    public static void sendEmail(String address) {
        // TODO implement here
    }

    // Send to address that a property has been posted with a specified ID
    public static void sendNotification(String address, int ID)
    {
    }


    public static void setupMeeting(String address) {
        // TODO implement here
    }


    public static void startUpEmailController() {
        // TODO implement here
    }

    public static boolean checkFormat(String email)
    {
        if(Pattern.compile("^(?:.+)@(?:\\S+)$").matcher(email).matches()){
            return true;
        }
        return false;
    }
}
