/**
 * FileName: Main.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package SystemControllers;
import Models.*;
import Enums.AccountType;
import InteractionControllers.*;

public class Main {
    private static Account user = null;
    private static boolean continueProgram = true;

    public static void main(String[] args)
    {
        if(args.length < 2)
        {
            IO.outputMessage("ERROR: Ensure Database Username and Password are Entered");
            System.exit(1);
        }
        DatabaseController.initialize(args[0], args[1]);
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
        String option = (String)IO.getDropdownInput(
            "Unregistered User Options", 
            "Select From:", 
            new String[]{
                "View Listed Properties",
                "Send Email",
                "Arrange Meeting",
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
            case "Arrange Meeting":
                Property temp2 = PropertyHub.getInstance().selectProperty();
                EmailController.getInstance().setupMeeting("UNREGISTERED", temp2.getOwnerEmail(), temp2.getPropertyID());
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
        String option = (String)IO.getDropdownInput(
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
                "Arrange Meeting",
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
            case "Arrange Meeting":
                uAccount.arrangeMeeting();
                break;
            case "Logout":
                user = AccountHandler.logout();
                break;
        }

    }

    public static void handleManager()
    {
        ManagerAccount mAccount = (ManagerAccount)user;
        String option = (String)IO.getDropdownInput(
            "Manager Options", 
            "Select From:", 
            new String[]{
                "Update Fees",
                "Update Posting Period",
                "View Property Info",
                "View User Info",
                "Update Summary Period",
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
            case "Update Posting Period":
                mAccount.updateFeePeriod();
                break;
            case "View Property Info":
                mAccount.viewPropertyInfo();
                break;
            case "View User Info":
                mAccount.viewUserInfo();
                break;
            case "Update Summary Period":
                mAccount.updateSummaryPeriod();
                break;
            case "Generate Summary":
                mAccount.generateSummary();
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
