package Models;

public abstract class Account {

    protected int accountType;
    protected int accountID;
    protected String email;
    protected String username;
    protected String password;

    public Account(String email, String username, String password)
    {
        this.email = email;
        this.username = username;
        this.password = password;
        this.accountID = -1;
    }

    public int getAccountType()
    {
        return accountType;
    }

    public void setAccountType(int type)
    {
        this.accountType = type;
    }

    public int getAccountID()
    {
        return accountID;
    }

    public void setAccountID(int id)
    {
        this.accountID = id;
    }

    public String getEmail()
    {
        return email;
    }
}
