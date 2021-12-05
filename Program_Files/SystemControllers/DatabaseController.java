package SystemControllers;

import Interfaces.PropertyType;
import Interfaces.PropertyQuadrant;
import Interfaces.PropertyStatus;
import Models.Account;
import Models.UserAccount;
import Models.Property;
import java.sql.*;
import java.util.HashMap;

public final class DatabaseController {

    private static DatabaseController INSTANCE;
    private static final String DBURL = "jdbc:mysql://localhost/prms_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "09125132465";
    private Connection database;
    HashMap<Integer, UserAccount> accounts;
    HashMap<Integer,Property> properties;
    private Property selectedProperty;
    private UserAccount selectedAccount;

    private DatabaseController() {
        try{
            database = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            accounts= new HashMap<Integer,UserAccount>();
            properties= new HashMap<Integer,Property>();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public Property getProperty(int property_id) {
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs12 = myStmt.executeQuery("select * from properties where property_id="+property_id);
            if (myRs12.next()){
                selectedProperty.setPropertyId(property_id);
                selectedProperty.setPropertyAddress(myRs12.getString("address"));
                if (myRs12.getString("type")=="apartment")
                    selectedProperty.setPropertyType(PropertyType.Apartment);
                else if (myRs12.getString("type")=="attached house")
                    selectedProperty.setPropertyType(PropertyType.AttachedHouse);
                else if (myRs12.getString("type")=="detached house")
                    selectedProperty.setPropertyType(PropertyType.DetachedHouse);
                else if (myRs12.getString("type")=="townhouse")
                    selectedProperty.setPropertyType(PropertyType.Townhouse);
                else if (myRs12.getString("type")=="condo")
                    selectedProperty.setPropertyType(PropertyType.Condo);
                selectedProperty.setNumBedrooms(myRs12.getInt("# of bedrooms"));
                selectedProperty.setNumBathrooms(myRs12.getInt("# of bathrooms"));
                selectedProperty.setIsFurnished(myRs12.getBoolean("is furnished"));
                if (myRs12.getString("city quadrant")=="NE")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.NE);
                else if (myRs12.getString("city quadrant")=="NW")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.NW);
                else if (myRs12.getString("city quadrant")=="SE")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.SE);
                else if (myRs12.getString("city quadrant")=="SW")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.SW);
                selectedProperty.setDaysRemaining(myRs12.getInt("days"));
                if (myRs12.getString("status")=="active")
                    selectedProperty.setPropertyStatus(PropertyStatus.Active);
                else if (myRs12.getString("status")=="rented")
                    selectedProperty.setPropertyStatus(PropertyStatus.Rented);
                else if (myRs12.getString("status")=="suspended")
                    selectedProperty.setPropertyStatus(PropertyStatus.Suspended);
                else if (myRs12.getString("status")=="cancelled")
                    selectedProperty.setPropertyStatus(PropertyStatus.Cancelled);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return selectedProperty;
    }

    public UserAccount getAccount(int account_id) {
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs13 = myStmt.executeQuery("select * from accounts where account_id="+account_id);
            if (myRs13.next()){
                selectedAccount.setAccountID(account_id);
                selectedAccount.setEmail(myRs13.getString("email"));
                selectedAccount.setUsername(myRs13.getString("username"));
                selectedAccount.setPassword(myRs13.getString("password"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return selectedAccount;
    }

    public HashMap<Integer, UserAccount> getAccountsHashMap() {
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs10 = myStmt.executeQuery("select * from accounts");
            while (myRs10.next()){
                this.accounts.put(myRs10.getInt("account_id")
                        ,new UserAccount(myRs10.getString("email")
                                , myRs10.getString("username")
                                , myRs10.getString("password")) {
                        });
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return accounts;
    }

    public HashMap<Integer, Property> getPropertiesHashMap() {
        try{
            Statement myStmt = database.createStatement();
            PropertyType type=null;
            PropertyQuadrant cityQuadrant=null;
            PropertyStatus status=null;
            ResultSet myRs11 = myStmt.executeQuery
                    ("select * from properties join accounts on properties.account_id = accounts.account_id order by property_id");
            while (myRs11.next()){
                if (myRs11.getString("type")=="apartment")
                    type=PropertyType.Apartment;
                else if (myRs11.getString("type")=="attached house")
                    type=PropertyType.AttachedHouse;
                else if (myRs11.getString("type")=="detached house")
                    type=PropertyType.DetachedHouse;
                else if (myRs11.getString("type")=="townhouse")
                    type=PropertyType.Townhouse;
                else if (myRs11.getString("type")=="condo")
                    type=PropertyType.Condo;

                if (myRs11.getString("city quadrant")=="NE")
                    cityQuadrant=PropertyQuadrant.NE;
                else if (myRs11.getString("city quadrant")=="NW")
                    cityQuadrant=PropertyQuadrant.NW;
                else if (myRs11.getString("city quadrant")=="SE")
                    cityQuadrant=PropertyQuadrant.SE;
                else if (myRs11.getString("city quadrant")=="SW")
                    cityQuadrant=PropertyQuadrant.SW;

                if (myRs11.getString("status")=="active")
                    status=PropertyStatus.Active;
                else if (myRs11.getString("status")=="rented")
                    status=PropertyStatus.Rented;
                else if (myRs11.getString("status")=="suspended")
                    status=PropertyStatus.Suspended;
                else if (myRs11.getString("status")=="cancelled")
                    status=PropertyStatus.Cancelled;

                this.properties.put(myRs11.getInt("property_id")
                        ,new Property(myRs11.getString("email")
                                ,type
                                ,myRs11.getString("address")
                                ,cityQuadrant
                                ,status
                                ,myRs11.getInt("# of bedrooms")
                                ,myRs11.getInt("# of bathrooms")
                                ,myRs11.getBoolean("is furnished")
                                ,myRs11.getInt("days")
                                ));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return properties;
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
                if (myRs.next()) {
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
                if (myRs2.getInt("account_id")==-1) {
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
                if (myRs3.next()) {
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
            myStmt.executeUpdate("update `Accounts` set `email` ="+property.getOwnerEmail()+" where account_id="+myRs4.getInt("account_id"));
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