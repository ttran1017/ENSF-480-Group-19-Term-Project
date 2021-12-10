/**
 * FileName: Main.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

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
                "View Listed Properties",
                "Send Email",
                "Register",
                "Login",
                "Exit",
                }
            );
        if(option == null)
            System.exit(1);
        switch(option)
        {
            case "View Listed Properties":
                PropertyViewer.unregisteredViewProperties();
                break;
            case "Send Email":
                Property temp = PropertyHub.getInstance().selectProperty();
                EmailController.getInstance().sendEmail("UNREGISTERED", temp.getOwnerEmail(), temp.getPropertyID());
                break;
            case "Register":
                AccountHandler.createAccount();
                break;
            case "Login":
                user = AccountHandler.login();
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
                "View Listed Properties",
                "View My Properties",
                "Register Property",
                "Post Property",
                "Update Property",
                "Send Email",
                "Logout"}
            );
        if(option == null)
            System.exit(1);
        switch(option)
        {
            case "Update Filter":
                uAccount.updateFilter();
                break;
            case "Change Subscription":
                uAccount.updateSubscription();
                break;
            case "View Listed Properties":
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
                uAccount.sendEmail();
                break;
            case "Logout":
                user = AccountHandler.logout();
                break;
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
                "Update Posting Period",
                "View Property Info",
                "View User Info",
                "Generate Summary",
                "Modify Listing",
                "Logout"}
            );
        if(option == null)
            System.exit(1);
        switch(option)
        {
            case "Update Fees":
                mAccount.updateFees();
                break;
            case "View Property Info":
                mAccount.viewPropertyInfo();
                break;
            case "Update Posting Period":
                break;
            case "View User Info":
                break;
            case "Generate Summary":
                break;
            case "Modify Listing":
                mAccount.modifyListing();
                break;
            case "Logout":
                user = AccountHandler.logout();
                break;
        }
    }
}
