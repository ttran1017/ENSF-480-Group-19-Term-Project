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
    this.ownedProperties = new ArrayList<Property>();
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
   * Constructs property viewer
   * @param filter filter object used in viewer
   * @param subscription subscription status
   */
  public void buildPropertyViewer(Filter filter, boolean subscription) { viewer = new PropertyViewer(email, filter, subscription); }

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
