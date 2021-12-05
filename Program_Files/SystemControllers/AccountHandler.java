package SystemControllers;
import Models.*;
import InteractionControllers.*;

import java.util.*;

public final class AccountHandler {
    private static DatabaseController database = DatabaseController.getInstance();
    private static HashMap<Integer, Account> accountList = database.getAccountsHashMap();

    public static void createAccount()
    {
        ArrayList<String> accountInfo = Input.getMultiStringInput("Login Form", new String[]{"Email","Username","Password"});
        String email = accountInfo.get(0);
      
       // Check for valid email
        if(!EmailController.checkFormat(email)){
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

    public static Account login()
    {
        String username = Input.getStringInput("Enter a Username");
        String password = Input.getStringInput("Enter a Password");
        int accountID = database.verifyLogin(username, password);
        if(accountID == -1)
        {
            Output.outputMessage("Login Failed");
            return null;
        }
        Output.outputMessage("Login Successful");
        return accountList.get(accountID);
    }

    public static void main(String[] args)
    {
        createAccount();
    }
}
