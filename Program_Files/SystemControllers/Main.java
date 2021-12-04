package SystemControllers;
import Models.*;
import InteractionControllers.*;

public class Main {
    private static Account user = null;
    private static boolean continueProgram = true;

    // main loop - DO NOT MODIFY
    public static void main(String[] args)
    {
        while(continueProgram)
        {
            if(user == null)                    // Unregistered User
                handleUnregistered();
            else if(user.accountType() == 1)    // Registered User
                handleUser();
            else                                // Manager
                handleManager();
        }
    }

    public static void handleUnregistered()
    {
        String option = (String)Input.getDropdownInput(
            "Unregistered User Options", 
            "Select From:", 
            new String[]{
                "View Properties",
                "Register",
                "Login",
                "Exit",
                }
            );
        switch(option)
        {
            case "View Properties":
                // TODO
                break;
            case "Register":
                AccountHandler.createAccount();
                break;
            case "Login":
                user = AccountHandler.login();
                System.out.println(user);
                break;
            case "Exit":
                continueProgram = false;
                break;
        }
    }

    public static void handleUser()
    {
        UserAccount sAccount = (UserAccount)user;
        String option = (String)Input.getDropdownInput(
            "Registered User Options", 
            "Select From:", 
            new String[]{
                "Update Filter",
                "Change Subscription",
                "View Property",
                "Register Property",
                "Post Property",
                "Update Property"}
            );
        switch(option)
        {
            case "Update Filter":
                break;
            case "Change Subscription":
                break;
            case "View Property":
                sAccount.viewProperties();
                break;
            case "Register Property":
                sAccount.registerProperty();
                break;
            case "Post Property":
                break;
            case "Update Property":
                break;
            //TO BE IMPLEMENTED
        }

    }

    public static void handleManager()
    {
        String option = (String)Input.getDropdownInput(
            "Manager Options", 
            "Select From:", 
            new String[]{
                "Update Fees",
                "View Property Info",
                "View Renter Info",
                "View Landlord Info",
                "Generate Summary",
                "Modify Listing"}
            );
    }
}
