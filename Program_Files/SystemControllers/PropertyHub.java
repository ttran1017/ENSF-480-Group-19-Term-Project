package SystemControllers;

import java.util.*;
import java.util.stream.Collectors;
import InteractionControllers.*;
import Models.*;
import Interfaces.*;
import Interfaces.Observer;

public final class PropertyHub implements Subject {

    private static PropertyHub INSTANCE;
    private static DatabaseController database = DatabaseController.getInstance();
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
            INSTANCE = new PropertyHub();
        }
        return INSTANCE;
    }

    public static Property createProperty(String email) {
        PropertyType type = (PropertyType)Input.getDropdownInput(
            "Property Type Select", 
            "Select Type:",
            PropertyType.values()
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
        boolean isFurnished = Input.getBoolInput("Is Property Furrnished?");
        Property newProperty = new Property(email, type, address, quadrant, "Suspended", numBedrooms, numBathrooms, isFurnished, 0);
        int id = database.addProperty(newProperty);
        getInstance().propertyList.put(id, newProperty);
        return newProperty;
    }

    public static void PostProperty(ArrayList<Property> properties)
    {
        List<Integer> IDs = properties.stream().filter(prop -> prop.getDaysRemaining() == 0).map(prop -> prop.getPropertyID()).collect(Collectors.toList());
        if(IDs == null)
        {
            Output.outputMessage("No Properties to Display");
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
                    properties.get(i).setDaysRemaining(FeeController.getPeriod());
                    break;
                }
            }
            // UPDATES THE PROPERTY HUB'S INFO
            getInstance().propertyList.get(selectedID).setDaysRemaining(FeeController.getPeriod());
            // UPDATES THE DATABASE'S INFO
            Property myProp = getInstance().propertyList.get(selectedID);
            database.updateListing(myProp);
        }
        else
        {
            Output.outputMessage("Transaction cancelled");
        }
        getInstance().notifyAllObservers();
    }
    
    public ArrayList<Property> getPropertyList() {
        return new ArrayList<Property>(getInstance().propertyList.values());
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyAllObservers(Property property) {
        for(Observer o : observers)
        {
            o.update(property);
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Property> props = new ArrayList<Property>();
        props.add(PropertyHub.createProperty("gogo@gmail.com"));
        PostProperty(props);
    }

    @Override
    public void notifyAllObservers() {
        // TODO Auto-generated method stub
        
    }
}