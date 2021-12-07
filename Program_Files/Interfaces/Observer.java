/**
 * FileName: Observer.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Interfaces;

import java.util.ArrayList;
import Models.Property;

/**
 * Observer Interface
 */
public interface Observer {
    /**
     * Updates observer with new Data
     * @param newProperty new Property List
     */
    public void updateObserver(ArrayList<Property> newProperty, int propertyID);

    /**
     * Called for when initialization is different from update
     * @param initialList initial Property List
     */
    public void initializeObserver(ArrayList<Property> initialList);
}