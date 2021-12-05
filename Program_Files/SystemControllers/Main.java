package SystemControllers;
import Models.*;
import InteractionControllers.*;
import Interfaces.AccountType;

public class Main {
    private static Account user = null;
    private static boolean continueProgram = true;

    // main loop - DO NOT MODIFY
    public static void main(String[] args)
    {
        while(continueProgram)
        {
            if(user == null)                                        // Unregistered User
                handleUnregistered();
            else if(user.getAccountType() == AccountType.User)      // Registered User
                handleUser();
            else if(user.getAccountType() == AccountType.Manager)    // Manager
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
                "Send Email",
                "Register",
                "Login",
                "Exit",
                }
            );
        switch(option)
        {
            case "View Properties":
                PropertyViewer.unregisteredViewProperties();
                break;
            case "Send Email":
                EmailController.sendEmail();
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
        UserAccount uAccount = (UserAccount)user;
        String option = (String)Input.getDropdownInput(
            "Registered User Options", 
            "Select From:", 
            new String[]{
                "Update Filter",
                "Change Subscription",
                "View All Properties",
                "View My Properties",
                "Register Property",
                "Post Property",
                "Update Property",
                "Send Email",
                "Logout"}
            );
        switch(option)
        {
            case "Update Filter":
                uAccount.updateFilter();
                break;
            case "Change Subscription":
                break;
            case "View All Properties":
                uAccount.viewAllProperties();
                break;
            case "View My Properties":
                uAccount.viewMyProperties();
                break;
            case "Register Property":
                uAccount.registerProperty();
                break;
            case "Post Property":
                uAccount.postProperty();
                break;
            case "Update Property":
                uAccount.updateProperty();
                break;
            case "Send Email":
                EmailController.sendEmail();
            case "Logout":
                user = null;
        }

    }

    public static void handleManager()
    {
        ManagerAccount mAccount = (ManagerAccount)user;
        String option = (String)Input.getDropdownInput(
            "Manager Options", 
            "Select From:", 
            new String[]{
                "Update Fees",
                "View Property Info",
                "View Renter Info",
                "View Landlord Info",
                "Generate Summary",
                "Modify Listing",
                "Logout"}
            );
    }
}
