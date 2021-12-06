package Models;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import SystemControllers.*;
import Interfaces.*;
import InteractionControllers.*;

public class ManagerAccount extends Account{

    private DatabaseController database = DatabaseController.getInstance();
    private Period summaryPeriod;
    private FilterBuilder filter;

    public ManagerAccount(String email, String username, String password) {
      super(email,username,password,AccountType.Manager);
    }

    public ManagerAccount(String email, String username, String password, int accountID) {
      super(email,username,password,AccountType.Manager);
      setAccountID(accountID);
    }

    public void updateFeePeriod(Period feePeriod) {
      int newPeriod = Input.getIntInput("Enter new period start date");
      FeeController.setPeriod(newPeriod);
    }


    public void updateFees() {
      int fee = FeeController.getFee();
      String message = "Current Fees: " + fee + "\nEnter New Fee:";
      int newFee = Input.getIntInput(message);
      FeeController.setFee(newFee);
    }

    public void updateFilterPeriod(){
      // Set period
      Input.getDateInput("When does the filter period start?");
      Input.getDateInput("When does the filter period end?");

      filter.updatePeriod(Period.between(summaryInitDate, nextSummaryDate));

      this.filter.setPeriod();
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

    public void setListingStatus(ArrayList<Property> properties) {
      PropertyHub.updateListing(properties);
    }



// *** If this doesn't work, we may have to get User accounts only by SQL

    public void viewUserInfo() {

      HashMap<Integer,Account> accounts = database.getAccountsHashMap();

      String[][] row_data = new String[accounts.size()][5];
      String[] col_headers = new String[5];

      col_headers = {"Account ID", "Account Type", "Email", "Username", "Owned Properties"};

      // Get all Values - Integer should be hidden from user
      Property[] accountArray = accounts.values().toArray();

      // Place into rows
      for(int q = 0; q < accountArray.size(); q++){

        row_data[q][0] = String.valueOf(accountArray[q].getAccountID());
        row_data[q][1] = String.valueOf(accountArray[q].getAccountType());
        row_data[q][2] = accountArray[q].getEmail();
        row_data[q][3] = accountArray[q].getUsername();

        if(row_data[q][1].equals("User")){
          ArrayList<Property> owned = accountArray[q].getOwnedProperties();
          ArrayList<int> propertyIDs = owned.stream().map(o -> o.getPropertyID()).collect(Collectors.toArrayList());
          String stringOwned = propertyIDs.stream().map(Object::toString).collect(Collectors.joining(", "));
          row_data[q][4] = stringOwned;
        }
        else{
          row_data[q][4] = " ";
        }
      }

      Output.displayStringArray(row_data, col_headers);
    }
}
