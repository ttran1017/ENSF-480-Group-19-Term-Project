package SystemControllers;

import Interfaces.PropertyType;
import Interfaces.PropertyQuadrant;
import Interfaces.PropertyStatus;
import Models.UserAccount;
import Models.Property;
import java.sql.*;

public final class DatabaseController {

    private static DatabaseController INSTANCE;
    private static final String DBURL = "jdbc:mysql://localhost/prms_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "09125132465";
    private Connection database;

    private DatabaseController() {
        try{
            database = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
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
        // Hey Sina just to make our code more modular, I decided that we should pass in full accounts and properties into DatabaseController thanks
        String email = account.getEmail();
        String username = account.getUsername();
        String password = account.getPassword();
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("INSERT INTO `Accounts`(email,username,password) VALUES ("+email+","+username+","+password+")");
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

    public void updateListing(Property property)
    {
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Properties` set address ="+property.getPropertyAddress()+" where property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Properties` set type ="+property.getPropertyType()+" where property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Properties` set `# of bedrooms` ="+property.getNumBedrooms()+" where property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Properties` set `# of bathrooms` ="+property.getNumBathrooms()+" where property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Properties` set `city quadrant` ="+property.getPropertyQuadrant()+" where property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Properties` set `is furnished` ="+property.getIsFurnished()+" where property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Properties` set `days` ="+property.getDaysRemaining()+" where property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Properties` set `status` ="+property.getPropertyStatus()+" where property_id="+property.getPropertyID());

            ResultSet myRs4 = myStmt.executeQuery("select * from Properties join Accounts on Properties.account_id = Accounts.account_id where properties.property_id="+property.getPropertyID());
            myStmt.executeUpdate("update `Accounts` set `email` ="+property.getOwnerEmail()+" where account_id="+myRs4.getString("account_id"));
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    }

    public int addProperty(Property property)
    {
        int property_id=-1;
        String address = property.getPropertyAddress();
        PropertyType type = property.getPropertyType();
        int numBedrooms = property.getNumBedrooms();
        int numBathrooms = property.getNumBathrooms();
        Boolean isFurnished = property.getIsFurnished();
        PropertyQuadrant cityQuadrant = property.getPropertyQuadrant();
        int days = property.getDaysRemaining();
        PropertyStatus status = property.getPropertyStatus();
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs6 = myStmt.executeQuery("select * from accounts where email="+property.getOwnerEmail());
            int account_id = myRs6.getInt("account_id");
            myStmt.executeUpdate("INSERT INTO `Properties`" +
                            "(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) " +
                            "VALUES" + " ("+account_id+","+address+","+type+","+numBedrooms+","+numBathrooms+","+isFurnished+","+cityQuadrant+","+days+","+status+")");
            ResultSet myRs5 = myStmt.executeQuery("select * from properties");
            while (myRs5.next()) {
                property_id = myRs5.getInt("property_id");
            }
            return property_id;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    }
    public int getFee() {
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs7 = myStmt.executeQuery("select * from Financing");
            return myRs7.getInt("fee");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    };
    public int getPeriod() {
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs8 = myStmt.executeQuery("select * from Financing");
            return myRs8.getInt("period");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    };

    public int getBalance() {
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs9 = myStmt.executeQuery("select * from Financing");
            return myRs9.getInt("balance");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    };

    public void updateFee(int fee) {
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Financing` set fee ="+fee);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    };
    public void updatePeriod(int period) {
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Financing` set period ="+period);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    };
    public void updateBalance(int deposit) {
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Financing` set balance ="+deposit);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    };

    public static void main(String[] args)
    {
        DatabaseController database = DatabaseController.getInstance();
    }
}