package Models;

import java.util.*;
import SystemControllers.PropertyHub;

public class UserAccount extends Account {
  private String email;
  private String username;
  private String password;
  private int accountID;
  private ArrayList<Property> ownedProperties;
  private PropertyViewer viewer;

  public UserAccount(String email, String username, String password) {
      this.email = email;
      this.username = username;
      this.password = password;
      this.ownedProperties = new ArrayList<Property>();
      this.viewer = new PropertyViewer(email);
      setAccountType(1);
  }

  
  public void registerProperty()
  {
    Property property = PropertyHub.getInstance().createProperty(email);
    ownedProperties.add(property);
  }

  public void postProperty()
  {
    PropertyHub.getInstance().postProperty(ownedProperties);
  }

  public void updateProperty()
  {
    viewMyProperties();
    PropertyHub.getInstance().updateProperty(ownedProperties);
  }
  
  public void updateFilter()
  {
    viewer.updateFilter();
  }

  public void viewMyProperties()
  {
    PropertyViewer.viewProperties(ownedProperties);
  }

  public void viewAllProperties()
  {
    viewer.viewProperties();
  }
  
  public String getEmail(){ return email; }
  public String getUsername(){ return username; }
  public String getPassword(){ return password; }
  public ArrayList<Property> getProperties(){ return this.ownedProperties; }

  public void setAccountID(int accountID) {
    this.accountID = accountID;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static void main(String[] args)
  {
    UserAccount user = new UserAccount("oo@gmail.com","000","12qwaszx");
    user.updateFilter();
  }
}
