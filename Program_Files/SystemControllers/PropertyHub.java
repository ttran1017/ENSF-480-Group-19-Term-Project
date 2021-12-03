package SystemControllers;

import java.util.*;
import Models.*;
import Interfaces.*;
import Interfaces.Observer;


public class PropertyHub implements Subject {

    
    public PropertyHub() {
    }

    
    private ArrayList<Property> propertyList;

    
    private DatabaseController database;

    
    private ArrayList<PropertyViewer> viewers;




    
    public void createProperty(String propType, String propAddr, String propQuad, int numBed, int numBath, boolean isFurnished) {
        // TODO implement here
    }

    
    public void addToDatabase(Property property) {
        // TODO implement here
    }

    
    public void updateSystemBalance() {
        // TODO implement here
    }

    
    public ArrayList<Property> getPropertyList() {
        // TODO implement here
        return null;
    }

    
    public Property getPropertyByID(int id) {
        // TODO implement here
        return null;
    }

    @Override
    public void addObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void notifyAllObservers() {
        // TODO Auto-generated method stub
        
    }

}