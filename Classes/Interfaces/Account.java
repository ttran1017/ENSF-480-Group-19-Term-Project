package Interfaces;

import java.util.*;

/**
 * 
 */
public interface Account {

    /**
     * 
     */
    public int accountType;

    /**
     * 
     */
    public String username;

    /**
     * 
     */
    public String password;

    /**
     * 
     */
    public String email;

    /**
     * @return
     */
    public void sendEmail();

}