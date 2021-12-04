package Models;

import java.util.*;
import SystemControllers.*;
import Interfaces.*;


public class ManagerAccount extends Account{

    private DatabaseController database = DatabaseController.getInstance();

    public ManagerAccount(String email, String username, String password) {
      // init DatabaseController
      this.email = email;
      this.username = username;
      this.password = password;
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
