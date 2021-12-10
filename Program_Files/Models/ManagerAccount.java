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
    private Period summaryPeriod;
    private Filter filter;

    public ManagerAccount(String email, String username, String password) {
      super(email,username,password,AccountType.Manager);
    }

    public ManagerAccount(String email, String username, String password, int accountID) {
      super(email,username,password,AccountType.Manager);
      setAccountID(accountID);
    }

    public void updateFeePeriod(Period feePeriod) {
      FeeController.setPeriod();
    }


    public void updateFees() {
      FeeController.setFee();
    }

    public void updateFilterPeriod(){
      // Set period
      filter = FilterBuilder.buildPeriodFilter();
    }


    public void generateSummary() {

      




//       Total number of houses listed in the period.

// o Total number of houses rented in the period
// o Total number of active listing.
// o List of houses rented in the period. Which displays, the landlord’s name, the house’s id number,
// followed by its address.




    }

    // Initializes periodic summaries
    public void periodicSummaryInit(){
      LocalDate summaryInitDate = LocalDate.now();
      LocalDate nextSummaryDate = Input.getDateInput("When would you like the next report?");
      summaryPeriod = Period.between(summaryInitDate, nextSummaryDate);
    }

    public void modifyListing() {
      PropertyHub.getInstance().managerUpdatePropertyStatus();
    }

    public void viewPropertyInfo()
    {
      PropertyViewer.viewProperties(PropertyHub.getPropertyList());
    }


// *** If this doesn't work, we may have to get User accounts only by SQL

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
      acc.viewUserInfo();
    }
}
