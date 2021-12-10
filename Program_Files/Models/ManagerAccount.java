/**
 * FileName: ManagerAccount.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Models;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import SystemControllers.*;
import Interfaces.*;
import InteractionControllers.*;

public class ManagerAccount extends Account{

    private DatabaseController database = DatabaseController.getInstance();
    private LocalDate startDate = LocalDate.of(1990, 1, 1);
    private LocalDate endDate = LocalDate.of(9999, 1, 1);;


    /**
     * ManagerAccount constructor
     * @param String email - takes in email
     * @param String username - takes in username
     * @param String password - sets user's password
     */
    public ManagerAccount(String email, String username, String password) {
      super(email,username,password,AccountType.Manager);
    }

    /**
     * ManagerAccount constructor
     * @param String email - takes in email
     * @param String username - takes in username
     * @param String password - sets user's password
     * @param int accountID - sets user's account ID
     *
     */
    public ManagerAccount(String email, String username, String password, int accountID) {
      super(email,username,password,AccountType.Manager);
      setAccountID(accountID);
    }

    /**
     * Update Fee period by calling the fee controller's setPeriod method
     */
    public void updateFeePeriod() {
      FeeController.setPeriod();
    }

    /**
     * Update fees by calling the fee controller's setFee method
     */
    public void updateFees() {
      FeeController.setFee();
    }

    /**
     * Update the period
     *
     */
    public void updateFilterPeriod(){
      // Set period
      startDate = Input.getDateInput("Enter start date");
      endDate = Input.getDateInput("Enter end date");
    }

    /**
     * Generate summary using the database
     *
     */
    public void generateSummary() {
      // Convert filter period to string
      ArrayList<Property> rented = database.getRentedProperties(startDate.toString(), endDate.toString());
      ArrayList<Property> listed = database.getListedProperties(startDate.toString(), endDate.toString());

      // Get total numbers
      int totListed = listed.size();
      int totRented = rented.size();
      int totActiveListed = 0;
      int currRow= 0;

      // Get total active listing
      for(Property listing : listed){
        if(listing.getPropertyStatus() == PropertyStatus.Active){
          totActiveListed++;
        }
      }

      // Initialize String arrays
      String[][] row_data = new String[totListed + totRented][5];
      String[] col_headers = new String[]{"Property ID", "Landlord Username", "Address","Type","Date"};

      // Get rented rows
      for(int g = 0; g < rented.size(); g++, currRow++){
        row_data[currRow][0] = String.valueOf(rented.get(g).getPropertyID());
        row_data[currRow][1] = AccountHandler.getAccountByID(rented.get(g).getOwnerID()).getUsername();
        row_data[currRow][2] = rented.get(g).getPropertyAddress();
        row_data[currRow][3] = "Rented";
        row_data[currRow][4] = database.getDateRented(rented.get(g).getPropertyID());
      }

      // Get listed rows
      for(int g = 0; g < listed.size(); g++, currRow++){
        row_data[currRow][0] = String.valueOf(listed.get(g).getPropertyID());
        row_data[currRow][1] = AccountHandler.getAccountByID(listed.get(g).getOwnerID()).getUsername();
        row_data[currRow][2] = listed.get(g).getPropertyAddress();
        row_data[currRow][3] = "Listed";
        row_data[currRow][4] = database.getDateListed(listed.get(g).getPropertyID());
      }

      // Display result
      Output.displaySummary(row_data, col_headers, totListed, totRented, totActiveListed);
    }

    /**
     * Modify the property listing status
     *
     */
    public void modifyListing() {
      PropertyHub.getInstance().managerUpdatePropertyStatus();
    }

    /**
     * View the properties by calling viewProperties method
     */
    public void viewPropertyInfo()
    {
      PropertyViewer.viewProperties(PropertyHub.getPropertyList());
    }

    /**
     * View the properties by calling viewProperties method
     */
    public void viewUserInfo() {

      HashMap<Integer,Account> accounts = DatabaseController.getInstance().getAccountsHashMap();

      String row_data[][] = new String[accounts.size()][5];
      String col_headers[] = new String[]{"Account ID", "Account Type", "Email", "Username", "Owned Properties"};

      // Get all Values - Integer should be hidden from user
      Account[] accountArray = Arrays.copyOf(accounts.values().toArray(),accounts.size(), Account[].class);

      // Place into rows
      for(int q = 0; q < accountArray.length; q++){

        row_data[q][0] = String.valueOf(accountArray[q].getAccountID());
        row_data[q][1] = String.valueOf(accountArray[q].getAccountType());
        row_data[q][2] = accountArray[q].getEmail();
        row_data[q][3] = accountArray[q].getUsername();

        if(row_data[q][1].equals("User")){
          ArrayList<Property> owned = database.getAllProperties(((UserAccount)accountArray[q]).getAccountID());
          ArrayList<Integer> propertyIDs = new ArrayList<Integer>(owned.stream().map(o -> o.getPropertyID()).collect(Collectors.toList()));
          String stringOwned = propertyIDs.stream().map(Object::toString).collect(Collectors.joining(", "));
          row_data[q][4] = stringOwned;
        }
        else{
          row_data[q][4] = " ";
        }
      }

      Output.displayStringArray(row_data, col_headers);
    }

    public static void main(String[] args)
    {
      ManagerAccount acc = new ManagerAccount("something", "username", "password");
      acc.generateSummary();
    }
}
