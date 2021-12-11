/**
 * FileName: Account.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Models;

import Enums.AccountType;

/**
 * Account Abstract class
 */
public abstract class Account {

    /**
     * Account fields
     */
    protected AccountType accountType;
    protected int accountID;
    protected String email;
    protected String username;
    protected String password;

    /**
     * Account constructor
     * @param email Account email
     * @param username Account username
     * @param password Account password
     * @param accountType User or Manager
     */
    public Account(String email, String username, String password, AccountType accountType)
    {
        this.accountType = accountType;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * get AccountType
     * @return accountType
     */
    public AccountType getAccountType() { return accountType; }
    
    /**
     * get email
     * @return email
     */
    public String getEmail(){ return email; }

    /**
     * get username
     * @return username
     */
    public String getUsername(){ return username; }

    /**
     * get password
     * @return password
     */
    public String getPassword(){ return password; }

    /**
     * get accountId
     * @return accountID
     */
    public int getAccountID() { return accountID; }

    /**
     * set accountId
     * @param id accountID
     */
    public void setAccountID(int id) { this.accountID = id; }
}