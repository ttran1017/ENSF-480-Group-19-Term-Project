package Models;

import java.util.*;
import Interfaces.*;
import SystemControllers.PropertyHub;


public class UserAccount extends Account {

    private String email;
    private String username;
    private String password;
    private ArrayList<Property> ownedProperties;
    private PropertyViewer viewer;
    private Filter filter;
    private boolean subscription;
    
    public UserAccount(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        setAccountType(1);
    }

    public void registerProperty()
    {
        Property myProperty = PropertyHub.createProperty(email);
        ownedProperties.add(myProperty);
    }

    public void PostProperty()
    {
        
    }
}