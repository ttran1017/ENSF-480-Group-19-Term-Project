package SystemControllers;

import java.util.*;
import java.util.stream.Collectors;

import InteractionControllers.Input;
import InteractionControllers.Output;
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
            Property myProp;
            for(int i = 0; i < properties.size(); i++)
            {
                if(properties.get(i).getPropertyID() == selectedID)
                {
                    properties.get(i).setDaysRemaining(FeeController.getPeriod());
                    myProp = properties.get(i);
                    break;
                }
            }
            getInstance().propertyList.get(selectedID).setDaysRemaining(FeeController.getPeriod());
            database.updateListing(
                null, 
                null, 
                null, 
                null, 
                -1, 
                -1, 
                -1, 
                myProp.getIsFurnished(), 
                FeeController.getPeriod());
        }
        else
        {
            Output.outputMessage("Transaction cancelled");
        }
        notifyAllObservers();
    }
    
    public static ArrayList<Property> getPropertyList() {
        return new ArrayList<Property>(getInstance().propertyList.values());
    }

    public static void addObserver(Observer o) {
    }

    public static void removeObserver(Observer o) {
    }

    public static void notifyAllObservers() {
    }

    public static void main(String[] args)
    {
        ArrayList<Property> props = new ArrayList<Property>();
        props.add(new Property(1, "email", "type", "address", "quad", 1, 1, false, 0));
        props.add(new Property(69, "email", "type", "address", "quad", 1, 1, false, 0));
        PostProperty(props);
    }
}