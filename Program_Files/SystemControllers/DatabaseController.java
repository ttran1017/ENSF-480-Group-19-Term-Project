package SystemControllers;

import Interfaces.*;
import Models.Account;

import java.util.*;


public final class DatabaseController {

    private static DatabaseController INSTANCE;

    private DatabaseController() {
    }

    public static DatabaseController getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new DatabaseController();
        }
        return INSTANCE;
    }

    public int verifyLogin(String username, String password)
    {
        // VERIFY LOGIN DETAILS IN DATABASE
        // RETURNS ACCOUNT ID IF FOUND -1 OTHERWISE
        return 1;
    }

    public int verifyRegistration(String email, String username, String password)
    {
        // SHOULD BE CHECKING WITH DATABASE HERE BUT SET TO TRUE FOR TESTING
        return 0;
    }

    public int addAccount(String email, String username, String password)
    {
        // Adds this shit to the SQL database
        return 1;
    }

}