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


    public void generateSummary() {

      // Requires a filter

//       Total number of houses listed in the period. Notice that some houses that are listed may not be active
// anymore. It means some houses their posting period can be expired or landlords have cancelled their
// posting, therefore the renters cannot view them anymore.

    }

    // Initializes periodic summaries
    public void periodicSummaryInit(){
      LocalDate summaryInitDate = LocalDate.now();
      LocalDate nextSummaryDate = Input.getDateInput("When would you like the next report?");
      summaryPeriod = Period.between(summaryInitDate, nextSummaryDate);
    }

    public void setListingStatus() {
      PropertyHub.getInstance().updatePropertyStatus(PropertyHub.getPropertyList());
    }


    public void viewUserInfo() {
      Output.displayAccounts(AccountHandler.getAccountList());
    }

    public void sendEmail() {
    }
}
