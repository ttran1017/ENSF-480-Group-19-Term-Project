package Models;

import Interfaces.Observer;
import Interfaces.PropertyQuadrant;
import Interfaces.PropertyType;
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
     * View properties for unregistered user
     * - Does not have a preexisting property list
     */
    public static void unregisteredViewProperties(ArrayList<Property> properties) 
    {
        //ArrayList<Property> properties = PropertyHub.getPropertyList();
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

    public static void main(String[] args)
    {
        ArrayList<Property> props = new ArrayList<Property>();
        for(int i = 6; i < 69; i++)
        {
            props.add(new Property());
        }
        unregisteredViewProperties(props);
    }
}