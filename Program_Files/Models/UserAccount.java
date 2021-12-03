package Models;

import java.util.*;
import Interfaces.*;


public class UserAccount implements Account {

    
    public UserAccount() {
    }


    private String username;
    private ArrayList<Property> ownedProperties;
    private PropertyViewer viewer;
    private Filter filter;
    private boolean subscription;


    
    public void registerProperty(String propType, String propAddr, String propQuad, int numBed, int numBath, boolean isFurnished) {
        // TODO implement here
    }


    public void updateListing() {
    }

    public void uploadProperty() {
    }

    public void Operation1() {
    }

    public void changeSubscription() {
    }

    public void UserAccount() {
    }

    public ArrayList<Property> getOwnedProperties() {
        return null;
    }

    public PropertyViewer getViewer() {
        return null;
    }

    public Filter getFilter() {
        return null;
    }

    public boolean getSubscription() {
        return false;
    }

    public void setOwnedProperties(ArrayList<Property> ownedProps) {
    }

    public void setViewer(PropertyViewer viewer) {
    }

    public void setFilter(Filter filter) {
    }

    public void setSubscription(boolean subscription) {
    }

    public void sendEmail() {
    }

    public void viewProperties() {
    }

}