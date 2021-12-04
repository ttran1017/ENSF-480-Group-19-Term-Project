package Models;

import java.util.*;


// 1 : user
// 2 : manager
public abstract class Account {

    private int accountType = 0;

    public int accountType()
    {
        return accountType;
    }

    public void setAccountType(int type)
    {
        this.accountType = type;
    }

}
