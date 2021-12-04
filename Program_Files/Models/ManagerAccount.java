package Models;

import java.util.*;
import SystemControllers.*;
import Interfaces.*;
import InteractionControllers.*

public class ManagerAccount extends Account{

    private DatabaseController database = DatabaseController.getInstance();
    private Period summaryPeriod;

    public ManagerAccount(String email, String username, String password) {
      // init DatabaseController
      this.email = email;
      this.username = username;
      this.password = password;
      setAccountType(2);
    }


    public void updateFeePeriod(Period feePeriod) {
      LocalDate start = Input.getDateInput("Enter new period start date");
      LocalDate end = Input.getDateInput("Enter new period end date");

      Period newPeriod = Period.between(start, end);

      FeeController.setPeriod(newPeriod);
    }


    public void updateFees() {
      int fee = FeeController.getFee();

      String message = "Current fees: " + fee + "\n";

      int newFee = Input.getStringInput(message);

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
      LocalDate summaryInitDate = new LocalDate();

      LocalDate nextSummaryDate = Input.getDateInput("When would you like the next report?");

      summaryPeriod = Period.between(summaryInitDate, nextSummaryDate);
    }


    public void setListingStatus() {
    }


    public void viewLandlordInfo() {
    }


    public void viewRenterInfo() {
    }

    public void sendEmail() {
    }
}
