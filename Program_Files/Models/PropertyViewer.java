package Models;

import Interfaces.Observer;
import Interfaces.PropertyQuadrant;
import Interfaces.PropertyType;
import SystemControllers.EmailController;
import SystemControllers.FilterBuilder;
import SystemControllers.PropertyHub;

import java.util.*;
import InteractionControllers.*;


public class PropertyViewer implements Observer {
    private String ownerEmail;
    private PropertyHub subject;
    private ArrayList<Property> viewableProperties;
    private Filter filter;
    private boolean subscribed;
    
    public PropertyViewer(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        this.subscribed = true;
        this.filter = new FilterBuilder().build();
        this.subject = PropertyHub.getInstance();
        subject.addObserver(this);
    }
    
    // CRITICALLY IMPORTANT !!
    public void updateFilter()
    {
        FilterBuilder newFilter = new FilterBuilder();
        String pType = (String)Input.getDropdownInput(
            "Filter",
            "Preferred Property Type:", new String[]{
            "Apartment",
            "AttachedHouse",
            "DetachedHouse",
            "Condo",
            "Townhouse",
            "No Preference"
            });
        switch(pType){
            case "Apartment":
                newFilter.setPropertyType(PropertyType.Apartment);
            case "AttachedHouse":
                newFilter.setPropertyType(PropertyType.AttachedHouse);
            case "DetachedHouse":
                newFilter.setPropertyType(PropertyType.DetachedHouse);
            case "Condo":
                newFilter.setPropertyType(PropertyType.Condo);
            case "Townhouse":
                newFilter.setPropertyType(PropertyType.Townhouse);
            default:
                break;
        }

        String pQuad = (String)Input.getDropdownInput(
            "Filter",
            "Preferred Property Quadrant:", new String[]{
            "NE",
            "NW",
            "SE",
            "SW",
            "No Preference"
            });
        switch(pQuad){
            case "NE":
                newFilter.setPropertyQuad(PropertyQuadrant.NE);
            case "NW":
                newFilter.setPropertyQuad(PropertyQuadrant.NW);
            case "SE":
                newFilter.setPropertyQuad(PropertyQuadrant.SE);
            case "SW":
                newFilter.setPropertyQuad(PropertyQuadrant.SW);
            default:
                break;
        }
        String min = (String)Input.getDropdownInput(
            "Filter",
            "Minimum Number of Bedrooms:", new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "No Preference"
            });
        switch(min){
            case "1":
                newFilter.setMinBedroom(1);
            case "2":
                newFilter.setMinBedroom(2);
            case "3":
                newFilter.setMinBedroom(3);
            case "4":
                newFilter.setMinBedroom(4);
            case "5":
                newFilter.setMinBedroom(5);
            case "6":
                newFilter.setMinBedroom(6);
            case "7":
                newFilter.setMinBedroom(7);
            case "8":
                newFilter.setMinBedroom(8);
            case "9":
                newFilter.setMinBedroom(9);
            case "10":
                newFilter.setMinBedroom(10);
            default:
                break;
        }
        String max = (String)Input.getDropdownInput(
            "Filter",
            "Maximum Number of Bedrooms:", new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "No Preference"
            });
        switch(max){
            case "1":
                newFilter.setMaxBedroom(1);
            case "2":
                newFilter.setMaxBedroom(2);
            case "3":
                newFilter.setMaxBedroom(3);
            case "4":
                newFilter.setMaxBedroom(4);
            case "5":
                newFilter.setMaxBedroom(5);
            case "6":
                newFilter.setMaxBedroom(6);
            case "7":
                newFilter.setMaxBedroom(7);
            case "8":
                newFilter.setMaxBedroom(8);
            case "9":
                newFilter.setMaxBedroom(9);
            case "10":
                newFilter.setMaxBedroom(10);
            default:
                break;
        }

        String furnished = (String)Input.getDropdownInput(
            "Filter",
            "Preference of Furnishment:", new String[]{
            "Furnished",
            "Unfurnished",
            "No Preference"
            });
        switch(furnished){
            case("Furnished"):
                newFilter.setIsFurnished(true);
            case("Unfurnished"):
                newFilter.setIsFurnished(false);
            default:
                break;
        }
        filter = newFilter.build();
    }

    /**
     * View properties for unregistered user
     * - Does not have a preexisting property list
     */
    public static void unregisteredViewProperties() 
    {
    }
    
    /**
     * Generic view method to view a property list
     * @param properties Property list
     */
    public static void viewProperties(ArrayList<Property> properties)
    {
        Output.displayProperties(properties);
    }

    /**
     * To view ALL properties in system
     */
    public void viewProperties() 
    {
        Output.displayProperties(viewableProperties);
    }


    public void updateObserver(Property newProperty){
        if(filter.check(newProperty))
        {
            viewableProperties.add(newProperty);
            if(subscribed)
                EmailController.sendNotification(ownerEmail, newProperty.getPropertyID());  // Send email only when subscribed
        }
    }

    public void initializeObserver(ArrayList<Property> newProperties) {
        viewableProperties = filter.filterAll(newProperties);
    }
}