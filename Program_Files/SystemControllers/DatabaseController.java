package SystemControllers;

import Interfaces.*;
import Models.Account;

import java.util.*;
import java.sql.*;

public final class DatabaseController {

    private static DatabaseController INSTANCE;
    private static final String DBURL = "jdbc:mysql://localhost/prms_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12qwaszx";

    private DatabaseController() {
        try{
            Connection database= DriverManager.getConnection(DBURL,USERNAME,PASSWORD);

            Statement myStmt = database.createStatement();

            ResultSet myRs = myStmt.executeQuery("select * from accounts");

            while(myRs.next()){
                System.out.println(myRs.getString("username")+", "+myRs.getString("password"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
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

    public static void main(String[] args)
    {
        DatabaseController databsae = DatabaseController.getInstance();
    }
}