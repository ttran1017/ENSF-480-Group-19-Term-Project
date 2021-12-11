/**
 * FileName: DatabaseController.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package SystemControllers;

import Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import Enums.AccountType;
import Enums.PropertyQuadrant;
import Enums.PropertyStatus;
import Enums.PropertyType;

public final class DatabaseController {
    private static DatabaseController INSTANCE;
    private static final String DBURL = "jdbc:mysql://localhost/prms_database";
    private static String USERNAME;
    private static String PASSWORD;
    private Connection database;

    /**
     * DatabaseController default constructor
     */
    private DatabaseController() {
        try{
            database = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /**
     * @return DatabaseController instance
     */
    public static DatabaseController getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new DatabaseController();
        }
        return INSTANCE;
    }

    /**
     * sets SQL username and password
     */
    public static void initialize(String username, String password)
    {
        USERNAME = username;
        PASSWORD = password;
    }

    /**
     * gets all properties associated with an account_id from SQL database in ArrayList form
     * @param int account_id - takes in account_id
     * @return ArrayList<Property>
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


    /**
     * gets all listed properties between start and end date
     * @param String start - takes in start date
     * @param String end - takes in end date
     * @return ArrayList<Property>
     */
    public ArrayList<Property> getListedProperties(String start, String end) {
        ArrayList<Property> periodProperties= new ArrayList<Property>();
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from properties join accounts on properties.account_id = accounts.account_id where properties.`date listed`>=\""+start+"\" and properties.`date listed` <=\""+end+"\"");
            while (myRs.next()){
                Property selectedProperty = new Property();
                selectedProperty.setPropertyId(myRs.getInt("property_id"));
                selectedProperty.setOwnerID(myRs.getInt("account_id"));
                selectedProperty.setOwnerEmail(myRs.getString("email"));
                selectedProperty.setPropertyAddress(myRs.getString("address"));
                selectedProperty.setNumBedrooms(myRs.getInt("# of bedrooms"));
                selectedProperty.setNumBathrooms(myRs.getInt("# of bathrooms"));
                selectedProperty.setIsFurnished(myRs.getBoolean("is furnished"));
                selectedProperty.setDaysRemaining(myRs.getInt("days"));
                selectedProperty.setPropertyType(PropertyType.valueOf(myRs.getString("type")));
                selectedProperty.setPropertyQuadrant(PropertyQuadrant.valueOf(myRs.getString("city quadrant")));
                selectedProperty.setPropertyStatus(PropertyStatus.valueOf(myRs.getString("status")));
                periodProperties.add(selectedProperty);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return periodProperties;
    }

    /**
     * gets all rented properties between start and end date
     * @param String start - takes in start date
     * @param String end - takes in end date
     * @return ArrayList<Property>
     */
    public ArrayList<Property> getRentedProperties(String start, String end) {
        ArrayList<Property> periodProperties= new ArrayList<Property>();
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from properties join accounts on properties.account_id = accounts.account_id where properties.`date rented`>=\""+start+"\" and properties.`date rented` <=\""+end+"\"");
            while (myRs.next()){
                Property selectedProperty = new Property();
                selectedProperty.setPropertyId(myRs.getInt("property_id"));
                selectedProperty.setOwnerID(myRs.getInt("account_id"));
                selectedProperty.setOwnerEmail(myRs.getString("email"));
                selectedProperty.setPropertyAddress(myRs.getString("address"));
                selectedProperty.setNumBedrooms(myRs.getInt("# of bedrooms"));
                selectedProperty.setNumBathrooms(myRs.getInt("# of bathrooms"));
                selectedProperty.setIsFurnished(myRs.getBoolean("is furnished"));
                selectedProperty.setDaysRemaining(myRs.getInt("days"));
                selectedProperty.setPropertyType(PropertyType.valueOf(myRs.getString("type")));
                selectedProperty.setPropertyQuadrant(PropertyQuadrant.valueOf(myRs.getString("city quadrant")));
                selectedProperty.setPropertyStatus(PropertyStatus.valueOf(myRs.getString("status")));
                periodProperties.add(selectedProperty);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return periodProperties;
    }

    /**
     * gets a hashmap of all accounts with account_id as key
     * @return HashMap<Integer,Account>
     */
    public HashMap<Integer,Account> getAccountsHashMap() {
        HashMap<Integer,Account> accounts = new HashMap<Integer,Account>();
        try{
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from accounts");
            while (myRs.next()){
                if (AccountType.valueOf(myRs.getString("account type")) == AccountType.User)
                {
                    int id = myRs.getInt("account_id");
                    accounts.put(id, 
                        new UserAccount(
                            id,
                            myRs.getString("email"), 
                            myRs.getString("username"), 
                            myRs.getString("password"),
                            getFilter(id),
                            getSubscription(id))
                        );
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

    /**
     * gets a hashmap of all properties with account_id as key
     * @return HashMap<Integer,Property>
     */
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

    /**
     * checks if the entered username and password are correct and returns corresponding account_id
     * @param String username - takes in username
     * @param String password - takes in password
     * @return int
     */
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

    /**
     * checks if the entered username and email are already taken or not
     * return 1 if email exists or 2 if username exists. If non exist, return 0.
     * @param String email - takes in email
     * @param String username - takes in username
     * @return int
     */
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

    /**
     * adds the given account to the SQL database
     * returns account_id
     * @param Account account - takes in Account object
     * @return int
     */
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

    /**
     * updates the given property on the SQL database
     * @param Property property - takes in Property object
     */
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
            if(myRs.next())
                myStmt.executeUpdate("update `Accounts` set `email` =\""+property.getOwnerEmail()+"\" where account_id=\""+myRs.getInt("account_id")+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    }

    /**
     * adds the given property to the SQL database
     * returns property_id
     * @param Property property - takes in Property object
     * @return int
     */
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

    /**
     * returns the fee from the SQL database
     * @return int
     */
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

    /**
     * returns the period from the SQL database
     * @return int
     */
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

    /**
     * returns the balance from the SQL database
     * @return int
     */
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

    /**
     * updates the fee on the SQL database
     * @param int fee - takes in new fee
     */
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

    /**
     * updates the period on the SQL database
     * @param int period - takes in new period
     */
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

    /**
     * updates the balance on the SQL database
     * @param int balance - takes in new balance
     */
    public void updateBalance(int deposit) {
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `Financing` set balance = balance +\""+deposit+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    };

    /**
     * gets all filters of the given account_id from the SQL database
     * @param int account_id - takes in account_id
     * @return Filter
     */
    public Filter getFilter(int account_id){
        FilterBuilder filter = new FilterBuilder();
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Filters where account_id=\""+account_id+"\"");
            if(myRs.next())
            {
                if(!myRs.getString("property type").equals("null"))
                    filter.setPropertyType(PropertyType.valueOf(myRs.getString("property type")));
                if(!myRs.getString("property quadrant").equals("null"))
                    filter.setPropertyQuad(PropertyQuadrant.valueOf(myRs.getString("property quadrant")));
                if(myRs.getInt("minimum bedrooms") != -1)
                    filter.setMinBedroom(myRs.getInt("minimum bedrooms"));
                if(myRs.getInt("maximum bedrooms") != -1)
                    filter.setMaxBedroom(myRs.getInt("maximum bedrooms"));
                if(myRs.getInt("minimum bathrooms") != -1)
                    filter.setMinBathroom(myRs.getInt("minimum bathrooms"));
                if(myRs.getInt("maximum bathrooms") != -1)
                    filter.setMaxBathroom(myRs.getInt("maximum bathrooms"));
                if(myRs.getInt("is furnished") != -1)
                    filter.setIsFurnished(myRs.getBoolean("is furnished"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return filter.build();
    }

    /**
     * updates the filters of the given account_id on the SQL database
     * @param int account_id - takes in account_id
     * @param Filter filter - takes in Filter object
     */
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

    /**
     * gets the subscription of the given account_id from the SQL database
     * returns true if subscribed, false if not.
     * @param int account_id - takes in account_id
     * @return boolean
     */
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

    /**
     * updates the subscription of the given account_id on the SQL database
     * @param int account_id - takes in account_id
     * @param boolean sub - takes in subscription boolean
     */
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

    /**
     * updates the "date listed" of the given property_id on the SQL database
     * @param int property_id - takes in property_id
     * @param String date - takes in date
     */
    public void updateDateListed(int property_id, String date){
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `properties` set `date listed` =\""+date+"\" where property_id=\""+property_id+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    }

    /**
     * updates the "date rented" of the given property_id on the SQL database
     * @param int property_id - takes in property_id
     * @param String date - takes in date       
     */
    public void updateDateRented(int property_id, String date){
        try{
            Statement myStmt = database.createStatement();
            myStmt.executeUpdate("update `properties` set `date rented` =\""+date+"\" where property_id=\""+property_id+"\"");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return;
    }

    /**
     * gets the "date listed" of the given property_id from the SQL database
     * @param int property_id - takes in property_id
     * @return String
     */
    public String getDateListed(int property_id){
        String date=null;
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from properties where property_id=\""+property_id+"\"");
            if(myRs.next())
                date= myRs.getString("date listed");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return date;
    }


    /**
     * gets the "date rented" of the given property_id from the SQL database
     * @param int property_id - takes in property_id
     * @return String
     */
    public String getDateRented(int property_id){
        String date=null;
        try {
            Statement myStmt = database.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from properties where property_id=\""+property_id+"\"");
            if(myRs.next())
                date= myRs.getString("date rented");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return date;
    }
}