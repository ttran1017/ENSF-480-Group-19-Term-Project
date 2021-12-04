package SystemControllers;

import Models.UserAccount;
import Models.Property;
import java.sql.*;

public final class DatabaseController {

    private static DatabaseController INSTANCE;
    private static final String DBURL = "jdbc:mysql://localhost/prms_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "09125132465";

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
            Connection database= DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from accounts where username="+username+"and password="+password);
                if (myRs.getString("account_id")!=null) {
                    return myRs.getInt("account_id");
                }
                else return -1;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    }

    public int verifyRegistration(String email, String username)
    {
        // SHOULD BE CHECKING WITH DATABASE HERE BUT SET TO TRUE FOR TESTING 0 if unique 1 match email 2 match username
        try{
            Connection database= DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            Statement myStmt = database.createStatement();
            ResultSet myRs2 = myStmt.executeQuery("select * from accounts where username="+username+"or email="+email);
            while (myRs2.next()) {
                if (myRs2.getString("account_id")==null) {
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

    public int addAccount(UserAccount account)
    {
        // Adds this shit to the SQL database
        // Hey Sina just to make our code more modular, I decided that we should pass in full accounts and properties into DatabaseController thanks
        String email = account.getEmail();
        String username = account.getUsername();
        String password = account.getPassword();
        try{
            Connection database= DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            Statement myStmt = database.createStatement();
            myStmt.executeQuery("INSERT INTO `Accounts`(email,username,password) VALUES ("+email+","+username+","+password+")");
            ResultSet myRs3 = myStmt.executeQuery("select * from accounts where username="+username+"and password="+password);
                if (myRs3.getString("account_id")!=null) {
                    return myRs3.getInt("account_id");
                }        
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }

    //int ID, String email, String type, String address, String quad, int bed, int bath, boolean furnished, int days)
    // STILL REQUIRES
    // -EMAIL
    // -ADDRESS
    // -DAYS PUBLISHED
    // -STATUS
    public void updateListing(Property property)
    {
        try{
            Connection database= DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            Statement myStmt = database.createStatement();
            myStmt.executeQuery("update `Properties` set type ="+property.getPropertyType()+" where property_id="+property.getPropertyID());
            myStmt.executeQuery("update `Properties` set `# of bedrooms` ="+property.getNumBedrooms()+" where property_id="+property.getPropertyID());
            myStmt.executeQuery("update `Properties` set `# of bathrooms` ="+property.getNumBathrooms()+" where property_id="+property.getPropertyID());
            myStmt.executeQuery("update `Properties` set `city quadrant` ="+property.getPropertyQuadrant()+" where property_id="+property.getPropertyID());
            myStmt.executeQuery("update `Properties` set `is furnished` ="+property.getIsFurnished()+" where property_id="+property.getPropertyID());
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return ;
    }

    // ===============================================
    // TO BE IMPLEMENTED BY SINA
    // ===============================================
    public int addProperty(Property property)
    {
        return 0;
        // Returns ID
    }
    public int getFee() { return -1; };
    public int getPeriod() { return -1; };
    public void updateFee(int fee) {};
    public void updatePeriod(int period) {};
    public void updateBalance(int deposit) {};

    public static void main(String[] args)
    {
        DatabaseController database = DatabaseController.getInstance();
    }
}