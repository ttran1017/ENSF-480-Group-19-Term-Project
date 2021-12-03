package SystemControllers;

import java.util.*;

import InteractionControllers.Input;
import Models.*;
import Interfaces.*;
import Interfaces.Observer;

public final class PropertyHub implements Subject {

    private static PropertyHub INSTANCE;
    private DatabaseController database;
    private ArrayList<Property> propertyList;
    private ArrayList<PropertyViewer> viewers;
    
    private PropertyHub() {
        database = DatabaseController.getInstance();
        propertyList = new ArrayList<>();
        viewers = new ArrayList<>();
    }

    public static PropertyHub getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new PropertyHub();
        }
        return INSTANCE;
    }

    public static void createProperty(String email) {
        String type = (String)Input.getDropdownInput(
            "Property Type Select", 
            "Select Type:",
            new String[]
            {
                "Apartment",
                "Attatched House",
                "Detched House",
                "Townhouse",
                "Condominium"
            }
        );
        String address = Input.getStringInput("Enter Street Address (w/o Quadrant)");
        String quadrant = (String)Input.getDropdownInput(
            "Quadrant Select", 
            "Select Type:",
            new String[]
            {
                "NE",
                "NW",
                "SE",
                "SW"
            }
        );
        int numBedrooms = Input.getIntInput("Enter Number of Bedrooms");
        int numBathrooms = Input.getIntInput("Enter Number of Bathrooms");
    }

    
    public void addToDatabase(Property property) {
    }

    
    public void updateSystemBalance() {
    }

    
    public ArrayList<Property> getPropertyList() {
        return null;
    }

    
    public Property getPropertyByID(int id) {
        return null;
    }

    public void addObserver(Observer o) {
    }

    public void removeObserver(Observer o) {
    }

    public void notifyAllObservers() {
    }
}