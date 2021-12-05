package SystemControllers;

import java.util.*;
import java.util.stream.Collectors;

import com.mysql.cj.protocol.a.authentication.MysqlOldPasswordPlugin;

import InteractionControllers.*;
import Models.*;
import Interfaces.*;
import Interfaces.Observer;

public final class PropertyHub implements Subject {

    private static PropertyHub INSTANCE;
    private static DatabaseController database;
    private HashMap<Integer, Property> propertyList;
    private ArrayList<Observer> observers;
    
    private PropertyHub() {
        propertyList = new HashMap<Integer, Property>();
        observers = new ArrayList<Observer>();
    }

    public static PropertyHub getInstance()
    {
        if(INSTANCE == null)
        {
            database = DatabaseController.getInstance();
            INSTANCE = new PropertyHub();
        }
        return INSTANCE;
    }

    public static ArrayList<Property> getPropertyList() {
        return new ArrayList<Property>(getInstance().propertyList.values());
    }

    public Property createProperty(String email) {
        PropertyType type = (PropertyType)Input.getDropdownInput(
            "Property Type Select", 
            "Select Type:",
            PropertyType.values()
        );
        String address = Input.getStringInput("Enter Street Address (W/O Quadrant)");
        PropertyQuadrant quadrant = (PropertyQuadrant)Input.getDropdownInput(
            "Quadrant Select", 
            "Select Type:",
            PropertyQuadrant.values()
        );
        int numBedrooms = Input.getIntInput("Enter Number of Bedrooms");
        int numBathrooms = Input.getIntInput("Enter Number of Bathrooms");
        boolean isFurnished = Input.getBoolInput("Is Property Furnished?");
        Property newProperty = new Property(email, type, address, quadrant, PropertyStatus.Cancelled, numBedrooms, numBathrooms, isFurnished, 0);
        int id = database.addProperty(newProperty);
        propertyList.put(id, newProperty);
        return newProperty;
    }

    public void postProperty(ArrayList<Property> properties)
    {
        List<Integer> IDs = properties.stream().filter(prop -> prop.getDaysRemaining() == 0).map(prop -> prop.getPropertyID()).collect(Collectors.toList());
        if(IDs == null)
        {
            Output.outputMessage("No Properties to Post");
            return;
        }
        int selectedID = (Integer)Input.getDropdownInput("Select From the Following Properties", "Property IDs", IDs.toArray());
        if(Input.getBoolInput("The payment fee is $" + FeeController.getFee() + ". Confirm?"))
        {
            FeeController.charge();
            // UPDATES THE USER'S INFO
            for(int i = 0; i < properties.size(); i++)
            {
                if(properties.get(i).getPropertyID() == selectedID)
                {
                    properties.get(i).setPropertyStatus(PropertyStatus.Active);
                    properties.get(i).setDaysRemaining(FeeController.getPeriod());
                    break;
                }
            }
            // UPDATES THE PROPERTY HUB'S INFO
            propertyList.get(selectedID).setPropertyStatus(PropertyStatus.Active);
            propertyList.get(selectedID).setDaysRemaining(FeeController.getPeriod());
            // UPDATES THE DATABASE'S INFO
            Property myProp = propertyList.get(selectedID);
            database.updateListing(myProp);
            notifyAllObservers(myProp);
        }
        else
        {
            Output.outputMessage("Transaction cancelled");
        }
    }
    
    public void updateProperty(ArrayList<Property> properties)
    {
        List<Integer> IDs = properties.stream().map(prop -> prop.getPropertyID()).collect(Collectors.toList());
        if(IDs == null)
        {
            Output.outputMessage("No Properties to Update");
            return;
        }
        int selectedID = (Integer)Input.getDropdownInput("Select From the Following Properties", "Property IDs", IDs.toArray());
        // UPDATE PROPERTY LISTING
        // - property
    }

    public void addObserver(Observer o) {
        observers.add(o);
        o.initializeObserver(getPropertyList());
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyAllObservers(Property property) {
        for(Observer o : observers)
        {
            o.updateObserver(property);
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Property> props = new ArrayList<Property>();
        props.add(new Property());
        props.add(new Property());
        props.add(new Property());
        getInstance().postProperty(props);
    }
}