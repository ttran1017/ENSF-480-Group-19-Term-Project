package Models;

import Interfaces.Observer;
import SystemControllers.EmailController;
import SystemControllers.FilterBuilder;
import SystemControllers.PropertyHub;

import java.util.*;
import InteractionControllers.Output;

public class PropertyViewer implements Observer {
    private String ownerEmail;
    private PropertyHub subject;
    private ArrayList<Property> viewableProperties;
    private Filter filter;
    private boolean subscribed;
    
    public PropertyViewer(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        this.subscribed = true;
        this.filter = new FilterBuilder().build();
        this.subject = PropertyHub.getInstance();
        subject.addObserver(this);
    }
    
    // CRITICALLY IMPORTANT !!
    public void updateFilter()
    {
        FilterBuilder newFilter = new FilterBuilder();
        /**
         * TO IMPLEMENT FILTER CRITERIA SELECTION
         */
        filter = newFilter.build();
    }

    /**
     * View properties for unregistered user
     * - Does not have a preexisting property list
     */
    public static void unregisteredViewProperties() 
    {
    }
    
    /**
     * Generic view method to view a property list
     * @param properties Property list
     */
    public static void viewProperties(ArrayList<Property> properties)
    {
        Output.displayProperties(properties);
    }

    /**
     * To view ALL properties in system
     */
    public void viewProperties() 
    {
        Output.displayProperties(viewableProperties);
    }


    public void updateObserver(Property newProperty){
        if(filter.check(newProperty))
        {
            viewableProperties.add(newProperty);
            if(subscribed)
                EmailController.sendNotification(ownerEmail, newProperty.getPropertyID());  // Send email only when subscribed
        }
    }

    public void initializeObserver(ArrayList<Property> newProperties) {
        viewableProperties = filter.filterAll(newProperties);
    }
}