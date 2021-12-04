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
        this.filter = null;
        this.subject = PropertyHub.getInstance();
        viewableProperties = subject.getPropertyList();
        subject.addObserver(this);
    }
    
    // CRITICALLY IMPORTANT !!
    public void updateFilter()
    {
        // ASK TO INPUT ALL THE STUFF FOR FILTER
        if(filter == null)
        {
            filter = new FilterBuilder().build();
        }
    }

    public static void staticViewProperties() {
    }
    
    public void viewProperties() {
        Output.displayProperties(viewableProperties);
    }

    public void update(Property newProperty){
        if(filter.check(newProperty))
        {
            viewableProperties.add(newProperty);
            if(subscribed)
                EmailController.sendNotification(ownerEmail, newProperty.getPropertyID());  // Send email only when subscribed
        }
    }
}