package Models;

import java.util.*;

import Interfaces.AccountType;
import SystemControllers.EmailController;
import SystemControllers.PropertyHub;

public class UserAccount extends Account {
  private ArrayList<Property> ownedProperties;
  private PropertyViewer viewer;

  public UserAccount(String email, String username, String password) 
  {
    super(email,username,password,AccountType.User);
    this.ownedProperties = new ArrayList<Property>();
    this.viewer = new PropertyViewer(email);
  }

  public UserAccount(
    String email, 
    String username, 
    String password, 
    int accountID, 
    ArrayList<Property> ownedProperties,
    Filter filter,
    boolean subscription) 
  {
    super(email,username,password,AccountType.User);
    this.ownedProperties = ownedProperties;
    this.viewer = new PropertyViewer(email,filter,subscription);
    setAccountID(accountID);
  }

  public void registerProperty()
  {
    Property property = PropertyHub.getInstance().createProperty(this);
    if(property != null)
      ownedProperties.add(property);
  }

  public void postProperty() { PropertyHub.getInstance().postProperty(ownedProperties); }

  public void updateProperty()
  {
    viewMyProperties();
    PropertyHub.getInstance().updatePropertyStatus(ownedProperties);
  }

  public void updateFilter() { viewer.updateFilter(accountID); }
  public void updateSubscription() { viewer.updateSubscription(accountID); }
  public void viewMyProperties() { PropertyViewer.viewProperties(ownedProperties); }
  public void viewAllProperties() { viewer.viewProperties(); }
  public ArrayList<Property> getOwnerProperties() { return this.ownedProperties; }
  public void sendEmail()
  {
    Property selectedProperty = PropertyHub.getInstance().selectProperty();
    EmailController.sendEmail(email, selectedProperty.getOwnerEmail(), selectedProperty.getPropertyID());
    return;
  }

  public static void main(String[] args)
  {
    UserAccount user = new UserAccount("oo@gmail.com","000","12qwaszx");
  }
}
