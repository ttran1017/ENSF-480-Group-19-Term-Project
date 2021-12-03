package Models;

import java.util.*;

import Interfaces.Account;


public class UnregisteredAccount implements Account {

    
    private int accountType = 0;    
    private String username = null;
    private String password = null;
    private String email = null;

    public UnregisteredAccount() {
    }

        
    public void viewProperties() {           
    }

    
    public void sendEmail() {    
    }
}