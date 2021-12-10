/**
 * FileName: PropertyHub.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package SystemControllers;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import Enums.PropertyQuadrant;
import Enums.PropertyStatus;
import Enums.PropertyType;
import InteractionControllers.*;
import Models.*;
import Interfaces.*;
import Interfaces.Observer;

/**
 * Singleton Subject which maintains all properties
 * in the system and interfaces with the database
 */
public final class PropertyHub implements Subject {

    /**
     * Class fields
     */
    private static PropertyHub INSTANCE;
    private static DatabaseController database;
    private HashMap<Integer, Property> propertyList;
    private ArrayList<Observer> observers;
    
    /**
     * PropertyHub initialization
     */
    private PropertyHub() 
    {
        propertyList = database.getPropertiesHashMap();
        observers = new ArrayList<Observer>();
    }

    /**
     * Get instance method
     * @return Only instance of PropertyHub
     */
    public static PropertyHub getInstance()
    {
        if(INSTANCE == null)
        {
            database = DatabaseController.getInstance();
            INSTANCE = new PropertyHub();
        }
        return INSTANCE;
    }

    /**
     * Get all properties in the system
     * @return All properties in the system
     */
    public static ArrayList<Property> getPropertyList() { return new ArrayList<Property>(getInstance().propertyList.values()); }

    /**
     * Select a specific property by ID
     * @return A specific property
     */
    public Property selectProperty()
    {
        Integer propertyID = (Integer)IO.getDropdownInput("Property Select", "Select a Property", 
        propertyList.values().stream()
        .filter(prop -> prop.getPropertyStatus() == PropertyStatus.Active)
        .map(prop -> prop.getPropertyID())
        .collect(Collectors.toList())
        .toArray());
        if(propertyID == null)
        {
            IO.outputMessage("Failed to Select Property");
            return null;
        }
        return propertyList.get(propertyID);
    }

    /**
     * Creates a property in the system and updates database
     * @param ownerAccount Property Owner
     * @return Property created
     */
    public Property createProperty(Account ownerAccount) 
    {
        PropertyType type = (PropertyType)IO.getDropdownInput(
            "Property Type Select", 
            "Select Type:",
            PropertyType.values()
        );
        String address = IO.getStringInput("Enter Street Address (W/O Quadrant)");
        PropertyQuadrant quadrant = (PropertyQuadrant)IO.getDropdownInput(
            "Quadrant Select", 
            "Select Type:",
            PropertyQuadrant.values()
        );
        int numBedrooms = IO.getIntInput("Enter Number of Bedrooms");
        int numBathrooms = IO.getIntInput("Enter Number of Bathrooms");
        boolean isFurnished = IO.getBoolInput("Is Property Furnished?");
        if(type == null || address == null || quadrant == null)
        {
            IO.outputMessage("Registration Failed");
            return null;
        }
        Property newProperty = new Property(
            ownerAccount.getAccountID(), 
            ownerAccount.getEmail(), 
            type, 
            address, 
            quadrant, 
            PropertyStatus.Cancelled, 
            numBedrooms, 
            numBathrooms, 
            isFurnished, 
            0
        );
        int id = database.addProperty(newProperty);
        newProperty.setPropertyId(id);
        propertyList.put(id, newProperty);
        return newProperty;
    }

    /**
     * Post a newly registered property
     * @param properties List of properties to post
     */
    public void postProperty(ArrayList<Property> properties)
    {
        List<Integer> IDs = properties.stream()
        .filter(prop -> prop.getPropertyStatus() == PropertyStatus.Cancelled || prop.getPropertyStatus() == PropertyStatus.Suspended)
        .map(prop -> prop.getPropertyID())
        .collect(Collectors.toList());
        if(IDs.size() == 0)
        {
            IO.outputMessage("No Properties to Post");
            return;
        }
        int selectedID = (Integer)IO.getDropdownInput("Select From the Following Properties", "Property IDs", IDs.toArray());
        if(IO.getBoolInput("The payment fee is $" + FeeController.getFee() + ". Confirm?"))
        {
            FeeController.charge();
            for(int i = 0; i < properties.size(); i++)
            {
                if(properties.get(i).getPropertyID() == selectedID)
                {
                    properties.get(i).setPropertyStatus(PropertyStatus.Active);
                    properties.get(i).setDaysRemaining(FeeController.getPeriod());
                    propertyList.put(selectedID,properties.get(i));
                    database.updateListing(properties.get(i));
                    break;
                }
            }
            notifyAllObservers(selectedID);
        }
        else
        {
            IO.outputMessage("Transaction cancelled");
        }
    }

    /**
     * Updates the property status of any property in the system
     */
    public void managerUpdatePropertyStatus()
    {
        List<Integer> IDs = getPropertyList().stream().map(prop -> prop.getPropertyID()).collect(Collectors.toList());
        if(IDs.size() == 0)
        {
            IO.outputMessage("No Properties to Update");
            return;
        }
        Integer selectedID = (Integer)IO.getDropdownInput("Select From the Following Properties", "Property IDs", IDs.toArray());
        if(selectedID == null)
        {
            IO.outputMessage("Failed to Select Property");
            return;
        }
        PropertyStatus selectedStatus = (PropertyStatus)IO.getDropdownInput(
            "Select From the Following Properties", 
            "Property IDs", 
            PropertyStatus.values()
        );
        Property modProperty = propertyList.get(selectedID);
        if(modProperty.getPropertyStatus() == PropertyStatus.Rented || modProperty.getPropertyStatus() == PropertyStatus.Cancelled)
            modProperty.setDaysRemaining(0);
        modProperty.setPropertyStatus(selectedStatus);
        database.updateListing(modProperty);
        database.updateDateListed(selectedID, LocalDate.now().toString());
        notifyAllObservers(selectedID);
    }
    
    /**
     * Updates any active property user owns
     * @param properties List of properties user owns
     */
    public void userUpdatePropertyStatus(ArrayList<Property> properties)
    {
        List<Integer> IDs = properties.stream()
        .map(prop -> prop.getPropertyID())
        .collect(Collectors.toList());
        if(IDs.size() == 0)
        {
            IO.outputMessage("No Properties to Update");
            return;
        }
        Integer selectedID = (Integer)IO.getDropdownInput("Select From the Following Properties", "Property IDs", IDs.toArray());
        if(selectedID == null)
        {
            IO.outputMessage("Failed to Select Property");
            return;
        }
        PropertyStatus selectedStatus = (PropertyStatus)IO.getDropdownInput(
            "Select From the Following Properties", 
            "Property IDs", 
            Arrays.copyOfRange(PropertyStatus.values(), 1, PropertyStatus.values().length)
        );
        for(int i = 0; i < properties.size(); i++)
        {
            if(properties.get(i).getPropertyID() == selectedID)
            {
                properties.get(i).setPropertyStatus(selectedStatus);
                if(properties.get(i).getPropertyStatus() == PropertyStatus.Cancelled)
                    properties.get(i).setDaysRemaining(0);
                else if(properties.get(i).getPropertyStatus() == PropertyStatus.Rented)
                {
                    properties.get(i).setDaysRemaining(0);
                    database.updateDateRented(selectedID, LocalDate.now().toString());
                }
                propertyList.replace(selectedID,properties.get(i));
                database.updateListing(properties.get(i));   
                break;
            }
        }
        notifyAllObservers(selectedID);
    }

    /**
     * Adds an observer and initializes it with data
     */
    public void addObserver(Observer o) 
    {
        observers.add(o);
        o.initializeObserver(getPropertyList());
    }

    /**
     * Removes an observer
     */
    public void removeObserver(Observer o) { observers.remove(o); }

    /**
     * Updates all observers in observer list
     */
    public void notifyAllObservers(int PropertyID) 
    {
        for(Observer o : observers)
            o.updateObserver(getPropertyList(),PropertyID);
    }
}