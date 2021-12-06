package Interfaces;
import java.util.ArrayList;

import Models.Property;

public interface Subject {

    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyAllObservers(ArrayList<Property> properties);

}