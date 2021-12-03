package System Controller;

import java.util.*;

/**
 * 
 */
public class PropertyHub implements Subject {

    /**
     * Default constructor
     */
    public PropertyHub() {
    }

    /**
     * 
     */
    private ArrayList<Property> propertyList;

    /**
     * 
     */
    private DatabaseController database;

    /**
     * 
     */
    private ArrayList<PropertyViewer> viewers;




    /**
     * @param propType 
     * @param propAddr 
     * @param propQuad 
     * @param numBed 
     * @param numBath 
     * @param isFurnished 
     * @return
     */
    public void createProperty(String propType, String propAddr, String propQuad, int numBed, int numBath, boolean isFurnished) {
        // TODO implement here
        return null;
    }

    /**
     * @param property
     */
    public void addToDatabase(Property property) {
        // TODO implement here
    }

    /**
     * @param o 
     * @return
     */
    public void addObserver(Observer o) {
        // TODO implement here
        return null;
    }

    /**
     * @param o 
     * @return
     */
    public void removeObserver(Observer o) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void notifyAllObserver() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void updateSystemBalance() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Property> getPropertyList() {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Property getPropertyByID(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param o 
     * @return
     */
    public void addObserver(Observer o) {
        // TODO implement here
        return null;
    }

    /**
     * @param o 
     * @return
     */
    public void removeObserver(Observer o) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void notifyAllObservers() {
        // TODO implement here
        return null;
    }

}