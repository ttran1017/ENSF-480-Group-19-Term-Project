package SystemControllers;
import Models.*;
import InteractionControllers.*;

public class Main {
    private static Account user = null;

    public static void main(String[] args)
    {
        while(true)
        {
            if(user == null)                    // Unregistered User
            {
                handleUnregistered();
            }
            else if(user.accountType() == 1)    // Registered User
            {
                Output.outputMessage("User Account Menu");
            }
            else                                // Manager
            {
                Output.outputMessage("Manager Account menu");
            }
        }
    }

    public static void handleUnregistered()
    {
        String option = (String)Input.getDropdownInput("Unregistered User Options", "Select From:", new String[]{"View Properties","Register","Login"});
        switch(option)
        {
            case "View Properties":
            {
                Output.outputMessage("You chose to view properties!");
                break;
            }
            case "Register":
            {
                AccountHandler.createAccount();
                break;
            }
            case "Login":
            {
                user = AccountHandler.login();
                System.out.println(user);
                break;
            }
        }
    }
}

//javac -cp ./lib/mysql-connector-java-8.0.23.jar;./SystemControllers/DatabaseController.java ./SystemControllers/Main.java
