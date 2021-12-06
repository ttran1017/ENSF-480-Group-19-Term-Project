package SystemControllers;

import Interfaces.PropertyType;
import Interfaces.AccountType;
import Interfaces.PropertyQuadrant;
import Interfaces.PropertyStatus;
import Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class DatabaseController {
    private static DatabaseController INSTANCE;
    private static final String DBURL = "jdbc:mysql://localhost/prms_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12qwaszx";
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

/* NOT NEEDED YET COMMENTED OUT DUE TO COMPILE ISSUES
    public Property getProperty(int property_id) {
        Property selectedProperty=null;
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from properties join accounts on properties.account_id = accounts.account_id where properties.property_id=\""+property_id+"\"");
            if (myRs.next()){
                selectedProperty.setPropertyId(property_id);
                selectedProperty.setOwnerID(myRs.getInt("account_id"));
                selectedProperty.setOwnerEmail(myRs.getString("email"));
                selectedProperty.setPropertyAddress(myRs.getString("address"));

                if (myRs.getString("type")=="apartment")
                    selectedProperty.setPropertyType(PropertyType.Apartment);
                else if (myRs.getString("type")=="attached house")
                    selectedProperty.setPropertyType(PropertyType.AttachedHouse);
                else if (myRs.getString("type")=="detached house")
                    selectedProperty.setPropertyType(PropertyType.DetachedHouse);
                else if (myRs.getString("type")=="townhouse")
                    selectedProperty.setPropertyType(PropertyType.Townhouse);
                else if (myRs.getString("type")=="condo")
                    selectedProperty.setPropertyType(PropertyType.Condo);

                selectedProperty.setNumBedrooms(myRs.getInt("# of bedrooms"));
                selectedProperty.setNumBathrooms(myRs.getInt("# of bathrooms"));
                selectedProperty.setIsFurnished(myRs.getBoolean("is furnished"));

                if (myRs.getString("city quadrant")=="NE")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.NE);
                else if (myRs.getString("city quadrant")=="NW")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.NW);
                else if (myRs.getString("city quadrant")=="SE")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.SE);
                else if (myRs.getString("city quadrant")=="SW")
                    selectedProperty.setPropertyQuadrant(PropertyQuadrant.SW);

                selectedProperty.setDaysRemaining(myRs.getInt("days"));

                if (myRs.getString("status")=="active")
                    selectedProperty.setPropertyStatus(PropertyStatus.Active);
                else if (myRs.getString("status")=="rented")
                    selectedProperty.setPropertyStatus(PropertyStatus.Rented);
                else if (myRs.getString("status")=="suspended")
                    selectedProperty.setPropertyStatus(PropertyStatus.Suspended);
                else if (myRs.getString("status")=="cancelled")
                    selectedProperty.setPropertyStatus(PropertyStatus.Cancelled);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return selectedProperty;
    }
*/
/* NOT NEEDED YET COMMENTED OUT DUE TO COMPILE ISSUES
    public UserAccount getAccount(int account_id) {
        UserAccount selectedAccount=null;
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from accounts where account_id="+account_id);
            if (myRs.next()){
                selectedAccount.setAccountID(account_id);
                selectedAccount.setAccountType(myRs.getInt("account type"));
                selectedAccount.setEmail(myRs.getString("email"));
                selectedAccount.setUsername(myRs.getString("username"));
                selectedAccount.setPassword(myRs.getString("password"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return selectedAccount;
    }
*/

    public ArrayList<Property> getAllProperties(int account_id) {
        ArrayList<Property> userProperties= new ArrayList<Property>();
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from properties join accounts on properties.account_id = accounts.account_id where accounts.account_id=\""+account_id+"\"");
            while (myRs.next()){
                Property selectedProperty = new Property();
                selectedProperty.setPropertyId(myRs.getInt("property_id"));
                selectedProperty.setOwnerID(account_id);
                selectedProperty.setOwnerEmail(myRs.getString("email"));
                selectedProperty.setPropertyAddress(myRs.getString("address"));
                selectedProperty.setNumBedrooms(myRs.getInt("# of bedrooms"));
                selectedProperty.setNumBathrooms(myRs.getInt("# of bathrooms"));
                selectedProperty.setIsFurnished(myRs.getBoolean("is furnished"));
                selectedProperty.setDaysRemaining(myRs.getInt("days"));
                selectedProperty.setPropertyType(PropertyType.valueOf(myRs.getString("type")));
                selectedProperty.setPropertyQuadrant(PropertyQuadrant.valueOf(myRs.getString("city quadrant")));
                selectedProperty.setPropertyStatus(PropertyStatus.valueOf(myRs.getString("status")));
                userProperties.add(selectedProperty);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return userProperties;
    }
    
    public HashMap<Integer,Account> getAccountsHashMap() {
        HashMap<Integer,Account> accounts = new HashMap<Integer,Account>();
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from accounts");
            while (myRs.next()){
                if (AccountType.valueOf(myRs.getString("account type")) == AccountType.User)
                {
                    accounts.put(myRs.getInt("account_id"),
                        new UserAccount(
                            myRs.getString("email"), 
                            myRs.getString("username"), 
                            myRs.getString("password"), 
                            myRs.getInt("account_id"), 
                            getAllProperties(myRs.getInt("account_id")),
                            getFilter(myRs.getInt("account_id")),
                            getSubscription(myRs.getInt("account_id"))));
                }
                else if (AccountType.valueOf(myRs.getString("account type")) == AccountType.Manager){
                    accounts.put(myRs.getInt("account_id"),
                        new ManagerAccount(
                            myRs.getString("email"), 
                            myRs.getString("username"), 
                            myRs.getString("password"), 
                            myRs.getInt("account_id"))
                        );
                }
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return accounts;
    }

    public HashMap<Integer,Property> getPropertiesHashMap() {
        HashMap<Integer,Property> properties = new HashMap<Integer,Property>();
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery
                    ("select * from properties join accounts on properties.account_id = accounts.account_id order by property_id");
            while (myRs.next()){
                PropertyType type = PropertyType.valueOf(myRs.getString("type"));
                PropertyQuadrant cityQuadrant = PropertyQuadrant.valueOf(myRs.getString("city quadrant"));
                PropertyStatus status = PropertyStatus.valueOf(myRs.getString("status"));
                Property temp = new Property(
                    myRs.getInt("account_id"),
                    myRs.getString("email"),
                    type,
                    myRs.getString("address"),
                    cityQuadrant,
                    status,
                    myRs.getInt("# of bedrooms"),
                    myRs.getInt("# of bathrooms"),
                    myRs.getBoolean("is furnished"),
                    myRs.getInt("days"));
                temp.setPropertyId(myRs.getInt("property_id"));
                properties.put(myRs.getInt("property_id"),temp);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return properties;
    }

    public int verifyLogin(String username, String password)
    {
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from accounts where username=\""+username+"\" and password=\""+password+"\"");
            if (myRs.next())
                return myRs.getInt("account_id");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    }

    public int verifyRegistration(String email, String username)
    {
        try
        {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from accounts where username=\""+username+"\" or email=\""+email+"\"");
            while (myRs.next()) 
            {
                if (myRs.getString("email").equals(email))
                    return 1;
                else if (myRs.getString("username").equals(username))
                    return 2;
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }

    public int addAccount(Account account)
    {
        String email = account.getEmail();
        String username = account.getUsername();
        String password = account.getPassword();
        AccountType type = account.getAccountType();
        int id=0;
        try{
            Statement myStmt = database.createStatement();
            Statement myStmt2 = database.createStatement();
            myStmt.executeUpdate("INSERT INTO `Accounts`(`account type`,email,username,password) VALUES (\""+type+"\",\""+email+"\",\""+username+"\",\""+password+"\")");
            ResultSet myRs = myStmt.executeQuery("select * from accounts where username=\""+username+"\" and password=\""+password+"\"");
                if (myRs.next()) {
                    id=myRs.getInt("account_id");
                    if (type==AccountType.User)
                    {
                        myStmt.executeUpdate("INSERT INTO `Filters`(account_id) VALUES (\""+myRs.getInt("account_id")+"\")");
                        ResultSet myRs2 = myStmt.executeQuery("select * from accounts where username=\""+username+"\" and password=\""+password+"\"");
                        if(myRs2.next())
                            myStmt2.executeUpdate("INSERT INTO `Subscriptions`(account_id) VALUES (\""+myRs2.getInt("account_id")+"\")");
                    }
                    return id;
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
            Boolean isFurnished = property.getIsFurnished();
            int isF=(isFurnished) ? 1 : 0;
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Properties` set address =\""+property.getPropertyAddress()+"\" where property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Properties` set type =\""+property.getPropertyType()+"\" where property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Properties` set `# of bedrooms` =\""+property.getNumBedrooms()+"\" where property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Properties` set `# of bathrooms` =\""+property.getNumBathrooms()+"\" where property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Properties` set `city quadrant` =\""+property.getPropertyQuadrant()+"\" where property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Properties` set `is furnished` =\""+isF+"\" where property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Properties` set `days` =\""+property.getDaysRemaining()+"\" where property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Properties` set `status` =\""+property.getPropertyStatus()+"\" where property_id=\""+property.getPropertyID()+"\"");

            ResultSet myRs = myStmt.executeQuery("select * from Properties join Accounts on Properties.account_id = Accounts.account_id where properties.property_id=\""+property.getPropertyID()+"\"");
            myStmt.executeUpdate("update `Accounts` set `email` =\""+property.getOwnerEmail()+"\" where account_id=\""+myRs.getInt("account_id")+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    }


    public int addProperty(Property property)
    {
        int property_id = -1;
        int account_id = property.getOwnerID();
        String address = property.getPropertyAddress();
        PropertyType type = property.getPropertyType();
        int numBedrooms = property.getNumBedrooms();
        int numBathrooms = property.getNumBathrooms();
        Boolean isFurnished = property.getIsFurnished();
        PropertyQuadrant cityQuadrant = property.getPropertyQuadrant();
        int days = property.getDaysRemaining();
        PropertyStatus status = property.getPropertyStatus();
        int isF=(isFurnished) ? 1 : 0;
        try{
            Statement myStmt = database.createStatement();
            Statement myStmt2 = database.createStatement();
            ResultSet myRs;
            myStmt.executeUpdate("INSERT INTO `Properties`" +
                            "(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) " +
                            "VALUES" + " (\""+account_id+"\",\""+address+"\",\""+type+"\",\""+numBedrooms+"\",\""+numBathrooms+"\",\""+isF+"\",\""+cityQuadrant+"\",\""+days+"\",\""+status+"\")");
            myRs = myStmt2.executeQuery("select * from properties");
            while (myRs.next()) {
                property_id = myRs.getInt("property_id");
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
            ResultSet myRs = myStmt.executeQuery("select * from Financing");
            if(myRs.next())
                return myRs.getInt("fee");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    };

    public int getPeriod() {
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Financing");
            if(myRs.next())
                return myRs.getInt("period");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    };

    public int getBalance() {
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Financing");
            if(myRs.next())
                return myRs.getInt("balance");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return -1;
    };

    public void updateFee(int fee) {
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Financing` set fee =\""+fee+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    };

    public void updatePeriod(int period) {
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Financing` set period =\""+period+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    };

    public void updateBalance(int deposit) {
        int balance =0;
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Financing");
            balance=myRs.getInt("balance");
            myStmt.executeUpdate("update `Financing` set balance = balance +\""+deposit+balance+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    };

    public Filter getFilter(int account_id){
        FilterBuilder filter = new FilterBuilder();
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Filters");
            if(myRs.next())
            {
                if(myRs.getString("property type") != null)
                    filter.setPropertyType(PropertyType.valueOf(myRs.getString("property type")));
                if(myRs.getString("property quadrant") != null)
                    filter.setPropertyQuad(PropertyQuadrant.valueOf(myRs.getString("property quadrant")));
                filter.setMinBedroom(myRs.getInt("minimum bedrooms"));
                filter.setMaxBedroom(myRs.getInt("maximum bedrooms"));
                filter.setMinBathroom(myRs.getInt("minimum bathrooms"));
                filter.setMaxBathroom(myRs.getInt("maximum bathrooms"));
                filter.setIsFurnished(myRs.getBoolean("is furnished")); 
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return filter.build();
    }

    public void updateFilter(int account_id, Filter filter){
        try{
            Statement myStmt = database.createStatement();
            if (filter.getPropertyType()==null)
                myStmt.executeUpdate("update `Filters` set `property type`= 'null' where account_id=\""+account_id+"\"");
            else myStmt.executeUpdate("update `Filters` set `property type`=\""+filter.getPropertyType()+"\" where account_id=\""+account_id+"\"");
            if (filter.getPropertyQuad()==null)
                myStmt.executeUpdate("update `Filters` set `property quadrant`= 'null' where account_id=\""+account_id+"\"");
            else myStmt.executeUpdate("update `Filters` set `property quadrant`=\""+filter.getPropertyQuad()+"\" where account_id=\""+account_id+"\"");
            if (filter.getMinBedroom()==null)
                myStmt.executeUpdate("update `Filters` set `minimum bedrooms`= -1 where account_id=\""+account_id+"\"");
            else myStmt.executeUpdate("update `Filters` set `minimum bedrooms`=\""+filter.getMinBedroom()+"\" where account_id=\""+account_id+"\"");
            if (filter.getMaxBedroom()==null)
                myStmt.executeUpdate("update `Filters` set `maximum bedrooms`= -1 where account_id=\""+account_id+"\"");
            else myStmt.executeUpdate("update `Filters` set `maximum bedrooms`=\""+filter.getMaxBedroom()+"\" where account_id=\""+account_id+"\"");
            if (filter.getMinBathroom()==null)
                myStmt.executeUpdate("update `Filters` set `minimum bathrooms`= -1 where account_id=\""+account_id+"\"");
            else myStmt.executeUpdate("update `Filters` set `minimum bathrooms`=\""+filter.getMinBathroom()+"\" where account_id=\""+account_id+"\"");
            if (filter.getMaxBathroom()==null)
                myStmt.executeUpdate("update `Filters` set `maximum bathrooms`= -1 where account_id=\""+account_id+"\"");
            else myStmt.executeUpdate("update `Filters` set `maximum bathrooms`=\""+filter.getMaxBathroom()+"\" where account_id=\""+account_id+"\"");
            if (filter.getFurnished()==null)
                myStmt.executeUpdate("update `Filters` set `is furnished`= -1 where account_id=\""+account_id+"\"");
            else myStmt.executeUpdate("update `Filters` set `is furnished`=\""+(filter.getFurnished()?1:0)+"\" where account_id=\""+account_id+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    }

    public boolean getSubscription(int account_id){
        boolean sub = true;
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Subscriptions where account_id=\""+account_id+"\"");
            if(myRs.next())
                sub= myRs.getBoolean("subscribed");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return sub;
    }

    public void updateSubscription(int account_id, boolean sub){
        int subbed=(sub) ? 1 : 0;
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Subscriptions` set subscribed =\""+subbed+"\" where account_id=\""+account_id+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    }


    public static void main(String[] args)
    {
        DatabaseController database = DatabaseController.getInstance();
    }
}