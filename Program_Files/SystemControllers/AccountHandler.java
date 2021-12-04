package SystemControllers;
import Models.*;
import InteractionControllers.*;

import java.util.*;

public final class AccountHandler {

    private static DatabaseController database = DatabaseController.getInstance();
    private static HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();

    public static void createAccount()
    {
        String[] accountParams = ["Enter an Email", "Enter a Username", "Enter a Password"];

        ArrayList<String> accountDetails = Input.getMultiStringInput("Enter Account Details", accountParams);

        String email = accountDetails[0];
        String username = accountDetails[1];
        String password = accountDetails[2];

        // Check for valid email
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()){
          Output.outputMessage("Invalid Email!");
          break;
        }

        switch(database.verifyRegistration(email,username,password))
        {
            case 0:
                int accountId = database.addAccount(email, username, password);
                accountList.put(accountId, new Account(email,username,password, type));
                Output.outputMessage("Account Creation Success");
                break;
            case 1:
                Output.outputMessage("Invalid Email!");
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
}
