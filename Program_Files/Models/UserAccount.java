/**
 * FileName: UserAccount.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Models;

import java.util.*;

import Interfaces.AccountType;
import SystemControllers.EmailController;
import SystemControllers.PropertyHub;

/**
 * User Account is for both:
 * - Landlords
 * - Renters
 * Can register and post properties as well as be notified of new property listings
 */
public class UserAccount extends Account {

  /**
   * UserAccount Fields
   */
  private ArrayList<Property> ownedProperties;
  private PropertyViewer viewer;

  /**
   * UserAccount Constructor for new Account
   * @param email User's email
   * @param username User's username
   * @param password User's password
   */
  public UserAccount(String email, String username, String password) 
  {
    super(email,username,password,AccountType.User);
    this.viewer = new PropertyViewer(email);
  }

  /**
   * Constructor for pre-existing user
   * @param email Pre-existing User's email
   * @param username Pre-existing User's username
   * @param password Pre-existing User's password
   * @param filter Pre-existing User's filter
   * @param sub Pre-existing User's subscription
   */
  public UserAccount(
    int accountID,
    String email, 
    String username, 
    String password, 
    Filter filter, 
    boolean sub)
  {
    super(email,username,password,AccountType.User);
    setAccountID(accountID);
    this.viewer = new PropertyViewer(email, filter, sub);
  }
  /**
   * Register a new property
   */
  public void registerProperty()
  {
    Property property = PropertyHub.getInstance().createProperty(this);
    if(property != null)
      ownedProperties.add(property);
  }

  /**
   * Post a property which the user has registered
   */
  public void postProperty() { PropertyHub.getInstance().postProperty(ownedProperties); }

  /**
   * Update an active property listing to be cancelled, rented, or suspended
   */
  public void updateProperty()
  {
    viewMyProperties();
    PropertyHub.getInstance().userUpdatePropertyStatus(ownedProperties);
  }

  /**
   * Update user's filter
   */
  public void updateFilter() { viewer.updateFilter(accountID); }

  /**
   * Update user's subscription to notifications
   */
  public void updateSubscription() { viewer.updateSubscription(accountID); }

  /**
   * View user's owned properties
   */
  public void viewMyProperties() { PropertyViewer.viewProperties(ownedProperties); }

  /**
   * View public listings
   */
  public void viewAllProperties() { viewer.viewProperties(); }

  /**
   * Get all owned properties
   * @return all owned properties
   */
  public ArrayList<Property> getOwnedProperties() { return this.ownedProperties; }

  /**
   * Set owned properties
   * @param properties
   */
  public void setOwnedProperties(ArrayList<Property> properties) { this.ownedProperties = properties; }

  /**
   * Send an email to landlord about selected property
   */
  public void sendEmail()
  {
    Property selectedProperty = PropertyHub.getInstance().selectProperty();
    EmailController.sendEmail(email, selectedProperty.getOwnerEmail(), selectedProperty.getPropertyID());
    return;
  }

  public static void main(String[] args)
  {
    UserAccount user = new UserAccount("oo@gmail.com","000","12qwaszx");
    user.sendEmail();
  }
}
