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
  private boolean subscription;

  public UserAccount(String email, String username, String password) {
      this.email = email;
      this.username = username;
      this.password = password;
      this.viewer = new PropertyViewer();
      setAccountType(1);
  }

  
  public void registerProperty(){
    Property property = PropertyHub.createProperty(email);
    ownedProperties.add(property);
  }
  
  public void updateListing(){
  }
  
  public void updateFilter()
  {
    viewer.updateFilter();
  }
  
  public void setSubscription(boolean v){
    this.subscription = v;
  }
  
  public String getEmail(){ return email; }
  public String getUsername(){ return username; }
  public String getPassword(){ return password; }
  public ArrayList<Property> getProperties(){ return this.ownedProperties; }

  public static void main(String[] args)
  {
    UserAccount user = new UserAccount("oo@gmail.com","000","12qwaszx");
    user.updateFilter();
  }
}
