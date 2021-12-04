package Models;

import Interfaces.Observer;
import SystemControllers.FilterBuilder;
import SystemControllers.PropertyHub;

import java.util.*;


public class PropertyViewer implements Observer {
    private ArrayList<Property> viewableProperties;
    private boolean subscription;
    private Filter filter;
    
    public PropertyViewer() {
        this.viewableProperties = PropertyHub.getInstance().getPropertyList();
        this.subscription = true;
        this.filter = null;
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
    }

    public void update() {
    }
}