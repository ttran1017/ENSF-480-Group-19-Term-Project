package Models;

import Interfaces.Observer;
import SystemControllers.FilterBuilder;

import java.util.*;


public class PropertyViewer implements Observer {
    private ArrayList<Property> viewableProperties;
    private Filter filter;
    
    public PropertyViewer() {
    }
    
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
    
    public void viewProperties(Filter filter) {
    }

    public ArrayList<Property> getViewableProperties() {
        return null;
    }

    public void setViewableProperties(ArrayList<Property> viewProps) {
    }

    public void update() {
    }
}