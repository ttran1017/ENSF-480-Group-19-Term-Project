package Models;

import Interfaces.AccountType;

public abstract class Account {
    protected AccountType accountType;
    protected int accountID;
    protected String email;
    protected String username;
    protected String password;

    public Account(String email, String username, String password, AccountType accountType)
    {
        this.accountType = accountType;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public AccountType getAccountType() { return accountType; }
    public String getEmail(){ return email; }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public int getAccountID() { return accountID; }

    public void setAccountID(int id) { this.accountID = id; }
}
