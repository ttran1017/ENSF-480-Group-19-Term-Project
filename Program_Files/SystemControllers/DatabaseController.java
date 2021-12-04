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
        try{

            ResultSet myRs = myStmt.executeQuery("select * from accounts where username="+username+"and password="+password);
                if (myRs.getString("account_id")!=NULL) {
                    return myRs.getString("account_id");
                }
                else return -1;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public int verifyRegistration(String email, String username)
    {
        // SHOULD BE CHECKING WITH DATABASE HERE BUT SET TO TRUE FOR TESTING 0 if unique 1 match email 2 match username
        try{

            ResultSet myRs2 = myStmt.executeQuery("select * from accounts where username="+username+"or email="+email);
            while (myRs2.next()) {
                if (myRs2.getString("account_id")==NULL) {
                    return 0;
                }
                else if (myRs2.getString("email")==email) {
                    return 1;
                }
                 else if (myRs2.getString("username")==username) {
                    return 2;
                }
            }
            return 0;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return 0;
    }

    public int addAccount(String email, String username, String password)
    {
        // Adds this shit to the SQL database

        try{
            myStmt.executeQuery("INSERT INTO `Accounts`(email,username,password) VALUES ("+email+","+username+","+password+")");
            ResultSet myRs3 = myStmt.executeQuery("select * from accounts where username="+username+"and password="+password);
                if (myRs3.getString("account_id")!=NULL) {
                    return myRs.getString("account_id");
                }        
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }

    public int addProperty(
        String email, 
        String propType, 
        String propAddr, 
        String propQuad, 
        int numBed, 
        int numBath, 
        boolean isFurnished, 
        int daysRemaaining)
    {
        return 0;
        // Returns ID
    }

    public static void main(String[] args)
    {
        DatabaseController databsae = DatabaseController.getInstance();
    }
}