package Models;

import java.util.*;
import Interfaces.*;


public class UserAccount extends Account {

    private String email;
    private String username;
    private String password;
    private ArrayList<Property> ownedProperties;
    private PropertyViewer viewer;
    private FilterBuilder filterBuilder;
    private Filter filter;
    private boolean subscription;

    public UserAccount(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        setAccountType(1);
    }

    public ArrayList<Property> getProperties(){
      return this.ownedProperties;
    }

    public void registerProperty(String propType, String propAddr, String propQuad, int numBed, int numBath, boolean isFurnished){
      Property property = new Property(propType, propAddr, propQuad, numBed, numBath, isFurnished); // Use PropertyHub
      ownedProperties.add(property);
    }

    public void updateListing(){

    }

    public void updateFilterPropType(String m){
      this.filter = filterBuilder.setPropertyType(m);
    }

    public void updateFilterPropQuad(String m){
      this.filter = filterBuilder.setPropertyQuad(m);
    }

    public void updateFilterMinBedroom(int n){
      this.filter = filterBuilder.setMinBedroom(n);
    }

    public void updateFilterMaxBedroom(int n){
      this.filter = filterBuilder.setMaxBedroom(n);
    }

    public void updateFilterIsFurnished(boolean b){
      this.filter = filterBuilder.setIsFurnished(b);
    }

    public void setSubscription(boolean v){
      this.subscription = v;
    }
}
