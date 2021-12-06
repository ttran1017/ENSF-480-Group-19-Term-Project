package Interfaces;

import java.util.ArrayList;
import Models.Property;

public interface Observer {
    public void updateObserver(ArrayList<Property> newProperty);
    public void initializeObserver(ArrayList<Property> initialList);
}