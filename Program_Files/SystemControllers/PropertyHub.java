package SystemControllers;

import java.util.*;
import java.util.stream.Collectors;

import InteractionControllers.Input;
import Models.*;
import Interfaces.*;
import Interfaces.Observer;

public final class PropertyHub implements Subject {

    private static PropertyHub INSTANCE;
    private static DatabaseController database;
    private HashMap<Integer, Property> propertyList;
    private ArrayList<PropertyViewer> viewers;
    
    private PropertyHub() {
        database = DatabaseController.getInstance();
        propertyList = new HashMap<Integer, Property>();
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

    public static Property createProperty(String email) {
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
        String address = Input.getStringInput("Enter Street Address (W/O Quadrant)");
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
        int id = database.addProperty(email, type, address,quadrant,numBedrooms,numBathrooms, false, 0);
        Property newProperty = new Property(id, email, type, address, quadrant, numBedrooms, numBathrooms, false, 0);
        getInstance().propertyList.put(id, newProperty);
        return newProperty;
    }

    public static void PostProperty(ArrayList<Property> properties)
    {
        List<Integer> IDs = properties.stream().filter(prop -> prop.isPosted()).map(prop -> prop.getPropertyID()).collect(Collectors.toList());
        Input.getDropdownInput("Select From the Following Properties", "Property IDs", IDs.toArray());
    }
    
    public static ArrayList<Property> getPropertyList() {
        return new ArrayList<Property>(getInstance().propertyList.values());
    }

    public void updateSystemBalance() {
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

    public static void main(String[] args)
    {
        ArrayList<Property> props = new ArrayList<Property>();
        props.add(new Property(1, "email", "type", "address", "quad", 1, 1, false, 0));
        props.add(new Property(69, "email", "type", "address", "quad", 1, 1, false, 0));
        PostProperty(props);
    }
}