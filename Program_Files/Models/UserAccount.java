package Models;

import java.util.*;
import Interfaces.*;


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

    // Filter

    // property viewer

    public ArrayList<Property> getProperties(){
      return this.ownedProperties;
    }

    public void registerProperty(String propType, String propAddr, String propQuad, int numBed, int numBath, boolean isFurnished){
      Property property = new Property(propType, propAddr, propQuad, numBed, numBath, isFurnished);
      ownedProperties.add(property);
    }

    public void updateListing(){

    }

    public uploadProperty(){

    }

    public void setSubscription(boolean v){
      this.subscription = v;
    }
}
