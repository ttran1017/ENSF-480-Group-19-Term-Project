package Models;

import Interfaces.Observer;
import SystemControllers.EmailController;
import SystemControllers.FilterBuilder;
import SystemControllers.PropertyHub;

import java.util.*;
import InteractionControllers.*;


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

    public void updateFilter()
    {
        filter = FilterBuilder.buildFilter();
    }

    /**
     * To view ALL properties in system
     */
    public void viewProperties() 
    {
        Output.displayProperties(viewableProperties);
    }


    public void updateObserver(Property newProperty){
        if(filter.checkPass(newProperty))
        {
            viewableProperties.add(newProperty);
            if(subscribed)
                EmailController.sendNotification(ownerEmail, newProperty.getPropertyID());  // Send email only when subscribed
        }
    }

    public void initializeObserver(ArrayList<Property> newProperties) {
        viewableProperties = filter.filterAll(newProperties);
    }

    public void updateSubscription()
    {
        // Ask user if they want to be subscribed
        // Update subscribed variable
    }

    // STATIC METHODS
    public static void unregisteredViewProperties() 
    {
        ArrayList<Property> properties = PropertyHub.getPropertyList();
        Filter tempFilter = FilterBuilder.buildFilter();
        properties = tempFilter.filterAll(properties);
        System.out.println(properties.size());
        for(Property prop : properties)
        {
            System.out.println(prop.getPropertyAddress());
            System.out.println(prop.getPropertyType());
        }
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