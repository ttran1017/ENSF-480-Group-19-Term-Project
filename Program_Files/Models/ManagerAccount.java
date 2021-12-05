package Models;

import java.util.*;
import SystemControllers.*;
import Interfaces.*;


public class ManagerAccount extends Account{

    private DatabaseController database = DatabaseController.getInstance();

    public ManagerAccount(String email, String username, String password) {
      super(email,username,password);
      setAccountType(2);
    }

    public ManagerAccount(String email, String username, String password, int accountID) {
      super(email,username,password);
      setAccountID(accountID);
      setAccountType(2);
    }

    public void updateFeePeriod() {
    }


    public void updateFees() {
    }


    public void generateSummary() {

    }


    public void editListingStatus() {
    }


    public void viewLandlordInfo() {
    }


    public void viewRenterInfo() {
    }

    public void sendEmail() {
    }
}
