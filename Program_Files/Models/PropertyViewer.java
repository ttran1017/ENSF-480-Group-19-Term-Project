/**
 * FileName: PropertyViewer.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Models;

import Interfaces.Observer;
import SystemControllers.DatabaseController;
import SystemControllers.EmailController;
import SystemControllers.FilterBuilder;
import SystemControllers.PropertyHub;
import java.util.ArrayList;

import InteractionControllers.Input;
import InteractionControllers.Output;

/**
 * PropertyViewers observe PropertyHub and updates viewable
 * listings according to its filter
 */
public class PropertyViewer implements Observer {

    /**
     * Class fields
     */
    private String ownerEmail;
    private PropertyHub subject;
    private ArrayList<Property> viewableProperties;
    private Filter filter;
    private boolean subscribed;
    
    /**
     * PropertyViewer constructor
     * @param ownerEmail User's email
     */
    public PropertyViewer(String ownerEmail) 
    {
        this.ownerEmail = ownerEmail;
        this.subscribed = true;
        this.filter = new FilterBuilder().build();
        this.subject = PropertyHub.getInstance();
        subject.addObserver(this);
    }

    /**
     * PropertyViewer constructor for program initialization
     * @param ownerEmail Pre-existing user's email
     * @param filter Pre-existing user's filter
     * @param subscribed Pre-existing user's subscription status
     */
    public PropertyViewer(String ownerEmail, Filter filter, boolean subscribed) 
    {
        this.ownerEmail = ownerEmail;
        this.subscribed = subscribed;
        this.filter = filter;
        this.subject = PropertyHub.getInstance();
        subject.addObserver(this);
    }

    /**
     * Updates the filter by constructing new Filter
     * @param accountID User's account ID
     */
    public void updateFilter(int accountID) 
    { 
        filter = FilterBuilder.buildFilter();
        viewableProperties = filter.filterAll(PropertyHub.getPropertyList());
        DatabaseController.getInstance().updateFilter(accountID, filter);
    }

    /**
     * Update PropertyViewer will new property list
     */
    public void updateObserver(ArrayList<Property> properties, int propertyID)
    {
        int oldSize = viewableProperties.size();
        viewableProperties = filter.filterAll(properties);
        if(subscribed && viewableProperties.size() > oldSize)
        {
                System.out.println("Send email");
                //EmailController.sendNotification(ownerEmail, newProperty.getPropertyID());
        }
    }

    /**
     * Updates subscription status of user
     * @param accountID User's account ID
     */
    public void updateSubscription(int accountID) 
    { 
        subscribed = Input.getBoolInput("Continue Subscribing?");
        DatabaseController.getInstance().updateSubscription(accountID, subscribed);
    }

    /**
     * Method to initialize observer
     */
    public void initializeObserver(ArrayList<Property> newProperties) { viewableProperties = filter.filterAll(newProperties); }

    /**
     * Method to view all filter viewable properties
     */
    public void viewProperties() { Output.displayProperties(viewableProperties); }

    // =================
    // STATIC METHODS
    // =================

    /**
     * View all properties for unregistered users
     */
    public static void unregisteredViewProperties() 
    {
        ArrayList<Property> properties = PropertyHub.getPropertyList();
        Filter tempFilter = FilterBuilder.buildFilter();
        properties = tempFilter.filterAll(properties);
        Output.displayProperties(properties);
    }
    
    /**
     * Generic view method to view a property list
     * @param properties Property list
     */
    public static void viewProperties(ArrayList<Property> properties)
    {
        Output.displayProperties(properties);
    }

    public static void main(String[] args)
    {
    }
}