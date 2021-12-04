package SystemControllers;
import Models.*;
import InteractionControllers.*;

import java.util.*;

public final class AccountHandler {

    private static DatabaseController database = DatabaseController.getInstance();
    private static HashMap<Integer, Account> accountList = new HashMap<Integer, Account>();

    public static void createAccount()
    {
        ArrayList<String> accountInfo = Input.getMultiStringInput("Login Form", new String[]{"Email","Username","Password"});
        String email = accountInfo.get(0);
        String username = accountInfo.get(1);
        String password = accountInfo.get(2);
        switch(database.verifyRegistration(email,username,password))
        {
            case 0:
                int accountId = database.addAccount(email, username, password);
                accountList.put(accountId, new UserAccount(email,username,password));
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
}