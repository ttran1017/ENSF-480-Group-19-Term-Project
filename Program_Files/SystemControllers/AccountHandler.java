/**
 * FileName: AccountHandler.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package SystemControllers;
import Models.*;
import InteractionControllers.*;
import Interfaces.AccountType;

import java.util.*;

/**
 * Class which keeps track of all accounts in the system
 * and interfaces with the database
 */
public final class AccountHandler {

    /**
     * Class fields
     */
    private static DatabaseController database = DatabaseController.getInstance();
    private static HashMap<Integer, Account> accountList = database.getAccountsHashMap();

    /**
     * Handles new account creation
     */
    public static void createAccount()
    {
        ArrayList<String> accountInfo = Input.getMultiStringInput("Login Form", new String[]{"Email","Username","Password"});
        String email = accountInfo.get(0);
      
       // Check for valid email
        if(!EmailController.getInstance().checkFormat(email)){
          Output.outputMessage("Invalid Email!");
          return;
        }
        String username = accountInfo.get(1);
        String password = accountInfo.get(2);
        switch(database.verifyRegistration(email,username))
        {
            case 0:
                UserAccount temp = new UserAccount(email, username, password);
                int accountID = database.addAccount(temp);
                temp.setAccountID(accountID);
                accountList.put(accountID, temp);
                Output.outputMessage("Account Creation Success");
                break;
            case 1:
                Output.outputMessage("Invalid Email");
                break;
            case 2:
                Output.outputMessage("Invalid Username");
                break;
        }
    }

    /**
     * Handles account logins
     * @return Account if login successful or null otherwise
     */
    public static Account login()
    {
        Account returnAccount;
        ArrayList<String> accountInfo = Input.getMultiStringInput("Login Form", new String[]{"Username","Password"});
        String username = accountInfo.get(0);
        String password = accountInfo.get(1);
        int accountID = database.verifyLogin(username, password);
        if(accountID == -1)
        {
            Output.outputMessage("Login Failed");
            return null;
        }
        returnAccount = accountList.get(accountID);
        if(returnAccount.getAccountType() == AccountType.User)
            ((UserAccount) returnAccount).setOwnedProperties(database.getAllProperties(accountID));
        Output.outputMessage("Login Successful");
        return accountList.get(accountID);
    }

    /**
     * Handles account logout
     * @return null
     */
    public static Account logout()
    {
        Output.outputMessage("Logout Successful");
        return null;
    }

    /**
     * Returns all accounts in the system as an arraylist
     * @return All accounts
     */
    public static ArrayList<Account> getAccountList(){ return new ArrayList<Account>(accountList.values()); }

    public static Account getAccountByID(Integer ID) { return accountList.get(ID); }
}
